package cn.madchick.oee.rpc.activity.deploy;

import cn.madchick.oee.rpc.activity.deploy.req.ActivityPageReq;
import cn.madchick.oee.rpc.activity.deploy.res.ActivityRes;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public interface IOeeActivityDeploy {
    /**
     * 通过分页查询活动列表信息，给ERP运营使用
     *
     * @param req   查询参数
     * @return      查询结果
     */
    ActivityRes queryActivityListByPageForErp(ActivityPageReq req);
}
