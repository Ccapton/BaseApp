package com.capton.common.user;

import android.content.Intent;
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

import static com.capton.common.user.SignUpActivity.SIGNUPED_PASSWORD;
import static com.capton.common.user.SignUpActivity.SIGNUPED_USERNAME;

/**
 * Created by capton on 2018/2/15.
 */

public class LoginActivity extends BaseActivity<LayoutLoginBinding> {

    public final static int SIGNUP_REQUEST = 0x002;

    public final static int LOGIN_SUCCESS = 0x001;
    public final static String LOGINED_DATA = "logined_data";

    private boolean isForgotPsw;

    private boolean clickAble = true;
    private final String hint = "获取验证码";
    private String template = "易Tv重置密码";
    private final int resendTime = 60;
    private int count = resendTime;

    public  static int USERNAME_OR_PSW_NONE = 100;
    public  static int USERNAME_HASBEAN_TOKEN =102 ;
    public  static int USERNAME_OR_PSW_WRONG =103;

    /*
    * # 100 :手机号或密码不能为空
# 101 :手机号格式错误
# 102 :该手机号已注册
# 103 :手机号或密码错误
# 103 :密码不少于 MIN_PSW_LENGTH 位
# 200 :该用户不存在
# 201 :token不正确
# 300 :用户不存在
# 301 :标题或内容为空
# 302 :标题长度不能小于 MIN_ARTICLE_TITLE_LENGTH
# 303 :内容长度不能小于 MIN_ARTICLE_CONTENT_LENGTH
# 401 :图片不能为空
# 500 :请求方法错误
    * */

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != 0)
                binding.comfirmCodeTvforgot.setText(String.valueOf(msg.what)+"s后可重发");
            else
                binding.comfirmCodeTvforgot.setText(hint);
        }
    };


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

    @Override
    protected void yourOperation() {

        setShowActionBar(false);
        readyLogin();
        readyToSignUp();
        readyToResetPsw();

        binding.closeLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.comfirmCodeTvforgot.setOnClickListener(new View.OnClickListener() {
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
    }
    private void codeOperation(){
        BmobSMS.requestSMSCode(getApplicationContext(), binding.user.getText().toString(), template
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

    private void readyToResetPsw() {
        binding.forgotPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotPswViews();
                binding.loginBtn.setOnClickListener(new ResetPswClickListener());
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

    public void readyLogin(){
        binding.loginBtn.setOnClickListener(new LoginClickListener());
    }

    private void showError(int code,String word){
                ToastUtils.showShort(word);
    }

    private void afterLoginSuccess(boolean islogined,User user) {
         Intent intent = new Intent();
        intent.putExtra(LOGINED_DATA, islogined);
        intent.putExtra(SpConstant.USER, user);
        setResult(LOGIN_SUCCESS,intent);
        finish();
    }


    private void readyToSignUp() {
        binding.signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                intent.putExtra(SignUpActivity.SIGNUPED_DATA,SIGNUP_REQUEST);
                startActivityForResult(intent,SIGNUP_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGNUP_REQUEST && resultCode == SignUpActivity.SIGNUP_SUCCESS){
            boolean signUped = data.getBooleanExtra(SignUpActivity.SIGNUPED_DATA,false);
            String username = data.getStringExtra(SIGNUPED_USERNAME);
            String password = data.getStringExtra(SIGNUPED_PASSWORD);
            if(signUped){
                String baseUrl = getString(R.string.baseurl);
                String loginUrl = baseUrl+getString(R.string.login);
                OkGo.<String>post(loginUrl)
                        .params("username",username)
                        .params("password",password)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                               handleResult(response);
                            }
                        });
            }
        }
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

    class LoginClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            tologin();
        }
    }
    private void tologin(){
        String baseUrl = getString(R.string.baseurl);
        String loginUrl = baseUrl+getString(R.string.login);
        OkGo.<String>post(loginUrl)
                .params("username",binding.user.getText().toString())
                .params("password",binding.psw.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                       handleResult(response);
                    }
                });
    }

    private void handleResult(Response<String> response) {
        try {
            ResultBean resultBean = (ResultBean) JsonUtil.strToObject(response.body(), ResultBean.class);
            SPUtils.getInstance().put(SpConstant.USER,JsonUtil.objToString(resultBean.getData()));
            showError(resultBean.getCode(),resultBean.getMessage());
            if (resultBean.getCode() ==0){
                afterLoginSuccess(true, (User) resultBean.getData());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ResetPswClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            BmobSMS.verifySmsCode(getApplicationContext(), binding.user.getText().toString(),
                    binding.comfirmCodeForgot.getText().toString(), new VerifySMSCodeListener() {
                        @Override
                        public void done(BmobException e) {
                            if ( e == null){
                                if (checkInput())
                                     resetPsw();
                            }else {
                                ToastUtils.showShort(e.toString());
                            }
                        }
                    });

        }
    }

    private void resetPsw() {

    }
}
