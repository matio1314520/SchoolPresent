package com.matio.frameworkmodel.fragment;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.PullListAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.GuideList;
import com.matio.frameworkmodel.bean.SearchList;
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

    private ArrayList<GuideList.DataEntity.ItemsEntity> mItemList = new ArrayList<>(); //数据源

    private String mId;  //id

    private String mKeyword;

    private PullListAdapter mListAdapter; // 适配器

    private final static String KEYWORD = "keyword";

    private ArrayList<SearchList.DataEntity.PostsEntity> mPostList = new ArrayList<>();

    public static ListFragment newInstance(int id, String keyword) {

        Bundle args = new Bundle();

        args.putInt(ID, id);

        args.putString(KEYWORD, keyword);

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

        if (mKeyword != null) {
            HttpUtils.get("http://api.liwushuo.com/v2/search/post?limit=20&offset=0&sort=&keyword=" + mKeyword, this);

        } else {

            HttpUtils.get(getUrl(), this);
        }
    }


    @Override
    public void setAdapter() {
        super.setAdapter();

        if (mKeyword != null){

            mListAdapter = new PullListAdapter(getActivity(), mItemList,true);
        }else {

            mListAdapter = new PullListAdapter(getActivity(), mItemList);

            mPtrLv.setMode(PullToRefreshBase.Mode.BOTH);

            mPtrLv.getRefreshableView().setAdapter(mListAdapter);
        }
    }

    /**
     * 从bundle中获取传值
     */
    private void getDataFromBundle() {

        if (getArguments() != null) {

            mId = getArguments().getInt(ID) + "";

            mKeyword = getArguments().getString(KEYWORD);
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
        if (mKeyword != null) {

            SearchList search = JSONObject.parseObject(result, SearchList.class);

            if (search != null) {

                mPostList.addAll(search.getData().getPosts());
            }

        } else {

            GuideList guide = JSONObject.parseObject(result, GuideList.class);

            if (guide != null) {

                //添加数据
                mItemList.addAll(guide.getData().getItems());

                //唤醒
                mListAdapter.notifyDataSetChanged();
            }
        }
    }
}
