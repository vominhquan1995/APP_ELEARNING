package com.elearning.elearning.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.elearning.elearning.mvp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.Response;

import static com.elearning.elearning.network.APIConstant.CONTENTTYPE;
import static com.elearning.elearning.network.APIConstant.HISTORY_LEARN_FOOTER_URL;
import static com.elearning.elearning.network.APIConstant.LESSON_INFORMATION_URL;
import static com.elearning.elearning.network.APIConstant.UPLOAD_AVATAR_FOOTER_URL;
import static com.elearning.elearning.network.APIConstant.UPLOAD_AVATAR_HEADER_URL;
import static com.elearning.elearning.network.APIConstant.USER_EDIT_INFO_URL;
import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 19/05/2017.
 */

public class API {
    private static String TAG = "API";
    private static OnAPIListener listener;

    //login
    public static void login(final String username, final String password, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.post(APIConstant.LOGIN_URL)
                .addBodyParameter(APIConstant.EMAIL, username)
                .addBodyParameter(APIConstant.PASSWORD, password)
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            Log.d("Login result", String.valueOf(response));
                            listener.onSuccessObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        listener.onError(error.getMessage());
                        Log.d("Login fail", (error.getMessage()));
                    }
                });
    }

    //sign up
    public static void registration(final String email, final String password, final String firstName, final String lastName, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.post(APIConstant.SIGNUP_URL)
                .addBodyParameter(APIConstant.EMAIL, email)
                .addBodyParameter(APIConstant.PASSWORD, password)
                .addBodyParameter(APIConstant.FIRSTNAME, firstName)
                .addBodyParameter(APIConstant.LASTNAME, lastName)
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Link", response);
                            listener.onString(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get list new course
    public static void listNewCourse(final String numberItem, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.NEW_COURSE_HEADER_URL + numberItem + APIConstant.NEW_COURSE_FOOTER_URL)
                .addHeaders(APIConstant.BEARER, User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get list most course
    public static void listMostCourse(final String numberItem, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.MOST_COURSE_HEADER_URL + numberItem + APIConstant.MOST_COURSE_FOOTER_URL)
                .addHeaders(APIConstant.BEARER, User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get list top review course
    public static void listReviewCourse(final String numberItem, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.TOP_REVIEW_COURSE_HEADER_URL + numberItem + APIConstant.TOP_REVIEW_COURSE_FOOTER_URL)
                .addHeaders(APIConstant.BEARER, User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //search list course
    public static void searchListCourse(final String keyword, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.post(APIConstant.COURSE_SEARCH)
                .addBodyParameter(APIConstant.SEARCH, keyword)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get Information User
    public static void getInfoUser(final String userID, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.USER_INFORMATION_URL + userID)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            listener.onSuccessObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        listener.onError(error.getMessage());
                    }
                });
    }

    //edit User Information
    public static void editUserInfo(final User user, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        //convert model object to json object
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(APIConstant.USERID, user.getUserId());
            jsonObject.put(APIConstant.PASSWORD, user.getPassword());
            jsonObject.put(APIConstant.EMAIL, user.getEmail());
            jsonObject.put(APIConstant.FIRSTNAME, user.getFirstName());
            jsonObject.put(APIConstant.LASTNAME, user.getLastName());
            jsonObject.put(APIConstant.ADDRESS, user.getAddress());
            jsonObject.put(APIConstant.AVATAR, user.getUrlAvatar());
            jsonObject.put(APIConstant.PHONE, user.getPhone());
            jsonObject.put(APIConstant.INFOMATION, user.getInfomation());
            jsonObject.put(APIConstant.DATEOFBIRTH, DATE_FORMAT_YYYYMMDD.format(user.getDateOfBirth()));
            jsonObject.put(APIConstant.ROLEID, user.getRole());
            jsonObject.put(APIConstant.GENDER, user.isGender());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("API", String.valueOf(jsonObject));
        AndroidNetworking.put(USER_EDIT_INFO_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .addHeaders(CONTENTTYPE, APIConstant.HEADERJSON)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        if (response.code() == APIConstant.STATUS_CODE_OK) {
                            try {
                                listener.onSuccessObject(null);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            listener.onError(null);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }


    //get list lesson
    public static void listLesson(final String idCourse, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.LIST_LESSON_HEADER_URL + idCourse + APIConstant.LIST_LESSON_FOOTER_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }


    //get Information Exam with random exam
    public static void getInformationExam(final String idLesson, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        Log.d("Token", User.get().getToken());
        AndroidNetworking.get(APIConstant.INFO_EXAM_HEADER_URL + idLesson + APIConstant.INFO_EXAM_FOOTER_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onSuccessObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get last history exam of user
    public static void getHistoryExam(final String idExam, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.HISTORY_EXAM_HEADER_URL + User.get().getUserId() + '/' + idExam)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onSuccessObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get all history exam of user
    public static void getHistoryExamAll(OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.HISTORY_DO_EXAM_HEADER_URL + User.get().getUserId() + APIConstant.HISTORY_DO_EXAM_FOOTER_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //get list question for exam
    public static void getListQuestion(final String idExam, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.LIST_QUESTION_HEADER_URL + idExam + APIConstant.LIST_QUESTION_FOOTER_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }

    //check result
    public static void checkResult(final int idExam, List<String> listIdAnswer, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(APIConstant.IDUSER, String.valueOf(User.get().getUserId()));
            jsonObject.put(APIConstant.ID_EXAM, String.valueOf(idExam));
            jsonObject.put(APIConstant.LISTID, listIdAnswer.toString().replace("[", "").replace("]", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post(APIConstant.CHECK_RESULT_URL)
                .addJSONObjectBody(jsonObject)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onSuccessObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }

    //get history
    public static void getHistory(OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.HISTORY_LEARN_HEADER_URL + User.get().getUserId() + HISTORY_LEARN_FOOTER_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }

    //get list notification
    public static void getNotification(OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.NOTFICATION_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }

    //upload avatar
    public static void uploadAvatar(File file, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.upload(UPLOAD_AVATAR_HEADER_URL + User.get().getUserId() + UPLOAD_AVATAR_FOOTER_URL)
                .addMultipartFile("", file)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            listener.onString(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }


    //get progress learn\
    public static void getProgressLearn(String idCourse, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.PROGRESS_LEARN_URL + User.get().getUserId() + "/" + idCourse)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            listener.onString(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }


    //get information list lesson
    public static void getInformationLesson(String idLesson, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(LESSON_INFORMATION_URL + idLesson)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onSuccessObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorBody());
                    }
                });
    }

    //filter rate
    public static void filterRate(final String minStar, final String maxStar, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.FILTER_RATE_HEADER_URL + minStar + '/' + maxStar + APIConstant.FILTER_RATE_FOOTER_URL)
                .addHeaders(APIConstant.BEARER, User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }

    //filter price
    public static void filterPrice(final String minPrice, final String maxPrice, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.get(APIConstant.FILTER_PRICE_HEADER_URL + minPrice + '/' + maxPrice + APIConstant.FILTER_PRICE_FOOTER_URL)
                .addHeaders(APIConstant.BEARER, User.get().getToken())
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listener.onSuccessArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getMessage());
                    }
                });
    }


    public interface OnAPIListener {
        void onSuccessObject(final JSONObject response) throws JSONException;

        void onSuccessArray(final JSONArray response) throws JSONException;

        void onString(final String response) throws JSONException;

        void onError(String errorMessage);
    }
}
