package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.EduTeacher;
import com.atguigu.edu_service.entity.vo.TeacherQuery;
import com.atguigu.edu_service.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xuhuasong
 * @since 2020-11-22
 */
@CrossOrigin
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询所有的讲师
    @ApiOperation(value = "查询所有的讲师")
    @GetMapping("/findAll")
    public ResultResponse findAllTeacher() {
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return ResultResponse.ok().data("items",teacherList);
    }

    //根据id来删除讲师（逻辑删除）
    @ApiOperation(value = "根据id来删除讲师（逻辑删除）")
    @DeleteMapping("/deleteTeacher/{id}")
    public ResultResponse deleteTeacherById(@ApiParam(name = "id",value = "讲师id",required = true)
                                        @PathVariable String id) {
        boolean isSuccess = eduTeacherService.removeById(id);
        if (isSuccess) {
            return ResultResponse.ok();
        }
        return ResultResponse.error();
    }

    /**
     * 讲师的分页查询
     * @param current 当前页
     * @param limit 每页记录的条数
     * @return
     */
    @ApiOperation(value = "讲师的分页查询")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public ResultResponse pageListTeacher(@PathVariable long current,
                                          @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        //底层会将分页的结果封装在teacherPage对象中
        IPage<EduTeacher> teacherIPage = eduTeacherService.page(teacherPage, null);
        //获取总记录数
        long total = teacherIPage.getTotal();
        //获取当前页的记录
        List<EduTeacher> currentRecords = teacherIPage.getRecords();

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("total",total);
        paramsMap.put("currentRecords",currentRecords);
        return ResultResponse.ok().data(paramsMap);
    }

    /**
     * 条件查询带分页的方法
     * @param current 当前页
     * @param limit 每页几条数据
     * @param teacherQuery 条件封装的对象
     * @return
     */
    @ApiOperation(value = "条件查询带分页的方法",response = ResultResponse.class)
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ResultResponse pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                               @RequestBody(required = false) TeacherQuery teacherQuery) {

        //创建page对象
        //current：当前页；limit：每页几条数据
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            //ge:大于等于
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            //le:小于等于
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        eduTeacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return ResultResponse.ok().data("total",total).data("rows",records);

    }

    /**
     * 根据id查询
     * @param id 讲师id
     * @return
     */
    @ApiOperation(value = "通过讲师id来查询讲师信息")
    @GetMapping("/getTeacherDetailById/{id}")
    public ResultResponse getTeacherDetailById(@PathVariable
                                               @ApiParam(name = "id",value = "讲师id编号",required = true)
                                                           String id) {
        EduTeacher eduTeacher = eduTeacherService.queryTeacherDetailById(id);
        int i = 1/0;
        return ResultResponse.ok().data("item",eduTeacher);
    }


    /**
     * 修改讲师信息
     * @param teacher
     * @return
     */
    @ApiOperation(value = "通过讲师id来修改讲师")
    @PostMapping("/updateTeacherById")
    public ResultResponse updateTeacherById(@RequestBody
                                            @ApiParam(name = "teacher",value = "修改后的讲师对象",required = true)
                                                    EduTeacher teacher) {
        boolean isSuccess = eduTeacherService.updateById(teacher);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();

    }

    /**
     * 添加讲师
     * @param teacher
     * @return
     */
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public ResultResponse addTeacher(@RequestBody EduTeacher teacher) {
        boolean isSuccess = eduTeacherService.save(teacher);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();

    }


}

