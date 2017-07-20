package com.elearning.elearning.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseRecyclerAdapter;
import com.elearning.elearning.mvp.model.HistoryLearnExam;
import com.elearning.elearning.network.APIConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class HistoryLearnAdapter extends BaseRecyclerAdapter<HistoryLearnExam> {
    private Context context;
    private OnItemClickListener onItemClickListener;

    public HistoryLearnAdapter(Context context, List<HistoryLearnExam> historyLearnExamList) {
        this.context = context;
        this.items = historyLearnExamList;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_course_history;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, HistoryLearnExam item) {
        HistoryLearnViewHolder courseViewHolder = (HistoryLearnViewHolder) holder;
        Picasso.with(context)
                .load(APIConstant.HOST_NAME_IMAGE + item.getUrlImage())
                .resize(200, 200)
                .into(courseViewHolder.image);
        courseViewHolder.name.setText(item.getNameCourse());
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
//        return new HistoryLearnViewHolder(rootView);
//    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(View view) {
        return new HistoryLearnViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private class HistoryLearnViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;

        public HistoryLearnViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageCourse);
            name = (TextView) itemView.findViewById(R.id.txtNameCourse);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
