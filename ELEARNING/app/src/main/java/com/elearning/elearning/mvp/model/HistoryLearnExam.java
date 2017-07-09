package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 06/07/2017.
 */

public class HistoryLearnExam {
    private int idCourse;
    private String nameCourse;
    private String urlImage;
    private boolean status;

    HistoryLearnExam() {

    }

    public HistoryLearnExam(int idCourse, String nameCourse, String urlImage, boolean status) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.urlImage = urlImage;
        this.status = status;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static List<HistoryLearnExam> parseHistoryLearn(JSONArray jsonArray) {
        List<HistoryLearnExam> historyLearnExams = new ArrayList();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                HistoryLearnExam historyLearnExam = new HistoryLearnExam();
                historyLearnExam.setIdCourse(data.getInt(APIConstant.HISTORY_LEARN_ID));
                historyLearnExam.setNameCourse(data.getString(APIConstant.HISTORY_LEARN_NAMECOURSES));
                historyLearnExam.setUrlImage(data.getString(APIConstant.HISTORY_LEARN_URLIMAGE));
                historyLearnExam.setStatus(data.getBoolean(APIConstant.HISTORY_LEARN_STATUS));
                historyLearnExams.add(historyLearnExam);
            }
            return historyLearnExams;
        } catch (JSONException e) {
            return null;
        }
    }
}
