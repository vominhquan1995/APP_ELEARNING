package com.elearning.elearning.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseFragment;

/**
 * Created by MinhQuan on 17/07/2017.
 */

public class AboutFragment extends BaseFragment {
    private TextView txtVersion;
    @Override
    public void initView() {
        txtVersion=(TextView) view.findViewById(R.id.txtVersion);
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            txtVersion.setText(String.format(getResources().getString(R.string.about_cap_version),pInfo.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {

    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_about;
    }
}
