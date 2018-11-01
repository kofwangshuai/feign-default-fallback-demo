package com.kof.fruit.a;


import com.fruit.feign.core.EnableFeignFallbackClients;
import com.fruit.feign.data.DefaultFallbackData;
import com.kof.fruit.a.feign.TestFeginClient;
import com.kof.fruit.a.feign.TestFeign2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableCircuitBreaker
@EnableFeignFallbackClients
public class FeignFallbackApplication {

    public static void main(String[] args) {

//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");

         SpringApplication.run(FeignFallbackApplication.class, args);

    }


    @Autowired
    private TestFeginClient testFeginClient;

    @Autowired
    private TestFeign2 testFeign2;


    @RequestMapping(value = "/test2" ,method = RequestMethod.GET)
    public DefaultFallbackData getTestInfo2(){
        try {
            Thread.sleep(1000*200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new DefaultFallbackData();
    }

    @RequestMapping(value = "/test1" ,method = RequestMethod.GET)
    public String getTestInfo1() throws InterruptedException {
        Thread.sleep(1000*200);
        return "123131313";
    }

    @RequestMapping(value = "/test/1111" ,method = RequestMethod.GET)
    public String getTestInfo(){
        return testFeginClient.getTestInfo1();
    }

    @RequestMapping(value = "/test/2222" ,method = RequestMethod.GET)
    public DefaultFallbackData getTestInfo2222(){
        return testFeign2.getTestInfo2();
    }

}
