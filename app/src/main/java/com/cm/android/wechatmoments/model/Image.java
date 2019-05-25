package com.cm.android.wechatmoments.model;

/**
 * image of tweets
 */
public class Image {

    private String url;

    private Image(){}
    private Image(String url){
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
