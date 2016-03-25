package com.matio.frameworkmodel.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.CategoryChildActivity;
import com.matio.frameworkmodel.adapter.ExpandableAdapter;
import com.matio.frameworkmodel.adapter.GuideRecyAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.GuideBanner;
import com.matio.frameworkmodel.bean.GuideList;
import com.matio.frameworkmodel.bean.GuideRecycler;
import com.matio.frameworkmodel.common.GuideConstant;
import com.matio.frameworkmodel.utils.ConvenientBannerUtils;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Angel on 2016/3/17.
 */
@ContentView(R.layout.fragment_expandable)
public class ExpandableFragment extends BaseFragment implements HttpUtils.Callback, ExpandableListView.OnGroupClickListener, PullToRefreshBase.OnRefreshListener2<ExpandableListView>, OnItemClickListener {

    @ViewInject(R.id.ptrelistview_fragment_expandable)
    private PullToRefreshExpandableListView mPtreLv;

    private ExpandableAdapter mExpandableAdapter;

    private Map<String, List<GuideList.DataEntity.ItemsEntity>> mItemMap = new HashMap<>();

    private ArrayList<String> mTitleList = new ArrayList<>(); //

    private View mHeaderView;  //头部

    private ViewHolder mViewHolder;

    private ArrayList<GuideRecycler.DataEntity.SecondaryBannersEntity> mRecyList = new ArrayList<>();

    private ArrayList<GuideBanner.DataEntity.BannersEntity> mBannerList = new ArrayList(); // banner数据源

    private ArrayList<String> mBannerUrlList = new ArrayList<>(); //banner 图片地址

    private GuideRecyAdapter mRecyAdapter;

    private int START_OFFSET = 0;

    private int ADD_OFFSET = 20;

    private static final int BANNER_TURNING = 2 * 1000;

    public static ExpandableFragment newInstance() {

        ExpandableFragment fragment = new ExpandableFragment();

        return fragment;
    }

    @Override
    public void onOperate() {

        //设置监听事件
      //  mPtreLv.setOnRefreshListener(this);

      //  mViewHolder.mHeaderBanner.setOnItemClickListener(this);

        //加载头部布局
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_header_guide, null);

        mViewHolder = new ViewHolder(mHeaderView);

        //添加头部
      //  mPtreLv.getRefreshableView().addHeaderView(mHeaderView);

        if (mViewHolder.mHeaderBanner != null) {
            //开始轮播
            mViewHolder.mHeaderBanner.startTurning(BANNER_TURNING);
        }
    }

    @Event(value = {R.id.ptrelistview_fragment_expandable}, type = AdapterView.OnItemClickListener.class)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {

        if (mTitleList != null && mItemMap != null) {

            mTitleList.clear();

            mItemMap.clear();
        }

        START_OFFSET = 0;

        requestListData(START_OFFSET);
    }

    /**
     * 上拉加载
     *
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {

        START_OFFSET += ADD_OFFSET;

        requestListData(START_OFFSET);
    }


    /**
     * banner点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {

        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), CategoryChildActivity.class);

        intent.putExtra("id",mBannerList.get(position).getId());

        //http://api.liwushuo.com/v2/collections/227/posts?limit=20&offset=0
        //http://api.liwushuo.com/v2/collections/225/posts?limit=20&offset=0

        startActivity(intent);
    }

    private class ViewHolder {

        @ViewInject(R.id.convenientBanner_header_guide)
        private ConvenientBanner mHeaderBanner;

        @ViewInject(R.id.recyclerview_fragment_guideheader)
        private RecyclerView mHeaderRecyView;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        //获取listview数据
        requestListData(START_OFFSET);

        //请求banner数据
     //   requestBannerData();

        //请求recyclerview数据
    //    requestRecyclerData();
    }

    /**
     * 获取listview数据
     */
    private void requestListData(int offset) {
    //PullToRefreshExpandableListView
       HttpUtils.get(getUrl(offset), this);
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
                    ConvenientBannerUtils.setBanner(mViewHolder.mHeaderBanner, mBannerUrlList);
                }
            }
        });
    }

    @Override
    public void setAdapter() {
       super.setAdapter();

        mExpandableAdapter = new ExpandableAdapter(getActivity(), mTitleList, mItemMap);

        mPtreLv.setMode(PullToRefreshBase.Mode.BOTH);

        mPtreLv.getRefreshableView().setAdapter(mExpandableAdapter);

        mPtreLv.getRefreshableView().setOnGroupClickListener(this);


   //     mViewHolder.mHeaderRecyView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

    //    mRecyAdapter = new GuideRecyAdapter(getActivity(), mRecyList);

     //   mViewHolder.mHeaderRecyView.setAdapter(mRecyAdapter);
    }

    @Override
    public void get(String result) {

        GuideList pull = JSONObject.parseObject(result, GuideList.class);

        if (pull != null) {

            List<GuideList.DataEntity.ItemsEntity> itemList = pull.getData().getItems();

            if (itemList != null && itemList.size() > 0) {
                for (int i = 0, size = itemList.size(); i < size; i++) {

                    GuideList.DataEntity.ItemsEntity item = itemList.get(i);

                    String key = formatDate(item.getPublished_at() * 1000L);

                    List<GuideList.DataEntity.ItemsEntity> itemsEntities = mItemMap.get(key);

                    if (itemsEntities != null) {

                        itemsEntities.add(item);

                    } else {

                        mTitleList.add(key);

                        itemsEntities = new ArrayList<>();

                        itemsEntities.add(item);

                        mItemMap.put(key, itemsEntities);
                    }
                    //设置默认展开
                    mPtreLv.getRefreshableView().expandGroup(i);
                }
            }
            //唤醒
            mExpandableAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 拼接url
     *
     * @return
     */
    private String getUrl(int offset) {

        return GuideConstant.HEADER_URL_GET + offset + GuideConstant.FOOTER_URL_GET;
    }

    /**
     * 格式化固定的时间
     *
     * @param time
     * @return 年月日 星期
     */
    private String formatDate(long time) {

        return formatDate(time, "yyyy-MM-dd E");

    }

    private String formatDate(long time, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(new Date(time));
    }

    //设置不可收拢
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mViewHolder.mHeaderBanner != null) {
            //停止轮播
            mViewHolder.mHeaderBanner.stopTurning();
        }
    }
}