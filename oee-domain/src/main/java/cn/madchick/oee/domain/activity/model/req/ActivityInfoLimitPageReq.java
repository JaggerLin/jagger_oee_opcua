package cn.madchick.oee.domain.activity.model.req;

import cn.madchick.oee.common.PageRequest;

/**
 * @description: 活动分页查询请求对象
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class ActivityInfoLimitPageReq extends PageRequest {
    /** 活动id **/
    private Long activityId;
    /** 活动名称 **/
    private String activityName;

    public ActivityInfoLimitPageReq(int page, int rows) {
        super(page, rows);
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
}
