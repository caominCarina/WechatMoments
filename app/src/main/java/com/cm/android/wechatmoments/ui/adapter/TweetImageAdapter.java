package com.cm.android.wechatmoments.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cm.android.wechatmoments.R;
import com.cm.android.wechatmoments.model.Image;

import java.util.List;

/**
 * TweetImageAdapter
 */
public class TweetImageAdapter extends BaseAdapter {
    private String TAG = "TweetImageAdapter";
    Context mContext;
    List<Image> images;
    String imgUrls = "";

    public TweetImageAdapter(Context context, List<Image> images) {
        super();
        this.mContext = context;
        this.images = images;
        for (Image image : images) {
            imgUrls += image.getUrl() + ",";
        }
        imgUrls = imgUrls.substring(0, imgUrls.length() - 1);
    }

    @Override
    public int getCount() {
        if (images != null) {
            return images.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            LayoutInflater inflater =
                    (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tweet_image_view_item, null);
            vh.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Image image = (Image)getItem(position);
        vh.imageView.setTag(position);
        vh.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG,"Tweet image click");
            }
        });
        return convertView;
    }

    public class ViewHolder {
        private ImageView imageView=null;
    }
}
