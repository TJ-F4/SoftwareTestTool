package com.tongji.f4.stt.model;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashSet;

/**
 * @program: stt
 * @description: The signature of a method
 * @author: saturn
 * @create: 2020/04/02
 **/
public class MethodSignature {
    private static HashSet<String> PARAMTYPE;
    private String methodName, returnType;
    private String[] paramClass;


    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParamClass(String[] paramClass) {
        this.paramClass = paramClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String[] getParamClass() {
        return paramClass;
    }
}
