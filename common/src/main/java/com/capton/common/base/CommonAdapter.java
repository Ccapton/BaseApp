package com.capton.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by capton on 2017/6/19.
 */

public abstract class CommonAdapter<T,S extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    public Context context;
    private ArrayList<T> dataList = new ArrayList<>();
    private int resId;
    private S sunViewHolder;

    public CommonAdapter(Context context, ArrayList<T> dataList, int resId) {
        this.context = context;
        this.resId=resId;
        this.dataList = dataList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindView(holder,dataList.get(position),position);
    }

    public abstract void bindView(RecyclerView.ViewHolder holder,T data,int position);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context).inflate(resId,parent,false));
    }
    public abstract S getViewHolder(View itemView);

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



}
