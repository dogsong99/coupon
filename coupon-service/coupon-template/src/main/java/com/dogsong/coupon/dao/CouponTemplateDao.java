package com.dogsong.coupon.dao;

import com.dogsong.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CouponTemplate Dao 借口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/15
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Integer> {

    /**
     * 根据模版名称查询模版
     *
     * > where name = ...
     */
    CouponTemplate findByName(String name);

    /**
     * 根据 available 和 expired 标记查找的模版记录
     *
     * > where available = ... and expired =...
     */
    List<CouponTemplate> findAllByAvailableAndExpired(Boolean available, Boolean expired);

    /**
     * 根据 expired 标记查找模版记录
     *
     * > where expired = ...
     */
    List<CouponTemplate> findAllByExpired(Boolean expired);

}
