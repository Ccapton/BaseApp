package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.capton.baseapp.databinding.FragmentAnimationChildBinding;
import com.capton.common.base.BaseFragment;
import com.capton.common.base.JsonUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

/**
 * Created by capton on 2018/2/7.
 */

public class AnimationChildFragment extends BaseFragment<FragmentAnimationChildBinding> {

    AnimationAdapter animationAdapter;
    int type = 0;

    int currentPn = 1;
    int topPn=1;
    int bottomPn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_animation_child;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        type = bundle.getInt("type");

        binding.resultRv.setLayoutManager(new GridLayoutManager(getContext(),2));

        initPn();

        if (getDataFromSpf())
            getDataFromBilibili(type,currentPn,true);


        binding.refreshLayout.setEnableLoadmore(true);
        binding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getDataFromBilibili(type,bottomPn+1,true);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getDataFromBilibili(type,topPn-1,false);
            }
        });
    }

    private void initPn() {
        int currentPn1=1;
        int currentPn2=1;
        if(SPUtils.getInstance().getInt(SpConstant.CURRENT_PN1)!=-1)
            currentPn1 = SPUtils.getInstance().getInt(SpConstant.CURRENT_PN1);
        if(SPUtils.getInstance().getInt(SpConstant.CURRENT_PN2)!=-1)
            currentPn2 = SPUtils.getInstance().getInt(SpConstant.CURRENT_PN2);

        if (type == 0){
            currentPn = currentPn1;
        }else {
            currentPn = currentPn2;
        }
        topPn = currentPn;
        bottomPn = currentPn;
        Log.i("initPn", "initPn: currentPn = "+currentPn);
     }

    private int downPn(int pn){
        bottomPn = pn;
        return bottomPn;
    }
    private int upPn(int pn){
        topPn = pn;
        return topPn;
    }


    public void saveToSpf(AnimationBean animationBean,boolean isdown,int pn){
        if(isdown)
            currentPn = downPn(pn);
        else
            currentPn = upPn(pn);

        if (currentPn <= 0)
            currentPn = 1;

        if (type == 0) {
            SPUtils.getInstance().put(SpConstant.ANIMATION_DATA1, JsonUtil.objToString(animationBean));
            SPUtils.getInstance().put(SpConstant.CURRENT_PN1, currentPn);
        } else {
            SPUtils.getInstance().put(SpConstant.ANIMATION_DATA2, JsonUtil.objToString(animationBean));
            SPUtils.getInstance().put(SpConstant.CURRENT_PN2, currentPn);

        }
        Log.i("saveToSpf", "saveToSpf: currentPn = "+currentPn);
    }

    public boolean getDataFromSpf(){
        String dataStr="";
        if (type==0)
          dataStr = SPUtils.getInstance().getString(SpConstant.ANIMATION_DATA1);
        else
          dataStr = SPUtils.getInstance().getString(SpConstant.ANIMATION_DATA2);
        if (TextUtils.isEmpty(dataStr))
            return false;
        AnimationBean animationBean = (AnimationBean) JsonUtil.strToObject
                (dataStr, AnimationBean.class);
        setDataToAdapter(animationBean,true);
        return true;
    }
    private void finishedLoading(){
        if (binding.refreshLayout.isLoading())
        binding.refreshLayout.finishLoadmore();
        if (binding.refreshLayout.isRefreshing())
        binding.refreshLayout.finishRefresh();
    }

    int rid1 = 33;
    int rid2 = 32;
    public void getDataFromBilibili(int type, final int pn, final boolean isDown){
        int rid = rid1;
        if (type == 1)
            rid = rid2;
       String url = "https://api.bilibili.com/x/web-interface/newlist?rid="+rid+"&type=0&pn="+pn+"&ps="+20;
        System.out.println(url);
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                          System.out.println(response.body());
                          AnimationBean bean = (AnimationBean) JsonUtil.strToObject(response.body(),AnimationBean.class);
                          if(bean.getCode()==0) {
                              setDataToAdapter(bean,isDown);
                              saveToSpf(bean,isDown,pn);
                          }else {
                              ToastUtils.showShort(bean.getMessage());
                          }
                        finishedLoading();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        finishedLoading();
                        ToastUtils.showShort("请求出错");
                    }
                });


    }

    private void setDataToAdapter(AnimationBean animationBean,boolean isDown) {
        if(animationBean != null) {
            if (animationAdapter == null) {
                animationAdapter = new AnimationAdapter(getContext(), animationBean.getData().getArchives(), R.layout.item_animation);
                binding.resultRv.setAdapter(animationAdapter);
            } else {
                if (isDown)
                    animationAdapter.getDataList().addAll(animationBean.getData().getArchives());
                else
                    animationAdapter.getDataList().addAll(0, animationBean.getData().getArchives());
                animationAdapter.notifyDataSetChanged();
            }
        }
        else {
            Log.i("setDataToAdapter", "setDataToAdapter: animationBean is null");
        }
    }

}
