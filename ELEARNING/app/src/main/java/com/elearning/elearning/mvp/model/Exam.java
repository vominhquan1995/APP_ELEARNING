package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 06/07/2017.
 */

public class Exam {
    private  String nameExam;
    private     int numberQuesion;
    private  int timeExam;
    private int idExam;
    Exam(){

    }

    public Exam(String nameExam, int numberQuesion, int timeExam, int idExam) {
        this.nameExam = nameExam;
        this.numberQuesion = numberQuesion;
        this.timeExam = timeExam;
        this.idExam = idExam;
    }

    public String getNameExam() {
        return nameExam;
    }

    public int getNumberQuesion() {
        return numberQuesion;
    }

    public int getTimeExam() {
        return timeExam;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public void setNumberQuesion(int numberQuesion) {
        this.numberQuesion = numberQuesion;
    }

    public void setTimeExam(int timeExam) {
        this.timeExam = timeExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }
    public static Exam parseInfoExam(JSONObject response) {
        try {
            Exam exam= new Exam();
            exam.setNameExam(response.getString(APIConstant.EXAMNAME));
            exam.setIdExam(response.getInt(APIConstant.IDEXAM));
            exam.setNumberQuesion(response.getInt(APIConstant.NUMBERQUESTION));
            exam.setTimeExam(response.getInt(APIConstant.TIMEEXAM));
            return  exam;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }
    }
}
