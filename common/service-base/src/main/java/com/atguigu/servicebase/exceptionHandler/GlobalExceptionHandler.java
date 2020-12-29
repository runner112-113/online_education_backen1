package com.atguigu.servicebase.exceptionHandler;

import com.atguigu.commonutils.utils.ResultResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     */

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse exception(Exception e) {
        e.printStackTrace();
        return ResultResponse.error().data("业务处理过程中出现异常","50000");
    }

    /**
     * 特定异常处理
     */

    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody
    public ResultResponse exception(ArithmeticException e) {
        e.printStackTrace();
        return ResultResponse.error().data("出现除零异常","50001");
    }



}
