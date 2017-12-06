package com.capton.rxretrofit.listener;

/**
 * Created by capton on 2017/11/29.
 */

public interface HttpOnNextListener {
     void onNext(String data, String method);
     void onError(Throwable e);
}
