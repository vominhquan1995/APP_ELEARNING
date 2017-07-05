package com.elearning.elearning.dialog;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import com.elearning.elearning.prefs.Constant;

public class FirebaseMessageDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getExtras().getString(Constant.FIREBASE_TITLE);
        String message = getIntent().getExtras().getString(Constant.FIREBASE_MSG);

        new AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(Html.fromHtml(message))
            .setPositiveButton(android.R.string.ok, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    NotificationManager notificationManager =
                        (NotificationManager) getApplicationContext()
                            .getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.cancelAll();
                    FirebaseMessageDialog.this.finish();
                }
            })
            .show();
    }
}
