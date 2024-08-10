package cn.madchick.oee.infrastructure.repository;

import cn.madchick.oee.domain.opcua.model.aggregates.OpcUaMiningRich;
import cn.madchick.oee.domain.opcua.model.aggregates.StrategyRich;
import cn.madchick.oee.domain.opcua.model.vo.StrategyBriefVO;
import cn.madchick.oee.domain.opcua.model.vo.StrategyDetailBriefVO;
import cn.madchick.oee.domain.opcua.repository.IOpcUaMiningRepository;
import cn.madchick.oee.infrastructure.dao.IOpcMiningTaskDao;
import cn.madchick.oee.infrastructure.dao.IStrategyDao;
import cn.madchick.oee.infrastructure.dao.IStrategyDetailDao;
import cn.madchick.oee.infrastructure.po.Strategy;
import cn.madchick.oee.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: OPCua采集服务仓储实现
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
@Repository
public class OpcUaMiningRepository implements IOpcUaMiningRepository {

    @Resource
    private IOpcMiningTaskDao opcMiningTaskDao;

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    /** 查询当前活动id对应的策略列表 **/
    @Override
    public List<Long> getStrategyIdList(Long activityId) {
        return opcMiningTaskDao.queryStrategyIdList(activityId);
    }

    /** 查询当前活动id和策略id对应的节点列表 **/
    @Override
    public List<String> getNodeList(Long strategyId, Long activityId) {
        return opcMiningTaskDao.queryNodeList(strategyId, activityId);
    }

    @Override
    public String getNode(Long nodeId) {
        return "";
    }

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        // 根据策略id查询策略和策略详情
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        StrategyDetail strategyDetail = strategyDetailDao.queryStrategyDetail(strategyId);
        // 组装策略和策略详情
        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        strategyBriefVO.setStrategyId(strategy.getStrategyId());
        strategyBriefVO.setStrategyDesc(strategy.getStrategyDesc());
        strategyBriefVO.setStrategyMode(strategy.getStrategyMode());
        strategyBriefVO.setExtInfo(strategy.getExtInfo());

        StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
        strategyDetailBriefVO.setStrategyId(strategyDetail.getStrategyId());
        strategyDetailBriefVO.setFrequency(strategyDetail.getFrequency());
        strategyDetailBriefVO.setThreshold(strategyDetail.getThreshold());
        strategyDetailBriefVO.setBeginTime(strategyDetail.getBeginTime());
        strategyDetailBriefVO.setEndTime(strategyDetail.getEndTime());

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVO);
    }

}
