package com.matio.frameworkmodel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseAppAdapter;
import com.matio.frameworkmodel.bean.Comment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Angel on 2016/3/24.
 */
public class CommentAdapter extends BaseAppAdapter {

    public CommentAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_listview_fragment_comment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        if (mList != null) {
            Comment.DataEntity.CommentsEntity comment = (Comment.DataEntity.CommentsEntity) mList.get(position);

            if (comment != null) {
                x.image().bind(viewHolder.iconImg, comment.getUser().getAvatar_url());

                viewHolder.nameTxt.setText(comment.getUser().getNickname());

                Comment.DataEntity.CommentsEntity.RepliedUserEntity replied_user = comment.getReplied_user();

                if (replied_user != null){

                    viewHolder.sayTxt.setText("回复"+ replied_user.getNickname()+":"+comment.getContent());
                }else {
                    viewHolder.sayTxt.setText(comment.getContent());
                }
            }
        }
        return convertView;
    }

    private class ViewHolder {

        @ViewInject(R.id.icon_item_listview_fragment_comment)
        public RoundedImageView iconImg;

        @ViewInject(R.id.name_item_listview_fragment_comment)
        public TextView nameTxt;

        @ViewInject(R.id.say_item_listview_fragment_comment)
        public TextView sayTxt;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
