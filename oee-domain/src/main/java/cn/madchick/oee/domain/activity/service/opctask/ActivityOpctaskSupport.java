package cn.madchick.oee.domain.activity.service.opctask;

import cn.madchick.oee.domain.activity.model.req.OpctaskReq;
import cn.madchick.oee.domain.activity.model.vo.TaskBillVO;
import cn.madchick.oee.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description: 采集任务配置的一些通用数据服务
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
public class ActivityOpctaskSupport {

    /** 活动仓储服务 **/
    @Resource
    protected IActivityRepository activityRepository;

    /** 在活动仓储中查询任务账单 **/
    protected TaskBillVO queryActivityTaskBill(OpctaskReq req){
        return activityRepository.queryActivityTaskBill(req);
    }
}
