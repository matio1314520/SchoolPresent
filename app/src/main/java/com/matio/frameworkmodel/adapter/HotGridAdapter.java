package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.GiftGrid;
import com.matio.frameworkmodel.bean.HotGrid;
import com.matio.frameworkmodel.bean.SearchGrid;
import com.matio.frameworkmodel.utils.ComeFrom;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class HotGridAdapter extends BaseAppAdapter {

    private ComeFrom mWhichOne;

    public HotGridAdapter(Context context, List list) {
        super(context, list);
    }

    public HotGridAdapter(Context context, List list, ComeFrom whichOne) {
        this(context, list);
        this.mWhichOne = whichOne;
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
        String imageUrl = null;

        String webUrl = null;

        String price = null;

        String name = null;

        String favorites_count = null;

        if (mList != null) {

            if (ComeFrom.SEARCH.equals(mWhichOne)) {

                SearchGrid.DataEntity.ItemsEntity data = (SearchGrid.DataEntity.ItemsEntity) mList.get(position);

                if (data != null) {

                    webUrl = data.getCover_webp_url();

                    price = data.getPrice();
                    name = data.getName();
                    favorites_count = data.getFavorites_count();
                    imageUrl = data.getCover_image_url();
                }

            }
            if (ComeFrom.HOT.equals(mWhichOne) ||(ComeFrom.DETAIL.equals(mWhichOne))) {
                HotGrid.HotDataEntity.HotItems.HotData data = (HotGrid.HotDataEntity.HotItems.HotData) mList.get(position);
                if (data != null) {

                    webUrl = data.getUrl();

                    price = data.getPrice();
                    name = data.getName();
                    favorites_count = data.getFavorites_count();
                    imageUrl = data.getCover_image_url();
                }

            }
            if (ComeFrom.GIFT.equals(mWhichOne)) {

                GiftGrid.DataEntity.ItemsEntity gift = (GiftGrid.DataEntity.ItemsEntity) mList.get(position);

                if (gift != null) {
                    webUrl = gift.getUrl();

                    price = gift.getPrice();
                    name = gift.getName();
                    favorites_count = gift.getFavorites_count();

                    imageUrl = gift.getCover_image_url();
                }
            }


            viewHolder.priceTxt.setText(price);

            viewHolder.nameTxt.setText(name);

            int fc = Integer.parseInt(favorites_count);

            if (fc < 1000) {

                viewHolder.loveTxt.setText(favorites_count);

            } else {

                viewHolder.loveTxt.setText(fc / 100 / 10 + "." + fc / 100 % 10 + "k");
            }

            x.image().bind(viewHolder.iconImg, imageUrl);

            viewHolder.iconImg.setTag(webUrl);
        }
        return convertView;
    }

    private class ViewHolder {
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


        @Event(value = R.id.icon_item_fragment_hot)
        private void onClick(View view) {
//            Intent intent = new Intent(mContext, StrategyDetailActivity.class);
//
//            Log.i("sssss", "onClick: " + view.getTag().toString());
//
//            intent.putExtra("url", view.getTag().toString());
//
//            mContext.startActivity(intent);
        }
    }
}
