package com.tongji.f4.stt.util;

import java.util.HashMap;

/**
 * @program: stt
 * @description: The converting tool between type name and class
 * @author: saturn
 * @create: 2020/04/03
 **/
public class TypeClassConverter {
    private static HashMap<String, Class> typeClassMap;
    static {
        typeClassMap = new HashMap<>();
        String[] types = new String[]{"int", "java.lang.Integer", "float", "java.lang.Float", "double", "java.lang.Double", "java.lang.String", "char", "java.lang.Character"};
        Class[] classes = new Class[]{int.class, Integer.class, float.class, Float.class, double.class, Double.class, String.class, char.class, Character.class};
        if(types.length == classes.length){
            for(int i = 0; i < types.length; i++){
                typeClassMap.put(types[i], classes[i]);
            }
        }
    }

    public static Class[] parseParamClass(String[] paramType){
        Class[] paramClass = new Class[paramType.length];
        for(int i = 0; i < paramType.length; i++){
            paramClass[i] = typeClassMap.get(paramType[i]);
        }
        return paramClass;
    }
}
