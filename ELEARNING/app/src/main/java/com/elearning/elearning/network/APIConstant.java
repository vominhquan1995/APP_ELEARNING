package com.elearning.elearning.network;

/**
 * Created by MinhQuan on 19/05/2017.
 */

public interface APIConstant {

    public static final int CONNECTION_TIMEOUT = 30;
    public static final int COUNT_DOWN_TIME_START = 30; //second

    String HOST_NAME = "http://apielearning.azurewebsites.net/api";
    //Login
    String LOGIN_URL = HOST_NAME+"/accounts/token";
    String SIGNUP_URL = HOST_NAME+"/accounts/signup";

}
