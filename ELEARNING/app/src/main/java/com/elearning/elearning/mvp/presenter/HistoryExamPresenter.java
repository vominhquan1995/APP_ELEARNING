package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.HistoryExam;
import com.elearning.elearning.mvp.view.HistoryExamView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 11/07/2017.
 */

public class HistoryExamPresenter {
    private HistoryExamView historyExamView;

    public HistoryExamPresenter(HistoryExamView historyExamView) {
        this.historyExamView = historyExamView;
    }
    public void   getListHistoryExam(){
        API.getHistoryExamAll(new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                historyExamView.onGetListHistory(HistoryExam.parseListHistory(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                historyExamView.onGetListHistoryFail(errorMessage);
            }
        });
    }
}
