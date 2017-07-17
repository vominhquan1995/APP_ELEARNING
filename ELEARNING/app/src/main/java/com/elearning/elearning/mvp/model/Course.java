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
 * Created by MinhQuan on 01/07/2017.
 */

public class Course {
    private String nameCourses;
    private String donors;
    private int numberCredits;
    private Date dateStart;
    private Date dateEnd;
    private double price;
    private String description;
    private String urlImage;
    private String teacher;
    private String category;
    private String cetificate;
    private int idTeacher;
    private int idCategory;
    private int idCetificate;
    private int id;
    private Date addTime;
    private Date editTime;

    public Course() {
    }

    public Course(String nameCourses, String donors, int numberCredits, Date dateStart, Date dateEnd, double price, String description, String urlImage, String teacher, String category, String cetificate, int idTeacher, int idCategory, int idCetificate, int id, Date addTime, Date editTime) {
        this.nameCourses = nameCourses;
        this.donors = donors;
        this.numberCredits = numberCredits;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.teacher = teacher;
        this.category = category;
        this.cetificate = cetificate;
        this.idTeacher = idTeacher;
        this.idCategory = idCategory;
        this.idCetificate = idCetificate;
        this.id = id;
        this.addTime = addTime;
        this.editTime = editTime;
    }

    public static List<Course> getListCourse(JSONArray jsonArray) {
        List<Course> courseList = new ArrayList();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                Course course = new Course();
                //TODO remove it
                course.setNameCourses(data.getString(APIConstant.COURSE_NAMECOURSES));
                course.setDonors(data.getString(APIConstant.COURSE_DONORS));
                course.setNumberCredits(data.getInt(APIConstant.COURSE_NUMBERCREDITS));
                course.setDateStart(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.COURSE_DATESTART).substring(0, 10)));
                course.setDateEnd(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.COURSE_DATEEND).substring(0, 10)));
                course.setPrice(data.getDouble(APIConstant.COURSE_PRICE));
                course.setDescription(data.getString(APIConstant.COURSE_DESCRIPTION));
                course.setTeacher(data.getString(APIConstant.COURSE_TEACHER));
                course.setCategory(data.getString(APIConstant.COURSE_CATEGORY));
                course.setCetificate(data.getString(APIConstant.COURSE_CETIFICATE));
                course.setUrlImage(data.getString(APIConstant.COURSE_URLIMAGE));
                course.setIdTeacher(data.getInt(APIConstant.COURSE_IDTEACHER));
                course.setIdCategory(data.getInt(APIConstant.COURSE_IDCATEGORY));
                course.setIdCetificate(data.getInt(APIConstant.COURSE_IDCETIFICATE));
                course.setId(data.getInt(APIConstant.COURSE_ID));
                course.setAddTime(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.COURSE_ADDTIME).substring(0, 10)));
                course.setEditTime(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.COURSE_EDITTIME).substring(0, 10)));
                courseList.add(course);
            }
//            Collections.sort(courseList);
            return courseList;
        } catch (JSONException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNameCourses() {
        return nameCourses;
    }

    public String getDonors() {
        return donors;
    }

    public int getNumberCredits() {
        return numberCredits;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public int getIdCetificate() {
        return idCetificate;
    }

    public int getId() {
        return id;
    }

    public Date getAddTime() {
        return addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCategory() {
        return category;
    }

    public String getCetificate() {
        return cetificate;
    }

    public void setNameCourses(String nameCourses) {
        this.nameCourses = nameCourses;
    }

    public void setDonors(String donors) {
        this.donors = donors;
    }

    public void setNumberCredits(int numberCredits) {
        this.numberCredits = numberCredits;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setIdCetificate(int idCetificate) {
        this.idCetificate = idCetificate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCetificate(String cetificate) {
        this.cetificate = cetificate;
    }
}
