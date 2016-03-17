package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.bean.PullList;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Angel on 2016/3/17.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    private Map<String, List<PullList.DataEntity.ItemsEntity>> mItemMap;

    private ArrayList<String> mTilteList;

    private Context mContext;

    private LayoutInflater mInflater;

    public ExpandableAdapter(Context context, ArrayList<String> tilteList, Map<String, List<PullList.DataEntity.ItemsEntity>> itemMap) {
        this.mItemMap = itemMap;
        this.mContext = context;
        this.mTilteList = tilteList;

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        return mItemMap != null ? mItemMap.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (mItemMap != null && mTilteList != null && mTilteList.size() > groupPosition && mItemMap.get(mTilteList.get(groupPosition)) != null) {

            return mItemMap.get(mTilteList.get(groupPosition)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return mTilteList != null ? mTilteList.get(groupPosition) : null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return mItemMap != null ? mItemMap.get(mTilteList.get(groupPosition)) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ParentViewHolder viewHolder = null;

        if (convertView != null) {

            viewHolder = (ParentViewHolder) convertView.getTag();
        } else {

            convertView = mInflater.inflate(R.layout.item_group_expandablelistview, null);

            viewHolder = new ParentViewHolder(convertView);

            convertView.setTag(viewHolder);
        }

        if (mTilteList != null) {

            viewHolder.mTitleTxt.setText(mTilteList.get(groupPosition));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_pulltorefreshlistview, null);

            viewHolder = new ChildViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        if (mItemMap != null && mTilteList != null) {

            PullList.DataEntity.ItemsEntity item =  mItemMap.get(mTilteList.get(groupPosition)).get(childPosition);

            if (item != null) {

                HttpUtils.setImage(viewHolder.contentImg,item.getCover_image_url());

                viewHolder.favoriteTxt.setText("" + item.getLikes_count());

                viewHolder.titleTxt.setText(item.getTitle());
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private class ParentViewHolder {

        @ViewInject(R.id.title_item_group_expandablelistview)
        public TextView mTitleTxt;

        public ParentViewHolder(View view) {

            x.view().inject(this, view);
        }
    }

    private class ChildViewHolder {

        @ViewInject(R.id.icon_item_ptrlistview)
        public ImageView contentImg;

        @ViewInject(R.id.name_item_ptrlistview)
        public TextView titleTxt;

        @ViewInject(R.id.favorite_item_ptrlistview)
        public TextView favoriteTxt;

        public ChildViewHolder(View convertView) {
            x.view().inject(this, convertView);
        }

    }
}
