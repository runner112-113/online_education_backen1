package com.atguigu.edu_service.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EduSubjectDto {

    @ApiModelProperty(value = "课程一级分类名称")
    @ExcelProperty(index = 0)
    private String firstSubjectName;

    @ApiModelProperty(value = "课程二级分类名称")
    @ExcelProperty(index = 1)
    private String secondSubjectName;
}
