package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.FragmentUtils;
import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    AnimationFragment animationFragment;

    @Override
    public String[] getPermissions() {
        return requestPermissions;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }

    List<Fragment> fragmentList=new ArrayList<>();
    List<String> titleList=new ArrayList<>();

      @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          animationFragment = new AnimationFragment();
          AnimationChildFragment animationChildFragment = new AnimationChildFragment();
          Bundle bundle = new Bundle();
          bundle.putInt("type",0);
          animationChildFragment.setArguments(bundle);

          AnimationChildFragment animationChildFragment2 = new AnimationChildFragment();
          Bundle bundle2 = new Bundle();
          bundle2.putInt("type",1);
          animationChildFragment2.setArguments(bundle2);

          fragmentList.add(animationChildFragment);
          fragmentList.add(animationChildFragment2);
          animationFragment.setFragmentList(fragmentList);
          titleList.add("连载");
          titleList.add("完结");
          animationFragment.setFragmentNameList(titleList);

         FragmentUtils.add(getSupportFragmentManager(),animationFragment,R.id.fragmentFrameLayout);

     }

    Map<Integer,Integer> resIdMap;
    List<String> data;
    @Override
    protected void yourOperation() {
        setTitle(R.string.app_name);
        setShowRightText(true);
        setRightText("刷新");



    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
