package com.matio.frameworkmodel.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.fragment.GridFragment;
import com.matio.frameworkmodel.utils.Sort;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Angel on 2016/3/24.
 * <p/>
 * CategoryGiftRightAdapter--->
 */
@ContentView(R.layout.activity_which)
public class WhichActivity extends BaseActivity {

    public static final String HEADER_URL_GET = "http://api.liwushuo.com/v2/item_subcategories/";

    public static final String FOOTER_URL_GET = "/items?limit=20&offset=0";

    @ViewInject(R.id.toolbar_activity_which)
    private Toolbar mWhichToolbar;

    private int mId;

    public static final String ID = "id";

    GridFragment gridFragment;

    @Override
    public void onOperate() {

        setSupportActionBar(mWhichToolbar);

        if (getIntent() != null) {
            mId = getIntent().getIntExtra(ID, 0);

            gridFragment = GridFragment.newInstance(null, mId + "", null,null);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_activity_which, gridFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_which, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = null;

        switch (item.getItemId()) {

            case R.id.default_activity_which:

                //http://api.liwushuo.com/v2/item_subcategories/7/items?limit=20&offset=0
                url = getUrl(Sort.DEFAULT);
                break;

            case R.id.hot_activity_which:

                //http://api.liwushuo.com/v2/item_subcategories/7/items?limit=20&offset=0&sort=hot
                url = getUrl(Sort.HOT);
                break;

            case R.id.price_up_activity_which:

                //http://api.liwushuo.com/v2/item_subcategories/7/items?limit=20&offset=0&sort=price%3Aasc
                url = getUrl(Sort.ASC);
                break;

            case R.id.price_down_acivity_which:

                //http://api.liwushuo.com/v2/item_subcategories/7/items?limit=20&offset=0&sort=price%3Adesc
                url = getUrl(Sort.DESC);
                break;
        }

        gridFragment = GridFragment.newInstance(null, null, url,null);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_activity_which, gridFragment)
                .commit();

        return true;
    }


    private String getUrl(Sort sort) {
        String url = HEADER_URL_GET + mId + FOOTER_URL_GET;

        if (Sort.DEFAULT.equals(sort)) {
            return url;
        }

        if (Sort.HOT.equals(sort)) {
            return url + "&sort=hot";
        }

        if (Sort.ASC.equals(sort)) {
            return url + "&sort=price%3Aasc";
        }
        if (Sort.DESC.equals(sort)) {
            return url + "&sort=price%3Adesc";
        }

        return null;
    }

}