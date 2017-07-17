package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.mvp.view.LessonView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.elearning.elearning.mvp.model.Lesson.parseLesson;

/**
 * Created by MinhQuan on 04/07/2017.
 */

public class LessonPresenter {
    private LessonView lessonView;

    public LessonPresenter(LessonView lessonView) {
        this.lessonView = lessonView;
    }

    public void getProgressLearn(int idCourse) {
        API.getProgressLearn(String.valueOf(idCourse), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {
                lessonView.onGetProgressSuccess(response);
            }

            @Override
            public void onError(String errorMessage) {
                lessonView.onGetProgressFail(errorMessage);
            }
        });
    }

    public void getInformationLesson(int idLesson, final onGetInfoLesson onGetInfoLesson) {
        API.getInformationLesson(String.valueOf(idLesson), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {
                onGetInfoLesson.getInfoSuccess(parseLesson(response));
            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                onGetInfoLesson.getInfoFail(errorMessage);
            }
        });
    }

    public interface onGetInfoLesson {
        void getInfoSuccess(Lesson lesson);

        void getInfoFail(String mess);
    }
}
