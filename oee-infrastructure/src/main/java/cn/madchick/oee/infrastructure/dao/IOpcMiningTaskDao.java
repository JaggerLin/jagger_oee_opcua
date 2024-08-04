package cn.madchick.oee.infrastructure.dao;

import cn.madchick.oee.infrastructure.po.OpcMiningTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@Mapper
public interface IOpcMiningTaskDao {
    /** 插入采集任务 **/
    void insert(OpcMiningTask opcMiningTask);

    /** 查询当前节点是否跟已存在活动中 **/
    OpcMiningTask queryNodeExistsActivity(OpcMiningTask opcMiningTask);

    /** 查询当前活动id对应的策略列表 **/
    List<Long>  queryStrategyIdList(Long activityId);

    /** 查询当前活动id和策略id对应的节点列表 **/
    List<String> queryNodeList(Long strategyId, Long activityId);
}
