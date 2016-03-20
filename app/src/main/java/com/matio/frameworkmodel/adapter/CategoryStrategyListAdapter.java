package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.activity.CategoryChildActivity;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.CategoryStrategy;
import com.matio.frameworkmodel.widget.CustomGridView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/2/13.
 * <p/>
 * 分类--》攻略---》listview适配器
 */
public class CategoryStrategyListAdapter extends BaseAppAdapter implements AdapterView.OnItemClickListener {


    private CategoryStrategy.CategoryDataEntity.CategoryChannelGroups mGroupList;

    public CategoryStrategyListAdapter(Context context, List list) {
        super(context, list);
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_listivew_strategy_category, parent, false);
            viewHoler = new ViewHoler(convertView);
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHoler) convertView.getTag();
        }

        mGroupList = (CategoryStrategy.CategoryDataEntity.CategoryChannelGroups) mList.get(position);
        if (mGroupList != null) {
            viewHoler.nameTxt.setText(mGroupList.getName());
        }

        CategoryGridAdapter adapter = new CategoryGridAdapter(mContext, mGroupList.getChannels());

        viewHoler.contentGrid.setAdapter(adapter);

        viewHoler.contentGrid.setOnItemClickListener(this);

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int subPosition, long id) {

        Intent intent = new Intent(mContext, CategoryChildActivity.class);

        intent.putExtra("id", mGroupList.getChannels().get(subPosition).getId());

        mContext.startActivity(intent);
    }



    private class ViewHoler {

        @ViewInject(R.id.allname_listview_category)
        public TextView nameTxt;

        @ViewInject(R.id.gridview_category)
        public CustomGridView contentGrid;

        public ViewHoler(View view) {
            x.view().inject(this, view);
        }
    }
}
