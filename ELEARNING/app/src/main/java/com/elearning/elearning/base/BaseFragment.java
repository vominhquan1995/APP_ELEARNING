package com.elearning.elearning.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public abstract int setFragmentView();
}
