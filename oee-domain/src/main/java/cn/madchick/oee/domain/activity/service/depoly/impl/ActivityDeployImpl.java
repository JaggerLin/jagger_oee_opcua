package cn.madchick.oee.domain.activity.service.depoly.impl;

import cn.madchick.oee.domain.activity.model.aggregates.ActivityConfigRich;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.madchick.oee.domain.activity.model.req.ActivityConfigReq;
import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.model.vo.ActivityVO;
import cn.madchick.oee.domain.activity.model.vo.NodeVO;
import cn.madchick.oee.domain.activity.model.vo.StrategyDetailVO;
import cn.madchick.oee.domain.activity.model.vo.StrategyVO;
import cn.madchick.oee.domain.activity.repository.IActivityRepository;
import cn.madchick.oee.domain.activity.service.depoly.IActivityDeploy;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {
    /** 日志*/
    Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);
    /** 活动仓储*/
    @Resource
    private IActivityRepository activityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
        logger.info("创建活动配置开始，activityId：{}", req.getActivityId());

        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();
        try {
            // 添加活动配置
            ActivityVO activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            // 添加节点配置
            List<NodeVO> nodeList = activityConfigRich.getNodeList();
            activityRepository.addNode(nodeList);

            // 添加策略配置
            StrategyVO strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            // 添加策略明细配置
            List<StrategyDetailVO> strategyDetailList = activityConfigRich.getStrategy().getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailList);

            logger.info("创建活动配置完成，activityId：{}", req.getActivityId());
        } catch (DuplicateKeyException e) {
            logger.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}", req.getActivityId(), JSON.toJSONString(req), e);
            throw e;
        }
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        // TODO: 非核心功能后续补充
    }

    @Override
    public ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req) {
        return activityRepository.queryActivityInfoLimitPage(req);
    }
}
