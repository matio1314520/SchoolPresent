package com.matio.frameworkmodel.fragment;

import android.os.Bundle;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseFragment;

import org.xutils.view.annotation.ContentView;

/**
 * Created by Angel on 2016/2/19.
 */
@ContentView(R.layout.fragment_hot)
public class HotFragment extends BaseFragment {

    public static HotFragment newInstance() {

        Bundle args = new Bundle();

        HotFragment fragment = new HotFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onOperate() {


        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment_hot,GridFragment.newInstance())
                .commit();

    }
}
