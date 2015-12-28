package com.honestme.androidexercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhangconglin on 2015/12/27.
 */
public class BaseViewHolder {
    private LayoutInflater mLayoutInflater;
    private View mContentView;

    public BaseViewHolder(LayoutInflater layoutInflater,View contentView,ViewGroup viewGroup,
                          int resourceId){
        mLayoutInflater = layoutInflater;

        if(contentView == null){
            contentView = mLayoutInflater.inflate(resourceId,viewGroup);
            contentView.setTag(this);
        }
        mContentView = contentView;
    }

    public void setTextView(String text,int ResourceId){
        TextView textView = (TextView)mContentView.findViewById(ResourceId);
        textView.setText(text);

    }
}
