package com.kof.fruit.a.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "fegin-a" )
public interface TestFeginClient {

    @RequestMapping(path = "/test1" ,method = RequestMethod.GET)
    String getTestInfo1();

}


