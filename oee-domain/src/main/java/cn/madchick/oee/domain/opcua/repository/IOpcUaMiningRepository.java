package cn.madchick.oee.domain.opcua.repository;

import cn.madchick.oee.domain.opcua.model.aggregates.StrategyRich;

import java.util.List;

/**
 * @description: OPC任务表仓储服务部
 * @author：jagger
 * @date: 2024/6/29
 * @Copyright： jagger - www.madchick.cn
 */
public interface IOpcUaMiningRepository {

    /**
     * 获取策略id列表
     * @param activityId 活动id
     * @return 策略id列表
     */
    List<Long> getStrategyIdList(Long activityId);
    /**
     * 获取节点列表
     * @param activityId 活动id, strategyId 策略id
     * @return 节点列表
     */
    List<String> getNodeList(Long strategyId, Long activityId);

    /**
     * 根据节点id获取采集任务中的节点名称
     * @param nodeId 节点id
     * @return 节点名称
     */
    String getNode(Long nodeId);

    /**
     * 获取策略详细
     * @param strategyId 策略id
     * @return 策略详细
     */
    StrategyRich queryStrategyRich(Long strategyId);
}
