package com.dogsong.coupon.advice;

import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/1
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对 {@link CouponException} 进行统一处理
     *
     */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException (
            HttpServletRequest req, Exception ex
    ) {
        CommonResponse<String> response = new CommonResponse<>(
                -1, "business error"
        );
        response.setData(ex.getMessage());
        return response;
    }

}
