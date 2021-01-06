package com.atguigu.edu_service.service;

import com.atguigu.edu_service.entity.EduChapter;
import com.atguigu.edu_service.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
public interface IEduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterAndVideoByCourseId(String courseId);

    boolean deleteChapterById(String chapterId);
}
