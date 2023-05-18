package com.yan.spring.cloud.consumer.service.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 拦截器
 *
 * @author : Y
 * @since 2023/5/18 20:30
 */
public class LoadBalancedRequestInterceptor implements ClientHttpRequestInterceptor {
    private volatile Map<String, Set<String>> targetUrlsCache = new HashMap<>();
    /**
     * 服务发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Scheduled(fixedRate = 10 * 1000) // 10秒钟更新-次缓存
    public void updateTargetUrlsCache() { //更新目标URLs
        //获取当前应用的机器列表
        // http://${ip}:${port}
        Map<String, Set<String>> newTargetUrlsCache = new HashMap<>();
        discoveryClient.getServices().forEach(serviceName -> {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            Set<String> newTargetUrls = serviceInstances
                    .stream()
                    .map(s -> s.isSecure() ? "https://" + s.getHost() + ":" + s.getPort() :
                            "http://" + s.getHost() + ":" + s.getPort()
                    ).collect(Collectors.toSet());
            newTargetUrlsCache.put(serviceName, newTargetUrls);
        });
        this.targetUrlsCache = newTargetUrlsCache;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        // URI:/${app-name}/uri
        // URI: "/" + serviceName + "/say?message= "
        URI requestURI = request.getURI();
        String path = requestURI.getPath();
        String[] parts = StringUtils.split(path.substring(1), "/");
        String serviceName = parts[0]; // serviceName
        String uri = parts[1]; // "/say?message=”
        //服务器列表快照

        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));
        int size = targetUrls.size();
        //size =3, index =0 -2
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);
        String actualURL = targetURL + "/" + uri + "?" + requestURI.getQuery();

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        //响应内容
        ResponseEntity<InputStream> entity = restTemplate.getForEntity(actualURL, InputStream.class,"");

        //头
        HttpHeaders httpHeaders = entity.getHeaders();
        //主题
        InputStream responseBody = entity.getBody();
        return new SimpleClientHttpResponse(httpHeaders, responseBody);

    }

    private static class SimpleClientHttpResponse implements ClientHttpResponse {
        private HttpHeaders headers;
        private InputStream body;

        public SimpleClientHttpResponse(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 0;
        }

        @Override
        public String getStatusText() throws IOException {
            return null;
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return null;
        }

        @Override
        public HttpHeaders getHeaders() {
            return null;
        }
    }

}