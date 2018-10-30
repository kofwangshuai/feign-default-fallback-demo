package com.kof.fruit.a;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
@Import({FeignClientsFallbackRegistrar.class})
public @interface EnableFeignFallbackClients  {
}
