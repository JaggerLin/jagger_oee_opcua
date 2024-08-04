package cn.madchick.oee.domain.opcua.service.mining;

import cn.madchick.oee.domain.opcua.model.req.OpcUaConfigReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaDelReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaStopReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaMiningReq;
import cn.madchick.oee.domain.opcua.model.res.OpcUaMiningResult;
import org.eclipse.milo.opcua.stack.core.UaException;

import java.util.concurrent.ExecutorService;

/**
 * @description: 采集任务接口
 * @author：jagger
 * @date: 2024/6/29
 * @Copyright： jagger - www.madchick.cn
 */
public interface IOpcUaClientMining {
    /**
     * opc采集服务执行
     * @param req, threadPoolExecutor
     * @return 采集结果
     */
    OpcUaMiningResult doOpcUaMining(OpcUaMiningReq req, ExecutorService threadPoolExecutor);

    /**
     * opc采集服务停止
     * @param req
     * @return 采集结果
     */
    OpcUaMiningResult stopOpcUaMining(OpcUaStopReq req);

    /**
     * opc采集服务更新
     * @param req 请求, threadPoolExecutor 线程池
     * @return 采集结果
     */
    OpcUaMiningResult updateOpcUaMining(OpcUaMiningReq req, ExecutorService threadPoolExecutor) throws UaException ;

    /**
     * opc采集任务单项删除
     * @param req
     * @return
     */
    OpcUaMiningResult delOpcUaTaskItem(OpcUaDelReq req);

    /**
     * 执行采集任务前map初始化
     * @param req
     * @return 空
     */
    boolean initHashMap(OpcUaMiningReq req);

    /**
     * 配置采集任务：更新节点列表和策略
     * @param req
     * @return 空
     */
    boolean updateHashMap(OpcUaMiningReq req);
}
