package com.dogsong.coupon.service.impl;

import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.service.IAsyncService;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Override
    public void asyncConstructCouponByTemplate(CouponTemplate template) {

    }



    @SuppressWarnings("all")
    private Set<String> buildCouponCode(CouponTemplate template) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Set<String> result = new HashSet<>(template.getCount());


        stopwatch.stop();

        return result;
    }


    private String buildCouponCodeSuffix14(String data) {
        char[] bases = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // 中间 6 位
        List<Character> chars = data.chars()
                .mapToObj(e -> (char) e).collect(Collectors.toList());


        return "";
    }

}
