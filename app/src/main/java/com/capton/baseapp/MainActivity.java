package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity<ActivityMainBinding> {



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
