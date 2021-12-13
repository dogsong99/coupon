package com.dogsong.coupon.converter;

import com.dogsong.coupon.constant.DistributeTarget;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 分发目标枚举属性转换器
 *
 * @author <a href="mailto:dogsong99@gmail.com">dogsong</a>
 * @since 2021/12/13
 */
@Converter
public class DistributeTargetConverter implements AttributeConverter<DistributeTarget, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DistributeTarget attribute) {
        return attribute.getCode();
    }

    @Override
    public DistributeTarget convertToEntityAttribute(Integer code) {
        return DistributeTarget.of(code);
    }
}
