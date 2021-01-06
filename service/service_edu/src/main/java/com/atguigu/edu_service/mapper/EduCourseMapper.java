package com.atguigu.edu_service.mapper;

import com.atguigu.edu_service.entity.EduCourse;
import com.atguigu.edu_service.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getFinalCourseInfoByCourseId(@Param("courseId") String courseId);
}
