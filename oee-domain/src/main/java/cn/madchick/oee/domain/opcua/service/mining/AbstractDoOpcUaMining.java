package cn.madchick.oee.domain.opcua.service.mining;

import cn.madchick.oee.domain.opcua.model.req.OpcUaConfigReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaDelReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaMiningReq;
import cn.madchick.oee.domain.opcua.model.req.OpcUaStopReq;
import cn.madchick.oee.domain.opcua.model.res.OpcUaMiningResult;
import cn.madchick.oee.domain.opcua.repository.IOpcUaMiningRepository;
import cn.madchick.oee.domain.opcua.service.factory.DistributionMiningTaskFactory;
import cn.madchick.oee.domain.opcua.service.mining.impl.DoOpcUaMiningImpl;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @description: 采集任务
 * @author：jagger
 * @date: 2024/6/30
 * @Copyright： jagger - www.madchick.cn
 */
public abstract class AbstractDoOpcUaMining extends OpcUaMiningSupport implements IOpcUaClientMining{

    private final Logger logger = LoggerFactory.getLogger(DoOpcUaMiningImpl.class);

    protected Map<Long, List<String>> strategyNodeMap = new ConcurrentHashMap<>();

    protected Map<Long, ManagedSubscription> strategySubscriptionMap = new ConcurrentHashMap<>();

    @Resource
    protected DistributionMiningTaskFactory distributionMiningTaskFactory;

    @Override
    public OpcUaMiningResult doOpcUaMining(OpcUaMiningReq req, ExecutorService threadPoolExecutor){

        // 如果strategyNodeMap是空说明第一次运行，进行初始化
        if(strategyNodeMap.isEmpty()){
            // 先将第一次执行请求初始化
            if(!initHashMap(req)){
                logger.info("error: 初始化节点Map失败!");
            }
            // 执行采集服务
            this.doMiningService(strategyNodeMap, threadPoolExecutor);
        }
        else {
            // 不是第一次先更新HashMap用于传给后台
            if(!updateHashMap(req)){
                logger.info("error: 更新节点Map失败!");
            }
            // 如果不是第一次运行：先检查策略是否存在，存在直接添加节点，不存在需要创建服务添加进线程池
            this.updateOpcUaMining(req, threadPoolExecutor);
        }

        return null;
    }

    @Override
    public OpcUaMiningResult stopOpcUaMining(OpcUaStopReq req){
        // 1.先检查此活动的状态是否为运行中，之前可能已经停止了
        // 查询活动状态这个属于activity中的服务内容，在应用中进行前置判断编排
        // 2.根据活动id获取所有唯一的策略
        List<Long> strategyIdList = super.getStrategyIdList(req.getActivityId());
        // 3.遍历策略id，获取对应的节点列表，重新返回新的HashMap
        for (Long strategyId : strategyIdList){
            // 3.1 取出当前HashMap中的列表
            List<String> currentNodes = strategyNodeMap.get(strategyId);
            // 3.2 取出请求删除的节点列表
            List<String> reqNodes = super.getNodeList(strategyId, req.getActivityId());
            // 3.3 取出策略id对应的订阅，再从订阅中移除节点
            ManagedSubscription managedSubscription = strategySubscriptionMap.get(strategyId);
            this.stopMiningService(managedSubscription, reqNodes);
            // 3.4 从map中移除被请求删除的节点,值被删光key仍然在，map：{10001=[], 10002=[]}
            currentNodes = removeValues(currentNodes, reqNodes);
            // 3.5 重新put进hashmap
            strategyNodeMap.put(strategyId, currentNodes);
            logger.info("当前map：{}",strategyNodeMap);
        }
        // 4.将新的HashMap重新赋值给监听的全局变量
        //updateSubscribeList(strategyNodeMap);
        // 第一种方案：更改订阅监视点后：停止原来的订阅服务，重新开启新的订阅服务
        // 第二种方案：更改订阅监视点后：新开新的订阅服务，停止原来的订阅服务;

        return null;
    }

    @Override
    public OpcUaMiningResult updateOpcUaMining(OpcUaMiningReq req, ExecutorService threadPoolExecutor){
        for (Long strategyId : strategyNodeMap.keySet()){
            if(isStrategyExist(strategyId)){
                // 取出请求的节点列表
                List<String> reqNodes = super.getNodeList(strategyId, req.getActivityId());
                // 声明采集服务
                IOpcUaSamplingTask miningTaskService;
                // 判断采集服务类型
                if (strategyId== 10001L){
                    miningTaskService = distributionMiningTaskFactory.getMiningTaskService(1);
                }
                else {
                    miningTaskService = distributionMiningTaskFactory.getMiningTaskService(2);
                }
                // 策略已存在：根据策略id获取map中的订阅，再添加节点
                miningTaskService.addSubscribeItems(strategySubscriptionMap.get(strategyId),reqNodes);
            }
            else {
                // 策略不存在：创建对应的采集服务并执行
                this.doMiningService(strategyId, strategyNodeMap, threadPoolExecutor);
            }
        }
        return null;
    }

    @Override
    public OpcUaMiningResult delOpcUaTaskItem(OpcUaDelReq req) {

        return null;
    }

    /** map中是否已存在策略 **/
    protected boolean isStrategyExist(Long strategyId) {
        // 判断map中是否已存在策略
        return strategyNodeMap.containsKey(strategyId);
    }

    /** 添加策略id和节点列表进入map **/
    protected void putNodeListInMap(Long strategyId, List<String> nodeList) {
        // {10001=[Dynamic/RandomDouble, Dynamic/RandomFloat], 10002=[Dynamic/RandomInt32, Dynamic/RandomInt64], 10003=[ComplexTypes/CustomEnumTypeVariable]}
        strategyNodeMap.put(strategyId, nodeList);
    }

    /** 比较两个List数组中是否有重复值 ,HashSet的查找和插入操作的平均时间复杂度为O(1)。双重循环其时间复杂度为O(n^2)。**/
    public boolean compareLists(List<String> list1, List<String> list2) {
        HashSet<String> set = new HashSet<>(list1);
        for (String num : list2) {
            if (set.contains(num)) {
                return true;
            }
        }
        return false;
    }

    /** 循环遍历list1中的每个元素。如果list2不包含当前元素，那么就将该元素添加到result列表中。最后，打印出result列表**/
    public List<String> removeValues(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        for (String x : list1) {
            if (!list2.contains(x)) {
                result.add(x);
            }
        }
        return result;
    }

    /** 开启采集时的初始化 **/
    @Override
    public synchronized boolean initHashMap(OpcUaMiningReq req){
        // 1.根据请求的活动id获取所有唯一的策略
        List<Long> strategyIdList = super.getStrategyIdList(req.getActivityId());
        // 2.如果没有策略说明活动未创建采集任务，直接返回
        if (strategyIdList == null || strategyIdList.isEmpty()){
            logger.info("活动id：{}，活动未创建采集任务无策略", req.getActivityId());
            return false;
        }
        // 3.遍历策略id，判断请求中的策略是否都初始化
        for (Long strategyId : strategyIdList){
            // 3.1 取出当前策略对应的节点列表
            List<String> reqNodes = super.getNodeList(strategyId, req.getActivityId());
            // 3.2 判断map中是否已存在策略,不存在添加进hashMap
            if (!isStrategyExist(strategyId)){
                putNodeListInMap(strategyId, reqNodes);
            }
        }

        return true;
    }

    /** 更新hashMap:修改采集配置 **/
    @Override
    public synchronized boolean updateHashMap(OpcUaMiningReq req){
        // 1.根据请求的活动id获取所有唯一的策略
        List<Long> strategyIdList = super.getStrategyIdList(req.getActivityId());
        // 2.如果没有策略说明活动未创建采集任务，直接返回
        if (strategyIdList == null || strategyIdList.isEmpty()){
            logger.info("活动id：{}，活动未创建采集任务无策略", req.getActivityId());
            return false;
        }
        // 3.遍历策略id
        for (Long strategyId : strategyIdList){
            // 3.1 取出当前策略对应的节点列表
            List<String> reqNodes = super.getNodeList(strategyId, req.getActivityId());
            // 3.2 存在策略即从map中取出当前节点列表与请求的列表对比是否有重复(防止重复提交请求)
            List<String> currentNodes = strategyNodeMap.get(strategyId);
            if (!compareLists(reqNodes, currentNodes)){
                // 3.4 不存在重复即更新map中的节点列表:put方法会覆盖掉原来与key关联的值
                currentNodes.addAll(reqNodes);
                putNodeListInMap(strategyId, currentNodes);
            }
            else {
                logger.info("活动id：{}，策略id：{}，节点重复活动已执行,操作无效", req.getActivityId(), strategyId);
            }

        }
        return true;
    }

    protected abstract ManagedSubscription doMiningService(Map<Long, List<String>> strategyNodeMap, ExecutorService threadPoolExecutor);

    protected abstract ManagedSubscription doMiningService(Long strategyId, Map<Long, List<String>> strategyNodeMap, ExecutorService threadPoolExecutor);

    protected abstract void stopMiningService(ManagedSubscription subscription, List<String> reqNodes);

}
