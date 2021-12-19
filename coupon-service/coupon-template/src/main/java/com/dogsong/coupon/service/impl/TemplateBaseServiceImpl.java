package com.dogsong.coupon.service.impl;

import com.dogsong.coupon.dao.CouponTemplateDao;
import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.service.ITemplateBaseService;
import com.dogsong.coupon.vo.CouponTemplateSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 优惠券模版基础服务接口实现
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/19
 */
@Slf4j
@Service
public class TemplateBaseServiceImpl implements ITemplateBaseService {

    private final CouponTemplateDao templateDao;

    @Autowired
    public TemplateBaseServiceImpl(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * 通过优惠券模版id 获取优惠券模版信息
     *
     * @param id 模版id
     * @return {@link CouponTemplate} 优惠券模版实体
     */
    @Override
    public CouponTemplate buildTemplateInfo(Integer id) throws CouponException {
        Optional<CouponTemplate> template = templateDao.findById(id);

        if (!template.isPresent()) {
            throw new CouponException("Template Is Not Exist!");
        }

        return template.get();
    }

    /**
     * 查找可用的优惠券模版
     *
     * @return {@link CouponTemplateSDK}s
     */
    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        List<CouponTemplate> templates = templateDao.findAllByAvailableAndExpired(true, false);

        return templates.stream()
                .map(this::template2TemplateSDK).collect(Collectors.toList());
    }

    /**
     * 获取模版 ids 到 CouponTemplateSDK 的映射
     *
     * @param ids 模版ids
     * @return Map<key: 模版 id, value: CouponTemplateSDK>
     */
    @Override
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids) {
        List<CouponTemplate> templates = templateDao.findAllById(ids);

        return templates.stream()
                .map(this::template2TemplateSDK).collect(Collectors.toMap(
                        CouponTemplateSDK::getId, Function.identity()
                ));
    }

    /**
     * 将 CouponTemplate 转化为 CouponTemplateSDK
     *
     * @param template {@link CouponTemplate}
     * @return {@link CouponTemplateSDK}
     */
    private CouponTemplateSDK template2TemplateSDK(CouponTemplate template) {
        return new CouponTemplateSDK(
                template.getId(),
                template.getName(),
                template.getLogo(),
                template.getDesc(),
                template.getCategory().getCode(),
                template.getProductLine().getCode(),
                template.getKey(),
                template.getTarget().getCode(),
                template.getRule()
        );
    }
}
