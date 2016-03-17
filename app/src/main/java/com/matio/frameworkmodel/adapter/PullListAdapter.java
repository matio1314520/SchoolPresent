package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.PullList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class PullListAdapter extends BaseAppAdapter {

    public PullListAdapter(Context context, List list) {
        super(context, list);
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
        if (mList != null) {

            PullList.DataEntity.ItemsEntity item = (PullList.DataEntity.ItemsEntity) mList.get(position);

            if (item != null) {

                x.image().bind(viewHolder.contentImg, item.getCover_image_url());

                viewHolder.favoriteTxt.setText("" + item.getLikes_count());

                viewHolder.titleTxt.setText(item.getTitle());
            }
        }
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
    }
}
