package com.elearning.elearning.activity.signin_signup;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

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
    private EditText edtBrithday, edtEmail, edtLastName, edtFirstName, edtPhone, edtPassword;
    private ViewGroup vgGender;
    private RadioGroup rgChoiceGender;
    private Button btnSignup;
    private RegistrationPresenter registrationPresenter;

    @Override
    public void initView() {
        edtBrithday = (EditText) view.findViewById(R.id.edtBrithday);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtLastName = (EditText) view.findViewById(R.id.edtLastName);
        edtFirstName = (EditText) view.findViewById(R.id.edtFirstName);
        edtPhone = (EditText) view.findViewById(R.id.edtPhone);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        vgGender = (ViewGroup) view.findViewById(R.id.vgGender);
        rgChoiceGender = (RadioGroup) vgGender.findViewById(R.id.rgChoice);
        edtBrithday.setOnClickListener(this);
        btnSignup = (Button) view.findViewById(R.id.btnSignUp);
        btnSignup.setOnClickListener(this);
        //set style date picker on style.xml
        DatetimeHelper.addDatePicker(edtBrithday, DATE_FORMAT, getResources().getString(R.string.save), getResources().getString(R.string.cancle));
    }

    @Override
    public void initValue() {
        edtBrithday.setText(DATE_FORMAT.format(new Date()));
    }

    @Override
    public void initAction() {
        registrationPresenter = new RegistrationPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                registrationPresenter.registration("a", "b", "c", "d");
                break;
            case R.id.edtBrithday:
                break;
        }
    }

    @Override
    public void onRegistrationSuccess() {
        Log.d("sign up view ", "success");
    }

    @Override
    public void onRegistrationFail() {
        Log.d("sign up view", "fail");
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_signup;
    }
}
