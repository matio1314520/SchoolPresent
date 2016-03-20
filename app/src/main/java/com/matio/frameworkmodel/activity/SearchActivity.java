package com.matio.frameworkmodel.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.bean.SearchHot;
import com.matio.frameworkmodel.common.SearchConstant;
import com.matio.frameworkmodel.fragment.SearchChildFragment;
import com.matio.frameworkmodel.utils.HttpUtils;
import com.matio.frameworkmodel.widget.FlowLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Angel on 2016/3/18.
 */
@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity implements HttpUtils.Callback {

    @ViewInject(R.id.flowlayout_activity_search)
    private FlowLayout mFlowLayout;   //流布局

    @ViewInject(R.id.searchinput_activity_search)
    private EditText mSearchEdt;

    @ViewInject(R.id.lin_activity_search)
    private LinearLayout mSearchLin;

    @Override
    public void onOperate() {

        //请求网络数据
        requestNetData();
    }

    /**
     * 请求网络数据
     */
    private void requestNetData() {

        HttpUtils.get(SearchConstant.HOT_URL_GET, this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void get(String result) {

        SearchHot searchHot = JSONObject.parseObject(result, SearchHot.class);

        if (searchHot != null) {

            for (String hotWord : searchHot.getData().getHot_words()) {

                TextView wordTxt = new TextView(this);

                wordTxt.setText(hotWord);

                wordTxt.setBackground(getResources().getDrawable(R.drawable.hotword_activity_search));

                mFlowLayout.addView(wordTxt);
            }
        }
    }

    @Event({R.id.back_activity_search, R.id.search_activity_search})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_activity_search:
                finish();
                break;
            case R.id.search_activity_search:

                String content = mSearchEdt.getText().toString();

                if (!TextUtils.isEmpty(content)) {

                    mSearchLin.setVisibility(View.GONE);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.container_activity_search, SearchChildFragment.newInstance(content))
                            .commit();
                }
                break;
        }
    }
}
