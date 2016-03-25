package com.matio.frameworkmodel.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.adapter.CommentAdapter;
import com.matio.frameworkmodel.base.BaseFragment;
import com.matio.frameworkmodel.bean.Comment;
import com.matio.frameworkmodel.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/3/24.
 */
@ContentView(R.layout.fragment_comment)
public class CommentFragment extends BaseFragment implements HttpUtils.Callback {

    //http://api.liwushuo.com/v2/items/1013058/comments?limit=20&offset=0
    //next_page_url

    @ViewInject(R.id.listview_fragment_comment)
    private ListView mCommentLv;

    private ArrayList<Comment.DataEntity.CommentsEntity> mCommentList = new ArrayList<>();

    private CommentAdapter mCommentAdapter;

    public static CommentFragment newInstance(String id) {

        Bundle args = new Bundle();

        args.putString("id", id);

        CommentFragment fragment = new CommentFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onOperate() {

    }

    @Override
    public void requestNetData() {
        super.requestNetData();

        HttpUtils.get(getUrl(), this);
    }

    private String getUrl() {

        if (getArguments() != null) {

            Log.i("ssss", " Url: "+"http://api.liwushuo.com/v2/items/" + getArguments().getString("id") + "/comments?limit=20&offset=0");

            return "http://api.liwushuo.com/v2/items/" + getArguments().getString("id") + "/comments?limit=20&offset=0";
        }

        return null;
    }


    @Override
    public void setAdapter() {
        super.setAdapter();

        mCommentAdapter = new CommentAdapter(getActivity(), mCommentList);

        mCommentLv.setAdapter(mCommentAdapter);
    }

    @Override
    public void get(String result) {
        Comment comment = JSONObject.parseObject(result, Comment.class);

        if (comment != null) {

            mCommentList.addAll(comment.getData().getComments());

            mCommentAdapter.notifyDataSetChanged();
        }
    }
}
