package com.elearning.elearning.mvp.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.elearning.elearning.R;
import com.elearning.elearning.activity.MainActivity;
import com.elearning.elearning.adapter.NavAdapter;
import com.elearning.elearning.fragment.ExamFragment;
import com.elearning.elearning.fragment.HistoryExamFragment;
import com.elearning.elearning.fragment.HistoryFragment;
import com.elearning.elearning.fragment.HomeFragment;
import com.elearning.elearning.fragment.LessonFragment;
import com.elearning.elearning.fragment.ListLessonFragment;
import com.elearning.elearning.fragment.NotificationFragment;
import com.elearning.elearning.fragment.UserInfoFragment;
import com.elearning.elearning.helper.FragmentNavigator;
import com.elearning.elearning.mvp.model.Course;
import com.elearning.elearning.mvp.view.MainView;
import com.elearning.elearning.network.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinhQuan on 30/06/2017.
 */

public class MainPresenter {
    public FragmentNavigator fragmentNavigator;
    private Context context;
    private MainView mainView;
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
                if (fragment instanceof ListLessonFragment) {
                    mainView.updateToolbar(true, false, context.getResources().getString(R.string.menu_listlesson));
                }
            }
        });
        fragmentNavigator.setRootFragment(new HomeFragment());
    }

    public void switchFragment(Fragment fragment) {
        boolean backRoot = false;
        if (fragment instanceof HomeFragment) {
            backRoot = true;
        }
        if (fragment instanceof UserInfoFragment) {
            backRoot = true;
        }
        if (fragment instanceof ListLessonFragment) {
            backRoot = true;
        }
        if (fragment instanceof ExamFragment) {
            backRoot = true;
        }
        if (backRoot) {
            fragmentNavigator.goToRoot();
        }
        fragmentNavigator.goTo(fragment);
    }

    public void goToFragment(String id) {
        if (id.equals(context.getString(R.string.nav_home))) {
            fragmentNavigator.goToRoot();
            mainView.updateToolbar(false, false, id);
        } else if (id.equals(context.getString(R.string.nav_exam))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new ExamFragment());
        } else if (id.equals(context.getString(R.string.nav_history_exam))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new HistoryExamFragment());
        }else if (id.equals(context.getString(R.string.nav_history))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new HistoryFragment());
        } else if (id.equals(context.getString(R.string.nav_notifications))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new NotificationFragment());
        } else if (id.equals(context.getString(R.string.nav_settings))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new UserInfoFragment());
        } else if (id.equals(context.getString(R.string.menu_listlesson))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new ListLessonFragment());
        } else if (id.equals(context.getString(R.string.menu_lesson))) {
            mainView.updateToolbar(true, false, id);
            switchFragment(new LessonFragment());
        } else if (id.equals(context.getString(R.string.nav_logout))) {
            mainView.onLogout();
        }
    }

    public void onBackPressed() {
        if (fragmentNavigator.getActiveFragment() instanceof LessonFragment) {
            mainView.updateToolbar(true, false, context.getString(R.string.menu_listlesson));
            fragmentNavigator.goOneBack();
        } else if (fragmentNavigator.getActiveFragment() instanceof ExamFragment ||
                fragmentNavigator.getActiveFragment() instanceof UserInfoFragment ||
                fragmentNavigator.getActiveFragment() instanceof ListLessonFragment
                || fragmentNavigator.getActiveFragment() instanceof HistoryFragment
                || fragmentNavigator.getActiveFragment() instanceof NotificationFragment
                || fragmentNavigator.getActiveFragment() instanceof HistoryExamFragment) {
            mainView.updateToolbar(false, false, context.getString(R.string.nav_home));
            fragmentNavigator.goToRoot();
            mainView.setItemSelected(context.getString(R.string.nav_home));
        } else {
            mainView.exitApp();
        }
    }

    public NavAdapter.OnItemMenuListener getOnItemMenuListener() {
        return this.onItemMenuListener;
    }

    public void search(String keyword) {
        API.searchListCourse(keyword, new API.OnAPIListener() {
            @Override
            public void onSuccessObject(JSONObject response) throws JSONException {

            }

            @Override
            public void onSuccessArray(JSONArray response) throws JSONException {
                mainView.onSearchResult(Course.getListCourse(response));
            }

            @Override
            public void onString(String response) throws JSONException {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
