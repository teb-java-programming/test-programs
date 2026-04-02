package com.teb.practice.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public Object getPrivateField(Object target, String fieldName) {

        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.get(target);
        } catch (Exception e) {
            throw new RuntimeException("Error accessing field: " + fieldName, e);
        }
    }

    public Object invokeMethod(Object target, String methodName) {

        try {
            Method method = target.getClass().getMethod(methodName);

            return method.invoke(target);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking method: " + methodName, e);
        }
    }
}
