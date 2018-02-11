package com.capton.baseapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.capton.common.base.CommonAdapter;

import java.util.List;

/**
 * Created by capton on 2018/2/1.
 */

public class StringAdapter extends CommonAdapter <String>{

    public StringAdapter(Context context, List<String> dataList, int resId) {
        super(context, dataList, resId);
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, String data, int position) {
        ((TextView)holder.itemView.findViewById(R.id.title)).setText(data);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
