package com.matio.frameworkmodel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.ExpandableAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.PullList;
import com.matio.frameworkmodel.common.GuideConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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
public class ExpandableFragment extends BaseFragment implements HttpUtils.Callback, ExpandableListView.OnGroupClickListener {

    public static final String ID = "id";

    @ViewInject(R.id.ptrelistview_fragment_expandable)
    private PullToRefreshExpandableListView mPtreLv;

    private String mId;

    private ExpandableAdapter mExpandableAdapter;

    private Map<String, List<PullList.DataEntity.ItemsEntity>> mItemMap = new HashMap<>();

    private ArrayList<String> mTitleList = new ArrayList<>();

    public static ExpandableFragment newInstance(int id) {

        Bundle args = new Bundle();

        args.putInt(ID, id);

        ExpandableFragment fragment = new ExpandableFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onOperate() {

        //从bundle中获取传值
        getDataFromBundle();

        mPtreLv.getRefreshableView().addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_container_header_guide, null));
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(getUrl(), this);
    }

    @Override
    public void setAdapter() {
        super.setAdapter();

        mExpandableAdapter = new ExpandableAdapter(getActivity(), mTitleList, mItemMap);

        mPtreLv.setMode(PullToRefreshBase.Mode.BOTH);

        mPtreLv.getRefreshableView().setAdapter(mExpandableAdapter);

        mPtreLv.getRefreshableView().setOnGroupClickListener(this);
    }

    @Override
    public void get(String result) {

        PullList pull = JSONObject.parseObject(result, PullList.class);

        if (pull != null) {

            List<PullList.DataEntity.ItemsEntity> itemList = pull.getData().getItems();

            if (itemList != null && itemList.size() > 0) {
                for (int i = 0, size = itemList.size(); i < size; i++) {

                    PullList.DataEntity.ItemsEntity item = itemList.get(i);

                    String key = formatDate(item.getPublished_at() * 1000L);

                    List<PullList.DataEntity.ItemsEntity> itemsEntities = mItemMap.get(key);

                    if (itemsEntities != null) {

                        itemsEntities.add(item);

                    } else {

                        mTitleList.add(key);

                        itemsEntities = new ArrayList<>();

                        itemsEntities.add(item);

                        mItemMap.put(key, itemsEntities);
                    }
                    mPtreLv.getRefreshableView().expandGroup(i);
                }
            }
            //唤醒
            mExpandableAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 从bundle中获取传值
     */
    private void getDataFromBundle() {

        if (getArguments() != null) {

            mId = getArguments().getInt(ID) + "";
        }
    }

    /**
     * 拼接url
     *
     * @return
     */
    private String getUrl() {

        if (mId != null) {
            return GuideConstant.HEADER_URL_GET + mId + GuideConstant.FOOTER_URL_GET;
        }
        return null;
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
}
