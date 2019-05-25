package com.cm.android.wechatmoments.model;

import android.text.TextUtils;
import java.util.List;

/**
 * tweet info Item
 */
public class TweetItem {

    private String content;
    private List<Image> images;
    private User sender;
    private List<Comment> comments;

    public TweetItem(){
    }
    public TweetItem(String content){
    }
    public TweetItem(String content, List<Image> images){
    }
    public TweetItem(String content, List<Image> images, User user){
        new TweetItem(content,images,user);
    }
    public TweetItem(String content,List<Image> images,User user,List<Comment> comments ){
        this.setContent(content);
        this.setImages(images);
        this.setSender(user);
        this.setComments(comments);
    }

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public boolean valid() {
        if (TextUtils.isEmpty(content) && (images == null || images.size() < 1)) {
            return false;
        }
        return true;
    }
    public boolean hasImages() {
        return images != null && images.size() > 0;
    }

    public boolean hasComments() {
        return comments != null && comments.size() > 0;
    }
}
