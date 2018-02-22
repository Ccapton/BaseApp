package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;


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


    @Override
    public void clickRightText() {
        binding.animationView.setAnimation("atm.json");
        binding.animationView.loop(true);
        binding.animationView.playAnimation();
    }
      @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


     }

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
