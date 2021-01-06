package com.atguigu.serice_vod.controller;

import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.serice_vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@Api(description = "阿里云视频点播")
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云视频点播
     * @param file
     * @return
     */
    @PostMapping("/uploadVideo")
    @ApiOperation(value = "上传视频到阿里云视频点播")
    public ResultResponse uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return ResultResponse.ok().data("videoId",videoId);
    }

    /**
     * 删除在阿里云视频点播上指定id的视频
     * @param videoId
     * @return
     */
    @DeleteMapping("/deleteVideoOnAliYun/{videoId}")
    @ApiOperation(value = "删除在阿里云视频点播上指定id的视频")
    public ResultResponse deleteAliYunVideo(
            @ApiParam(name = "videoId",value = "云端视频id",required = true)
            @PathVariable String videoId) {
        boolean isSuccess = vodService.deleteVideo(videoId);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }
}
