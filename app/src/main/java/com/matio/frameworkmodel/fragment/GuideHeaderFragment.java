package com.matio.frameworkmodel.fragment;

import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.GuideRecyAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.GuideBanner;
import com.matio.frameworkmodel.bean.GuideRecycler;
import com.matio.frameworkmodel.common.GuideConstant;
import com.matio.frameworkmodel.utils.ConvenientBannerUtils;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/3/17.
 */
@ContentView(R.layout.fragment_header_guide)
public class GuideHeaderFragment extends BaseFragment {

    @ViewInject(R.id.convenientBanner_header_guide)
    private ConvenientBanner mHeaderBanner;

    @ViewInject(R.id.recyclerview_fragment_guideheader)
    private RecyclerView mHeaderRecyView;

    private ArrayList<GuideRecycler.DataEntity.SecondaryBannersEntity> mRecyList = new ArrayList<>();

    private ArrayList<GuideBanner.DataEntity.BannersEntity> mBannerList = new ArrayList(); // banner数据源

    private ArrayList<String> mBannerUrlList = new ArrayList<>(); //banner 图片地址

    private GuideRecyAdapter mRecyAdapter;

    public static GuideHeaderFragment newInstance() {

        GuideHeaderFragment fragment = new GuideHeaderFragment();

        return fragment;
    }

    @Override
    public void onOperate() {

        if (mHeaderBanner != null) {
            //开始轮播
            mHeaderBanner.stopTurning();
        }
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        //获取广告头数据
        requestBannerData();

        //获取TabLayout数据
        requestRecyclerData();
    }

    @Override
    public void setAdapter() {
        super.setAdapter();

        mRecyAdapter = new GuideRecyAdapter(getActivity(), mRecyList);

        mHeaderRecyView.setAdapter(mRecyAdapter);
    }

    /**
     * 获取RecyclerView数据
     */
    private void requestRecyclerData() {

        HttpUtils.get(GuideConstant.RECYCLER_URL_GET, new HttpUtils.Callback() {
            @Override
            public void get(String result) {
                GuideRecycler guideRecycler = JSONObject.parseObject(result, GuideRecycler.class);
                if (guideRecycler != null) {

                    mRecyList.addAll(guideRecycler.getData().getSecondary_banners());
                }
                mRecyAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取广告头数据
     */
    private void requestBannerData() {
        HttpUtils.get(GuideConstant.BANNER_URL_GET, new HttpUtils.Callback() {
            @Override
            public void get(String result) {

                if (result != null) {
                    GuideBanner guideBanner = JSONObject.parseObject(result, GuideBanner.class);
                    if (guideBanner != null) {

                        mBannerList.addAll(guideBanner.getData().getBanners());

                        for (GuideBanner.DataEntity.BannersEntity banner : guideBanner.getData().getBanners()) {

                            mBannerUrlList.add(banner.getImage_url());
                        }
                    }
                    //  //设置广告头效果
                    ConvenientBannerUtils.setBanner(mHeaderBanner, mBannerUrlList);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHeaderBanner != null) {
            //停止轮播
            mHeaderBanner.stopTurning();
        }
    }
}
