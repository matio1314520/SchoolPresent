package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.HotGrid;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class HotGridAdapter extends BaseAppAdapter {

    public HotGridAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gridview_hot, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mList != null) {

            HotGrid.HotDataEntity.HotItems.HotData data = (HotGrid.HotDataEntity.HotItems.HotData) mList.get(position);
            if (data != null) {
                viewHolder.priceTxt.setText(data.getPrice());

                viewHolder.nameTxt.setText(data.getName());

                String favorites_count = data.getFavorites_count();

                int fc = Integer.parseInt(favorites_count);

                if (fc < 1000) {

                    viewHolder.loveTxt.setText(favorites_count);

                } else {

                    viewHolder.loveTxt.setText(fc / 100 / 10 + "." + fc / 100 % 10 + "k");
                }

                x.image().bind(viewHolder.iconImg, data.getCover_image_url());
            }
        }
        return convertView;
    }

    /**
     *
     */
    class ViewHolder {
        @ViewInject(R.id.icon_item_fragment_hot)
        public ImageView iconImg;

        @ViewInject(R.id.price_item_fragment_hot)
        public TextView priceTxt;

        @ViewInject(R.id.love_item_fragment_hot)
        public TextView loveTxt;

        @ViewInject(R.id.name_item_fragment_hot)
        public TextView nameTxt;

        public ViewHolder(View convertView) {
            x.view().inject(this, convertView);
        }
    }

}
