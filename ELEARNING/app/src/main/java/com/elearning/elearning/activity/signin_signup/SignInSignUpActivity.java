package com.elearning.elearning.activity.signin_signup;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.PageAdapter;
import com.elearning.elearning.base.BaseActivity;

/**
 * Created by MinhQuan on 22/05/2017.
 */

public class SignInSignUpActivity extends BaseActivity {
    private PageAdapter mPageAdapter;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;

    @Override
    public int getView() {
        return R.layout.activity_login_signup_tab;
    }

    @Override
    public void initView() {
        mPageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPage);
        setupViewPager(mViewPager);
        mtabLayout = (TabLayout) findViewById(R.id.tab);
        mtabLayout.setupWithViewPager(mViewPager);
        mtabLayout.getTabAt(0).setIcon(R.drawable.sign_in);
        mtabLayout.getTabAt(1).setIcon(R.drawable.sign_up);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {

    }

    public void setupViewPager(ViewPager viewPager) {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new LoginFragment(), "Login");
        pageAdapter.addFragment(new SignUpFragment(), "Sign Up");
        viewPager.setAdapter(pageAdapter);
    }
}
