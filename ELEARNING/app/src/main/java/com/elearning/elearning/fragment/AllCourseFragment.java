package com.elearning.elearning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.base.BaseEndlessRecyclerAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.dialog.DialogFilter;
import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.AllCoursePresenter;
import com.elearning.elearning.mvp.view.AllCourseView;

import java.util.List;

import static com.elearning.elearning.prefs.Constant.NUMBER_ITEM_SLIDE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_CATEGORY;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_PRICE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_RATE;

/**
 * Created by MinhQuan on 19/07/2017.
 */

public class AllCourseFragment extends BaseFragment implements View.OnClickListener, AllCourseView {
    private TextView txtFilterPrice, txtFilterRate, txtAll, txtFilterCategory;
    private FrameLayout frFilter;
    private ImageView imgFilter;
    private String typeFilter;
    private AllCoursePresenter allCoursePresenter;
    private RecyclerView rvListCourse;
    private CourseAdapter listCourseAdapter;
    private LinearLayout lnFilter;

    @Override
    public void initView() {
        rvListCourse = (RecyclerView) view.findViewById(R.id.rvCourse);
        txtFilterPrice = (TextView) view.findViewById(R.id.txtFilterPrice);
        txtFilterRate = (TextView) view.findViewById(R.id.txtFilterRate);
        txtAll = (TextView) view.findViewById(R.id.txtAll);
        txtFilterCategory = (TextView) view.findViewById(R.id.txtFilterCategory);
        imgFilter = (ImageView) view.findViewById(R.id.imgFilter);
        frFilter = (FrameLayout) view.findViewById(R.id.frFilter);
        lnFilter = (LinearLayout) view.findViewById(R.id.lnFilter);
    }

    @Override
    public void initValue() {
        allCoursePresenter = new AllCoursePresenter(this);
        //listCourse = new ArrayList<>();
        listCourseAdapter = new CourseAdapter(context, R.layout.item_course_search);
        rvListCourse.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvListCourse.setAdapter(listCourseAdapter);
    }

    @Override
    public void initAction() {
        txtFilterPrice.setOnClickListener(this);
        txtFilterRate.setOnClickListener(this);
        txtAll.setOnClickListener(this);
        txtFilterCategory.setOnClickListener(this);
        setPageView();
        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frFilter.getVisibility() == View.VISIBLE) {
                    lnFilter.setBackgroundColor(getResources().getColor(R.color.color_background_main));
                    frFilter.setVisibility(View.GONE);
                } else {
                    lnFilter.setBackgroundColor(getResources().getColor(R.color.color_tab_selected));
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
            case R.id.txtAll:
                listCourseAdapter.removeAllItems();
                setPageView();
                typeFilter = null;
                break;
            case R.id.txtFilterCategory:
                typeFilter = TYPE_FILTER_CATEGORY;
                break;
        }
        frFilter.setVisibility(View.GONE);
        if (typeFilter != null) {
            new DialogFilter.Build(getMainActivity(), typeFilter).setonFilterListener(new DialogFilter.Build.onFilterListener() {
                @Override
                public void onCancel() {
                    Log.d("Filter", "Cancel");
                }

                @Override
                public void onApplyPrice(String from, String to) {
                    showProgressDialog();
                    Log.d("Filter Apply Price", from + "--->" + to);
                    allCoursePresenter.filterPrice(Integer.parseInt(from.replace(".", "")), Integer.parseInt(to.replace(".", "")));
                }

                @Override
                public void onApplyRate(int minStar, int maxStar) {
                    showProgressDialog();
                    allCoursePresenter.filterRate(minStar, maxStar);
                    Log.d("Filter Apply Rate", String.valueOf(minStar) + "--->" + String.valueOf(maxStar));
                }

                @Override
                public void onApplyCategor(int idCategory) {
                    showProgressDialog();
                    allCoursePresenter.filterCategory(idCategory);
                    Log.d("Filter Apply Category", String.valueOf(idCategory));
                }
            }).show();
        }
    }

    @Override
    public void onFilterRate(List<Course> courseArray) {
        listCourseAdapter.removeAllItems();
        listCourseAdapter.insertLoadmoreItems(courseArray);
        dismissProgressDialog();
    }

    @Override
    public void onFilterPrice(List<Course> courseArray) {
        listCourseAdapter.removeAllItems();
        listCourseAdapter.insertLoadmoreItems(courseArray);
        dismissProgressDialog();
    }

    @Override
    public void onFilterCategory(List<Course> courseArray) {
        listCourseAdapter.removeAllItems();
        listCourseAdapter.insertLoadmoreItems(courseArray);
        dismissProgressDialog();
    }

    @Override
    public void onGetListCategory(List<Category> listCategory) {

    }

    @Override
    public void onGetItemPage(List<Course> courseArray) {
        if (courseArray.size() < NUMBER_ITEM_SLIDE) {
            listCourseAdapter.setEndlessLoadingEnable(false);
        }else{
            listCourseAdapter.setEndlessLoadingEnable(true);
        }
        listCourseAdapter.insertLoadmoreItems(courseArray);

    }

    @Override
    public void onFilterFail(String mess) {

    }
    public  void  setPageView(){
        allCoursePresenter.getItemPage(NUMBER_ITEM_SLIDE, 1);
        //listCourseAdapter.setEndlessLoadingEnable(true);
        listCourseAdapter.setEndlessLoadingListener(rvListCourse, new BaseEndlessRecyclerAdapter.OnEndlessLoadListener() {
            @Override
            public void onEndlessLoad(int page) {
                allCoursePresenter.getItemPage(NUMBER_ITEM_SLIDE, page + 1);
            }
        });
    }
}
