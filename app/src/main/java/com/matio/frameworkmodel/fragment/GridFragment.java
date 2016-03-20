package com.matio.frameworkmodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.GridDetailActivity;
import com.matio.frameworkmodel.adapter.HotGridAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.HotGrid;
import com.matio.frameworkmodel.bean.SearchGrid;
import com.matio.frameworkmodel.common.HotConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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

    private static final int HORIZONTAL_SPACING = 6;

    private static final int VERTICAL_SPACING = 10;

    private ArrayList<HotGrid.HotDataEntity.HotItems.HotData> mDataList = new ArrayList<>();

    private ArrayList<SearchGrid.DataEntity.ItemsEntity> mItemList = new ArrayList<>();

    private String mKeyword;

    private static final String KEYWORD = "keyword";

    public static GridFragment newInstance(String keyword) {

        Bundle args = new Bundle();

        args.putString(KEYWORD, keyword);

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

        if (mKeyword != null) {

            HttpUtils.get("http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=&keyword=" + mKeyword, this);

        } else {

            HttpUtils.get(HotConstant.GRID_URL_GET, this);
        }
    }

    @Override
    public void setAdapter() {
        super.setAdapter();

        if (mKeyword != null) {

            mHotAdapter = new HotGridAdapter(getActivity(), mDataList, true);

        } else {

            mHotAdapter = new HotGridAdapter(getActivity(), mDataList);

            mPtreGiv.setMode(PullToRefreshBase.Mode.BOTH);
        }

        GridView refreshableView = mPtreGiv.getRefreshableView();

        refreshableView.setNumColumns(NUM_COLUMN);

        refreshableView.setHorizontalSpacing(HORIZONTAL_SPACING);

        refreshableView.setVerticalSpacing(VERTICAL_SPACING);

        refreshableView.setAdapter(mHotAdapter);

    }

    @Override
    public void get(String result) {

        if (mKeyword != null) {

            SearchGrid gridCom = JSONObject.parseObject(result, SearchGrid.class);

            if (gridCom != null) {

                mItemList.addAll(gridCom.getData().getItems());

                mHotAdapter.notifyDataSetChanged();
            }
        } else {

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

    @Event(value = R.id.ptregridview_fragment_grid, type = AdapterView.OnItemClickListener.class)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), GridDetailActivity.class);

        //GET http://api.liwushuo.com/v2/items/1042963

        if (mKeyword != null) {

            intent.putExtra("id", mItemList.get(position).getId());

        } else {

            intent.putExtra("id", mDataList.get(position).getId());
        }

        startActivity(intent);
    }
}
