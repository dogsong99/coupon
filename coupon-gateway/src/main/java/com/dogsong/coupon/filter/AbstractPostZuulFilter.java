package com.dogsong.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * <h1>Post filters 通用的抽象过滤器类</h1>
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/11/29
 */
public abstract class AbstractPostZuulFilter extends AbstractZuulFilter {

    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
}
