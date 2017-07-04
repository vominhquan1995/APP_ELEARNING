package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.mvp.view.ListLessonView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class ListLessonPresenter {
    private ListLessonView listLessonView;

    public ListLessonPresenter(ListLessonView listLessonView) {
        this.listLessonView = listLessonView;
    }

    public void getListLesson(int idCourse) {
        API.listLesson(String.valueOf(idCourse), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                listLessonView.onGetListSuccess(Lesson.getListLesson(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                listLessonView.onGetListFail(errorMessage);
            }
        });
    }
}
