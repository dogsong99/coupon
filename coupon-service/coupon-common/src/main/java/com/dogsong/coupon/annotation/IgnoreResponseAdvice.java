package com.dogsong.coupon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 忽略统一响应注解定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IgnoreResponseAdvice {
}
