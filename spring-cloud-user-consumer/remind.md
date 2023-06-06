## RestTemplate

RestTemplate增加一个LoadBalancerInterceptor，调用Netflix中的LoadBalancer实现，
根据Eureka客户端应用获取目标应用IP+Port信息，轮训的方式调用。

QPS:经过全链路压测，计算单机极限QPS，集群QPS=单机QPS*集群机器数量*可靠性比率
全链路压测除了压极限QPS，还有错误数量

## IRule

* 随机规则: RandomRule
* 最可用规则: BestAvailableRule
* 轮训规则: RoundRobinRule
* 重试实现: RetryRule
* 客户端配置: ClientConfigEnabledRoundRobinRule
* 可用性过滤规则: AvailabilityFilteringRule
* RT权重规则: WeightedResponseTimeRule
* 规避区域规则: ZoneAvoidanceRule

## Feign

注意: Spring Cloud Feign和OpenFeign区别
Spring Cloud Feign是OpenFeign扩展,并且使用Spring MVC注解来做URI映射,比如
@RequestMapping或@GetMapping 之类
OpenFeign :灵感来自于]AX-RS ( Java REST标准) ,重复发明轮子。


## Feign. Spring Cloud Open Feign. JAX-RS. Spring Web MVC注解驱动特性

|技术栈 |HTTP方法 |变量路径 |请求参数 |自描述消息|使用场景|
|---|---|---|---|---|---|
|JAX-RS |@GET|@PathParam|@FormParam|@Produces("application/json")|客户端、服务端声明|
|Spring MVC|@GetMapping|@PathVariable|@RequestParam|@RequestMapping(produces="application/json")|服务端声明|
|OpenFeign|@RequestLine("GET...")|@Param|@Param||客户端声明|
|Spring Cloud Feign|@GetMapping|@PathVariable|@RequestParam||客户端声明|



## Spring Cloud服务调用

* 服务发现- DiscoveryClient (Eureka、ZK、Consul、 Nacos 等)
* 负载均街- Netflix Ribbon (唯一选择)
* Feign (唯一选择)

## Enable模块驱动

* org.spring.framework.context.annotation.ImportBeanDefinitionRegistrar
  * EnableFeignClients
* org.spring.framework.context.annotation.ImportSelector
  * @EnableAsync
* @Configuration 类
  * EnableWebMvc


## Spring Cloud Open Feign实现细节

@EnableFeignClients
实现策略: Enable 模块驱动
具体实现: org.spring.framework.context.annotation.ImportBeanDefinitionRegistrar
主要工作: 
* 注册默认配置
* 注册所有标注@FeignClient配置类

注册所有标注@Feignclient配置类
源码位置: 
org.springframework.cloud.openfeign.FeignClientsRegistrar.registerFeignClients
通过ClasspathScanningCandidateComponentProvider扫描指定的basePackages集合中
的类是否标注@FeignClient,如果有的话，作为AnnotatedBeanDefinition返回，其中包
含@FeignClient属性元数据，来自于AnnotationMetadata,再重新注册
FeignClientFactoryBean的BeanDefinition

EnableFeignClient > @FeignClient元信息->标注接口定义的
FeignClientFactoryBean >形成被标注接口的代理对象



