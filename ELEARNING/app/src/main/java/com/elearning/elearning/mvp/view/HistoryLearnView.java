package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.HistoryLearnExam;

import java.util.List;

/**
 * Created by MinhQuan on 09/07/2017.
 */

public interface HistoryLearnView {
    void onGetHistorySuccess(List<HistoryLearnExam> historyLearnExamList);

    void onGetHistoryFail(String message);
}
