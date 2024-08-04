package cn.madchick.oee.domain.opcua.model.vo;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcUaMiningVO {
    /** 任务id **/
    private Long taskId;
    /** 节点id **/
    private Long nodeId;
    /** 节点名称**/
    private String nodeName;
    /** 活动id **/
    private Long activityId;
    /** 活动名称 **/
    private String activityName;
    /** 策略id **/
    private Long strategyId;
    /** 采集启用 **/
    private Boolean opcEnabled;
    /** 防重ID **/
    private String uuid;


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Boolean getOpcEnabled() {
        return opcEnabled;
    }

    public void setOpcEnabled(Boolean opcEnabled) {
        this.opcEnabled = opcEnabled;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "OpcUaMiningVO{" +
                "taskId=" + taskId +
                ", nodeId=" + nodeId +
                ", nodeName='" + nodeName + '\'' +
                ", activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", strategyId=" + strategyId +
                ", opcEnabled=" + opcEnabled +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
