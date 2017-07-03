package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Course;

import java.util.List;

/**
 * Created by MinhQuan on 30/06/2017.
 */

public interface MainView {
    void exitApp();

    void onCloseDrawer();

    void onLogout();

    void updateToolbar(boolean isFragment,boolean isSearch, String title);

    void onSearchResult(List<Course> courseList);

    void setItemSelected(String id);
}
