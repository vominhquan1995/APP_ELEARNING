package com.elearning.elearning.activity.signin_signup;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.presenter.LoginPresenter;
import com.elearning.elearning.mvp.view.LoginView;

/**
 * Created by vomin on 18/05/2017.
 */

public class LoginFragment extends BaseFragment implements LoginView, View.OnClickListener {

    private LoginPresenter loginPresenter;
    private EditText txtEmail,txtPass;
    @Override
    public void initView() {
        txtEmail=(EditText) view.findViewById(R.id.txtEmail);
        txtPass=(EditText) view.findViewById(R.id.txtPass);
    }

    @Override
    public void initValue() {
        loginPresenter=new LoginPresenter(this);
    }

    @Override
    public void initAction() {
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_login;
    }

    @Override
    public void onLoginSuccess() {
        Log.d("Login","Success");
    }

    @Override
    public void onLoginFail(String message) {
        Log.d("Login","Fail");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                loginPresenter.login(txtEmail.getText().toString(),txtPass.getText().toString());
                break;
        }
    }
}
