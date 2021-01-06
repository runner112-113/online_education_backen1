package com.atguigu.edu_service.service.impl;

import com.atguigu.edu_service.entity.EduChapter;
import com.atguigu.edu_service.entity.EduCourse;
import com.atguigu.edu_service.entity.EduCourseDescription;
import com.atguigu.edu_service.entity.EduVideo;
import com.atguigu.edu_service.entity.dto.CourseInfoDto;
import com.atguigu.edu_service.entity.dto.CourseQueryDto;
import com.atguigu.edu_service.entity.vo.CoursePublishVo;
import com.atguigu.edu_service.mapper.EduChapterMapper;
import com.atguigu.edu_service.mapper.EduCourseDescriptionMapper;
import com.atguigu.edu_service.mapper.EduCourseMapper;
import com.atguigu.edu_service.mapper.EduVideoMapper;
import com.atguigu.edu_service.service.IEduChapterService;
import com.atguigu.edu_service.service.IEduCourseService;
import com.atguigu.edu_service.service.IEduVideoService;
import com.atguigu.servicebase.exceptionHandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Autowired
    private IEduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(CourseInfoDto courseInfoDto) {
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

        //返回课程id给前端
        return id;
    }

    @Override
    public CourseInfoDto getCourseInfoByCourseId(String courseId) {
        //查询基本信息
        EduCourse eduCourse = this.getById(courseId);
        CourseInfoDto courseInfoDto = new CourseInfoDto();
        BeanUtils.copyProperties(eduCourse,courseInfoDto);

        //查询描述信息
        EduCourseDescription eduCourseDescription = eduCourseDescriptionMapper.selectById(courseId);
        courseInfoDto.setDescription(eduCourseDescription.getDescription());
        return courseInfoDto;
    }

    @Override
    public String updateCourseInfo(CourseInfoDto courseInfoDto) {
        //更新基本信息表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoDto,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i < 0) {
            throw new GuliException(20001,"课程基本信息更新失败");
        }
        //更新课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoDto,eduCourseDescription);
        int i1 = eduCourseDescriptionMapper.updateById(eduCourseDescription);
        if (i1 < 0) {
            throw new GuliException(20001,"课程描述信息更新失败");
        }

        return courseInfoDto.getId();
    }

    @Override
    public CoursePublishVo getFinalCourseInfoByCourseId(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.getFinalCourseInfoByCourseId(courseId);
        return coursePublishVo;
    }

    @Override
    public boolean updateCourseStatus(String courseId) {
        //更新课程的转态为Normal(已发布)，默认是Draft(未发布)
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");

        int result = baseMapper.updateById(eduCourse);
        return result >0;


    }

    @Override
    public Map<String, Object> getPageCourseInfoCondition(long current, long limit, CourseQueryDto courseQueryDto) {
        Page<EduCourse> page = new Page<EduCourse>(current, limit);

        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();

        //获取筛选的条件
        if(!StringUtils.isEmpty(courseQueryDto.getStatus())) {
            wrapper.eq(EduCourse::getStatus,courseQueryDto.getStatus());
        }
        if(!StringUtils.isEmpty(courseQueryDto.getTitle())) {
            wrapper.eq(EduCourse::getTitle,courseQueryDto.getTitle());
        }
        if(!StringUtils.isEmpty(courseQueryDto.getSubjectParentId())) {
            wrapper.eq(EduCourse::getSubjectParentId,courseQueryDto.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseQueryDto.getSubjectId())) {
            wrapper.eq(EduCourse::getSubjectId,courseQueryDto.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseQueryDto.getTeacherId())) {
            wrapper.eq(EduCourse::getTeacherId,courseQueryDto.getTeacherId());
        }

        wrapper.orderByDesc(EduCourse::getGmtCreate);

        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(page, wrapper);

        long total = eduCourseIPage.getTotal();

        List<EduCourse> records = eduCourseIPage.getRecords();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("list",records);

        return resultMap;
    }

    @Override
    public boolean deleteCourseInfoByCourseId(String courseId) {
        //先删除小节
        eduVideoService.deleteVideoInfo(courseId);
        //删除章节
        LambdaQueryWrapper<EduChapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduChapter::getCourseId,courseId);
        eduChapterMapper.delete(wrapper);
        //删除描述信息
        eduCourseDescriptionMapper.deleteById(courseId);
        //删除课程信息
        int result = baseMapper.deleteById(courseId);
        return result > 0;
    }
}
