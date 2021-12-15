package com.dogsong.coupon.service;

import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.vo.TemplateRequest;

/**
 * 构建优惠券模版接口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/15
 */
public interface IBuildTemplateService {

    /**
     * 创建优惠券模版
     *
     * @param request {@link TemplateRequest} 模版信息请求对象
     * @return {@link CouponTemplate} 优惠券模版实体
     */
    CouponTemplate buildTemplate(TemplateRequest request) throws CouponException;

}
