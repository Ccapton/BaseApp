package com.capton.rxretrofit.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by capton on 2017/11/29.
 */

public class RetrofitUtil {

    public static Retrofit getRetrofit(String baseUrl){
        Retrofit.Builder builder =
                new  Retrofit.Builder()
                        .baseUrl(baseUrl)

                         // 配合rxjava、rxandroid来使用，必须设置这个
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                        // 一定要用ScalarsConverterFactory.create()作为解析器，来获取String类型的数据
                        .addConverterFactory(ScalarsConverterFactory.create());
        return builder.build();
    }
}
