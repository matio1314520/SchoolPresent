package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/2/15.
 */
public class CategoryGiftLeftAdapter extends BaseAppAdapter {

    private List<String> list;

    private Context context;

    public CategoryGiftLeftAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_category_gift, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameTxt.setText(list.get(position));

        return convertView;
    }


    class ViewHolder {
        @ViewInject(R.id.name_item_category_gift)
        public TextView nameTxt;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
