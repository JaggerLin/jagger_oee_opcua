package cn.madchick.oee.domain.opcua.service.mining;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.UaException;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @description:
 * @author：jagger
 * @date: 2024/7/2
 * @Copyright： jagger - www.madchick.cn
 */
public interface IOpcUaSamplingTask {
    /** 删除监听某一节点 **/
    void deleteSubscribeItem(ManagedSubscription subscription, String reqNode);

    /** 批量删除监听列表 **/
    void deleteSubscribeItems(ManagedSubscription subscription, List<String> reqNodes);

    /** 线程池中运行监听客户端 **/
    void runSubscriptionInThreadPool(ManagedSubscription subscription, List<String> batchIdentifiers, ExecutorService threadPoolExecutor);

     /** 线程池中运行订阅监听器**/
    void runSubscriptionListenerInThreadPool(OpcUaClient opcUaClient, ExecutorService threadPoolExecutor);

    /** 添加监听某一节点 **/
    void addSubscribeItem(ManagedSubscription subscription, String reqNode);

    /** 添加多个监听节点 **/
    void addSubscribeItems(ManagedSubscription subscription, List<String> batchIdentifiers);

     /** 创建订阅 **/
     ManagedSubscription createSubscription(OpcUaClient client, int publishingInterval);

    /** 禁用订阅 **/
    void disableSubscription(OpcUaClient client, ManagedSubscription subscription);

    /** 启用订阅 **/
    void enableSubscription(OpcUaClient client, ManagedSubscription subscription);

     /** 删除某个订阅 **/
     void deleteSubscription(ManagedSubscription subscription) throws UaException;

     /** 批量删除订阅 **/
    void deleteSubscriptions(ManagedSubscription subscription, OpcUaClient client);

     /** 订阅状态监听器 **/
    void subscriptionStatusListener(OpcUaClient opcUaClient);

     /** 数据变化监听器 **/
     void dataChangeCallback(ManagedSubscription subscription);

}
