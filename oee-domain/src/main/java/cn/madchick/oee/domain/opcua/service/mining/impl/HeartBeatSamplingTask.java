package cn.madchick.oee.domain.opcua.service.mining.impl;

import cn.madchick.oee.domain.activity.model.vo.NodeRecordVO;
import cn.madchick.oee.domain.opcua.service.mining.IOpcUaSamplingTask;
import cn.madchick.oee.domain.support.kafka.producer.KafkaProducer;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @description:
 * @author：jagger
 * @date: 2024/7/2
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class HeartBeatSamplingTask implements IOpcUaSamplingTask {

    private final Logger logger = LoggerFactory.getLogger(DoOpcUaMiningImpl.class);

    @Resource
    private KafkaProducer kafkaProducer;

    /** 从订阅的监视项目中删除某一个 **/
    @Override
    public void deleteSubscribeItem(ManagedSubscription subscription, String reqNode) {
        // 1.获取订阅中所有监视点
        List<ManagedDataItem> dataItems = subscription.getDataItems();

        // 2.遍历监视点列表，删除指定节点
        for(ManagedDataItem dataItem : dataItems){
            if(dataItem.getNodeId().getIdentifier().toString().equals(reqNode)){
                try {
                    subscription.deleteDataItem(dataItem);
                } catch (UaException e) {
                    throw new RuntimeException(e);
                }
                logger.info("删除节点成功：{}",dataItem);
                break;
            }
        }
    }

    @Override
    public void deleteSubscribeItems(ManagedSubscription subscription, List<String> reqNodes) {
        // 获取当前订阅中所有监视点
        List<ManagedDataItem> dataItems = subscription.getDataItems();
        List<ManagedDataItem> deletedItems  = new ArrayList<>();
        for(String reqNode : reqNodes){
            for(ManagedDataItem dataItem : dataItems){
                if(dataItem.getNodeId().getIdentifier().toString().equals(reqNode)){
                    deletedItems.add(dataItem);
                }
            }
        }
        try {
            subscription.deleteDataItems(deletedItems);
        } catch (UaException e) {
            throw new RuntimeException(e);
        }
        logger.info("已删除，更新订阅列表成功");
    }

    @Override
    public void runSubscriptionInThreadPool(ManagedSubscription subscription, List<String> batchIdentifiers, ExecutorService threadPoolExecutor) {
        // 批量订阅节点
        addSubscribeItems(subscription, batchIdentifiers);
        // 提交数据监听任务到线程池
        DataListenerThread thread02 = new DataListenerThread(subscription);
        threadPoolExecutor.execute(thread02);
        logger.info("数据监听任务提交成功");
    }

    @Override
    public void runSubscriptionListenerInThreadPool(OpcUaClient opcUaClient, ExecutorService threadPoolExecutor) {
        // 初始化创建订阅状态监听器
        InitThread thread01 = new InitThread(opcUaClient);
        threadPoolExecutor.execute(thread01);
        logger.info("订阅初始化任务提交成功");
    }

    private class InitThread extends Thread{
        //标识线程是否结束
        private volatile boolean threadStopFlag = true;
        private final OpcUaClient opcUaClient;

        public InitThread(OpcUaClient client) {
            this.opcUaClient = client;
        }

        @Override
        public void run() {
            while (threadStopFlag) {
                subscriptionStatusListener(opcUaClient);
            }
        }

        public void stopThread() {
            threadStopFlag = false;
        }

        public void startThread() {
            threadStopFlag = true;
        }
    }

    private class DataListenerThread extends Thread{
        //标识线程是否结束
        volatile boolean threadStopFlag = true;
        private final ManagedSubscription heartBeatSubscription;

        public DataListenerThread(ManagedSubscription subscription) {
            this.heartBeatSubscription = subscription;
        }

        @Override
        public void run() {
            while (threadStopFlag) {
                //ManagedSubscription heartBeatSubscription = localVar.get();
                // 将节点列表添加进opc客户端监听
                dataChangeCallback(heartBeatSubscription);
            }
        }

        public void stopThread() {
            threadStopFlag = false;
        }

        public void startThread() {
            threadStopFlag = true;
        }
    }

    @Override
    public void addSubscribeItem(ManagedSubscription subscription, String reqNode){
        try{
            NodeId nodeId = new NodeId(2, reqNode);
            subscription.createDataItem(nodeId);
            logger.info("OPC UA 新增监听项：{}, 监听线程ID：{}", nodeId.getIdentifier().toString(),Thread.currentThread().getId());
        }
        catch (UaException e){
            logger.info("OPC UA 新增监听项异常：{}", e.getMessage());
        }
    }

    @Override
    public void addSubscribeItems(ManagedSubscription subscription, List<String> batchIdentifiers){
        // 批量订阅多个节点，默认为2
        int batchNamespaceIndex = 2;

        try {
            // 创建opc专用格式节点ID列表
            List<NodeId> nodeIdList = new ArrayList<>();
            // 遍历所有节点
            for (String id : batchIdentifiers) {
                nodeIdList.add(new NodeId(batchNamespaceIndex, id));
            }
            // 创建监听数据项
            subscription.createDataItems(nodeIdList);
            //subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
            logger.info("OPC UA 新增监听项数量：{}, 监听线程ID：{}", nodeIdList.size(),Thread.currentThread().getId());
        }
        catch (Exception e) {
            logger.info("OPC UA 批量订阅异常：{}", e.getMessage());
        }
    }

    @Override
    public ManagedSubscription createSubscription(OpcUaClient client, int publishingInterval){
        try {
            // 创建订阅(指定client和采样间隔)
            return ManagedSubscription.create(client, publishingInterval);
        }
        catch (Exception e) {
            logger.info("OPC UA 创建订阅异常：{}", e.getMessage());
            return null;
        }
    }

    @Override
    public void disableSubscription(OpcUaClient client, ManagedSubscription subscription) {
        List<UInteger> subidList= new ArrayList<>();
        UInteger subscriptionId = subscription.getSubscription().getSubscriptionId();
        subidList.add(subscriptionId);
        client.setPublishingMode(false, subidList);
    }

    @Override
    public void enableSubscription(OpcUaClient client, ManagedSubscription subscription) {
        List<UInteger> subidList= new ArrayList<>();
        UInteger subscriptionId = subscription.getSubscription().getSubscriptionId();
        subidList.add(subscriptionId);
        client.setPublishingMode(true, subidList);
    }

    @Override
    public void deleteSubscription(ManagedSubscription subscription) throws UaException {
        subscription.delete();
    }

    @Override
    public void deleteSubscriptions(ManagedSubscription subscription, OpcUaClient client) {
        List<UInteger> subidList= new ArrayList<>();
        UInteger subscriptionId = subscription.getSubscription().getSubscriptionId();
        subidList.add(subscriptionId);
        client.deleteSubscriptions(subidList);
    }

    @Override
    public void subscriptionStatusListener(OpcUaClient opcUaClient) {
        // 创建订阅管理器, 并添加订阅监听器
        opcUaClient.getSubscriptionManager().addSubscriptionListener(new CustomSubscriptionListener());
    }

    @Override
    public void dataChangeCallback(ManagedSubscription subscription) {
        // 创建一个CountDownLatch对象，用于等待事件
        final CountDownLatch eventLatch = new CountDownLatch(1);
        try{
            // 添加数据监听器
            subscription.addDataChangeListener((items, values) -> {
                for (int i = 0; i < items.size(); i++) {
                    logger.info(
                            "OPC UA 节点名：{} 值：{} 时间：{}",
                            items.get(i).getNodeId().getIdentifier().toString(), values.get(i).getValue().getValue().toString(), System.currentTimeMillis()
                    );
                    NodeRecordVO nodeRecordVO = new NodeRecordVO();
                    nodeRecordVO.setNodeId(1000L);
                    nodeRecordVO.setNodeName(items.get(i).getNodeId().getIdentifier().toString());
                    nodeRecordVO.setNodeValue(values.get(i).getValue().getValue().toString());
                    nodeRecordVO.setUpdateTime(new Date(System.currentTimeMillis()));
                    nodeRecordVO.setArea("MD3");
                    nodeRecordVO.setNodeType("Boolean");
                    nodeRecordVO.setNodeUnit("/");
                    kafkaProducer.sendOpcHeartbeatData(nodeRecordVO);
                }
            });
            // 持续监听
            eventLatch.await();
        }
        catch (Exception e) {
            logger.info("OPC UA 数据监听器异常：{}", e.getMessage());
        }
    }

    /**
     * 自定义订阅监听类
     */
    private class CustomSubscriptionListener implements UaSubscriptionManager.SubscriptionListener {

        CustomSubscriptionListener() {
        }

        @Override
        public void onKeepAlive(UaSubscription subscription, DateTime publishTime) {
            logger.info("订阅{}：无监听项保持订阅中...", subscription.getSubscriptionId());
        }

        @Override
        public void onStatusChanged(UaSubscription subscription, StatusCode status) {
            logger.info("订阅{}：订阅状态改变：{}", subscription.getSubscriptionId(), status.getValue());
        }

        @Override
        public void onPublishFailure(UaException exception) {

            logger.info("订阅发布失败：{}", exception.getMessage());
        }

        @Override
        public void onNotificationDataLost(UaSubscription subscription) {
            logger.info("订阅{}：订阅通知数据丢失",subscription.getSubscriptionId());
        }
    }
}
