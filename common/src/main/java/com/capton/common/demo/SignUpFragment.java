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
import com.capton.common.databinding.FragmentSignupBinding;
import com.capton.ep.EasyPermission;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by capton on 2018/1/19.
 */

public class SignUpFragment extends BaseFragment<FragmentSignupBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_signup;
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
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = binding.phone.getText().toString();
                final String psw = binding.psw.getText().toString();
                BmobUtil.signUp(phone, psw, null, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        ToastUtils.showShort("注册成功");
                        getContext().sendBroadcast(new Intent("注册成功"));

                        BmobUtil.login(phone, psw, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                 FragmentUtils.remove(SignUpFragment.this);
                                ToastUtils.showShort("登录成功");
                                getContext().sendBroadcast(new Intent("登录成功"));
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                ToastUtils.showShort(s+ " " +i);
                            }
                        });

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
