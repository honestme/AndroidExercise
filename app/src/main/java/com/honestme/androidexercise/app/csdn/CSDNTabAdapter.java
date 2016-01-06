package com.honestme.androidexercise.app.csdn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zhangconglin on 2015/12/31.
 */
public class CSDNTabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLES = new String[]{"业界","移动","云计算","软件研发","程序员","极客头条","专题"};
    private Bundle mArgs;

    public CSDNTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position & TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mainFragment = new Fragment();
        mArgs.putInt("type", position);
        mainFragment.setArguments(mArgs);
        return mainFragment;
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }
}

