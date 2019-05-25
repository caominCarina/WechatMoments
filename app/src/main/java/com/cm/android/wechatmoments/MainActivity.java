package com.cm.android.wechatmoments;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.cm.android.wechatmoments.model.TweetItem;
import com.cm.android.wechatmoments.model.User;
import com.cm.android.wechatmoments.ui.adapter.TweetItemAdapter;
import com.cm.android.wechatmoments.ui.view.AppBarStateChangeListener;
import com.cm.android.wechatmoments.ui.view.TweetScrollListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Context mContext;

    private List<TweetItem> mTweetItemData = null;
    private TweetScrollListView mTweetListView;
    private ArrayAdapter<String> mListAdapter;
    List<TweetItem> tweets = new ArrayList<TweetItem>();
    private TweetItemAdapter mTweetItemAdapter= null;

    private FloatingActionButton mFloatingActionButton;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        //getContent();
        initData();
        initViews();
    }

    private void initViews() {
        mTweetListView = (TweetScrollListView) findViewById(R.id.tweet_list);
        mTweetListView.setAdapter(mTweetItemAdapter);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_view);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GRAY);
        mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.page_info));

        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE",state.name());
                if( state == State.EXPANDED ) {
                    //expanded
                    Log.d(TAG,"AppBar expanded");
                }else if(state == State.COLLAPSED){
                    //collapsed
                    Log.d(TAG,"AppBar collapsed");
                }else {
                    //collapsing
                    Log.d(TAG,"AppBar collapsing");
                }
            }
        });
    }
    private void getContent(){
        Log.d(TAG,"get moments content");
    }

    private void onBackPress(){
        Log.d(TAG,"Exit the app");
    }

    /**
     * Test Data
     * by caomin
     */
    private void initData(){
        mTweetItemData = new LinkedList<TweetItem>();
        User mTestUser1 = new User("user1",null,"null","null");
        User mTestUser2 = new User("user2",null,"null","null");
        User mTestUser3 = new User("user3",null,"null","null");
        User mTestUser4 = new User("user4",null,"null","null");
        User mTestUser5 = new User("user1",null,"null","null");
        User mTestUser6 = new User("user2",null,"null","null");
        User mTestUser7 = new User("user3",null,"null","null");
        User mTestUser8 = new User("user4",null,"null","null");
        User mTestUser9 = new User("user4",null,"null","null");
        TweetItem mTestTweetItem1 = new TweetItem("contents1",null,mTestUser1,null);
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
        mTweetItemAdapter = new TweetItemAdapter(mContext,mTweetItemData);
    }
}
