package cn.madchick.oee.domain.support.kafka.producer;

import cn.madchick.oee.domain.activity.model.vo.NodeRecordVO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @description: MQ 发送消息服务
 * @author：jagger
 * @date: 2024/8/5
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * MQ主题：opc采集到的实时数据
     * 创建kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic OPC_HeartBeat
     * 创建kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic OPC_Threshold
     * 1) zkServer **** 2) 进入到Kafka安装目录:.\bin\windows\kafka-server-start.bat .\config\server.properties **** 3) 以管理员权限新开:kafka-topics.bat --bootstrap-server localhost:9092 --list
     */
    public static final String TOPIC_HeartBeat = "OPC_HeartBeat";

    public static final String TOPIC_Threshold = "OPC_Threshold";

    /**
     * 发送心跳采集消息
     * @param nodeRecord 消息记录
     */
    public ListenableFuture<SendResult<String, Object>> sendOpcHeartbeatData(NodeRecordVO nodeRecord) {
        String objJson = JSON.toJSONString(nodeRecord);
        logger.info("topic:{} 发送心跳采集消息到MQ，消息内容：{}", TOPIC_HeartBeat ,objJson);
        return kafkaTemplate.send(TOPIC_HeartBeat, objJson);
    };

    /**
     * 发送阈值采集消息
     * @param nodeRecord 消息记录
     */
    public ListenableFuture<SendResult<String, Object>> sendOpcThresholdData(NodeRecordVO nodeRecord) {
        String objJson = JSON.toJSONString(nodeRecord);
        logger.info("topic:{} 发送阈值采集消息到MQ，消息内容：{}", TOPIC_Threshold ,objJson);
        return kafkaTemplate.send(TOPIC_Threshold, objJson);
    };
}
