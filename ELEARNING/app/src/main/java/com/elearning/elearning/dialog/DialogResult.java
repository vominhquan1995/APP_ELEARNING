package com.elearning.elearning.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.elearning.elearning.R;


/**
 * Created by Covisoft on 07/01/2016.
 */
public class DialogResult {
    public static class Build {
        private final String TAG = getClass().getSimpleName();
        private AlertDialog.Builder builder;
        private AlertDialog dialog;
        private Button btnOk;
        private TextView txtStatus, txtBody;
        private ImageView imgStatus;
        private OnLogoutListener onLogoutListener;

        public Build(Activity activity) {
            builder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_result_exam, null);
            builder.setView(view);
            dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            btnOk = (Button) view.findViewById(R.id.btnOk);
            txtStatus = (TextView) view.findViewById(R.id.txtStatus);
            txtBody = (TextView) view.findViewById(R.id.txtBody);
            imgStatus = (ImageView) view.findViewById(R.id.imgStatus);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLogoutListener.onOk();
                    dismiss();
                }
            });
        }

        public Build setOnLogoutListener(OnLogoutListener onLogoutListener) {
            this.onLogoutListener = onLogoutListener;
            return this;
        }

        public Build setTxtStatus(String status) {
            this.txtStatus.setText(status);
            return this;
        }

        public Build setTxtBody(String body) {
            this.txtBody.setText(body);
            return this;
        }

        public Build setImgStatus(Drawable drawable) {
            this.imgStatus.setBackground(drawable);
            return this;
        }

        public void show() {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

        public void dismiss() {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        public interface OnLogoutListener {
            void onOk();
        }
    }
}
