package com.atguigu.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

public class ExcelDto {
    @ExcelProperty(value = "编号")
    private String number;
    @ExcelProperty(value = "性别")
    private String sex;
    @ExcelProperty(value = "姓名")
    private String name;

    public ExcelDto(String number, String sex, String name) {
        this.number = number;
        this.sex = sex;
        this.name = name;
    }

    public ExcelDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExcelDto{" +
                "number='" + number + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
