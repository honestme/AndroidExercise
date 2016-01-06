package com.honestme.androidexercise.app.csdn;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ListView;


import com.honestme.androidexercise.BaseViewHolder;
import com.honestme.androidexercise.HonestAdapter;
import com.honestme.androidexercise.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

/**
 * Created by zhangconglin on 2015/12/30.
 */
public class CSDNMainActivity extends Activity {
    private final String TAG = getClass().getSimpleName();

    private Context mContext;

    private TabPageIndicator mIndicator ;
    private ViewPager mViewPager;
    private CSDNTabAdapter mPagerAdapter;
    //Adapter for listview.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_csdn_activity_main);
        mIndicator = (TabPageIndicator)findViewById(R.id.app_csdn_indicator);
        mViewPager = (ViewPager)findViewById(R.id.app_csdn_pager);
        mViewPager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mViewPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
