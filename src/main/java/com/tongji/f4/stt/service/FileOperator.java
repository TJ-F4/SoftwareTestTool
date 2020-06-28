package com.tongji.f4.stt.service;

import com.tongji.f4.stt.SttApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @program: stt
 * @description: The tool for saving file
 * @author: saturn
 * @create: 2020/04/02
 **/

@Service
public class FileOperator {
    private static Logger LOGGER = LoggerFactory.getLogger(FileOperator.class);

    @Autowired(required = true)
    GlobalVariableOperator gvo;

    public static boolean saveFile(String path,  MultipartFile excelFile){
        try{
            File localFile = new File(path);

            OutputStream os = new FileOutputStream(localFile);

            BufferedInputStream bis = new BufferedInputStream(excelFile.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(os);

            byte[] buf = new byte[1024];
            int read = 0;
            while((read = bis.read(buf)) != -1){
                bos.write(buf, 0, read);
            }
            bos.flush();
            bis.close();
            bos.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveExcelFile(MultipartFile excelFIle){
        String excelName = excelFIle.getOriginalFilename();

        //处理部分浏览器文件名含有路径的问题
        String[] secs = excelName.replaceAll("\\\\","/").split("/");
        if (secs.length > 1){
            excelName = secs[secs.length - 1];
        }
        return saveFile(gvo.getExcelPath(excelName), excelFIle);
    }

    public boolean saveJarFile(MultipartFile jarFile){
        String jarName = jarFile.getOriginalFilename();

        //处理部分浏览器文件名含有路径的问题
        String[] secs = jarName.replaceAll("\\\\","/").split("/");
        if (secs.length > 1){
            jarName = secs[secs.length - 1];
        }


        String path = gvo.getJarPath(jarName);
        File file = new File(path);
        if (file.exists()){
            gvo.removeJarClMapping(jarName);
        }
        return saveFile(path, jarFile);
    }

    public String[] getAllJars(){
        String jarRoot = gvo.getJarDirectory();
        File jarDir = new File(jarRoot);
        return jarDir.list();
    }

    public String[] getAllExcels(){
        String excelRoot = gvo.getExcelDirectory();
        File excelDir = new File(excelRoot);
        return excelDir.list();
    }
}
