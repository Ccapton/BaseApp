package com.capton.baseapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.capton.common.base.CommonAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by capton on 2018/1/30.
 */

public class DemoAdapter extends CommonAdapter<String> {


    public DemoAdapter(Context context, List<String> dataList, int resId) {
        super(context, dataList, resId);
    }

    public DemoAdapter(Context context, List<String> dataList, Map<Integer, Integer> resIdMap) {
        super(context, dataList, resIdMap);
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, String data, int position) {
        ((TextView)holder.itemView.findViewById(R.id.text)).setText(data);
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0)
          return 0;
        return 1;
    }


}
