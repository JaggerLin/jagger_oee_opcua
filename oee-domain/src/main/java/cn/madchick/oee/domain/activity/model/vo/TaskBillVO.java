package cn.madchick.oee.domain.activity.model.vo;

/**
 * @description: 任务账单【状态】
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class TaskBillVO {
    /** 节点ID */
    private Long nodeId;
    /** 节点名称 */
    private String nodeName;
    /** 节点启用状态 */
    private Boolean opcEnabled;
    /** activity 活动ID */
    private Long activityId;
    /** activity 活动名称 */
    private String activityName;
    /** 策略ID */
    private Long strategyId;
    /** 活动状态 */
    private Integer state;

    public Long getNodeId() {return nodeId;}
    public void setNodeId(Long nodeId) {this.nodeId = nodeId;}
    public String getNodeName() {return nodeName;}
    public void setNodeName(String nodeName) {this.nodeName = nodeName;}
    public Boolean getOpcEnabled() {return opcEnabled;}
    public void setOpcEnabled(Boolean opcEnabled) {this.opcEnabled = opcEnabled;}
    public Long getActivityId() {return activityId;}
    public void setActivityId(Long activityId) {this.activityId = activityId;}
    public String getActivityName() {return activityName;}
    public void setActivityName(String activityName) {this.activityName = activityName;}
    public Long getStrategyId() {return strategyId;}
    public void setStrategyId(Long strategyId) {this.strategyId = strategyId;}
    public Integer getState() {return state;}
    public void setState(Integer state) {this.state = state;}

    @Override
    public String toString() {
        return "TaskBillVO{" +
                "nodeId=" + nodeId +
                ", nodeName='" + nodeName + '\'' +
                ", opcEnabled=" + opcEnabled +
                ", activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", strategyId=" + strategyId +
                ", state=" + state +
                '}';
    }
}
