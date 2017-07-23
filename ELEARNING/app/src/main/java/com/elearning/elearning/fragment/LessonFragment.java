package com.elearning.elearning.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.mvp.presenter.LessonPresenter;
import com.elearning.elearning.mvp.view.LessonView;
import com.elearning.elearning.network.APIConstant;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.elearning.elearning.prefs.Constant.TYPE_PDF;
import static com.elearning.elearning.prefs.Constant.TYPE_VIDEO;

/**
 * Created by MinhQuan on 04/07/2017.
 */

public class LessonFragment extends BaseFragment implements LessonView {
    private LessonPresenter lessonPresenter;
    private Lesson lessonItem = new Lesson();
    private WebView webView;
    private PDFView pdfView;
    private ImageView imgFull;
    private TextView name;
    private TextView time, txtTitleProgress;
    private TextView typeLesson;
    private TextView description;
    private static onDoExam onDoExam;
    private LinearLayout lnContent, lnInfo;
    private ReadFilePdf readFilePdf;
    private ProgressBar progressLearn;

    @Override
    public void initView() {
        lnContent = (LinearLayout) view.findViewById(R.id.lnContent);
        lnInfo = (LinearLayout) view.findViewById(R.id.lnInfo);
        webView = (WebView) view.findViewById(R.id.webView);
        pdfView = (PDFView) view.findViewById(R.id.pdfView);
        name = (TextView) view.findViewById(R.id.txtNameLesson);
        time = (TextView) view.findViewById(R.id.txtTime);
        txtTitleProgress = (TextView) view.findViewById(R.id.txtTitleProgress);
        typeLesson = (TextView) view.findViewById(R.id.txtTypeLesson);
        description = (TextView) view.findViewById(R.id.txtDescription);
        imgFull = (ImageView) view.findViewById(R.id.imgFull);
        progressLearn = (ProgressBar) view.findViewById(R.id.prLearn);
        txtTitleProgress.setText(getResources().getString(R.string.cap_title_progress_learn));
    }

    @Override
    public void initValue() {
//        showProgressDialog();
        lessonPresenter = new LessonPresenter(this);
    }

    @Override
    public void initAction() {
        ListLessonFragment.setOnSendLessonItem(new ListLessonFragment.onSendLessonItem() {
            @Override
            public void onSend(Lesson lesson) {
                //set value of lesson
                lessonItem = lesson;
                setUI();
                lessonPresenter.getProgressLearn(lessonItem.getCourseId());
            }
        });
        HistoryExamFragment.setSendIdLesson(new HistoryExamFragment.onSendLessonID() {
            @Override
            public void onSend(int lessonID) {
                showProgressDialog();
                lessonPresenter.getInformationLesson(lessonID, new LessonPresenter.onGetInfoLesson() {
                    @Override
                    public void getInfoSuccess(Lesson lesson) {
                        dismissProgressDialog();
                        lessonItem = lesson;
                        setUI();
                        lessonPresenter.getProgressLearn(lessonItem.getCourseId());
                    }

                    @Override
                    public void getInfoFail(String mess) {
                        dismissProgressDialog();
                        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        view.findViewById(R.id.btnDoExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setIdLesson(lessonItem.getId());
            }
        });
        imgFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lnInfo.getVisibility() == View.VISIBLE) {
                    lnInfo.setVisibility(View.GONE);
                    imgFull.setBackground(getResources().getDrawable(R.drawable.exit_full_screen));
                } else {
                    lnInfo.setVisibility(View.VISIBLE);
                    imgFull.setBackground(getResources().getDrawable(R.drawable.full_screen));
                }
            }
        });
        view.findViewById(R.id.btnDoExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().gotoFragment(context.getResources().getString(R.string.nav_exam));
                if (onDoExam != null) {
                    onDoExam.onDoExam(lessonItem.getId());
                }
            }
        });
    }

    public void setUI() {
        if (lessonItem != null) {
            Log.d("Lesson", APIConstant.HOST_NAME_IMAGE + lessonItem.getLessonUrl());
            if (lessonItem.getSourseType().equals(TYPE_VIDEO)) {
                pdfView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(APIConstant.HOST_NAME_IMAGE + lessonItem.getLessonUrl());
            } else if (lessonItem.getSourseType().equals(TYPE_PDF)) {
                pdfView.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                new ReadFilePdf().execute(APIConstant.HOST_NAME_IMAGE + lessonItem.getLessonUrl());
            } else {
                pdfView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                String url = "https://view.officeapps.live.com/op/view.aspx?src=" + APIConstant.HOST_NAME_IMAGE + lessonItem.getLessonUrl();
                Log.d("Powerpoint", url);
                webView.loadUrl(url);
            }
            name.setText(String.format(context.getResources().getString(R.string.lesson_name), String.valueOf(lessonItem.getName())));
            typeLesson.setText(String.format(context.getResources().getString(R.string.lesson_source_type), String.valueOf(lessonItem.getSourseType())));
            time.setText(String.format(context.getResources().getString(R.string.lesson_time), String.valueOf(lessonItem.getTime())));
            description.setText(String.format(context.getResources().getString(R.string.lesson_description), lessonItem.getDescription()));
        }
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_lesson;
    }

    @Override
    public void onGetProgressSuccess(String value) {
        progressLearn.setProgress(Integer.parseInt(value));
    }

    @Override
    public void onGetProgressFail(String mess) {
        dismissProgressDialog();
        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
    }

    class ReadFilePdf extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.setBackgroundColor(Color.LTGRAY);
            pdfView.fromStream(inputStream)
                    .swipeHorizontal(false)
                    .load();
        }
    }

//    @Override
//    protected void onBackPressed() {
//        super.onBackPressed();
//
//    }

    public static void setOnDoExam(LessonFragment.onDoExam onDoExam) {
        LessonFragment.onDoExam = onDoExam;
    }

    public interface onDoExam {
        void onDoExam(int idLesson);
    }
}
