# 2023 学习

## application

application.properties会继承bootstrap.properties属性 因此，application.properties没有必要配置

Spring Cloud Bootstrap 上下文优先于Spring Boot应用上 下文，并且Bootstrap上下文是Spring Boot应用上下文双亲(parent)

## springBoot和springCloud区别和联

答: Spring Framework,封装了大多数Java E标准技术以及开源实现方法，提高生成力。
不过Spring应用需要配置的相关组件，Spring Boot帮助简化了配置步骤，采用默认的配置能够快速的启动或者构建一个JavaEE应用。
Spring Boot单机应用，Spring Cloud是将Spring Boot变成云应用，那么里面需要增强一些组件， 监控、限流、服务注册和发现等等。

## 什么是Spring Cloud?

Spring Cloud为开发人员提供快速构建分布式系统的一些通用模式，其中包括:配置管理、服务发
现、服务短路、智能路由、微型网关、控制总线、一次性令牌、全局锁、领导选举、分布式会话和
集群状态。分布式系统间的协调导向样板模式，井且使用Spring Cloud的开发人员能够快速地构建
实现这些模式的服务和应用。这些服务和应用也将在任何环境下工作良好，无论是开发者的笔记本、
还是数据中心裸机或者管控平台。


## Environment

了解Environment以及PropertySource关系以及编程的模式，可以极大地提高实现弹性，比如你可以抛弃
(Spring Cloud官方的分布式配置实现， 比如Git。可以结合Zookeeper以及Ali Diamond 来实现


## 哪些配置项应该写 在bootstrap.yml里面，哪些应该写在application.yml里面，哪些应该写在外部配置文件(spring.application.name}.yml里面可以动态更新的

boostrap.yml配置Spring Cloud属性
application.yml配置当前Spring Boot应用属性，比如server.port等
动态配置，比如开关、数据阈值，可以放在配置服务器

## 什么时候用spring profile什么时候用maven profile

Spring Profile用于运行时，Maven Profile用于编译(构建)时

## SpringData-jpa和JTA持久化框架区别

答: jpa主要关注分布式事务，事务操作背后可能资源是数据库、消息、或者缓存等等。
从数据库角度，JTA 使用JPA作为存储，但是可以使用JDBC。
JTA 还能操作IMS。

## feign 和ribbon区别?feign内部实现依靠的是ribbon的嘛?

答: Feign 作为声明式客户端调用，Ribbon 主要负责负载均衡。Feign 可以整合
Ribbon,但是不是强依赖。Spring Cloud对Feign 增强，Feign 原始不支持Spring WebMVC，而是支持标准JAX-RS ( Rest标准)


## 扩展HTTP客户端

* ClientHttpRequestFactory
  * Spring实现
    * SimpleClientHttpRequestFactory
  * HttpClient
      * HttpComponentsClientHttpRequestFactory
  * OkHttp
      * OkHttp3ClientHttpRequestFactory
      * OkHttpClientHttpRequestFactory

## 经验之谈

* 1.任何结论,需要程序检验(稳定重现)
* 2.经常写代码、多看、多思考
* 3.训练思维模式(抽象思维、辩证思维、逆向思维、发散思维)
* 4.原始积累( JDK、框架: Spring、Tomcat、 Log4j等等 )
* 5.少对同伴代码指点,方案上可以讨论( Code Review思维是否严谨)
* 6.乐于分享(演讲、技术沙龙、讨论会议等)
