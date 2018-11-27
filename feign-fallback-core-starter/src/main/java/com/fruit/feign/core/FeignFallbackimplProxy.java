package com.fruit.feign.core;

import com.alibaba.fastjson.JSON;

import com.fruit.feign.data.DefaultFallbackData;
import com.fruit.feign.data.FallbackData;
import com.fruit.feign.spi.IFallbackDataSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;


public class FeignFallbackimplProxy implements InvocationHandler {

    Logger logger = LoggerFactory.getLogger(FeignFallbackimplProxy.class);


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception, Throwable {
        String className = method.getDeclaringClass().getName();
        if (Object.class.equals(method.getDeclaringClass())) {
            //如果传进来的是一个實現類
            if (logger.isDebugEnabled()) {
                logger.debug(" \n\n >>>>>>>>>>>>>>>>> \n this class 【" + className + " 】 is an interface \n<<<<<<<<<<<<<<<<<<<< \n \n");
            }

            try {
                return method.invoke(this, args);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw e;
            } catch (Throwable t) {
                logger.error(t.getMessage(), t);
                throw t;
            }
        } else {
            //如果传进来的是一个接口（核心)
            if (logger.isDebugEnabled()) {
                logger.debug(" \n\n >>>>>>>>>>>>>>>>> \n this class 【" + className + " 】 is an interface \n<<<<<<<<<<<<<<<<<<<< \n \n");
            }
            return run(method, args);
        }
    }

    /**
     * 实现接口的核心方法
     *
     * @param method
     * @param args
     * @return
     */
    public Object run(Method method, Object[] args) throws Exception {


        Object fallbackData = null;
        traceLog(method, args);
        Type type = method.getGenericReturnType();

        /* todo : 判断返回结构是一个map ,void 等等的时候的处理方式 ： */
        try {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type rawType = parameterizedType.getRawType();
                type = rawType;
            }
            String typeName = type.getTypeName();
            Map<Class, Object> defaultFallbackSpi = createDefaultFallbackSpi();
            Object stringStringMap = defaultFallbackSpi.get(type);
//            Field[] fields = o.getClass().getDeclaredFields();
//            boolean defaultDataKey=false;
//            for (Field field :fields){
//                field.setAccessible(true);
//                String name1 = field.getName();
//                if (stringStringMap!=null&&stringStringMap.containsKey(name1)){
//                    defaultDataKey=true;
//                    field.set(o,stringStringMap.get(name1));
//                }
//            }
            if (stringStringMap != null) {
                fallbackData = stringStringMap;
            } else {
                throw new NullPointerException(" 缺少配置 ：");
//                fallbackData = createMethodReturnData(method, methodGenericReturnType.newInstance());
            }
        } catch (Exception e) {
            logger.error("Message :" + e.getMessage() + "LocalizedMessage：" + e.getLocalizedMessage(), e);
            throw e;
        }
        return fallbackData;
    }

    private Map<Class, Object> createDefaultFallbackSpi() {

        Map<Class, Object> resultMap = new HashMap<>();
        ServiceLoader<IFallbackDataSpi> serviceLoader = ServiceLoader.load(IFallbackDataSpi.class);
        Iterator<IFallbackDataSpi> it = serviceLoader.iterator();
        while (it.hasNext()) {
            IFallbackDataSpi demoService = it.next();
            Map<Class, Object> returnTypeDataMap = demoService.getReturnTypeDataMap();
            resultMap.putAll(returnTypeDataMap);
        }

        return resultMap;
    }

    private Object createMethodReturnData(Method method, Object o) {
        Object fallbackData = null;
        if (o instanceof String) {
            FallbackData fResponeData = createDefaultFallbackData(method);
            String jsonString = JSON.toJSONString(fResponeData);
            fallbackData = jsonString;
        } else if (o instanceof Byte) {
            return new Byte("-1");
        } else if (o instanceof Integer) {
            return new Integer(-1);
        } else if (o instanceof Long) {
            return new Long("-1");
        } else if (o instanceof Float) {
            return new Float("-1");
        } else if (o instanceof Short) {
            return new Short("-1");
        } else if (o instanceof Double) {
            return new Double("-1");
        } else if (o instanceof Boolean) {
            return new Boolean(false);
        } else if (o instanceof Character) {
            return new Character('1');
        } else {
            FallbackData Data = createDefaultFallbackData(method);
            fallbackData = Data;
        }
        return fallbackData;
    }

    private DefaultFallbackData createDefaultFallbackData(Method method) {
        DefaultFallbackData fResponeData = new DefaultFallbackData();
        fResponeData.setCode("fallback");
        fResponeData.setMessage("hystrix fall back: method name =" + method.getName() + " , class name = " + method.getDeclaringClass().getCanonicalName() + "....................");
        return fResponeData;
    }

    private void traceLog(Method method, Object[] args) {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        if (logger.isInfoEnabled()) {
            String jsonString = JSON.toJSONString(args);
            logger.debug("\n\n >>>>>>>>>> \n className =" + className + " , methodName = " + methodName + " args = " + jsonString +
                    "\n<<<<<<<<<<<<<<<<\n\n");
        }
    }


}