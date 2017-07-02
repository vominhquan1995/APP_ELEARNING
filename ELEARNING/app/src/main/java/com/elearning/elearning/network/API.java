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

import okhttp3.Response;

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
                .addBodyParameter("email", "vominhquan.hutech@gmail.com")
                .addBodyParameter("password", "123456")
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
    public static void registration(final String username, final String password, final String firstName, final String lastName, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        Log.d("Link", APIConstant.SIGNUP_URL);
        AndroidNetworking.post(APIConstant.SIGNUP_URL)
                .addBodyParameter("email", "vominhquan.vcdsdcc@gmail.com")
                .addBodyParameter("password", "123456")
                .addBodyParameter("lastname", "Võ")
                .addBodyParameter("firstname", "Quân")
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
                        Log.d("Login fail", (error.getErrorBody()));
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
            jsonObject.put(APIConstant.PHONE, user.getPhone());
            jsonObject.put(APIConstant.INFOMATION, user.getInfomation());
            jsonObject.put(APIConstant.DATEOFBIRTH,DATE_FORMAT_YYYYMMDD.format(user.getDateOfBirth()));
            jsonObject.put(APIConstant.ROLEID,user.getRole());
            jsonObject.put(APIConstant.GENDER,user.isGender());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("API",String.valueOf(jsonObject));
        AndroidNetworking.put(USER_EDIT_INFO_URL)
                .addHeaders(APIConstant.AUTHORIZATION, APIConstant.BEARER + User.get().getToken())
                .addHeaders(APIConstant.CONTENTTYPE, APIConstant.HEADERJSON)
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
                        }else{
                            listener.onError(null);
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

        void onError(String errorMessage);
    }
}
