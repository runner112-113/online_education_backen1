package com.atguigu.edu_service.service.impl;

import com.atguigu.edu_service.entity.EduVideo;
import com.atguigu.edu_service.mapper.EduVideoMapper;
import com.atguigu.edu_service.service.IEduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements IEduVideoService {

    @Override
    public void deleteVideoInfo(String courseId) {
        //TODO:删除小节之前，要删除视频
        LambdaQueryWrapper<EduVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduVideo::getCourseId,courseId);
        baseMapper.delete(wrapper);
    }
}
