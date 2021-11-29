package com.dogsong.coupon.filter;

import org.springframework.stereotype.Component;

/**
 * 在过滤器中存储客户端发送请求的时间戳
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/11/29
 */
@Component
public class PreRequestFilter extends AbstractPreZuulFilter{

    @Override
    protected Object cRun() {
        context.put("startTime", System.currentTimeMillis());
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
        return 0;
    }
}
