package com.capton.rxretrofit.api;


import com.capton.rxretrofit.listener.HttpOnNextListener;
import com.capton.rxretrofit.retrofit.RetrofitUtil;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by capton on 2017/11/29.
 */

public class BaseApi {

    private Retrofit retrofit;
    protected String baseUrl = "";
    private String method= "";


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public HttpOnNextListener onNextListener;

    public BaseApi(HttpOnNextListener onNextListener) {
        this.onNextListener = onNextListener;
    }


    public Retrofit getRetrofit(){
        this.retrofit = RetrofitUtil.getRetrofit(baseUrl);
        return this.retrofit;
    }

    public void doHttpDeal(final Observable<String> observable){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String value) {
                        if(onNextListener!=null)
                            onNextListener.onNext(value,method);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(onNextListener!=null)
                            onNextListener.onError(e);
                    }

                });
    }



}
