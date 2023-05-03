RestTemplate增加一个LoadBalancerInterceptor，调用Netflix中的LoadBalancer实现，
根据Eureka客户端应用获取目标应用IP+Port信息，轮训的方式调用。

QPS:经过全链路压测，计算单机极限QPS，集群QPS=单机QPS*集群机器数量*可靠性比率
全链路压测除了压极限QPS，还有错误数量

* IRule
  * 随机规则: RandomRule
  * 最可用规则: BestAvailableRule
  * 轮训规则: RoundRobinRule
  * 重试实现: RetryRule
  * 客户端配置: ClientConfigEnabledRoundRobinRule
  * 可用性过滤规则: AvailabilityFilteringRule
  * RT权重规则: WeightedResponseTimeRule
  * 规避区域规则: ZoneAvoidanceRule
