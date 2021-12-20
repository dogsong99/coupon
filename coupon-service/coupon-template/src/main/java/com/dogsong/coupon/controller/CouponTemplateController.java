package com.dogsong.coupon.controller;

import com.alibaba.fastjson.JSON;
import com.dogsong.coupon.entity.CouponTemplate;
import com.dogsong.coupon.exception.CouponException;
import com.dogsong.coupon.service.IBuildTemplateService;
import com.dogsong.coupon.service.ITemplateBaseService;
import com.dogsong.coupon.vo.CouponTemplateSDK;
import com.dogsong.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 优惠券模版相关的功能控制器
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/21
 */
@Slf4j
@RestController
public class CouponTemplateController {

    private final IBuildTemplateService buildTemplateService;

    private final ITemplateBaseService templateBaseService;

    @Autowired
    public CouponTemplateController(IBuildTemplateService buildTemplateService,
                                    ITemplateBaseService templateBaseService) {
        this.buildTemplateService = buildTemplateService;
        this.templateBaseService = templateBaseService;
    }

    /**
     * 构建优惠券模版
     * 127.0.0.1:7001/coupon-template/template/build
     *
     * @param request {@link TemplateRequest} 模版请求参数
     * @return {@link CouponTemplate} 返回优惠券模版
     */
    @PostMapping("/template/build")
    public CouponTemplate buildTemplate(@RequestBody TemplateRequest request) throws CouponException {
        log.info("Build Template: {}.", JSON.toJSONString(request));
        return buildTemplateService.buildTemplate(request);
    }

    /**
     * 构造优惠券模版详情
     *
     * 127.0.0.1:7001/coupon-template/template/info
     *
     * @param id 优惠券模版id
     * @return {@link CouponTemplate} 返回优惠券模版
     */
    @GetMapping("/template/info")
    public CouponTemplate buildTemplateInfo(@RequestParam("id") Integer id) throws CouponException {
        log.info("Build Template Info For: {}.", id);
        return templateBaseService.buildTemplateInfo(id);
    }

    /**
     * 查找所有可用的优惠券模版
     *
     * 127.0.0.1:7001/coupon-template/template/sdk/all
     *  -- sdk 说明是对外部服务提供的
     *
     * @return List<CouponTemplateSDK> 返回优惠券模版
     */
    @GetMapping("/template/sdk/all")
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        log.info("Find All Usable Template.");
        return templateBaseService.findAllUsableTemplate();
    }

    /**
     * 获取模版 ids 到 CouponTemplateSDK 的映射
     *
     * 127.0.0.1:7001/coupon-template/template/sdk/infos
     *
     * @param ids 优惠券模版ids
     * @return Map<CouponTemplateSDK> 返回优惠券模版
     */
    @GetMapping("/template/sdk/infos")
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(@RequestParam("ids") Collection<Integer> ids) {
        log.info("FindIds2TemplateSDK: {}.", ids);
        return templateBaseService.findIds2TemplateSDK(ids);
    }

}
