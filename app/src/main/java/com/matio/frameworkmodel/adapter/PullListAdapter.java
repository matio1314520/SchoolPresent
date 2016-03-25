package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.StrategyDetailActivity;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.GuideList;
import com.matio.frameworkmodel.bean.SearchList;
import com.matio.frameworkmodel.utils.ComeFrom;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class PullListAdapter extends BaseAppAdapter {

    public static final String URL = "url";

    private ComeFrom mComeFrom;

    public PullListAdapter(Context context, List list) {
        super(context, list);

    }

    public PullListAdapter(Context context, List list, ComeFrom comeFrom) {
        this(context, list);
        this.mComeFrom = comeFrom;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_pulltorefreshlistview, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (ComeFrom.SEARCH.equals(mComeFrom)) {
            SearchList.DataEntity.PostsEntity post = (SearchList.DataEntity.PostsEntity) mList.get(position);

            if (post != null) {

                x.image().bind(viewHolder.contentImg, post.getCover_image_url());

                viewHolder.favoriteTxt.setText("" + post.getLikes_count());

                viewHolder.titleTxt.setText(post.getTitle());
            }

        }

        if (ComeFrom.GUIDE.equals(mComeFrom)) {
            if (mList != null) {

                GuideList.DataEntity.ItemsEntity item = (GuideList.DataEntity.ItemsEntity) mList.get(position);

                if (item != null) {

                    x.image().bind(viewHolder.contentImg, item.getCover_image_url());

                    viewHolder.favoriteTxt.setText("" + item.getLikes_count());

                    viewHolder.titleTxt.setText(item.getTitle());
                }
            }
        }

        viewHolder.contentImg.setTag(position);

        return convertView;
    }

    public class ViewHolder {

        @ViewInject(R.id.icon_item_ptrlistview)
        public ImageView contentImg;

        @ViewInject(R.id.name_item_ptrlistview)
        public TextView titleTxt;

        @ViewInject(R.id.favorite_item_ptrlistview)
        public TextView favoriteTxt;

        public ViewHolder(View convertView) {
            x.view().inject(this, convertView);
        }

        @Event(R.id.icon_item_ptrlistview)
        private void onClick(View view) {

            Intent intent = new Intent(mContext, StrategyDetailActivity.class);

            intent.putExtra(URL,  ((GuideList.DataEntity.ItemsEntity)mList.get(Integer.valueOf(view.getTag().toString()))).getUrl());

            mContext.startActivity(intent);
        }
    }
}
