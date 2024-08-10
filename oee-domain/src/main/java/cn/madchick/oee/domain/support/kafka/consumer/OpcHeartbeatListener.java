package cn.madchick.oee.domain.support.kafka.consumer;

import cn.madchick.oee.domain.activity.model.vo.NodeRecordVO;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @description:
 * @author：jagger
 * @date: 2024/8/5
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class OpcHeartbeatListener {

    private Logger logger = LoggerFactory.getLogger(OpcHeartbeatListener.class);

    @KafkaListener(topics = "OPC_HeartBeat", groupId = "Oee")
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        Optional<?> message = Optional.ofNullable(record.value());
        // 0. 实际场景在消费MQ的时候，可以通过固定标识判断是否已经消费过，添加记录。对程序的处理会起到优化作用。

        // 1. 判断消息是否存在
        if (!message.isPresent()) {
            return;
        }
        // 2. 转化对象（或者你也可以重写Serializer<T>）
        NodeRecordVO nodeRecordVO = JSON.parseObject((String) message.get(), NodeRecordVO.class);
        logger.info("消费MQ消息,异步落库 message：{}", message.get());
        // 3. 更新数据库库存【实际场景业务体量较大，可能也会由于MQ消费引起并发，对数据库产生压力，所以如果并发量较大，可以把库存记录缓存中，并使用定时任务进行处理缓存和数据库库存同步，减少对数据库的操作次数】

        // 4. 消息消费完成
        ack.acknowledge();
    }
}
