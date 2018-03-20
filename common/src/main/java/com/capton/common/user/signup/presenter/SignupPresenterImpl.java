package com.capton.common.user.signup.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.capton.common.user.signup.model.SignupModel;
import com.capton.common.user.signup.view.SignupView;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;

/**
 * Created by capton on 2018/03/21.
 */

public class SignupPresenterImpl implements SignupPresenter {

    SignupView signupView ;
    SignupModel signupModel;

    private final int resendTime = 60;
    private int count = resendTime;

    public SignupPresenterImpl(SignupView signupView) {
        this.signupView = signupView;
        signupModel = new SignupModel(this);
    }

    @Override
    public void signup(String apiUrl, String username, String passsword) {
        signupModel.getSignupUser(apiUrl,username,passsword);
    }

    @Override
    public void afterSignup(Response<String> response) {
        signupView.afterSignUp(response);
    }

    private void runTimer(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count--;
                signupView.afterSendCode(count);
                if (count == 0) {
                    count = resendTime;
                    signupView.clickAble(true);
                    cancel();
                }
            }};
        timer.schedule(timerTask,1000,1000);
        signupView.clickAble(false);
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
       /* BmobSMS.verifySmsCode(context, username,code, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if ( e == null){
                    signupView.afterConfirmCode();
                }else {
                    ToastUtils.showShort(e.toString());
                }
            }
        });*/
         signupView.afterConfirmCode();
    }
}
