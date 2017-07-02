package com.elearning.elearning.activity.signin_signup;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout vgEmail, vgFirstName, vgLastName, vgPassword, vgPasswordConfirm;
    private ImageView iconEmail, iconFirstName, iconLastName, iconPassword, iconPasswordConfirm;
    private EditText edtEmail, edtFirstName, edtLastName, edtPassword,edtPasswordConfirm;
    private RadioGroup rgChoiceGender;
    private Button btnSignup;
    private RegistrationPresenter registrationPresenter;

    @Override
    public void initView() {
        vgEmail = (LinearLayout) view.findViewById(R.id.vgEmail);
        vgFirstName = (LinearLayout) view.findViewById(R.id.vgFirstName);
        vgLastName = (LinearLayout) view.findViewById(R.id.vgLastName);
        vgPassword = (LinearLayout) view.findViewById(R.id.vgPassword);
        vgPasswordConfirm = (LinearLayout) view.findViewById(R.id.vgPasswordConfirm);
        //icon
        iconEmail = (ImageView) vgEmail.findViewById(R.id.imgIcon);
        iconFirstName = (ImageView) vgFirstName.findViewById(R.id.imgIcon);
        iconLastName = (ImageView) vgLastName.findViewById(R.id.imgIcon);
        iconPassword = (ImageView) vgPassword.findViewById(R.id.imgIcon);
        iconPasswordConfirm = (ImageView)  vgPasswordConfirm.findViewById(R.id.imgIcon);
        //edit text
        edtEmail = (EditText) vgEmail.findViewById(R.id.editText);
        edtFirstName = (EditText) vgFirstName.findViewById(R.id.editText);
        edtLastName = (EditText) vgLastName.findViewById(R.id.editText);
        edtPassword = (EditText) vgPassword.findViewById(R.id.editText);
        edtPasswordConfirm = (EditText) vgPasswordConfirm .findViewById(R.id.editText);
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
        iconPassword.setBackground(context.getResources().getDrawable(R.drawable.password));
        iconPasswordConfirm.setBackground(context.getResources().getDrawable(R.drawable.password));
        //validate type input
        edtEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edtPasswordConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        edtBrithday.setOnClickListener(this);
//        btnSignup = (Button) view.findViewById(R.id.btnSignUp);
//        btnSignup.setOnClickListener(this);
        //set style date picker on style.xml
//        DatetimeHelper.addDatePicker(edtBrithday, DATE_FORMAT, getResources().getString(R.string.save), getResources().getString(R.string.cancle));
    }

    @Override
    public void initValue() {
//        edtBrithday.setText(DATE_FORMAT.format(new Date()));
    }

    @Override
    public void initAction() {
        registrationPresenter = new RegistrationPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnSignUp:
//                registrationPresenter.registration("a", "b", "c", "d");
//                break;
//            case R.id.edtBrithday:
//                break;
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
