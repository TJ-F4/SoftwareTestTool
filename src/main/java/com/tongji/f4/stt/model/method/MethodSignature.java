package com.tongji.f4.stt.model.method;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: stt
 * @description: The signature of a method
 * @author: saturn
 * @create: 2020/04/02
 **/

@ApiModel(description = "函数签名")
public class MethodSignature {
    @ApiModelProperty(value = "方法名")
    private String methodName;
    @ApiModelProperty(value = "返回类型")
    private String returnType;
    @ApiModelProperty(value = "参数列表")
    private String[] paramType;


    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParamType(String[] paramType) {
        this.paramType = paramType;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String[] getParamType() {
        return paramType;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(returnType).append(" ").append(methodName).append("(");
        for (String param :
                paramType) {
            sb.append(paramType).append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append(")");
        return sb.toString();
    }
}
