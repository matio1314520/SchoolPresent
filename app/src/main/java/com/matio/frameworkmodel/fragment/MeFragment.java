package com.matio.frameworkmodel.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.LoginActivity;
import com.matio.frameworkmodel.activity.CaptureActivity;
import com.matio.frameworkmodel.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Angel on 2016/3/14.
 */
@ContentView(R.layout.fragment_me)
public class MeFragment extends BaseFragment {

    @ViewInject(R.id.gift_img_mine)
    private ImageView giftImg;

    @ViewInject(R.id.strategy_img_mine)
    private ImageView strategyImg;

    @ViewInject(R.id.content_mine)
    private TextView contentTxt;

    @ViewInject(R.id.personal_fragment_me)
    private ImageView iconImg;

    public static MeFragment newInstance() {

        MeFragment fragment = new MeFragment();

        return fragment;
    }

    @Override
    public void onOperate() {

    }

    @Event(value = {R.id.gift_img_mine, R.id.strategy_img_mine, R.id.personal_fragment_me, R.id.scan_fragment_me})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift_img_mine:

                setTextAndImg();

                break;

            case R.id.strategy_img_mine:

                contentTxt.setText("喜欢的攻略放在这里哦");

                strategyImg.setVisibility(View.VISIBLE);

                giftImg.setVisibility(View.INVISIBLE);

                break;

            case R.id.personal_fragment_me:

                toAnotherPage();

                break;

            case R.id.scan_fragment_me:

                scan();

                break;
        }
    }

    /**
     * 扫描二维码
     */
    private void scan() {
        startActivity(new Intent(getActivity(), CaptureActivity.class));
    }


    /**
     * 页面跳转
     */
    private void toAnotherPage() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 设置图片及文字
     */
    private void setTextAndImg() {
        contentTxt.setText("喜欢的礼物放在这里哦");
        giftImg.setVisibility(View.VISIBLE);
        strategyImg.setVisibility(View.INVISIBLE);
    }
}
