package cn.madchick.oee.domain.activity.service.depoly;

import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.madchick.oee.domain.activity.model.req.ActivityConfigReq;
import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;

/**
 * @description: 部署活动配置接口
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public interface IActivityDeploy {

        /**
        * 创建活动信息
        * @param req 活动配置信息
        */
        void createActivity(ActivityConfigReq req);

        /**
        * 修改活动信息
        * @param req 活动配置信息
        */
        void updateActivity(ActivityConfigReq req);

        /**
         * 查询活动信息
         * @param req 活动分页请求
         * @return 活动分页信息
         */
        ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req);
}
