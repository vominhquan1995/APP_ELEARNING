package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Lesson;

import java.util.List;

/**
 * Created by vomin on 04/07/2017.
 */

public interface ListLessonView {
    void onGetListSuccess(List<Lesson> lessonList);

    void onGetListFail(String errorMessafe);
}
