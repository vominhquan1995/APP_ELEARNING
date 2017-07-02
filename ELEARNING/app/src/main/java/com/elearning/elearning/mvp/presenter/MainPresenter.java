package com.elearning.elearning.mvp.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.adapter.NavAdapter;
import com.elearning.elearning.fragment.HomeFragment;
import com.elearning.elearning.fragment.UserInfoFragment;
import com.elearning.elearning.helper.FragmentNavigator;
import com.elearning.elearning.mvp.view.MainView;

/**
 * Created by MinhQuan on 30/06/2017.
 */

public class MainPresenter {
    private Context context;
    private MainView mainView;
    public FragmentNavigator fragmentNavigator;
    private NavAdapter.OnItemMenuListener onItemMenuListener;

    public MainPresenter(final MainView mainView) {
        this.context = (Context) mainView;
        this.mainView = mainView;
        init();
        this.onItemMenuListener = new NavAdapter.OnItemMenuListener() {
            @Override
            public void onItemMenuClick(String id) {
                mainView.onCloseDrawer();
                goToFragment(id);
            }
        };
    }

    void init() {
        fragmentNavigator = new FragmentNavigator(((MainActivity) mainView).getSupportFragmentManager(), R.id.frameContainer);
        fragmentNavigator.setOnStackChanged(new FragmentNavigator.onStackChanged() {
            @Override
            public void onChanged(Fragment fragment) {

            }
        });
        fragmentNavigator.setRootFragment(new HomeFragment());
    }

    public void switchFragment(Fragment fragment) {
        fragmentNavigator.goToRoot();
        fragmentNavigator.goTo(fragment);
    }

    private void goToFragment(String id) {
        if (id.equals(context.getString(R.string.nav_home))) {
            fragmentNavigator.goToRoot();
        } else if (id.equals(context.getString(R.string.nav_settings))) {
            switchFragment(new UserInfoFragment());
        } else if (id.equals(context.getString(R.string.nav_logout))) {
            mainView.onLogout();
        }
    }

    public NavAdapter.OnItemMenuListener getOnItemMenuListener() {
        return this.onItemMenuListener;
    }
}