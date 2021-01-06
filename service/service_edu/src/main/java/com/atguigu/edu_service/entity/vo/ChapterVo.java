package com.atguigu.edu_service.entity.vo;

import com.atguigu.edu_service.entity.dto.VideoDto;
import lombok.Data;

import java.util.List;

@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoDto> children;

}
