package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.view.HomeView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class HomePresenter {
    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    public void getListNewCourse(int numberItem) {
        API.listNewCourse(String.valueOf(numberItem), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                homeView.onGetNewCourseSuccess(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                homeView.onGetNewCourseFail(errorMessage);
            }
        });
    }

    public void getListMostCourse(int numberItem) {
        API.listMostCourse(String.valueOf(numberItem), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                homeView.onGetMostCourseSuccess(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                homeView.onGetMostCourseFail(errorMessage);
            }
        });
    }

    public void getListTopReviewCourse(int numberItem) {
        API.listReviewCourse(String.valueOf(numberItem), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                homeView.onGetTopReviewCourseSuccess(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                homeView.onGetTopReviewCourseFail(errorMessage);
            }
        });
    }
}
