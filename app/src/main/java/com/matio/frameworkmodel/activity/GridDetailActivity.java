package com.matio.frameworkmodel.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.GuidePagerAdapter;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.bean.GridDetail;
import com.matio.frameworkmodel.common.HotConstant;
import com.matio.frameworkmodel.fragment.CommentFragment;
import com.matio.frameworkmodel.fragment.GridFragment;
import com.matio.frameworkmodel.utils.ConvenientBannerUtils;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/2/19.
 */
@ContentView(R.layout.activity_girddetail)
public class GridDetailActivity extends BaseActivity implements HttpUtils.Callback {

    @ViewInject(R.id.banner_activity_griddetail)
    private ConvenientBanner mGridBanner;

    @ViewInject(R.id.name_activity_griddetail)
    private TextView mNameTxt;

    @ViewInject(R.id.price_activity_griddetail)
    private TextView mPriceTxt;

    @ViewInject(R.id.description_activity_griddetail)
    private TextView mDescriptionTxt;

    @ViewInject(R.id.vip_activity_griddetail)
    private ViewPager mGridVip;

    @ViewInject(R.id.tab_activity_griddetail)
    private TabLayout mGridTab;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    private ArrayList<String> mTabList = new ArrayList<>();   //tablayout

    private int mId;

    @Override
    public void onOperate() {

        HttpUtils.get(getUrl(), this);
    }

    /**
     * 获取传值
     *
     * @return
     */
    private String getUrl() {

        if (getIntent() != null) {

            mId = getIntent().getIntExtra("id",0);

            return HotConstant.HEADER_GET_URL + mId;
        }

        return null;
    }

    /**
     * 一键分享
     */
    private void share() {


    }

    @Override
    public void get(String result) {

        GridDetail gridDetail = JSONObject.parseObject(result, GridDetail.class);

        if (gridDetail != null) {
            GridDetail.DataEntity data = gridDetail.getData();
            if (data != null) {

                ConvenientBannerUtils.setBanner(mGridBanner, data.getImage_urls());

                mNameTxt.setText(data.getName());
                mPriceTxt.setText("￥" + data.getPrice());
                mDescriptionTxt.setText(data.getDescription());

                mTabList.add("图文介绍");

                mTabList.add("评论");

                Log.i("ssss", "html: "+ data.getDetail_html());

                //http://api.liwushuo.com/v2%2Fitems%2F1013058%2Frecommend
                //http://api.liwushuo.com/v2%2Fitems%2F1008959%2Frecommend
                mFragmentList.add(GridFragment.newInstance(null, mId + "", null, data.getDetail_html()));

                mFragmentList.add(CommentFragment.newInstance(mId + ""));

                mGridVip.setAdapter(new GuidePagerAdapter(getSupportFragmentManager(), mFragmentList, mTabList));

                mGridTab.setupWithViewPager(mGridVip);
            }
        }
    }
}
