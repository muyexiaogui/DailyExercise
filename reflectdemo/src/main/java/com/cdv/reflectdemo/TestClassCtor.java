package com.cdv.reflectdemo;

import android.util.Log;

/**
 *  用于测试的测试类
 */
public class TestClassCtor {
    public static final String TAG = TestClassCtor.class.getSimpleName();

    /**
     * 测试私有字段
     */
    private String testPrivateField;

    /**
     *  测试静态私有字段
     */
    private static String testStaticPrivateFiled;


    /**
     * 测试无参的构造方法
     */
    public TestClassCtor() {
    }

    /**
     * 测试带参数的公有构造方法
     * @param a
     */
    public TestClassCtor(int a){
        Log.e(TAG," a->"+a);

    }

    /**
     * 测试带参数的私有构造方法
     * @param a
     * @param b
     */
     private TestClassCtor(int a,String b){
         Log.e(TAG," b->"+ b);

     }

    /**
     * 测试私有方法
     * @param d
     * @return
     */
     private String doSomething(String d){
         Log.e(TAG,"doSomething"+d);
        return "123"+d;
     }

     public static String testStaticMethod(String para){
         return "testStaticMethod->"+ para;
     }

     private static String  testStaticMethod(){
         Log.e(TAG,"testStaticMethod");
         return "testStaticMethod" ;
     }


     public String printTestStaticPrivateFiled(){
         return testStaticPrivateFiled;
     }

    public static String getTestStaticPrivateFiled() {
        return testStaticPrivateFiled;
    }

    public static void setTestStaticPrivateFiled(String testStaticPrivateFiled) {
        TestClassCtor.testStaticPrivateFiled = testStaticPrivateFiled;
    }

    public String getTestPrivateField() {
        return testPrivateField;
    }

    public void setTestPrivateField(String testPrivateField) {
        this.testPrivateField = testPrivateField;
    }

    @Override
    public String toString() {
        return "TestClassCtor{" +
                "testPrivateField='" + testPrivateField + '\'' +
                '}';
    }
}
