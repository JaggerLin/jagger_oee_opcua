package cn.madchick.oee.test.domain;

import cn.madchick.oee.domain.opcua.model.req.OpcUaMiningReq;
import cn.madchick.oee.domain.opcua.service.mining.IOpcUaClientMining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DoOpcUa {
    private Logger logger = LoggerFactory.getLogger(DoOpcUa.class);

    @Resource
    private IOpcUaClientMining opcUaClientMining;

    @Resource
    ExecutorService threadPoolExecutor01;

    @Test
    public void test_mining(){
        OpcUaMiningReq jaggerReq = new OpcUaMiningReq("jagger", 120981321L, "111");

        opcUaClientMining.doOpcUaMining(jaggerReq,threadPoolExecutor01);
    }
}
