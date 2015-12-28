package com.honestme.androidexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhangconglin on 2015/12/27.
 */
public abstract class HonestAdapter<T> extends BaseAdapter {
    private List<T> mList;
    private LayoutInflater mLayoutInflater;
    private int mResourceId;

    public HonestAdapter(Context context,List<T> list,int resourceId){
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
    }

    public void setItems(List<T> list){
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = new BaseViewHolder(mLayoutInflater,convertView,parent,mResourceId);
        populate(viewHolder,(T)getItem(position));

        return convertView;
    }

    public abstract void populate(BaseViewHolder viewHolder,T item);
}
