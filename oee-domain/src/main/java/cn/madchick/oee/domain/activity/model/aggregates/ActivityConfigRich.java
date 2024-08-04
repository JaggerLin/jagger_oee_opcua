package cn.madchick.oee.domain.activity.model.aggregates;

import cn.madchick.oee.domain.activity.model.vo.ActivityVO;
import cn.madchick.oee.domain.activity.model.vo.NodeVO;
import cn.madchick.oee.domain.activity.model.vo.StrategyVO;

import java.util.List;

/**
 * @description: 活动配置聚合信息
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class ActivityConfigRich {
    /** 活动配置 */
    private ActivityVO activity;

    /** 策略配置(含策略明细) */
    private StrategyVO strategy;

    /** 节点配置 */
    private List<NodeVO> nodeList;

    public ActivityConfigRich() {
    }

    public ActivityConfigRich(ActivityVO activity, StrategyVO strategy, List<NodeVO> nodeList) {
        this.activity = activity;
        this.strategy = strategy;
        this.nodeList = nodeList;
    }

    public ActivityVO getActivity() {return activity;}
    public void setActivity(ActivityVO activity) {this.activity = activity;}
    public StrategyVO getStrategy() {return strategy;}
    public void setStrategy(StrategyVO strategy) {this.strategy = strategy;}
    public List<NodeVO> getNodeList() {return nodeList;}
    public void setNodeList(List<NodeVO> nodeList) {this.nodeList = nodeList;}

}
