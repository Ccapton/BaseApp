package com.capton.baseapp;

import android.os.Bundle;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
