package com.kof.fruit.a.factorys;



import java.lang.reflect.Proxy;

public class Invoker  {
    /** todo : 临时的demo实现 */

    public Invoker(){};
    private Object newProxyInstance;
    public Object getInstance(Class<?> cls){
        MethodProxy invocationHandler = new MethodProxy();
         newProxyInstance = Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[] { cls},
                 invocationHandler);
        return newProxyInstance;
    }

}
