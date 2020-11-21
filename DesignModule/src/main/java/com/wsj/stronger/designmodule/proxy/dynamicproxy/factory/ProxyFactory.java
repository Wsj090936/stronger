package com.wsj.stronger.designmodule.proxy.dynamicproxy.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 生成代理对象的工厂
 *
 * @Author jiahao
 * @Date 2020/11/21 12:17
 */
public class ProxyFactory {

    private ProxyFactory(){


    }

    private static ProxyFactory proxyFactory = new ProxyFactory();

    public static ProxyFactory getInstance(){
        return proxyFactory;
    }

    public <T> T getJdkProxy(T object){
        @SuppressWarnings("unchecked")
        T proxyInstance = (T) Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {


            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我来当你这个店的代理，不过要给我工资");
                Object result = method.invoke(object, args);
                System.out.println("拿到工资了，一共3000元");
                return result;
            }

        });
        return proxyInstance;
    }
}
