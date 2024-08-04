package cn.madchick.oee.domain.opcua.service.mining;

import cn.madchick.oee.domain.opcua.model.aggregates.StrategyRich;
import cn.madchick.oee.domain.opcua.repository.IOpcUaMiningRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/7/27
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcUaMiningSupport {
    @Resource
    protected IOpcUaMiningRepository opcUaMiningRepository;

    protected StrategyRich queryStrategyRich(Long strategyId){
        return opcUaMiningRepository.queryStrategyRich(strategyId);
    }

    protected List<Long> getStrategyIdList(Long activityId){
        return opcUaMiningRepository.getStrategyIdList(activityId);
    }

    protected List<String> getNodeList(Long strategyId, Long activityId){
        return opcUaMiningRepository.getNodeList(strategyId, activityId);
    }
}
