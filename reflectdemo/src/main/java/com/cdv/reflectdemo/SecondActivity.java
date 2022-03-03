package com.cdv.reflectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 根据一个字符串得到一个类
        //       getReflectClass();

        // 获取类成员
        //       getClssMember();

        //对泛型类反射
        //       reflectGener();

        // 调用封装类
        testRefInvoke();

    }

    private void testRefInvoke() {


        try {
            Class getTestClass = Class.forName("com.cdv.reflectdemo.TestClassCtor");
            TestClassCtor object = (TestClassCtor) RefInvoke.createObject(getTestClass);
            String s = object.toString();
            Log.e(TAG, "s-》" + s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //一个参数
        TestClassCtor getTestClass = new TestClassCtor();
        Class paraType1 = int.class;
        Object paraValue1 = 1;
        RefInvoke.createObject(getTestClass.getClass(), paraType1, paraValue1);

        //获取类的方法 并调用
        String methodName = "doSomething";
        Class[] paraType2 = {String.class};
        Object[] paraValue2 = {"test->invokeInstanceMethod"};
        String result = (String) RefInvoke.invokeInstanceMethod(getTestClass, methodName, paraType2, paraValue2);
        Log.e(TAG, "result->" + result);


        // 无参数 私有 静态方法
        String testClassName = "com.cdv.reflectdemo.TestClassCtor";
        String testMethodName = "testStaticMethod";
        String result2 = (String) RefInvoke.invokeStaticMethod(testClassName, testMethodName);
        Log.e(TAG, "result2->" + result2);

        // 有参数  公有  静态方法
        Class paraType = String.class;
        Object paraValue = "有参数  公有  静态方法";
        String result3 = (String) RefInvoke.invokeStaticMethod(testClassName, testMethodName, paraType, paraValue);
        Log.e(TAG, "result3->" + result3);


        //获取属性
        getTestClass.setTestPrivateField("hello");
        String filedName = "testPrivateField";
        String fieldObject = (String) RefInvoke.getFieldObject(getTestClass, filedName);
        Log.e(TAG,"fieldObject-->"+ fieldObject);

        String fieldName = (String)RefInvoke.getFieldObject(testClassName,getTestClass,filedName);
        Log.e(TAG,"fieldName-->"+ fieldName);




    }

    /**
     * 对泛型类反射
     */
    private void reflectGener() {
        try {
            Class aClass = Class.forName("com.cdv.reflectdemo.Singleton");
            Method mInstance = aClass.getDeclaredMethod("mInstance");
            mInstance.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取类成员
     */
    private void getClssMember() {

        //1、获取所有构造函数
        TestClassCtor testClassCtor = new TestClassCtor();
        Class testClassCtorClass = testClassCtor.getClass();
        String name = testClassCtorClass.getName();

        //获取所有的构造函数
        Constructor[] declaredConstructors = testClassCtorClass.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            int modifiers = declaredConstructors[i].getModifiers();
            Log.e(TAG, "构造函数名称:" + name + "构造函数作用域：" + Modifier.toString(modifiers));

            //获取每个构造函数的参数
            Class[] parameterTypes = declaredConstructors[i].getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                String paraName = parameterTypes[j].getName();
                Log.e(TAG, "参数名称：" + paraName);
            }
        }

        //2、获取类的某一个构造函数
        /* 通过一个字符串反射出一个类，然后通过反射获取到类的构造函数，执行构造函数获取到类的实例
         *有了实例，可以通过反射进一步得到实例的所有字段和方法
         */
        try {
            //  获取有一个参数的构造方法 ，类型为int
            Class[] c1 = {int.class};
            Constructor declaredConstructor = testClassCtorClass.getDeclaredConstructor(c1);
            Log.e(TAG, "获取到的构造方法名称：" + declaredConstructor.getName());
            Log.e(TAG, "获取到的构造方法作用域：" + Modifier.toString(declaredConstructor.getModifiers()));

            //获取有两个参数的构造方法
            Class[] c2 = {int.class, String.class};
            Constructor declaredConstructor1 = testClassCtorClass.getDeclaredConstructor(c2);
            Log.e(TAG, "获取到的构造方法名称：" + declaredConstructor1.getName());
            Log.e(TAG, "获取到的构造方法作用域：" + Modifier.toString(declaredConstructor1.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //3、调用构造函数
        try {
            Class getTestClass = Class.forName("com.cdv.reflectdemo.TestClassCtor");
            //含有参数
            Class[] c2 = {int.class, String.class};
            Constructor declaredConstructor = getTestClass.getDeclaredConstructor(c2);
            Object newInstance = declaredConstructor.newInstance(1, "3");

            //无参
            Constructor declaredConstructor1 = getTestClass.getDeclaredConstructor();
            Object newInstance1 = declaredConstructor1.newInstance();

            //如果构造函数是无参的
            Object newInstance2 = getTestClass.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //4 、获取类的私有实例并调用
        try {
            // 调用一个私有方法
            TestClassCtor getTestClass = new TestClassCtor();
            Method doSomething = getTestClass.getClass().getDeclaredMethod("doSomething", String.class);
            doSomething.setAccessible(true);
            doSomething.invoke(getTestClass, "hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 5、获取类的静态私有方法
        try {
            Class getTestClass = Class.forName("com.cdv.reflectdemo.TestClassCtor");
            Method testStaticMethod = getTestClass.getDeclaredMethod("testStaticMethod");
            testStaticMethod.setAccessible(true);
            testStaticMethod.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //6、 获取类的私有字段
        try {
            TestClassCtor getTestClass = new TestClassCtor();
            Field testPrivateField = getTestClass.getClass().getDeclaredField("testPrivateField");
            testPrivateField.setAccessible(true);
            testPrivateField.set(getTestClass, "ceshishuju");

            Log.e(TAG, "获取到的testPrivateField：" + getTestClass.getTestPrivateField());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 7、获取类的静态私有字段并修改

        try {
            TestClassCtor getTestClass = new TestClassCtor();
            Field testStaticPrivateFiled = getTestClass.getClass().getDeclaredField("testStaticPrivateFiled");
            testStaticPrivateFiled.setAccessible(true);
            testStaticPrivateFiled.set(getTestClass, "hahahah");

            Method getTestStaticPrivateFiled = getTestClass.getClass().getDeclaredMethod("getTestStaticPrivateFiled");
            getTestStaticPrivateFiled.setAccessible(true);
            Object invoke = getTestStaticPrivateFiled.invoke(null);
            Log.e(TAG, "获取到的testStaticPrivateFiled：" + invoke.toString());
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据一个字符串得到一个类
     */
    private void getReflectClass() {
        //1、获取一个类
        String a = "abc";
        Class aClass = a.getClass(); // class java.lang.String

        //2、ClassforName


    }
}