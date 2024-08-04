package cn.madchick.oee.domain.activity.model.req;

/**
 * @description: opc采集任务请求
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class OpctaskReq {
    /** 节点id **/
    private Long nodeId;
    /** 活动id **/
    private Long activityId;
    /** 策略id **/
    private Long strategyId;

    public OpctaskReq() {
    }
    /** 默认策略构造函数 **/
    public OpctaskReq(Long nodeId, Long activityId) {
        this.nodeId = nodeId;
        this.activityId = activityId;
    }
    /** 指定所有 **/
    public OpctaskReq(Long nodeId, Long activityId, Long strategyId) {
        this.nodeId = nodeId;
        this.activityId = activityId;
        this.strategyId = strategyId;
    }

    public Long getNodeId() {return nodeId;}
    public void setNodeId(Long nodeId) {this.nodeId = nodeId;}
    public Long getActivityId() {return activityId;}
    public void setActivityId(Long activityId) {this.activityId = activityId;}
    public Long getStrategyId() {return strategyId;}
    public void setStrategyId(Long strategyId) {this.strategyId = strategyId;}
}
