package com.cm.android.wechatmoments;

import android.content.Context;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cm.android.wechatmoments.callBack.TweetsLoadCallback;
import com.cm.android.wechatmoments.callBack.UserLoadCallback;
import com.cm.android.wechatmoments.forTest.ServiceErrorAndUseTestData;
import com.cm.android.wechatmoments.model.TweetItem;
import com.cm.android.wechatmoments.model.User;
import com.cm.android.wechatmoments.ui.adapter.TweetItemAdapter;
import com.cm.android.wechatmoments.ui.view.AppBarStateChangeListener;
import com.cm.android.wechatmoments.ui.view.TweetScrollListView;
import com.cm.android.wechatmoments.util.GetInfoFromNet;
import com.cm.android.wechatmoments.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserLoadCallback,TweetsLoadCallback{
    private String TAG = "MainActivity";
    private Context mContext;
    private static final boolean LAUNCH_FOR_TEST = false;

    private List<TweetItem> mTweetItemData = new LinkedList<TweetItem>();
    private TweetScrollListView mTweetListView;
    private TweetItemAdapter mTweetItemAdapter = new TweetItemAdapter();

    private FloatingActionButton mUserAvatar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mProfileImage;
    private TextView mUserNameText;
    private NetworkUtil mNetworkUtil = new NetworkUtil();
    private GetInfoFromNet mGetInfoFromNet;
    private ServiceErrorAndUseTestData mServiceErrorAndUseTestData = new ServiceErrorAndUseTestData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mContext = this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        initViews();
        try {
            getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_view);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mUserAvatar = (FloatingActionButton)findViewById(R.id.user_image);
        mProfileImage = (ImageView)findViewById(R.id.moments_background);
        mUserNameText = (TextView)findViewById(R.id.user_name);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GRAY);
        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d(TAG,"appBarLayout.onStateChanged="+state.name());
                if( state == State.EXPANDED ) {
                    //expanded
                    mUserNameText.setVisibility(View.VISIBLE);
                    refreshTweetLists();
                    Log.d(TAG,"AppBar expanded");
                }else if(state == State.COLLAPSED){
                    //collapsed
                    mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.page_info));
                    Log.d(TAG,"AppBar collapsed");
                }else {
                    //collapsing
                    Log.d(TAG,"AppBar collapsing");
                    mUserNameText.setVisibility(View.GONE);
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });

        mTweetListView = (TweetScrollListView) findViewById(R.id.tweet_list);
        if(mTweetItemData.isEmpty()){
            mTweetItemData = new ServiceErrorAndUseTestData().initData(mContext,mTweetItemData);
        }
       /* Iterator<TweetItem> tweets = mTweetItemData.iterator();
        while(tweets.hasNext()){
            Log.d(TAG,"initView!!!!!print main activity tweetlist!!!!!!");
            TweetItem tweet = tweets.next();
            tweet.printTweetInfo();
        }*/
    }

    private void bindTweetListsAdapter(){
        mTweetItemAdapter.setContext(mContext);
        mTweetItemAdapter.setTweets(mTweetItemData);
        mTweetListView.setAdapter(mTweetItemAdapter);
    }

    private void onBackPress(){
        Log.d(TAG,"Exit the app");
        finish();
    }

    private void getContent() {
        if(mNetworkUtil.isNetworkAvailable(mContext) & !LAUNCH_FOR_TEST || true){
            getInfoFromNet();
        } else{
            getTweetsInfoFromTest(mContext,mTweetItemData);
            getUsersInfoFromTest();
        }
        mTweetItemAdapter = new TweetItemAdapter(mContext,mTweetItemData);
        mTweetListView.setAdapter(mTweetItemAdapter);
    }

    private void getInfoFromNet(){
        if(mGetInfoFromNet == null){
            mGetInfoFromNet = new GetInfoFromNet(this,this);
        }
        mTweetItemData.clear();
        mGetInfoFromNet.getTweetInfo();
        mTweetItemData = mServiceErrorAndUseTestData.getDataFromLocalJson(mContext);
        Iterator<TweetItem> tweets = mTweetItemData.iterator();
        mGetInfoFromNet.getUserInfo();
    }

    private void getTweetsInfoFromTest(Context contex,List<TweetItem> tweetItems){
        mTweetItemData.clear();
        mTweetItemData = mServiceErrorAndUseTestData.initData(mContext,tweetItems);
        bindTweetListsAdapter();
    }

    private void getUsersInfoFromTest(){
        mProfileImage.setImageResource(R.drawable.default_background);
        mUserAvatar.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_avatar));
    }

    private void refreshTweetLists() {
        getContent();
    }

    /**
     * implements UserInfoCallback
     * @Auth cmlimi
     */
    @Override
    public void onLoadUserInfoSuccess(User user) {
        Log.d(TAG,"onLoadUserInfoSuccess!!");
        Log.d("caomin","onLoadUserInfoSuccess--nickname="+user.getNick());
        mUserNameText.setText(user.getNick());
        if(user.getProfileImage() != null){
            Picasso.with(mContext).load(user.getProfileImage()).into(mProfileImage);
        }
        if(user.getAvatar() != null){
            Picasso.with(mContext).load(user.getAvatar()).into(mUserAvatar);
        }
    }

    @Override
    public void onLoadUserInfoFail() {
        Log.e(TAG,"onLoadUserInfoFail");
    }

    /**
     * implements UserInfoCallback
     * @Auth cmlimi
     */
    @Override
    public void onLoadAllTweetSuccess(List<TweetItem> list, int totalCount) {
        Log.d(TAG,"onLoadAllTweetSuccess");
        mTweetItemData = list;
        bindTweetListsAdapter();
    }

    @Override
    public void onLoadAllTweetFail() {
        Log.e(TAG,"onLoadAllTweetFail");
    }

    @Override
    public void queryTweetSuccess(List<TweetItem> list) {
        Log.d(TAG,"queryTweetSuccess");
        mTweetItemData = list;
        bindTweetListsAdapter();
    }

    @Override
    public void queryTweetFail() {
        Log.e(TAG,"queryTweetFail");
    }
}
