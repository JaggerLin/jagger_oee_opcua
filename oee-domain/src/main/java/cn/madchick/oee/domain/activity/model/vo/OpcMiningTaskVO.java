package cn.madchick.oee.domain.activity.model.vo;

/**
 * @description: 节点配置记录
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcMiningTaskVO {

    /** 任务id **/
    private Long taskId;
    /** 活动id **/
    private Long activityId;
    /** 策略id **/
    private Long strategyId;
    /** 采集启用 **/
    private Boolean opcEnabled;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    @Override
    public String toString() {
        return "OpcMiningTaskVO{" +
                "taskId=" + taskId +
                ", activityId=" + activityId +
                ", strategyId=" + strategyId +
                ", opcEnabled=" + opcEnabled +
                '}';
    }
}
