package com.capton.baseapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.FragmentUtils;
import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;


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
    public void setClickListener() {

    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }

     MainFragment mainFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("去注册");
        intentFilter.addAction("去登录");
        intentFilter.addAction("去更新");
        intentFilter.addAction("登录成功");
        intentFilter.addAction("更新成功");

        registerReceiver(broadcastReceiver,intentFilter);

        mainFragment = new MainFragment();
        FragmentUtils.add(getSupportFragmentManager(),mainFragment,R.id.fragmentContainer);


    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("去注册".equals(intent.getAction())){
                setTitle("注册");
            }
            if("去登录".equals(intent.getAction())){
                setTitle("去登录");
            }
            if("去更新".equals(intent.getAction())){
                setTitle("去更新");
            }
            if("登录成功".equals(intent.getAction())||"更新成功".equals(intent.getAction())){
                 setTitle(R.string.app_name);
                 FragmentUtils.hide(getSupportFragmentManager());
                 FragmentUtils.show(mainFragment);
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        FragmentUtils.hide(getSupportFragmentManager());
        FragmentUtils.show(mainFragment);
    }
}
