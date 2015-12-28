package com.honestme.androidexercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangconglin on 2015/12/27.
 */
public class HonestItem implements Parcelable {
    private String mContent;

    public HonestItem(String content){
        mContent = content;
    }

    public HonestItem(Parcel source){
        mContent = source.readString();
    }

    public String getContent() {
        return mContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mContent);
    }

    public static final Parcelable.Creator<HonestItem> CREATOR = new Parcelable.Creator<HonestItem>(){
        @Override
        public HonestItem createFromParcel(Parcel source) {
            return new HonestItem(source);
        }

        @Override
        public HonestItem[] newArray(int size) {
            return new HonestItem[size];
        }
    };
}
