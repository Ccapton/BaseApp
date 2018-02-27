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

        bindView((CommonViewHolder) holder,dataList.get(position),position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(holder.itemView,position);

            }
        });
    }

    public abstract void bindView(CommonViewHolder holder,T data,int position);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

     @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(resIdMap != null)
            if(resIdMap.get(viewType) == null) {
                 try {
                    throw new NoMatchViewTypeException("没有与viewType相匹配的布局，请检查getItemViewType(int position)的返回值");
                } catch (NoMatchViewTypeException e) {
                    e.printStackTrace();
                }
                return null;
            }else {
                return new CommonViewHolder(LayoutInflater.from(context).inflate(resIdMap.get(viewType), parent, false),context);
            }
         return new CommonViewHolder(LayoutInflater.from(context).inflate(resId, parent, false),context);
     }



    @Override
    public abstract int getItemViewType(int position);

    public void add(T t){
        dataList.add(t);
        notifyItemRangeChanged(getItemCount(),getItemCount()+1);

    }
    public void add(T t,int index){
        dataList.add(index,t);
        notifyItemInserted(index);
    }
    public void remove(T t){
        dataList.remove(t);
        int position = dataList.indexOf(t);
        notifyItemRemoved(position);
    }
    public void remove(int index){
        dataList.remove(index);
        notifyItemRemoved(index);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}

class NoMatchViewTypeException extends Exception{
    public NoMatchViewTypeException(String message) {
        super(message);
    }
}