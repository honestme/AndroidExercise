package com.honestme.androidexercise.app.animetasteextra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.honestme.androidexercise.R;

/**
 * Created by zhangconglin on 2016/1/11.
 */
public class ATEMainFragment extends android.support.v4.app.Fragment{

    private int mSubjectType;
    private ListView mListView;



    public ATEMainFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_ate_main_fragment,container);
        mListView = (ListView)view.findViewById(R.id.app_ate_main_fragment_listview);
        return view;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
