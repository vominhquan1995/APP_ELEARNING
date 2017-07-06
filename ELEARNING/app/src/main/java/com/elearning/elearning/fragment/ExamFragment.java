package com.elearning.elearning.fragment;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Exam;
import com.elearning.elearning.mvp.model.HistoryExam;
import com.elearning.elearning.mvp.model.Question;
import com.elearning.elearning.mvp.presenter.ExamPresenter;
import com.elearning.elearning.mvp.view.ExamView;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by MinhQuan on 05/07/2017.
 */

public class ExamFragment extends BaseFragment implements ExamView {
    private ExamPresenter examPresenter;
    private TextView txtNameExam, txtNumberQuestion, txtTime, txtRequest, txtLastPoint, txtLastTime, txtLastStatus;
    private TextView txtContentQuestion;
    private FrameLayout frameInfo, frameDoExam;
    private RadioGroup rg;
    List<Question> listQuestionData;

    @Override
    public void initView() {
        txtNameExam = (TextView) view.findViewById(R.id.txtNameExam);
        txtNumberQuestion = (TextView) view.findViewById(R.id.txtNumberQuestion);
        txtTime = (TextView) view.findViewById(R.id.txtTime);
        txtRequest = (TextView) view.findViewById(R.id.txtRequestQuestion);
        txtLastPoint = (TextView) view.findViewById(R.id.txtLastPoint);
        txtLastTime = (TextView) view.findViewById(R.id.txtLastTime);
        txtLastStatus = (TextView) view.findViewById(R.id.txtLastStatus);
        txtContentQuestion = (TextView) view.findViewById(R.id.txtContentQuestion);
        frameInfo = (FrameLayout) view.findViewById(R.id.frInfoExam);
        frameDoExam = (FrameLayout) view.findViewById(R.id.frDoExam);
        rg = (RadioGroup) view.findViewById(R.id.rg);
    }

    @Override
    public void initValue() {
        examPresenter = new ExamPresenter(this);
        listQuestionData = new ArrayList<>();
    }

    @Override
    public void initAction() {
        examPresenter.getInfoExam(2);
        view.findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameDoExam.setVisibility(View.VISIBLE);
                frameInfo.setVisibility(View.GONE);
                examPresenter.getListQuestion(2, new ExamPresenter.onGetListQuestion() {
                    @Override
                    public void getListQuestionSuccess(List<Question> listQuestion) {
                        Log.d("List Question",String.valueOf(listQuestionData.size()));
                        listQuestionData = listQuestion;
                        setQuestion(0);
                    }

                    @Override
                    public void getListQuestionFail(String mess) {

                    }
                });
            }
        });
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_exam;
    }

    @Override
    public void onGetInfoSuccess(Exam exam) {
        txtNameExam.setText(String.format(getResources().getString(R.string.exam_name), exam.getNameExam()));
        txtNumberQuestion.setText(String.format(getResources().getString(R.string.exam_number_question), String.valueOf(exam.getNumberQuesion())));
        txtTime.setText(String.format(getResources().getString(R.string.exam_time), String.valueOf(exam.getTimeExam())));
        txtRequest.setText(String.format(getResources().getString(R.string.exam_request_question), String.valueOf(Math.round(exam.getNumberQuesion() * (0.75))), String.valueOf(exam.getNumberQuesion())));
        examPresenter.getHistoryExam(2, new ExamPresenter.onGetHistory() {
            @Override
            public void onGetHistorySuccess(HistoryExam historyExam) {
                if (historyExam != null) {
                    txtLastTime.setText(String.format(getResources().getString(R.string.exam_history_time), historyExam.getDateExam()));
                    txtLastPoint.setText(String.format(getResources().getString(R.string.exam_history_point), String.valueOf(historyExam.getPoint())));
                    txtLastStatus.setText(String.format(getResources().getString(R.string.exam_history_result), historyExam.getStatus()));
                }
            }

            @Override
            public void onGetHistoryFail(String mess) {

            }
        });
    }

    @Override
    public void onGetInfoFail(String mess) {
        Log.d(TAG, mess);
    }

    private void setQuestion(int currentQuesiton) {
        if (listQuestionData != null) {
            txtContentQuestion.setText(listQuestionData.get(0).getContentQuestion());
            rg.removeAllViews();
            for (Question.Answer answer : listQuestionData.get(0).getAnswerList()) {
                RadioButton radioButton = new RadioButton(context);
            radioButton.setButtonDrawable(R.drawable.general_comp_btn_radio);
                radioButton.setText(answer.getContentAnswer());
                radioButton.setPadding(10, 0, 0, 0);
                rg.addView(radioButton);
                RadioGroup.LayoutParams params = (RadioGroup.LayoutParams) radioButton.getLayoutParams();
                params.setMargins(0, 5, 0, 5);
                radioButton.setLayoutParams(params);
            }
            ((RadioButton) rg.getChildAt(0)).setChecked(true);
            rg.invalidate();
        }
    }
}
