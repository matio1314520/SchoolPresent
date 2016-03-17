package com.matio.frameworkmodel.fragment;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.PullListAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.PullList;
import com.matio.frameworkmodel.common.GuideConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/3/17.
 */
@ContentView(R.layout.fragment_list)
public class ListFragment extends BaseFragment implements HttpUtils.Callback {

    @ViewInject(R.id.ptrlistview_fragment_list)
    private PullToRefreshListView mPtrLv;

    public static final String ID = "id";

    private ArrayList<PullList.DataEntity.ItemsEntity> mItemList = new ArrayList<>(); //数据源

    private String mId;  //id

    private PullListAdapter mListAdapter; // 适配器

    public static ListFragment newInstance(int id) {

        Bundle args = new Bundle();

        args.putInt(ID, id);

        ListFragment fragment = new ListFragment();

        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onOperate() {

        //从bundle中获取传值
        getDataFromBundle();
    }


    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(getUrl(), this);
    }


    @Override
    public void setAdapter() {
        super.setAdapter();

        mListAdapter = new PullListAdapter(getActivity(),mItemList);

        mPtrLv.setMode(PullToRefreshBase.Mode.BOTH);

        mPtrLv.getRefreshableView().setAdapter(mListAdapter);
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

    @Override
    public void get(String result) {

        PullList pull = JSONObject.parseObject(result, PullList.class);

        if (pull != null) {

            //添加数据
            mItemList.addAll(pull.getData().getItems());

            //唤醒
            mListAdapter.notifyDataSetChanged();
        }
    }
}
