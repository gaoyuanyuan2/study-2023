性能一般，大多数用nginx 
# Zuul

很多企业内部的服务通过Zuul做个服务网关

RequestContext已经存在ThreadLocal中了，为什么还要使用 ConcurrentHashMap?

答: ThreadLocal 只能管当前线程， 不能管理子线程，子线程需要使用 InheritableThreadLocal。
ConcurrentHashMap实现一下，如果上下文处于多线程线程的环境，比如传递到子线程。

比如: T1在管理RequestContext， 但是T1又创建了多个线程(t1、t2)， 这个时候，把上下文传递到了子线程t1和t2。
Java的进程所对应的线程main线程(group: main) ，main线程是所有子 线程的父线程，main线程T1 ，T1又可以创建t1和t2。


ZuulServlet经管理了RequestContext的生命周期了， 为什么 ContextLifeCycleFilter还要在做一遍?

答:ZuulServlet 最终也公清理掉RequestContext。
RequestContext是任何Servlet或者Filter 都能处理，那么为了防止不正确的关闭，那么ContextLifeCycleFilter相当于兜底操作，就是防止ThreadLocal没有被remove掉。

ThreadLocal对应了一个Thread,那么是不是意味着者Thread处理完了，那么ThreadLocal也随之GC?
所有Servlet均采用线程池，因此，不清空的话，可能会出现意想不到的情况。除非，每次都异常!(这种情况也要依赖于线程池的实现)



