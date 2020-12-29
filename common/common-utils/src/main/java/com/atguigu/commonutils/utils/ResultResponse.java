package com.atguigu.commonutils.utils;

import com.atguigu.commonutils.enums.ResultCode;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultResponse {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultResponse() {
    }

    public static ResultResponse ok() {

        ResultResponse r = new ResultResponse();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        return r;
    }

    public static ResultResponse error() {
        ResultResponse r = new ResultResponse();
        r.setSuccess(false);
        r.setCode(ResultCode.FAILURE.getCode());
        r.setMessage(ResultCode.FAILURE.getMsg());
        return r;
    }

    public ResultResponse success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultResponse message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultResponse code(int code) {
        this.setCode(code);
        return this;
    }

    public ResultResponse data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultResponse data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
