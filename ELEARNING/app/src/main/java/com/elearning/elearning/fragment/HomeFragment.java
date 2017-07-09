package com.elearning.elearning.fragment;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    //send id course to list lesson
    private static HomeFragment.onSendCourseID onSendCourseID;
    private RecyclerView mRecyclerNew, mRecyclerMost, mRecyclerTopView;
    private CourseAdapter newCourseAdapter;
    private CourseAdapter mostCourseAdapter;
    private CourseAdapter topReviewCourseAdapter;
    private HomePresenter homePresenter;
    //list Course
    private List<Course> listNewCourse = new ArrayList<>();
    private List<Course> listMostCourse = new ArrayList<>();
    private List<Course> listTopReview = new ArrayList<>();
    private ViewPager slide;
    private SlideAdapter slideAdapter;
    private Timer timer;
    private int page = 0;

    public static void setSendCourseID(HomeFragment.onSendCourseID onSendCourseID) {
        HomeFragment.onSendCourseID = onSendCourseID;
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
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
        newCourseAdapter = new CourseAdapter(context, listNewCourse, R.layout.item_course);
        mostCourseAdapter = new CourseAdapter(context, listMostCourse, R.layout.item_course);
        topReviewCourseAdapter = new CourseAdapter(context, listTopReview, R.layout.item_course);
        mRecyclerNew.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerNew.setAdapter(newCourseAdapter);
        mRecyclerMost.setAdapter(mostCourseAdapter);
        mRecyclerTopView.setAdapter(topReviewCourseAdapter);
        slideAdapter = new SlideAdapter(getMainActivity());
        slide.setAdapter(slideAdapter);
    }

    @Override
    public void initAction() {
        pageSwitcher(3);
        homePresenter.getListNewCourse(NUMBER_ITEM_SLIDE);
        getMainActivity().showProgressDialog();
        newCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_listlesson));
                if (onSendCourseID != null) {
                    onSendCourseID.onSend(listNewCourse.get(position).getId());
                }
            }
        });
        mostCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_listlesson));
                if (onSendCourseID != null) {
                    onSendCourseID.onSend(listMostCourse.get(position).getId());
                }
            }
        });
        topReviewCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_listlesson));
                if (onSendCourseID != null) {
                    onSendCourseID.onSend(listTopReview.get(position).getId());
                }
            }
        });
    }

    @Override
    public void onGetNewCourseSuccess(List<Course> courseArray) {
        //don't set this.listNewCourse=courseArray cuz notifyDataSetChanged will not working
        for (Course itemCourse : courseArray) {
            this.listNewCourse.add(itemCourse);
        }
        newCourseAdapter.notifyDataSetChanged();
        homePresenter.getListMostCourse(NUMBER_ITEM_SLIDE);
    }

    @Override
    public void onGetNewCourseFail(String message) {
        Log.d("Home", "Fail");
    }

    @Override
    public void onGetMostCourseSuccess(List<Course> courseArray) {
        Log.d("Home", "most");
        getMainActivity().dismissProgressDialog();
        for (Course itemCourse : courseArray) {
            this.listMostCourse.add(itemCourse);
        }
        mostCourseAdapter.notifyDataSetChanged();
        homePresenter.getListTopReviewCourse(NUMBER_ITEM_SLIDE);
    }

    @Override
    public void onGetMostCourseFail(String message) {
        Log.d("Home", "Fail");
    }

    @Override
    public void onGetTopReviewCourseSuccess(List<Course> courseArray) {
        getMainActivity().dismissProgressDialog();
        Log.d("Home", "Top-reivew");
        getMainActivity().dismissProgressDialog();
        for (Course itemCourse : courseArray) {
            this.listTopReview.add(itemCourse);
        }
        topReviewCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetTopReviewCourseFail(String message) {
        Log.d("Home", "Fail");
    }

    public interface onSendCourseID {
        void onSend(int CourseId);
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
