package com.tongji.f4.stt.controller;

import com.tongji.f4.stt.model.method.MethodSignature;
import com.tongji.f4.stt.service.FileOperator;
import com.tongji.f4.stt.service.GlobalVariableOperator;
import com.tongji.f4.stt.util.JarParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: stt
 * @description: The controller for opering the content of jar file
 * @author: saturn
 * @create: 2020/04/03
 **/

@RestController
@Api(tags = "Jar包操作接口")
@RequestMapping("/jar")
public class JarContentController {

    @Autowired(required = true)
    GlobalVariableOperator gvo;

    @GetMapping("/classes")
    @ApiOperation("获取jar包中的所有类")
    @ApiImplicitParam(name = "jarName", value = "jar包文件名", required = true)
    public List<String> getJarClasses(@RequestParam("jarName") String jarName){
        if(!gvo.isJarLoaded(jarName)) {
            JarParser jp = new JarParser();
            gvo.putJarClMapping(jarName, jp);
            return jp.parseForClassList(gvo.getJarPath(jarName));
        }
        return gvo.getJarParser(jarName).parseForClassList(jarName);
    }

    @GetMapping("/methods")
    @ApiOperation("获取jar包中某类的所有方法")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "jarName", value = "jar包文件名", required = true),
                    @ApiImplicitParam(name = "className", value = "类名", required = true)
            }
    )
    public List<MethodSignature> getJarClasses(@RequestParam("jarName") String jarName,
                                               @RequestParam("className") String className){
        if(!gvo.isJarLoaded(jarName)) {
            JarParser jp = new JarParser();
            gvo.putJarClMapping(jarName, jp);
            return jp.parseForMethodList(gvo.getJarPath(jarName));
        }
        return gvo.getJarParser(jarName).parseForMethodList(className);
    }
}
