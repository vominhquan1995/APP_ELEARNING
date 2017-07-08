package com.elearning.elearning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.HomePresenter;
import com.elearning.elearning.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vomin on 30/06/2017.
 */

public class HomeFragment extends BaseFragment implements HomeView {
    //send id course to list lesson
    private static HomeFragment.onSendCourseID onSendCourseID;
    private RecyclerView mRecyclerNew, mRecyclerMost, mRecyclerTopView;
    private CourseAdapter mCourseAdapter;
    private HomePresenter homePresenter;
    //list Course
    private List<Course> listNewCourse = new ArrayList<>();

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
    }

    @Override
    public void initValue() {
        //init presenter home
        homePresenter = new HomePresenter(this);
        mCourseAdapter = new CourseAdapter(context, listNewCourse, R.layout.item_course);
        mRecyclerNew.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerNew.setAdapter(mCourseAdapter);
        mRecyclerMost.setAdapter(mCourseAdapter);
        mRecyclerTopView.setAdapter(mCourseAdapter);

    }

    @Override
    public void initAction() {
        homePresenter.getListNewCourse(8);
        getMainActivity().showProgressDialog();
        mCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_listlesson));
                if (onSendCourseID != null) {
                    onSendCourseID.onSend(listNewCourse.get(position).getId());
                }
                Log.d("Home", String.valueOf(mCourseAdapter.getItem(position).getId()));
            }
        });
    }

    @Override
    public void onGetNewCourseSuccess(List<Course> courseArray) {
        getMainActivity().dismissProgressDialog();
        //don't set this.listNewCourse=courseArray cuz notifyDataSetChanged will not working
        for (Course itemCourse : courseArray) {
            this.listNewCourse.add(itemCourse);
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetNewCourseFail(String message) {
        Log.d("Home", "Fail");
    }

    public interface onSendCourseID {
        void onSend(int CourseId);
    }
}
