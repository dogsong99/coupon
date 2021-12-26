package com.dogsong.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 用户优惠券的状态
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/26
 */
@Getter
@AllArgsConstructor
public enum CouponStatus {

    USABLE("可用的", 1),
    USED("已使用的", 2),
    EXPIRED("过期的(未被使用的)", 3);

    /** 优惠券状态描述信息 */
    private String description;

    /** 优惠券状态码 */
    private Integer code;

    /**
     * 根据 code 获取到 CouponStatus
     *
     * @param code 码
     * @return {@link CouponStatus}
     */
    public static CouponStatus of(Integer code) {
        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> Objects.equals(bean.code, code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exist!"));
    }

}
