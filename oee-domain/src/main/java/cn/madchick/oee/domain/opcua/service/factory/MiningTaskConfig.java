package cn.madchick.oee.domain.opcua.service.factory;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.domain.opcua.service.mining.IOpcUaSamplingTask;
import cn.madchick.oee.domain.opcua.service.mining.impl.HeartBeatSamplingTask;
import cn.madchick.oee.domain.opcua.service.mining.impl.IntervalSamplingTask;
import cn.madchick.oee.domain.opcua.service.mining.impl.ThresholdSamplingTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：jagger
 * @date: 2024/7/2
 * @Copyright： jagger - www.madchick.cn
 */
public class MiningTaskConfig {
    /** 采集策略组 */
    protected static Map<Integer, IOpcUaSamplingTask> miningTaskMap = new ConcurrentHashMap<>();

    @Resource
    private HeartBeatSamplingTask heartBeatSamplingTask;

    @Resource
    private IntervalSamplingTask intervalSamplingTask;

    @Resource
    private ThresholdSamplingTask thresholdSamplingTask;

    @PostConstruct
    public void init(){
        miningTaskMap.put(Constants.MiningTaskType.HearBeatSampling.getCode(), heartBeatSamplingTask);
        miningTaskMap.put(Constants.MiningTaskType.IntervalSampling.getCode(), intervalSamplingTask);
        miningTaskMap.put(Constants.MiningTaskType.ThresholdSampling.getCode(), thresholdSamplingTask);
    }
}
