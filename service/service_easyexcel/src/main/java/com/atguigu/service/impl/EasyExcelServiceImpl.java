package com.atguigu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.atguigu.dto.ExcelDto;
import com.atguigu.service.EasyExcelService;
import com.atguigu.utils.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EasyExcelServiceImpl implements EasyExcelService {
    @Override
    public String exportDataExcel(HttpServletResponse response) {
        //模拟数据库中取数

        List<ExcelDto> dtoList = new ArrayList<>();
        for (int i = 1;i <= 10;i++) {
            ExcelDto excelDto = new ExcelDto();
            excelDto.setName("张三" + i);
            excelDto.setNumber(i + "");
            if (i % 2 == 0) {
                excelDto.setSex("男");
            }else {
                excelDto.setSex("女");
            }
            dtoList.add(excelDto);
        }

        String fileName = "导出测试";
        String sheetName = "人员信息";

        try {
            ExcelUtil.writeExcel(response,dtoList,fileName,sheetName,ExcelDto.class);
            return "导出成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导出过程出现异常";
    }
}
