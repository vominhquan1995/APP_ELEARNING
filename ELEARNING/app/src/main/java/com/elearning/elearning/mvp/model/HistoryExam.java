package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONObject;

import java.util.Date;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 06/07/2017.
 */

public class HistoryExam {
    private int idExam;
    private Date dateExam;
    private int point;
    private String status;

    HistoryExam() {

    }

    public HistoryExam(int idExam, Date dateExam, int point, String status) {
        this.idExam = idExam;
        this.dateExam = dateExam;
        this.point = point;
        this.status = status;
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
            historyExam.setPoint(jsonObject.getInt(APIConstant.POINTEXAM));
            historyExam.setStatus(jsonObject.getString(APIConstant.EXAMSTATUS));
            historyExam.setDateExam(DATE_FORMAT_YYYYMMDD.parse(jsonObject.getString(APIConstant.EXAMTIME).substring(0, 10)));
            return historyExam;
        } catch (Exception ex) {
            return null;
        }
    }
}
