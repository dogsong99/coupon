package com.dogsong.coupon.feign;

import com.dogsong.coupon.vo.CommonResponse;
import com.dogsong.coupon.vo.CouponTemplateSDK;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 优惠券模版微服务 Feign 接口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2022/1/6
 */
@FeignClient(value = "eureka-client-coupon-template")
public interface TemplateClient {

    /**
     * 查找所有有可能的优惠券模版
     */
    @RequestMapping(value = "/coupon-template/template/sdk/all", method = RequestMethod.GET)
    CommonResponse<List<CouponTemplateSDK>> findAllUsableTemplate();


}
