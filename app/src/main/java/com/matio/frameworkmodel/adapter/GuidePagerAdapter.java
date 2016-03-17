package com.matio.frameworkmodel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Angel on 2016/1/29.
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    private List<String> mTilteList;

    public GuidePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    public GuidePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        this(fm, fragmentList);
        this.mTilteList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTilteList != null ? mTilteList.get(position) : null;
    }
}
