package cn.madchick.oee.infrastructure.po;

import java.util.Date;

/**
 * @description: 采集策略表
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class Strategy {
    /**
     * 自增ID、策略ID、策略描述、策略方式、扩展信息、创建时间、修改时间
     */
    private Long id;
    private Long strategyId;
    private Integer strategyMode;
    private String strategyDesc;
    private Date createTime;
    private Date updateTime;
    private String extInfo;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public Long getStrategyId(){return strategyId;}
    public void setStrategyId(Long strategyId){this.strategyId = strategyId;}
    public Integer getStrategyMode(){return strategyMode;}
    public void setStrategyMode(Integer strategyMode){this.strategyMode = strategyMode;}
    public String getStrategyDesc(){return strategyDesc;}
    public void setStrategyDesc(String strategyDesc){this.strategyDesc = strategyDesc;}
    public Date getCreateTime(){return createTime;}
    public void setCreateTime(Date createTime){this.createTime = createTime;}
    public Date getUpdateTime(){return updateTime;}
    public void setUpdateTime(Date updateTime){this.updateTime = updateTime;}
    public String getExtInfo(){return extInfo;}
    public void setExtInfo(String extInfo){this.extInfo = extInfo;}
}
