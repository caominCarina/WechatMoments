package com.cm.android.wechatmoments.callBack;

import com.cm.android.wechatmoments.model.TweetItem;
import java.util.List;

/**
 * Tweet  callback
 */
public interface TweetsLoadCallback {

    void onLoadAllTweetSuccess(List<TweetItem> list, int totalCount);
    void onLoadAllTweetFail();

    void queryTweetSuccess(List<TweetItem> list);
    void queryTweetFail();
}
