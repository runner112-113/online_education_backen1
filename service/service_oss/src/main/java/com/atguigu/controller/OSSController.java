package com.atguigu.controller;

import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Api(description = "阿里云文件存储管理oss")
@RestController
@RequestMapping("/eduoss/file")
public class OSSController {
    @Autowired
    private OssService ossService;

    /**
     * 头像文件上传到阿里云oss
     * @param file
     * @return
     */
    @ApiOperation(value = "上传头像文件到oss",response = ResultResponse.class)
    @PostMapping("/upload")
    public ResultResponse uploadFile2Oss(@ApiParam(name = "file",value = "文件" ,required = true)
                                                     @RequestParam("file") MultipartFile file) {
        String url = ossService.uploadFileAvatar2Oss(file);
        return ResultResponse.ok().data("url",url);
    }

    @ApiOperation(value = "下载文件",response = ResultResponse.class)
    @PostMapping("/downLoadFile")
    public ResultResponse uploadFile2Oss() {
        String url = ossService.downLoadFile2Oss();
        return ResultResponse.ok().data("url",url);
    }
}
