
## Feign

Feign:原生并不是Spring Web MVC的实现，基于AX-RS (Java REST规范)实现。
Spring Cloud封装了 Feign，使其支持Spring Web MVC。
RestTemplate 小、HttpMessageConverter RestTemplate以及Spring Web MVC可以显示地自定义HttpMessageConverter实现。

Feign服务(服务提供)端:不一定强制实现Feign申明接口


## IRule

* 随机规则: RandomRule
  * 最可用规则: BestAvailableRule
  * 轮训规则: RoundRobinRule
  * 重试实现: RetryRule
  * 客户端配置: ClientConfigEnabledRoundRobinRule
  * 可用性过滤规则: AvailabilityFilteringRule
  * RT权重规则: WeightedResponseTimeRule
  * 规避区域规则: ZoneAvoidanceRule
