package cn.madchick.oee.domain.activity.model.vo;

import java.util.Date;

/**
 * @description: 策略详情配置
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class StrategyDetailVO {
    private Long strategyId;
    private Integer frequency;
    private Integer threshold;
    private Date beginTime;
    private Date endTime;

    public Long getStrategyId() {return strategyId;}
    public void setStrategyId(Long strategyId) {this.strategyId = strategyId;}
    public Integer getFrequency() {return frequency;}
    public void setFrequency(Integer frequency) {this.frequency = frequency;}
    public Integer getThreshold() {return threshold;}
    public void setThreshold(Integer threshold) {this.threshold = threshold;}
    public Date getBeginTime() {return beginTime;}
    public void setBeginTime(Date beginTime) {this.beginTime = beginTime;}
    public Date getEndTime() {return endTime;}
    public void setEndTime(Date endTime) {this.endTime = endTime;}

    @Override
    public String toString() {
        return "StrategyDetailVO{" +
                "strategyId=" + strategyId +
                ", frequency=" + frequency +
                ", threshold=" + threshold +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
