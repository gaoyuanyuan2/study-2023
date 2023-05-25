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

|技术栈 |HTTP方法 |变量路径 |请求参数 |自描述消息|
|---|---|---|---|---|
|JAX-RS |@GET|@PathParam|@FormParam|@Produces("application/json")|
|Spring MVC|@GetMapping|@PathVariable|@RequestParam|@RequestMapping(produces="application/json")|
|OpenFeign|@RequestLine("GET...")|@Param|@Param||
|Spring Cloud Feign|@GetMapping|@PathVariable|@RequestParam||












