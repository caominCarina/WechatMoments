package com.cm.android.wechatmoments.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import android.widget.Toast;


import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.List;

/**
 * TweetImageAdapter
 */
public class NineImageAdapter extends NineGridImageViewAdapter<String> {
    @Override
    protected void onDisplayImage(Context context, ImageView imageView, String url) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }

    @Override
    protected void onItemImageClick(Context context, int index, List list) {
        super.onItemImageClick(context, index, list);
        Toast.makeText(context, "" + index, Toast.LENGTH_LONG).show();
    }
}
