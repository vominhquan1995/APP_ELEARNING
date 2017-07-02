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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class CourseAdapter extends BaseRecyclerAdapter<Course> {
    private Context context;

    public CourseAdapter(Context context, List<Course> listCourse) {
        this.context = context;
        this.items = listCourse;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_course;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Course item) {
        CourseAdapter.CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
//        Picasso.with(context).load(APIConstant.HOST_NAME_IMAGE + item.getUrlImage()).into(courseViewHolder.image);
        courseViewHolder.image.setBackground(context.getResources().getDrawable(R.drawable.default_course));
        courseViewHolder.name.setText(item.getNameCourses());
        courseViewHolder.credits.setText(String.valueOf(item.getNumberCredits()));
        courseViewHolder.donor.setText(item.getDonors());
        courseViewHolder.dateStart.setText(DATE_FORMAT_YYYYMMDD.format(item.getDateStart()));
        courseViewHolder.dateEnd.setText(DATE_FORMAT_YYYYMMDD.format(item.getDateEnd()));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
        return new CourseViewHolder(rootView);
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
            credits = (TextView) itemView.findViewById(R.id.txtDonor);
            donor = (TextView) itemView.findViewById(R.id.txtCredits);
            dateStart = (TextView) itemView.findViewById(R.id.txtDateStart);
            dateEnd = (TextView) itemView.findViewById(R.id.txtDateEnd);
        }
    }

    /**
     * Model course item
     */
    public class ItemCourse {
        //Image Course
        private Drawable image;
        //Name Course
        private String name;
        //Number Credits
        private int Credits;
        //Donor
        private String donor;
        //Date start
        private String dateStart;
        //Date end
        private String dateEnd;

        public ItemCourse(Drawable image, String name, int credits, String donor, String dateStart, String dateEnd) {
            this.image = image;
            this.name = name;
            Credits = credits;
            this.donor = donor;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
        }

        public Drawable getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public int getCredits() {
            return Credits;
        }

        public String getDonor() {
            return donor;
        }

        public String getDateStart() {
            return dateStart;
        }

        public String getDateEnd() {
            return dateEnd;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCredits(int credits) {
            Credits = credits;
        }

        public void setDonor(String donor) {
            this.donor = donor;
        }

        public void setDateStart(String dateStart) {
            this.dateStart = dateStart;
        }

        public void setDateEnd(String dateEnd) {
            this.dateEnd = dateEnd;
        }
    }
}
