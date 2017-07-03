package com.elearning.elearning.activity.signin_signup;

import android.content.Intent;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.helper.DatetimeHelper;
import com.elearning.elearning.mvp.presenter.RegistrationPresenter;
import com.elearning.elearning.mvp.view.RegistrationView;

import java.util.Date;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT;

/**
 * Created by MinhQuan on 20/05/2017.
 */

public class SignUpFragment extends BaseFragment implements RegistrationView, View.OnClickListener {
    private LinearLayout vgEmail, vgFirstName, vgLastName, vgPassword, vgPasswordConfirm;
    private ImageView iconEmail, iconFirstName, iconLastName, iconPassword, iconPasswordConfirm;
    private EditText edtEmail, edtFirstName, edtLastName, edtPassword, edtPasswordConfirm;
    private RadioGroup rgChoiceGender;
    private Button btnSignup;
    private RegistrationPresenter registrationPresenter;
    private static SignUpFragment.onSignupListener onSignupListener;
    CheckBox cbShowHidePass;

    @Override
    public void initView() {
        vgEmail = (LinearLayout) view.findViewById(R.id.vgEmail);
        vgFirstName = (LinearLayout) view.findViewById(R.id.vgFirstName);
        vgLastName = (LinearLayout) view.findViewById(R.id.vgLastName);
        vgPassword = (LinearLayout) view.findViewById(R.id.vgPassword);
        vgPasswordConfirm = (LinearLayout) view.findViewById(R.id.vgPasswordConfirm);

        cbShowHidePass = (CheckBox) vgPassword.findViewById(R.id.cbShowHidePass);
        //icon
        iconEmail = (ImageView) vgEmail.findViewById(R.id.imgIcon);
        iconFirstName = (ImageView) vgFirstName.findViewById(R.id.imgIcon);
        iconLastName = (ImageView) vgLastName.findViewById(R.id.imgIcon);
        iconPassword = (ImageView) vgPassword.findViewById(R.id.imgIcon);
        iconPasswordConfirm = (ImageView) vgPasswordConfirm.findViewById(R.id.imgIcon);
        //edit text
        edtEmail = (EditText) vgEmail.findViewById(R.id.editText);
        edtFirstName = (EditText) vgFirstName.findViewById(R.id.editText);
        edtLastName = (EditText) vgLastName.findViewById(R.id.editText);
        edtPassword = (EditText) vgPassword.findViewById(R.id.editText);
        edtPasswordConfirm = (EditText) vgPasswordConfirm.findViewById(R.id.editText);
        //set hint text
        edtEmail.setHint(context.getResources().getString(R.string.email));
        edtFirstName.setHint(context.getResources().getString(R.string.firstName));
        edtLastName.setHint(context.getResources().getString(R.string.lastName));
        edtPassword.setHint(context.getResources().getString(R.string.passWord));
        edtPasswordConfirm.setHint(context.getResources().getString(R.string.passWordConfirm));
        //set icon
        iconEmail.setBackground(context.getResources().getDrawable(R.drawable.profile_email));
        iconFirstName.setBackground(context.getResources().getDrawable(R.drawable.profile_name));
        iconLastName.setBackground(context.getResources().getDrawable(R.drawable.profile_name));
        iconPasswordConfirm.setBackground(context.getResources().getDrawable(R.drawable.password));
        //validate type input
        edtEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        edtPasswordConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        edtPasswordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btnSignup = (Button) view.findViewById(R.id.btnSignUp);
        //set value
        edtEmail.setText("vominhquan.hutech@gmail.com");
        edtFirstName.setText("Quân");
        edtLastName.setText("Võ");
        edtPassword.setText("123456");
        edtPasswordConfirm.setText("123456");
    }

    public static void setOnSignupListener(SignUpFragment.onSignupListener onSignupListener) {
        SignUpFragment.onSignupListener = onSignupListener;
    }

    @Override
    public void initValue() {
    }

    @Override
    public void initAction() {
        registrationPresenter = new RegistrationPresenter(this);
        btnSignup.setOnClickListener(this);
        cbShowHidePass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtPasswordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edtPassword.setTransformationMethod(null);
                    edtPasswordConfirm.setTransformationMethod(null);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                if (validateInput() == null) {
                    registrationPresenter.registration(edtEmail.getText().toString()
                            , edtPassword.getText().toString(), edtFirstName.getText().toString(), edtLastName.getText().toString());
                } else {
                    Log.d("Validate", validateInput());
                    Toast.makeText(getContext(), validateInput(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onRegistrationSuccess(String email) {
        Toast.makeText(getContext(), context.getResources().getString(R.string.cap_signup_success), Toast.LENGTH_SHORT).show();
        onSignupListener.onSignupSuccess(email);
    }

    @Override
    public void onRegistrationFail() {
        Toast.makeText(getContext(), context.getResources().getString(R.string.cap_signup_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_signup;
    }

    public String validateInput() {
        String errorMessage = null;
        if (edtEmail.getText().toString().trim().equals("")) {
            errorMessage = context.getResources().getString(R.string.validate_email);
        } else if (!isEmailValid(edtEmail.getText().toString())) {
            errorMessage = context.getResources().getString(R.string.validate_email);
        } else if (edtPassword.getText().toString().equals("")) {
            errorMessage = context.getResources().getString(R.string.validate_pass);
        } else if (edtPasswordConfirm.getText().toString().equals("")) {
            errorMessage = context.getResources().getString(R.string.validate_pass_confirm);
        } else if (!edtPasswordConfirm.getText().toString().equals(edtPassword.getText().toString())) {
            errorMessage = context.getResources().getString(R.string.validate_confirmpass_error);
        } else if (edtFirstName.getText().toString().equals("")) {
            errorMessage = context.getResources().getString(R.string.validate_firstName);
        } else if (edtLastName.getText().toString().equals("")) {
            errorMessage = context.getResources().getString(R.string.validate_lastName);
        }
        return errorMessage;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    public interface onSignupListener {
        void onSignupSuccess(String email);
    }
}
