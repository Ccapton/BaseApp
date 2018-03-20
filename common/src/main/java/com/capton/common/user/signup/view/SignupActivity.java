package com.capton.common.user.signup.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.capton.common.R;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.JsonUtil;
import com.capton.common.databinding.LayoutSigninBinding;
import com.capton.common.user.ResultBean;
import com.capton.common.user.signup.presenter.SignupPresenter;
import com.capton.common.user.signup.presenter.SignupPresenterImpl;
import com.capton.ep.EasyPermission;
import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/03/21.
 */

public class SignupActivity extends BaseActivity <LayoutSigninBinding> implements SignupView {

    SignupPresenter signupPresenter;

    public final static int SIGNUP_SUCCESS = 0x002;
    public final static String SIGNUPED_DATA = "signuped_data";
    public final static String SIGNUPED_USERNAME = "signuped_username";
    public final static String SIGNUPED_PASSWORD = "signuped_password";

    private final String hint = "获取验证码";
    private final int resendTime = 60;
    private int count = resendTime;
    private String template = "易Tv";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != 0)
                binding.comfirmCodeTv.setText(String.valueOf(msg.what)+"s后可重发");
            else
                binding.comfirmCodeTv.setText(hint);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupPresenter = new SignupPresenterImpl(this);
    }

    @Override
    protected void yourOperation() {
        setShowActionBar(false);
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupPresenter.confirmCode(getApplicationContext(),
                        binding.userSignin.getText().toString(),
                        binding.pswSignIn.getText().toString());
            }
        });
        binding.comfirmCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput())
                signupPresenter.sendCode(getApplicationContext(),
                        binding.userSignin.getText().toString(),
                        template);
            }
        });
        binding.closeSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(binding.userSignin.getText().toString())) {
            ToastUtils.showShort("手机号不能为空");
            return false;
        } else {
            if (binding.userSignin.getText().toString().length() != 11) {
                ToastUtils.showShort("请输入正确的手机号");
                return false;
            }
        }
        if (TextUtils.isEmpty(binding.pswSignIn.getText().toString())) {
            ToastUtils.showShort("密码不能为空");
            return false;
        }else {
            if (binding.pswSignIn.getText().toString().length() < 6) {
                ToastUtils.showShort("密码少于6位");
                return false;
            }
        }
        return true;
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
    public int getLayoutId() {
        return R.layout.layout_signin;
    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }

    @Override
    public void afterSignUp(Response<String> response) {
        try {
            ResultBean resultBean = (ResultBean) JsonUtil.strToObject(response.body(), ResultBean.class);
            ToastUtils.showShort(resultBean.getMessage());
            if (resultBean.getCode() == 0) {
                afterSignUp(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void afterSignUp(boolean isSignUped) {
        Intent intent = new Intent();
        intent.putExtra(SIGNUPED_DATA,isSignUped);
        intent.putExtra(SIGNUPED_USERNAME,binding.userSignin.getText().toString());
        intent.putExtra(SIGNUPED_PASSWORD,binding.pswSignIn.getText().toString());
        setResult(SIGNUP_SUCCESS,intent);
        finish();
    }

    @Override
    public void afterSendCode(int count) {
        handler.sendEmptyMessage(count);
    }

    @Override
    public void clickAble(boolean clickable) {
         binding.comfirmCodeTv.setClickable(clickable);
    }

    @Override
    public void afterConfirmCode() {
         String baseUrl = getString(R.string.baseurl);
         String signupUrl = baseUrl + getString(R.string.signup);
         signupPresenter.signup(signupUrl,binding.userSignin.getText().toString(),
                 binding.pswSignIn.getText().toString());
    }
}
