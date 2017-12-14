package com.capton.baseapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.OkGoUtil;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

public class MainActivity extends BaseActivity<ActivityMainBinding>{

    SmartRefreshLayout refreshLayout;
    ListView recyclerView;
    String dataList[] =new String[]{"hello","hello","hello","hello","hello","hello","hello","hello"
    ,"hello","hello","hello","hello","hello","hello","hello","hello"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding.refreshlayout.setEnableLoadmore(true);
        binding.refreshlayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                requestData();
            }
        });

        binding.recyclerview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList));


    }

    private void requestData(){
        OkGoUtil.getNews(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                System.out.println(response.body());
                binding.refreshlayout.finishRefresh();
                binding.refreshlayout.finishLoadmore();
            }
        },this,"头条",0,5);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setClickListener() {

    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }
}
