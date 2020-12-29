package com.atguigu.edu_service.service;

import com.atguigu.edu_service.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xuhuasong
 * @since 2020-11-22
 */
public interface EduTeacherService extends IService<EduTeacher> {

    EduTeacher queryTeacherDetailById(String id);
}
