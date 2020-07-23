package org.rockyJie.v2.plugin;

import org.rockyJie.v2.annotation.Intercepts;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RockeyJie
 * 2020/7/21
 */
public class Plugin implements InvocationHandler {

    private Object target;  //被代理的对象
    private Interceptor interceptor;    //拦截器本身

    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object warp(Object obj, Interceptor interceptor){
        Class clazz = obj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new Plugin(obj, interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Plugin invoke");
        if(interceptor.getClass().isAnnotationPresent(Intercepts.class)){
            if(method.getName().equals(interceptor.getClass().getAnnotation(Intercepts.class).value())){
                return interceptor.intercept(new Invocation(method, target, args));
            }
        }
        return method.invoke(target,method, args);
    }
}
