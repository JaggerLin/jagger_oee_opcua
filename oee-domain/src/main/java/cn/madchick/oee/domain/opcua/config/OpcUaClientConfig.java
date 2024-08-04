package cn.madchick.oee.domain.opcua.config;

import cn.hutool.json.JSONUtil;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description: OpcUaClient配置类
 * @author：jagger
 * @date: 2024/6/29
 * @Copyright： jagger - www.madchick.cn
 */
@Configuration
@EnableConfigurationProperties(OpcUaClientProperties.class)
public class OpcUaClientConfig {

    private final Logger logger = LoggerFactory.getLogger(OpcUaClientConfig.class);

    @Bean(name = "OpcUaClient")
    public OpcUaClient opcUaClientConnect(OpcUaClientProperties opcUaClientProperties) throws Exception {
        // 1.生成连接地址
        String host = opcUaClientProperties.getHost();
        String port = opcUaClientProperties.getPort();
        String path = opcUaClientProperties.getPath();
        String endPointUrl = "opc.tcp://" + host + ":" + port + path;
        logger.info("endpoint url: {}", endPointUrl);
        // 2.创建临时目录, 用于存放安全证书
        Path securityTempDir = Paths.get(System.getProperty("java.io.tmpdir"), "security");
        // 3.如果目录不存在，则创建, 否则抛出异常
        Files.createDirectories(securityTempDir);
        if (!Files.exists(securityTempDir)) {
            throw new Exception("unable to create security dir: " + securityTempDir);
        }
        logger.info("security temp dir: {}", securityTempDir);
        // 4.创建客户端实例
        OpcUaClient opcUaClient = OpcUaClient.create(endPointUrl,
                endpoints ->
                        endpoints.stream() // 获取所有端点
                                .filter(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getUri())) // 选择安全策略, 这里选择None
                                .findFirst(), // 选择第一个安全策略
                configBuilder ->
                        configBuilder // 配置
                                .setApplicationName(LocalizedText.english("eclipse milo opc-ua client")) //应用程序名称
                                .setApplicationUri("urn:eclipse:milo:examples:client") //应用程序URI
                                //访问方式
                                .setIdentityProvider(new AnonymousProvider()) // 匿名访问
                                .setRequestTimeout(UInteger.valueOf(5000)) // 请求超时时间
                                .build() // 构建
        );
        // 5.连接
        opcUaClient.connect().get();
        logger.info("opcUaClient creating,please wait......");
        // 6.返回客户端实例:线程休眠一下再返回对象，给创建过程一个时间。
        Thread.sleep(3000);
        logger.info("opcUaClient connected.");
        return opcUaClient;
    }

}
