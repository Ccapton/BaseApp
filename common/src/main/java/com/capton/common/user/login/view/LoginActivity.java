package com.capton.common.user.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.capton.common.R;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.JsonUtil;
import com.capton.common.base.SpConstant;
import com.capton.common.databinding.LayoutLoginBinding;
import com.capton.common.user.ResultBean;
import com.capton.common.user.User;
import com.capton.common.user.login.presenter.LoginPresenter;
import com.capton.common.user.login.presenter.LoginPresenterImpl;
import com.capton.common.user.signup.view.SignupActivity;
import com.capton.ep.EasyPermission;
import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/03/20.
 */

public class LoginActivity extends BaseActivity <LayoutLoginBinding> implements LoginView {

    LoginPresenter loginPresenter;

    private boolean isForgotPsw;
    private String template = "易Tv";


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != 0)
                binding.comfirmCodeTvforgot.setText(String.valueOf(msg.what)+"s后可重发");
            else
                binding.comfirmCodeTvforgot.setText("获取验证码");
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowActionBar(false);
        loginPresenter = new LoginPresenterImpl(this);
    }

    public final static int SIGNUP_REQUEST = 0x002;
    @Override
    protected void yourOperation() {
        binding.loginBtn.setOnClickListener(new LoginClickListener());
        binding.comfirmCodeTvforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput())
                    loginPresenter.sendCode(getApplicationContext(),
                        binding.user.getText().toString(),
                        template);
            }
        });

        binding.forgotPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotPswViews();
            }
        });
        binding.signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                intent.putExtra(SignupActivity.SIGNUPED_DATA,SIGNUP_REQUEST);
                startActivityForResult(intent,SIGNUP_REQUEST);
            }
        });
        binding.closeLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        return R.layout.layout_login;
    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }

    @Override
    public void afterSendCode(int count) {
        handler.sendEmptyMessage(count);
    }

    @Override
    public void  clickAble(boolean clickable) {
        binding.comfirmCodeTvforgot.setClickable(clickable);
    }

    @Override
    public void afterConfirmCode() {
        if (checkInput()) {
            String baseUrl = getString(R.string.baseurl);
            String resetUrl = baseUrl+getString(R.string.resetPsw);
            loginPresenter.resetPsw(resetUrl,binding.user.getText().toString(),"chen3723910",binding.psw.getText().toString());
        }
    }

    @Override
    public void afterLogin(Response<String> response) {
        try {
            ResultBean resultBean = (ResultBean) JsonUtil.strToObject(response.body(), ResultBean.class);
            ToastUtils.showShort(resultBean.getMessage());
            if (resultBean.getCode() ==0){
                SPUtils.getInstance().put(SpConstant.USER,JsonUtil.objToString(resultBean.getData()));
                afterLoginSuccess(true,resultBean.getData());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void afterResetPsw(Response<String> response) {
        try {
            ResultBean resultBean = (ResultBean) JsonUtil.strToObject(response.body(), ResultBean.class);
            ToastUtils.showShort(resultBean.getMessage());
            if (resultBean.getCode() ==0){
                SPUtils.getInstance().put(SpConstant.USER,JsonUtil.objToString(resultBean.getData()));
                afterLoginSuccess(false,resultBean.getData());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public final static int LOGIN_SUCCESS = 0x001;
    public final static String LOGINED_DATA = "logined_data";

    private void afterLoginSuccess(boolean islogined,User user) {
        Intent intent = new Intent();
        intent.putExtra(LOGINED_DATA, islogined);
        intent.putExtra(SpConstant.USER, user);
        setResult(LOGIN_SUCCESS,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (isForgotPsw) {
            hideForgotPswViews();
        } else {
            super.onBackPressed();
        }
    }

    private void hideForgotPswViews(){
        binding.title.setText("登录");
        binding.loginBtn.setText("登录");
        binding.forgotPswLayout.setVisibility(View.GONE);
        binding.forgotPsw.setVisibility(View.VISIBLE);
        binding.loginBtn.setOnClickListener(new LoginClickListener());
        isForgotPsw = false;
    }

    private void showForgotPswViews() {
        binding.title.setText("重置密码");
        binding.loginBtn.setText("重置密码");
        binding.forgotPswLayout.setVisibility(View.VISIBLE);
        binding.forgotPsw.setVisibility(View.GONE);
        binding.loginBtn.setOnClickListener(new ResetPswClickListener());
        isForgotPsw = true;
    }
    private boolean checkInput() {
        if (TextUtils.isEmpty(binding.user.getText().toString())) {
            ToastUtils.showShort("手机号不能为空");
            return false;
        } else {
            if (binding.user.getText().toString().length() != 11) {
                ToastUtils.showShort("请输入正确的手机号");
                return false;
            }
        }
        if (TextUtils.isEmpty(binding.psw.getText().toString())) {
            ToastUtils.showShort("密码不能为空");
            return false;
        }else {
            if (binding.psw.getText().toString().length() < 6) {
                ToastUtils.showShort("密码少于6位");
                return false;
            }
        }
        return true;
    }

    class LoginClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String baseUrl = getString(R.string.baseurl);
            String loginUrl = baseUrl+getString(R.string.login);
            loginPresenter.login(loginUrl,
                    binding.user.getText().toString(),
                    binding.psw.getText().toString());
        }
    }

    class ResetPswClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            loginPresenter.confirmCode(getApplicationContext(),binding.user.getText().toString(),binding.comfirmCodeForgot.getText().toString());
        }
    }
}
