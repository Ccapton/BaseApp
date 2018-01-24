package com.capton.common.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by capton on 2018/1/19.
 */

public class BmobUtil {

    /*
       回调说明：
       SaveListener.postFailure(int code, String msg)
         code：
    *  // 304 用户名(手机号)或密码为空
    *  // 202 该用户已被注册
    * */
    private static Context sContext;
    private static  MyBmobUser myBmobUser;

    public static void init(Context context,String appkey){
        Bmob.initialize(context,appkey);
        sContext = context;
    }
    public static MyBmobUser getCurrentUser(){
        myBmobUser = MyBmobUser.getCurrentUser(sContext,MyBmobUser.class);
        return myBmobUser;
    }
    public static void signUp(String phone, String psw, String code, SaveListener saveListener){
        myBmobUser = new MyBmobUser();
        myBmobUser.setUsername(phone);
        myBmobUser.setPassword(psw);
        myBmobUser.signUp(sContext,saveListener);
    }
    public static void login(String phone,String psw,SaveListener saveListener){
        logout();
        myBmobUser = new MyBmobUser();
        myBmobUser.setUsername(phone);
        myBmobUser.setPassword(psw);
        myBmobUser.login(sContext,saveListener);
    }
    public static void logout(){
        BmobUser.logOut(sContext);
        myBmobUser = MyBmobUser.getCurrentUser(sContext,MyBmobUser.class);
        System.out.println("BmobUtil.logout");
    }
    public static void updateFace(String url,UpdateListener updateListener){
        myBmobUser = MyBmobUser.getCurrentUser(sContext,MyBmobUser.class);
         myBmobUser.setFace(url);
         myBmobUser.update(sContext,myBmobUser.getObjectId(),updateListener);
    }
    public static void updateNick(String nick,UpdateListener updateListener){
        myBmobUser = MyBmobUser.getCurrentUser(sContext,MyBmobUser.class);
        myBmobUser.setNick(nick);
        myBmobUser.update(sContext,myBmobUser.getObjectId(),updateListener);
    }
    public static void updatePsw(String newPsw,String code,UpdateListener updateListener){
        myBmobUser = MyBmobUser.getCurrentUser(sContext,MyBmobUser.class);
        myBmobUser.setPassword(newPsw);
        myBmobUser.update(sContext,myBmobUser.getObjectId(),updateListener);

    }
    public static void updateEMail(String E_Mail,UpdateListener updateListener){
        myBmobUser = MyBmobUser.getCurrentUser(sContext,MyBmobUser.class);
        myBmobUser.setEmail(E_Mail);
        myBmobUser.update(sContext,myBmobUser.getObjectId(),updateListener);
    }
    public static void updateMyBmobUser(MyBmobUser myBmobUser, UpdateListener updateListener){
         myBmobUser.update(sContext,updateListener);
    }
}
