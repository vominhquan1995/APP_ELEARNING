package com.elearning.elearning.fragment;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.dialog.DialogConfirm;
import com.elearning.elearning.dialog.DialogResult;
import com.elearning.elearning.mvp.model.Exam;
import com.elearning.elearning.mvp.model.HistoryExam;
import com.elearning.elearning.mvp.model.Question;
import com.elearning.elearning.mvp.presenter.ExamPresenter;
import com.elearning.elearning.mvp.view.ExamView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.elearning.elearning.prefs.Constant.MSG_FAIL;
import static com.elearning.elearning.prefs.Constant.POS_DOWN;
import static com.elearning.elearning.prefs.Constant.POS_UP;
import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT;
import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by MinhQuan on 05/07/2017.
 */

public class ExamFragment extends BaseFragment implements ExamView, View.OnClickListener {
    private ExamPresenter examPresenter;
    private TextView txtNameExam_Do, txtNameExam, txtNumberQuestion, txtTime, txtRequest, txtLastPoint, txtLastTime, txtLastStatus, txtTimeCountdown;
    private TextView txtContentQuestion, txtIndexQuestion, txtNumberAnswer;
    private FrameLayout frameInfo, frameDoExam;
    private RadioGroup rg;
    private Button btnPrev, btnNext, btnDone;
    private List<Question> listQuestionData;
    private Question currentQuestion;
    private int posCurrent, posHeaderAnswerCurrent;
    private String[] listHeaderQuestion = new String[]{"A", "B", "C", "D", "E", "F"};
    private Exam examInfo;
    private ProgressBar prTimeCountdown;
    private ArrayAnswer arrayAnswer;
    private TimeCountdownAsyncTask timeCountdownAsyncTask;
    private  int idLessonSave;

    @Override
    public void initView() {
        txtNameExam = (TextView) view.findViewById(R.id.txtNameExam);
        txtNameExam_Do = (TextView) view.findViewById(R.id.txtNameExam_Do);
        txtNumberQuestion = (TextView) view.findViewById(R.id.txtNumberQuestion);
        txtTime = (TextView) view.findViewById(R.id.txtTime);
        txtRequest = (TextView) view.findViewById(R.id.txtRequestQuestion);
        txtLastPoint = (TextView) view.findViewById(R.id.txtLastPoint);
        txtLastTime = (TextView) view.findViewById(R.id.txtLastTime);
        txtLastStatus = (TextView) view.findViewById(R.id.txtLastStatus);
        txtContentQuestion = (TextView) view.findViewById(R.id.txtContentQuestion);
        txtIndexQuestion = (TextView) view.findViewById(R.id.txtIndexQuestion);
        txtTimeCountdown = (TextView) view.findViewById(R.id.txtTimeCountdown);
        txtNumberAnswer = (TextView) view.findViewById(R.id.txtNumberAnswer);
        frameInfo = (FrameLayout) view.findViewById(R.id.frInfoExam);
        frameDoExam = (FrameLayout) view.findViewById(R.id.frDoExam);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnPrev = (Button) view.findViewById(R.id.btnPrev);
        btnDone = (Button) view.findViewById(R.id.btnDone);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        prTimeCountdown = (ProgressBar) view.findViewById(R.id.prTimeCountdown);
    }

    @Override
    public void initValue() {
//        examPresenter = new ExamPresenter(this);
//        listQuestionData = new ArrayList<>();
//        arrayAnswer = new ArrayAnswer();
//        posCurrent = 0;
//        posHeaderAnswerCurrent = 0;
        LessonFragment.setOnDoExam(new LessonFragment.onDoExam() {
            @Override
            public void onDoExam(int idLesson) {
                examPresenter.getInfoExam(idLesson);
                idLessonSave=idLesson;
                showProgressDialog();
            }
        });
        init();
    }

    @Override
    public void initAction() {
        //examPresenter.getInfoExam(lessonId);
        showProgressDialog();
        view.findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameDoExam.setVisibility(View.VISIBLE);
                frameInfo.setVisibility(View.GONE);
                showProgressDialog();
                Log.d("Exam id", String.valueOf(examInfo.getIdExam()));
                examPresenter.getListQuestion(examInfo.getIdExam(), new ExamPresenter.onGetListQuestion() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void getListQuestionSuccess(List<Question> listQuestion) {
                        initDoExam(listQuestion);
                    }

                    @Override
                    public void getListQuestionFail(String mess) {
                        Log.d("Exam", "get question fail");
                    }
                });
            }
        });
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //get index of radio checked
                int indexChecked = rg.indexOfChild(view.findViewById(rg.getCheckedRadioButtonId()));
                if (currentQuestion.getAnswerList().get(indexChecked).getIdAnswer() != -1) {
                    arrayAnswer.add(currentQuestion.getIdQuestion(), currentQuestion.getAnswerList().get(indexChecked).getIdAnswer(), indexChecked);
                }
                //update number question answer
                txtNumberAnswer.setText(String.format(getResources().getString(R.string.cap_number_answered), String.valueOf(arrayAnswer.getSize()), String.valueOf(examInfo.getNumberQuesion())));
            }
        });
        MainActivity.setOnExitExam(new MainActivity.onExitExam() {
            @Override
            public void onExitExam() {
                if(frameDoExam.getVisibility()==View.VISIBLE){
                    new DialogConfirm.Build(getMainActivity())
                            .setTxtTitle(getResources().getString(R.string.cap_done_exam))
                            .setTxtBody(getResources().getString(R.string.cap_ask_done_exam))
                            .setOnLogoutListener(new DialogConfirm.Build.OnLogoutListener() {
                                @Override
                                public void onConfirm() {
                                    stopCountDown();
                                    showProgressDialog();
                                }

                                @Override
                                public void onCancel() {

                                }
                            }).show();
                }else{
                    getMainActivity().backOnePage();
                }
            }
        });
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_exam;
    }

    @Override
    public void onGetInfoSuccess(Exam exam) {
        examInfo = exam;
        //isadded Rreturn true if the fragment is currently added to its activity.
        if(isAdded()){
            txtNameExam.setText(String.format(getResources().getString(R.string.exam_name), examInfo.getNameExam()));
            txtNumberQuestion.setText(String.format(getResources().getString(R.string.exam_number_question), String.valueOf(examInfo.getNumberQuesion())));
            txtTime.setText(String.format(getResources().getString(R.string.exam_time), String.valueOf(examInfo.getTimeExam())));
            txtRequest.setText(String.format(getResources().getString(R.string.exam_request_question), String.valueOf(Math.round(examInfo.getNumberQuesion() * (0.75))), String.valueOf(exam.getNumberQuesion())));
            examPresenter.getHistoryExam(examInfo.getIdExam(), new ExamPresenter.onGetHistory() {
                @Override
                public void onGetHistorySuccess(HistoryExam historyExam) {
                    if (historyExam != null) {
                        dismissProgressDialog();
                        txtLastTime.setText(String.format(getResources().getString(R.string.exam_history_time), DATE_FORMAT.format(historyExam.getDateExam())));
                        txtLastPoint.setText(String.format(getResources().getString(R.string.exam_history_point), String.valueOf(historyExam.getPoint())));
                        txtLastStatus.setText(String.format(getResources().getString(R.string.exam_history_result), historyExam.getStatus()));
                    } else {
                        dismissProgressDialog();
                        txtLastTime.setText(String.format(getResources().getString(R.string.exam_history_time), getResources().getString(R.string.cap_no_data)));
                        txtLastPoint.setText(String.format(getResources().getString(R.string.exam_history_point), getResources().getString(R.string.cap_no_data)));
                        txtLastStatus.setText(String.format(getResources().getString(R.string.exam_history_result), getResources().getString(R.string.cap_no_data)));
                    }
                }

                @Override
                public void onGetHistoryFail(String mess) {
                    dismissProgressDialog();
                    txtLastTime.setText(String.format(getResources().getString(R.string.exam_history_time), getResources().getString(R.string.cap_no_data)));
                    txtLastPoint.setText(String.format(getResources().getString(R.string.exam_history_point), getResources().getString(R.string.cap_no_data)));
                    txtLastStatus.setText(String.format(getResources().getString(R.string.exam_history_result), getResources().getString(R.string.cap_no_data)));
                }
            });
        }
    }

    @Override
    public void onGetInfoFail(String mess) {
        //handle show dialong warning in there
        dismissProgressDialog();
        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceiveReuslt(int point, String mess) {
        dismissProgressDialog();
        playSound();
        Drawable drawable;
        if (mess.equals(MSG_FAIL)) {
            drawable = getResources().getDrawable(R.drawable.result_fail);
        } else {
            drawable = getResources().getDrawable(R.drawable.result_pass);
        }
        new DialogResult.Build(getMainActivity())
                .setTxtStatus(mess)
                .setImgStatus(drawable)
                .setTxtBody(String.format(context.getResources().getString(R.string.cap_reulst), String.valueOf(point), String.valueOf(examInfo.getNumberQuesion())))
                .setOnLogoutListener(new DialogResult.Build.OnLogoutListener() {
                    @Override
                    public void onOk() {
                        examPresenter.getInfoExam(idLessonSave);
                        init();
                    }
                }).show();
    }


    @Override
    public void onReceiveReusltFail(String mess) {
        dismissProgressDialog();
        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
    }

    private void setChoice() {
        if (arrayAnswer.getLastChoice(currentQuestion.getIdQuestion()) != -1) {
            ((RadioButton) rg.getChildAt(arrayAnswer.getLastChoice(currentQuestion.getIdQuestion()))).setChecked(true);
        }
    }

    private void setQuestion(int posCurrent) {
        if (listQuestionData.size() != 0) {
            currentQuestion = listQuestionData.get(posCurrent);
            txtContentQuestion.setText(currentQuestion.getContentQuestion());
            txtIndexQuestion.setText(String.format(getResources().getString(R.string.cap_index_question), String.valueOf(posCurrent + 1)));
            rg.removeAllViews();
            posHeaderAnswerCurrent = 0;
            for (Question.Answer answer : currentQuestion.getAnswerList()) {
                RadioButton radioButton = new RadioButton(context);
                radioButton.setButtonDrawable(R.drawable.custom_answer_choice);
                radioButton.setText(String.format(getResources().getString(R.string.cap_struct_answer), listHeaderQuestion[posHeaderAnswerCurrent], answer.getContentAnswer()));
                radioButton.setPadding(10, 0, 0, 0);
                rg.addView(radioButton);
                RadioGroup.LayoutParams params = (RadioGroup.LayoutParams) radioButton.getLayoutParams();
                params.setMargins(0, 15, 0, 15);
                radioButton.setLayoutParams(params);
                posHeaderAnswerCurrent++;
            }
            rg.invalidate();
        }
    }

    private void updatePosCurrent(String type) {
        if (listQuestionData.size() != 0) {
            switch (type) {
                case POS_UP:
                    if (posCurrent == listQuestionData.size() - 1) {
                        posCurrent = 0;
                    } else {
                        posCurrent++;
                    }
                    break;
                case POS_DOWN:
                    if (posCurrent == 0) {
                        posCurrent = listQuestionData.size() - 1;
                    } else {
                        posCurrent--;
                    }
                    break;
            }
            setQuestion(posCurrent);
            setChoice();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPrev:
                updatePosCurrent(POS_DOWN);
                break;
            case R.id.btnNext:
                updatePosCurrent(POS_UP);
                break;
            case R.id.btnDone:
                new DialogConfirm.Build(getMainActivity())
                        .setTxtTitle(getResources().getString(R.string.cap_done_exam))
                        .setTxtBody(getResources().getString(R.string.cap_ask_done_exam))
                        .setOnLogoutListener(new DialogConfirm.Build.OnLogoutListener() {
                            @Override
                            public void onConfirm() {
                                stopCountDown();
                                showProgressDialog();
                            }

                            @Override
                            public void onCancel() {

                            }
                        }).show();
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initDoExam(List<Question> listQuestion) {
        dismissProgressDialog();
        txtNameExam_Do.setText(examInfo.getNameExam());
        prTimeCountdown.setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_background_main_blue)));
        txtNumberAnswer.setText(String.format(getResources().getString(R.string.cap_number_answered), "0", String.valueOf(examInfo.getNumberQuesion())));
        listQuestionData = listQuestion;
        prTimeCountdown.setMax(examInfo.getTimeExam() * 60);
        startCountDown();
        setQuestion(0);
    }

    private void init() {
        frameInfo.setVisibility(View.VISIBLE);
        frameDoExam.setVisibility(View.GONE);
        examPresenter = new ExamPresenter(this);
        listQuestionData = new ArrayList<>();
        arrayAnswer = new ArrayAnswer();
        posCurrent = 0;
        posHeaderAnswerCurrent = 0;
    }

    private void startCountDown() {
        timeCountdownAsyncTask = new TimeCountdownAsyncTask(getMainActivity());
        timeCountdownAsyncTask.execute(examInfo.getTimeExam());
    }

    private void stopCountDown() {
        if (timeCountdownAsyncTask != null) {
            //timeCountdownAsyncTask.cancel(true);
            timeCountdownAsyncTask = null;
        }
    }

    public class TimeCountdownAsyncTask extends AsyncTask<Integer, Integer, Void> {

        private Activity contextParent;

        public TimeCountdownAsyncTask(Activity contextParent) {
            this.contextParent = contextParent;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            //start second
            //don't hanlde UI on there
            int minute = integers[0];
            int second = minute * 60;
            int secondCurrent = 59;
            minute--;
            while (second > 0) {
                if (timeCountdownAsyncTask == null) {
                    second = 0;
                } else {
                    try {
                        Thread.sleep(1000);
                        if (secondCurrent > 0) {
                            secondCurrent--;
                        } else {
                            secondCurrent = 59;
                            minute--;
                        }
                        second--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // call onProgressUpdate
                    publishProgress(minute, secondCurrent, second);
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //start first
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //update UI after receive value from doInBackground
            int minutes = values[0];
            int seconds = values[1];
            int secondCountdown = values[2];
            //update UI
            if (secondCountdown < 60) {
                prTimeCountdown.setProgressTintList(ColorStateList.valueOf(Color.RED));
            }
            prTimeCountdown.setProgress(secondCountdown);
            txtTimeCountdown.setText(String.format(contextParent.getResources().getString(R.string.cap_time_countdown), String.valueOf(minutes), String.valueOf(seconds)));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtTimeCountdown.setText("Hết giờ");
            showProgressDialog();
            examPresenter.checkResult(examInfo.getIdExam(), arrayAnswer.getListAnswer());
        }
    }

    private class ArrayAnswer {
        List<AnswerSend> listAnswerSends;

        ArrayAnswer() {
            listAnswerSends = new ArrayList<>();
        }

        private void add(int idQuestion, int idAnswer, int pos) {
            Iterator<AnswerSend> i = listAnswerSends.iterator();
            while (i.hasNext()) {
                AnswerSend item = i.next();
                if (item.getIdQuestion() == idQuestion) {
                    i.remove();
                }
            }
            this.listAnswerSends.add(new AnswerSend(idQuestion, idAnswer, pos));
        }

        private int getLastChoice(int idQuestion) {
            int pos = -1;
            for (AnswerSend item : listAnswerSends) {
                if (item.getIdQuestion() == idQuestion) {
                    pos = item.getPosCurrent();
                }
            }
            return pos;
        }

        private List<String> getListAnswer() {
            List<String> listAnswer = new ArrayList<>();
            for (AnswerSend item : listAnswerSends) {
                listAnswer.add(String.valueOf(item.getIdAnswer()));
            }
            return listAnswer;
        }

        private int getSize() {
            return this.listAnswerSends.size();
        }
    }

    //model
    private class AnswerSend {
        int idQuestion;
        int idAnswer;
        int posCurrent;

        public AnswerSend(int idQuestion, int number, int posCurrent) {
            this.idQuestion = idQuestion;
            this.idAnswer = number;
            this.posCurrent = posCurrent;
        }

        public int getIdQuestion() {
            return idQuestion;
        }

        public int getIdAnswer() {
            return idAnswer;
        }

        public void setIdQuestion(int idQuestion) {
            this.idQuestion = idQuestion;
        }

        public void setIdAnswer(int idAnswer) {
            this.idAnswer = idAnswer;
        }

        public void setPosCurrent(int posCurrent) {
            this.posCurrent = posCurrent;
        }

        public int getPosCurrent() {
            return posCurrent;
        }
    }
}
