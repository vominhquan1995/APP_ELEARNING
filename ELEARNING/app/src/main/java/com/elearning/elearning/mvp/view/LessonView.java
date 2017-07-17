package com.elearning.elearning.mvp.view;

/**
 * Created by MinhQuan on 04/07/2017.
 */

public interface LessonView {
    void onGetProgressSuccess(String value);

    void onGetProgressFail(String mess);
}
