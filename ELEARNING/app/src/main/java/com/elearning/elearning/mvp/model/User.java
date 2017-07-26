package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class User {
    /**
     * Only one User instance throw application
     * Model user login
     */
    private static User instance;
    private String token;
    private int userId;
    private int role;
    private String urlAvatar;
    private String userName;
    /**
     * Model user information
     */
    private String password;
    private String lastName;
    private String firstName;
    private boolean gender;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String infomation;
    private String description;

    private User() {

    }

    public static User get() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public int getRole() {
        return role;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getInfomation() {
        return infomation;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void init(JSONObject response) {
        try {
            setToken(response.get(APIConstant.TOKEN).toString());
            setRole(response.getInt(APIConstant.ROLE));
            setUserId(response.getInt(APIConstant.UID));
            setUserName(response.getString(APIConstant.USERNAME));
            setUrlAvatar(response.getString(APIConstant.AVATAR));
        } catch (Exception e) {

        }
    }

    public User setInformation(JSONObject response) {
        try {
            setUserName(response.getString(APIConstant.LASTNAME)+"  "+ response.getString(APIConstant.FIRSTNAME));
            setFirstName(response.getString(APIConstant.FIRSTNAME));
            setLastName(response.getString(APIConstant.LASTNAME));
            setPassword(response.getString(APIConstant.PASSWORD));
            setEmail(response.getString(APIConstant.EMAIL));
            setUrlAvatar(response.getString(APIConstant.AVATAR));
            setAddress(response.getString(APIConstant.ADDRESS));
            setDateOfBirth(DATE_FORMAT_YYYYMMDD.parse(response.getString(APIConstant.DATEOFBIRTH).substring(0, 10)));
            setInfomation(response.getString(APIConstant.INFOMATION));
            setPhone(response.getString(APIConstant.PHONE));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
