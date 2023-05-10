# 2023 学习

application.properties会继承bootstrap.properties属性 因此，application.properties没有必要配置


## springBoot和springCloud区别和联

答: Spring Framework,封装了大多数Java E标准技术以及开源实现方法，提高生成力。
不过Spring应用需要配置的相关组件，Spring Boot帮助简化了配置步骤，采用默认的配置能够快速的启动或者构建一个JavaEE应用。
Spring Boot单机应用，Spring Cloud是将Spring Boot变成云应用，那么里面需要增强一些组件， 监控、限流、服务注册和发现等等。|

## SpringData-jpa和JTA持久化框架区别

答: jpa主要关注分布式事务，事务操作背后可能资源是数据库、消息、或者缓存等等。
从数据库角度，JTA 使用JPA作为存储，但是可以使用JDBC。
JTA 还能操作IMS。

## feign 和ribbon区别?feign内部实现依靠的是ribbon的嘛?

答: Feign 作为声明式客户端调用，Ribbon 主要负责负载均衡。Feign 可以整合
Ribbon,但是不是强依赖。Spring Cloud对Feign 增强，Feign 原始不支持Spring WebMVC，而是支持标准JAX-RS ( Rest标准)
