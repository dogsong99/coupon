package com.dogsong.coupon.schedule;

import com.dogsong.coupon.dao.CouponTemplateDao;
import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.vo.TemplateRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时清理已过期的优惠券模版
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/19
 */
@Slf4j
@Component
public class ScheduleTask {

    /** CouponTemplate Dao */
    private final CouponTemplateDao templateDao;

    @Autowired
    public ScheduleTask(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void offlineCouponTemplate() {
        log.info("Start To Expire CouponTemplate");

        List<CouponTemplate> templates = templateDao.findAllByExpired(false);
        if (CollectionUtils.isEmpty(templates)) {
            log.info("Done To Expire CouponTemplate.");
            return;
        }

        Date cur = new Date();
        List<CouponTemplate> expiredTemplates = new ArrayList<>(templates.size());

        templates.forEach(t -> {
            // 根据优惠券模版规则中的"过期规则"校验模版是否过期
            TemplateRule rule = t.getRule();
            if (rule.getExpiration().getDeadLine() < cur.getTime()) {
                t.setExpired(true);
                expiredTemplates.add(t);
            }
        });

        if (CollectionUtils.isNotEmpty(expiredTemplates)) {
            log.info("Expired CouponTemplate Num: {}.", templateDao.saveAll(expiredTemplates));
        }

        log.info("Done To Expire CouponTemplate.");

    }
}
