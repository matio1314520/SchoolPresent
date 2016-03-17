package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.CategoryGift;
import com.matio.frameworkmodel.widget.CustomGridView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/2/15.
 */
public class CategoryGiftRightAdapter extends BaseAppAdapter {


    public CategoryGiftRightAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_listview_right_gift, parent, false);

            viewHoler = new ViewHoler(convertView);

            convertView.setTag(viewHoler);

        } else {
            viewHoler = (ViewHoler) convertView.getTag();
        }

        CategoryGift.CategoryGiftData.CategoryGiftCategories categories = (CategoryGift.CategoryGiftData.CategoryGiftCategories) mList.get(position);

        if (categories != null) {
            viewHoler.nameTxt.setText(categories.getName());
        }

        CategoryGiftGridAdapter adapter = new CategoryGiftGridAdapter(mContext, categories.getSubcategories());

        viewHoler.contentGrid.setAdapter(adapter);

        return convertView;
    }


   private class ViewHoler {

        @ViewInject(R.id.name_item_right_gift)
        public TextView nameTxt;

        @ViewInject(R.id.gridview_item_right_gift)
        public CustomGridView contentGrid;

        public ViewHoler(View view) {
            x.view().inject(this, view);
        }
    }
}
