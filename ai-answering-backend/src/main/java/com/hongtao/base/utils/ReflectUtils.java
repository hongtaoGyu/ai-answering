package com.hongtao.base.utils;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtils extends ReflectUtil {
    public static Object getTypeByClassGeneric(Class clazz, int index) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        return ((ParameterizedType) genericSuperclass).getActualTypeArguments()[index];
    }
}
