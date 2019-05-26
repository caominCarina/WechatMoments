package com.cm.android.wechatmoments.util;

import android.util.Log;

import com.cm.android.wechatmoments.callBack.TweetsLoadCallback;
import com.cm.android.wechatmoments.callBack.UserLoadCallback;
import com.cm.android.wechatmoments.model.TweetItem;
import com.cm.android.wechatmoments.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * GetInfoFromNet
 */
public class GetInfoFromNet {
    private String TAG = "GetInfoFromNet";
    public UserLoadCallback mUserLoadCallback;
    public TweetsLoadCallback mTweetsLoadCallback;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    Request request;

    public GetInfoFromNet(UserLoadCallback userLoadCallback ,TweetsLoadCallback tweetsLoadCallback) {
        this.mUserLoadCallback = userLoadCallback;
        this.mTweetsLoadCallback = tweetsLoadCallback;
    }

    public void getUserInfo() {
        request = new Request.Builder()
                .url("https://thoughtworks-ios.herokuapp.com/user/jsmith")
                .build();
        try {
            run(true,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTweetInfo(){
        request = new Request.Builder()
                .url("https://thoughtworks-ios.herokuapp.com/user/jsmith/tweets")
                .build();
        try {
            run(false,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(boolean userInfo,boolean tweetInfo) throws Exception {
        Log.d(TAG,"request="+request);
        Response response = client.newCall(request).execute();
        Log.d(TAG,"reponse="+response.isSuccessful());
        if (!response.isSuccessful()){
            if(userInfo){
                mUserLoadCallback.onLoadUserInfoFail();
            }else if(tweetInfo){
                mTweetsLoadCallback.queryTweetFail();
            }
            throw new IOException("Unexpected code " + response);
        }else{
            if(userInfo){
                User mUser = gson.fromJson(response.body().charStream(), User.class);
                mUser.printUserInfo();
                mUserLoadCallback.onLoadUserInfoSuccess(mUser);
            }else if(tweetInfo){
                List<TweetItem> mTweetItems = gson.fromJson(response.body().charStream(), new TypeToken<List<TweetItem>>(){}.getType());
                mTweetsLoadCallback.queryTweetSuccess(mTweetItems);
            }

        }
    }
}
