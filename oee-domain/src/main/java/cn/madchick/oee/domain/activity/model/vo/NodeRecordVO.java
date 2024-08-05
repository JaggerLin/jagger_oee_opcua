package cn.madchick.oee.domain.activity.model.vo;

import java.util.Date;

/**
 * @description:
 * @author：jagger
 * @date: 2024/8/5
 * @Copyright： jagger - www.madchick.cn
 */
public class NodeRecordVO {
    /**
     *  节点ID、节点名称、节点值、节点类型、节点单位、区域、创建时间、更新时间
     */
    private Long nodeId;
    private String nodeName;
    private String nodeValue;
    private String nodeType;
    private String nodeUnit;
    private String area;
    private Date createTime;
    private Date updateTime;

    public Long getNodeId(){return nodeId;}
    public void setNodeId(Long nodeId){this.nodeId = nodeId;}
    public String getNodeName(){return nodeName;}
    public void setNodeName(String nodeName){this.nodeName = nodeName;}
    public String getNodeValue(){return nodeValue;}
    public void setNodeValue(String nodeValue){this.nodeValue = nodeValue;}
    public String getNodeType(){return nodeType;}
    public void setNodeType(String nodeType){this.nodeType = nodeType;}
    public String getNodeUnit(){return nodeUnit;}
    public void setNodeUnit(String nodeUnit){this.nodeUnit = nodeUnit;}
    public String getArea(){return area;}
    public void setArea(String area){this.area = area;}
    public Date getCreateTime(){return createTime;}
    public void setCreateTime(Date createTime){this.createTime = createTime;}
    public Date getUpdateTime(){return updateTime;}
    public void setUpdateTime(Date updateTime){this.updateTime = updateTime;}
}
