package com.tongji.f4.stt.util;

import com.tongji.f4.stt.model.method.MethodLocator;
import com.tongji.f4.stt.model.method.MethodSignature;
import com.tongji.f4.stt.model.testresult.ExcelTestResult;
import com.tongji.f4.stt.model.testsuite.ExcelTestSuite;
import com.tongji.f4.stt.service.GlobalVariableOperator;
import com.tongji.f4.stt.util.testcase.ExcelTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: stt
 * @description: The tool for testing a method
 * @author: saturn
 * @create: 2020/04/02
 **/
public class MethodTester {
    private Object instance;
    private Method method;
    private String[] params;
    private String fileName;
    private GlobalVariableOperator gvo;
    private static Logger LOGGER = LoggerFactory.getLogger(MethodTester.class);

    public static MethodTester create(GlobalVariableOperator gvo, ExcelTestSuite ets) {
        MethodTester methodTester = null;
        String fileName = ets.getExcelFileName();
        MethodLocator ml = ets.getMethodLocator();
        String jarName = ml.getJarName();
        String className = ml.getClassName();
        MethodSignature ms = ml.getMethodSignature();
        String methodName = ms.getMethodName();
        String[] paramType = ms.getParamType();
        try {
            Class testClass = gvo.getJarParser(jarName).getClass(className);
            Constructor c = testClass.getDeclaredConstructor();
            c.setAccessible(true);
            Object instance = c.newInstance();
            Method method = testClass.getMethod(methodName, TypeClassConverter.parseParamClass(paramType));
            method.setAccessible(true);
            methodTester = new MethodTester(instance, method, paramType, fileName, gvo);

        } catch (ClassNotFoundException e) {
            LOGGER.error("No such class named " + className);
        } catch (NoSuchMethodException e){
            LOGGER.error("No such method " + ms.toString() + " in class " + className);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return methodTester;
    }

    private MethodTester(Object instance, Method method, String[] params, String fileName, GlobalVariableOperator gvo){
        this.instance = instance;
        this.method = method;
        this.params = params;
        this.fileName = fileName;
        this.gvo = gvo;
    }

    public ExcelTestResult runTest(){
        Iterator<List<String>> iter = ExcelTestCase.create(gvo, fileName);
        ExcelTestResult testResult = new ExcelTestResult();
        List<String> param;
        int currentRow = 0, passed = 0;
        while (iter != null && iter.hasNext()){
            currentRow++;
            param = iter.next();
            if(param.size() - 1 != params.length){
                testResult.addFailLog("第" + currentRow + "行参数不匹配\n");
                continue;
            }
            Object[] actualParam = tryParseParam(param.subList(0, param.size() - 1));
            if(actualParam == null){
                testResult.addFailLog("第" + currentRow + "行参数类型不正确\n");
                continue;
            }
            try {
                Object returnVal = method.invoke(instance, actualParam);
                Object expectVal = param.get(param.size() - 1);

                if(!expectVal.equals(returnVal)){
                    String log = "第" + currentRow + "行测试用例不通过，预期值为 " + expectVal + ", 实际值为 " + returnVal + "\n";
                    testResult.addFailLog(log);
                }else {
                    passed++;
                }
            } catch (Exception e) {
                testResult.addFailLog("第" + currentRow + "行测试用例执行时发生异常\n");
            }
        }
        testResult.setPassNum(passed);
        DecimalFormat df = new DecimalFormat("0.0");
        testResult.setPercentage(Float.parseFloat(df.format((float)passed / currentRow * 100)));
        return testResult;
    }

    private Object[] tryParseParam(List<String> param){
        Object[] result = new Object[param.size()];
        for(int i = 0; i < result.length; i++){
            try{
                result[i] = tryParseParm(param.get(i), params[i]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

    private Object tryParseParm(String param, String paramType) throws Exception{
        Object o = null;
        try {
            switch (paramType) {
                case "int":
                case "java.lang.Integer":
                    o = Integer.parseInt(param);
                    break;
                case "float":
                case "java.lang.Float":
                    o = Float.parseFloat(param);
                    break;
                case "double":
                case "java.lang.Double":
                    o = Double.parseDouble(param);
                case "java.lang.String":
                    o = param;
                    break;
                case "char":
                case "java.lang.Character":
                    o = param.charAt(0);
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            throw e;
        }
        return o;
    }
}
