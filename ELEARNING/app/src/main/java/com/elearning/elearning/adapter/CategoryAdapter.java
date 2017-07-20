package com.elearning.elearning.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.mvp.model.Category;
import com.elearning.elearning.network.APIConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vomin on 20/07/2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categoryList;
    private TextView txtTitle;
    private ImageView imageView;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        super(context,R.layout.item_category,categoryList);
        this.context = context;
        this.categoryList = categoryList;
    }
    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Category getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    public View getCustomView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_category, viewGroup, false);
        txtTitle = (TextView) row.findViewById(R.id.txtTitleCategory);
        imageView = (ImageView) row.findViewById(R.id.imgCategory);
        txtTitle.setText(categoryList.get(i).getNameCategory());
        Picasso.with(context)
                .load(APIConstant.HOST_NAME_IMAGE + categoryList.get(i).getUrlImage())
                .resize(350, 240)
                .into(imageView);
        return row;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
