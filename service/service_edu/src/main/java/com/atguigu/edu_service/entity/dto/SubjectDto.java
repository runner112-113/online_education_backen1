package com.atguigu.edu_service.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubjectDto {

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "二级分类课程名称")
    private String title;
}
