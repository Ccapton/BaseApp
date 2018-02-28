package com.capton.common.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.capton.common.R;
import com.capton.common.base.BaseFragment;
import com.capton.common.bmob.BmobUtil;
import com.capton.common.databinding.FragmentLoginBinding;
import com.capton.ep.EasyPermission;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by capton on 2018/1/19.
 */

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public String[] getPermissions() {
        return new String[0];
    }

    @Override
    public EasyPermission.OnPermissionListener getPermissionListener() {
        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phone = binding.phone.getText().toString();
                final String psw = binding.psw.getText().toString();
                BmobUtil.login(phone, psw, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        BmobUtil.getCurrentUser();
                        FragmentUtils.remove(LoginFragment.this);
                        ToastUtils.showShort("登录成功");
                        getContext().sendBroadcast(new Intent("登录成功"));
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        ToastUtils.showShort(s+ " " +i);
                    }
                });
            }
        });

    }
}
