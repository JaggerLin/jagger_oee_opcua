package cn.madchick.oee.domain.node.repository;

import cn.madchick.oee.domain.activity.model.vo.NodeVO;

import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/18
 * @Copyright： jagger - www.madchick.cn
 */
public interface INodeRepository {
    void addNode(List<NodeVO> nodeList);

    NodeVO queryNode(Long nodeId);
}
