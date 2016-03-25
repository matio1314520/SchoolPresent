package com.matio.frameworkmodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.matio.frameworkmodel.bean.CommentGrid;
import com.matio.frameworkmodel.bean.GiftGrid;
import com.matio.frameworkmodel.bean.HotGrid;
import com.matio.frameworkmodel.bean.SearchGrid;
import com.matio.frameworkmodel.common.HotConstant;
import com.matio.frameworkmodel.utils.ComeFrom;
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
    private PullToRefreshGridView mPtrGiv;

    private HotGridAdapter mHotAdapter;

    private static final int NUM_COLUMN = 2;

    private static final int HORIZONTAL_SPACING = 6;

    private static final int VERTICAL_SPACING = 10;

    private ArrayList<HotGrid.HotDataEntity.HotItems.HotData> mDataList = new ArrayList<>();

    private ArrayList<SearchGrid.DataEntity.ItemsEntity> mItemList = new ArrayList<>();

    private ArrayList<GiftGrid.DataEntity.ItemsEntity> mGiftList = new ArrayList<>();

    private ArrayList<HotGrid.HotDataEntity.HotItems.HotData> mDetailList = new ArrayList<>();

    private String mKeyword;

    private String mId;

    private String mUrl;

    private String mHtml;

    private static final String KEYWORD = "keyword";

    private static final String ID = "id";

    private static final String URL = "url";

    public static GridFragment newInstance(String keyword, String id, String url, String html) {

        Bundle args = new Bundle();

        args.putString(KEYWORD, keyword);

        args.putString(ID, id);

        args.putString(URL, url);

        args.putString("html", html);

        GridFragment fragment = new GridFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onOperate() {

        if (getArguments() != null) {

            mKeyword = getArguments().getString(KEYWORD);

            mId = getArguments().getString(ID);

            mUrl = getArguments().getString(URL);

            mHtml = getArguments().getString("html");
        }
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(getUrl(), this);
    }

    private String getUrl() {

        if (mUrl == null && mHtml == null) {
            //搜索界面
            if (mKeyword != null && mId == null) {

                return "http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=&keyword=" + mKeyword;
            }

            //热门界面
            if (mKeyword == null && mId == null) {

                return HotConstant.GRID_URL_GET;
            }

            //礼物界面
            if (mKeyword == null && mId != null) {

                return "http://api.liwushuo.com/v2/item_subcategories/" + mId + "/items?limit=20&offset=0";
            }
        }

        if (mUrl != null) {

            return mUrl;
        }

        //gridfragment
        if (mHtml != null && mId != null) {
            return "http://api.liwushuo.com/v2%2Fitems%2F" + mId + "%2Frecommend";
        }

        return null;
    }

    @Override
    public void setAdapter() {
        super.setAdapter();


        if (mHtml == null) {
            if (mKeyword != null && mId == null && mUrl == null) {

                mHotAdapter = new HotGridAdapter(getActivity(), mDataList, ComeFrom.SEARCH);
            }

            if (mKeyword == null && mId == null && mUrl == null) {

                mHotAdapter = new HotGridAdapter(getActivity(), mDataList, ComeFrom.HOT);

                mPtrGiv.setMode(PullToRefreshBase.Mode.BOTH);
            }

            if (mUrl != null || mKeyword == null && mId != null) {

                mHotAdapter = new HotGridAdapter(getActivity(), mGiftList, ComeFrom.GIFT);
            }
        }else {

            mHotAdapter = new HotGridAdapter(getActivity(), mDetailList, ComeFrom.DETAIL);
        }

        GridView refreshableView = mPtrGiv.getRefreshableView();

        refreshableView.setNumColumns(NUM_COLUMN);

        refreshableView.setHorizontalSpacing(HORIZONTAL_SPACING);

        refreshableView.setVerticalSpacing(VERTICAL_SPACING);

        if (mHtml != null) {

//            WebView webView = new WebView(getActivity());
//
//            WebSettings webSettings = webView.getSettings();
//
//            webSettings.setJavaScriptEnabled(true);
//
//            webView.loadData(mHtml, "text/html", "UTF-8");
        }

        refreshableView.setAdapter(mHotAdapter);
    }

    @Override
    public void get(String result) {

        if (mHtml == null) {

            if (mKeyword != null && mId == null && mUrl == null) {

                SearchGrid gridCom = JSONObject.parseObject(result, SearchGrid.class);

                if (gridCom != null) {

                    mItemList.addAll(gridCom.getData().getItems());
                }
            }

            if (mKeyword == null && mId == null && mUrl == null) {

                HotGrid hotGrid = JSONObject.parseObject(result, HotGrid.class);

                if (hotGrid != null) {

                    List<HotGrid.HotDataEntity.HotItems> hotItemsList = hotGrid.getData().getItems();

                    for (HotGrid.HotDataEntity.HotItems items : hotItemsList) {

                        mDataList.add(items.getData());
                    }
                }
            }

            if (mUrl != null || mKeyword == null && mId != null) {

                GiftGrid giftGrid = JSONObject.parseObject(result, GiftGrid.class);

                if (giftGrid != null) {

                    mGiftList.addAll(giftGrid.getData().getItems());
                }
            }
        } else {

            CommentGrid commentGrid = JSONObject.parseObject(result, CommentGrid.class);

            if (commentGrid != null) {

                mDetailList.addAll(commentGrid.getData().getRecommend_items());
            }
        }
        mHotAdapter.notifyDataSetChanged();
    }

    @Event(value = R.id.ptregridview_fragment_grid, type = AdapterView.OnItemClickListener.class)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), GridDetailActivity.class);

        if (mHtml == null) {

            if (mKeyword != null && mId == null && mUrl == null) {

                intent.putExtra(ID, mItemList.get(position).getId());
            }

            if (mKeyword == null && mId == null && mUrl == null) {
                //1013058
                Log.i("ssss", "onItemClick: "+ mDataList.get(position).getId());

                intent.putExtra(ID, mDataList.get(position).getId());
            }

            if (mKeyword == null && mId != null) {

                intent.putExtra(ID, mGiftList.get(position).getId());
            }
        }else {

            intent.putExtra(ID,mDetailList.get(position).getId());
        }

        startActivity(intent);
    }
}