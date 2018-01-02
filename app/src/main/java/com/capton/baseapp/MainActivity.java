package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.DialogUtil;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("capton");
        textView.setHeight(300);


       final AlertDialog dialog = DialogUtil.showCustomDialog(this,
               textView,
               R.drawable.dialog_demo,
               600,
               false);
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
           }
       });
    }
}
