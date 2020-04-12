package com.tongji.f4.stt.controller;

import com.tongji.f4.stt.service.FileOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: stt
 * @description: The controller for handling file list
 * @author: saturn
 * @create: 2020/04/11
 **/

@RestController
@RequestMapping("/list")
@Api("文件列举API")
public class FileListController {
    @Autowired(required = true)
    FileOperator fo;

    @GetMapping("/jars")
    @ApiOperation("获取已上传的所有jar文件")
    public String[] getJarList(){
        return fo.getAllJars();
    }

    @GetMapping("/excels")
    @ApiOperation("获取已上传的所有excel文件")
    public String[] getExcelList(){
        return fo.getAllExcels();
    }
}
