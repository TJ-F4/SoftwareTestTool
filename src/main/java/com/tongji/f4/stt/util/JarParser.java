package com.tongji.f4.stt.util;

import com.tongji.f4.stt.model.method.MethodSignature;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @program: stt
 * @description: The tool for parsing jar file
 * @author: saturn
 * @create: 2020/04/02
 **/
public class JarParser {
    private URLClassLoader ucl;
    private List<String> classList;

    public List<MethodSignature> parseForMethodList(String className){
        if(ucl != null) {
            try {
                Class c = ucl.loadClass(className);
                List<MethodSignature> mss = new ArrayList<>();
                Method[] methods = c.getDeclaredMethods();
                for (Method m:
                     methods) {
                    MethodSignature ms = new MethodSignature();
                    Type[] params = m.getParameterTypes();
                    String[] paramTypes = new String[params.length];
                    for(int i = 0; i < paramTypes.length; i++){
                        paramTypes[i] = params[i].getTypeName();
                    }
                    ms.setMethodName(m.getName());
                    ms.setParamType(paramTypes);
                    ms.setReturnType(m.getReturnType().getName());
                    mss.add(ms);
                }
                return mss;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<String> parseForClassList(String jarFilePath) {
        if(classList != null){
            return classList;
        }

        classList = new ArrayList<>();
        try{
            File f = new File(jarFilePath);
            if (!f.exists()){
                return classList;
            }
            URL url = f.toURI().toURL();
            ucl = new URLClassLoader(new URL[]{url},Thread.currentThread().getContextClassLoader());

            JarFile jar = new JarFile(jarFilePath);
            Enumeration<JarEntry> enumFiles = jar.entries();
            JarEntry entry;
            String classFullName;

            while(enumFiles.hasMoreElements()){
                entry = (JarEntry)enumFiles.nextElement();
                classFullName = entry.getName();
                if(classFullName.endsWith(".class")) {
                    String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                    classList.add(className);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return classList;
    }

    public Class getClass(String className) throws ClassNotFoundException {
        if(ucl != null){
            return ucl.loadClass(className);
        }
        return null;
    }
}
