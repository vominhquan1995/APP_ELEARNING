package com.elearning.elearning.fragment;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.mvp.presenter.LessonPresenter;
import com.elearning.elearning.mvp.view.LessonView;
import com.elearning.elearning.network.APIConstant;
import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by MinhQuan on 04/07/2017.
 */

public class LessonFragment extends BaseFragment implements LessonView {
    private LessonPresenter lessonPresenter;
    private Lesson lessonItem = new Lesson();
    private WebView webView;
    private PDFView PDFView;
    private TextView name;
    private TextView time;
    private TextView typeLesson;
    private TextView description;

    @Override
    public void initView() {
        webView = (WebView) view.findViewById(R.id.wvLesson);
//        PDFView = (PDFView) view.findViewById(R.id.pdfView);
        name = (TextView) view.findViewById(R.id.txtNameLesson);
        time = (TextView) view.findViewById(R.id.txtTime);
        typeLesson = (TextView) view.findViewById(R.id.txtTypeLesson);
        description = (TextView) view.findViewById(R.id.txtDescription);
    }

    @Override
    public void initValue() {
        lessonPresenter = new LessonPresenter(this);
    }

    @Override
    public void initAction() {
        ListLessonFragment.setOnSendLessonItem(new ListLessonFragment.onSendLessonItem() {
            @Override
            public void onSend(Lesson lesson) {
                lessonItem = lesson;
                setUI();
            }
        });
        view.findViewById(R.id.btnDoExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setIdLesson(lessonItem.getId());
            }
        });
    }

    public void setUI() {
        if (lessonItem != null) {
            Log.d("Lesson", APIConstant.HOST_NAME_IMAGE + lessonItem.getLessonUrl());
            webView.loadUrl("http://apielearning.azurewebsites.net/FilesUploaded/10_06_2017_09_45_49nature%20sounds%20forest%201%20minute%20HD%201080p.mp4");
//            String doc="<iframe src='http://docs.google.com/viewer?url=http://apielearning.azurewebsites.net/FilesUploaded/10_06_2017_09_39_31pdf-test.pdf&embedded=true'  style='border: none;'></iframe>";
//            webView.setVisibility(WebView.VISIBLE);
//            webView.getSettings().setJavaScriptEnabled(true);
//            webView.loadData(doc, "text/html", "UTF-8");
//            Uri myUri = Uri.parse("http://apielearning.azurewebsites.net/FilesUploaded/10_06_2017_09_39_31pdf-test.pdf");
//            PDFView.fromUri(myUri).load();
//            webView.loadUrl(APIConstant.HOST_NAME_IMAGE + lessonItem.getLessonUrl());

            //set information
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
}
