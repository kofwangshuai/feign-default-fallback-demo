
**前言** 
     
     微服务中我们的系统被划分为一个个独立的微服务，服务之间存在一定的依赖关系。而在我们实际项目中spring cloud ，并且使用sc的基于http协议的Feign组件，处理我们服务间通信 。
     这样我们就需要考虑到当服务调用链路比较长 ，系统微服务数量越来越多。我们从系统的高可用性，高可靠性出发 ，我们是需要一种服务过载保护机制 --- 服务降级和服务熔断。
     
     当然 feign 组件是提供对服务熔断的支持--基于 hystrix的实现 
     
**原生熔断集成方式**     

    配置文件 ：
            feign.hystrix.enabled=true
            hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 3501
    注解 ： 
            @EnableHystrix（开启熔断的注解）
            @EnableHystrixDashboard （开启熔断仪表盘注解）
            @EnableFeignClients(开启feignclient接口的注册)
    
    代码编辑
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

**问题** 
        
       在做服务熔断这件事情上，我们遇到的一个什么问题？？    四个字 ：过于繁琐
       
       问题一 ： 我们需要为每个接口 分别在 provider和 comsumer 提供一个fallbcak实现类，并且comsumer的实现 是业务无关的。
       问题二 ： 开启熔断机制以后，所有的接口都是需要在所有的consumer中提供熔断所需的fallback实现类。否则熔断机制就无法使用。
       问题三 ： 当provider提供的接口 ，在不同consumer中调用，重复性的样板式代码 重复编码。
       问题四 ： 也无法控制 同一个接口 在不同的服务中发生熔断时候的响应数据的格式和内容
       
       原生的实现方式 一个非常不友好的实现的方式 ，所有才有该项目的创建 ，在原有基础上进行扩展 。我们不会改变原生集成方式的任何一点，该项目是提供一种全局处理服务熔断的机制。
  
 _**集成到业务服务_**
  
     1 原有的配置和注解 保持不变以外 ，将启动类的 @ EnableFeignClients 替换为 EnableFeignFallbackClients; 可以参考demo工程
     2 编辑一个xxxx.xxxx.xxx.spi包（非强制性的要求）， 该包内实现接口IFallbackDataSpi ，用来配置我们预期的服务熔断发生时候的响应数据的 ；可以参考core工程的spi包实现
     
       
     
