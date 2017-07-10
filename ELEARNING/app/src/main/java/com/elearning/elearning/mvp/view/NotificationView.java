package com.elearning.elearning.mvp.view;

import com.elearning.elearning.mvp.model.Notification;

import java.util.List;

/**
 * Created by MinhQuan on 10/07/2017.
 */

public interface NotificationView {
    void onGetListNotification(List<Notification> notificationList);

    void onGetListNotificationFail(String error);
}
