package com.capton.common.user.signup.model;

import com.capton.common.user.signup.presenter.SignupPresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by capton on 2018/3/21.
 */

public class SignupModel {
    SignupPresenter signupPresenter;

    public SignupModel(SignupPresenter signupPresenter) {
        this.signupPresenter = signupPresenter;
    }

    public void getSignupUser(String apiUrl,String username,String password){
        OkGo.<String>post(apiUrl)
                .params("username",username)
                .params("password",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        signupPresenter.afterSignup(response);
                    }
                });
    }

}
