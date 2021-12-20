package com.dogsong.coupon.controller;

import com.dogsong.coupon.exception.CouponException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监控检查接口
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/20
 */
@Slf4j
@RestController
public class HealthCheck {

    /** 服务发现客户端 */
    private final DiscoveryClient client;

    private final Registration registration;

    @Autowired
    public HealthCheck(DiscoveryClient client, Registration registration) {
        this.client = client;
        this.registration = registration;
    }

    /**
     * 监控检查接口
     *
     * 127.0.0.1:7001/coupon-template/health
     */
    @GetMapping("/health")
    public String health() {
        log.debug("view health api");
        return "CouponTemplate Is OK!";
    }

    /**
     * 异常测试接口
     *
     * 127.0.0.1:7001/coupon-template/exception
     */
    @GetMapping("/exception")
    public String exception() throws CouponException {
        log.debug("view exception api");
        throw new CouponException("CouponTemplate Has Some Problem");
    }

    /**
     * 获取 Eureka Server 上的微服务元信息
     *
     * 127.0.0.1:7001/coupon-template/info
     */
    @GetMapping("/info")
    public List<Map<String, Object>> info() {
        // 大约需要等待两分钟时间才能获取到注册的信息
        List<ServiceInstance> instances = client.getInstances(registration.getServiceId());

        List<Map<String, Object>> result = new ArrayList<>(instances.size());

        instances.forEach(i -> {
            Map<String, Object> info = new HashMap<>();
            info.put("serviceId", i.getServiceId());
            info.put("instanceId", i.getInstanceId());
            info.put("port", i.getPort());

            result.add(info);
        });
        return result;
    }

}
