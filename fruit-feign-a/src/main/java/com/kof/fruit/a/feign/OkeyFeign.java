package com.kof.fruit.a.feign;

import com.kof.fruit.a.FResponeData;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "fegin-a" )
public interface OkeyFeign {

    @RequestMapping(path = "/test2" ,method = RequestMethod.GET)
    FResponeData getTestInfo2();

}
