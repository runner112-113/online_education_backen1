package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.EduSubject;
import com.atguigu.edu_service.entity.vo.SubjectVo;
import com.atguigu.edu_service.service.IEduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xuhuasong
 * @since 2020-12-30
 */
@Api(description = "课程分类功能")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private IEduSubjectService eduSubjectService;

    /**
     * 课程添加
     * @param file
     * @return
     */
    @PostMapping("/addSubject")
    @ApiOperation(value = "上传课程分类的Excel文件")
    public ResultResponse addSubjectFromExcelFile(MultipartFile file) {
        eduSubjectService.addSubjectFromExcelFile(file,eduSubjectService);
        return ResultResponse.ok();
    }


    /**
     * 获取所有的一级和二级分类课程，并以树形展示
     * @return
     */
    @GetMapping("/getAllSubjectsForTree")
    @ApiOperation(value = "获取所有的一级和二级分类课程，并以树形展示")
    public ResultResponse getAllSubjectsForTree() {
        List<SubjectVo> subjectVoList = eduSubjectService.getAllSubjectsForTree();
        return ResultResponse.ok().data("list",subjectVoList);
    }

}

