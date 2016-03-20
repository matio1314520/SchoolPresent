package com.matio.frameworkmodel.activity;

import android.webkit.WebView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Angel on 2016/3/13.
 */
@ContentView(R.layout.activity_strategydetail)
public class StrategyDetailActivity extends BaseActivity {

    //猜你喜欢
    //http://api.liwushuo.com/v2%2Fposts%2F1036255%2Frecommend

    @ViewInject(R.id.webview_activity_strategydetail)
    private WebView mWebView;

    @Override
    public void onOperate() {
        if (getIntent() != null) {
            String url = getIntent().getStringExtra("url");

            //CategoryConstant.HEADER_CATEGORYDETAIL_URL_GET + id
            mWebView.loadUrl(url);
        }
    }
}
