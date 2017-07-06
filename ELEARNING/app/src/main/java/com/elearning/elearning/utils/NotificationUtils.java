package com.elearning.elearning.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;

import com.elearning.elearning.network.APIConstant;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationUtils {

    private static final int NOTIFICATION_ID = 100;
    private static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    private Context context;

    public NotificationUtils(Context context) {
        this.context = context;
    }

    // Clears notification tray messages
    public static void clearNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public void showNotificationMessage(int notificationIcon, String title, String message, String urlImage) {
        showNotificationMessage(notificationIcon, title, message, urlImage, null);
    }

//    public void showNotificationMessage(int notificationIcon, String title, String message, String urlImage, PendingIntent pendingIntent) {
//        showNotificationMessage(notificationIcon, title, urlImage, message, pendingIntent);
//    }

    public void showNotificationMessage(int notificationIcon, String title, String message,
                                        String imageUrl, PendingIntent pendingIntent) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        // Notification sound
        final Uri notificationSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (!TextUtils.isEmpty(imageUrl)) {
            if (imageUrl.length() > 4 && Patterns.WEB_URL.matcher(APIConstant.HOST_NAME_IMAGE+imageUrl)
                    .matches()) {
                Bitmap bitmap = getBitmapFromURL(APIConstant.HOST_NAME_IMAGE+imageUrl);

                if (bitmap != null) {
                    // show big notification
                    showBigNotification(bitmap, builder, notificationIcon, title, message,
                            pendingIntent, notificationSound);
                } else {
                    // show small notification
                    showSmallNotification(builder, notificationIcon, title, message, pendingIntent,
                            notificationSound);
                }
            }
        } else {
            showSmallNotification(builder, notificationIcon, title, message, pendingIntent,
                    notificationSound);
        }

    }

    private void showSmallNotification(NotificationCompat.Builder builder, int icon,
                                       String title, String message, PendingIntent pendingIntent, Uri notificationSound) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);

        Notification notification;
        notification = builder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setSound(notificationSound)
                .setStyle(inboxStyle)
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon))
                .setContentText(message)
                .setVibrate(new long[]{3000, 3000, 3000, 3000, 3000}) // vibration
                .setLights(Color.GREEN, 3000, 3000) // led light
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void showBigNotification(Bitmap bitmap, NotificationCompat.Builder builder,
                                     int icon, String title, String message, PendingIntent pendingIntent,
                                     Uri notificationSound) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(title);
        bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
        bigPictureStyle.bigPicture(bitmap);
        Notification notification;
        notification = builder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setSound(notificationSound)
                .setStyle(bigPictureStyle)
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon))
                .setContentText(message)
                .setVibrate(new long[]{3000, 3000, 3000, 3000, 3000}) // vibration
                .setLights(Color.GREEN, 3000, 3000) // led light
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_BIG_IMAGE, notification);
    }

    /**
     * Downloading push notification image before displaying it in the notification tray
     */
    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
