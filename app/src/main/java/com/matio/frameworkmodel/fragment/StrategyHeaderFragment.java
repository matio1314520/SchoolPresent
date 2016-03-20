package com.matio.frameworkmodel.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.CategoryChildActivity;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.CategorySubject;
import com.matio.frameworkmodel.common.CategoryConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/2/15.
 */
@ContentView(R.layout.fragment_header_strategy_category)
public class StrategyHeaderFragment extends BaseFragment implements HttpUtils.Callback, View.OnClickListener {

    @ViewInject(value = R.id.ad_header_category)
    private TabLayout mSubjectTab;

    private int mId;

    public static StrategyHeaderFragment newInstance() {

        StrategyHeaderFragment fragment = new StrategyHeaderFragment();

        return fragment;
    }

    @Override
    public void onOperate() {

    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(CategoryConstant.RECYCLER_URL_GET,this);
    }

    @Override
    public void get(String result) {
        CategorySubject subject = JSONObject.parseObject(result, CategorySubject.class);

        if (subject != null) {

            List<CategorySubject.CategoryData.CategoryCollections> collections = subject.getData().getCollections();

            //设置TabLayout
            for (int i = 0, size = collections.size(); i < size; i++) {

                ImageView imageView = new ImageView(getActivity());

                mId = collections.get(i).getId();

                imageView.setOnClickListener(this);

                x.image().bind(imageView, collections.get(i).getBanner_image_url());

                mSubjectTab.addTab(mSubjectTab.newTab().setCustomView(imageView));
            }
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), CategoryChildActivity.class);
        intent.putExtra("id", mId);
        startActivity(intent);
    }
}
