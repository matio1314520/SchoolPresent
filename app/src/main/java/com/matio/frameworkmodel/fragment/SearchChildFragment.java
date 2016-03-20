package com.matio.frameworkmodel.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.GuidePagerAdapter;
import com.matio.frameworkmodel.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Angel on 2016/3/15.
 */
@ContentView(R.layout.fragment_searchchild)
public class SearchChildFragment extends BaseFragment {

    @ViewInject(R.id.tab_fragment_searchchild)
    private TabLayout mSearchTab;

    @ViewInject(R.id.viewpager_fragment_searchchild)
    private ViewPager mSearchVip;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    private ArrayList<String> titleList = new ArrayList<>();

    private String mKeyword;

    public static SearchChildFragment newInstance(String content) {

        Bundle args = new Bundle();

        args.putString("content", content);

        SearchChildFragment fragment = new SearchChildFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        if (getArguments() != null) {

                        String content = getArguments().getString("content");
            if (content != null) {
                mKeyword = URLEncoder.encode(content);
            }
        }
    }

    @Override
    public void onOperate() {
        titleList.add("礼物");

        titleList.add("攻略");

        mFragmentList.add(GridFragment.newInstance(mKeyword));

        mFragmentList.add(ListFragment.newInstance(0, mKeyword));
    }

    @Override
    public void setAdapter() {

        mSearchVip.setAdapter(new GuidePagerAdapter(getChildFragmentManager(), mFragmentList, titleList));

        mSearchTab.setTabMode(TabLayout.MODE_FIXED);

        mSearchTab.setupWithViewPager(mSearchVip);
    }
}