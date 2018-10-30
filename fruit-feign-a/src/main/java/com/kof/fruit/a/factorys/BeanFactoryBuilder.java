package com.kof.fruit.a.factorys;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactoryBuilder
{
    private static List<Beanfactory> beanFactorys=new ArrayList<Beanfactory>();

    private static ConcurrentHashMap<Class , SingltonBeanFactory> classFactoryMap=new ConcurrentHashMap<>();


    public static <T> SingltonBeanFactory obtainFactory(Class clazz){
        if(!classFactoryMap.containsKey(clazz)){
            for (Beanfactory beanfactory :beanFactorys){
                classFactoryMap.putIfAbsent(clazz,new SingltonBeanFactory<T>(clazz ,beanfactory.getBean((Class<T>) clazz)));

            }
        }

        if (!classFactoryMap.containsKey(clazz)){
            classFactoryMap.putIfAbsent(clazz, new SingltonBeanFactory<T>(clazz ));
        }
        return classFactoryMap.get(clazz);
    }

    public static void registerBeanFactory(Beanfactory beanFactory){
        beanFactorys.add(beanFactory);

    }

    public static class SingltonBeanFactory<T>
    {
        private volatile T instance=null;

        private String classname;

        public SingltonBeanFactory(Class clazz ,T instance){
            this.classname=clazz.getName();
            this.instance=instance;
        }

        public SingltonBeanFactory(Class clazz){
            this.classname=clazz.getName();
        }

        public T getInstance(){
            if (instance==null){
                synchronized (SingltonBeanFactory.class){
                    if(instance==null){
                        ClassLoader loader=Thread.currentThread().getContextClassLoader();
                        try {
                            Class<?> aClass = loader.loadClass(classname);
                            instance= (T) aClass.newInstance();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return instance;
        }
    }
}
