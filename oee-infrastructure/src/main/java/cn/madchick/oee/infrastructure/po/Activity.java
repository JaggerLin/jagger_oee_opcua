package cn.madchick.oee.infrastructure.po;

import java.util.Date;

/**
 * @description: 采集活动信息表
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class Activity {
    /**
     * 自增ID、活动ID、活动名称、设备编码、区域、连接状态、创建时间、更新时间、活动状态、创建人
     * 活动状态：编辑、提审、通过、运行、拒绝、关闭、开启
     */
    private Long id;
    private Long activityId;
    private String area;
    private String activityName;
    private String passwd;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    private String creator;

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}
    public Long getActivityId() { return activityId;}
    public void setActivityId(Long activityId) { this.activityId = activityId;}
    public String getArea() { return area;}
    public void setArea(String area) { this.area = area;}
    public String getActivityName() { return activityName;}
    public void setActivityName(String activityName) { this.activityName = activityName;}
    public String getPasswd() { return passwd;}
    public void setPasswd(String passwd) { this.passwd = passwd;}
    public Date getCreateTime() { return createTime;}
    public void setCreateTime(Date createTime) { this.createTime = createTime;}
    public Date getUpdateTime() { return updateTime;}
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime;}
    public Integer getState() { return state;}
    public void setState(Integer state) { this.state = state;}
    public String getCreator() { return creator;}
    public void setCreator(String creator) { this.creator = creator;}
}
