package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.view.LoginView;
import com.elearning.elearning.network.API;

import org.json.JSONObject;

/**
 * Created by MinhQuan on 19/05/2017.
 */

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String username,String password) {
        API.login(username,password, new API.OnAPIListener() {
            @Override
            public void onSuccess(JSONObject response) {
                loginView.onLoginSuccess();
            }
            @Override
            public void onError(String errorMessage) {
                loginView.onLoginFail(errorMessage);
            }
        });
    }
}
