package com.dogsong.coupon.service;

import com.alibaba.fastjson.JSON;
import com.dogsong.coupon.constant.CouponCategory;
import com.dogsong.coupon.constant.DistributeTarget;
import com.dogsong.coupon.constant.PeriodType;
import com.dogsong.coupon.constant.ProductLine;
import com.dogsong.coupon.vo.TemplateRequest;
import com.dogsong.coupon.vo.TemplateRule;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * 构造优惠券码模版服务测试
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BuildTemplateTest {

    @Autowired
    private IBuildTemplateService buildTemplateService;

    @Test
    public void testBuildTemplate() throws Exception {
        System.out.println(JSON.toJSONString(
                buildTemplateService.buildTemplate(fakeTemplateRequest())
        ));
        Thread.sleep(5000);
    }

    /**
     * fake TemplateRequest
     */
    private TemplateRequest fakeTemplateRequest() {
        TemplateRequest request = new TemplateRequest();
        request.setName("优惠券模版-" + new Date().getTime());
        request.setLogo("www.baidu.com");
        request.setDesc("这是一张优惠券模版");
        request.setCategory(CouponCategory.MANJIAN.getCode());
        request.setProductLine(ProductLine.DABAO.getCode());
        request.setCount(1000);
        request.setUserId(1001L);
        request.setTarget(DistributeTarget.SINGLE.getCode());

        TemplateRule rule = new TemplateRule();
        rule.setExpiration(new TemplateRule.Expiration(
                PeriodType.REGULAR.getCode(),
                1, DateUtils.addDays(new Date(), 60).getTime()
        ));
        rule.setDiscount(new TemplateRule.Discount(5, 1));
        rule.setLimitation(1);
        rule.setUsage(new TemplateRule.Usage(
                "河南省", "郑州市",
                JSON.toJSONString(Arrays.asList("文娱", "居家"))
        ));
        rule.setWeight(JSON.toJSONString(Collections.EMPTY_LIST));

        request.setRule(rule);
        return request;
    }

}
