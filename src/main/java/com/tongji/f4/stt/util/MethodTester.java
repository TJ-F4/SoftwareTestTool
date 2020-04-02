package com.tongji.f4.stt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @program: stt
 * @description: The tool for testing a method
 * @author: saturn
 * @create: 2020/04/02
 **/
public class MethodTester {
    private Method method;
    private static Logger LOGGER = LoggerFactory.getLogger(MethodTester.class);

    public static MethodTester create(String className, String methodName){
        MethodTester methodTester = null;
        try {
            Class testClass = Class.forName(className);
            Method method = testClass.getMethod(methodName);
            methodTester = new MethodTester(method);
        } catch (ClassNotFoundException e) {
            LOGGER.error("No such class named " + className);
        } catch (NoSuchMethodException e){
            LOGGER.error("No such method in class " + className);
        }
        return methodTester;
    }

    private MethodTester(Method method){
        this.method = method;
    }

    public void test(){

    }
}
