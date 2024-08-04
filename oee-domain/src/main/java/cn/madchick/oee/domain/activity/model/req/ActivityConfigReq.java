package cn.madchick.oee.domain.activity.model.req;

import cn.madchick.oee.domain.activity.model.aggregates.ActivityConfigRich;

/**
 * @description: 活动配置请求对象
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class ActivityConfigReq {

    /** 活动ID */
    private Long activityId;

    /** 活动配置信息 */
    private ActivityConfigRich activityConfigRich;

    public ActivityConfigReq() {
    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }

    public Long getActivityId() {return activityId;}
    public void setActivityId(Long activityId) {this.activityId = activityId;}
    public ActivityConfigRich getActivityConfigRich() {return activityConfigRich;}
    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {this.activityConfigRich = activityConfigRich;}
}
