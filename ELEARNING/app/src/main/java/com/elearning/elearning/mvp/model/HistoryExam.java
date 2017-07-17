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

public class HistoryExam {
    private int idExam;
    private int idLesson;
    private String nameExam;
    private Date dateExam;
    private int point;
    private String status;

    HistoryExam() {

    }

    public HistoryExam(int idExam, int idLesson, String nameExam, Date dateExam, int point, String status) {
        this.idExam = idExam;
        this.idLesson = idLesson;
        this.nameExam = nameExam;
        this.dateExam = dateExam;
        this.point = point;
        this.status = status;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public String getNameExam() {
        return nameExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public int getIdExam() {
        return idExam;
    }

    public Date getDateExam() {
        return dateExam;
    }

    public int getPoint() {
        return point;
    }

    public String getStatus() {
        return status;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public void setDateExam(Date dateExam) {
        this.dateExam = dateExam;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static HistoryExam parseHistory(JSONObject jsonObject) {
        try {
            HistoryExam historyExam = new HistoryExam();
            historyExam.setIdExam(jsonObject.getInt(APIConstant.EXAMID));
            historyExam.setNameExam(jsonObject.getString(APIConstant.EXAMNAME));
            historyExam.setPoint(jsonObject.getInt(APIConstant.POINTEXAM));
            historyExam.setStatus(jsonObject.getString(APIConstant.EXAMSTATUS));
            historyExam.setDateExam(DATE_FORMAT_YYYYMMDD.parse(jsonObject.getString(APIConstant.EXAMTIME).substring(0, 10)));
            return historyExam;
        } catch (Exception ex) {
            return null;
        }
    }

    public static List<HistoryExam> parseListHistory(JSONArray jsonArray) {
        List<HistoryExam> historyExamList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject data = (JSONObject) jsonArray.get(i);
                HistoryExam historyExam = new HistoryExam();
                historyExam.setIdLesson(data.getInt(APIConstant.LESSONID));
                historyExam.setIdExam(data.getInt(APIConstant.EXAMID));
                historyExam.setNameExam(data.getString(APIConstant.EXAMNAME));
                historyExam.setPoint(data.getInt(APIConstant.POINTEXAM));
                historyExam.setStatus(data.getString(APIConstant.EXAMSTATUS));
                historyExam.setDateExam(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.EXAMTIME).substring(0, 10)));
                historyExamList.add(historyExam);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return historyExamList;
    }
}
