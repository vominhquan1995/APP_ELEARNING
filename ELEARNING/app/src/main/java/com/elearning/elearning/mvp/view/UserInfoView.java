package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.User;

/**
 * Created by MinhQuan on 02/07/2017.
 */

public interface UserInfoView {
    void getInfoSuccess(User user);

    void getInfoFail(String messError);

    void editSuccess();

    void editFail();
}
