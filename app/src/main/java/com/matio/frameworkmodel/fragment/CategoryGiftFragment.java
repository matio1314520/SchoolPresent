package com.matio.frameworkmodel.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.CategoryGiftLeftAdapter;
import com.matio.frameworkmodel.adapter.CategoryGiftRightAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.CategoryGift;
import com.matio.frameworkmodel.common.CategoryConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/2/15.
 */
@ContentView(R.layout.fragment_gift_category)
public class CategoryGiftFragment extends BaseFragment implements HttpUtils.Callback {

    @ViewInject(R.id.listview_left_category_gift)
    private ListView mLeftLV;

    @ViewInject(R.id.listview_right_category_gift)
    private  ListView mRightLv;

    private ArrayList<String> mNameList = new ArrayList<>();  // 左边listview数据源

    private ArrayList<CategoryGift.CategoryGiftData.CategoryGiftCategories> mSubList = new ArrayList<>(); //右边listview数据源

    private CategoryGiftLeftAdapter mLeftAdapter; //左边的listv适配器

    private CategoryGiftRightAdapter mRightAdapter;  //右边的listv适配器

    public static CategoryGiftFragment newInstance() {

        CategoryGiftFragment fragment = new CategoryGiftFragment();

        return fragment;
    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(CategoryConstant.GIFT_GET_URL, this);
    }

    @Override
    public void onOperate() {

    }

    @Override
    public void setAdapter() {
        super.setAdapter();

        mLeftAdapter = new CategoryGiftLeftAdapter(getActivity(), mNameList);

        mLeftLV.setAdapter(mLeftAdapter);

        mRightAdapter = new CategoryGiftRightAdapter(getActivity(), mSubList);

        mRightLv.setAdapter(mRightAdapter);
    }

    @Override
    public void get(String result) {
        CategoryGift.CategoryGiftData giftData = JSONObject.parseObject(result, CategoryGift.class).getData();
        if (giftData != null) {
            for (CategoryGift.CategoryGiftData.CategoryGiftCategories category : giftData.getCategories()) {
                mNameList.add(category.getName());
            }
            mSubList.addAll(giftData.getCategories());
        }
        mLeftAdapter.notifyDataSetChanged();
    }
}
