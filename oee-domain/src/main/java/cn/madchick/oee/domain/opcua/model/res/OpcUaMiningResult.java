package cn.madchick.oee.domain.opcua.model.res;

import cn.madchick.oee.common.Constants;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/29
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcUaMiningResult {
    /** 用户id **/
    private String uId;
    /** 策略id **/
    private Long strategyId;
    /** 活动状态 **/
    private Integer state = Constants.ActivityState.DOING.getCode();
}
