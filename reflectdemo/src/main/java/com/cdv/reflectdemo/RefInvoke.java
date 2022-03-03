package com.cdv.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefInvoke {

    /**
     * 无参
     *
     * @param className 类的名称
     * @return 类的实例
     */
    public static Object createObject(String className) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyples, pareVaules);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 无参
     *
     * @param clazz 类的实例
     * @return 类的实例
     */
    public static Object createObject(Class clazz) {
        Class[] pareTyple = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return createObject(clazz, pareTyple, pareVaules);
    }

    /**
     * 一个参数
     *
     * @param className 类的名称
     * @param pareTyple 参数类型
     * @param pareVaule 参数值
     * @return 类的实例
     */
    public static Object createObject(String className, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = new Class[]{pareTyple};
        Object[] pareVaules = new Object[]{pareVaule};

        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyples, pareVaules);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 一个参数
     *
     * @param clazz     类的实例
     * @param pareTyple 参数类型
     * @param pareVaule 参数值
     * @return 类的实例
     */
    public static Object createObject(Class clazz, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = new Class[]{pareTyple};
        Object[] pareVaules = new Object[]{pareVaule};

        return createObject(clazz, pareTyples, pareVaules);
    }

    /**
     * 多个参数
     *
     * @param className  类的名称
     * @param pareTyples 参数类型
     * @param pareVaules 参数值
     * @return 类的实例
     */
    public static Object createObject(String className, Class[] pareTyples, Object[] pareVaules) {
        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyples, pareVaules);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 多个参数
     *
     * @param clazz      类的实例
     * @param pareTyples 参数类型
     * @param pareVaules 参数值
     * @return 类的实例
     */
    public static Object createObject(Class clazz, Class[] pareTyples, Object[] pareVaules) {
        try {
            Constructor ctor = clazz.getDeclaredConstructor(pareTyples);
            ctor.setAccessible(true);
            return ctor.newInstance(pareVaules);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 调用实例方法
     *
     * @param obj        类的实例
     * @param methodName 方法名称
     * @param pareTyples 参数类型
     * @param pareVaules 参数值
     * @return 如果方法有返回值, 返回反射调用后的返回值，如果是void 返回 null
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] pareTyples, Object[] pareVaules) {
        if (obj == null) {
            return null;
        }
        try {
            //调用一个private方法
            Method method = obj.getClass().getDeclaredMethod(methodName, pareTyples); //在指定类中获取指定的方法
            method.setAccessible(true);
            return method.invoke(obj, pareVaules);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 调用实例一个参数的方法
     *
     * @param obj        类的实例
     * @param methodName 方法名称
     * @param pareTyple  方法类型
     * @param pareVaule  方法值
     * @return 如果方法有返回值, 返回反射调用后的返回值，如果是void 返回 null
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = {pareTyple};
        Object[] pareVaules = {pareVaule};

        return invokeInstanceMethod(obj, methodName, pareTyples, pareVaules);
    }

    /**
     * 调用无参的方法
     *
     * @param obj        类的实例
     * @param methodName 方法名称
     * @return 如果方法有返回值, 返回反射调用后的返回值，如果是void 返回 null
     */
    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return invokeInstanceMethod(obj, methodName, pareTyples, pareVaules);
    }


    /**
     * 调用无参数的静态方法
     *
     * @param className   类的名称
     * @param method_name 方法名称
     * @return 方法返回值  如果是void  返回null
     */
    public static Object invokeStaticMethod(String className, String method_name) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return invokeStaticMethod(className, method_name, pareTyples, pareVaules);
    }

    /**
     * 有一个参数的静态方法
     *
     * @param className  类的名称
     * @param method_name  方法名称
     * @param pareTyple  参数类型
     * @param pareVaule  参数值
     * @return 方法返回值  如果是void  返回null
     */
    public static Object invokeStaticMethod(String className, String method_name, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = new Class[]{pareTyple};
        Object[] pareVaules = new Object[]{pareVaule};

        return invokeStaticMethod(className, method_name, pareTyples, pareVaules);
    }

    /**
     * 多个参数的静态方法
     *
     * @param className  类的名称
     * @param method_name  方法名称
     * @param pareTyples  参数类型
     * @param pareVaules  参数值
     * @return 方法返回值  如果是void  返回null
     */
    public static Object invokeStaticMethod(String className, String method_name, Class[] pareTyples, Object[] pareVaules) {
        try {
            Class obj_class = Class.forName(className);
            return invokeStaticMethod(obj_class, method_name, pareTyples, pareVaules);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 无参静态方法
     * @param clazz  类的实例
     * @param method_name  方法名称
     * @return 方法返回值  如果是void  返回null
     */
    public static Object invokeStaticMethod(Class clazz, String method_name) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return invokeStaticMethod(clazz, method_name, pareTyples, pareVaules);
    }

    /**
     * 一个参数
     * @param clazz  类的实例
     * @param method_name  方法名称
     * @param classType  参数类型
     * @param pareVaule  参数值
     * @return 方法返回值  如果是void  返回null
     */
    public static Object invokeStaticMethod(Class clazz, String method_name, Class classType, Object pareVaule) {
        Class[] classTypes = new Class[]{classType};
        Object[] pareVaules = new Object[]{pareVaule};

        return invokeStaticMethod(clazz, method_name, classTypes, pareVaules);
    }

    /**
     * 多个参数
     * @param clazz  类的实例
     * @param method_name  方法名称
     * @param pareTyples 参数类型
     * @param pareVaules  参数值
     * @return 方法返回值  如果是void  返回null
     */
    public static Object invokeStaticMethod(Class clazz, String method_name, Class[] pareTyples, Object[] pareVaules) {
        try {
            Method method = clazz.getDeclaredMethod(method_name, pareTyples);
            method.setAccessible(true);
            return method.invoke(null, pareVaules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取属性的值
     * @param obj  类的实例
     * @param filedName  属性名称
     * @return 属性的值
     */
    public static Object getFieldObject(Object obj, String filedName) {
        return getFieldObject(obj.getClass(), obj, filedName);
    }

    /**
     * 获取属性的值
     * @param className  类的名称
     * @param obj 反射类的对象
     * @param filedName 属性名称
     * @return 属性的值
     */
    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class obj_class = Class.forName(className);
            return getFieldObject(obj_class, obj, filedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字段属性值
     * @param clazz  类的实例
     * @param obj  反射类的对象
     * @param filedName   字段名称
     * @return
     */
    public static Object getFieldObject(Class clazz, Object obj, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(Object obj, String filedName, Object filedVaule) {
        setFieldObject(obj.getClass(), obj, filedName, filedVaule);
    }

    /**
     *
     * @param clazz
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(Class clazz, Object obj, String filedName, Object filedVaule) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedVaule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param className
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(String className, Object obj, String filedName, Object filedVaule) {
        try {
            Class obj_class = Class.forName(className);
            setFieldObject(obj_class, obj, filedName, filedVaule);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Object getStaticFieldObject(String className, String filedName) {
        return getFieldObject(className, null, filedName);
    }

    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }

    public static void setStaticFieldObject(String classname, String filedName, Object filedVaule) {
        setFieldObject(classname, null, filedName, filedVaule);
    }

    public static void setStaticFieldObject(Class clazz, String filedName, Object filedVaule) {
        setFieldObject(clazz, null, filedName, filedVaule);
    }
}