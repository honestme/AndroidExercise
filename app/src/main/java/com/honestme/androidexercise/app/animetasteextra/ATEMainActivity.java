package com.honestme.androidexercise.app.animetasteextra;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.honestme.androidexercise.R;
import com.viewpagerindicator.TabPageIndicator;

public class ATEMainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private TabPageIndicator mPageIndicator;
    private ATETabAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_ate_main_activity);
        mTabAdapter = new ATETabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mPageIndicator.setViewPager(mViewPager);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
