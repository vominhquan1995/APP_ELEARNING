package com.elearning.elearning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.dialog.DialogFilter;
import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.AllCoursePresenter;
import com.elearning.elearning.mvp.view.AllCourseView;

import java.util.ArrayList;
import java.util.List;

import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_CATEGORY;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_PRICE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_RATE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_SALE;

/**
 * Created by MinhQuan on 19/07/2017.
 */

public class AllCourseFragment extends BaseFragment implements View.OnClickListener, AllCourseView {
    private TextView txtFilterPrice, txtFilterRate, txtFilterSale, txtFilterCategory;
    private FrameLayout frFilter;
    private ImageView imgFilter;
    private String typeFilter;
    private AllCoursePresenter allCoursePresenter;
    private RecyclerView rvListCourse;
    private CourseAdapter listCourseAdapter;
    private List<Course> listCourse;

    @Override
    public void initView() {
        rvListCourse = (RecyclerView) view.findViewById(R.id.rvCourse);
        txtFilterPrice = (TextView) view.findViewById(R.id.txtFilterPrice);
        txtFilterRate = (TextView) view.findViewById(R.id.txtFilterRate);
        txtFilterSale = (TextView) view.findViewById(R.id.txtFilterSale);
        txtFilterCategory = (TextView) view.findViewById(R.id.txtFilterCategory);
        imgFilter = (ImageView) view.findViewById(R.id.imgFilter);
        frFilter = (FrameLayout) view.findViewById(R.id.frFilter);
    }

    @Override
    public void initValue() {
        allCoursePresenter = new AllCoursePresenter(this);
        listCourse = new ArrayList<>();
        listCourseAdapter = new CourseAdapter(context, listCourse, R.layout.item_course_search);
        rvListCourse.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvListCourse.setAdapter(listCourseAdapter);
    }

    @Override
    public void initAction() {
        txtFilterPrice.setOnClickListener(this);
        txtFilterRate.setOnClickListener(this);
        txtFilterSale.setOnClickListener(this);
        txtFilterCategory.setOnClickListener(this);
        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frFilter.getVisibility() == View.VISIBLE) {
                    frFilter.setVisibility(View.GONE);
                } else {
                    frFilter.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_all_course;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtFilterPrice:
                typeFilter = TYPE_FILTER_PRICE;
                break;
            case R.id.txtFilterRate:
                typeFilter = TYPE_FILTER_RATE;
                break;
            case R.id.txtFilterSale:
                typeFilter = TYPE_FILTER_SALE;
                break;
            case R.id.txtFilterCategory:
                typeFilter = TYPE_FILTER_CATEGORY;
                break;
        }
        frFilter.setVisibility(View.GONE);
        new DialogFilter.Build(getMainActivity(), typeFilter).setonFilterListener(new DialogFilter.Build.onFilterListener() {
            @Override
            public void onCancel() {
                Log.d("Filter", "Cancel");
            }

            @Override
            public void onApplyPrice(String from, String to) {
                showProgressDialog();
                Log.d("Filter Apply Price", from + "--->" + to);
                allCoursePresenter.filterPrice(Integer.parseInt(from.replace(".","")),Integer.parseInt(to.replace(".","")));
            }

            @Override
            public void onApplyRate(int minStar, int maxStar) {
                showProgressDialog();
                allCoursePresenter.filterRate(minStar, maxStar);
                Log.d("Filter Apply Rate", String.valueOf(minStar) + "--->" + String.valueOf(maxStar));
            }
        }).show();
    }

    @Override
    public void onFilterRate(List<Course> courseArray) {
        listCourse.removeAll(listCourse);
        for (Course item : courseArray) {
            listCourse.add(item);
        }
        listCourseAdapter.notifyDataSetChanged();
        dismissProgressDialog();
    }

    @Override
    public void onFilterPrice(List<Course> courseArray) {
        listCourse.removeAll(listCourse);
        for (Course item : courseArray) {
            listCourse.add(item);
        }
        listCourseAdapter.notifyDataSetChanged();
        dismissProgressDialog();
    }

    @Override
    public void onFilterCategory(List<Course> courseArray) {

    }

    @Override
    public void onGetListCategory(List<Category> listCategory) {

    }

    @Override
    public void onFilterFail(String mess) {

    }
}
