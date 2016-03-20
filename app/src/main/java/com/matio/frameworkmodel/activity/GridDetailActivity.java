package com.matio.frameworkmodel.activity;

import android.content.Intent;
import android.view.View;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.common.HotConstant;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by Angel on 2016/2/19.
 */
@ContentView(R.layout.activity_girddetail)
public class GridDetailActivity extends BaseActivity implements HttpUtils.Callback {

    @Override
    public void onOperate() {

        requestNetData();
    }

    /**
     * 请求网络
     */
    private void requestNetData() {
        HttpUtils.get(getUrl(), this);

    }

    /**
     * 获取传值
     * @return
     */
    private String getUrl() {

        if (getIntent() != null) {

            int id = getIntent().getIntExtra("id", 0);

            return HotConstant.HEADER_GET_URL + id;
        }

        return null;
    }


    @Event(value = {R.id.back_activity_hotdetail, R.id.share_activity_hotdetail, R.id.favorite_activity_hotdetail, R.id.buy_activity_hotdetail})
    private void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_activity_hotdetail:
                finish();
                break;

            case R.id.share_activity_hotdetail:

                share();

                break;


            case R.id.favorite_activity_hotdetail:

                startActivity(new Intent(this,LoginActivity.class));

                break;


            case R.id.buy_activity_hotdetail:


                break;
        }
    }

    /**
     * 一键分享
     */
    private void share() {


    }

    @Override
    public void get(String result) {

    }
}
