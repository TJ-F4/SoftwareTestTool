package com.tongji.f4.stt.model.testsuite;

import com.tongji.f4.stt.model.method.MethodLocator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: stt
 * @description: The information needed for a method test.
 * @author: saturn
 * @create: 2020/04/03
 **/

@ApiModel(description = "测试套装")
public class ExcelTestSuite {
    @ApiModelProperty(value = "测试用例Excel文件名")
    private String excelFileName;
    @ApiModelProperty(value = "待测方法位置")
    private MethodLocator methodLocator;

    public MethodLocator getMethodLocator() {
        return methodLocator;
    }

    public void setMethodLocator(MethodLocator methodLocator) {
        this.methodLocator = methodLocator;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
}
