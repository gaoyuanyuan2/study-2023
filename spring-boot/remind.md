#  template

## JSP内置( built-in ) 九大变量

*  Servlet Scope 上下文( Spring @Scope )
*  PageContext ( page变量)
   *  关注当前页面
   *  A forward B
     *  A变量只能A页面使用，不能共享给B
     *  A和B可以采用同名变量,同时使用
*  Servlet Request (请求上下文) - WebApplicationContext#SCOPE REQUEST
   *  关注当前请求
     *  A forward B
     *  A请求变量可以用于B页面
*  Servlet Session (会话上下文) - WebApplicationContext#SCOPE SESSION
   *  关注当前会话
     *  A forward / redirect B
     *  A请求变量可以用于B页面
*  Servlet ServletContext (应用上下文) - WebApplicationContext#SCOPE APPLICATION
   *  关注当前应用
     *  用户A和用户B会话可以共享


## Spring MVC

* *.do-> | DispatcherServlet -> Controller -> View J -> ViewResolver -> Vi ew#render J -> HTML ->HttpServletResponse
    * Thymeleaf ViewResolver -> ThymeleafViewResolver
    * Thymeleaf View -> ThymeleafView
        * 通过模板名称解析模板资源
            * TemplateResolution
        * 读取资源，并且渲染内容HTML
            * IEngineContext
            * ProcessorTemplateHandler
        * HTML内容输出到Response
        * 源码路径
           * org.thymeleaf.TemplateEngine#process(org.thymeleaf.TemplateSpec)
           * org.thymeleaf.context.IContext java.io.Writer
           * org.thymeleaf.engine.TemplateManager#parseAndProcess

## Struts
* *.do-> ActionServlet-> Action -> ForwardAction -> RequestDispatcher -> JSP ( Servlet) -> HTML -> HttpServletResponse

## JSP为什么Spring抛弃

*  Java EE和Spring竞争关系的
*  Spring 想独立门户
*  WebFlux
   * Servlet 3.1
   * Reactor +Netty
* @Controller、@RequestParam
    * Spring Web MVC
    * Spring WebFlux
        * 不再看到Servlet API
        * ServletRequest
        * ServletResponse

JSP 性能更好

JSP-> JSP模板->翻译Servlet Java源文件->编程Servlet Class -> Servlet加载-> Servlet执行(字节码执行)
Thymeleaf -> Thymeleaf模板->解释执行模板表达式(动态运行时)

## HTTP状态码( org.springframework.http.HttpStatus)

* 200
  *  org.springframework.http.HttpStatus#OK
* 304
  *  org.springframework.http.HttpStatus#NOT_MODIFIED
    * 第一次完整请求，获取响应头(200),直接获取
    * 第二次请求，只读取头信息，响应头(304)，取上次Body结果
* 400
  * org.springframework.http.HttpStatus#BAD_REQUEST
* 404
* 500


## 函数式编程 + Reactive

* Reactive programming
* 编程风格
  * Fluent流畅的
  * Streams 流式的
* 业务效果
  * 流程编排
  * 大多数业务逻辑是数据操作
* 函数式语言特性( Java 8+ )
  * 消费类型| Consumer
  * 生产类型Supplier
  * 转换类型Function
  * 判断类型Predicate
  * 提升/减少维度map / reduce / flatMap

Stream是| Iterator模式,数据已完全准备,拉模式( Pull )
Reactive是观察者模式，来一个算一个， 推模式( Push)， 当有数据变化的时候,作出反应( Reactive)
React(反应)

## WebFlux使用场景

长期异步执行,一旦提交,慢慢操作。是否适合RPC操作?
任务型的,少量线程,多个任务长时间运作,达到伸缩性。

Mono :单数据optional 0:1, RxJava: Single
Flux:多数据集合，Collection  0:N, RxJava : observable

## 解决循环依赖

* @Lazy
* org.springframework.beans.factory.ObjectProvider.getIfAvailable()

## 普通Bean

* 编码BeanDefinition 生成的
* XML配置的
* 注解标注的
* 直接注册的
* FactoryBean生成的

