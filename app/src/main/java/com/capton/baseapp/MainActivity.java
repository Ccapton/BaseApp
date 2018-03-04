package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;
import com.capton.common.user.LoginActivity;
import com.capton.ep.EasyPermission;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public String[] getPermissions() {
        return new String[]{PERMISSION_CAMERA};
    }

    @Override
    public EasyPermission.OnPermissionListener getPermissionListener() {
        return new EasyPermission.OnPermissionListener() {
            @Override
            public void onPermissionDenied(String s) {
                if (s.equals(PERMISSION_CAMERA)){

                }else if(s.equals(PERMISSION_CALL_PHONE)){

                }
            }

            @Override
            public void onPermissionGranted(String s) {

            }
        };
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void clickMore() {

    }


    @Override
    public void clickRightText() {
        ActivityUtils.startActivity(LoginActivity.class);
    }
      @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          EventBus.getDefault().register(this);

     }

     DemoAdapter adapter;
     List<String> picList= new ArrayList<>();
    List<Integer> randomList = new ArrayList<>();
     String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1519395850442&di=942b93483279c791ffdb3019d65d2484&imgtype=0&src=http%3A%2F%2Fdynamic-image.yesky.com%2F740x-%2FuploadImages%2F2015%2F131%2F33%2FD472XQ25C7H2.jpg";
    @Override
    protected void yourOperation() {
        setTitle(R.string.app_name);
        setShowRightText(true);
        setRightText("点击我呀");

        for (int i = 0; i < 23; i++) {
            picList.add(url);
            int random = (int) (ConvertUtils.dp2px(200)*Math.random());
            if (random <ConvertUtils.dp2px(100))
                random = ConvertUtils.dp2px(100);
            randomList.add(random);
        }

        adapter = new DemoAdapter(this,picList,R.layout.item_waterfall);
        adapter.notifyDataSetChanged();
        adapter.setRandomHeightList(randomList);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.rv.setLayoutManager(layoutManager);
        binding.rv.setAdapter(adapter);

        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.refresh.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(adapter!=null) {
                    for (int i = 0; i < 15; i++) {
                        adapter.add(url);
                        int random = (int) (ConvertUtils.dp2px(200)*Math.random());
                        if (random < ConvertUtils.dp2px(100))
                            random = ConvertUtils.dp2px(100);
                        adapter.getRandomHeightList().add(random);
                     }
                    binding.refresh.finishLoadmore(300);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                     EventBus.getDefault().post(true,"sleep");
            }
        });

    }


    @Subscriber(tag = "sleep",mode = ThreadMode.ASYNC)
    public void sleep(boolean sleep){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EventBus.getDefault().post(true,"refresh");
    }
    @Subscriber(tag = "refresh",mode = ThreadMode.MAIN)
    public void refresh(boolean refresh){
        if(adapter!=null){
            adapter.getDataList().clear();
            adapter.getRandomHeightList().clear();
            for (int i = 0; i < 15; i++) {
                adapter.getDataList().add(url);
                int random = (int) (ConvertUtils.dp2px(200)*Math.random());
                if (random < ConvertUtils.dp2px(100))
                    random = ConvertUtils.dp2px(100);
                adapter.getRandomHeightList().add(random);
            }
            adapter.notifyDataSetChanged();
            binding.refresh.finishRefresh();
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
