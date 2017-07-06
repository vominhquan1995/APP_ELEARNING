package com.elearning.elearning.dialog;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.elearning.elearning.R;
import com.elearning.elearning.helper.ImageConvert;
import com.elearning.elearning.network.APIConstant;
import com.elearning.elearning.prefs.Constant;

import java.io.IOException;

public class FirebaseMessageDialog extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private TextView txtTitle, txtBody;
    private ImageView imgContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getExtras().getString(Constant.FIREBASE_TITLE);
        String message = getIntent().getExtras().getString(Constant.FIREBASE_MSG);
        String urlImage = getIntent().getExtras().getString(Constant.FIREBASE_IMAGE);
        builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_notification, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtBody = (TextView) view.findViewById(R.id.txtBody);
        imgContent = (ImageView) view.findViewById(R.id.imageNotification);
        try {
            //set value
            txtTitle.setText(title);
            txtBody.setText(message);
            imgContent.setBackground(ImageConvert.getDrawableFromUrl(APIConstant.HOST_NAME_IMAGE + urlImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.findViewById(R.id.imgCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager =
                        (NotificationManager) getApplicationContext()
                                .getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancelAll();
                FirebaseMessageDialog.this.finish();
                finish();
            }
        });
        dialog.show();
    }
}
