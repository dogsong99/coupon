package com.dogsong.coupon.service.impl;

import com.dogsong.coupon.constant.Constant;
import com.dogsong.coupon.dao.CouponTemplateDao;
import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.service.IAsyncService;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 异步服务借口实现
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/17
 */
@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    /** CouponTemplateDao Dao */
    private final CouponTemplateDao templateDao;

    /** 注入 Redis 模版类 */
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public AsyncServiceImpl(CouponTemplateDao templateDao, StringRedisTemplate redisTemplate) {
        this.templateDao = templateDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据模版异步的创建优惠券码
     *
     * @param template {@link CouponTemplate} 优惠券模版实体
     * @return void
     */
    @Async("getAsyncExecutor")
    @SuppressWarnings("all")
    @Override
    public void asyncConstructCouponByTemplate(CouponTemplate template) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Set<String> couponCodes = buildCouponCode(template);

        // coupon_template_code_1
        String redisKey = String.format("%s%s",
                Constant.RedisPrefix.COUPON_TEMPLATE, template.getId().toString());
        log.info("Push CouponCode To Redis: {}.",
                redisTemplate.opsForList().rightPushAll(redisKey, couponCodes));

        template.setAvailable(true);
        templateDao.save(template);

        stopwatch.stop();
        log.info("Construct CouponCode By Template Cost: {}ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));

        // TODO 发送短信或者邮件通知优惠券模版已经可用

        log.info("CouponTemplate({}) Is Available!", template.getId());
    }



    /**
     * 构造优惠券码
     *
     * 优惠券码(对应于每一张优惠券，18位)
     *  前四位: 产品线 + 类型
     *  中间六位: 日期随机(211217)
     *  后八位: 0 ～ 9 随机数构成
     *
     * @param template {@link CouponTemplate} 实体类
     * @return Set<String> 与 template.count 相同个数的优惠券码
     */
    @SuppressWarnings("all")
    private Set<String> buildCouponCode(CouponTemplate template) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Set<String> result = new HashSet<>(template.getCount());

        // 前四位
        String prefix4 = template.getProductLine().getCode().toString()
                + template.getCategory().getCode();
        String data = new SimpleDateFormat("yyMMdd").format(template.getCreateTime());

        for (int i = 0; i != template.getCount(); i ++) {
            result.add(prefix4 + buildCouponCodeSuffix14(data));
        }

        while (result.size() < template.getCount()) {
            result.add(prefix4 + buildCouponCodeSuffix14(data));
        }

        assert result.size() == template.getCount();

        stopwatch.stop();
        log.info("Build Coupon Code Cost: {}ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }


    /**
     * 构造优惠券码的后 14 位
     *
     * @param data 优惠券创建日期
     * @return String
     */
    private String buildCouponCodeSuffix14(String data) {
        char[] bases = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        // 中间 6 位
        List<Character> chars = data.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        // 洗牌
        Collections.shuffle(chars);
        String mid6 = chars.stream().map(Objects::toString).collect(Collectors.joining());
        // 后 8 位
        String suffix8 = RandomStringUtils.random(1, bases) + RandomStringUtils.randomNumeric(7);
        return mid6 + suffix8;
    }

}
