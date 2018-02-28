package com.capton.baseapp;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.capton.baseapp.databinding.ActivityCardBinding;
import com.capton.common.base.BaseActivity;
import com.capton.common.base.ScaleView;
import com.capton.ep.EasyPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class CardActivity extends BaseActivity<ActivityCardBinding>{

    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1519395850442&di=942b93483279c791ffdb3019d65d2484&imgtype=0&src=http%3A%2F%2Fdynamic-image.yesky.com%2F740x-%2FuploadImages%2F2015%2F131%2F33%2FD472XQ25C7H2.jpg";

      List<View> viewList = new ArrayList<>();
    @Override
    protected void yourOperation() {

        for (int i = 0; i < 6; i++) {
            View view = View.inflate(this,R.layout.layout_image,null);
            ImageView imageView = view.findViewById(R.id.pic);
            Glide.with(this).load(url).into(imageView);
            viewList.add(view);
        }

        binding.viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }


            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }

    @Override
    public String[] getPermissions() {
        return new String[0];
    }

    @Override
    public EasyPermission.OnPermissionListener getPermissionListener() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card;
    }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }
}
