package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.EduSubject;
import com.atguigu.edu_service.service.IEduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultResponse addSubjectFromExcelFile(MultipartFile file) {
        eduSubjectService.addSubjectFromExcelFile(file,eduSubjectService);
        return ResultResponse.ok();
    }

}

