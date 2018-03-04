package com.capton.common.user;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.capton.common.R;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.JsonUtil;
import com.capton.common.databinding.LayoutSigninBinding;
import com.capton.ep.EasyPermission;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * Created by capton on 2018/2/15.
 */

public class SignUpActivity extends BaseActivity<LayoutSigninBinding> {

    public final static int SIGNUP_SUCCESS = 0x002;
    public final static String SIGNUPED_DATA = "signuped_data";
    public final static String SIGNUPED_USERNAME = "signuped_username";
    public final static String SIGNUPED_PASSWORD = "signuped_password";


    private boolean clickAble = true;
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
    protected void yourOperation() {

        setShowActionBar(false);

        binding.comfirmCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickAble) {
                    if (checkInput()) {
                        runTimer();
                        codeOperation();
                    }
                }
            }
        });
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.verifySmsCode(getApplicationContext(), binding.userSignin.getText().toString(),
                        binding.comfirmCode.getText().toString(), new VerifySMSCodeListener() {
                            @Override
                            public void done(BmobException e) {
                                if ( e == null){
                                    if (checkInput())
                                       toSignUp();
                                }else {
                                    ToastUtils.showShort(e.toString());
                                }
                            }
                        });
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



    private void runTimer(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count--;
                handler.sendEmptyMessage(count);
                if (count == 0) {
                    count = resendTime;
                    clickAble = true;
                    cancel();
                }
            }};
        timer.schedule(timerTask,1000,1000);
        clickAble = false;
    }

    private void codeOperation(){
        BmobSMS.requestSMSCode(getApplicationContext(), binding.userSignin.getText().toString(), template
                , new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if ( e == null){
                            ToastUtils.showShort("验证码已发送");
                        }else {
                            ToastUtils.showShort(e.toString());
                        }
                    }
                });
    }


    private void toSignUp() {
        String baseUrl = getString(R.string.baseurl);
        String signupUrl = baseUrl+getString(R.string.signup);
        OkGo.<String>post(signupUrl)
                .params("username",binding.userSignin.getText().toString())
                .params("password",binding.pswSignIn.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            ResultBean resultBean = (ResultBean) JsonUtil.strToObject(response.body(), ResultBean.class);
                            showError(resultBean.getCode(),resultBean.getMessage());
                            if (resultBean.getCode() == 0) {
                                afterSignUp(true);
                            } 
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void showError(int code,String word){
        ToastUtils.showShort(word);
    }

    private void afterSignUp(boolean isSignUped) {
        Intent intent = new Intent();
        intent.putExtra(SIGNUPED_DATA,isSignUped);
        intent.putExtra(SIGNUPED_USERNAME,binding.userSignin.getText().toString());
        intent.putExtra(SIGNUPED_PASSWORD,binding.pswSignIn.getText().toString());
        setResult(SIGNUP_SUCCESS,intent);
        finish();
    }
}
