package com.cm.android.wechatmoments.ForTest;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.cm.android.wechatmoments.R;
import com.cm.android.wechatmoments.model.Comment;
import com.cm.android.wechatmoments.model.Image;
import com.cm.android.wechatmoments.model.TweetItem;
import com.cm.android.wechatmoments.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * LoadImageUtil
 */
public class ServiceErrorAndUseTestData {

    public List<TweetItem> initData(Context context,List<TweetItem> mTweetItemData){
        mTweetItemData = new LinkedList<TweetItem>();
        User mTestUser1 = new User("user1",null,"null","null");
        User mTestUser2 = new User("user2",null,"null","null");
        User mTestUser3 = new User("user3",null,"null","null");
        User mTestUser4 = new User("user4",null,"null","null");
        User mTestUser5 = new User("user5",null,"null","null");
        User mTestUser6 = new User("user6",null,"null","null");
        User mTestUser7 = new User("user7",null,"null","null");
        User mTestUser8 = new User("user8",null,"null","null");
        User mTestUser9 = new User("user9",null,"null","null");

        Image image1 = new Image("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558850835373&di=c838711a6e44fb1114161d834f2b2a2d&imgtype=0&src=http%3A%2F%2Fpic15.nipic.com%2F20110628%2F1369025_192645024000_2.jpg");
        Uri mUrlLocal1 = getUriFromDrawableRes(context, R.drawable.tweet_image_default);
        Image image2 = new Image(mUrlLocal1.toString());
        Image image3 = new Image(mUrlLocal1.toString());
        Image image4 = new Image(mUrlLocal1.toString());
        Image image5 = new Image(mUrlLocal1.toString());
        Image image6 = new Image(mUrlLocal1.toString());
        List<Image> images = Arrays.asList(image1,image2, image3,image4,image5,image6);
        Comment mTestComment1 = new Comment("This is my first content! welcome to Moments of wechat!",mTestUser9);
        Comment mTestComment2 = new Comment("This is my second content! welcome to Moments of wechat too!",mTestUser8);
        List<Comment> comments = Arrays.asList(mTestComment1,mTestComment2);

        TweetItem mTestTweetItem1 = new TweetItem("contents1",images,mTestUser1,comments);
        TweetItem mTestTweetItem2 = new TweetItem("contents2",null,mTestUser2,null);
        TweetItem mTestTweetItem3 = new TweetItem("contents3",null,mTestUser3,null);
        TweetItem mTestTweetItem4 = new TweetItem("contents4",null,mTestUser4,null);
        TweetItem mTestTweetItem5 = new TweetItem("contents1",null,mTestUser5,null);
        TweetItem mTestTweetItem6 = new TweetItem("contents2",null,mTestUser6,null);
        TweetItem mTestTweetItem7 = new TweetItem("contents3",null,mTestUser7,null);
        TweetItem mTestTweetItem8 = new TweetItem("contents4",null,mTestUser8,null);
        TweetItem mTestTweetItem9 = new TweetItem("contents4",null,mTestUser9,null);
        mTweetItemData.add(mTestTweetItem1);
        mTweetItemData.add(mTestTweetItem2);
        mTweetItemData.add(mTestTweetItem3);
        mTweetItemData.add(mTestTweetItem4);
        mTweetItemData.add(mTestTweetItem5);
        mTweetItemData.add(mTestTweetItem6);
        mTweetItemData.add(mTestTweetItem7);
        mTweetItemData.add(mTestTweetItem8);
        mTweetItemData.add(mTestTweetItem9);
        return mTweetItemData;
    }

    public Uri getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);
    }

}
