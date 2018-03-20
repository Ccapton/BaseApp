package com.capton.common.user.login.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/03/20.
 */

public interface LoginPresenter {
    void login(String apiUrl,String username,String passsword);
    void afterLogin(Response<String> response);
    void resetPsw(String apiUrl,String username,String adminPsw,String password);
    void afterResetPsw(Response<String> response);
    void sendCode(Context context,String username,String template);
    void confirmCode(Context context,String username, String code);
}
