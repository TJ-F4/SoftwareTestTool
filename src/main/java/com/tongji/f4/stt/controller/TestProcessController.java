package com.tongji.f4.stt.controller;

import com.tongji.f4.stt.model.method.MethodSignature;
import com.tongji.f4.stt.model.testsuite.ExcelTestSuite;
import com.tongji.f4.stt.service.GlobalVariableOperator;
import com.tongji.f4.stt.util.MethodTester;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: stt
 * @description: The controller for handling test process
 * @author: saturn
 * @create: 2020/04/03
 **/

@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestProcessController {
    @Autowired(required = true)
    GlobalVariableOperator gvo;

    @ApiOperation("运行测试用例")
    @PostMapping("/run")
    public List<String> runTestCase(@RequestBody(required = true) ExcelTestSuite excelTestSuite){
        MethodTester mt = MethodTester.create(gvo, excelTestSuite);
        return mt.runTest();
    }
}
