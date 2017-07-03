package com.elearning.elearning.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.signin_signup.SignInSignUpActivity;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.adapter.NavAdapter;
import com.elearning.elearning.base.BaseActivity;
import com.elearning.elearning.dialog.DialogLogOut;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.model.User;
import com.elearning.elearning.mvp.presenter.MainPresenter;
import com.elearning.elearning.mvp.view.MainView;
import com.elearning.elearning.prefs.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    private TextView txtUserName, txtTitle;
    private LinearLayout lnActivity, lnFragment, lnSearch, lnMenu, lnSearchContainer;
    private android.widget.SearchView searchView;
    //search result
    private RecyclerView mRecyclerSearchResult;
    private CourseAdapter mCourseAdapter;
    private List<Course> listCourse;


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
        lnSearch = (LinearLayout) findViewById(R.id.lnSearch);
        lnMenu = (LinearLayout) findViewById(R.id.btnMenu);
        lnSearchContainer = (LinearLayout) findViewById(R.id.lnSearchContainer);
        rlNav = (RecyclerView) findViewById(R.id.navRows);
        searchView = (android.widget.SearchView) findViewById(R.id.edtSearch);
        mRecyclerSearchResult = (RecyclerView) findViewById(R.id.recyclerSearchResult);
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
        lnActivity = (LinearLayout) findViewById(R.id.lnActivity);
        lnFragment = (LinearLayout) findViewById(R.id.lnFragment);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        listCourse = new ArrayList();
        mCourseAdapter = new CourseAdapter(context, listCourse, R.layout.item_course_search);
        mRecyclerSearchResult.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerSearchResult.setAdapter(mCourseAdapter);
    }

    @Override
    public void initAction() {
        lnMenu.setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(this);
        searchView.setOnClickListener(this);
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
        searchView.setOnSearchClickListener(this);
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mainPresenter.search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                updateToolbar(false, true, null);
                findViewById(R.id.lnContainer).setVisibility(View.GONE);
                lnSearchContainer.setVisibility(View.VISIBLE);
                return false;
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
            case R.id.edtSearch:
//                updateToolbar(false, true, null);
//                findViewById(R.id.lnContainer).setVisibility(View.GONE);
//                lnSearchContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.btnBack:
                updateToolbar(false, false, null);
                onBackPressed();
                break;
        }
    }

    @Override
    public void exitApp() {

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

    @Override
    public void updateToolbar(boolean isFragment, boolean isSearch, String title) {
        txtTitle.setText(title);
        if (isFragment) {
            lnActivity.setVisibility(View.GONE);
            lnFragment.setVisibility(View.VISIBLE);
        } else {
            if (isSearch) {
                lnMenu.setVisibility(View.GONE);
            } else {
                lnMenu.setVisibility(View.VISIBLE);
            }
            lnActivity.setVisibility(View.VISIBLE);
            lnFragment.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSearchResult(List<Course> courseList) {
        this.listCourse.clear();
        for (Course itemCourse : courseList) {
            this.listCourse.add(itemCourse);
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void setItemSelected(String id) {
        navAdapter.setSelectedId(id);
    }

    private void goLogin() {
        sharedPreferences.edit().putBoolean(Constant.AUTO_LOGIN, false).apply();
        sharedPreferences.edit().putString(Constant.USER_EMAIL, "").apply();
        sharedPreferences.edit().putString(Constant.USER_PW, "").apply();
        Intent intent = new Intent(context, SignInSignUpActivity.class);
        context.startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (lnSearchContainer.getVisibility() == View.VISIBLE) {
            updateToolbar(false, false, null);
            findViewById(R.id.lnContainer).setVisibility(View.VISIBLE);
            lnSearchContainer.setVisibility(View.GONE);
            searchView.setQuery("", false);
            searchView.clearFocus();
        } else {
            super.onBackPressed();
        }
        onCloseDrawer();
        mainPresenter.onBackPressed();
    }
}
