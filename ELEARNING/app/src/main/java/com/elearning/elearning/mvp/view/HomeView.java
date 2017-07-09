package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Course;

import java.util.List;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public interface HomeView {
    void onGetNewCourseSuccess(List<Course> courseArray);

    void onGetNewCourseFail(String message);

    void onGetMostCourseSuccess(List<Course> courseArray);

    void onGetMostCourseFail(String message);

    void onGetTopReviewCourseSuccess(List<Course> courseArray);

    void onGetTopReviewCourseFail(String message);
}
