package com.dogsong.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * fake 商品信息
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {

    /** 商品类型 {@link com.dogsong.coupon.constant.GoodsType}*/
    private Integer type;

    /** 商品价格 */
    private Double price;

    /** 商品数量 */
    private Integer count;

    // TODO 名称, 使用信息

}
