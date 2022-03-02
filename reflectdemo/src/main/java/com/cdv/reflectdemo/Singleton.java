package com.cdv.reflectdemo;

/**
 *  对泛型进行反射
 * @param <T>
 */
public abstract class Singleton<T> {

    private T mInstance;

    protected abstract T mInstance();

    public  final T getmInstance(){
       synchronized (this){
           if(mInstance == null){
               mInstance = mInstance();
           }
           return mInstance;
       }
    }

}
