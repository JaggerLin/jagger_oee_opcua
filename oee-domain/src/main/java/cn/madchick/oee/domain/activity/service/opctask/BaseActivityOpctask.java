package cn.madchick.oee.domain.activity.service.opctask;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.common.Result;
import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.res.OpctaskResult;
import cn.madchick.oee.domain.activity.model.vo.OpcMiningTaskVO;
import cn.madchick.oee.domain.activity.model.vo.TaskBillVO;
import cn.madchick.oee.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 采集任务基类
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public abstract class BaseActivityOpctask extends ActivityOpctaskSupport implements IActivityOpctask{

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public OpctaskResult configOpcTask(OpctaskReq req) {
        //0.查询节点是否存在已存在任务中
        OpcMiningTaskVO opcMiningTaskVO = this.queryNodeExistsActivity(req.getNodeId());
        if(null != opcMiningTaskVO){
            return buildOpcTaskResult(opcMiningTaskVO.getStrategyId(),opcMiningTaskVO.getTaskId(), Constants.ResponseCode.NODE_EXIST);
        }
        //1.获取到详细的任务账单
        TaskBillVO taskBillVO = super.queryActivityTaskBill(req);
        //2.校验处理：如果不成功返回信息
        Result checkResult = this.checkActivityTaskBill(req, taskBillVO);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())){
            return new OpctaskResult(checkResult.getCode(), checkResult.getInfo());
        }
        //3.生成任务id,调用仓储插入数据库
        long taskId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = this.grabActitivy(req, taskBillVO, taskId);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())){
            return new OpctaskResult(grabResult.getCode(), grabResult.getInfo());
        }
        //4.调用下面的封装结果方法：返回结果
        return buildOpcTaskResult(taskBillVO.getStrategyId(),taskId,Constants.ResponseCode.SUCCESS);
    }

    /**
     * 封装结果【返回的策略ID，用于执行opc监听】
     *
     * @param strategyId        策略ID
     * @param taskId            任务ID
     * @param code              状态码
     * @return 封装结果
     */
    private OpctaskResult buildOpcTaskResult(Long strategyId, Long taskId, Constants.ResponseCode code) {
        OpctaskResult opctaskResult = new OpctaskResult(code.getCode(), code.getInfo());
        opctaskResult.setStrategyId(strategyId);
        opctaskResult.setTaskId(taskId);
        return opctaskResult;
    }

    /**
     * 活动信息校验处理
     *
     * @param opcTask 参与活动请求
     * @param bill    任务账单
     * @return 校验结果
     */
    protected abstract Result checkActivityTaskBill(OpctaskReq opcTask, TaskBillVO bill);

    protected  abstract OpcMiningTaskVO queryNodeExistsActivity(Long nodeId);

    protected  abstract  Result grabActitivy(OpctaskReq opctaskReq, TaskBillVO bill, Long taskId);
}
