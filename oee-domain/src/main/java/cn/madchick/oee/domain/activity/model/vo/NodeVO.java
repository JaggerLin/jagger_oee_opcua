package cn.madchick.oee.domain.activity.model.vo;

/**
 * @description: 节点信息配置
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class NodeVO {
    private Long nodeId;
    private String nodeName;
    private String nodeType;
    private Integer scanCycle;
    private Integer connectStatus;
    private Boolean opcEnabled;
    private Boolean sensor;
    private String ipAddress;
    private String deviceCode;
    private String area;

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
    public Boolean getOpcEnabled() { return opcEnabled;}
    public void setOpcEnabled(Boolean opcEnabled) { this.opcEnabled = opcEnabled;}
    public Boolean getSensor(){return sensor;}
    public void setSensor(Boolean sensor){this.sensor = sensor;}
    public String getIpAddress() { return ipAddress;}
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress;}
    public String getDeviceCode() { return deviceCode;}
    public void setDeviceCode(String deviceCode) { this.deviceCode = deviceCode;}
    public String getArea() { return area;}
    public void setArea(String area) { this.area = area;}

    @Override
    public String toString() {
        return "NodeVO{" +
                "nodeId='" + nodeId + '\'' +
                ", nodeName=" + nodeName +
                ", nodeType=" + nodeType +
                ", scanCycle=" + scanCycle +
                ", connectStatus=" + connectStatus +
                ", opcEnabled=" + opcEnabled +
                ", sensor=" + sensor +
                ", ipAddress=" + ipAddress +
                ", deviceCode=" + deviceCode +
                ", area=" + area +
                '}';
    }
}
