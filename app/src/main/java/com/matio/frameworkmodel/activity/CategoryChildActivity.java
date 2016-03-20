package com.matio.frameworkmodel.activity;

import android.view.View;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.fragment.ListFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by Angel on 2016/3/20.
 */
@ContentView(R.layout.activity_categotychild)
public class CategoryChildActivity extends BaseActivity {

    @Override
    public void onOperate() {

        if (getIntent() != null) {

            int id = getIntent().getIntExtra("id", 0);

            getSupportFragmentManager()

                    .beginTransaction()

                    .add(R.id.container_activity_category_child, ListFragment.newInstance(id, null))

            .commit();
        }
    }

    @Event(value = {R.id.back_activity_categorychild, R.id.show_activity_categorychild})
    private void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_activity_categorychild:
                finish();
                break;

            case R.id.show_activity_categorychild:
                break;
        }
    }
}
