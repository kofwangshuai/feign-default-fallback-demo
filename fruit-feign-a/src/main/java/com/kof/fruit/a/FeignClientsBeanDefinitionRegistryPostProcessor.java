package com.kof.fruit.a;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FeignClientsBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor{

    public final static Map<String, Object> fallbacks = new HashMap();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        for (String clasz: fallbacks.keySet()){
            GenericBeanDefinition definition = new GenericBeanDefinition();
            try {
                definition.setBeanClass(Thread.currentThread().getContextClassLoader().loadClass(clasz));    //设置类
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addGenericArgumentValue(new MethodProxy<>());
            definition.setConstructorArgumentValues(constructorArgumentValues);
            definition.setScope("prototype");       //设置scope
            definition.setLazyInit(false);          //设置是否懒加载
            definition.setAutowireCandidate(true);  //设置是否可以被其他对象自动注入
            String[] beanDefinitionNames = beanDefinitionRegistry.getBeanDefinitionNames();

            System.out.println("beanDefinitionNames="+beanDefinitionNames.toString());
            String substring = clasz.substring(clasz.lastIndexOf("."),
                    clasz.length());
            beanDefinitionRegistry.registerBeanDefinition(clasz, definition);

            String[] beanDefinitionNames1 = beanDefinitionRegistry.getBeanDefinitionNames();
            for (String beanname : beanDefinitionNames1){
                BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanname);
                String beanClassName = beanDefinition.getBeanClassName();
                if (clasz.equals(beanClassName)){
                    int aa=1;
                }
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        for (String clasz: fallbacks.keySet()){
            Object bean = configurableListableBeanFactory.getBean(clasz);
            bean=bean;
        }

    }



}
