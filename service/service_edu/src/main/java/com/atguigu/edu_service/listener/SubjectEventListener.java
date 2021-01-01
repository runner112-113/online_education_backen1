package com.atguigu.edu_service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu_service.entity.EduSubject;
import com.atguigu.edu_service.entity.dto.EduSubjectDto;
import com.atguigu.edu_service.service.IEduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Map;

public class SubjectEventListener extends AnalysisEventListener<EduSubjectDto> {

    //由于SubjectEventListener没有交给Spring管理，所以可以通过构造器的方式将EduSubjectService注入

    private IEduSubjectService eduSubjectService;

    public SubjectEventListener() {
    }

    public SubjectEventListener(IEduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 读取标题栏的数据
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    /**
     * 一行一行的读取出标题以外的记录
     * @param eduSubjectDto 读取到的记录
     * @param analysisContext
     */

    @Override
    public void invoke(EduSubjectDto eduSubjectDto, AnalysisContext analysisContext) {

        if (null == eduSubjectDto) {
            throw new RuntimeException("文件内容为空");
        }
        //添加一级分类
        EduSubject eduSubject = this.exitFirstSubject(eduSubjectService, eduSubjectDto.getFirstSubjectName());
        if (null == eduSubject) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0").setTitle(eduSubjectDto.getFirstSubjectName());
            eduSubjectService.save(eduSubject);
        }

        //添加二级分类
        String pid = eduSubject.getId();//获取一级分类的id
        EduSubject eduSubject1 = this.exitSecondSubject(eduSubjectService, eduSubjectDto.getSecondSubjectName(), pid);
        if (null == eduSubject1) {
            eduSubject1 = new EduSubject();
            eduSubject1.setTitle(eduSubjectDto.getSecondSubjectName())
                    .setParentId(pid);
            eduSubjectService.save(eduSubject1);
        }

    }

    /**
     * 判断一级课程是否已经存在了
     * @param eduSubjectService
     * @param firstSubjectName
     * @return
     */
    private EduSubject exitFirstSubject(IEduSubjectService eduSubjectService,String firstSubjectName) {
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getTitle,firstSubjectName)
                .eq(EduSubject::getParentId,0);
        return eduSubjectService.getOne(wrapper);
    }

    /**
     * 判断二级课程是否已经存在了
     * @param eduSubjectService
     * @param secondSubjectName
     * @param pid
     * @return
     */
    private EduSubject exitSecondSubject(IEduSubjectService eduSubjectService,String secondSubjectName,String pid) {
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getTitle,secondSubjectName)
                .eq(EduSubject::getParentId,pid);
        return eduSubjectService.getOne(wrapper);
    }

    /**
     * 数据读取完之后进行的操作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
