package com.matio.frameworkmodel.activity;

import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.fragment.GuideFragment;
import com.matio.frameworkmodel.fragment.MeFragment;
import com.matio.frameworkmodel.fragment.HotFragment;
import com.matio.frameworkmodel.fragment.CategoryFragment;
import com.matio.frameworkmodel.utils.FragmentUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements FragmentUtils.OnRgsExtraCheckedChangedListener {

    @ViewInject(R.id.radiogroup_activity_main)
    private RadioGroup mMainRag;   //radiogroup

    @Override
    public void onOperate() {

        ArrayList<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(GuideFragment.newInstance());

        fragmentList.add(HotFragment.newInstance());

        fragmentList.add(CategoryFragment.newInstance());

        fragmentList.add(MeFragment.newInstance());

        new FragmentUtils(getSupportFragmentManager(), fragmentList, R.id.container_activity_main, mMainRag, this);
    }

    @Override
    public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {

    }
}
