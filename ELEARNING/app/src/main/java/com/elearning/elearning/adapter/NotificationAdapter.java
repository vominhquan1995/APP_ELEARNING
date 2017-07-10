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
import com.elearning.elearning.mvp.model.Notification;
import com.elearning.elearning.network.APIConstant;
import com.elearning.elearning.prefs.DatetimeFomat;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MinhQuan on 01/07/2017.
 */

public class NotificationAdapter extends BaseRecyclerAdapter<Notification> {
    private Context context;
    private OnItemClickListener onItemClickListener;

    public NotificationAdapter(Context context, List<Notification>  notificationList) {
        this.context = context;
        this.items = notificationList;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_notification;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Notification item) {
        NotificationViewHolder courseViewHolder = (NotificationViewHolder) holder;
        Picasso.with(context)
                .load(APIConstant.HOST_NAME_IMAGE + item.getUrlImage())
                .resize(200, 200)
                .into(courseViewHolder.image);
        courseViewHolder.title.setText(item.getTitle());
        courseViewHolder.body.setText(item.getBody());
        courseViewHolder.dateStart.setText(String.format(context.getResources().getString(R.string.cap_notification_start),DatetimeFomat.DATE_FORMAT.format(item.getDateStart())));
        courseViewHolder.dateEnd.setText(String.format(context.getResources().getString(R.string.cap_notification_end),DatetimeFomat.DATE_FORMAT.format(item.getDateEnd())));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
        return new NotificationViewHolder(rootView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private class NotificationViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, body, dateStart,dateEnd;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageNotification);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            body = (TextView) itemView.findViewById(R.id.txtBody);
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
