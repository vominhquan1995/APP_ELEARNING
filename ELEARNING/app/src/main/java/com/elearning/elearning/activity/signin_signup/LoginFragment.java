package com.elearning.elearning.activity.signin_signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.presenter.LoginPresenter;
import com.elearning.elearning.mvp.view.LoginView;
import com.elearning.elearning.prefs.Constant;

/**
 * Created by vomin on 18/05/2017.
 */

public class LoginFragment extends BaseFragment implements LoginView, View.OnClickListener {

    private LoginPresenter loginPresenter;
    private CheckBox chkAutoLogin, cbShowHidePass;
    private boolean autoLogin = false;
    private LinearLayout vgEmail, vgPass;
    private ImageView iconEmail, iconPass;
    private EditText edtEmail, edtPass;
    @Override
    public void initView() {
        vgEmail = (LinearLayout) view.findViewById(R.id.vgEmail);
        vgPass = (LinearLayout) view.findViewById(R.id.vgPassWord);
        edtEmail = (EditText) vgEmail.findViewById(R.id.editText);
        edtPass = (EditText) vgPass.findViewById(R.id.editText);
        //icon
        iconEmail = (ImageView) vgEmail.findViewById(R.id.imgIcon);
        iconPass = (ImageView) vgPass.findViewById(R.id.imgIcon);
        chkAutoLogin = (CheckBox) view.findViewById(R.id.cbAuto);
        cbShowHidePass = (CheckBox) vgPass.findViewById(R.id.cbShowHidePass);
        iconEmail.setBackground(context.getResources().getDrawable(R.drawable.profile_email));
        iconPass.setBackground(context.getResources().getDrawable(R.drawable.password));
        //set hint text
        edtEmail.setHint(context.getResources().getString(R.string.email));
        edtPass.setHint(context.getResources().getString(R.string.passWord));
        //validate type input
        edtEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        //set value
        edtEmail.setText("vominhquan.hutech@gmail.com");
        edtPass.setText("123456");
    }

    @Override
    public void initValue() {
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void initAction() {
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
        chkAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    autoLogin = true;
                } else {
                    autoLogin = false;
                }
            }
        });
        cbShowHidePass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edtPass.setTransformationMethod(null);
                }
            }
        });
        SignUpFragment.setOnSignupListener(new SignUpFragment.onSignupListener() {
            @Override
            public void onSignupSuccess(String email) {
                edtEmail.setText(email);
                edtPass.setText("");
            }
        });
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_login;
    }

    @Override
    public void onLoginSuccess() {
        checkAutoLogin();
        Toast.makeText(getContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), MainActivity.class));
        Log.d("Login", "Success");
    }

    @Override
    public void onLoginFail(String message) {
        checkAutoLogin();
        autoLogin = false;
        Toast.makeText(getContext(), R.string.login_fail, Toast.LENGTH_SHORT).show();
        Log.d("Login", "Fail");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                loginPresenter.login(edtEmail.getText().toString(), edtPass.getText().toString());
                break;
        }
    }

    /**
     * Save data check auto login
     */
    private void checkAutoLogin() {
        //Save auto login
        getSignInSignUpActivity().sharedPreferences.edit().putBoolean(Constant.AUTO_LOGIN, autoLogin).apply();
        if (autoLogin) {
            getSignInSignUpActivity().sharedPreferences.edit().putString(Constant.USER_EMAIL, edtEmail.getText().toString()).apply();
            getSignInSignUpActivity().sharedPreferences.edit().putString(Constant.USER_PW, edtPass.getText().toString()).apply();
        } else {
            getSignInSignUpActivity().sharedPreferences.edit().putString(Constant.USER_EMAIL, "").apply();
            getSignInSignUpActivity().sharedPreferences.edit().putString(Constant.USER_PW, "").apply();
        }
    }
}
