package cn.madchick.oee.domain.activity.service.opctask.impl;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.common.Result;
import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.vo.OpcMiningTaskVO;
import cn.madchick.oee.domain.activity.model.vo.TaskBillVO;
import cn.madchick.oee.domain.activity.repository.IOpcMiningTaskRepository;
import cn.madchick.oee.domain.activity.service.opctask.BaseActivityOpctask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@Service
public class ActivityOpctaskImpl extends BaseActivityOpctask {

    private Logger logger = LoggerFactory.getLogger(ActivityOpctaskImpl.class);

    @Resource
    private IOpcMiningTaskRepository opcMiningTaskRepository;

    @Resource
    private TransactionTemplate transactionTemplate;

    /** 校验 **/
    @Override
    protected Result checkActivityTaskBill(OpctaskReq opcTask, TaskBillVO bill) {
        // 校验：当前活动状态是否为执行中
        Integer state = bill.getState();
        if (Constants.ActivityState.DOING.getCode().equals(state)) {
            logger.warn("活动当前状态非可用 state：{}", bill.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "请先停止活动再配置");
        }

        // 校验：活动日期

        return Result.buildSuccessResult();
    }

    /** 查询节点是否已存在 **/
    @Override
    protected OpcMiningTaskVO queryNodeExistsActivity(Long nodeId) {
        return opcMiningTaskRepository.queryNodeExistsActivity(nodeId);
    }

    @Override
    protected Result grabActitivy(OpctaskReq opctaskReq, TaskBillVO bill, Long taskId) {
        try {
            return transactionTemplate.execute(status -> {
                try {
                    // 写入活动采集任务记录
                    opcMiningTaskRepository.configActivity(bill.getActivityId(), bill.getActivityName(), bill.getStrategyId(), bill.getNodeId(), bill.getNodeName(), taskId, bill.getOpcEnabled());
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("唯一索引冲突 activityId：{} uId：{}", opctaskReq.getActivityId(), opctaskReq.getNodeId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {

        }
    }

}
