package com.kof.fruit.a.factorys;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class FeignClientFallbackFactory implements  Beanfactory, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public <T> T getBean(Class<T> var1) {
        return this.applicationContext.getBean(var1);
    }

    @Override
    public <T> boolean isFactory(Class<T> var1) {
        Map map=this.applicationContext.getBeansOfType(var1);
        return !map.isEmpty();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        BeanFactoryBuilder.registerBeanFactory(this);
    }
}
