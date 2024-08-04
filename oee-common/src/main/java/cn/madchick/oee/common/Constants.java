package cn.madchick.oee.common;

/**
 * @description:  常量定义
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class Constants {
    /**
     * 响应码
     */
    public enum ResponseCode {
        /**
         * 成功、失败、非法参数、主键冲突
         */
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        INDEX_DUP("0003", "主键冲突"),
        NO_UPDATE("0004", "SQL操作无更新"),
        NODE_EXIST("D001", "节点已存在"),
        RULE_ERR("D002", "规则执行失败"),
        NOT_CONSUMED_TAKE("D003", "未消费活动领取记录"),
        OUT_OF_STOCK("D004", "活动无库存"),
        ERR_TOKEN("D005", "分布式锁失败");

        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 活动状态：1编辑、2提审、3通过、4运行(审核通过后worker扫描状态)、5拒绝、6关闭、7开启
     */
    public enum ActivityState {

        /**
         * 1：编辑
         */
        EDIT(1, "编辑"),
        /**
         * 2：提审
         */
        ARRAIGNMENT(2, "提审"),
        /**
         * 3：通过
         */
        PASS(4, "通过"),
        /**
         * 4：运行(活动中)
         */
        DOING(5, "运行(活动中)"),
        /**
         * 5：拒绝
         */
        REFUSE(6, "拒绝"),
        /**
         * 6：关闭
         */
        CLOSE(7, "关闭"),
        /**
         * 7：开启
         */
        OPEN(8, "开启");

        private Integer code;
        private String info;

        ActivityState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 抽奖策略模式：定频采集、直接采集、定时采集、阈值过滤
     */
    public enum StrategyMode {
        /**
         * 直接采集：
         */
        HUNGRY(1, "直接采集"),
        /**
         * 定频采集：
         */
        FREQUENCY(2, "定频采集"),
        /**
         * 定时采集：
         */
        Timing(3, "定时采集"),
        /**
         * 阈值过滤：
         */
        THRESHOLD(4, "阈值过滤");

        private Integer code;
        private String info;

        StrategyMode(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 连接状态：0Good、1Bad、2Error
     */
    public enum ConnectStatus {
        /**
         * 信号质量好
         */
        GOOD(0, "Good"),

        /**
         * 信号质量差
         */
        BAD(1, "Bad"),

        /**
         * 错误
         */
        ERROR(2, "Error");

        private Integer code;
        private String info;

        ConnectStatus(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * Ids 生成策略枚举
     */
    public enum Ids {
        /** 雪花算法 */
        SnowFlake,
        /** 日期算法 */
        ShortCode,
        /** 随机算法 */
        RandomNumeric;
    }

    public enum MiningTaskType{
        /** 间隔采样 */
        IntervalSampling(3, "间隔采样"),
        /** 心跳采样 */
        HearBeatSampling(1, "心跳采样"),
        /** 阈值采样 */
        ThresholdSampling(2, "阈值采样");

        private Integer code;
        private String info;

        MiningTaskType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 消息发送状态（0未发送、1发送成功、2发送失败）
     */
    public enum MQState {
        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");

        private Integer code;
        private String info;

        MQState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
