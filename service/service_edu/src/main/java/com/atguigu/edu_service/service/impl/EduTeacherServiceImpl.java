package com.atguigu.edu_service.service.impl;

import com.atguigu.edu_service.entity.EduTeacher;
import com.atguigu.edu_service.mapper.EduTeacherMapper;
import com.atguigu.edu_service.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author xuhuasong
 * @since 2020-11-22
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    public EduTeacherMapper eduTeacherMapper;

    @Override
    public EduTeacher queryTeacherDetailById(String id) {
        EduTeacher eduTeacher = eduTeacherMapper.selectById(id);
        return eduTeacher;
    }
}
