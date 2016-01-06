package com.honestme.androidexercise.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangconglin on 2016/1/6.
 */
public class Book implements Parcelable {
    private int mId;
    private String mName;

    public Book(int id,String name){
        mId = id;
        mName = name;
    }


    public Book() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
    }

    protected Book(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
