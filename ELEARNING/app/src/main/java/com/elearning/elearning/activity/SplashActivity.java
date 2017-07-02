package com.elearning.elearning.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.signin_signup.SignInSignUpActivity;
import com.elearning.elearning.base.BaseActivity;
import com.elearning.elearning.mvp.presenter.LoginPresenter;
import com.elearning.elearning.mvp.view.LoginView;
import com.elearning.elearning.prefs.Constant;

import static com.elearning.elearning.prefs.Constant.SPLASH_TIME;


public class SplashActivity extends BaseActivity implements LoginView {

    @Override
    public int getView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {

        countDownToLogin();
    }

    /**
     * wait for 1s & move to LoginActivity
     */
    private void countDownToLogin() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isOnline()) {
                    if (sharedPreferences.getBoolean(Constant.AUTO_LOGIN, false)) {
                        String userEmail = sharedPreferences.getString(Constant.USER_EMAIL, "");
                        String userPw = sharedPreferences.getString(Constant.USER_PW, "");
                        if (!userEmail.equals("") && !userPw.equals("")) {
                            new LoginPresenter(SplashActivity.this).login(userEmail, userPw);
                        } else {
                            onLoginFail(null);
                        }
                    } else {
                        onLoginFail(null);
                    }
                } else {
//                    startActivity(new Intent(context, DisconnectActivity.class));
//                    finish();
                    //Go to DisconnectActivity
                }

            }
        }, SPLASH_TIME);
    }

    //add by Quan 16/01 check network
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(SplashActivity.this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFail(String serviceMsg) {
        startActivity(new Intent(context, SignInSignUpActivity.class));
        finish();
    }
}
