package com.kof.fruit.a.feign;



import com.fruit.feign.data.DefaultFallbackData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "fegin-a" )
public interface TestFeign2 {

    @RequestMapping(path = "/test2" ,method = RequestMethod.GET)
    DefaultFallbackData getTestInfo2();

}
