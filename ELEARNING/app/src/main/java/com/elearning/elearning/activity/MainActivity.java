package com.elearning.elearning.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.signin_signup.SignInSignUpActivity;
import com.elearning.elearning.adapter.NavAdapter;
import com.elearning.elearning.base.BaseActivity;
import com.elearning.elearning.dialog.DialogLogOut;
import com.elearning.elearning.mvp.model.User;
import com.elearning.elearning.mvp.presenter.MainPresenter;
import com.elearning.elearning.mvp.view.MainView;
import com.elearning.elearning.prefs.Constant;
import com.squareup.picasso.Picasso;

/**
 * Created by vomin on 30/06/2017.
 */

public class MainActivity extends BaseActivity implements MainView, View.OnClickListener {
    private DrawerLayout drawerLayout;
    private LinearLayout lnNav;
    private RecyclerView rlNav;
    private NavAdapter navAdapter;
    private MainPresenter mainPresenter;
    //information for navigation
    private ImageView avatarUser;
    private TextView txtUserName;


    @Override
    public int getView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        avatarUser = (ImageView) findViewById(R.id.avatarUser);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        mainPresenter = new MainPresenter(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        lnNav = (LinearLayout) findViewById(R.id.lnNav);
        rlNav = (RecyclerView) findViewById(R.id.navRows);
    }

    @Override
    public void initValue() {
        rlNav.setLayoutManager(new LinearLayoutManager(context));
        rlNav.setItemAnimator(new DefaultItemAnimator());
        navAdapter = new NavAdapter(this);
        rlNav.setAdapter(navAdapter);
        navAdapter.setOnItemClickListener(mainPresenter.getOnItemMenuListener());
        //set value for information user
        txtUserName.setText(User.get().getUserName());
        Picasso.with(context).load(User.get().getUrlAvatar()).into(avatarUser);
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

    @Override
    public void exitApp() {

    }

    @Override
    public void setChecked(String id) {

    }

    @Override
    public void onCloseDrawer() {
        drawerLayout.closeDrawers();
        findViewById(R.id.lnHide).setVisibility(View.GONE);
    }

    @Override
    public void onLogout() {
        new DialogLogOut.Build(this).setOnLogoutListener(new DialogLogOut.Build.OnLogoutListener() {
            @Override
            public void onConfirm() {
                goLogin();
            }

            @Override
            public void onCancel() {

            }
        }).show();
    }

    private void goLogin() {
        sharedPreferences.edit().putBoolean(Constant.AUTO_LOGIN, false).apply();
        sharedPreferences.edit().putString(Constant.USER_EMAIL, "").apply();
        sharedPreferences.edit().putString(Constant.USER_PW, "").apply();
        Intent intent = new Intent(context, SignInSignUpActivity.class);
        context.startActivity(intent);
        finish();
    }
}
