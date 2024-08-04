package cn.madchick.oee.infrastructure.po;

import java.util.Date;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcMiningTask {
    /** 自增id **/
    private Long id;
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
    /** 创建时间 **/
    private Date createTime;
    /** 更新时间 **/
    private Date updateTime;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
