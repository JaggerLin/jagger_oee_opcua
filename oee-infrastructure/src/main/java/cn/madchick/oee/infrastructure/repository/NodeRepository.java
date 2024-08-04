package cn.madchick.oee.infrastructure.repository;

import cn.madchick.oee.domain.activity.model.vo.NodeVO;
import cn.madchick.oee.domain.node.repository.INodeRepository;
import cn.madchick.oee.infrastructure.dao.INodeDao;
import cn.madchick.oee.infrastructure.po.Node;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/18
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class NodeRepository implements INodeRepository {
    @Resource
    private INodeDao nodeDao;

    /** 添加节点 **/
    @Override
    public void addNode(List<NodeVO> nodeList) {
        List<Node> req = new ArrayList<>();
        for (NodeVO nodeVo : nodeList) {
            Node node = new Node();
            BeanUtils.copyProperties(nodeVo, node);
            req.add(node);
        }
        nodeDao.insertList(req);
    }

    /** 查询节点 **/
    @Override
    public NodeVO queryNode(Long nodeId) {
        Node node = nodeDao.queryNodeInfo(nodeId);
        NodeVO nodeVo = new NodeVO();
        BeanUtils.copyProperties(node, nodeVo);
        return nodeVo;
    }
}
