package cn.madchick.oee.domain.opcua.model.vo;

import java.util.Date;

/**
 * @description: 策略简要信息
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
public class StrategyBriefVO {

    private Long strategyId;
    private Integer strategyMode;
    private String strategyDesc;
    private String extInfo;

    public Long getStrategyId(){return strategyId;}
    public void setStrategyId(Long strategyId){this.strategyId = strategyId;}
    public Integer getStrategyMode(){return strategyMode;}
    public void setStrategyMode(Integer strategyMode){this.strategyMode = strategyMode;}
    public String getStrategyDesc(){return strategyDesc;}
    public void setStrategyDesc(String strategyDesc){this.strategyDesc = strategyDesc;}
    public String getExtInfo(){return extInfo;}
    public void setExtInfo(String extInfo){this.extInfo = extInfo;}

    @Override
    public String toString() {
        return "StrategyBriefVO{" +
                "strategyId=" + strategyId +
                ", strategyMode=" + strategyMode +
                ", strategyDesc='" + strategyDesc + '\'' +
                ", extInfo='" + extInfo + '\'' +
                '}';
    }
}
