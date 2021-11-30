package com.dogsong.coupon.advice;

import com.dogsong.coupon.annotation.IgnoreResponseAdvice;
import com.dogsong.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 通用响应数据处理
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/1
 */
@RestControllerAdvice
public class CommonResponseDateAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行增强
     */
    @SuppressWarnings("all")
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果当前方法所在类标识了 @IgnoreResponseAdvice 注解，不要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        // 如果当前方法标识了 @IgnoreResponseAdvice 注解，不要处理
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        // 对响应进行处理, 执行 beforeBodyWrite() 方法
        return true;
    }

    /**
     * 响应返回之前的处理
     *
     */
    @SuppressWarnings("all")
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(
                0, ""
        );

        // 如果 o 是 null，response 不需要设置 data
        if (null == o) {
            return response;

        // 如果 o 已经是 CommonResponse，不需要再次处理
        } else if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
        // 否则，把 o 作为 CommonResponse 的 data 部分
        } else {
            response.setData(o);
        }

        return response;
    }
}
