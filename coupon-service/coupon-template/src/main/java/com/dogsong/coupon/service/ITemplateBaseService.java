package com.dogsong.coupon.service;

import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.vo.CouponTemplateSDK;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 优惠券模版基础(view, delete...)服务定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/16
 */
public interface ITemplateBaseService {

    /**
     * 通过优惠券模版id 获取优惠券模版信息
     *
     * @param id 模版id
     * @return {@link CouponTemplate} 优惠券模版实体
     */
    CouponTemplate buildTemplateInfo(Integer id) throws CouponException;

    /**
     * 查找可用的优惠券模版
     *
     * @return {@link CouponTemplateSDK}s
     */
    List<CouponTemplateSDK> findAllUsableTemplate();

    /**
     * 获取模版 ids 到 CouponTemplateSDK 的映射
     *
     * @param ids 模版ids
     * @return Map<key: 模版 id, value: CouponTemplateSDK>
     */
    Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids);


}
