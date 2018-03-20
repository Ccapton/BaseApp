package com.capton.common.user.login.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.capton.common.user.login.model.LoginModel;
import com.capton.common.user.login.view.LoginView;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;

/**
 * Created by capton on 2018/03/20.
 */

public class LoginPresenterImpl implements LoginPresenter {

    LoginView loginView ;
    LoginModel loginModel;

    private final int resendTime = 60;
    private int count = resendTime;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel(this);
    }

    @Override
    public void login(String apiUrl,String username, String passsword) {
        loginModel.getLoginedUser(apiUrl,username,passsword);
    }

    @Override
    public void afterLogin(Response<String> response) {
        loginView.afterLogin(response);
    }

    @Override
    public void resetPsw(String apiUrl,String username,String token, String password) {
        loginModel.resetPsw(apiUrl,username,token,password);
    }

    @Override
    public void afterResetPsw(Response<String> response) {
        loginView.afterResetPsw(response);
    }

    private void runTimer(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count--;
                loginView.afterSendCode(count);
                if (count == 0) {
                    count = resendTime;
                    loginView.clickAble(true);
                    cancel();
                }
            }};
        timer.schedule(timerTask,1000,1000);
        loginView.clickAble(false);
    }

    @Override
    public void sendCode(Context context,String username,String template) {
        runTimer();
        BmobSMS.requestSMSCode(context, username, template
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

    @Override
    public void confirmCode(Context context,String username, String code) {
        /*BmobSMS.verifySmsCode(context, username,code, new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if ( e == null){
                            loginView.afterConfirmCode();
                        }else {
                            ToastUtils.showShort(e.toString());
                        }
                    }
                });*/
        loginView.afterConfirmCode();
    }
}
