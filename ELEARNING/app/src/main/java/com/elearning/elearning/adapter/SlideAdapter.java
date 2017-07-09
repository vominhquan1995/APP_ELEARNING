package com.elearning.elearning.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.elearning.elearning.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MinhQuan on 09/07/2017.
 */

public class SlideAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4};

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_slide, null);
        ImageView imgSlide = (ImageView) view.findViewById(R.id.imgSlide);
        imgSlide.setImageResource(images[position]);
        int deviceWidthInPixels = context.getResources().getDisplayMetrics().widthPixels;
//        int deviceHeightInPixels = context.getResources().getDisplayMetrics().heightPixels;
        imgSlide.getLayoutParams().width = deviceWidthInPixels;
//        imgSlide.getLayoutParams().height = deviceHeightInPixels;
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
