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

//    String HOST_NAME = "http://apielearning.azurewebsites.net/api";
    String HOST_NAME = "http:/apielearninghutech.azurewebsites.net/api";
    String HOST_NAME_IMAGE = "http://apielearninghutech.azurewebsites.net";
    //Login
    String LOGIN_URL = HOST_NAME + "/accounts/token";
    //Sign up
    String SIGNUP_URL = HOST_NAME + "/accounts/signup";
    //New Course
    String NEW_COURSE_HEADER_URL = HOST_NAME + "/courses/";
    String NEW_COURSE_FOOTER_URL = "/newcourses";
    //MostCourse
    String MOST_COURSE_HEADER_URL = HOST_NAME + "/courses/";
    String MOST_COURSE_FOOTER_URL = "/mostcourses";
    //Top Review Course
    String TOP_REVIEW_COURSE_HEADER_URL = HOST_NAME + "/courses/";
    String TOP_REVIEW_COURSE_FOOTER_URL = "/top-review";
    //Search Course
    String COURSE_SEARCH = HOST_NAME + "/courses/search";
    //User Information
    String USER_INFORMATION_URL = HOST_NAME + "/students/";
    //Edit Information
    String USER_EDIT_INFO_URL = HOST_NAME + "/students";
    //Upload avatar
    String UPLOAD_AVATAR_HEADER_URL=HOST_NAME+"/accounts/UploadAvatar/";
    String UPLOAD_AVATAR_FOOTER_URL="/upload-file";
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
    //Get history learn
    String HISTORY_LEARN_HEADER_URL=HOST_NAME+ "/students/";
    String HISTORY_LEARN_FOOTER_URL="/history";
    //Get history do exam
    String HISTORY_DO_EXAM_HEADER_URL=HOST_NAME+ "/exams/";
    String HISTORY_DO_EXAM_FOOTER_URL="/list-history-exam";
    //Get list notification
    String NOTFICATION_URL=HOST_NAME+ "/notification";
    //get progress learn
    String PROGRESS_LEARN_URL=HOST_NAME+ "/students/";
    //get information lesson
    String LESSON_INFORMATION_URL=HOST_NAME+ "/lesson/";
    //filter rate
    String FILTER_RATE_HEADER_URL=HOST_NAME+ "/courses/";
    String FILTER_RATE_FOOTER_URL="/filter-rate";
    //filter price
    String FILTER_PRICE_HEADER_URL=HOST_NAME+ "/courses/";
    String FILTER_PRICE_FOOTER_URL="/filter-price";
    //filter category
    String FILTER_CATEGORY_HEADER_URL=HOST_NAME+ "/courses/";
    String FILTER_CATEGORY_FOOTER_URL="/filter-category";
    //get list category
    String LIST_CATEGORY_URL = HOST_NAME + "/categorys";
    //get item page
    String GET_ITEM_PAGE_HEADER_URL=HOST_NAME+ "/courses/";
    String GET_ITEM_PAGE_FOOTER_URL="/get-page";
    //Header
    String BEARER = "Bearer ";
    String AUTHORIZATION = "Authorization";
    String CONTENTTYPE = "Content-Type";
    String HEADERJSON = "application/json";
    String HEADERFORM = "application/x-www-form-urlencoded";
    String ID="id";
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
    String LESSONID="lessonId";
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

    /**
     * history learn
     */
    String HISTORY_LEARN_ID="id";
    String HISTORY_LEARN_NAMECOURSES="nameCourses" ;
    String HISTORY_LEARN_URLIMAGE="urlImage" ;
    String HISTORY_LEARN_STATUS="status" ;

    /**
     * model notification
     */
    String NOTIFICATION_ID="id";
    String NOTIFICATION_TITLE="title";
    String NOTIFICATION_BODY="body";
    String NOTIFICATION_URLIMAGE="urlImage";
    String NOTIFICATION_ADDDATE="dateStart";
    String NOTIFICATION_EDITDATE="dateEnd";


}
