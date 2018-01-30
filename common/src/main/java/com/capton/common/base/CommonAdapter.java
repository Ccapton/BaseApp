package com.capton.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by capton on 2017/6/19.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter {

    public Context context;
    private List<T> dataList = new ArrayList<>();
    private int resId;
    private OnItemClickListener itemClickListener;
    private Map<Integer,Integer> resIdMap; // key:viewType value:布局文件id
    private Map<Integer,CommonViewHolder> holderMap; // key:viewType value:布局文件id

    public CommonAdapter(Context context, List<T> dataList, int resId) {
        this.context = context;
        this.resId=resId;
        this.dataList = dataList;
    }
    public CommonAdapter(Context context, List<T> dataList, Map<Integer,Integer> resIdMap) {
        this.context = context;
        this.resIdMap = resIdMap;
        this.dataList = dataList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        bindView(holder,dataList.get(position),position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(holder.itemView,position);

            }
        });
    }

    public abstract void bindView(RecyclerView.ViewHolder holder,T data,int position);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

     @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(resIdMap != null)
          return new CommonViewHolder(LayoutInflater.from(context).inflate(resIdMap.get(viewType), parent, false));
         return new CommonViewHolder(LayoutInflater.from(context).inflate(resId, parent, false));
     }

    @Override
    public abstract int getItemViewType(int position);

    public void add(T t){
        dataList.add(t);
        notifyDataSetChanged();
    }
    public void add(T t,int index){
        dataList.add(index,t);
        notifyDataSetChanged();
    }
    public void remove(T t){
        dataList.remove(t);
        notifyDataSetChanged();
    }
    public void remove(int index){
        dataList.remove(index);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
