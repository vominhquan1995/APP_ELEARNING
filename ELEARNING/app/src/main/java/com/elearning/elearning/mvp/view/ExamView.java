package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Exam;
import com.elearning.elearning.mvp.model.HistoryExam;

/**
 * Created by MinhQuan on 06/07/2017.
 */

public interface ExamView {
    void onGetInfoSuccess(Exam exam);

    void onGetInfoFail(String mess);

    void onReceiveReuslt(int point,String mess);

    void onReceiveReusltFail(String mess);
}
