package com.elearning.elearning.network;

import android.icu.text.LocaleDisplayNames;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndStringRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by MinhQuan on 19/05/2017.
 */

public class API {
    private static String TAG = "API";
    private static OnAPIListener listener;
    //login
    public static void login(final String username,final String password, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        AndroidNetworking.post(APIConstant.LOGIN_URL)
                .addBodyParameter("email","vominhquasdsn.vc@gmail.com")
                .addBodyParameter("password","123456")
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            Log.d("Login result",String.valueOf(response));
                            listener.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }@Override
                    public void onError(ANError error) {
                        listener.onError(error.getMessage());
                        Log.d("Login fail",(error.getErrorBody()));
                    }
                });
    }
    //sign up
    public static void registration(final String username,final String password,final String firstName, final String lastName, OnAPIListener onAPIListener) {
        listener = onAPIListener;
        Log.d("Link" ,APIConstant.SIGNUP_URL);
        AndroidNetworking.post(APIConstant.SIGNUP_URL)
                .addBodyParameter("email","vominhquan.vcdsdcc@gmail.com")
                .addBodyParameter("password","123456")
                .addBodyParameter("lastname","Võ")
                .addBodyParameter("firstname","Quân")
                .setOkHttpClient(NetworkUtil.createDefaultOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            Log.d("Login result",String.valueOf(response));
                            listener.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }@Override
                    public void onError(ANError error) {
                        listener.onError(error.getMessage());
                        Log.d("Login fail",(error.getErrorBody()));
                    }
                });
}
    public interface OnAPIListener {
        void onSuccess(final JSONObject response) throws JSONException;

        void onError(String errorMessage);
    }
}
