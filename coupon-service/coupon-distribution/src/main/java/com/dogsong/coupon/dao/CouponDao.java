package com.dogsong.coupon.dao;

import com.dogsong.coupon.constant.CouponStatus;
import com.dogsong.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Coupon Dao 接口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/28
 */
public interface CouponDao extends JpaRepository<Coupon, Integer> {

    /**
     * 根据 userId + 状态寻找优惠券记录
     * -- where userId = ... and status = ...
     */
    List<Coupon> findAllByUserIdAndStatus(Long userId, CouponStatus status);

}
