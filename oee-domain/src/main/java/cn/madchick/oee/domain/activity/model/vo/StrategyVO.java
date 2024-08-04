package cn.madchick.oee.domain.activity.model.vo;

import java.util.List;

/**
 * @description: 策略信息配置
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class StrategyVO {
    private Long strategyId;
    private Integer strategyMode;
    private String strategyDesc;
    private String extInfo;
    // 策略详情配置
    private List<StrategyDetailVO> strategyDetailList;

    public Long getStrategyId(){return strategyId;}
    public void setStrategyId(Long strategyId){this.strategyId = strategyId;}
    public Integer getStrategyMode(){return strategyMode;}
    public void setStrategyMode(Integer strategyMode){this.strategyMode = strategyMode;}
    public String getStrategyDesc(){return strategyDesc;}
    public void setStrategyDesc(String strategyDesc){this.strategyDesc = strategyDesc;}
    public String getExtInfo(){return extInfo;}
    public void setExtInfo(String extInfo){this.extInfo = extInfo;}

    public List<StrategyDetailVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }

    @Override
    public String toString(){
        return "StrategyVO{" +
                "strategyId=" + strategyId +
                ", strategyMode=" + strategyMode +
                ", strategyDesc='" + strategyDesc + '\'' +
                ", extInfo='" + extInfo + '\'' +
                '}';
    }
}
