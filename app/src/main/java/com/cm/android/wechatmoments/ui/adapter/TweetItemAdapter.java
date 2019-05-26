package com.cm.android.wechatmoments.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cm.android.wechatmoments.R;
import com.cm.android.wechatmoments.model.TweetItem;
import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * TweetItemAdapter
 */
public class TweetItemAdapter extends BaseAdapter {

    private String TAG = "TweetItemAdapter";
    private List<TweetItem> tweets;
    private List<String> urls_list;

    private Context context;
    public TweetItemAdapter() {
    }
    public TweetItemAdapter(Context context) {
        this.context = context;
    }

    public TweetItemAdapter(Context context, List<TweetItem> tweets) {
        this.context = context;
        this.tweets = tweets;
    }
    public void setContext(Context context){
        this.context = context;
    }

    public void setTweets(List<TweetItem> tweets){
        this.tweets = tweets;
    }

    @Override
    public int getCount() {
        if (tweets != null) {
            return tweets.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return tweets.get(position);
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
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tweet_item, null);
            vh.avatarImage = (ImageView) convertView.findViewById(R.id.avatarImage);
            vh.nickText = (TextView) convertView.findViewById(R.id.nickText);
            vh.contentText = (TextView) convertView.findViewById(R.id.contentText);
            vh.imgsGridView = (NineGridImageView) convertView.findViewById(R.id.imgsGridView);
            vh.commentListView = (ListView) convertView.findViewById(R.id.commentListView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        TweetItem tweet = (TweetItem) getItem(position);
        if(tweet.getSender().getNick()!= null){
            Log.d(TAG,"adapter===getNick="+tweet.getSender().getNick());
            vh.nickText.setText(tweet.getSender().getNick());
        }else{
            vh.nickText.setText("Default");
        }
        if(tweet.getContent()!= null){
            vh.contentText.setText(tweet.getContent());
        }else{
            vh.contentText.setVisibility(View.GONE);
        }
        if (tweet.hasImages()) {
            int size = tweet.getImages().size();
            urls_list = new ArrayList<>();
            for(int i = 0;i < size;i++){
                urls_list.add(tweet.getImages().get(i).getUrl());
            }
            vh.imgsGridView.setVisibility(View.VISIBLE);
            vh.imgsGridView.setAdapter(new NineImageAdapter());
            vh.imgsGridView.setImagesData(urls_list);
        } else {
            vh.imgsGridView.setVisibility(View.GONE);
        }
        if (tweet.hasComments()) {
            vh.commentListView.setAdapter(new CommentAdapter(context, tweet.getComments()));
            vh.commentListView.setVisibility(View.VISIBLE);
        } else {
            vh.commentListView.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class ViewHolder {
        private ImageView avatarImage = null;
        private TextView nickText = null;
        private TextView contentText = null;
        private NineGridImageView imgsGridView=null;
        private ListView commentListView=null;
    }


}
