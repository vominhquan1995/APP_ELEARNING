package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Exam;
import com.elearning.elearning.mvp.model.HistoryExam;
import com.elearning.elearning.mvp.model.Question;
import com.elearning.elearning.mvp.view.ExamView;
import com.elearning.elearning.network.API;
import com.elearning.elearning.network.APIConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by MinhQuan on 06/07/2017.
 */

public class ExamPresenter {
    private ExamView examView;

    public ExamPresenter(ExamView examView) {
        this.examView = examView;
    }

    public void getInfoExam(int idLesson) {
        API.getInformationExam(String.valueOf(idLesson), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {
                examView.onGetInfoSuccess(Exam.parseInfoExam(response));
            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                examView.onGetInfoFail(errorMessage);
            }
        });
    }

    //get last history
    public void getHistoryExam(int idExam, final onGetHistory onGetHistory) {
        API.getHistoryExam(String.valueOf(idExam), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {
                onGetHistory.onGetHistorySuccess(HistoryExam.parseHistory(response));
            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                onGetHistory.onGetHistoryFail(errorMessage);
            }
        });
    }

    public void getListQuestion(int idExam, final onGetListQuestion onGetListQuestion) {
        API.getListQuestion(String.valueOf(idExam), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                onGetListQuestion.getListQuestionSuccess(Question.parseListQuestion(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                onGetListQuestion.getListQuestionFail(errorMessage);
            }
        });
    }


    public void checkResult(int idEXam, List<String> listIdAnswer) {
        API.checkResult(idEXam, listIdAnswer, new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {
                examView.onReceiveReuslt(response.getInt(APIConstant.NUMBERRIGHT), response.getString(APIConstant.MESSAGE));
            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                examView.onReceiveReusltFail(errorMessage);
            }
        });
    }

    public interface onGetHistory {
        void onGetHistorySuccess(HistoryExam historyExam);

        void onGetHistoryFail(String mess);
    }

    public interface onGetListQuestion {
        void getListQuestionSuccess(List<Question> listQuestion);

        void getListQuestionFail(String mess);
    }
}
