package com.capton.common.user;

/**
 * Created by capton on 2018/3/4.
 */

public class ResultBean {

    /**
     * code : 0
     * message : 登陆成功
     * data : {"id":1,"username":"13760201342","password":"1125","nick":"CAPTON","gender":"男","age":20,"avater":"223232","introduction":"3323223","token":"akrlza2oa6wvr7xc"}
     */

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
