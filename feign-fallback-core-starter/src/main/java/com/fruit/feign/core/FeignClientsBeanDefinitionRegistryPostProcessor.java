package com.fruit.feign.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kof wang
 */
@Component
public class FeignClientsBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(FeignClientsBeanDefinitionRegistryPostProcessor.class);

    public final static Map<String, Object> fallbacks = new HashMap();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        for (String clasz : fallbacks.keySet()) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            try {
                definition.setBeanClass(Thread.currentThread().getContextClassLoader().loadClass(clasz));    //设置类
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addGenericArgumentValue(new FeignFallbackimplProxy());
            definition.setConstructorArgumentValues(constructorArgumentValues);
            definition.setScope("prototype");       //设置scope
            definition.setLazyInit(false);          //设置是否懒加载
            definition.setAutowireCandidate(true);  //设置是否可以被其他对象自动注入
            beanDefinitionRegistry.registerBeanDefinition(clasz, definition);
            logger.info("【fallback 注册 】= BeanDefinitionRegistry.registerBeanDefinition() 【className】 = " + definition.getBeanClassName());
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {


    }


}
