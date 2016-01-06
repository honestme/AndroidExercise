package com.honestme.androidexercise.app.csdn;

/**
 * Created by zhangconglin on 2016/1/4.
 */
public class CSDNNewsItem {
    private String mTitle;
    private String mDate;
    private String mContent;
    private int mId;
    private String mContentLink;
    private String mImageLink;
    private int mNewsType;
    private String mViewTimes;
    private String mRecommends;

    public String getViewTimes() {
        return mViewTimes;
    }

    public void setViewTimes(String viewTimes) {
        mViewTimes = viewTimes;
    }

    public String getRecommends() {
        return mRecommends;
    }

    public void setRecommends(String recommends) {
        mRecommends = recommends;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getContentLink() {
        return mContentLink;
    }

    public void setContentLink(String contentLink) {
        mContentLink = contentLink;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        mImageLink = imageLink;
    }

    public int getNewsType() {
        return mNewsType;
    }

    public void setNewsType(int newsType) {
        mNewsType = newsType;
    }

    @Override
    public String toString() {
        return "CSDNNewsItem [id=" + mId + ", title=" + mTitle + ", contentLink=" + mContentLink + ", date=" + mDate + ", imgLink=" + mImageLink
                + ", content=" + mContent + ", newsType=" + mNewsType +",viewTimes=" + mViewTimes
                + ",recommends=" + mRecommends + "]";
    }
}
