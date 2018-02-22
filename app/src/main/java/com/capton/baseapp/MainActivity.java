package com.capton.baseapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;

import net.steamcrafted.loadtoast.LoadToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public String[] getPermissions() {
        requestPermissions = new String[]{PERMISSION_CAMERA};
        return requestPermissions;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void clickMore() {

    }

    LoadToast lt;
    @Override
    public void clickRightText() {
        if (lt == null) {
            lt = new LoadToast(this);
            lt.setText("数据加载中");
            lt.setTextSize(12);
            lt.setBackgroundColor(Color.GRAY);
            lt.setTranslationY(300);
            lt.show();
        }else {
            lt.success();
            lt = null;
        }
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
        setRightText("点击我呀");



    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
