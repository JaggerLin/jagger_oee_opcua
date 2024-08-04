package cn.madchick.oee.domain.activity.repository;

import cn.madchick.oee.domain.activity.model.vo.NodeVO;

import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public interface INodeRepository {
    /**
     * 添加节点配置集合
     *
     * @param nodeConfigList 节点配置集合
     */
    void addNode(List<NodeVO> nodeConfigList);

    /**
     * 查询节点
     *
     * @param nodeId 节点ID
     * @return 节点信息
     */
    NodeVO queryNode(Long nodeId);
}
