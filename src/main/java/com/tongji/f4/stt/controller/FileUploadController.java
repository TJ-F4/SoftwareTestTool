package com.tongji.f4.stt.controller;

import com.tongji.f4.stt.service.FileOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired(required = true)
    FileOperator fo;

    @PostMapping("/excel")
    @ApiOperation("上传excel文件")
    @ApiImplicitParam(name = "file", value = "excel文件", required = true)
    public String uploadExcel(@RequestParam("file") MultipartFile excelFIle){
        if(fo.saveExcelFile(excelFIle)){
            return "Success";
        }else {
            return "Failure";
        }
    }

    @PostMapping("/jar")
    @ApiOperation("上传jar文件")
    @ApiImplicitParam(name = "file", value = "jar文件", required = true)
    public String uploadJar(@RequestParam("file") MultipartFile jarFile){
        if(fo.saveJarFile(jarFile)){
            return "success";
        }else {
            return "failure";
        }
    }
}
