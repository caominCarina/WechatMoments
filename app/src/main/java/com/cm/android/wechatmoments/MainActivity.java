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

import com.cm.android.wechatmoments.ForTest.ServiceErrorAndUseTestData;
import com.cm.android.wechatmoments.model.TweetItem;
import com.cm.android.wechatmoments.ui.adapter.TweetItemAdapter;
import com.cm.android.wechatmoments.ui.view.AppBarStateChangeListener;
import com.cm.android.wechatmoments.ui.view.TweetScrollListView;

import java.util.ArrayList;
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
        initViews();
    }

    private void initViews() {
        mTweetListView = (TweetScrollListView) findViewById(R.id.tweet_list);
        mTweetItemData = new ServiceErrorAndUseTestData().initData(mContext,mTweetItemData);
        mTweetItemAdapter = new TweetItemAdapter(mContext,mTweetItemData);
        mTweetListView.setAdapter(mTweetItemAdapter);

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
    private void onBackPress(){
        Log.d(TAG,"Exit the app");
        finish();
    }

}
