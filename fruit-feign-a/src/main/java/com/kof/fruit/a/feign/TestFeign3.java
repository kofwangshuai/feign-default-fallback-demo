package com.kof.fruit.a.feign;

import com.fruit.feign.data.DefaultFallbackData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "fegin-a" ,fallback =TestFeign3Impl.class )
public interface TestFeign3 {

    @RequestMapping(path = "/test3" ,method = RequestMethod.GET)
    DefaultFallbackData getTestInfo3();

}

@Component
class TestFeign3Impl implements TestFeign3{

    @Override
    public DefaultFallbackData getTestInfo3() {
        DefaultFallbackData defaultFallbackData = new DefaultFallbackData();
        defaultFallbackData.setCode("fallback");
        defaultFallbackData.setMessage("feignclient原生的fallback机制");
        return defaultFallbackData;
    }
}