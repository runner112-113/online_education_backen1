package com.atguigu.edu_service.controller;

import com.atguigu.commonutils.utils.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "登录控制层")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域
public class EduLoginController {

    @PostMapping("/login")
    @ApiOperation(value = "通过用户名，密码登录，获取token")
    public ResultResponse login() {
        return ResultResponse.ok().data("token","admin");
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取登录用户的信息")
    public ResultResponse info(String token) {
        return ResultResponse.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }


}
