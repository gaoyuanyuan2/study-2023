# Eureka

## 问答

1、都是在内存里面缓存着，EurekaClient并不是所有的服务，而是缓存需要的服务。

2、consumer调用provider-a挂了，会自动切换provider-b吗， 保证请求可用吗

答:当provider-a挂，会自动切换，不过不一定及时。不及时，服务端可能存在. 脏数据，或者轮训更新时间未达。

3、Eureka不适合大规模集群。

## 配置

```properties
### Eureka Server应用名称
spring.application.name = spring-cloud-eureka-server
### Eureka Server服务端口
server.port= 9090
###取消服务器自我注册
eureka.client.register-with-eureka=true
###注册中心的服务器，没有必要再去检索服务
eureka.client.fetch-registry = true
## Eureka Server服务URL,用于客户端注册
##当前Eureka服务器向9091 (Eureka服务器)复制数据
eureka.client.serviceUrl.defaultZone=\
http://localhost:9091/eureka 
```

```properties
### Eureka Server应用名称
spring.application.name = spring-cloud-eureka-server
### Eureka Server服务端口
server.port= 9091
###取消服务器自我注册
eureka.client.register-with-eureka=true
###注册中心的服务器，没有必要再去检索服务
eureka.client.fetch-registry = true
## Eureka Server服务URL,用于客户端注册
##当前Eureka服务器向9091 (Eureka服务器)复制数据
eureka.client.serviceUrl.defaultZone=\
http://localhost:9090/eureka 
```


基本概念
Source:来源，近义词: Producer、 Publisher
Sink:接收器，近义词: Consumer、 Subscriber
Processor:对于. 上流而言是Sink，对于下流而言是Source
