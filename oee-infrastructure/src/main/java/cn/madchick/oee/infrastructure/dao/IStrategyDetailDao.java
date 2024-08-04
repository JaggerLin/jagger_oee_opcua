package cn.madchick.oee.infrastructure.dao;

import cn.madchick.oee.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 策略明细表DAO
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Mapper
public interface IStrategyDetailDao {
    /**
     * 查询策略表详细配置列表
     * @param strategyId 策略ID
     * @return           返回结果
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询策略表详细配置
     * @param strategyId 策略ID
     * @return           返回结果
     */
    StrategyDetail queryStrategyDetail(Long strategyId);
    /**
     * 插入策略配置组
     *
     *
     */
    void insertList(List<StrategyDetail> list);
}
