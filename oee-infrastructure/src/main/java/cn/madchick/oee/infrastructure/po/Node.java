package cn.madchick.oee.infrastructure.po;

import java.util.Date;

/**
 * @description: 采集节点表
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class Node {
    /**
     * 自增ID、节点ID、节点名称、节点类型、扫描速率、信号状态、创建时间、更新时间、功能状态、是否传感器、IP地址、设备编码
     * 节点状态：编辑、提审、通过、运行、拒绝、关闭、开启
     */
    private Long id;
    private Long nodeId;
    private String nodeName;
    private String nodeType;
    private Integer scanCycle;
    private Integer connectStatus;
    private Date createTime;
    private Date updateTime;
    private Boolean opcEnabled;
    private Boolean sensor;
    private String ipAddress;
    private String deviceCode;
    private String area;

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}
    public Long getNodeId() { return nodeId;}
    public void setNodeId(Long nodeId) { this.nodeId = nodeId;}
    public String getNodeName() { return nodeName;}
    public void setNodeName(String nodeName) { this.nodeName = nodeName;}
    public String getNodeType() { return nodeType;}
    public void setNodeType(String nodeType) { this.nodeType = nodeType;}
    public Integer getScanCycle() { return scanCycle;}
    public void setScanCycle(Integer scanCycle) { this.scanCycle = scanCycle;}
    public Integer getConnectStatus(){return connectStatus;}
    public void setConnectStatus(Integer connectStatus){this.connectStatus = connectStatus;}
    public Date getCreateTime() { return createTime;}
    public void setCreateTime(Date createTime) { this.createTime = createTime;}
    public Date getUpdateTime() { return updateTime;}
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime;}
    public Boolean getOpcEnabled() { return opcEnabled;}
    public void setOpcEnabled(Boolean opcEnabled) { this.opcEnabled = opcEnabled;}
    public boolean getSensor() { return sensor;}
    public void setSensor(Boolean sensor) { this.sensor = sensor;}
    public String getIpAddress() { return ipAddress;}
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress;}
    public String getDeviceCode() { return deviceCode;}
    public void setDeviceCode(String deviceCode) { this.deviceCode = deviceCode;}
    public String getArea() { return area;}
    public void setArea(String area) { this.area = area;}
}
