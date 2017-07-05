package com.elearning.elearning.service;

import android.content.Intent;
import android.text.Html;
import android.util.Log;

import com.elearning.elearning.R;
import com.elearning.elearning.dialog.FirebaseMessageDialog;
import com.elearning.elearning.prefs.Constant;
import com.elearning.elearning.utils.NotificationUtils;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;

public class FirebaseMessagingService extends
        com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = FirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        try {
            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
//            Log.d("urlImage",object.getString("urlImage"));
            sendNotification(object.getString("title"),
                    object.getString("body"));
        } catch (Exception ex) {

        }
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "Firebase message: " + remoteMessage.getData());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }

    private void sendNotification(String title, String message) {

        Intent intent = new Intent(getApplicationContext(), FirebaseMessageDialog.class);
        intent.putExtra(Constant.FIREBASE_TITLE, title);
        intent.putExtra(Constant.FIREBASE_MSG, message);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        new NotificationUtils(getApplicationContext())
                .showNotificationMessage(R.drawable.logo_300_300, title, String.valueOf(Html.fromHtml(
                        message.substring(0, message.length() >= 100 ? 100 : message.length()))));
        startActivity(intent);
    }
}
