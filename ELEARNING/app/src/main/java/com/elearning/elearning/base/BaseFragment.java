package com.elearning.elearning.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.activity.signin_signup.SignInSignUpActivity;

/**
 * Created by MinhQuan on 22/05/2017.
 */

public abstract class BaseFragment extends Fragment implements Init {
    protected Context context;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setFragmentView(), container, false);
        context = getActivity();
        // init value
        initView();
        initValue();
        initAction();

        return view;

    }

    protected SignInSignUpActivity getSignInSignUpActivity() {
        return ((SignInSignUpActivity) getActivity());
    }

    protected MainActivity getMainActivity() {
        return ((MainActivity) getActivity());
    }

    protected SharedPreferences getSharePreferences() {
        return getMainActivity().sharedPreferences;
    }

    protected void onBackPressed() {
//        SystemHelper.hideKeyboard(context);
        getMainActivity().onBackPressed();
    }

    protected void showProgressDialog() {
        getMainActivity().showProgressDialog();
    }

    protected void dismissProgressDialog() {
        getMainActivity().dismissProgressDialog();
    }

    protected void playSound() {
        getMainActivity().playSound();
    }
    protected void loadAvatar() {
        getMainActivity().loadAvatar();
    }

    public abstract int setFragmentView();
}
