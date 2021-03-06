package com.cm.android.wechatmoments.model;

import android.util.Log;

/**
 * comments of tweets
 */
public class Comment {
    private String TAG = "Comment";
    private String content;
    private User sender;

    private Comment(){}
    private Comment(String content){
        new Comment(content,null);
    }
    public Comment(String content, User sender){
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

    public void printCommentInfo(){
        Log.d(TAG,"content:"+getContent()+
                "\n Sender.user:"+getSender().getUsername()+"Sender.userAvatar:"+getSender().getAvatar()
                +"\n Sender.nick:"+getSender().getNick());
    }
}
