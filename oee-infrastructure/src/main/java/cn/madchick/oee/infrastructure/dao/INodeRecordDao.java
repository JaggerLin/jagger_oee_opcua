package cn.madchick.oee.infrastructure.dao;

import cn.madchick.oee.infrastructure.po.NodeRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 节点记录接口
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Mapper
public interface INodeRecordDao {
    /**
     * 查询节点记录
     * @param nodeId 节点ID
     * @return 返回结果
     */
    NodeRecord queryNodeRecord(String nodeId);

}
