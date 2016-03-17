package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.bean.GuideRecycler;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class GuideRecyAdapter extends RecyclerView.Adapter<GuideRecyAdapter.ViewHolder> {

    private Context mContext;

    private List<GuideRecycler.DataEntity.SecondaryBannersEntity> mList;

    public GuideRecyAdapter(Context context, List<GuideRecycler.DataEntity.SecondaryBannersEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_guide_header, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mList != null) {
            final GuideRecycler.DataEntity.SecondaryBannersEntity banner = mList.get(position);

            x.image().bind(holder.iconView, banner.getImage_url());

            holder.iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//
//                    Intent intent = new Intent(mContext, StrategyDetailActivity.class);
//
//                    intent.putExtra("url", banner.getWebp_url());
//
//                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @ViewInject(R.id.icon_item_recycler_guide_header)
        public ImageView iconView;


        public ViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);
        }
    }
}
