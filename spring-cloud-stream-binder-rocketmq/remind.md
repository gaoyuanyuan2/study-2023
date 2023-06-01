# Spring Cloud Stream Binder RocketMQ

## 自定义实现Spring Cloud Stream Binder RocketMQ

RocketMQ->发消息、收消息

bindProducer发消息
bindConsumer收消息


## Binder实现步骤
A typical binder implementation consists of the following:

* A class that implements the Binder interface; (实现Binder 接口)
* A Spring @Configuration class that creates a bean of type Binder along with the middleware  connection infrastructure (Binder实现类上标注@Configuration 注解)
* A META-INF/spring.binders file found on the classpath containing one or more binder definitions, as shown in the following example: (META-INF/spring.binders配置binder名称和Binder 实现类映射)

```properties
kafka:\
org.springframework.cloud.stream.binder.kafka.config.KafkaBinderConfiguration
```

## 思考:假设有多个Binder实现jar同时存在,那么采用哪个呢?

* 配置默认Binder名称
```properties
spring.cloud.stream.defaultBinder=rabbit
```

* 指定某种Binder实现

```properties
spring.cloud.stream.bindings.input.binder=kafka
spring.cloud.stream.bindings.output.binder=rabbit
```


