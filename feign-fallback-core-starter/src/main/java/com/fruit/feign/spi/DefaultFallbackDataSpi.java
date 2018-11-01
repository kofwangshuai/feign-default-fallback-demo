package com.fruit.feign.spi;

import com.fruit.feign.data.DefaultFallbackData;

import java.util.HashMap;
import java.util.Map;

public class DefaultFallbackDataSpi implements IFallbackDataSpi {

    /** 1  指定當前配置返回的結果對象中 需要初始化的字段 **/
    private Map<Class,Map<String ,String>> returnTypeDataMap=new HashMap<>();


    public Map<Class, Map<String, String>> getReturnTypeDataMap() {
        return returnTypeDataMap;
    }

    public DefaultFallbackDataSpi(){
        init();
    }

    void init(){
        // TODO: 2018/11/1   可以优化的更合理一些 ： 可以需要配置项目中所有的接口返回值类型，以及响应的结果
        Map<String,String> keys=new HashMap<>();
        keys.put("code","fallback");
        keys.put("message","hytrix熔断测试的case");
        returnTypeDataMap.put(DefaultFallbackData.class,keys);
    }

    @Override
    public String name() {
        return DefaultFallbackDataSpi.class.getName();
    }


}
