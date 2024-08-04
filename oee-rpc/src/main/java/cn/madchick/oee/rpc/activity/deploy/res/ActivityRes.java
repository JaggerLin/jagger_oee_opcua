package cn.madchick.oee.rpc.activity.deploy.res;

import java.io.Serializable;
import java.util.List;
import cn.madchick.oee.common.Result;
import cn.madchick.oee.rpc.activity.deploy.dto.ActivityDTO;

/**
 * @description: 活动查询结果
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class ActivityRes implements Serializable {

    private Result result;
    private Long count;
    private List<ActivityDTO> activityDTOList;

    public ActivityRes() {
    }

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, Long count, List<ActivityDTO> activityDTOList) {
        this.result = result;
        this.count = count;
        this.activityDTOList = activityDTOList;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ActivityDTO> getActivityDTOList() {
        return activityDTOList;
    }

    public void setActivityDTOList(List<ActivityDTO> activityDTOList) {
        this.activityDTOList = activityDTOList;
    }
}
