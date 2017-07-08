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
    //List Lesson
    String LIST_LESSON_HEADER_URL = HOST_NAME + "/courses/";
    String LIST_LESSON_FOOTER_URL = "/lessons";
    //Get Information Exam
    String INFO_EXAM_HEADER_URL = HOST_NAME + "/exams/";
    String INFO_EXAM_FOOTER_URL = "/information";
    //Get History Exam User
    String HISTORY_EXAM_HEADER_URL = HOST_NAME + "/exams/";
    //Get list question for exam
    String LIST_QUESTION_HEADER_URL = HOST_NAME + "/exams/";
    String LIST_QUESTION_FOOTER_URL = "/getQuestion";
    //Check Answer
    String CHECK_RESULT_URL = HOST_NAME + "/exams/check-answer";
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
     * Lesson Parram
     */
    String LESSON_NAME = "name";
    String LESSON_INDEX = "index";
    String LESSON_COURSEID = "courseId";
    String LESSON_LESSONURL = "lessonUrl";
    String LESSON_SOURSETYPE = "sourceType";
    String LESSON_TIME = "time";
    String LESSON_URLIMAGE = "urlImage";
    String LESSON_DESCRIPTION = "description";
    String LESSON_ID = "id";
    /**
     * Search
     */
    String SEARCH = "search";

    /**
     * model exam information
     */
    String EXAMNAME="examName";
    String TIMEEXAM="timeExam";
    String NUMBERQUESTION="numberQuestion";
    String IDEXAM="id";

    /**
     * model history exam
     */
    String EXAMID="examId";
    String POINTEXAM="point";
    String EXAMSTATUS="status";
    String EXAMTIME="time";

    /**
     * model question
     */
    String IDQUESTION="idQuestion";
    String CONTENTQUESTION="content";
    String LISTANSWER="listAnswer";
    String IDANSWER="idAnswer";
    String CONTENTANSWER="contentAnswer";

    String IDUSER="idUser";
    String ID_EXAM="idExam";
    String LISTID="listID";
    String NUMBERRIGHT="numberRight";
    String MESSAGE="message";

}
