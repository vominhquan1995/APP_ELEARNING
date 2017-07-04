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
import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.network.APIConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class LessonAdapter extends BaseRecyclerAdapter<Lesson> {
    private Context context;
    private int layout;
    private OnItemClickListener onItemClickListener;

    public LessonAdapter(Context context, List<Lesson> listLesson, int layout) {
        this.context = context;
        this.items = listLesson;
        this.layout = layout;
    }

    @Override
    protected int getItemLayout() {
        return layout;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Lesson item) {
        LessonAdapter.LessonViewHolder LessonViewHolder = (LessonViewHolder) holder;
        Picasso.with(context)
                .load(APIConstant.HOST_NAME_IMAGE + item.getUrlImage())
                .resize(200, 200)
                .into(LessonViewHolder.image);
        LessonViewHolder.name.setText(item.getName());
        LessonViewHolder.typeLesson.setText(String.format(context.getResources().getString(R.string.lesson_source_type), String.valueOf(item.getSourseType())));
        LessonViewHolder.time.setText(String.format(context.getResources().getString(R.string.lesson_time), String.valueOf(item.getTime())));
        LessonViewHolder.description.setText(String.format(context.getResources().getString(R.string.lesson_description), item.getDescription()));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
        return new LessonViewHolder(rootView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private class LessonViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView time;
        private TextView typeLesson;
        private TextView description;

        public LessonViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageLesson);
            name = (TextView) itemView.findViewById(R.id.txtNameLesson);
            time = (TextView) itemView.findViewById(R.id.txtTime);
            typeLesson = (TextView) itemView.findViewById(R.id.txtTypeLesson);
            description = (TextView) itemView.findViewById(R.id.txtDescription);
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
