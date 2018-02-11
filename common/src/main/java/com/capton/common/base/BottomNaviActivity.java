package com.capton.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.capton.common.R;
import com.capton.common.databinding.ActivityBottomNaviBinding;

/**
 * Created by capton on 2018/1/31.
 */

public abstract class BottomNaviActivity <T extends ViewDataBinding> extends BaseActivity <ActivityBottomNaviBinding>{

    public T childBinding;

    @Override
    protected abstract void yourOperation();

    @Override
    public abstract String[] getPermissions();

    @Override
    public  int getLayoutId(){
        return R.layout.activity_bottom_navi;
    };

    @Override
    public abstract void clickMore();

    @Override
    public abstract void clickRightText();

    public abstract int getContentLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getContentLayoutId(),binding.fragmentContainer,true);

        setBottomMenu();
        setShowDivider(true);
        setBottomHeight(46);


    }

    public void setBottomHeight(int heightDp){
        binding.bottomnaviView.getLayoutParams().height = ConvertUtils.dp2px(heightDp);
    }

    public abstract void setBottomMenu();


    public void setShowDivider(boolean showDivider){
        if(showDivider)
           binding.bottomDivider.setVisibility(View.VISIBLE);
        else
           binding.bottomDivider.setVisibility(View.GONE);
    }
    public View getBottomDivider(){
        return binding.bottomDivider;
    }
    public void setBottomDividerBgResource(int colorResId){
        binding.bottomDivider.setBackgroundResource(colorResId);
    }
    public void setBottomDividerColor(int color){
        binding.bottomDivider.setBackgroundColor(color);
    }
}
