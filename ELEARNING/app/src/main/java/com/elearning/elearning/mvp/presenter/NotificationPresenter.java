package com.elearning.elearning.mvp.presenter;

import com.elearning.elearning.mvp.model.Notification;
import com.elearning.elearning.mvp.view.NotificationView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 10/07/2017.
 */

public class NotificationPresenter {
    private NotificationView notificationView;

    public NotificationPresenter(NotificationView notificationView) {
        this.notificationView = notificationView;
    }

    public void getNotification() {
        API.getNotification(new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                notificationView.onGetListNotification(Notification.getListNotification(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {
                notificationView.onGetListNotificationFail(errorMessage);
            }
        });
    }
}
