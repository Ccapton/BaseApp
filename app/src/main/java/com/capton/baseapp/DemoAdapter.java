package com.capton.baseapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.capton.common.base.CommonAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by capton on 2018/1/30.
 */

public class DemoAdapter extends CommonAdapter<String> {

    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517334110883&di=cf50d6a86393b89883775f02602fe8aa&imgtype=0&src=http%3A%2F%2Fimg3.yxlady.com%2Fyl%2FUploadFiles_5361%2F20160513%2F20160513171214742.jpg";
    String url2 ="http://bmob-cdn-10799.b0.upaiyun.com/2017/07/27/c472c53557aa44c7950516a48b6b7283.jpg";
    String url3 ="http://bmob-cdn-10799.b0.upaiyun.com/2017/05/05/b6e58751c2a24e318ae17495128c91a5.jpg";
    String url4 ="http://bmob-cdn-10799.b0.upaiyun.com/2017/04/21/9b221d7971ae43028da782ca3f127500.jpg";
    public DemoAdapter(Context context, List<String> dataList, int resId) {
        super(context, dataList, resId);
    }

    public DemoAdapter(Context context, List<String> dataList, Map<Integer, Integer> resIdMap) {
        super(context, dataList, resIdMap);
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, String data, int position) {
        ((TextView)holder.itemView.findViewById(R.id.text)).setText(data);
        String path = url;
        if(position %2 == 0)
            path = url;
        if(position %3 == 0)
            path = url2;
        if(position %5 == 0)
            path = url3;
        if(position %7 == 0)
            path = url4;
             Glide.with(context).load(path).into((ImageView) holder.itemView.findViewById(R.id.pic));

    }

    @Override
    public int getItemViewType(int position) {
        if(position <= 15)
          return 0;
        else if(position%2 == 0)
            return 0;
        return 1;
    }


}
