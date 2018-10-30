package com.kof.fruit.a;



import java.lang.reflect.Proxy;

public class Invoker  {

    public Invoker(){};

    private MethodProxy invocationHandler;

    public MethodProxy getInvocationHandler() {
        return invocationHandler;
    }

    public void setInvocationHandler(MethodProxy invocationHandler) {
        this.invocationHandler = invocationHandler;
    }

    public Object getNewProxyInstance() {
        return newProxyInstance;
    }

    public void setNewProxyInstance(Object newProxyInstance) {
        this.newProxyInstance = newProxyInstance;
    }

    private Object newProxyInstance;
    public Object getInstance(Class<?> cls){
        MethodProxy invocationHandler = new MethodProxy();
        setInvocationHandler(invocationHandler);
         newProxyInstance = Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[] { cls},
                 invocationHandler);
        return newProxyInstance;
    }

}
