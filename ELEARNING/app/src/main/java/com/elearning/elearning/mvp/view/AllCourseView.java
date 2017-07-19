package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Course;

import java.util.List;

/**
 * Created by MinhQuan on 20/07/2017.
 */

public interface AllCourseView {
    void onFilterRate(List<Course> courseArray);

    void onFilterPrice(List<Course> courseArray);

    void onFilterFail(String mess);
}
