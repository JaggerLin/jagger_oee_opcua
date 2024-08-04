package cn.madchick.oee.application.process.deploy.impl;

import cn.madchick.oee.application.process.deploy.IActivityDeployProcess;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.service.depoly.IActivityDeploy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@Service
public class ActivityDeployProcessImpl implements IActivityDeployProcess {

    @Resource
    private IActivityDeploy activityDeploy;

    @Override
    public ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req) {
        return activityDeploy.queryActivityInfoLimitPage(req);
    }
}
