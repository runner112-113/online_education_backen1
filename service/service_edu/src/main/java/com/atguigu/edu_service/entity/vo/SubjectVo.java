package com.atguigu.edu_service.entity.vo;

import com.atguigu.edu_service.entity.dto.SubjectDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubjectVo {

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "一级分类课程名称")
    private String title;

    @ApiModelProperty(value = "二级分类的集合")
    private List<SubjectDto> children;
}
