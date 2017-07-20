package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.mvp.model.Course;

import java.util.List;

/**
 * Created by MinhQuan on 20/07/2017.
 */

public interface AllCourseView {
    void onFilterRate(List<Course> courseArray);

    void onFilterPrice(List<Course> courseArray);

    void onFilterCategory(List<Course> courseArray);

    void onGetListCategory(List<Category> listCategory);

    void onFilterFail(String mess);
}
