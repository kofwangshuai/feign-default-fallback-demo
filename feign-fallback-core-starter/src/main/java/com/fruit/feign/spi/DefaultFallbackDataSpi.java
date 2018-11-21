package com.fruit.feign.spi;

import com.fruit.feign.data.DefaultFallbackData;

import java.util.HashMap;
import java.util.Map;

/***
 * 1  需要将 带有@Feignclinet的接口的方法，在发生服务熔断的时候，预设的响应数据 ;
 *
 * */

public class DefaultFallbackDataSpi implements IFallbackDataSpi {

    private Map<Class,Object> returnTypeDataMap=new HashMap<>();

    public Map<Class, Object> getReturnTypeDataMap() {
        return returnTypeDataMap;
    }

    public DefaultFallbackDataSpi(){
        init();
    }

    void init(){
        DefaultFallbackData keys=new DefaultFallbackData<>();
        keys.setCode("fallback");
        keys.setMessage("hytrix熔断测试的case");
        returnTypeDataMap.put(DefaultFallbackData.class,keys);
    }

    @Override
    public String name() {
        return DefaultFallbackDataSpi.class.getName();
    }


}
