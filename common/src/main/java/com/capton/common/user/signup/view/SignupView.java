package com.capton.common.user.signup.view;

import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/03/21.
 */
public interface SignupView {
    void afterSignUp(Response<String> response);
    void afterSendCode(int count);
    void clickAble(boolean clickable);
    void afterConfirmCode();
}
