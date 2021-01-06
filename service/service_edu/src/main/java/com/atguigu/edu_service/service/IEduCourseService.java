package com.atguigu.edu_service.service;

import com.atguigu.edu_service.entity.EduCourse;
import com.atguigu.edu_service.entity.dto.CourseInfoDto;
import com.atguigu.edu_service.entity.dto.CourseQueryDto;
import com.atguigu.edu_service.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
public interface IEduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoDto courseInfoDto);

    CourseInfoDto getCourseInfoByCourseId(String courseId);

    String updateCourseInfo(CourseInfoDto courseInfoDto);

    CoursePublishVo getFinalCourseInfoByCourseId(String courseId);

    boolean updateCourseStatus(String courseId);

    Map<String, Object> getPageCourseInfoCondition(long current, long limit, CourseQueryDto courseQueryDto);

    boolean deleteCourseInfoByCourseId(String courseId);
}
