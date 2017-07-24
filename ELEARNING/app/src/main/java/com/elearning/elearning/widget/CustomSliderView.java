package com.elearning.elearning.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.elearning.elearning.R;


/**
 * Created by thongph on 5/25/16.
 */
public class CustomSliderView extends BaseSliderView {

    private Context mContext;

    public CustomSliderView(Context context) {
        super(context);

        mContext = context;
    }

    @Override
    public View getView() {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(mContext).inflate(R.layout.item_slide, null);
        ImageView sliderImage = (ImageView) v.findViewById(R.id.imgSlide);
        bindEventAndShow(v, sliderImage);
        return v;
    }
}
