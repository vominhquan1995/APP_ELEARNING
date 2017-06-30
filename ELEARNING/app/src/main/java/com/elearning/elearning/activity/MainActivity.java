package com.elearning.elearning.activity;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseActivity;

/**
 * Created by vomin on 30/06/2017.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private DrawerLayout drawerLayout;
    private LinearLayout lnNav;
    @Override
    public int getView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        lnNav = (LinearLayout) findViewById(R.id.lnNav);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {
        findViewById(R.id.btnMenu).setOnClickListener(this);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                findViewById(R.id.space).setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                findViewById(R.id.lnHide).setVisibility(View.GONE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMenu:
                drawerLayout.openDrawer(lnNav);
                findViewById(R.id.lnHide).setVisibility(View.VISIBLE);
                break;
        }
    }
}
