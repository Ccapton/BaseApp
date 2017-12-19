package com.capton.baseapp;

import android.os.Bundle;

import com.capton.baseapp.databinding.LayoutLeftDrawerBinding;
import com.capton.common.base.BaseDrawerActivity;

public class MainActivity extends BaseDrawerActivity<LayoutLeftDrawerBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        


    }

    @Override
    public int getDrawerLayout() {
        return R.layout.layout_left_drawer;
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
