package com.elearning.elearning.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.base.BaseEndlessRecyclerAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.dialog.DialogFilter;
import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.AllCoursePresenter;
import com.elearning.elearning.mvp.view.AllCourseView;

import java.util.Collections;
import java.util.List;

import static com.elearning.elearning.prefs.Constant.NUMBER_COLUMNS_2;
import static com.elearning.elearning.prefs.Constant.NUMBER_ITEM_SLIDE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_CATEGORY;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_DEFAULT;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_PRICE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_RATE;
import static com.elearning.elearning.prefs.Constant.TYPE_SORT_CREDITS;
import static com.elearning.elearning.prefs.Constant.TYPE_SORT_DEFAULT;
import static com.elearning.elearning.prefs.Constant.TYPE_SORT_NAME;
import static com.elearning.elearning.prefs.Constant.TYPE_SORT_PRICE;

/**
 * Created by MinhQuan on 19/07/2017.
 */

public class AllCourseFragment extends BaseFragment implements View.OnClickListener, AllCourseView {
    private TextView txtFilterPrice, txtFilterRate, txtFilterAll, txtFilterCategory, txtNotFound, txtSortName, txtSortPrice, txtSortCredits, txtSortDefault;
    private FrameLayout frFilter, frSort;
    private ImageView imgFilter, imgOneColumns, imgColumn;
    private String typeFilter, typeSort;
    private AllCoursePresenter allCoursePresenter;
    private RecyclerView rvListCourse;
    private CourseAdapter listCourseAdapter;
    private LinearLayout lnFilter, lnGrid, lnSort;

    @Override
    public void initView() {
        rvListCourse = (RecyclerView) view.findViewById(R.id.rvCourse);
        txtFilterPrice = (TextView) view.findViewById(R.id.txtFilterPrice);
        txtFilterRate = (TextView) view.findViewById(R.id.txtFilterRate);
        txtNotFound = (TextView) view.findViewById(R.id.txtNotFound);
        txtSortName = (TextView) view.findViewById(R.id.txtSortName);
        txtSortPrice = (TextView) view.findViewById(R.id.txtSortPrice);
        txtSortCredits = (TextView) view.findViewById(R.id.txtSortCredits);
        txtSortDefault = (TextView) view.findViewById(R.id.txtSortDefault);
        txtFilterAll = (TextView) view.findViewById(R.id.txtFilterAll);
        txtFilterCategory = (TextView) view.findViewById(R.id.txtFilterCategory);
        imgFilter = (ImageView) view.findViewById(R.id.imgFilter);
        imgOneColumns = (ImageView) view.findViewById(R.id.imgOneColumns);
        imgColumn = (ImageView) view.findViewById(R.id.imgColumn);
        frFilter = (FrameLayout) view.findViewById(R.id.frFilter);
        frSort = (FrameLayout) view.findViewById(R.id.frSort);
        lnFilter = (LinearLayout) view.findViewById(R.id.lnFilter);
        lnSort = (LinearLayout) view.findViewById(R.id.lnSort);
        lnGrid = (LinearLayout) view.findViewById(R.id.lnGrid);
    }

    @Override
    public void initValue() {
        allCoursePresenter = new AllCoursePresenter(this);
        //listCourse = new ArrayList<>();
        listCourseAdapter = new CourseAdapter(context, R.layout.item_course_search);
        rvListCourse.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvListCourse.setAdapter(listCourseAdapter);
        typeSort = TYPE_SORT_DEFAULT;
        typeFilter = TYPE_FILTER_DEFAULT;
    }

    @Override
    public void initAction() {
        txtFilterPrice.setOnClickListener(this);
        txtFilterRate.setOnClickListener(this);
        txtFilterAll.setOnClickListener(this);
        txtFilterCategory.setOnClickListener(this);
        txtSortName.setOnClickListener(this);
        txtSortPrice.setOnClickListener(this);
        txtSortCredits.setOnClickListener(this);
        txtSortDefault.setOnClickListener(this);
        lnFilter.setOnClickListener(this);
        lnSort.setOnClickListener(this);
        lnGrid.setOnClickListener(this);
        initCourseAdapter(false);
        setPageView();
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_all_course;
    }

    @Override
    public void onClick(View view) {
        lnFilter.setBackgroundColor(getResources().getColor(R.color.color_background_main));
        lnSort.setBackgroundColor(getResources().getColor(R.color.color_background_main));
        switch (view.getId()) {
            case R.id.lnFilter:
                frSort.setVisibility(View.GONE);
                if (frFilter.getVisibility() == View.VISIBLE) {
                    lnFilter.setBackgroundColor(getResources().getColor(R.color.color_background_main));
                    frFilter.setVisibility(View.GONE);
                } else {
                    lnFilter.setBackgroundColor(getResources().getColor(R.color.color_tab_selected));
                    frFilter.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lnSort:
                frFilter.setVisibility(View.GONE);
                if (frSort.getVisibility() == View.VISIBLE) {
                    lnSort.setBackgroundColor(getResources().getColor(R.color.color_background_main));
                    frSort.setVisibility(View.GONE);
                } else {
                    lnSort.setBackgroundColor(getResources().getColor(R.color.color_tab_selected));
                    frSort.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lnGrid:
                if (typeFilter == TYPE_FILTER_DEFAULT) {
                    Toast.makeText(context, getResources().getString(R.string.cap_not_support), Toast.LENGTH_LONG).show();
                } else {
                    if (imgOneColumns.getVisibility() == View.VISIBLE) {
                        imgOneColumns.setVisibility(View.GONE);
                        imgColumn.setVisibility(View.VISIBLE);
                        initCourseAdapter(true);
                    } else {
                        imgOneColumns.setVisibility(View.VISIBLE);
                        imgColumn.setVisibility(View.GONE);
                        initCourseAdapter(false);
                    }
                }
                break;
            case R.id.txtFilterPrice:
                typeFilter = TYPE_FILTER_PRICE;
                filter();
                setUI(false, false);
                break;
            case R.id.txtFilterRate:
                typeFilter = TYPE_FILTER_RATE;
                filter();
                setUI(false, false);
                break;
            case R.id.txtFilterAll:
                listCourseAdapter.removeAllItems();
                setPageView();
                typeFilter = TYPE_FILTER_DEFAULT;
                updateUIFilter();
                setUI(false, false);
                break;
            case R.id.txtFilterCategory:
                typeFilter = TYPE_FILTER_CATEGORY;
                filter();
                setUI(false, false);
                break;
            case R.id.txtSortName:
                typeSort = TYPE_SORT_NAME;
                sort();
                setUI(false, false);
                break;
            case R.id.txtSortPrice:
                typeSort = TYPE_SORT_PRICE;
                sort();
                setUI(false, false);
                break;
            case R.id.txtSortCredits:
                typeSort = TYPE_SORT_CREDITS;
                sort();
                setUI(false, false);
                break;
            case R.id.txtSortDefault:
                typeSort = TYPE_SORT_DEFAULT;
                sort();
                setUI(false, false);
                break;
        }
    }

    @Override
    public void onFilterRate(List<Course> courseArray) {
        if (courseArray.size() == 0) {
            txtNotFound.setVisibility(View.VISIBLE);
            rvListCourse.setVisibility(View.GONE);
        } else {
            txtNotFound.setVisibility(View.GONE);
            rvListCourse.setVisibility(View.VISIBLE);
        }
        listCourseAdapter.setEndlessLoadingEnable(false);
        listCourseAdapter.removeAllItems();
        listCourseAdapter.insertLoadmoreItems(courseArray);
        dismissProgressDialog();
    }

    @Override
    public void onFilterPrice(List<Course> courseArray) {
        if (courseArray.size() == 0) {
            txtNotFound.setVisibility(View.VISIBLE);
            rvListCourse.setVisibility(View.GONE);
        } else {
            txtNotFound.setVisibility(View.GONE);
            rvListCourse.setVisibility(View.VISIBLE);
        }
        listCourseAdapter.setEndlessLoadingEnable(false);
        listCourseAdapter.removeAllItems();
        listCourseAdapter.insertLoadmoreItems(courseArray);
        dismissProgressDialog();
    }

    @Override
    public void onFilterCategory(List<Course> courseArray) {
        if (courseArray.size() == 0) {
            txtNotFound.setVisibility(View.VISIBLE);
            rvListCourse.setVisibility(View.GONE);
        } else {
            txtNotFound.setVisibility(View.GONE);
            rvListCourse.setVisibility(View.VISIBLE);
        }
        listCourseAdapter.setEndlessLoadingEnable(false);
        listCourseAdapter.removeAllItems();
        listCourseAdapter.insertLoadmoreItems(courseArray);
        dismissProgressDialog();
    }

    @Override
    public void onGetListCategory(List<Category> listCategory) {
        dismissProgressDialog();
        Toast.makeText(context, getResources().getString(R.string.cap_error_data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetItemPage(List<Course> courseArray) {
        dismissProgressDialog();
        if (courseArray.size() == 0 && !listCourseAdapter.isEndlessLoadingEnable()) {
            txtNotFound.setVisibility(View.VISIBLE);
            rvListCourse.setVisibility(View.GONE);
        } else {
            txtNotFound.setVisibility(View.GONE);
            rvListCourse.setVisibility(View.VISIBLE);
        }
        if (courseArray.size() < NUMBER_ITEM_SLIDE) {
            listCourseAdapter.setEndlessLoadingEnable(false);
        } else {
            listCourseAdapter.setEndlessLoadingEnable(true);
        }
        listCourseAdapter.insertLoadmoreItems(courseArray);
        sort();
    }

    @Override
    public void onFilterFail(String mess) {
        dismissProgressDialog();
        Toast.makeText(context, getResources().getString(R.string.cap_error_data), Toast.LENGTH_SHORT).show();
    }

    public void setPageView() {
        if (txtNotFound.getVisibility() == View.VISIBLE) {
            showProgressDialog();
        }
        allCoursePresenter.getItemPage(NUMBER_ITEM_SLIDE, 1);
        //listCourseAdapter.setEndlessLoadingEnable(true);
        listCourseAdapter.setEndlessLoadingListener(rvListCourse, new BaseEndlessRecyclerAdapter.OnEndlessLoadListener() {
            @Override
            public void onEndlessLoad(int page) {
                allCoursePresenter.getItemPage(NUMBER_ITEM_SLIDE, page + 1);
            }
        });
    }

    private void filter() {
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
        updateUIFilter();
    }

    private void sort() {
        updateUISort();
        //add list and comparator to sort
        Collections.sort(listCourseAdapter.getItems(), new Course(typeSort));
        listCourseAdapter.reloadList();
    }

    private void updateUISort() {
        if (typeSort.equals(TYPE_SORT_NAME)) {
            txtSortName.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtSortDefault.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortPrice.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortCredits.setTextColor(getResources().getColor(R.color.colorBlack));
        } else if (typeSort.equals(TYPE_SORT_PRICE)) {
            txtSortPrice.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtSortDefault.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortName.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortCredits.setTextColor(getResources().getColor(R.color.colorBlack));
        } else if (typeSort.equals(TYPE_SORT_CREDITS)) {
            txtSortCredits.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtSortDefault.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortPrice.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortName.setTextColor(getResources().getColor(R.color.colorBlack));
        } else {
            txtSortDefault.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtSortCredits.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortPrice.setTextColor(getResources().getColor(R.color.colorBlack));
            txtSortName.setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    private void updateUIFilter() {
        if (typeFilter.equals(TYPE_FILTER_PRICE)) {
            txtFilterPrice.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtFilterRate.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterCategory.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterAll.setTextColor(getResources().getColor(R.color.colorBlack));
        } else if (typeFilter.equals(TYPE_FILTER_RATE)) {
            txtFilterRate.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtFilterPrice.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterCategory.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterAll.setTextColor(getResources().getColor(R.color.colorBlack));
        } else if (typeFilter.equals(TYPE_FILTER_CATEGORY)) {
            txtFilterCategory.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtFilterPrice.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterRate.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterAll.setTextColor(getResources().getColor(R.color.colorBlack));
        } else {
            txtFilterAll.setTextColor(getResources().getColor(R.color.color_background_main_blue));
            txtFilterPrice.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterRate.setTextColor(getResources().getColor(R.color.colorBlack));
            txtFilterCategory.setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    private void initCourseAdapter(boolean isTwoColumns) {
        if (isTwoColumns) {
            listCourseAdapter = new CourseAdapter(context, R.layout.item_course, listCourseAdapter.getItems());
            rvListCourse.setAdapter(listCourseAdapter);
            rvListCourse.setLayoutManager(new GridLayoutManager(context, NUMBER_COLUMNS_2));
        } else {
            listCourseAdapter = new CourseAdapter(context, R.layout.item_course_search, listCourseAdapter.getItems());
            rvListCourse.setAdapter(listCourseAdapter);
            rvListCourse.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        }
        listCourseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().goToListLesson(listCourseAdapter.getItem(position).getId());
            }
        });
    }

    public void setUI(boolean isShowSort, boolean isShowFilter) {
        if (isShowSort) {
            frSort.setVisibility(View.VISIBLE);
        } else {
            frSort.setVisibility(View.GONE);
        }
        if (isShowFilter) {
            frFilter.setVisibility(View.VISIBLE);
        } else {
            frFilter.setVisibility(View.GONE);
        }

    }
}
