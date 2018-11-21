package com.fruit.feign.core;

import java.lang.reflect.Proxy;

public class Invoker {

    public Invoker() {
    }

    private Object newProxyInstance;

    public Object getInstance(Class<?> cls) {
        FeignFallbackimplProxy invocationHandler = new FeignFallbackimplProxy();
        newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
        return newProxyInstance;
    }

}