package cn.madchick.oee.infrastructure.dao;

import cn.madchick.oee.infrastructure.po.Node;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 策略明细表DAO
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Mapper
public interface INodeDao {

    /**
     * 查询节点信息
     * @param nodeId 节点ID
     * @return 返回结果
     */
    Node queryNodeInfo(Long nodeId);

    /**
     * 插入节点配置
     * @param list 节点配置
     */
    void insertList(List<Node> list);
}
