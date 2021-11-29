package com.dogsong.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印日志
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/11/29
 */
@Slf4j
@Component
public class AccessLogFilter extends AbstractPostZuulFilter {


    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();

        String uri = request.getRequestURI();
        // 从 PreRequestFilter 获取设置的请求时间戳
        Long startTime = (Long) context.get("startTime");
        long duration = System.currentTimeMillis() - startTime;

        // 从网关中通过的请求都会打印日志: uri + duration
        log.info("uri: {}, duration: {}.", uri, duration);

        return success();
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }
}
