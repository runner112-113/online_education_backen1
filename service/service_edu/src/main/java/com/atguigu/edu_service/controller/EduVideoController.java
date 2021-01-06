package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.EduVideo;
import com.atguigu.edu_service.service.IEduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
@Api(description = "课程小节相关操作")
public class EduVideoController {

    @Autowired
    private IEduVideoService eduVideoService;

    /**
     * 添加课程小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/addVideo")
    @ApiOperation(value = "添加课程小节")
    public ResultResponse addVideo(@RequestBody EduVideo eduVideo) {
        boolean isSuccess = eduVideoService.save(eduVideo);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }

    //TODO：后面需要完善，当删除小节时，要将其中的视频也要删除
    /**
     * 删除课程小节,TODO：后面需要完善，当删除小节时，要将其中的视频也要删除
     * @param videoId
     * @return
     */
    @DeleteMapping("/deleteVideo/{videoId}")
    @ApiOperation(value = "删除课程小节")
    public ResultResponse deleteVideo(@PathVariable String videoId) {
        boolean isSuccess = eduVideoService.removeById(videoId);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }


    @GetMapping("/getVideoById/{videoId}")
    @ApiOperation(value = "根据小节id获取课程小节详情")
    public ResultResponse getVideoById(@PathVariable String videoId) {
        EduVideo videoInfo = eduVideoService.getById(videoId);
        return ResultResponse.ok().data("videoInfo",videoInfo);
    }


    /**
     * 修改课程小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/updateVideo")
    @ApiOperation(value = "修改课程小节")
    public ResultResponse updateVideo(@RequestBody EduVideo eduVideo) {
        boolean isSuccess = eduVideoService.updateById(eduVideo);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }

}

