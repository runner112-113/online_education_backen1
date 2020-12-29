package com.atguigu.controller;

import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;

@RestController
@Api(description = "oss相关业务")
@RequestMapping("/oss")
public class OSSController {
    @Autowired
    private OssService ossService;

    @PostMapping("/upLoadFile")
    @ApiOperation(value = "上传文件到oss",response = ResultResponse.class)
    public ResultResponse uploadFile2Oss(@ApiParam(name = "file",value = "文件" ,required = true)
                                                     MultipartFile file) {
        String url = ossService.uploadFile2Oss(file);
        return ResultResponse.ok().data("url",url);
    }

    @PostMapping("/downLoadFile")
    @ApiOperation(value = "下载文件",response = ResultResponse.class)
    public ResultResponse uploadFile2Oss() {
        String url = ossService.downLoadFile2Oss();
        return ResultResponse.ok().data("url",url);
    }
}
