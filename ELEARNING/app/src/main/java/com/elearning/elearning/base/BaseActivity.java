package com.elearning.elearning.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

public abstract class BaseActivity extends AppCompatActivity implements Init {
    private final String TAG = "BaseActivity";
    public SharedPreferences sharedPreferences;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());

        initBaseValue();
        initView();
        initValue();
        initAction();
    }

    private void initBaseValue() {
        context = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        initProgressDialog();
    }

    private void initProgressDialog() {
        ViewGroup layout = (ViewGroup) findViewById(android.R.id.content).getRootView();
    }

    public abstract int getView();
}
