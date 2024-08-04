package cn.madchick.oee.application.process.deploy;

import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public interface IActivityDeployProcess {
    /**
     * 查询活动分页查询聚合对象
     *
     * @param req 请求参数；分页、活动
     * @return    查询结果
     */
    ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req);
}
