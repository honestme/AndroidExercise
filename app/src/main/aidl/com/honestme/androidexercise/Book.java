package com.honestme.androidexercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangconglin on 2016/1/6.
 */
public class Book implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Book() {
    }

    protected Book(Parcel in) {
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
