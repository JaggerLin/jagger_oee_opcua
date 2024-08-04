package cn.madchick.oee.domain.opcua.service.mining.impl;


import cn.madchick.oee.domain.opcua.model.aggregates.StrategyRich;
import cn.madchick.oee.domain.opcua.model.req.OpcUaDelReq;
import cn.madchick.oee.domain.opcua.model.res.OpcUaMiningResult;
import cn.madchick.oee.domain.opcua.model.vo.StrategyBriefVO;
import cn.madchick.oee.domain.opcua.model.vo.StrategyDetailBriefVO;
import cn.madchick.oee.domain.opcua.service.mining.AbstractDoOpcUaMining;

import cn.madchick.oee.domain.opcua.service.mining.IOpcUaSamplingTask;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;


/**
 * @description: OPC UA 订阅服务
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
@Service("doOpcUaMining")
public class DoOpcUaMiningImpl extends AbstractDoOpcUaMining {

    private final Logger logger = LoggerFactory.getLogger(DoOpcUaMiningImpl.class);

    /** 装配IOC容器中的Opc客户端Bean对象 **/
    @Resource
    OpcUaClient opcUaClient;

    @Override
    protected ManagedSubscription doMiningService(Map<Long, List<String>> strategyNodeMap, ExecutorService threadPoolExecutor){

        // 创建订阅
        ManagedSubscription subscription = null;

        for (Long strategyId : strategyNodeMap.keySet()){
            // 获取策略详情
            StrategyRich strategyRich = super.queryStrategyRich(strategyId);
            StrategyDetailBriefVO strategyDetail = strategyRich.getStrategyDetail();
            //根据策略id取出节点列表
            List<String> identifiers = strategyNodeMap.get(strategyId);
            logger.info("初始化：策略{}准备执行采样服务的节点列表：{}", strategyId, identifiers);
            // 根据策略ID获取不同的采集服务
            subscription = getManagedSubscription(threadPoolExecutor, strategyId, strategyDetail, identifiers);
        }

        logger.info("初始化：采集初始化服务执行成功");
        return subscription;

    }

    @Override
    protected ManagedSubscription doMiningService(Long strategyId, Map<Long, List<String>> strategyNodeMap, ExecutorService threadPoolExecutor) {

        // 获取策略详情
        StrategyRich strategyRich = super.queryStrategyRich(strategyId);
        StrategyDetailBriefVO strategyDetail = strategyRich.getStrategyDetail();
        //根据策略id取出节点列表
        List<String> identifiers = strategyNodeMap.get(strategyId);
        logger.info("新增订阅：准备执行采样服务的节点列表：{}", identifiers);
        // 根据策略ID获取不同的采集服务
        ManagedSubscription subscription = getManagedSubscription(threadPoolExecutor, strategyId, strategyDetail, identifiers);

        logger.info("新增订阅：采集服务执行成功");
        return subscription;
    }

    @Override
    protected void stopMiningService(ManagedSubscription subscription, List<String> reqNodes) {
        IOpcUaSamplingTask miningTaskService = distributionMiningTaskFactory.getMiningTaskService(1);
        miningTaskService.deleteSubscribeItems(subscription, reqNodes);
        logger.info("停止：成功停止当前活动节点{}", reqNodes);
    }

    private ManagedSubscription getManagedSubscription(ExecutorService threadPoolExecutor, Long strategyId, StrategyDetailBriefVO strategyDetail, List<String> identifiers) {
        ManagedSubscription subscription;
        IOpcUaSamplingTask miningTaskService;
        if (strategyId == 10001L){
            // 工厂模式匹配到对应的采集服务
            miningTaskService = distributionMiningTaskFactory.getMiningTaskService(1);
            // 创建订阅
            subscription = miningTaskService.createSubscription(opcUaClient, strategyDetail.getFrequency());
            strategySubscriptionMap.put(strategyId, subscription);
            // 线程池执行订阅监听器(只需要执行一个)
            miningTaskService.runSubscriptionListenerInThreadPool(opcUaClient, threadPoolExecutor);
        }
        else {
            miningTaskService = distributionMiningTaskFactory.getMiningTaskService(2);
            subscription = miningTaskService.createSubscription(opcUaClient, strategyDetail.getFrequency());
            strategySubscriptionMap.put(strategyId, subscription);
        }
        // 线程池执行数据采集服务
        miningTaskService.runSubscriptionInThreadPool(subscription, identifiers, threadPoolExecutor);
        return subscription;
    }

}
