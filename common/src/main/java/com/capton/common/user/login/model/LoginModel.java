package com.capton.common.user.login.model;

import com.capton.common.user.login.presenter.LoginPresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/3/20.
 */

public class LoginModel {
    LoginPresenter loginPresenter;
    public LoginModel(LoginPresenter loginPresenter){
        this.loginPresenter = loginPresenter;
    }

    public void getLoginedUser(String apiUrl,String username, String password){
        OkGo.<String>post(apiUrl)
                .params("username",username)
                .params("password",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                          loginPresenter.afterLogin(response);
                    }
                });
    }
    public void resetPsw(String apiUrl,String username,String adminPsw, String password){
        OkGo.<String>post(apiUrl+username)
                .params("adminPsw",adminPsw)
                .params("newPsw",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        loginPresenter.afterResetPsw(response);
                    }
                });
    }


}
