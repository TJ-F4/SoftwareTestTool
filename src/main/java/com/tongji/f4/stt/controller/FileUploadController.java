package com.tongji.f4.stt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: stt
 * @description: The interface for file upload
 * @author: saturn
 * @create: 2020/04/02
 **/

@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/upload")
public class FileUploadController {
    @GetMapping("/excel")
    @ApiOperation("上传excel文件")
    @ApiImplicitParam(name = "file", value = "excel文件", defaultValue = "hello", required = true)
    public String hello(String file){
        return file + "received";
    }

}
