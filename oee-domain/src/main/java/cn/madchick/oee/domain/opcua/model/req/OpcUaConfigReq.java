package cn.madchick.oee.domain.opcua.model.req;

/**
 * @description: 采集配置更新请求
 * @author：jagger
 * @date: 2024/7/1
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcUaConfigReq {

    /** 请求用户id **/
    private String uId;
    /** 活动id **/
    private Long activityId;
    /** 防重id **/
    private String uuid;

    /** 构造函数 **/
    public OpcUaConfigReq() {}

    public OpcUaConfigReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }

    public OpcUaConfigReq(String uId, Long activityId, String uuid) {
        this.uId = uId;
        this.activityId = activityId;
        this.uuid = uuid;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
