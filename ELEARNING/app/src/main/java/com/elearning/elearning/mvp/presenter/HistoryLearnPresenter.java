package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.HistoryLearnExam;
import com.elearning.elearning.mvp.view.HistoryLearnView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 09/07/2017.
 */

public class HistoryLearnPresenter {
    private HistoryLearnView historyLearnView;

    public HistoryLearnPresenter(HistoryLearnView historyLearnView) {
        this.historyLearnView = historyLearnView;
    }

    public void getHistory() {
        API.getHistory(new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                historyLearnView.onGetHistorySuccess(HistoryLearnExam.parseHistoryLearn(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                historyLearnView.onGetHistoryFail(errorMessage);
            }
        });
    }
}
