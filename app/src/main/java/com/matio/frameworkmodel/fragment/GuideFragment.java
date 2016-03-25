package com.matio.frameworkmodel.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSONObject;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.SearchActivity;
import com.matio.frameworkmodel.adapter.GuidePagerAdapter;
import com.matio.frameworkmodel.adapter.PopAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.GuideTab;
import com.matio.frameworkmodel.common.GuideConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 2016/2/19.
 */
@ContentView(R.layout.fragment_guide)
public class GuideFragment extends BaseFragment implements HttpUtils.Callback {

    @ViewInject(R.id.vip_fragment_guide)
    private ViewPager mGuideVip;

    @ViewInject(R.id.tab_fragment_guide)
    private TabLayout mGuideTab;

    @ViewInject(R.id.showall_fragment_guide)
    private ImageView mPopupImg;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>(); //fragment数据源

    private ArrayList<String> mTitleList = new ArrayList<>(); //标题数据源

    private ArrayList<GuideTab.DataEntity.CandidatesEntity> mPopList = new ArrayList<>();

    private ArrayList<GuideTab.DataEntity.ChannelsEntity> mTabList = new ArrayList<>();

    private PopAdapter mPopAdapter;

    private PopupWindow mPopupWindow;

    public static GuideFragment newInstance() {

        GuideFragment fragment = new GuideFragment();

        return fragment;
    }

    @Override
    public void onOperate() {

        HttpUtils.get(GuideConstant.TAB_URL_GET, this);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Event({R.id.signin_fragment_guide, R.id.changenight_fragment_guide, R.id.search_fragment_guide, R.id.showall_fragment_guide})
    private void onClick(View view) {

        switch (view.getId()) {

            case R.id.signin_fragment_guide:

                //日历

                break;

            case R.id.changenight_fragment_guide:

                //夜晚模式 白昼模式

                break;

            case R.id.search_fragment_guide:

                //页面跳转
                startActivity(new Intent(getActivity(), SearchActivity.class));

                break;

            case R.id.showall_fragment_guide:

                //spinner  切换频道
                showPopupWindow();

                break;
        }
    }

    private void showPopupWindow() {

        View showView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_fragment_guide, null);

        ViewHolder viewHolder = new ViewHolder(showView);

        viewHolder.gridView.setAdapter(mPopAdapter);

        mPopupWindow = new PopupWindow(showView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);

        mPopupWindow.showAsDropDown(mPopupImg);

    }

    class ViewHolder {

        @ViewInject(R.id.gridview_popupwindow_fragment_guide)
        public GridView gridView;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }


        @Event(value = R.id.gridview_popupwindow_fragment_guide, type = AdapterView.OnItemClickListener.class)
        private void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (mPopList != null) {

                int targetId = mPopList.get(position).getId();

                for (int i = 0, size = mPopList.size(); i < size; i++) {

                    if (targetId == mTabList.get(i).getId()) {

                        mGuideVip.setCurrentItem(i);
                    }
                }
            }
            mPopupWindow.dismiss();
        }
    }

    @Override
    public void get(String result) {

        GuideTab guideTab = JSONObject.parseObject(result, GuideTab.class);

        if (guideTab != null) {

            mPopList.addAll(guideTab.getData().getCandidates());

            List<GuideTab.DataEntity.ChannelsEntity> channelList = guideTab.getData().getChannels();

            if (channelList != null && channelList.size() > 0) {

                mTabList.addAll(channelList);

                //遍历
                for (int i = 0, size = channelList.size(); i < size; i++) {


                    //添加数据
                    mTitleList.add(channelList.get(i).getName());

                    if (i == 0) {

                        mFragmentList.add(ExpandableFragment.newInstance());

                    } else {

                        mFragmentList.add(ListFragment.newInstance(channelList.get(i).getId(), null));
                    }
                }

                //viewpager设置适配器
                mGuideVip.setAdapter(new GuidePagerAdapter(getChildFragmentManager(), mFragmentList, mTitleList));

                //将viewpager和tablayout联动
                mGuideTab.setupWithViewPager(mGuideVip);
            }

            mPopAdapter = new PopAdapter(getActivity(), mPopList);
        }
    }
}