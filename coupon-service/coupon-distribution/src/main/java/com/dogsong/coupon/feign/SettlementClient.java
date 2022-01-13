package com.dogsong.coupon.feign;

import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.vo.CommonResponse;
import com.dogsong.coupon.vo.SettlementInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 优惠券结算微服务 Feign 接口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2022/1/13
 */
@FeignClient(value = "eureka-client-coupon-settlement")
public interface SettlementClient {

    @RequestMapping(value = "/coupon-settlement/settlement/compute", method = RequestMethod.POST)
    CommonResponse<SettlementInfo> computeRule(@RequestBody SettlementInfo settlement) throws CouponException;

}
