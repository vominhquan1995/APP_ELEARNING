package com.elearning.elearning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.HomePresenter;
import com.elearning.elearning.mvp.view.HomeView;
import com.elearning.elearning.widget.CustomSliderView;

import java.util.List;

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
    private SliderLayout sliderLayout;
    private String[] urlSlide = new String[]{"http://apiyduoctructuyen.azurewebsites.net/FilesUploaded/slide_1.png", "http://apiyduoctructuyen.azurewebsites.net/FilesUploaded/slide_2.png", "http://apiyduoctructuyen.azurewebsites.net/FilesUploaded/slide_3.png", "http://apiyduoctructuyen.azurewebsites.net/FilesUploaded/slide_4.png"};


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
//        slide = (ViewPager) view.findViewById(R.id.vpSlide);
        sliderLayout = (SliderLayout) view.findViewById(R.id.vpSlide);
        sliderLayout.getLayoutParams().height = deviceHeightInPixels / 4;
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
        newCourseAdapter.setEndlessLoadingEnable(false);
        mostCourseAdapter.setEndlessLoadingEnable(false);
        topReviewCourseAdapter.setEndlessLoadingEnable(false);
        initSlider(sliderLayout, urlSlide);
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
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_all_course));
            }
        });
        txtViewMore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_all_course));
            }
        });
        load();
//        homePresenter.getListNewCourse(NUMBER_ITEM_SLIDE);
//        getMainActivity().showProgressDialog();
        newCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(newCourseAdapter.getItem(position).getId(),newCourseAdapter.getItem(position).getNameCourses());
            }
        });
        mostCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(mostCourseAdapter.getItem(position).getId(),mostCourseAdapter.getItem(position).getNameCourses());
            }
        });
        topReviewCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(topReviewCourseAdapter.getItem(position).getId(),topReviewCourseAdapter.getItem(position).getNameCourses());
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
        Toast.makeText(context, getResources().getString(R.string.cap_error_data), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(context, getResources().getString(R.string.cap_error_data), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(context, getResources().getString(R.string.cap_error_data), Toast.LENGTH_SHORT).show();
    }

    public void initSlider(SliderLayout sliderLayout, final String[] url) {
        for (String item : url) {
            CustomSliderView customSliderView = new CustomSliderView(context);
            // initialize a SliderLayout
            customSliderView
                    .image(item)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
//                            Toast.makeText(context, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                        }
                    });
            //add your extra information
            sliderLayout.addSlider(customSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(3000);
    }
}
