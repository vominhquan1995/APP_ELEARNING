package com.elearning.elearning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.HistoryExamAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.HistoryExam;
import com.elearning.elearning.mvp.presenter.HistoryExamPresenter;
import com.elearning.elearning.mvp.view.HistoryExamView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhQuan on 11/07/2017.
 */

public class HistoryExamFragment extends BaseFragment implements HistoryExamView {
    private HistoryExamPresenter historyExamPresenter;
    private HistoryExamAdapter adapterPass, adapterFail;
    private List<HistoryExam> listExamPass = new ArrayList<>();
    private List<HistoryExam> listExamFail = new ArrayList<>();
    private RecyclerView rvPass, rvFail;
    private TextView txtTitlePass,txtTitleFail;
    private static HistoryExamFragment.onSendLessonID onSendLessonID;
    @Override
    public void initView() {
        txtTitlePass=(TextView) view.findViewById(R.id.txtTitle1);
        txtTitleFail=(TextView) view.findViewById(R.id.txtTitle2);
        txtTitlePass.setText(context.getResources().getString(R.string.cap_exam_pass));
        txtTitleFail.setText(context.getResources().getString(R.string.cap_exam_fail));
        rvPass = (RecyclerView) view.findViewById(R.id.rv1);
        rvPass.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvFail = (RecyclerView) view.findViewById(R.id.rv2);
        rvFail.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initValue() {
        historyExamPresenter = new HistoryExamPresenter(this);
        listExamPass = new ArrayList<>();
        listExamFail = new ArrayList<>();
        adapterPass = new HistoryExamAdapter(context, listExamPass, new HistoryExamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_lesson));
                onSendLessonID.onSend(listExamPass.get(position).getIdLesson());
                Log.d("Send lesson id", String.valueOf(listExamPass.get(position).getIdLesson()));
            }
        });
        adapterFail = new HistoryExamAdapter(context, listExamFail, new HistoryExamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getMainActivity().gotoFragment(getResources().getString(R.string.menu_lesson));
                onSendLessonID.onSend(listExamFail.get(position).getIdLesson());
                Log.d("History exam", String.valueOf(position));
            }
        });
        rvPass.setAdapter(adapterPass);
        rvFail.setAdapter(adapterFail);

    }

    @Override
    public void initAction() {
        showProgressDialog();
        historyExamPresenter.getListHistoryExam();
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_histoy;
    }

    @Override
    public void onGetListHistory(List<HistoryExam> historyExamList) {
        for (HistoryExam item : historyExamList) {
            if (item.getStatus().equals("Hoàn Thành")) {
                listExamPass.add(item);
            } else {
                listExamFail.add(item);
            }
        }
        adapterPass.notifyDataSetChanged();
        adapterFail.notifyDataSetChanged();
        dismissProgressDialog();
    }

    @Override
    public void onGetListHistoryFail(String mess) {

    }
    public static void setSendIdLesson(HistoryExamFragment.onSendLessonID onSendLessonID) {
        HistoryExamFragment.onSendLessonID = onSendLessonID;
    }
    public interface onSendLessonID {
        void onSend(int LessonId);
    }
}
