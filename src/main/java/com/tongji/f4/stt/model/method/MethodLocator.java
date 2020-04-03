package com.tongji.f4.stt.model.method;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: stt
 * @description: The information needed to locate a method concisely
 * @author: saturn
 * @create: 2020/04/03
 **/
@ApiModel(description = "方法位置")
public class MethodLocator {
    @ApiModelProperty(value = "jar文件名")
    private String jarName;
    @ApiModelProperty(value = "类名")
    private String className;
    @ApiModelProperty(value = "方法签名")
    private MethodSignature methodSignature;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MethodSignature getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(MethodSignature methodSignature) {
        this.methodSignature = methodSignature;
    }
}
