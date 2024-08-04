package cn.madchick.oee.interfaces.facade;

import cn.madchick.oee.common.Result;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.model.vo.ActivityVO;
import cn.madchick.oee.interfaces.assembler.IMapping;
import cn.madchick.oee.rpc.activity.deploy.IOeeActivityDeploy;
import cn.madchick.oee.application.process.deploy.IActivityDeployProcess;
import cn.madchick.oee.rpc.activity.deploy.dto.ActivityDTO;
import cn.madchick.oee.rpc.activity.deploy.req.ActivityPageReq;
import cn.madchick.oee.rpc.activity.deploy.res.ActivityRes;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 采集活动部署
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@Service
public class OeeActivityDeploy implements IOeeActivityDeploy {
    private Logger logger = LoggerFactory.getLogger(OeeActivityDeploy.class);

    @Resource
    private IActivityDeployProcess activityDeploy;

    @Resource
    private IMapping<ActivityVO, ActivityDTO> activityMapping;

    @Override
    public ActivityRes queryActivityListByPageForErp(ActivityPageReq req) {
        try {
            logger.info("活动部署分页数据查询开始 erpID：{}", req.getErpId());

            // 1. 包装入参
            // 1.1页码和每页条数
            ActivityInfoLimitPageReq activityInfoLimitPageReq = new ActivityInfoLimitPageReq(req.getPage(),req.getRows());
            // 1.2活动ID
            activityInfoLimitPageReq.setActivityId(req.getActivityId());
            // 1.3活动名称
            activityInfoLimitPageReq.setActivityName(req.getActivityName());

            // 2. 查询结果
            // 2.1查询活动部署分页数据,返回活动部署分页数据
            ActivityInfoLimitPageRich activityInfoLimitPageRich = activityDeploy.queryActivityInfoLimitPage(activityInfoLimitPageReq);
            // 2.2获取活动部署分页数据,总数
            Long count = activityInfoLimitPageRich.getCount();
            // 2.3获取活动部署分页数据
            List<ActivityVO> activityVOList = activityInfoLimitPageRich.getActivityVOList();

            // 3. 转换对象,将活动部署分页数据转换为DTO,返回给前端
            List<ActivityDTO> activityDTOList = activityMapping.sourceToTarget(activityVOList);

            // 4. 封装数据
            // 4.1封装返回结果
            ActivityRes activityRes = new ActivityRes(Result.buildSuccessResult());
            // 4.2设置活动部署分页数据
            activityRes.setCount(count);
            // 4.3设置活动部署分页数据
            activityRes.setActivityDTOList(activityDTOList);

            logger.info("活动部署分页数据查询完成 erpID：{} count：{}", req.getErpId(), count);

            // 5. 返回结果
            return activityRes;
        } catch (Exception e) {
            logger.error("活动部署分页数据查询失败 erpID：{} reqStr：{}", req.getErpId(), JSON.toJSON(req), e);
            return new ActivityRes(Result.buildErrorResult());
        }
    }
}
