package com.atguigu.commonutils.enums;


import com.atguigu.commonutils.constans.Constants;

public enum ResultCode{
    /**
     * .请求成功
     */
    SUCCESS(Constants.SUCESS,"请求成功"),
    FAILURE(Constants.FAILURE,"请求失败");

    /**
     * .请求失败
     */

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
