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
import com.elearning.elearning.dialog.DialogConfirm;
import com.elearning.elearning.helper.Sound;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.model.User;
import com.elearning.elearning.mvp.presenter.MainPresenter;
import com.elearning.elearning.mvp.view.MainView;
import com.elearning.elearning.network.APIConstant;
import com.elearning.elearning.prefs.Constant;
import com.google.firebase.messaging.FirebaseMessaging;
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
    private LinearLayout lnActivity, lnFragment, lnSearch, lnMenu, lnSearchContainer, lnFragmentContainer;
    private android.widget.SearchView searchView;
    //search result
    private RecyclerView mRecyclerSearchResult;
    private CourseAdapter mCourseAdapter;
    private List<Course> listCourse;
    private static MainActivity.onSendCourseID onSendCourseID;
    private static MainActivity.onExitExam onExitExam;
    private static Sound sound;
    public static int IdLesson = 0;


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
        lnFragmentContainer = (LinearLayout) findViewById(R.id.lnContainer);
        rlNav = (RecyclerView) findViewById(R.id.navRows);
        searchView = (android.widget.SearchView) findViewById(R.id.edtSearch);
        mRecyclerSearchResult = (RecyclerView) findViewById(R.id.recyclerSearchResult);
    }

    public static void setSendCourseID(MainActivity.onSendCourseID onSendCourseID) {
        MainActivity.onSendCourseID = onSendCourseID;
    }

    public static void setOnExitExam(MainActivity.onExitExam onExitExam) {
        MainActivity.onExitExam = onExitExam;
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
        Picasso.with(context).load(APIConstant.HOST_NAME_IMAGE + User.get().getUrlAvatar()).resize(350, 350).into(avatarUser);
        lnActivity = (LinearLayout) findViewById(R.id.lnActivity);
        lnFragment = (LinearLayout) findViewById(R.id.lnFragment);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        listCourse = new ArrayList();
        mCourseAdapter = new CourseAdapter(context, listCourse, R.layout.item_course_search);
        mRecyclerSearchResult.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerSearchResult.setAdapter(mCourseAdapter);
        // subscribe to topic
        FirebaseMessaging.getInstance().subscribeToTopic(Constant.FIREBASE_TOPIC_USER);
        sound = new Sound(context);
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
                lnFragmentContainer.setVisibility(View.GONE);
                lnSearchContainer.setVisibility(View.VISIBLE);
                return false;
            }
        });
        mCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                lnFragmentContainer.setVisibility(View.VISIBLE);
                lnSearchContainer.setVisibility(View.GONE);
                gotoFragment(getResources().getString(R.string.menu_listlesson));
                if (onSendCourseID != null) {
                    onSendCourseID.onSend(listCourse.get(position).getId());
                }
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
//                updateToolbar(false, false, null);
                onBackPressed();
                break;
        }
    }

    @Override
    public void exitApp() {
        finish();
    }

    @Override
    public void onCloseDrawer() {
        drawerLayout.closeDrawers();
        findViewById(R.id.lnHide).setVisibility(View.GONE);
    }

    @Override
    public void onLogout() {
        new DialogConfirm.Build(this).setOnLogoutListener(new DialogConfirm.Build.OnLogoutListener() {
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
    public void updateToolbar(boolean isHome, boolean isSearch, String title) {
        txtTitle.setText(title);
        if (isHome) {
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

    @Override
    public void exitExam() {
        onExitExam.onExitExam();
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
        onCloseDrawer();
        if (lnSearchContainer.getVisibility() == View.VISIBLE) {
            updateToolbar(false, false, null);
            findViewById(R.id.lnContainer).setVisibility(View.VISIBLE);
            lnSearchContainer.setVisibility(View.GONE);
            searchView.setQuery("", false);
            searchView.clearFocus();
        } else {
            mainPresenter.onBackPressed();
        }
    }

    public void gotoFragment(String id) {
        mainPresenter.goToFragment(id);
    }

    public interface onSendCourseID {
        void onSend(int CourseId);
    }

    public interface onExitExam {
        void onExitExam();
    }

    public static int getIdLesson() {
        return IdLesson;
    }

    public static void setIdLesson(int idLesson) {
        IdLesson = idLesson;
    }

    public void playSound() {
        sound.playRingtone();
    }

    public void loadAvatar() {
        Picasso.with(context).load(APIConstant.HOST_NAME_IMAGE + User.get().getUrlAvatar()).resize(350, 350).into(avatarUser);
    }

    public void backOnePage() {
        mainPresenter.backOnePage();
    }
}
