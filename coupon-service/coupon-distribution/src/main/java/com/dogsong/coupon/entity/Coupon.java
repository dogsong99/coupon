package com.dogsong.coupon.entity;

import com.dogsong.coupon.constant.CouponStatus;
import com.dogsong.coupon.vo.CouponTemplateSDK;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 优惠券(用户领取的优惠券记录)实体表
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
@JsonSerialize
public class Coupon {

    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /** 关联优惠券模版的主键(逻辑外键) */
    @Column(name = "template_id", nullable = false)
    private Integer templateId;

    /** 领取用户 */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 优惠券码 */
    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    /** 领取时间 */
    @CreatedDate
    @Column(name = "assign_time", nullable = false)
    private Date assignTime;

    /** 优惠券状态 */
    @Column(name = "status", nullable = false)
    private CouponStatus status;

    /** 优惠券对应的模版信息 */
    @Transient
    private CouponTemplateSDK templateSDK;

    /**
     * 返回一个无效的 Coupon 对象
     */
    public static Coupon invalidCoupon() {
        Coupon coupon = new Coupon();
        coupon.setId(-1);
        return coupon;
    }

    /**
     * 构造优惠券
     *
     * @param templateId 优惠券模版id
     * @param userId 领取用户id
     * @param couponCode 优惠券码
     * @param status 优惠券状态
     */
    public Coupon(Integer templateId, Long userId, String couponCode,
                  CouponStatus status) {
        this.templateId = templateId;
        this.userId = userId;
        this.couponCode = couponCode;
        this.status = status;
    }
}
