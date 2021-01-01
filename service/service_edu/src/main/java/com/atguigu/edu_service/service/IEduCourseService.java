package com.atguigu.edu_service.service;

import com.atguigu.edu_service.entity.EduCourse;
import com.atguigu.edu_service.entity.dto.CourseInfoDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
public interface IEduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoDto courseInfoDto);
}
