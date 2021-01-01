package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.dto.CourseInfoDto;
import com.atguigu.edu_service.service.IEduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
@RestController
@RequestMapping("/eduservice/course")
@Api(description = "课程信息相关操作的Controller")
public class EduCourseController {

    @Autowired
    private IEduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    @ApiOperation(value = "添加课程的基本信息")
    public ResultResponse addCourseInfo(@RequestBody CourseInfoDto courseInfoDto) {
        eduCourseService.saveCourseInfo(courseInfoDto);
        return ResultResponse.ok();
    }

}

