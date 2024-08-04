package cn.madchick.oee.domain.opcua.config;

/**
 * @description: opcua客户端配置属性
 * @author：jagger
 * @date: 2024/6/29
 * @Copyright： jagger - www.madchick.cn
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opc.client.config", ignoreInvalidFields = true)
public class OpcUaClientProperties {
    /** 状态；open = 开启、close 关闭 */
    private boolean enable;
    /** opcua client host */
    private String host;
    /** opcua client port */
    private String port;
    /** opcua client path 格式：opc.tcp://localhost:62541/milo*/
    private String path;
    /** 账密 */
    private String password;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
