package cn.madchick.oee.domain.activity.service.stateflow.event;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.common.Result;
import cn.madchick.oee.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: 编辑状态
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class EditingState extends AbstractState {
        /** 编辑 -> 提审 */
        @Override
        public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
            boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.ARRAIGNMENT);
            return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动提审成功") : Result.buildErrorResult("活动状态变更失败");
        }
        /** 编辑->无法流转->审核通过 */
        @Override
        public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "编辑中不可审核通过");
        }
        /** 编辑->无法流转->审核拒绝 */
        @Override
        public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "编辑中不可审核拒绝");
        }
        /** 编辑->关闭 */
        @Override
        public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
            boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);
            return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动关闭成功") : Result.buildErrorResult("活动状态变更失败");
        }
        /** 编辑->无法流转->开启 */
        @Override
        public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "非关闭活动不可开启");
        }
        /** 编辑->无法流转->进行中 */
        @Override
        public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "编辑中活动不可执行活动中变更");
        }
}
