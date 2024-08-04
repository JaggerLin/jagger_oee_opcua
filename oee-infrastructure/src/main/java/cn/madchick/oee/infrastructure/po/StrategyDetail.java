package cn.madchick.oee.infrastructure.po;

import java.util.Date;

/**
 * @description: 策略详情表
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class StrategyDetail {
    /**
     * 自增ID、策略ID、节点ID、节点名称、频率、创建时间、更新时间
     */
    private Long id;
    private Long strategyId;
    private Integer frequency;
    private Integer threshold;
    private Date beginTime;
    private Date endTime;
    private Date createTime;
    private Date updateTime;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
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
    public Date getCreateTime() {return createTime;}
    public void setCreateTime(Date createTime) {this.createTime = createTime;}
    public Date getUpdateTime() {return updateTime;}
    public void setUpdateTime(Date updateTime) {this.updateTime = updateTime;}
}
