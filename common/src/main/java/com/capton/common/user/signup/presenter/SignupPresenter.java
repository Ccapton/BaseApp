package com.capton.common.user.signup.presenter;

import android.content.Context;

import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/03/21.
 */

public interface SignupPresenter {
    void signup(String apiUrl,String username,String passsword);
    void afterSignup(Response<String> response);
    void sendCode(Context context,String username,String template);
    void confirmCode(Context context, String username, String code);
}
