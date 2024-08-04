package cn.madchick.oee.domain.opcua.service.factory;

import cn.madchick.oee.domain.opcua.service.mining.IOpcUaSamplingTask;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：jagger
 * @date: 2024/7/2
 * @Copyright： jagger - www.madchick.cn
 */
@Service
public class DistributionMiningTaskFactory extends MiningTaskConfig{
    /** 根据采集任务类型返回对应的采集服务 **/
    public IOpcUaSamplingTask getMiningTaskService(Integer miningTaskType){
        return miningTaskMap.get(miningTaskType);
    }
}
