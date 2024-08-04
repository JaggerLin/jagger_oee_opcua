package cn.madchick.oee.infrastructure.repository;

import cn.madchick.oee.domain.activity.model.vo.OpcMiningTaskVO;
import cn.madchick.oee.domain.activity.repository.IOpcMiningTaskRepository;
import cn.madchick.oee.infrastructure.dao.IActivityDao;
import cn.madchick.oee.infrastructure.dao.IOpcMiningTaskDao;
import cn.madchick.oee.infrastructure.po.OpcMiningTask;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@Repository
public class OpcMiningTaskRepository implements IOpcMiningTaskRepository {

    @Resource
    private IActivityDao iActivityDao;

    @Resource
    private IOpcMiningTaskDao opcMiningTaskDao;

    @Override
    public void configActivity(Long activityId, String activityName, Long strategyId, Long nodeId, String nodeName, Long taskId, Boolean opcEnabled) {
        OpcMiningTask opcMiningTask = new OpcMiningTask();

        opcMiningTask.setNodeId(nodeId);
        opcMiningTask.setTaskId(taskId);
        opcMiningTask.setActivityId(activityId);
        opcMiningTask.setActivityName(activityName);
        opcMiningTask.setOpcEnabled(opcEnabled);
        opcMiningTask.setStrategyId(strategyId);
        String uuid = activityId + "_" + taskId;
        opcMiningTask.setUuid(uuid);
        opcMiningTask.setNodeName(nodeName);

        opcMiningTaskDao.insert(opcMiningTask);
    }

    @Override
    public OpcMiningTaskVO queryNodeExistsActivity(Long nodeId) {
        OpcMiningTask opcMiningTask = new OpcMiningTask();

        opcMiningTask.setNodeId(nodeId);

        OpcMiningTask noNodeTakeActivityOrder = opcMiningTaskDao.queryNodeExistsActivity(opcMiningTask);
        // 未查询到符合的领取单，直接返回 NULL
        if (null == noNodeTakeActivityOrder) {
            return null;
        }
        //查到继续
        OpcMiningTaskVO opcMiningTaskVO = new OpcMiningTaskVO();
        opcMiningTaskVO.setActivityId(noNodeTakeActivityOrder.getActivityId());
        opcMiningTaskVO.setTaskId(noNodeTakeActivityOrder.getTaskId());
        opcMiningTaskVO.setStrategyId(noNodeTakeActivityOrder.getStrategyId());
        opcMiningTaskVO.setOpcEnabled(noNodeTakeActivityOrder.getOpcEnabled());

        return opcMiningTaskVO;
    }
}
