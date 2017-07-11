package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.User;
import com.elearning.elearning.mvp.view.UserInfoView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by MinhQuan on 02/07/2017.
 */

public class UserInfoPresenter {
    private UserInfoView userInfoView;


    public UserInfoPresenter(UserInfoView userInfoView) {
        this.userInfoView = userInfoView;
    }

    public void getUserInfo(int userID) {
        API.getInfoUser(String.valueOf(userID), new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {
                userInfoView.getInfoSuccess(User.get().setInformation(response));
            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                userInfoView.getInfoFail(errorMessage);
            }
        });
    }

    public void editUserInfo(final User user) {
        API.editUserInfo(user, new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {
                userInfoView.editSuccess();
            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                userInfoView.editFail();
            }
        });
    }

    public void uploadAvatar(File file,final onUploadAvatar onUploadAvatar) {
        API.uploadAvatar(file, new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {

            }

            @Override
            public void onString(String response) throws JSONException {
                onUploadAvatar.onUploadSuccess(response.toString());
            }

            @Override
            public void onError(String errorMessage) {
                onUploadAvatar.onUploadFail(errorMessage);
            }
        });
    }

    public interface onUploadAvatar {
        void onUploadSuccess(String url);

        void onUploadFail(String mess);
    }
}
