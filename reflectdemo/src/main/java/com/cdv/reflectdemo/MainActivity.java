package com.cdv.reflectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReflectClassName();
        getClassMember();

    }

    /**
     * 获取类的成员
     */
    private void getClassMember() {
        //1、获取类的构造函数
        //1.1  获取类的所有构造方法
        TestClassCtor testClass = new TestClassCtor();
        Class tempClass = testClass.getClass();
        String testClassName = tempClass.getName();  // com.cdv.reflectdemo.TestClassCtor
        Log.e(TAG, "testClassName:" + testClassName);

        // 获取类的所有构造方法  如果只是需要获取公有类的构造函数，可以使用getConstructors()
        Constructor[] constructors = tempClass.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            int modifiers = constructors[i].getModifiers();
            // 获取构造方法修饰域
            String s = Modifier.toString(modifiers);
            //输出修饰域和方法名称
            Log.e(TAG, "构造方法名称：" + testClassName + "构造方法修饰域" + s);

            //获取指定构造函数方法参数的集合
            Class[] parameterTypes = constructors[i].getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                //获取参数名称
                String name = parameterTypes[j].getName();
                Log.e(TAG, " 获取到的参数名称：" + name);
            }
        }

        //1.2 获取类的某个构造函数
        try {
            //获取无参数的构造函数
            Constructor constructor = tempClass.getDeclaredConstructor();
            Log.e(TAG, "获取无参数的构造函数：" + Modifier.toString(constructor.getModifiers()));

            //获取又一个参数的构造方法 ，参数类型为int
            Class[] p2 = {int.class};
            Constructor declaredConstructor = tempClass.getDeclaredConstructor(p2);
            Log.e(TAG, "获取又一个参数的构造方法：" + Modifier.toString(declaredConstructor.getModifiers()));

            //获取有两个参数的构造方法
            Class[] p3 = {int.class, String.class};
            Constructor declaredConstructor1 = tempClass.getDeclaredConstructor(p3);
            Log.e(TAG, "获取有两个参数的构造方法：" + Modifier.toString(declaredConstructor1.getModifiers()));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //1.3  调用构造函数
        //通过反射调用构造函数，得到类的实例，
        try {
            //含参数
            Class aClass = Class.forName("com.cdv.reflectdemo.ReflectTest");
            Class[] p4 = {int.class, String.class};
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(p4);
            Object hah = declaredConstructor.newInstance(1, "hah");

            //无参数
            Constructor declaredConstructor1 = aClass.getDeclaredConstructor();
            Object o = declaredConstructor1.newInstance();

            //如果构造函数是无参数的
            Class<?> aClass1 = Class.forName("com.cdv.reflectdemo.ReflectTest");
            Object o1 = aClass1.newInstance();

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //2、获取私有实例方法并调用它
        try {
            Class aClass = Class.forName("com.cdv.reflectdemo.ReflectTest");
            Class[] a = {int.class, String.class};
            Constructor declaredConstructor = aClass.getDeclaredConstructor(a);
            Object obj = declaredConstructor.newInstance();

            // 调用private方法
            Class[] c = {String.class};
            Method doSomething = aClass.getDeclaredMethod("doSomething", c);
            doSomething.setAccessible(true);
            Object arrlist[] = {"hello"};
            Object result = doSomething.invoke(obj, arrlist);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    /**
     * 根据一个字符串得到一个类
     */
    private void getReflectClassName() {

        //1、 通过一个对象，获取到它的类型
        String a = "22222";
        Class aClass = a.getClass();  //输出aClass:class java.lang.String
        Log.e(TAG, "aClass:" + aClass);

        //2、 Class.forName方法
        try {
            Class c1 = Class.forName("java.lang.String"); //class java.lang.String
            Class c2 = Class.forName("android.webkit.WebView");//class android.webkit.WebView
            Class c3 = c2.getSuperclass();// class android.widget.AbsoluteLayout

            Log.e(TAG, "c1:" + c1);
            Log.e(TAG, "c2:" + c2);
            Log.e(TAG, "c3:" + c3);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //3、class属性
        //每一个类都有class属性，可以得到这个类的类型
        Class c4 = String.class; // class java.lang.String
        Class c5 = int.class; // int
        Class c6 = android.app.Activity.class; //class android.app.Activity
        Log.e(TAG, "c4:" + c4);
        Log.e(TAG, "c5:" + c5);
        Log.e(TAG, "c6:" + c6);


        //4、 TYPE属性
        // 基本数据类型都有TYPE属性
        Class c7 = Boolean.TYPE;//boolean
        Class c8 = Void.TYPE;//void
        Log.e(TAG, "c7:" + c7);
        Log.e(TAG, "c8:" + c8);
    }
}