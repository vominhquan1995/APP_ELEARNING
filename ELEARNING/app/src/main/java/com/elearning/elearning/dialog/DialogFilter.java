package com.elearning.elearning.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.elearning.elearning.R;
import com.elearning.elearning.prefs.Number;

import java.text.DecimalFormat;

import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_PRICE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_RATE;
import static com.elearning.elearning.prefs.Constant.TYPE_FILTER_SALE;

/**
 * Created by MinhQuan on 19/07/2017.
 */

public class DialogFilter {
    public static class Build {
        private AlertDialog.Builder builder;
        private AlertDialog dialog;
        private TextView txtPriceFrom, txtPriceTo;
        private Button btnCancel,btnApply;
        private CrystalRangeSeekbar rangeSeekbarPrice;
        private RatingBar ratingBarMin, ratingBarMax;
        private onFilterListener onFilterListener;
        private FrameLayout frPrice,frRate;
        private String typeFilter;

        public Build(Activity activity, final String typeFilter) {
            this.typeFilter=typeFilter;
            builder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_filter, null);
            //get view
            btnCancel=(Button) view.findViewById(R.id.btnCancel);
            btnApply=(Button) view.findViewById(R.id.btnApply);
            txtPriceFrom = (TextView) view.findViewById(R.id.txtPriceFrom);
            txtPriceTo = (TextView) view.findViewById(R.id.txtPriceTo);
            rangeSeekbarPrice = (CrystalRangeSeekbar) view.findViewById(R.id.rangeSeekbarPrice);
            frPrice=(FrameLayout) view.findViewById(R.id.frPrice);
            frRate=(FrameLayout) view.findViewById(R.id.frRate);
            ratingBarMin=(RatingBar) view.findViewById(R.id.rtMin);
            ratingBarMax=(RatingBar) view.findViewById(R.id.rtMax);
            //set type filter
            if(typeFilter==TYPE_FILTER_PRICE){
                frPrice.setVisibility(View.VISIBLE);
                frRate.setVisibility(View.GONE);
            }else if(typeFilter==TYPE_FILTER_RATE){
                frPrice.setVisibility(View.GONE);
                frRate.setVisibility(View.VISIBLE);
            }else if(typeFilter==TYPE_FILTER_SALE){

            }else{

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
                    switch (typeFilter){
                        case TYPE_FILTER_PRICE:
                            onFilterListener.onApplyPrice(txtPriceFrom.getText().toString(),txtPriceTo.getText().toString());
                            break;
                        case TYPE_FILTER_RATE:
                            onFilterListener.onApplyRate((int)ratingBarMin.getRating(),(int)ratingBarMax.getRating());
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
        public  interface  onFilterListener{
            void onCancel();

            void onApplyPrice(String from,String to);

            void onApplyRate(int minStar, int maxStar);
        }
    }
}
