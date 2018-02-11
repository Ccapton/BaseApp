package com.capton.baseapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.capton.common.base.CommonAdapter;
import com.lzy.imagepicker.view.CropImageView;

import java.util.List;

/**
 * Created by capton on 2018/2/7.
 */

public class AnimationAdapter extends CommonAdapter <AnimationBean.DataBean.ArchivesBean>{

    public AnimationAdapter(Context context, List<AnimationBean.DataBean.ArchivesBean> dataList, int resId) {
        super(context, dataList, resId);
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, AnimationBean.DataBean.ArchivesBean data, int position) {
        ((TextView)holder.itemView.findViewById(R.id.title)).setText(data.getTitle());
        Glide.with(context).load(data.getPic()).into((CropImageView)holder.itemView.findViewById(R.id.pic));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
