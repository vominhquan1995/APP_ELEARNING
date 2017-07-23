package com.elearning.elearning.fragment;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.HomePresenter;
import com.elearning.elearning.mvp.view.HomeView;
import com.elearning.elearning.adapter.SlideAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.elearning.elearning.prefs.Constant.NUMBER_ITEM_SLIDE;

/**
 * Created by vomin on 30/06/2017.
 */

public class HomeFragment extends BaseFragment implements HomeView {
    private RecyclerView mRecyclerNew, mRecyclerMost, mRecyclerTopView;
    private CourseAdapter newCourseAdapter;
    private CourseAdapter mostCourseAdapter;
    private CourseAdapter topReviewCourseAdapter;
    private HomePresenter homePresenter;
    private TextView txtViewMore1, txtViewMore2, txtViewMore3;
    private ViewPager slide;
    private SlideAdapter slideAdapter;
    private Timer timer;
    private int page = 0;


    @Override
    public int setFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        txtViewMore1 = (TextView) view.findViewById(R.id.txtViewMore);
        txtViewMore2 = (TextView) view.findViewById(R.id.txtViewMore2);
        txtViewMore3 = (TextView) view.findViewById(R.id.txtViewMore3);
        mRecyclerNew = (RecyclerView) view.findViewById(R.id.recyclerNew);
        mRecyclerNew.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        //most
        mRecyclerMost = (RecyclerView) view.findViewById(R.id.recyclerMost);
        mRecyclerMost.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        //top view
        mRecyclerTopView = (RecyclerView) view.findViewById(R.id.recyclerTopView);
        mRecyclerTopView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        int deviceHeightInPixels = context.getResources().getDisplayMetrics().heightPixels;
        slide = (ViewPager) view.findViewById(R.id.vpSlide);
        slide.getLayoutParams().height = deviceHeightInPixels / 4;
    }

    @Override
    public void initValue() {
        //init presenter home
        homePresenter = new HomePresenter(this);
        newCourseAdapter = new CourseAdapter(context, R.layout.item_course);
        mostCourseAdapter = new CourseAdapter(context, R.layout.item_course);
        topReviewCourseAdapter = new CourseAdapter(context, R.layout.item_course);
        mRecyclerNew.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerNew.setAdapter(newCourseAdapter);
        mRecyclerMost.setAdapter(mostCourseAdapter);
        mRecyclerTopView.setAdapter(topReviewCourseAdapter);
        slideAdapter = new SlideAdapter(getMainActivity());
        slide.setAdapter(slideAdapter);
        newCourseAdapter.setEndlessLoadingEnable(false);
        mostCourseAdapter.setEndlessLoadingEnable(false);
        topReviewCourseAdapter.setEndlessLoadingEnable(false);

    }

    @Override
    public void initAction() {
        txtViewMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_all_course));
            }
        });
        txtViewMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        txtViewMore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        pageSwitcher(3);
        load();
//        homePresenter.getListNewCourse(NUMBER_ITEM_SLIDE);
//        getMainActivity().showProgressDialog();
        newCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(newCourseAdapter.getItem(position).getId());
            }
        });
        mostCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(mostCourseAdapter.getItem(position).getId());
            }
        });
        topReviewCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(topReviewCourseAdapter.getItem(position).getId());
            }
        });
    }
    public void load() {
        showProgressDialog();
        homePresenter.getListNewCourse(NUMBER_ITEM_SLIDE);
    }
    @Override
    public void onGetNewCourseSuccess(List<Course> courseArray) {
        newCourseAdapter.removeAllItems();
        newCourseAdapter.insertLoadmoreItems(courseArray);
        homePresenter.getListMostCourse(NUMBER_ITEM_SLIDE);
    }

    @Override
    public void onGetNewCourseFail(String message) {
        dismissProgressDialog();
        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetMostCourseSuccess(List<Course> courseArray) {
        mostCourseAdapter.removeAllItems();
        mostCourseAdapter.insertLoadmoreItems(courseArray);
        homePresenter.getListTopReviewCourse(NUMBER_ITEM_SLIDE);
    }

    @Override
    public void onGetMostCourseFail(String message) {
        dismissProgressDialog();
        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetTopReviewCourseSuccess(List<Course> courseArray) {
        getMainActivity().dismissProgressDialog();
        topReviewCourseAdapter.removeAllItems();
        topReviewCourseAdapter.insertLoadmoreItems(courseArray);
    }

    @Override
    public void onGetTopReviewCourseFail(String message) {
        dismissProgressDialog();
        Toast.makeText(context,getResources().getString(R.string.cap_error_data),Toast.LENGTH_SHORT).show();
    }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                    if (page > 3) { // In my case the number of pages are 4
                        page = 0;
//                        timer.cancel();
                    } else {
                        slide.setCurrentItem(page++);
                    }
                }
            });

        }
    }
}
