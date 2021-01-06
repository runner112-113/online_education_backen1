package com.atguigu.edu_service.controller;


import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.edu_service.entity.EduChapter;
import com.atguigu.edu_service.entity.vo.ChapterVo;
import com.atguigu.edu_service.service.IEduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xuhuasong
 * @since 2021-01-01
 */
@RestController
@RequestMapping("/eduservice/chapter")
@Api(description = "课程章节相关操作")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private IEduChapterService eduChapterService;

    @GetMapping("/getChapterAndVideoByCourseId/{courseId}")
    @ApiOperation(value = "通过课程id来获取该课程的章节和小节")
    public ResultResponse getChapterAndVideoByCourseId(@PathVariable String courseId) {
        List<ChapterVo> chapterVoList = eduChapterService.getChapterAndVideoByCourseId(courseId);
        return ResultResponse.ok().data("list",chapterVoList);
    }

    @PostMapping("/addChapter")
    @ApiOperation(value = "添加章节")
        public ResultResponse addChapter(@RequestBody EduChapter eduChapter) {

        boolean result = eduChapterService.save(eduChapter);
        return result ? ResultResponse.ok() : ResultResponse.error().data("20001","添加章节失败");
    }

    @PostMapping("/updateChapter")
    @ApiOperation(value = "修改章节")
    public ResultResponse updateChapter(@RequestBody EduChapter eduChapter) {

        boolean result = eduChapterService.updateById(eduChapter);
        return result ? ResultResponse.ok() : ResultResponse.error().data("20001","添加章节失败");
    }

    @GetMapping("/getChapterInfoById/{chapterId}")
    @ApiOperation(value = "根据章节id来获取章节的信息")
    public ResultResponse getChapterInfoById(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return ResultResponse.ok().data("chapterInfo",eduChapter);
    }

    @DeleteMapping("/deleteChapter/{chapterId}")
    @ApiOperation(value = "删除章节")
    public ResultResponse deleteChapter(@PathVariable("chapterId") String chapterId) {
        boolean isSuccess = eduChapterService.deleteChapterById(chapterId);
        return isSuccess ? ResultResponse.ok() : ResultResponse.error();
    }


}

