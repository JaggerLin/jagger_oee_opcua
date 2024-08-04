package cn.madchick.oee.domain.activity.model.vo;

import java.util.Date;

/**
 * @description: 活动信息配置
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class ActivityVO {
    private Long id;
    private Long activityId;
    private String area;
    private String activityName;
    private String passwd;
    private Integer state;
    private String creator;
    private Date createTime;
    private Date updateTime;

    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }
    public Long getActivityId() { return activityId;}
    public void setActivityId(Long activityId) { this.activityId = activityId;}
    public String getArea() { return area;}
    public void setArea(String area) { this.area = area;}
    public String getActivityName() { return activityName;}
    public void setActivityName(String activityName) { this.activityName = activityName;}
    public String getPasswd() { return passwd;}
    public void setPasswd(String passwd) { this.passwd = passwd;}
    public Integer getState() { return state;}
    public void setState(Integer state) { this.state = state;}
    public String getCreator() { return creator;}
    public void setCreator(String creator) { this.creator = creator;}
    public Date getCreateTime() {return createTime;}
    public void setCreateTime(Date createTime) {this.createTime = createTime;}
    public Date getUpdateTime() {return updateTime;}
    public void setUpdateTime(Date updateTime) {this.updateTime = updateTime;}


    @Override
    public String toString() {
        return "ActivityVO{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", area='" + area + '\'' +
                ", activityName='" + activityName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", state=" + state +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
