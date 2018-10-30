package com.kof.fruit.a.factorys;

public interface Beanfactory {

    <T> T getBean(Class<T> var1);

    <T> boolean isFactory(Class<T> var1);
}
