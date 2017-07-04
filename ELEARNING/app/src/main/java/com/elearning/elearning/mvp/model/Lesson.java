package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vomin on 04/07/2017.
 */

public class Lesson {
    private String name;
    private int index;
    private int courseId;
    private String lessonUrl;
    private String sourseType;
    private int time;
    private String urlImage;
    private String description;
    private int id;

    public Lesson() {

    }

    public Lesson(String name, int index, int courseId, String lessonUrl, String sourseType, int time, String urlImage, String description, int id) {
        this.name = name;
        this.index = index;
        this.courseId = courseId;
        this.lessonUrl = lessonUrl;
        this.sourseType = sourseType;
        this.time = time;
        this.urlImage = urlImage;
        this.description = description;
        this.id = id;
    }

    public static List<Lesson> getListLesson(JSONArray jsonArray) {
        List<Lesson> lessonList = new ArrayList();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                Lesson lesson = new Lesson();
                lesson.setId(data.getInt(APIConstant.LESSON_ID));
                lesson.setName(data.getString(APIConstant.LESSON_NAME));
                lesson.setIndex(data.getInt(APIConstant.LESSON_INDEX));
                lesson.setCourseId(data.getInt(APIConstant.LESSON_COURSEID));
                lesson.setLessonUrl(data.getString(APIConstant.LESSON_LESSONURL));
                lesson.setSourseType(data.getString(APIConstant.LESSON_SOURSETYPE));
                lesson.setTime(data.getInt(APIConstant.LESSON_TIME));
                lesson.setUrlImage(data.getString(APIConstant.LESSON_URLIMAGE));
                lesson.setDescription(data.getString(APIConstant.LESSON_DESCRIPTION));
                //TODO remove it
                lessonList.add(lesson);
            }
//            Collections.sort(courseList);
            return lessonList;
        } catch (JSONException e) {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public void setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
    }

    public String getSourseType() {
        return sourseType;
    }

    public void setSourseType(String sourseType) {
        this.sourseType = sourseType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
