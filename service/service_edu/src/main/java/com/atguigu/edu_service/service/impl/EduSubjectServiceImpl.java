package com.atguigu.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu_service.entity.EduSubject;
import com.atguigu.edu_service.entity.dto.EduSubjectDto;
import com.atguigu.edu_service.listener.SubjectEventListener;
import com.atguigu.edu_service.mapper.EduSubjectMapper;
import com.atguigu.edu_service.service.IEduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xuhuasong
 * @since 2020-12-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {

    @Override
    public void addSubjectFromExcelFile(MultipartFile file,IEduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, EduSubjectDto.class,new SubjectEventListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
