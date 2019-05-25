package com.cm.android.wechatmoments.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Override ListView to show ListView under the NestedScrollView
 */
public class TweetScrollListView extends ListView {
    public TweetScrollListView(Context context) {
        super(context);
    }

    public TweetScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TweetScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}




