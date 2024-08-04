package cn.madchick.oee.domain.opcua.model.aggregates;

import cn.madchick.oee.domain.opcua.model.vo.StrategyBriefVO;
import cn.madchick.oee.domain.opcua.model.vo.StrategyDetailBriefVO;

import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
public class StrategyRich {
    /** 策略id **/
    private Long strategyId;
    /** 策略配置 **/
    private StrategyBriefVO strategy;
    /** 策略明细 **/
    private StrategyDetailBriefVO strategyDetail;
    /** 策略明细列表 **/
    private List<StrategyDetailBriefVO> strategyDetailList;

    public StrategyRich(){}

    public StrategyRich(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyRich(Long strategyId, StrategyBriefVO strategy, StrategyDetailBriefVO strategyDetail) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetail = strategyDetail;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyBriefVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyBriefVO strategy) {
        this.strategy = strategy;
    }

    public StrategyDetailBriefVO getStrategyDetail() {
        return strategyDetail;
    }

    public void setStrategyDetail(StrategyDetailBriefVO strategyDetail) {
        this.strategyDetail = strategyDetail;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}
