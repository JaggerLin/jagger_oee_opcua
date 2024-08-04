package cn.madchick.oee.domain.opcua.model.req;

/**
 * @description: 从采集服务中删除某个监视节点
 * @author：jagger
 * @date: 2024/7/2
 * @Copyright： jagger - www.madchick.cn
 */
public class OpcUaDelReq {
    /** 请求用户id **/
    private String uId;
    /** 活动id **/
    private Long activityId;
    /** 防重id **/
    private Long nodeId;

    /** 构造函数 **/
    public OpcUaDelReq() {}

    public OpcUaDelReq(String uId, Long nodeId) {
        this.uId = uId;
        this.nodeId = nodeId;
    }

    public OpcUaDelReq(String uId, Long activityId, Long nodeId) {
        this.uId = uId;
        this.activityId = activityId;
        this.nodeId = nodeId;
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

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}
