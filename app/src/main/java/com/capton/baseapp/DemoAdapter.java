package com.capton.baseapp;

import android.content.Context;
import android.view.ViewGroup;

import com.capton.common.base.CommonAdapter;
import com.capton.common.base.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by capton on 2018/2/23.
 */

public class DemoAdapter extends CommonAdapter<String> {

    List<Integer> randomHeightList = new ArrayList<>();

    public List<Integer> getRandomHeightList() {
        return randomHeightList;
    }

    public void setRandomHeightList(List<Integer> randomHeightList) {
        this.randomHeightList = randomHeightList;
    }

    public DemoAdapter(Context context, List<String> dataList, int resId) {
        super(context, dataList, resId);
    }

    @Override
    public void bindView(CommonViewHolder holder, String data, int position) {

        ViewGroup.LayoutParams lp = holder.getView(R.id.cardview).getLayoutParams();
        lp.height = randomHeightList.get(position);
        holder.getView(R.id.cardview).setLayoutParams(lp);

        holder.setImage(R.id.pic,data);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
