package com.matio.frameworkmodel.fragment;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.HotGridAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.HotGrid;
import com.matio.frameworkmodel.common.HotConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
@ContentView(R.layout.fragment_grid)
public class GridFragment extends BaseFragment implements HttpUtils.Callback {

    @ViewInject(R.id.ptregridview_fragment_grid)
    private PullToRefreshGridView mPtreGiv;

    private HotGridAdapter mHotAdapter;

    private static final int NUM_COLUMN = 2;

    private ArrayList<HotGrid.HotDataEntity.HotItems.HotData> mDataList = new ArrayList<>();

    public static GridFragment newInstance() {

        Bundle args = new Bundle();

        GridFragment fragment = new GridFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onOperate() {

    }


    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(HotConstant.GRID_URL_GET, this);
    }

    @Override
    public void setAdapter() {
        super.setAdapter();

        mHotAdapter = new HotGridAdapter(getActivity(), mDataList);

        mPtreGiv.setMode(PullToRefreshBase.Mode.BOTH);

        mPtreGiv.getRefreshableView().setNumColumns(NUM_COLUMN);

        mPtreGiv.getRefreshableView().setAdapter(mHotAdapter);
    }


    @Override
    public void get(String result) {

        HotGrid hotGrid = JSONObject.parseObject(result, HotGrid.class);

        if (hotGrid != null) {

            List<HotGrid.HotDataEntity.HotItems> hotItemsList = hotGrid.getData().getItems();

            for (HotGrid.HotDataEntity.HotItems items : hotItemsList) {

                mDataList.add(items.getData());
            }
            mHotAdapter.notifyDataSetChanged();
        }
    }
}
