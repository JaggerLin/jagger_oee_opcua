package cn.madchick.oee.domain.activity.repository;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.vo.*;

import java.util.List;

/**
 * @description: 活动仓库服务(活动表、节点表、策略表、策略明细表)
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public interface IActivityRepository {

        /**
        * 添加活动配置
        * @param activity 活动配置
        */
        void addActivity(ActivityVO activity);

        /**
        * 添加节点配置集合
        *
        * @param nodeConfigList 节点配置集合
        */
        void addNode(List<NodeVO> nodeConfigList);

        /**
        * 添加策略配置
        *
        * @param strategy 策略配置
        */
        void addStrategy(StrategyVO strategy);

        /**
        * 添加策略明细配置
        *
        * @param strategyDetailList 策略明细集合
        */
        void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

        /**
        * 变更活动状态
        *
        * @param activityId    活动ID
        * @param beforeState   修改前状态
        * @param afterState    修改后状态
        * @return              更新结果
        */
        boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);

        TaskBillVO queryActivityTaskBill(OpctaskReq req);

        /**
         * 查询活动分页查询聚合对象
         *
         * @param req 请求参数；分页、活动
         * @return    查询结果
         */
        ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req);
}
