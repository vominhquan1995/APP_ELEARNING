package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.view.AllCourseView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 20/07/2017.
 */

public class AllCoursePresenter {
    private AllCourseView allCourseView;

    public AllCoursePresenter(AllCourseView allCourseView) {
        this.allCourseView = allCourseView;
    }

    public void filterRate(int minStar, int maxStar) {
        API.filterRate(String.valueOf(minStar), String.valueOf(maxStar), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                allCourseView.onFilterRate(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                allCourseView.onFilterFail(errorMessage);
            }
        });
    }

    public void filterPrice(int minPrice, int maxPrice) {
        API.filterPrice(String.valueOf(minPrice), String.valueOf(maxPrice), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                allCourseView.onFilterPrice(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                allCourseView.onFilterFail(errorMessage);
            }
        });
    }

    public void filterCategory(int idCategory) {
        API.filterCategory(String.valueOf(idCategory), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                allCourseView.onFilterCategory(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                allCourseView.onFilterFail(errorMessage);
            }
        });
    }

    public void getListCategory() {
        API.listCategory(new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                allCourseView.onGetListCategory(Category.pareListCategory(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                allCourseView.onFilterFail(errorMessage);
            }
        });
    }
}
