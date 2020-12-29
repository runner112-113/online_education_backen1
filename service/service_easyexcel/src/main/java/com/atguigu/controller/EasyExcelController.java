package com.atguigu.controller;

import com.atguigu.commonutils.utils.ResultResponse;
import com.atguigu.service.EasyExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/excel")
@Api(description = "EasyExcel测试模块")
public class EasyExcelController {

    @Autowired
    EasyExcelService easyExcelService;

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel",response = ResultResponse.class)
    public ResultResponse exportDataExcel(HttpServletResponse response) {
        String exportMessages = easyExcelService.exportDataExcel(response);
        return ResultResponse.ok().data("111",exportMessages);
    }


}
