package com.dogsong.coupon.service;

import com.dogsong.coupon.entity.CouponTemplate;

/**
 * 异步服务接口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/15
 */
public interface IAsyncService {

    /**
     * 根据模版,异步创建优惠券码
     *
     * @param template {@link CouponTemplate} 优惠券模版实体
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);

}
