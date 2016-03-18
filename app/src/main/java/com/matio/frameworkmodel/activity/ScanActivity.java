package com.matio.frameworkmodel.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Angel on 2016/3/18.
 */
@ContentView(R.layout.activity_scan)
public class ScanActivity extends BaseActivity {

    @ViewInject(R.id.scan_activity_scan)
    private ImageView mScanImg;

    @Override
    public void onOperate() {

        //设置扫描动画
        setCustomAnimation();
    }

    /**
     * 设置扫描动画
     */
    private void setCustomAnimation() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0f, 1.0f);

        scaleAnimation.setDuration(1500);  //单次时间

        scaleAnimation.setRepeatCount(-1); // 无限循环

        scaleAnimation.setRepeatMode(Animation.RESTART);  //从新开始

        mScanImg.startAnimation(scaleAnimation);  //开启动画
    }

    @Event({R.id.back_activity_scan})
    private void onClicl(View view) {
        switch (view.getId()) {

            case R.id.back_activity_scan:
                finish();
                break;


        }
    }

}
