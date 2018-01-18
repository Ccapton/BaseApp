package com.capton.common.base;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by capton on 2018/1/18.
 */

public class SnackBarUtil {
    public static Snackbar show(View view,String text,int duration){
        Snackbar snackbar =Snackbar.make(view,text,duration);
        snackbar.show();
        return snackbar;
    }
    public static Snackbar showActionLong(View view, String text, View.OnClickListener onClickListener){
        Snackbar snackbar =Snackbar.make(view,text,Snackbar.LENGTH_LONG);
        snackbar.setAction(text,onClickListener);
        snackbar.show();
        return snackbar;
    }
    public static Snackbar showActionShort(View view, String text, View.OnClickListener onClickListener){
        Snackbar snackbar =Snackbar.make(view,text,Snackbar.LENGTH_SHORT);
        snackbar.setAction(text,onClickListener);
        snackbar.show();
        return snackbar;
    }
    public static Snackbar showActionIndefinite(View view, String text,String action, View.OnClickListener onClickListener){
        Snackbar snackbar =Snackbar.make(view,text,Snackbar.LENGTH_INDEFINITE);
         snackbar.setAction(action,onClickListener);
        snackbar.show();
        return snackbar;
    }
}
