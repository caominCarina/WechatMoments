package com.cm.android.wechatmoments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cm.android.wechatmoments.model.Comment;
import com.cm.android.wechatmoments.R;

import java.util.List;

/**
 * Comment Adapter
 */
public class CommentAdapter extends BaseAdapter {

    private List<Comment> comments;

    private Context mContext;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.mContext = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        if (comments != null) {
            return comments.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getCount() < 1) {
            return null;
        }
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            LayoutInflater inflater =
                    (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment_item, null);
            vh.avatarImage = (ImageView) convertView.findViewById(R.id.avatarImage);
            vh.nickText = (TextView) convertView.findViewById(R.id.nickText);
            vh.contentText = (TextView) convertView.findViewById(R.id.contentText);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Comment comment = (Comment) getItem(position);
        vh.nickText.setText(comment.getSender().getNick());
        vh.contentText.setText(comment.getContent());
        return convertView;
    }

    public class ViewHolder {
        private ImageView avatarImage = null;
        private TextView nickText = null;
        private TextView contentText = null;
    }
}
