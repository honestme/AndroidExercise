package com.honestme.androidexercise.app.animetasteextra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangconglin on 2016/1/11.
 */
public class ATETabAdapter extends FragmentPagerAdapter {
    public static String[] mSubjects = {"主页","访谈录","每周惊奇","艾墨镇","冰糖葫芦","专题","关注我们"};

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (getPageTitle(position).toString()){
            case "主页":
                ATEMainFragment fragmentMain = new ATEMainFragment();
                fragment = fragmentMain;
                Bundle bundle = new Bundle();
                bundle.putString("type",mSubjects[position]);
                fragmentMain.setArguments(bundle);
                break;
            case "访谈录":
                break;
            case "每周惊奇":
                break;
            case "艾墨镇":
                break;
            case "冰糖葫芦":
                break;
            case "专题":
                break;
            case "关注我们":
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return mSubjects.length;
    }

    public ATETabAdapter(FragmentManager fm) {
        super(fm);
    }

    public ATETabAdapter(List<Fragment> list,FragmentManager fm){
        super(fm);
        mFragmentList = list;
    }
}
