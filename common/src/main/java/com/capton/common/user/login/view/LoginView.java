package com.capton.common.user.login.view;

import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/03/20.
 */
public interface LoginView {
    void afterSendCode(int count);
    void clickAble(boolean clickable);
    void afterConfirmCode();
    void afterLogin(Response<String> response);
    void afterResetPsw(Response<String> response);
}
