package com.capton.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.capton.common.R;
import com.capton.common.databinding.ActivityBaseBinding;

/**
 * Created by capton on 2017/11/27.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    public ActivityBaseBinding baseBinding;
    public T binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(getLayoutInflater(),getLayoutId(),null,false);
        baseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base,null,false);
        baseBinding.container.addView(binding.getRoot());

        setContentView(baseBinding.getRoot());

        setShowMoreIcon(false);
        setClickListener(true);
        setClickListener();
    }

    /**
     * 获取子类布局id
     * @return
     */
    public abstract int getLayoutId();
    /**
     * 是否展示返回按钮
     * @param show
     */
    public void setShowBackIcon(boolean show){
        if(show)
            baseBinding.back.setVisibility(View.VISIBLE);
        else
            baseBinding.back.setVisibility(View.INVISIBLE);
    }
    /**
     * 是否展示更多按钮
     * @param show
     */
    public void setShowMoreIcon(boolean show){
        if(show)
            baseBinding.more.setVisibility(View.VISIBLE);
        else
            baseBinding.more.setVisibility(View.INVISIBLE);
    }

    /**
     * 是否展示右边文字
     * @param show
     */
    public void setShowRightText(boolean show){
        if(show)
            baseBinding.rightText.setVisibility(View.VISIBLE);
        else
            baseBinding.rightText.setVisibility(View.INVISIBLE);
    }

    /**
     * 抽象点击函数
     */
    public abstract void setClickListener();

        /**
         * 设置右边文字
         * @param text
         */
    public void setRightText(String text){
        if(text!=null)
            baseBinding.rightText.setText(text);
    }

    private void setClickListener(boolean isBase){
        baseBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        baseBinding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMore();
            }
        });
        baseBinding.rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightText();
            }
        });
    }

    /**
     *  点击更多按钮
     */
    public abstract void clickMore();

    /**
     *  点击右侧文字
     */
    public abstract void clickRightText();


    /**
     *  更换"更多"按钮的图标，例如变成发送、或者完成的图标
     */
    public void setMoreIcon(int resId){
        baseBinding.more.setImageResource(resId);
    }

    public void setTitle(String title){
        baseBinding.title.setText(title);
    }

}
