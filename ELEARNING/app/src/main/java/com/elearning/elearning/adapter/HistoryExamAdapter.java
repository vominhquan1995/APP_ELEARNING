package com.elearning.elearning.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseRecyclerAdapter;
import com.elearning.elearning.mvp.model.HistoryExam;

import java.util.List;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT;

/**
 * Created by MinhQuan on 12/07/2017.
 */

public class HistoryExamAdapter extends BaseRecyclerAdapter<HistoryExam> {
    private Context context;
    private OnItemClickListener onItemClickListener;

    public HistoryExamAdapter(Context context, List<HistoryExam> historyExams, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.items = historyExams;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_exam_history;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, HistoryExam item) {
        HistoryExamAdapter.HistoryExamViewholder historyExamViewholder = (HistoryExamAdapter.HistoryExamViewholder) holder;
        historyExamViewholder.name.setText(item.getNameExam());
        historyExamViewholder.point.setText(String.valueOf(item.getPoint()));
        historyExamViewholder.date.setText(DATE_FORMAT.format(item.getDateExam()));
        historyExamViewholder.status.setText(item.getStatus());
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
//        return new HistoryExamAdapter.HistoryExamViewholder(rootView);
//    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(View view) {
        return new HistoryExamViewholder(view);
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private class HistoryExamViewholder extends RecyclerView.ViewHolder {
        private TextView name, point, date, status;

        public HistoryExamViewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtNameExam);
            point = (TextView) itemView.findViewById(R.id.txtPoint);
            date = (TextView) itemView.findViewById(R.id.txtDate);
            status = (TextView) itemView.findViewById(R.id.txtStatus);
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
