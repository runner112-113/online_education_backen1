package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.EduCourse;
import com.atguigu.edu_service.entity.dto.CourseInfoDto;
import com.atguigu.edu_service.entity.dto.CourseQueryDto;
import com.atguigu.edu_service.entity.vo.CoursePublishVo;
import com.atguigu.edu_service.service.IEduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 添加课程的基本信息
     * @param courseInfoDto
     * @return
     */
    @PostMapping("/addCourseInfo")
    @ApiOperation(value = "添加课程的基本信息")
    public ResultResponse addCourseInfo(@RequestBody CourseInfoDto courseInfoDto) {
        String courseId = eduCourseService.saveCourseInfo(courseInfoDto);
        return ResultResponse.ok().data("courseId",courseId);
    }


    /**
     * 通过courseId来获取课程的基本信息
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseInfoByCourseId/{courseId}")
    @ApiOperation(value = "通过courseId来获取课程的基本信息")
    public ResultResponse getCourseInfoByCourseId(@PathVariable String courseId) {
        CourseInfoDto courseInfoDto = eduCourseService.getCourseInfoByCourseId(courseId);
        return ResultResponse.ok().data("courseInfo",courseInfoDto);
    }

    /**
     * 更新课程的基本信息
     * @param courseInfoDto
     * @return
     */
    @PostMapping("/updateCourseInfo")
    @ApiOperation(value = "更新课程的基本信息")
    public ResultResponse updateCourseInfo(@RequestBody CourseInfoDto courseInfoDto) {
        String courseId = eduCourseService.updateCourseInfo(courseInfoDto);
        return ResultResponse.ok().data("courseId",courseId);
    }

    /**
     * 发布之前确认课程的信息
     * @param courseId
     * @return
     */
    @GetMapping("/confirmCourseInfo/{courseId}")
    @ApiOperation(value = "确认课程的信息")
    public ResultResponse confirmCourseInfo(@PathVariable String courseId) {
        CoursePublishVo finalCourseInfo = eduCourseService.getFinalCourseInfoByCourseId(courseId);
        return ResultResponse.ok().data("finalCourseInfo", finalCourseInfo);
    }

    /**
     * 更改课程状态为已发布
     * @param courseId
     * @return
     */
    @PutMapping("/publishCourseInfo/{courseId}")
    @ApiOperation(value = "更改课程状态为已发布")
    public ResultResponse publishCourseInfo(@PathVariable String courseId) {
        boolean isSuccess = eduCourseService.updateCourseStatus(courseId);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }

    /**
     * 课程列表的分页查询
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageCourseInfo/{current}/{limit}")
    @ApiOperation(value = "课程列表的分页查询")
    public ResultResponse getAllCourseInfoByPage(@PathVariable("current") long current,
                                                 @PathVariable("limit") long limit) {
        Page<EduCourse> page = new Page<>(current,limit);
        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(EduCourse::getGmtCreate);
        IPage<EduCourse> coursePage = eduCourseService.page(page, wrapper);

        long total = coursePage.getTotal();

        List<EduCourse> coursePageList = coursePage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",coursePageList);

        return ResultResponse.ok().data(map);

    }


    /**
     * 课程列表带条件的分页查询
     * @param current
     * @param limit
     * @param courseQueryDto
     * @return
     */
    @PostMapping("/getPageCourseInfoCondition/{current}/{limit}")
    @ApiOperation(value = "课程列表带条件的分页查询")
    public ResultResponse getPageCourseInfoCondition(@PathVariable("current") long current,
                                                     @PathVariable("limit") long limit,
                                                     @RequestBody(required = false) CourseQueryDto courseQueryDto) {
        Map<String, Object> resultMap = eduCourseService.getPageCourseInfoCondition(current,limit,courseQueryDto);
        return ResultResponse.ok().data(resultMap);
    }

    /**
     * 删除课程信息
     * @param courseId
     * @return
     */
    @DeleteMapping("/deleteCourseInfo/{courseId}")
    @ApiOperation(value = "删除课程信息")
    @Transactional
    public ResultResponse deleteCourseInfo(@PathVariable("courseId") String courseId) {
        boolean isSuccess = eduCourseService.deleteCourseInfoByCourseId(courseId);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }
}

