package com.honestme.androidexercise;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhangconglin on 2015/12/27.
 */
public class BaseViewHolder {
    private LayoutInflater mLayoutInflater;
    private View mContentView;
    private SparseArray<View> mSparseArray;

    public BaseViewHolder(LayoutInflater layoutInflater,View contentView,ViewGroup viewGroup,
                          int resourceId){
        mLayoutInflater = layoutInflater;

        mContentView = contentView;
    }

    public BaseViewHolder getViewHolder(LayoutInflater layoutInflater,View contentView,ViewGroup viewGroup,
                                        int resourceId){
        if(contentView == null){
            contentView = mLayoutInflater.inflate(resourceId,viewGroup);
            contentView.setTag(this);
        }else {
            return (BaseViewHolder)contentView.getTag();
        }

        return new BaseViewHolder(layoutInflater,contentView,viewGroup,resourceId);
    }

    public View findView(int resourceId){
        View view = mSparseArray.get(resourceId);

        if(view == null){
            view = mContentView.findViewById(resourceId);
            mSparseArray.append(resourceId,view);
        }

        return view;
    }


    public android.view.View getContentView() {
        return mContentView;
    }

    public void setTextView(String text,int resourceId){
        TextView textView = (TextView)mContentView.findViewById(resourceId);
        textView.setText(text);

    }

    public void setImageView(String imageUrl,int resourceId){
        ImageView imageView = (ImageView)mContentView.findViewById(resourceId);

    }
}
