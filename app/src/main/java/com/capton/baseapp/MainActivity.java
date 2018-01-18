package com.capton.baseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.SnackBarUtil;


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

    Snackbar snackbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);

         binding.test.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 snackbar = SnackBarUtil.showActionIndefinite(binding.test, "测试SnackBar","OK",new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         snackbar.dismiss();
                         startActivity(new Intent(MainActivity.this,DemoActivity.class));
                     }
                 });

              }
         });


    }
}
