package com.cm.android.wechatmoments.model;

import android.text.TextUtils;

/**
 * User info
 */
public class User {
    private String username;
    private String nick;
    private String avatar;
    private String profileImage;

    public User(){
    }
    public User(String nick){
        new User(nick,"null","null","null");
    }
    public User(String nick,String username){
        new User(nick,username,"null","null");
    }
    public User(String nick,String username,String avatar ){
        new User(nick,username,avatar,"null");
    }
    public User(String nick,String username,String avatar,String profileImage){
        /*if(username == "null"){
            setUsername(nick);
        }*/
        setUsername(nick);
        setNick(nick);
        setUsername(username);
        setAvatar(avatar);
        setProfileImage(profileImage);
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public boolean isVaildUser(){
        if(TextUtils.isEmpty(nick)){
            return true;
        }
        return false;
    };
}
