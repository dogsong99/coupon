package com.dogsong.coupon.service.impl;

import com.dogsong.coupon.dao.CouponTemplateDao;
import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.service.IAsyncService;
import com.dogsong.coupon.service.IBuildTemplateService;
import com.dogsong.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 构建优惠券模版接口实现
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/19
 */
@Slf4j
@Service
public class BuildTemplateServiceImpl implements IBuildTemplateService {

    /** 异步服务 */
    private final IAsyncService asyncService;

    /** CouponTemplate Dao */
    private final CouponTemplateDao templateDao;

    @Autowired
    public BuildTemplateServiceImpl(IAsyncService asyncService, CouponTemplateDao templateDao) {
        this.asyncService = asyncService;
        this.templateDao = templateDao;
    }

    /**
     * 创建优惠券模版
     *
     * @param request {@link TemplateRequest} 模版信息请求对象
     * @return {@link CouponTemplate} 优惠券模版实体
     */
    @Override
    public CouponTemplate buildTemplate(TemplateRequest request) throws CouponException {
        // 参数合法性校验
        if (!request.validate()) {
            throw new CouponException("BuildTemplate Param Is Not Valid!");
        }

        // 判断同名的优惠券是否存在
        if (null != templateDao.findByName(request.getName())) {
            throw new CouponException("Exist Same Name Template!");
        }

        // 构造 CouponTemplate 并保存在数据库中
        CouponTemplate template = requestToTemplate(request);
        template = templateDao.save(template);

        // 根据优惠券模版异步生成优惠券码
        asyncService.asyncConstructCouponByTemplate(template);
        return template;
    }

    /**
     * 将 TemplateRequest 转换为 CouponTemplate
     *
     * @param request {@link TemplateRequest} 请求参数
     * @return {@link CouponTemplate} 优惠券模版
     */
    private CouponTemplate requestToTemplate(TemplateRequest request) {
        return new CouponTemplate(
          request.getName(),
          request.getLogo(),
          request.getDesc(),
          request.getCategory(),
          request.getProductLine(),
          request.getCount(),
          request.getUserId(),
          request.getTarget(),
          request.getRule()
        );
    }
}
