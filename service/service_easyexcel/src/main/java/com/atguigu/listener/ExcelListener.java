package com.atguigu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.dto.ExcelDto;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelDto> {

    /**
     * 一行一行的读
     * @param excelDto
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelDto excelDto, AnalysisContext analysisContext) {

    }

    /**
     * 读取标题
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
