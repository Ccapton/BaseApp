package com.capton.common.demo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.FragmentUtils;
import com.capton.common.R;
import com.capton.common.base.BottomNaviActivity;
import com.capton.common.base.ViewPagerFragment;
import com.capton.common.databinding.ActivityDemoBinding;
import com.capton.ep.EasyPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by capton on 2018/1/18.
 */

public class DemoActivity extends BottomNaviActivity<ActivityDemoBinding> {


    ViewPagerFragment viewPagerFragment;
    List<String> stringList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    SignUpFragment signUpFragment;
    LoginFragment loginFragment;
    UpdateFragment updateFragment;
    UpdateFragment updateFragment2;
    UpdateFragment updateFragment3;

    @Override
    protected void yourOperation() {
        setShowActionBar(false);

        initFragment();

    }

    private void initFragment() {
        stringList.add("注册");
        stringList.add("登录");
        stringList.add("更新");
        stringList.add("我的");
        stringList.add("我的");

        signUpFragment = new SignUpFragment();
        loginFragment = new LoginFragment();
        updateFragment = new UpdateFragment();
        updateFragment2 = new UpdateFragment();
        updateFragment3 = new UpdateFragment();

        fragmentList.add(signUpFragment);
        fragmentList.add(loginFragment);
        fragmentList.add(updateFragment);
        fragmentList.add(updateFragment2);
        fragmentList.add(updateFragment3);

        viewPagerFragment = ViewPagerFragment.getInstance();
        viewPagerFragment.setFragmentList(fragmentList);
        viewPagerFragment.setFragmentNameList(stringList);
        viewPagerFragment.setTabLayoutVisible(false);
        FragmentUtils.add(getSupportFragmentManager(),viewPagerFragment, R.id.fragmentlayout);
    }

    @Override
    public String[] getPermissions() {
        return new String[0];
    }

    @Override
    public EasyPermission.OnPermissionListener getPermissionListener() {
        return null;
    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void setBottomMenu() {
        binding.bottomnaviView.inflateMenu(R.menu.menu_demo);
        binding.bottomnaviView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item1)
                    viewPagerFragment.setCurrentFragment(0,false);
                if(item.getItemId() == R.id.item2)
                    viewPagerFragment.setCurrentFragment(1,false);
                if(item.getItemId() == R.id.item3)
                    viewPagerFragment.setCurrentFragment(2,false);
                if(item.getItemId() == R.id.item4)
                    viewPagerFragment.setCurrentFragment(3,false);
                if(item.getItemId() == R.id.item5)
                    viewPagerFragment.setCurrentFragment(4,false);
                return true;
            }
        });
        viewPagerFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Menu menu = binding.bottomnaviView.getMenu();
                binding.bottomnaviView.setSelectedItemId(menu.getItem(position).getItemId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
