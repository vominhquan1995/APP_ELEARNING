package com.elearning.elearning.dialog;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.elearning.elearning.R;
import com.elearning.elearning.helper.ImageConvert;
import com.elearning.elearning.network.APIConstant;
import com.elearning.elearning.prefs.Constant;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class FirebaseMessageDialog extends AppCompatActivity {
    private TextView txtTitle, txtBody;
    private ImageView imgContent;
    private Button btnCancel, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getExtras().getString(Constant.FIREBASE_TITLE);
        String message = getIntent().getExtras().getString(Constant.FIREBASE_MSG);
        String urlImage = getIntent().getExtras().getString(Constant.FIREBASE_IMAGE);
        final View notificationView = LayoutInflater
                .from(this).inflate(R.layout.dialog_notification, (ViewGroup) findViewById(R.id.dialog_notification));
        txtTitle = (TextView) notificationView.findViewById(R.id.txtTitle);
        txtBody = (TextView) notificationView.findViewById(R.id.txtBody);
        imgContent = (ImageView) notificationView.findViewById(R.id.imageNotification);
        btnCancel = (Button) notificationView.findViewById(R.id.btnCancel);
        btnView = (Button) notificationView.findViewById(R.id.btnView);
        //set value
        txtTitle.setText(title);
        txtBody.setText(message);
        Picasso.with(this)
                .load(APIConstant.HOST_NAME_IMAGE + urlImage)
                .resize(200, 200)
                .into(imgContent);
        btnCancel.setOnClickListener(new View.OnClickListener() {
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
        final AlertDialog shoppingDialog = new AlertDialog.Builder(this)
                .setView(notificationView)
                .create();
        shoppingDialog.show();
    }
}
