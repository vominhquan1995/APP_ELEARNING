package com.elearning.elearning.fragment;

import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.helper.DatetimeHelper;
import com.elearning.elearning.mvp.model.User;
import com.elearning.elearning.mvp.presenter.UserInfoPresenter;
import com.elearning.elearning.mvp.view.UserInfoView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class UserInfoFragment extends BaseFragment implements UserInfoView, View.OnClickListener {
    private static final int REQUEST_CODE = 101;
    private UserInfoPresenter userInfoPresenter;
    private ImageView avatarUser;
    private LinearLayout vgEmail, vgFirstName, vgLastName, vgPhone, vgAddress, vgInformation, vgButton, vgBirthday, vgGender;
    private ImageView iconEmail, iconFirstName, iconLastName, iconPhone, iconAddress, iconInformation, iconBirthday;
    private EditText edtEmail, edtFirstName, edtLastName, edtPhone, edtAddress, edtInformation, edtBirthday;
    private TextView txtChangeImage;
    private Button btnCancel, btnSave;
    private RadioGroup rgGender;

    @Override
    public void initView() {
        avatarUser = (ImageView) view.findViewById(R.id.avatarUser);
        vgEmail = (LinearLayout) view.findViewById(R.id.vgEmail);
        vgFirstName = (LinearLayout) view.findViewById(R.id.vgFirstName);
        vgLastName = (LinearLayout) view.findViewById(R.id.vgLastName);
        vgPhone = (LinearLayout) view.findViewById(R.id.vgPhone);
        vgAddress = (LinearLayout) view.findViewById(R.id.vgAddress);
        vgInformation = (LinearLayout) view.findViewById(R.id.vgInformation);
        vgButton = (LinearLayout) view.findViewById(R.id.vgButton);
        vgBirthday = (LinearLayout) view.findViewById(R.id.vgBirthDay);
        vgGender = (LinearLayout) view.findViewById(R.id.vgGender);
        txtChangeImage = (TextView) view.findViewById(R.id.txtChangeImage);
        //icon
        iconEmail = (ImageView) vgEmail.findViewById(R.id.imgIcon);
        iconFirstName = (ImageView) vgFirstName.findViewById(R.id.imgIcon);
        iconLastName = (ImageView) vgLastName.findViewById(R.id.imgIcon);
        iconPhone = (ImageView) vgPhone.findViewById(R.id.imgIcon);
        iconAddress = (ImageView) vgAddress.findViewById(R.id.imgIcon);
        iconInformation = (ImageView) vgInformation.findViewById(R.id.imgIcon);
        iconBirthday = (ImageView) vgBirthday.findViewById(R.id.imgIcon);
        //edit text
        edtEmail = (EditText) vgEmail.findViewById(R.id.editText);
        edtFirstName = (EditText) vgFirstName.findViewById(R.id.editText);
        edtLastName = (EditText) vgLastName.findViewById(R.id.editText);
        edtPhone = (EditText) vgPhone.findViewById(R.id.editText);
        edtAddress = (EditText) vgAddress.findViewById(R.id.editText);
        edtInformation = (EditText) vgInformation.findViewById(R.id.editText);
        edtBirthday = (EditText) vgBirthday.findViewById(R.id.editText);
        //set icon
        iconEmail.setBackground(context.getResources().getDrawable(R.drawable.profile_email));
        iconFirstName.setBackground(context.getResources().getDrawable(R.drawable.profile_name));
        iconLastName.setBackground(context.getResources().getDrawable(R.drawable.profile_name));
        iconPhone.setBackground(context.getResources().getDrawable(R.drawable.profile_phone));
        iconAddress.setBackground(context.getResources().getDrawable(R.drawable.profile_address));
        iconInformation.setBackground(context.getResources().getDrawable(R.drawable.profile_info));
        iconBirthday.setBackground(context.getResources().getDrawable(R.drawable.profile_birthday));
        //set avatar
        Picasso.with(context).load(User.get().getUrlAvatar()).into(avatarUser);
        //validate type input
        edtEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        edtPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        //set style date picker on style.xml
        DatetimeHelper.addDatePicker(edtBirthday, DATE_FORMAT, getResources().getString(R.string.save), getResources().getString(R.string.cancle));
        //gender
        rgGender = (RadioGroup) vgGender.findViewById(R.id.rgChoice);
    }

    @Override
    public void initValue() {
        showProgressDialog();
        userInfoPresenter = new UserInfoPresenter(this);
    }

    @Override
    public void initAction() {
        userInfoPresenter.getUserInfo(User.get().getUserId());
        vgButton.findViewById(R.id.btnCancel).setOnClickListener(this);
        vgButton.findViewById(R.id.btnSave).setOnClickListener(this);
        txtChangeImage.setOnClickListener(this);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdo1)
                    User.get().setGender(true);
                else
                    User.get().setGender(false);
            }
        });
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_user_info;
    }

    @Override
    public void getInfoSuccess(User user) {
        dismissProgressDialog();
        edtEmail.setText(user.getEmail());
        edtFirstName.setText(user.getFirstName());
        edtLastName.setText(user.getLastName());
        edtAddress.setText(user.getAddress());
        edtPhone.setText(user.getPhone());
        edtInformation.setText(user.getInfomation());
        edtBirthday.setText(DATE_FORMAT.format(user.getDateOfBirth()));
    }

    @Override
    public void getInfoFail(String messError) {
        Log.d("Fragment User", messError);
    }

    @Override
    public void editSuccess() {
        userInfoPresenter.getUserInfo(User.get().getUserId());
        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void editFail() {
        Toast.makeText(context, "Không thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel: {
                onBackPressed();
                break;
            }
            case R.id.btnSave: {
                try {
                    User.get().setEmail(edtEmail.getText().toString());
                    User.get().setFirstName(edtFirstName.getText().toString());
                    User.get().setLastName(edtLastName.getText().toString());
                    User.get().setAddress(edtAddress.getText().toString());
                    User.get().setPhone(edtPhone.getText().toString());
                    User.get().setInfomation(edtInformation.getText().toString());
                    User.get().setDateOfBirth(DATE_FORMAT.parse(edtBirthday.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                userInfoPresenter.editUserInfo(User.get());
            }
            case  R.id.txtChangeImage:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE:
        }
    }
}
