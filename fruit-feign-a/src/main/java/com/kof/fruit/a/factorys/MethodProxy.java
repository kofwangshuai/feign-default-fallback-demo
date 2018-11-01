//package com.kof.fruit.a.factorys;
//
//import com.alibaba.fastjson.JSON;
//import com.kof.fruit.a.entity.FResponeData;
//import com.kof.fruit.a.entity.ResponeData;
//
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//
//
//
//public class MethodProxy<T> implements InvocationHandler {
//
//
//    /** todo  添加一个 缓存所有的带有feignclinent注解的接口类 和 **/
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {
//        //如果传进来是一个已实现的具体类（本次演示略过此逻辑)
//        if (Object.class.equals(method.getDeclaringClass())) {
//            try {
//                return method.invoke(this, args);
//            } catch (Throwable t) {
//                t.printStackTrace();
//            }
//            //如果传进来的是一个接口（核心)
//        } else {
//            return run(method, args);
//        }
//        return null;
//    }
//
//    /**
//     * 实现接口的核心方法
//     * @param method
//     * @param args
//     * @return
//     */
//    public Object run(Method method,Object[] args){
//
//        // TODO :: 重新设计一下实现方式 ：：
//        String name = method.getName();
//        String clazz=method.getDeclaringClass().getCanonicalName();
//        Type type=method.getGenericReturnType();
//        String typeName = type.getTypeName();
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//
//        try {
//            Class<?> aClass1 = contextClassLoader.loadClass(typeName);
//
//            Object o = aClass1.newInstance();
//
//            Field[] fields = o.getClass().getDeclaredFields();
//
////            Field code = null;
////            try {
////                code = o.getClass().getDeclaredField("code");
////            } catch (NoSuchFieldException e) {
////                e.printStackTrace();
////            }
////
////            Field message = null;
////            try {
////                message = o.getClass().getDeclaredField("message");
////            } catch (NoSuchFieldException e) {
////                e.printStackTrace();
////            }
//
//            for (Field field :fields){
//                field.setAccessible(true);
//                String name1 = field.getName();
//                if (name1.equals("code")){
//                    field.set(o,"12233333333333333333333333");
//                }else if(name1.equals("message")){
//                    field.set(o,"hystrix fall back: method name ="+name+" , class name = "+clazz+"....................");
//                }
//
//                // TODO: 2018/11/1   spi  加载配置类 ： name 和 value
//
//            }
//            return o;
//
//
////            if (o instanceof String){
////                FResponeData fResponeData = new FResponeData();
////                fResponeData.setCode("1231231");
////                fResponeData.setMessage("hystrix fall back: method name ="+name+" , class name = "+clazz+"....................");
////                String jsonString = JSON.toJSONString(fResponeData);
////                return jsonString;
////            }else if ( o instanceof ResponeData){
////                FResponeData fResponeData = new FResponeData();
////                fResponeData.setCode("1231231");
////                fResponeData.setMessage("hystrix fall back: method name ="+name+" , class name = "+clazz+"....................");
////                return fResponeData;
////            }
//        } catch (ClassNotFoundException e) {
//
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//
//        return new Object();
//    }
//
//}