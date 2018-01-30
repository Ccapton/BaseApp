package com.capton.common.im;

import com.capton.common.bmob.BmobUtil;
import com.capton.common.bmob.MyBmobUser;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

/**
 * Created by capton on 2018/1/25.
 */

public class ImUtil {
    public static void login(String userName,String password,EMCallBack emCallBack){
       // EMClient.getInstance().login(userName,password,emCallBack);
    }
    public static void logout(EMCallBack emCallBack){
       /* if(emCallBack == null)
            EMClient.getInstance().logout(true);
        else
        EMClient.getInstance().logout(false, emCallBack);*/
    }
    public static void addConnectListener(EMConnectionListener connectionListener){
        EMClient.getInstance().addConnectionListener(connectionListener);
    }
    public static void checkImLogin(EMCallBack emCallBack){
        MyBmobUser bmobUser = BmobUtil.getCurrentUser();;
        if(bmobUser != null)
            ImUtil.login(bmobUser.getUsername(),"imdemo",emCallBack);
    }
    public static void setContactListener(EMContactListener emContactListener){
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }

}
