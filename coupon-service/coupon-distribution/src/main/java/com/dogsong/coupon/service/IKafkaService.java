package com.dogsong.coupon.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Kafka 相关的服务接口定义
 *
 * @author <a href="mailto:dogsong99@gmail.com">domi</a>
 * @since 2021/12/28
 */
public interface IKafkaService {

    /**
     * 消费优惠券 Kafka 消息
     *
     * @param record {@link ConsumerRecord}
     */
    void consumeCouponKafkaMessage(ConsumerRecord<?, ?> record);

}
