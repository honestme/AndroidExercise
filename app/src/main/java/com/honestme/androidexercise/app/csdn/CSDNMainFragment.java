package com.honestme.androidexercise.app.csdn;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.honestme.androidexercise.BaseViewHolder;
import com.honestme.androidexercise.HonestAdapter;
import com.honestme.androidexercise.R;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by zhangconglin on 2015/12/31.
 */
public class CSDNMainFragment extends android.support.v4.app.Fragment {
    private final int REFRESH = 0;
    private final int LOAD_MORE = 1;

    private int mType = CSDNApiUtil.NEWS_YEJIE;

    private int mPage = 1;

    private Context mContext = getActivity();

    private ListView mListView;
    private PtrFrameLayout mFrameLayout;

    private HonestAdapter<CSDNNewsItem> mAdapter;

    private List<CSDNNewsItem> mNewsItems = new ArrayList<CSDNNewsItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public CSDNMainFragment() {
        super();
        Bundle bundle = getArguments();
        mType = bundle.getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_csdn_fragment_main,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = (ListView)getView().findViewById(R.id.app_csdn_listview);
        mFrameLayout = (PtrFrameLayout)getView().findViewById(R.id.app_csdn_ptr_frame);
        mAdapter = new HonestAdapter<CSDNNewsItem>(mContext,mNewsItems,R.layout.app_csdn_item_news) {
            @Override
            public void populate(BaseViewHolder viewHolder, CSDNNewsItem item) {
                viewHolder.setTextView(item.getTitle(),R.id.app_csdn_news_item_title);
                viewHolder.setTextView(item.getContent(), R.id.app_csdn_news_item_content);
                viewHolder.setImageView(item.getImageLink(), R.id.app_csdn_news_item_image);
                viewHolder.setTextView(item.getDate(), R.id.app_csdn_news_item_date);
                viewHolder.setTextView(item.getRecommends(), R.id.app_csdn_news_item_recommends);
                viewHolder.setTextView(item.getViewTimes(),R.id.app_csdn_news_item_viewTimes);
            }
        };

        mFrameLayout = new PtrFrameLayout(mContext,null);
        mFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new LoadDataTask().execute(REFRESH);
            }
        });

    }

    private class LoadDataTask extends AsyncTask<Integer,Void,Integer>{
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer param) {
            mAdapter.setItems(mNewsItems);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]){
                case REFRESH:
                    refresh();
                    break;
                case LOAD_MORE:
                    loadMore();
            }

            return -1;
        }
    }

    public void refresh(){
        try{
            List<CSDNNewsItem> list = new ArrayList<CSDNNewsItem>();

            list = CSDNApiUtil.getNewsTitleFromHtml(mType, mPage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadMore(){

    }
}

