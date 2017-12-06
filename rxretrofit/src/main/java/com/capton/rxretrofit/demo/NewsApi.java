package com.capton.rxretrofit.demo;

import android.app.Application;
import android.content.Context;

import com.capton.rxretrofit.R;
import com.capton.rxretrofit.api.BaseApi;
import com.capton.rxretrofit.listener.HttpOnNextListener;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 *  这个接口用的是我的极速数据新闻api，演示api。
 *  如果到时请求不到数据了，应该是被前面的人把剩余的请求数消耗完了，请自行找接口测试吧
 *
 * Created by capton on 2017/11/29.
 */

public class NewsApi extends BaseApi {

    private NewsServer server;
    private String appkey;
    public final static String GET_NEWS = "get_news";  // 请求方法 ，每个接口类自由定义，

    /**
     * 演示api，获取新闻数据
     * @param appContext 这里的context是为了获取系统资源，如果不需要context，则你新建BaseApi子类时可以不传context.
     * @param onNextListener 监听回调
     */
    public NewsApi(Context appContext, HttpOnNextListener onNextListener) {
        super(onNextListener);
        if(appContext instanceof Application) {
            appkey = appContext.getResources().getString(R.string.jisu_news_appkey);
            setBaseUrl("http://api.jisuapi.com/news/");        // 设置baseUrl
            server = getRetrofit().create(NewsServer.class);   //  获取Server实例
        }else {
            try {
                throw new Exception("context 不是 Applicaion");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void getNews(String channel,int start,int num){
        setMethod(GET_NEWS);   // 设置方法
        doHttpDeal(server.getNews(channel,start,num,appkey));  // 开始请求
    }

    private interface NewsServer{
        /**
         *  以包裹 String 类型的Observable<String>类型返回，则可以自由地选择解析框架、
         *  很直接地可以看到请求到的数据本体
         * @param channel
         * @param start
         * @param num
         * @param appkey
         * @return
         */
        // channel=头条&start=0&num=10&appkey=yourappkey
        @GET("get")
        Observable<String> getNews(@Query("channel") String channel, @Query("start") int start,
                                   @Query("num") int num, @Query("appkey") String appkey);
    }
}
