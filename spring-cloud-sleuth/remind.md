## 日志发生的变化

当应用ClassPath下存在org.springframework.cloud:spring-cloud-starter-sleuth时候， 日志会发生调整。

## 整体流程
spring-cloud-starter-sleuth会自动装配一个名为TraceFilter组件(在Spring WebMVC DispatcherServlet之前)，
它会增加一些slf4j MDC
MDC : Mapped Diagnostic Context

`org.springframework.cloud:spring-cloud-starter-sleuth`会调整当前日志系统(slf4j )的MDC 

Slf4jSpanLogger#logContinuedSpan(Span)

public void logContinuedSpan(Span span)
