package com.cm.android.wechatmoments.callBack;

import com.cm.android.wechatmoments.model.User;

/**
 * User callback
 */
public interface UserLoadCallback {
    void onLoadUserInfoSuccess(User user);
    void onLoadUserInfoFail();
}
