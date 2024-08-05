package cn.madchick.oee.test.domain;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.common.Result;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityConfigRich;
import cn.madchick.oee.domain.activity.model.req.ActivityConfigReq;
import cn.madchick.oee.domain.activity.model.vo.ActivityVO;
import cn.madchick.oee.domain.activity.model.vo.NodeVO;
import cn.madchick.oee.domain.activity.model.vo.StrategyDetailVO;
import cn.madchick.oee.domain.activity.model.vo.StrategyVO;
import cn.madchick.oee.domain.activity.service.depoly.IActivityDeploy;
import cn.madchick.oee.domain.activity.service.stateflow.IStateHandler;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTest {
    private Logger logger = LoggerFactory.getLogger(ActivityTest.class);

    // 通过@Resource注解，将IActivityDeploy注入到ActivityTest中, 用于测试
    @Resource
    private IActivityDeploy activityDeploy;

    // 通过@Resource注解，将IStateHandler注入到ActivityTest中, 用于测试
    @Resource
    private IStateHandler stateHandler;

    // ActivityConfigRich是一个领域模型，用于封装活动配置信息
    private ActivityConfigRich activityConfigRich;

    // 活动ID
    private Long activityId = 120981321L;

    // 初始化方法
    @Before
    public void init() {
        // 创建一个活动对象
        ActivityVO activity = new ActivityVO();
        activity.setActivityId(activityId);
        activity.setActivityName("测试MD3-1#机采集");
        activity.setArea("MD3");
        activity.setState(Constants.ActivityState.EDIT.getCode());
        activity.setPasswd("12345");
        activity.setCreator("jagger");

        // 创建一个策略对象
        StrategyVO strategy = new StrategyVO();
        strategy.setStrategyId(10002L);
        strategy.setStrategyDesc("Mining Strategy");
        strategy.setStrategyMode(Constants.StrategyMode.HUNGRY.getCode());
        strategy.setExtInfo("nothing");

        // 创建一个策略详细对象
        StrategyDetailVO strategyDetail_01 = new StrategyDetailVO();
        strategyDetail_01.setStrategyId(strategy.getStrategyId());
        strategyDetail_01.setBeginTime(new Date());
        strategyDetail_01.setEndTime(new Date());
        strategyDetail_01.setFrequency(1000);
        strategyDetail_01.setThreshold(60000);

        // 创建一个策略详细对象
        StrategyDetailVO strategyDetail_02 = new StrategyDetailVO();
        strategyDetail_02.setStrategyId(strategy.getStrategyId());
        strategyDetail_02.setBeginTime(new Date());
        strategyDetail_02.setEndTime(new Date());
        strategyDetail_02.setFrequency(1000);
        strategyDetail_02.setThreshold(60000);

        // 创建一个list，用于存放策略详细对象
        List<StrategyDetailVO> strategyDetails = new ArrayList<>();
        strategyDetails.add(strategyDetail_01);
        strategyDetails.add(strategyDetail_02);
        // 将策略详细对象存放到策略对象中
        strategy.setStrategyDetailList(strategyDetails);

        // 创建一个node节点
        NodeVO nodeVO_01 = new NodeVO();
        nodeVO_01.setNodeId(30001L);
        nodeVO_01.setNodeName("MD3-1#机");
        nodeVO_01.setArea("MD3");
        nodeVO_01.setConnectStatus(Constants.ConnectStatus.BAD.getCode());
        nodeVO_01.setDeviceCode("P3001");
        nodeVO_01.setSensor(Boolean.TRUE);
        nodeVO_01.setIpAddress("192.168.0.1");
        nodeVO_01.setOpcEnabled(Boolean.TRUE);
        nodeVO_01.setNodeType("Boolean");
        nodeVO_01.setScanCycle(1000);

        // 创建一个node节点
        NodeVO nodeVO_02 = new NodeVO();
        nodeVO_02.setNodeId(30002L);
        nodeVO_02.setNodeName("MD3-2#机");
        nodeVO_02.setArea("MD3");
        nodeVO_02.setOpcEnabled(Boolean.FALSE);
        nodeVO_02.setDeviceCode("P3002");
        nodeVO_02.setSensor(Boolean.TRUE);
        nodeVO_02.setIpAddress("192.168.1.0");
        nodeVO_02.setConnectStatus(Constants.ConnectStatus.BAD.getCode());
        nodeVO_02.setNodeType("Boolean");
        nodeVO_02.setScanCycle(1000);

        // 创建一个nodeIdList，用于存放nodeId
        List<NodeVO> nodeList = new ArrayList<>();
        nodeList.add(nodeVO_01);
        nodeList.add(nodeVO_02);

        // 创建一个ActivityConfigRich对象，用于存放活动配置信息
        activityConfigRich = new ActivityConfigRich(activity, strategy, nodeList);
    }

    @Test
    public void test_createActivity() {
        // 调用activityDeploy的createActivity方法，创建活动
        activityDeploy.createActivity(new ActivityConfigReq(activityId, activityConfigRich));
    }

    @Test
    public void test_alterState() {
        Result arraignment = stateHandler.arraignment(20243001L, Constants.ActivityState.EDIT);
        logger.info("提交审核，测试：{}", JSON.toJSONString(arraignment));
        logger.info("审核通过，测试：{}", JSON.toJSONString(stateHandler.checkPass(20243001L, Constants.ActivityState.ARRAIGNMENT)));
        logger.info("运行活动，测试：{}", JSON.toJSONString(stateHandler.doing(20243001L, Constants.ActivityState.PASS)));
        logger.info("二次提审，测试：{}", JSON.toJSONString(stateHandler.checkPass(20243001L, Constants.ActivityState.EDIT)));
    }

}
