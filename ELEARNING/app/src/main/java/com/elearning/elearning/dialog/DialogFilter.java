package com.elearning.elearning.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.elearning.elearning.R;
import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.presenter.AllCoursePresenter;
import com.elearning.elearning.mvp.view.AllCourseView;
import com.elearning.elearning.prefs.Number;

import java.util.ArrayList;
import java.util.List;

import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_CATEGORY;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_PRICE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_RATE;

/**
 * Created by MinhQuan on 19/07/2017.
 */

public class DialogFilter {
    public static class Build implements AllCourseView {
        ArrayAdapter<String> spinnerAdapter;
        private Activity activity;
        private AlertDialog.Builder builder;
        private AlertDialog dialog;
        private TextView txtPriceFrom, txtPriceTo;
        private Button btnCancel, btnApply;
        private CrystalRangeSeekbar rangeSeekbarPrice;
        private RatingBar ratingBarMin, ratingBarMax;
        private onFilterListener onFilterListener;
        private FrameLayout frPrice, frRate, frCategory;
        private AllCoursePresenter allCoursePresenter;
        private Spinner spinnerCategory;
        private String typeFilter;
        private List<String> listTitleCategory;
        private List<Category> categoryList;
        private int posCategorySelected;
        private LinearLayout lnButton;

        public Build(final Activity activity, final String typeFilter) {
            this.activity = activity;
            this.typeFilter = typeFilter;
            builder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_filter, null);
            //get view
            lnButton = (LinearLayout) view.findViewById(R.id.lnButton);
            btnCancel = (Button) view.findViewById(R.id.btnCancel);
            btnApply = (Button) view.findViewById(R.id.btnApply);
            txtPriceFrom = (TextView) view.findViewById(R.id.txtPriceFrom);
            txtPriceTo = (TextView) view.findViewById(R.id.txtPriceTo);
            rangeSeekbarPrice = (CrystalRangeSeekbar) view.findViewById(R.id.rangeSeekbarPrice);
            frPrice = (FrameLayout) view.findViewById(R.id.frPrice);
            frRate = (FrameLayout) view.findViewById(R.id.frRate);
            frCategory = (FrameLayout) view.findViewById(R.id.frCategory);
            ratingBarMin = (RatingBar) view.findViewById(R.id.rtMin);
            ratingBarMax = (RatingBar) view.findViewById(R.id.rtMax);
            spinnerCategory = (Spinner) view.findViewById(R.id.spCategory);
            //init value
            categoryList = new ArrayList<>();
            listTitleCategory = new ArrayList<>();
            allCoursePresenter = new AllCoursePresenter(this);
            //set type filter
            if (typeFilter == TYPE_FILTER_PRICE) {
                lnButton.setVisibility(View.VISIBLE);
                frPrice.setVisibility(View.VISIBLE);
                frCategory.setVisibility(View.VISIBLE);
                frRate.setVisibility(View.GONE);
            } else if (typeFilter == TYPE_FILTER_RATE) {
                lnButton.setVisibility(View.VISIBLE);
                frPrice.setVisibility(View.GONE);
                frCategory.setVisibility(View.GONE);
                frRate.setVisibility(View.VISIBLE);
            } else if (typeFilter == TYPE_FILTER_CATEGORY) {
                allCoursePresenter.getListCategory();
            } else {

            }

            //set action
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    onFilterListener.onCancel();
                }
            });
            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (typeFilter) {
                        case TYPE_FILTER_PRICE:
                            onFilterListener.onApplyPrice(txtPriceFrom.getText().toString(), txtPriceTo.getText().toString());
                            break;
                        case TYPE_FILTER_RATE:
                            onFilterListener.onApplyRate((int) ratingBarMin.getRating(), (int) ratingBarMax.getRating());
                            break;
                        case TYPE_FILTER_CATEGORY:
                            onFilterListener.onApplyCategor(categoryList.get(posCategorySelected).getId());
                            break;
                    }
                    dismiss();
                }
            });
            rangeSeekbarPrice.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
                @Override
                public void valueChanged(java.lang.Number minValue, java.lang.Number maxValue) {
                    txtPriceFrom.setText(Number.formatterPrice.format(minValue));
                    txtPriceTo.setText(Number.formatterPrice.format(maxValue));
                }
            });
            builder.setView(view);
            dialog = builder.create();
        }

        public void show() {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

        public void dismiss() {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        public Build setonFilterListener(onFilterListener onFilterListener) {
            this.onFilterListener = onFilterListener;
            return this;
        }

        @Override
        public void onFilterRate(List<Course> courseArray) {

        }

        @Override
        public void onFilterPrice(List<Course> courseArray) {

        }

        @Override
        public void onFilterCategory(List<Course> courseArray) {

        }

        @Override
        public void onGetListCategory(List<Category> listCategory) {
            for (Category item : listCategory) {
                categoryList.add(item);
                listTitleCategory.add(item.getNameCategory());
            }
            lnButton.setVisibility(View.VISIBLE);
            frCategory.setVisibility(View.VISIBLE);
            frPrice.setVisibility(View.GONE);
            frRate.setVisibility(View.GONE);
            spinnerAdapter = new ArrayAdapter<String>
                    (activity.getBaseContext(), android.R.layout.simple_dropdown_item_1line, listTitleCategory);
            spinnerCategory.setAdapter(spinnerAdapter);
            spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    posCategorySelected = i;
                    ((TextView) spinnerCategory.getSelectedView()).setTextColor(ContextCompat.getColor(activity.getBaseContext(), R.color.color_background_main_blue));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        @Override
        public void onGetItemPage(List<Course> courseArray) {

        }

        @Override
        public void onFilterFail(String mess) {

        }

        public interface onFilterListener {
            void onCancel();

            void onApplyPrice(String from, String to);

            void onApplyRate(int minStar, int maxStar);

            void onApplyCategor(int idCategory);
        }
    }
}
