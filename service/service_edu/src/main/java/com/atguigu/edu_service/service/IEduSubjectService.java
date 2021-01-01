package com.atguigu.edu_service.service;

import com.atguigu.edu_service.entity.EduSubject;
import com.atguigu.edu_service.entity.vo.SubjectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xuhuasong
 * @since 2020-12-30
 */
public interface IEduSubjectService extends IService<EduSubject> {

    void addSubjectFromExcelFile(MultipartFile file,IEduSubjectService eduSubjectService);

    List<SubjectVo> getAllSubjectsForTree();
}
