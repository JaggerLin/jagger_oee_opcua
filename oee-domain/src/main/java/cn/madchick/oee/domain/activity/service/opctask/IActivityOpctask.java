package cn.madchick.oee.domain.activity.service.opctask;

import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.res.OpctaskResult;

/**
 * @description: 采集任务接口定义
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public interface IActivityOpctask {
    /**
     * 配置opc采集任务
     * @param req
     * @return  配置结果
     */
     OpctaskResult configOpcTask(OpctaskReq req);
}
