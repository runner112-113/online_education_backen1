package com.atguigu.edu_service.service.impl;

import com.atguigu.edu_service.entity.EduChapter;
import com.atguigu.edu_service.entity.EduVideo;
import com.atguigu.edu_service.entity.dto.VideoDto;
import com.atguigu.edu_service.entity.vo.ChapterVo;
import com.atguigu.edu_service.mapper.EduChapterMapper;
import com.atguigu.edu_service.mapper.EduVideoMapper;
import com.atguigu.edu_service.service.IEduChapterService;
import com.atguigu.servicebase.exceptionHandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javafx.scene.control.Label;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements IEduChapterService {

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterVo> getChapterAndVideoByCourseId(String courseId) {
        //1、根据courseId来获取所有的课程章节Chapter
        LambdaQueryWrapper<EduChapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduChapter::getCourseId,courseId);
        List<EduChapter> eduChaptersList = baseMapper.selectList(wrapper);

        //将得到的EduChapter转化为ChapterVo
        List<ChapterVo> chapterVoList = new ArrayList<>();
        eduChaptersList.forEach(x -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(x,chapterVo);
            chapterVoList.add(chapterVo);
        });

        //2、根据courseId以及章节id即chapterId，来获取小节
        chapterVoList.forEach(x -> {
            LambdaQueryWrapper<EduVideo> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(EduVideo::getChapterId,x.getId())
                    .eq(EduVideo::getCourseId,courseId);
            List<EduVideo> eduVideosList = eduVideoMapper.selectList(wrapper1);
            //转化EduVideo为VideoDto
            List<VideoDto> videoDtoList = new ArrayList<>();
            eduVideosList.forEach(m -> {
                VideoDto videoDto = new VideoDto();
                BeanUtils.copyProperties(m,videoDto);
                videoDtoList.add(videoDto);
            });
            x.setChildren(videoDtoList);
        });
        return chapterVoList;
    }

    @Override
    public boolean deleteChapterById(String chapterId) {
        //当章节的下面有小节的时候是不允许删除的
        //1、该章节下面还有没有小节
        LambdaQueryWrapper<EduVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduVideo::getChapterId,chapterId);
        int videoCount = eduVideoMapper.selectCount(wrapper);
        if (videoCount == 0 ) {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }else {
            throw new GuliException(20001,"该章节下面还小节，不可以被删除哦");
        }
    }
}
