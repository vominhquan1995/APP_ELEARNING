package com.elearning.elearning.network;

/**
 * Created by MinhQuan on 19/05/2017.
 */

public interface APIConstant {

    public static final int CONNECTION_TIMEOUT = 30;
    public static final int COUNT_DOWN_TIME_START = 30; //second

    //Response
    int STATUS_CODE_OK = 200;
    int STATUS_CODE_AUTHORIZE = 401;

    String HOST_NAME = "http://apielearning.azurewebsites.net/api";
    String HOST_NAME_IMAGE = "http://apielearning.azurewebsites.net";
    //Login
    String LOGIN_URL = HOST_NAME + "/accounts/token";
    //Sign up
    String SIGNUP_URL = HOST_NAME + "/accounts/signup";
    //New Course
    String NEW_COURSE_HEADER_URL = HOST_NAME + "/courses/";
    String NEW_COURSE_FOOTER_URL = "/newcourses";
    //Search Course
    String COURSE_SEARCH = HOST_NAME + "/courses/search";
    //User Information
    String USER_INFORMATION_URL = HOST_NAME + "/students/";
    //Edit Information
    String USER_EDIT_INFO_URL = HOST_NAME + "/students";
    //Header
    String BEARER = "Bearer ";
    String AUTHORIZATION = "Authorization";
    String CONTENTTYPE = "Content-Type";
    String HEADERJSON = "application/json";
    String HEADERFORM = "application/x-www-form-urlencoded";
    /**
     * User params
     */
    String TOKEN = "access_token";
    String UID = "uid";
    String USERID = "id";
    String PASSWORD = "password";
    String ROLE = "role";
    String ROLEID = "roleId";
    String AVATAR = "urlAvatar";
    String USERNAME = "displayName";
    String LASTNAME = "lastName";
    String FIRSTNAME = "firstName";
    String GENDER = "gender";
    String DATEOFBIRTH = "dateOfBirth";
    String EMAIL = "email";
    String PHONE = "phone";
    String ADDRESS = "address";
    String INFOMATION = "infomation";
    String DESCRIPTION = "description";
    /**
     * Course Parram
     */
    String COURSE_NAMECOURSES = "nameCourses";
    String COURSE_DONORS = "donors";
    String COURSE_NUMBERCREDITS = "numberCredits";
    String COURSE_DATESTART = "dateStart";
    String COURSE_DATEEND = "dateEnd";
    String COURSE_PRICE = "price";
    String COURSE_DESCRIPTION = "description";
    String COURSE_URLIMAGE = "urlImage";
    String COURSE_TEACHER = "teacher";
    String COURSE_CATEGORY = "category";
    String COURSE_CETIFICATE = "cetificate";
    String COURSE_IDTEACHER = "idTeacher";
    String COURSE_IDCATEGORY = "idCategory";
    String COURSE_IDCETIFICATE = "idCetificate";
    String COURSE_ID = "id";
    String COURSE_ADDTIME = "addTime";
    String COURSE_EDITTIME = "editTime";

    /**
     * Search
     */
    String SEARCH="search";
}
