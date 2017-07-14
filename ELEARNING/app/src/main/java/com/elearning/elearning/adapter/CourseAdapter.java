package com.elearning.elearning.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseRecyclerAdapter;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.network.APIConstant;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT;
import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class CourseAdapter extends BaseRecyclerAdapter<Course> {
    private Context context;
    private int layout;
    private OnItemClickListener onItemClickListener;

    public CourseAdapter(Context context, List<Course> listCourse, int layout) {
        this.context = context;
        this.items = listCourse;
        this.layout = layout;
    }

//    public void setLayout(int layout) {
//        this.layout = layout;
//    }

    @Override
    protected int getItemLayout() {
        return layout;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Course item) {
        CourseAdapter.CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
        Picasso.with(context)
                .load(APIConstant.HOST_NAME_IMAGE + item.getUrlImage())
                .resize(400, 240)
                .into(courseViewHolder.image);
//        courseViewHolder.image.setBackground(context.getResources().getDrawable(R.drawable.default_course));
        courseViewHolder.name.setText(item.getNameCourses());
        courseViewHolder.credits.setText(String.format(context.getResources().getString(R.string.course_creadits), String.valueOf(item.getNumberCredits())));
        courseViewHolder.donor.setText(String.format(context.getResources().getString(R.string.course_donor), item.getDonors()));
        courseViewHolder.dateStart.setText(String.format(context.getResources().getString(R.string.course_date_start), DATE_FORMAT.format(item.getDateStart())));
        courseViewHolder.dateEnd.setText(String.format(context.getResources().getString(R.string.course_date_end), DATE_FORMAT.format(item.getDateEnd())));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
        return new CourseViewHolder(rootView);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private class CourseViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView credits;
        private TextView donor;
        private TextView dateStart;
        private TextView dateEnd;

        public CourseViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageCourse);
            name = (TextView) itemView.findViewById(R.id.txtNameCourse);
            donor = (TextView) itemView.findViewById(R.id.txtDonor);
            credits = (TextView) itemView.findViewById(R.id.txtCredits);
            dateStart = (TextView) itemView.findViewById(R.id.txtDateStart);
            dateEnd = (TextView) itemView.findViewById(R.id.txtDateEnd);
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
