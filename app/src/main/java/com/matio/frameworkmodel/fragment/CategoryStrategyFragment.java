package com.matio.frameworkmodel.fragment;


import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.CategoryStrategyListAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.CategoryStrategy;
import com.matio.frameworkmodel.common.CategoryConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 2016/2/15.
 */
@ContentView(R.layout.fragment_strategy_category)
public class CategoryStrategyFragment extends BaseFragment implements HttpUtils.Callback {

    private CategoryStrategyListAdapter mAdapter;

    @ViewInject(R.id.listview_fragment_strategy_category)
    private ListView mStrategyLv;

    private List<CategoryStrategy.CategoryDataEntity.CategoryChannelGroups> mChannelList = new ArrayList<>();

    public static CategoryStrategyFragment newInstance() {

        CategoryStrategyFragment fragment = new CategoryStrategyFragment();

        return fragment;
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(CategoryConstant.STRATEGY_URL_GET, this);
    }

    @Override
    public void onOperate() {
        //加载布局
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_header_strategy, null);
//
//        mStrategyLv.addHeaderView(view);
    }

    @Override
    public void setAdapter() {
        super.setAdapter();

        mAdapter = new CategoryStrategyListAdapter(getActivity(), mChannelList);

        mStrategyLv.setAdapter(mAdapter);
    }


    @Override
    public void get(String result) {

        CategoryStrategy categoryStrategy = JSONObject.parseObject(result, CategoryStrategy.class);

        if (categoryStrategy != null) {

            mChannelList.addAll(categoryStrategy.getData().getChannel_groups());

            mAdapter.notifyDataSetChanged();
        }
    }
}