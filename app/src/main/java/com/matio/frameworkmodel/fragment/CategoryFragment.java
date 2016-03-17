package com.matio.frameworkmodel.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.GuidePagerAdapter;
import com.matio.frameworkmodel.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/2/19.
 */
@ContentView(R.layout.fragment_category)
public class CategoryFragment extends BaseFragment implements ViewPager.OnPageChangeListener  {


    @ViewInject(R.id.viewpager_fragment_category)
    private ViewPager mCategoryVip;

    @ViewInject(R.id.rag_category)
    private RadioGroup mCategoryRag;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    public static CategoryFragment newInstance() {

        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    @Override
    public void onOperate() {

        mFragmentList.add(CategoryStrategyFragment.newInstance());

        mFragmentList.add(CategoryGiftFragment.newInstance());
    }


    @Override
    public void setAdapter() {
        super.setAdapter();

        mCategoryVip.setAdapter(new GuidePagerAdapter(getChildFragmentManager(), mFragmentList));

        mCategoryVip.addOnPageChangeListener(this);
    }

    @Event(value = R.id.rag_category, type = RadioGroup.OnCheckedChangeListener.class)
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.strategy_radiobutton_category) {

            mCategoryVip.setCurrentItem(0);

        } else if (checkedId == R.id.gift_radiobutton_category) {

            mCategoryVip.setCurrentItem(1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        ((RadioButton) mCategoryRag.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    @Event(R.id.search_fragment_guide)
    private void onClick(View view) {
      //  startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}
