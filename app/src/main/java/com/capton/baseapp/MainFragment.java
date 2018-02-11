package com.capton.baseapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.FragmentUtils;
import com.capton.baseapp.databinding.FragmentMainBinding;
import com.capton.common.base.BaseFragment;
import com.capton.common.bmob.BmobUtil;
import com.capton.common.demo.LoginFragment;
import com.capton.common.demo.SignUpFragment;
import com.capton.common.demo.UpdateFragment;

/**
 * Created by capton on 2018/1/19.
 */

public class MainFragment extends BaseFragment <FragmentMainBinding>{
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("登录成功");

        getContext().registerReceiver(broadcastReceiver,intentFilter);

        if(BmobUtil.getCurrentUser() != null) {
            binding.currentUser.setVisibility(View.VISIBLE);
            binding.userPhone.setText(BmobUtil.getCurrentUser().getUsername());
            binding.logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BmobUtil.logout();
                    binding.currentUser.setVisibility(View.GONE);
                }
            });
        }

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().sendBroadcast(new Intent("去注册"));
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentUtils.add(getFragmentManager(),signUpFragment,R.id.container);
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().sendBroadcast(new Intent("去登录"));
                LoginFragment loginFragment = new LoginFragment();
                FragmentUtils.add(getFragmentManager(),loginFragment,R.id.container);
            }
        });
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().sendBroadcast(new Intent("去更新"));
                UpdateFragment updateFragment = new UpdateFragment();
                FragmentUtils.add(getFragmentManager(),updateFragment,R.id.container);
            }
        });
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
              if("登录成功".equals(intent.getAction())) {
                  binding.currentUser.setVisibility(View.VISIBLE);
                  binding.userPhone.setText(BmobUtil.getCurrentUser().getUsername());
              }
        }
    };

    @Override
    public void onDestroy() {
        getContext().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }


}
