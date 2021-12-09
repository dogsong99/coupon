package com.dogsong.coupon.entity;

import com.dogsong.coupon.constant.CouponCategory;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 优惠券模版实体类定义: 基础属性 + 规则属性
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate implements Serializable {

    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /** 是否可用状态 */
    private Boolean available;

    /** 是否过期 */
    private Boolean expired;

    /** 优惠券名称 */
    private String name;

    /** logo */
    private String logo;

    /** 优惠券描述 */
    private String desc;

    /** 优惠券分类 */
    private CouponCategory category;







}
