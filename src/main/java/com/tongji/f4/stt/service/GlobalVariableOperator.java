package com.tongji.f4.stt.service;

import com.tongji.f4.stt.SttApplication;
import com.tongji.f4.stt.util.JarParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * @program: stt
 * @description: The class which holds some global variables
 * @author: saturn
 * @create: 2020/04/03
 **/

@Service
public class GlobalVariableOperator {
    private HashMap<String, JarParser> jarClMap;
    private String Root, JarDirectory, ExcelDirectory;
    private static Logger LOGGER = LoggerFactory.getLogger(GlobalVariableOperator.class);

    public GlobalVariableOperator() {
        String path = SttApplication.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        Root = new File(path).getParentFile().getAbsolutePath();

        int sep = Root.lastIndexOf("\\file:");
        int sep_linux = Root.lastIndexOf("/file");
        if(sep >= 0 && sep < Root.length()){
            Root = Root.substring(0, sep);
        }else if(sep_linux >= 0 && sep_linux < Root.length()){
            Root = Root.substring(0, sep_linux);
        }

        JarDirectory = Root + File.separator + "jar";
        ExcelDirectory = Root + File.separator + "excel";
        File excelDir = new File(JarDirectory);
        File jarDir = new File(ExcelDirectory);
        if(!excelDir.exists()){
            excelDir.mkdir();
        }
        if(!jarDir.exists()){
            jarDir.mkdir();
        }
        jarClMap = new HashMap<>();
        LOGGER.info("Root is " + Root);
    }

    public String getExcelPath(String fileName){
        return ExcelDirectory + File.separator + fileName;
    }

    public String getJarPath(String jarName){
        return JarDirectory + File.separator + jarName;
    }

    public JarParser getJarParser(String jarName){
        return jarClMap.get(jarName);
    }

    public void putJarClMapping(String jarName, JarParser jp){
        if(!jarClMap.containsKey(jarName)) {
            jarClMap.put(jarName, jp);
        }
    }

    public boolean isJarLoaded(String jarName){
        return jarClMap.containsKey(jarName);
    }

    public String getJarDirectory() {
        return JarDirectory;
    }

    public String getExcelDirectory() {
        return ExcelDirectory;
    }

}
