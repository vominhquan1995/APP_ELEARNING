package com.elearning.elearning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.NotificationAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Notification;
import com.elearning.elearning.mvp.presenter.NotificationPresenter;
import com.elearning.elearning.mvp.view.NotificationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhQuan on 11/07/2017.
 */

public class NotificationFragment extends BaseFragment implements NotificationView {
    private NotificationPresenter notificationPresenter;
    private List<Notification> notificationList;
    private NotificationAdapter notificationAdapter;
    private RecyclerView rvListNotification;

    @Override
    public void initView() {
        rvListNotification = (RecyclerView) view.findViewById(R.id.rvListNotification);
        rvListNotification.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initValue() {
        notificationPresenter = new NotificationPresenter(this);
        notificationList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(context, notificationList);
        rvListNotification.setAdapter(notificationAdapter);
    }

    @Override
    public void initAction() {
        showProgressDialog();
        notificationPresenter.getNotification();
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_notification;
    }

    @Override
    public void onGetListNotification(List<Notification> notificationList) {
        dismissProgressDialog();
        for (Notification itemNotification : notificationList) {
            this.notificationList.add(itemNotification);
        }
        notificationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetListNotificationFail(String error) {

    }
}
