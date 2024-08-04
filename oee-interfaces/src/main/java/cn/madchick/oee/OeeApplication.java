package cn.madchick.oee;

import cn.madchick.oee.domain.opcua.model.req.OpcUaMiningReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaStopReq;
import cn.madchick.oee.domain.opcua.service.mining.IOpcUaClientMining;
import cn.madchick.oee.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.redisson.api.RTopic;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/18
 * @Copyright： jagger - www.madchick.cn
 */
@SpringBootApplication
@Configurable
@EnableDubbo
public class OeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OeeApplication.class, args);
        }

    @Resource
    private IOpcUaClientMining opcUaClientMining;

    @Resource
    private RTopic dynamicThreadPoolRedisTopic;

    @Bean
    public ApplicationRunner applicationRunner(ExecutorService threadPoolExecutor01) {
        ThreadPoolConfigEntity threadPoolConfigEntity = new ThreadPoolConfigEntity("oee-main-application", "threadPoolExecutor01");
        threadPoolConfigEntity.setPoolSize(6);
        threadPoolConfigEntity.setMaximumPoolSize(10);
        dynamicThreadPoolRedisTopic.publish(threadPoolConfigEntity);

        return args -> {
            OpcUaMiningReq jaggerReq = new OpcUaMiningReq("jagger", 120981321L, "111");
            opcUaClientMining.doOpcUaMining(jaggerReq, threadPoolExecutor01);

            Thread.sleep(10000);

            OpcUaStopReq stopReq = new OpcUaStopReq("jagger", 120981321L);
            opcUaClientMining.stopOpcUaMining(stopReq);

            OpcUaMiningReq jaggerReq1 = new OpcUaMiningReq("lzg", 120981321L, "111");
            opcUaClientMining.doOpcUaMining(jaggerReq1, threadPoolExecutor01);
        };
    }
}
