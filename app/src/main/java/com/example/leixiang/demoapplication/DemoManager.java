package com.example.leixiang.demoapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lei.xiang on 2017/8/10.
 */

public class DemoManager {

    public <T> T create(final Class<T> service,final Context context){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Log.d("打印===","打印新内容");
                        Toast.makeText(context,"打印",Toast.LENGTH_SHORT).show();
                        return null;
                    }
                });
    }

}
