package com.atguigu.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu_service.entity.EduSubject;
import com.atguigu.edu_service.entity.dto.EduSubjectDto;
import com.atguigu.edu_service.entity.dto.SubjectDto;
import com.atguigu.edu_service.entity.vo.SubjectVo;
import com.atguigu.edu_service.listener.SubjectEventListener;
import com.atguigu.edu_service.mapper.EduSubjectMapper;
import com.atguigu.edu_service.service.IEduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SubjectVo> getAllSubjectsForTree() {

        //查询所有的一级课程分类
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getParentId,"0");
        List<EduSubject> eduSubjectsList = baseMapper.selectList(wrapper);
        List<SubjectVo> subjectVoList = new ArrayList<>();
        eduSubjectsList.forEach(x -> {
            SubjectVo subjectVo = new SubjectVo();
            BeanUtils.copyProperties(x,subjectVo);
            subjectVoList.add(subjectVo);
        });
        //遍历每一个一级课程分类，并寻找其二级课程分类
        subjectVoList.forEach(x -> {
            LambdaQueryWrapper<EduSubject> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(EduSubject::getParentId,x.getId());
            List<EduSubject> eduSubjectsList2 = baseMapper.selectList(wrapper1);
            List<SubjectDto> subjectDtoList = new ArrayList<>();
            eduSubjectsList2.forEach(k -> {
                SubjectDto subjectDto = new SubjectDto();
                BeanUtils.copyProperties(k,subjectDto);
                subjectDtoList.add(subjectDto);
            });
            x.setChildren(subjectDtoList);
        });
        return subjectVoList;
    }
}
