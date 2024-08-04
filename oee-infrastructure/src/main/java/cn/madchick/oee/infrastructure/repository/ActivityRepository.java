package cn.madchick.oee.infrastructure.repository;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.vo.*;
import cn.madchick.oee.domain.activity.repository.IActivityRepository;
import cn.madchick.oee.infrastructure.dao.IActivityDao;
import cn.madchick.oee.infrastructure.dao.INodeDao;
import cn.madchick.oee.infrastructure.dao.IStrategyDao;
import cn.madchick.oee.infrastructure.dao.IStrategyDetailDao;
import cn.madchick.oee.infrastructure.po.Activity;
import cn.madchick.oee.infrastructure.po.Node;
import cn.madchick.oee.infrastructure.po.Strategy;
import cn.madchick.oee.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private INodeDao nodeDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    /** 新增活动 **/
    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    /** 添加节点 **/
    @Override
    public void addNode(List<NodeVO> nodeList) {
        List<Node> req = new ArrayList<>();
        for (NodeVO nodeVo : nodeList) {
            Node node = new Node();
            BeanUtils.copyProperties(nodeVo, node);
            req.add(node);
        }
        nodeDao.insertList(req);
    }

    /** 新增策略 **/
    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insert(req);
    }

    /** 新增策略明细 **/
    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);
    }

    /** 修改活动状态 **/
    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        //修改活动状态
        AlterStateVO alterStateVO = new AlterStateVO(activityId,((Constants.ActivityState) beforeState).getCode(),((Constants.ActivityState) afterState).getCode());
        //返回修改的行数
        int count = activityDao.alterState(alterStateVO);
        //返回是否修改成功
        return 1 == count;
    }

    /** 根据req请求查询任务账单 **/
    @Override
    public TaskBillVO queryActivityTaskBill(OpctaskReq req) {
        //1.查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());
        //2.查询节点信息
        Node node = nodeDao.queryNodeInfo(req.getNodeId());
        //3.封装结果为taskBillVO
        TaskBillVO taskBillVO = new TaskBillVO();
        taskBillVO.setNodeId(node.getNodeId());
        taskBillVO.setNodeName(node.getNodeName());
        taskBillVO.setOpcEnabled(node.getOpcEnabled());
        taskBillVO.setActivityId(activity.getActivityId());
        taskBillVO.setActivityName(activity.getActivityName());
        taskBillVO.setState(activity.getState());
        //策略id从请求中获取
        taskBillVO.setStrategyId(req.getStrategyId());
        //返回结果
        return taskBillVO;
    }

    /**
     * 查询活动分页数据
     * @param req 请求参数；分页、活动
     * @return
     */
    @Override
    public ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req) {
        Long count = activityDao.queryActivityInfoCount(req);
        List<Activity> list = activityDao.queryActivityInfoList(req);

        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity activity : list) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setArea(activity.getArea());
            activityVO.setPasswd(activity.getPasswd());
            activityVO.setState(activity.getState());
            activityVO.setCreator(activity.getCreator());
            activityVO.setCreateTime(activity.getCreateTime());
            activityVO.setUpdateTime(activity.getUpdateTime());

            activityVOList.add(activityVO);
        }

        return new ActivityInfoLimitPageRich(count, activityVOList);
    }
}
