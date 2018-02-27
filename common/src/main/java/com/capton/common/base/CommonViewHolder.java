package com.capton.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by capton on 2018/1/1.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View itemView;
    private Context context;

    public CommonViewHolder(View itemView,Context context) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        views = new SparseArray<>();
    }


    public <T extends View> T getView(int id){
        if (views.get(id) == null)
            views.put(id,itemView.findViewById(id));
        return (T)views.get(id);
    }
    public void setText(int id,String text){
      TextView textView = getView(id);
      textView.setText(text);
    }
    public void setImage(int id,String url){
        ImageView imageView = getView(id);
        Glide.with(context).load(url).into(imageView);
    }
}
