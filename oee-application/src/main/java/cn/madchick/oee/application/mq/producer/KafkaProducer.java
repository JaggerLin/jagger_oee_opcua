package cn.madchick.oee.application.mq.producer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @description: MQ生产者-发送信息
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * MQ主题：中奖发货单
     *
     * 启动zk：bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
     * 启动kafka：bin/kafka-server-start.sh -daemon config/server.properties
     * 创建topic：bin/kafka-topics.sh --create --zookeeper 172.17.0.5:2181 --replication-factor 1 --partitions 1 --topic lottery_invoice
     */
    public static final String TOPIC_INVOICE = "lottery_invoice";

    /**
     * MQ主题：活动领取记录
     *
     * 创建topic：bin/kafka-topics.sh --create --zookeeper 172.17.0.5:2181 --replication-factor 1 --partitions 1 --topic lottery_activity_partake
     */
    public static final String TOPIC_ACTIVITY_PARTAKE = "lottery_activity_partake";



}
