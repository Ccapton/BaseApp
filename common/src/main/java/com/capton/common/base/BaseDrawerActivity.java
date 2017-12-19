package com.capton.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.capton.common.R;
import com.capton.common.databinding.ActivityBaseDrawerBinding;

/**
 * Created by capton on 2017/12/19.
 */

public abstract class BaseDrawerActivity <T extends ViewDataBinding> extends BaseActivity<ActivityBaseDrawerBinding> {

    public T drawerBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_drawer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawerBinding = DataBindingUtil.inflate(getLayoutInflater(),getDrawerLayout(),null,false);
        binding.drawerWrapper.addView(drawerBinding.getRoot());


    }

    public abstract int getDrawerLayout();
}
