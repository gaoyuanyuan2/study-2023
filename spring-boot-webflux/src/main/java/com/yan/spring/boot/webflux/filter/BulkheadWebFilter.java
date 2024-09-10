package com.yan.spring.boot.webflux.filter;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring WebFlux 熔断、限流等特性
 *
 * @author : Y
 * @since 2024/9/10 20:06
 */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BulkheadWebFilter implements WebFilter, EnvironmentAware, BeanFactoryAware,
        InitializingBean, DisposableBean {

    public static final String BULKHEAD_ATTRIBUTE_NAME = Bulkhead.class.getName() + "@WebFlux";

    private static final Logger logger = LoggerFactory.getLogger(BulkheadWebFilter.class);
    private ObjectProvider<HandlerMapping> handlerMappingProvider;

    private BeanFactory beanFactory;
    private Environment environment;

    protected BulkheadRegistry bulkheadRegistry;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HandlerMethod handlerMethod = retrieveHandlerMethod(this.handlerMappingProvider, exchange);
        if (logger.isDebugEnabled())
            logger.debug("try decorate request : {}", handlerMethod.toString());
        final Bulkhead bulkhead = this.bulkheadRegistry.bulkhead(handlerMethod.toString(), customizeBulkheadConfig(handlerMethod, environment, beanFactory));

        //execute
        return bulkhead.executeSupplier(() -> chain.filter(exchange));
    }

    protected BulkheadConfig customizeBulkheadConfig(HandlerMethod handlerMethod, Environment environment, BeanFactory beanFactory) {
        //default implements
        return BulkheadConfig.ofDefaults();
    }

    protected HandlerMethod retrieveHandlerMethod(ObjectProvider<HandlerMapping> handlerMappingProvider, ServerWebExchange exchange) {
        return Flux.fromStream(handlerMappingProvider.stream())
                .concatMap(mapping -> mapping.getHandler(exchange))
                .next()
                .map(HandlerMethod.class::cast)
                .block();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    protected BulkheadRegistry initializeBulkheadRegistry(Environment environment, BeanFactory beanFactory) {
        //use default
        return BulkheadRegistry.ofDefaults();
    }

    protected void clearBulkheadRegistry(BulkheadRegistry bulkheadRegistry) {
        List<String> bulkheadNames = bulkheadRegistry.getAllBulkheads().asJava()
                .stream()
                .map(Bulkhead::getName)
                .collect(Collectors.toList());

        for (String bulkheadName : bulkheadNames)
            bulkheadRegistry.remove(bulkheadName);
    }

    @Override
    public void destroy() throws Exception {
        if (this.bulkheadRegistry != null) {
            clearBulkheadRegistry(bulkheadRegistry);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //initialize BulkheadRegistry
        this.bulkheadRegistry = initializeBulkheadRegistry(this.environment, this.beanFactory);

        this.handlerMappingProvider = this.beanFactory.getBeanProvider(HandlerMapping.class);
    }
}