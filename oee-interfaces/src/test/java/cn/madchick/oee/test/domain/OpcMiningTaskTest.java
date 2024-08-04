package cn.madchick.oee.test.domain;

import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.res.OpctaskResult;
import cn.madchick.oee.domain.activity.service.opctask.IActivityOpctask;
import cn.madchick.oee.infrastructure.dao.IOpcMiningTaskDao;
import cn.madchick.oee.infrastructure.po.OpcMiningTask;
import com.alibaba.fastjson2.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OpcMiningTaskTest {
    private Logger logger = LoggerFactory.getLogger(OpcMiningTaskTest.class);

    @Resource
    private IOpcMiningTaskDao iOpcMiningTaskDao;

    @Resource
    private IActivityOpctask opctask;

    @Test
    public void test_insert(){
        OpcMiningTask opcMiningTask = new OpcMiningTask();

        opcMiningTask.setTaskId(100001L);
        opcMiningTask.setNodeId(30001L);
        opcMiningTask.setActivityId(120981321L);
        opcMiningTask.setActivityName("MD3测试");
        opcMiningTask.setNodeName("xxxx-test");
        opcMiningTask.setOpcEnabled(true);
        opcMiningTask.setStrategyId(10111L);
        opcMiningTask.setUuid("unkonwjioj1001");

        iOpcMiningTaskDao.insert(opcMiningTask);
    }

    @Test
    public void test_opctask(){
        OpctaskReq opctaskReq = new OpctaskReq(30005L,120981321L,10001L);

        OpctaskResult opctaskResult = opctask.configOpcTask(opctaskReq);
        logger.info("请求结果:{}" , JSON.toJSONString(opctaskResult));
    }
}
