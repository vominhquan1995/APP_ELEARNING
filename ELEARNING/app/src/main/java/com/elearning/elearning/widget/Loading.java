package com.elearning.elearning.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.elearning.elearning.R;

public class Loading extends RelativeLayout {
    public Loading(Context context) {
        super(context);

        //Root config
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        //Child config
        LinearLayout.LayoutParams childParams1 = new
                LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout lnChild1 = new LinearLayout(context);
        lnChild1.setLayoutParams(childParams1);
        lnChild1.setGravity(Gravity.CENTER);
        lnChild1.setBackgroundColor(context.getResources().getColor(android.R.color.black));
        lnChild1.setAlpha(0.5f);
        addView(lnChild1);

        ///
        LinearLayout.LayoutParams childParams2 = new
                LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout lnChild2 = new LinearLayout(context);
        lnChild2.setLayoutParams(childParams2);
        lnChild2.setGravity(Gravity.CENTER);
        lnChild2.setAlpha(1f);
        //Dialog
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_background_main_blue)));
        progressBar.setBackground(getResources().getDrawable(R.drawable.logo_300_300));
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        lnChild2.addView(progressBar);
        addView(lnChild2);
        hide();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //To block UI
            }
        });
    }

    public int pixelToDp(Context context, int pixel) {
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (pixel * density);
        return paddingDp;
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }
}
