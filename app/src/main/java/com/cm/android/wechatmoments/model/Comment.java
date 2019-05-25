package com.cm.android.wechatmoments.model;

/**
 * comments of tweets
 */
public class Comment {

    private String content;
    private User sender;

    private Comment(){}
    private Comment(String content){
        new Comment(content,null);
    }
    private Comment(String content,User sender){
        setContent(content);
        setSender(sender);
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
}
