package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.mvp.view.LessonView;

/**
 * Created by MinhQuan on 04/07/2017.
 */

public class LessonPresenter {
    private LessonView lessonView;

    public LessonPresenter(LessonView lessonView) {
        this.lessonView = lessonView;
    }
}
