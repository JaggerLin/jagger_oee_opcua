package cn.madchick.oee.domain.activity.repository;

import cn.madchick.oee.domain.activity.model.vo.OpcMiningTaskVO;

/**
 * @description: 节点配置任务仓储接口
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public interface IOpcMiningTaskRepository {

    /** 任务配置到活动中 **/
    void configActivity(Long activityId, String activityName, Long strategyId, Long nodeId, String nodeName, Long taskId, Boolean opcEnabled);

    /** 查询节点是否已存在活动中 **/
    OpcMiningTaskVO queryNodeExistsActivity(Long nodeId);
}
