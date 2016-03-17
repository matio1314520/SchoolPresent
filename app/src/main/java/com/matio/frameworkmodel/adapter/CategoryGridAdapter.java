package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.CategoryStrategy;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/2/12.
 *
 * 分类--》攻略---》listview-->gridview
 */
public class CategoryGridAdapter extends BaseAppAdapter {

    public CategoryGridAdapter(Context context, List list) {
        super(context, list);

    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gridview_strategy_category, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CategoryStrategy.CategoryDataEntity.CategoryChannelGroups.CategoryChannels channels = (CategoryStrategy.CategoryDataEntity.CategoryChannelGroups.CategoryChannels) mList.get(position);

        viewHolder.nameTxt.setText(channels.getName());

        HttpUtils.setImage(viewHolder.iconImg, channels.getIcon_url());

        return convertView;
    }

    class ViewHolder {

        @ViewInject(R.id.icon_gridview_category)
        public ImageView iconImg;

        @ViewInject(R.id.name_gridview_category)
        public TextView nameTxt;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
