package cn.madchick.oee.domain.activity.model.res;

import cn.madchick.oee.common.Result;
/**
 * @description: opc采集任务配置结果
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class OpctaskResult extends Result{
    /** 任务id **/
    private Long taskId;
    /** 策略id **/
    private Long strategyId;

    public OpctaskResult(String code, String info) {
        super(code,info);
    }

    public Long getTaskId() {return taskId;}
    public void setTaskId(Long taskId) {this.taskId = taskId;}
    public Long getStrategyId(){return strategyId;}
    public void setStrategyId(Long strategyId){this.strategyId = strategyId;}
}
