package com.capton.common.bmob;

import cn.bmob.v3.BmobUser;

/**
 * Created by capton on 2018/1/19.
 */

public class MyBmobUser extends BmobUser {
    private String nick;
    private String face;
    private boolean gender;
    private String birthday;
    private String rongCloudToken;

    public String getRongCloudToken() {
        return rongCloudToken;
    }

    public void setRongCloudToken(String rongCloudToken) {
        this.rongCloudToken = rongCloudToken;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
