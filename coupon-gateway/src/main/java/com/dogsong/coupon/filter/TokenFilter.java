package com.dogsong.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 检验请求中传递的 token
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/11/29
 */
@Slf4j
@Component
public class TokenFilter extends AbstractPreZuulFilter {

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request to %s.",
                request.getMethod(), request.getRequestURI()));

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            log.error("error: token is empty.");
            fail(401, "error: token is empty.");
        }

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
        return 1;
    }
}
