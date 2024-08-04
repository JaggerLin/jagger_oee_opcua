package cn.madchick.oee.domain.opcua.service.mining.impl;

import cn.madchick.oee.domain.opcua.service.mining.IOpcUaSamplingTask;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @description: 间隔采样：按照设定的采样周期执行
 * @author：jagger
 * @date: 2024/7/2
 * @Copyright： jagger - www.madchick.cn
 */
@Component
public class IntervalSamplingTask implements IOpcUaSamplingTask {

    private final Logger logger = LoggerFactory.getLogger(DoOpcUaMiningImpl.class);


    @Override
    public void deleteSubscribeItem(ManagedSubscription subscription, String reqNode) {

    }

    @Override
    public void deleteSubscribeItems(ManagedSubscription subscription, List<String> reqNodes) {

    }

    @Override
    public void runSubscriptionInThreadPool(ManagedSubscription subscription, List<String> batchIdentifiers, ExecutorService threadPoolExecutor) {

    }

    @Override
    public void runSubscriptionListenerInThreadPool(OpcUaClient opcUaClient, ExecutorService threadPoolExecutor) {

    }

    @Override
    public void addSubscribeItem(ManagedSubscription subscription, String reqNode) {

    }

    @Override
    public void addSubscribeItems(ManagedSubscription subscription, List<String> batchIdentifiers) {

    }

    @Override
    public ManagedSubscription createSubscription(OpcUaClient client, int publishingInterval) {
        return null;
    }

    @Override
    public void disableSubscription(OpcUaClient client, ManagedSubscription subscription) {

    }

    @Override
    public void enableSubscription(OpcUaClient client, ManagedSubscription subscription) {

    }

    @Override
    public void deleteSubscription(ManagedSubscription subscription) throws UaException {

    }

    @Override
    public void deleteSubscriptions(ManagedSubscription subscription, OpcUaClient client) {

    }

    @Override
    public void subscriptionStatusListener(OpcUaClient opcUaClient) {

    }

    @Override
    public void dataChangeCallback(ManagedSubscription subscription) {

    }
}
