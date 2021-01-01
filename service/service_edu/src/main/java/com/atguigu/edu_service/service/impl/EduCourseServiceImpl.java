package com.atguigu.edu_service.service.impl;

import com.atguigu.edu_service.entity.EduCourse;
import com.atguigu.edu_service.entity.EduCourseDescription;
import com.atguigu.edu_service.entity.dto.CourseInfoDto;
import com.atguigu.edu_service.mapper.EduCourseDescriptionMapper;
import com.atguigu.edu_service.mapper.EduCourseMapper;
import com.atguigu.edu_service.service.IEduCourseService;
import com.atguigu.servicebase.exceptionHandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IEduCourseService {

    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;

    @Override
    public void saveCourseInfo(CourseInfoDto courseInfoDto) {
        //分别要将信息插入到课程的基本信息表和课程描述表中，且要保证两者一对一的关系  主键约束
        //插入课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoDto,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert <= 0) {
            throw new GuliException(20001,"课程信息插入失败");
        }

        //主键会回填，获取基本信息的主键将其设置为课程，描述表的主键id
        String id = eduCourse.getId();

        //插入课程描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(id).setDescription(courseInfoDto.getDescription());
        int insert1 = eduCourseDescriptionMapper.insert(eduCourseDescription);
        if (insert1 <= 0) {
            throw new GuliException(20001,"课程描述信息插入失败");
        }
    }
}
