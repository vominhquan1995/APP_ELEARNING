package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.HistoryExam;

import java.util.List;

/**
 * Created by MinhQuan on 11/07/2017.
 */

public interface HistoryExamView {
    void onGetListHistory(List<HistoryExam> historyExamList);

    void onGetListHistoryFail(String mess);
}
