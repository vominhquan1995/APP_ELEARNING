package com.elearning.elearning.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.CourseAdapter;
import com.elearning.elearning.adapter.HistoryLearnAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.HistoryLearnExam;
import com.elearning.elearning.mvp.presenter.HistoryLearnPresenter;
import com.elearning.elearning.mvp.view.HistoryLearnView;

import java.util.ArrayList;
import java.util.List;

import static com.elearning.elearning.prefs.Constant.NUMBER_COLUMNS_3;

/**
 * Created by MinhQuan on 09/07/2017.
 */

public class HistoryFragment extends BaseFragment implements HistoryLearnView {
    private RecyclerView rvCourseStuding, rvCourseDone;
    private ImageView imgCourse;
    private TextView txtNameCourse;
    private HistoryLearnAdapter adapterCourseStuding, adapterCourseDone;
    private List<HistoryLearnExam> listCourseStuding = new ArrayList<>();
    private List<HistoryLearnExam> listCourseDone = new ArrayList<>();
    private HistoryLearnPresenter historyLearnPresenter;

    @Override
    public void initView() {
        rvCourseStuding = (RecyclerView) view.findViewById(R.id.rvCourseStuding);
        rvCourseStuding.setLayoutManager(new GridLayoutManager(context, NUMBER_COLUMNS_3));
        rvCourseDone = (RecyclerView) view.findViewById(R.id.rvCourseDone);
        rvCourseDone.setLayoutManager(new GridLayoutManager(context, NUMBER_COLUMNS_3));
    }

    @Override
    public void initValue() {
        historyLearnPresenter = new HistoryLearnPresenter(this);
        adapterCourseDone = new HistoryLearnAdapter(context, listCourseDone);
        adapterCourseStuding = new HistoryLearnAdapter(context, listCourseStuding);
        rvCourseDone.setAdapter(adapterCourseDone);
        rvCourseStuding.setAdapter(adapterCourseStuding);
    }

    @Override
    public void initAction() {
        showProgressDialog();
        historyLearnPresenter.getHistory();
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_histoy;
    }

    @Override
    public void onGetHistorySuccess(List<HistoryLearnExam> historyLearnExamList) {
        for (HistoryLearnExam item : historyLearnExamList) {
            if (item.isStatus()) {
                listCourseDone.add(item);
            } else {
                listCourseStuding.add(item);
            }
        }
        dismissProgressDialog();
        adapterCourseDone.notifyDataSetChanged();
        adapterCourseStuding.notifyDataSetChanged();
    }
    @Override
    public void onGetHistoryFail(String message) {
        Log.d("History", message);
    }
}
