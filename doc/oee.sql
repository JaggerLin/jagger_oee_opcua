
create database OeeOpc;

USE OeeOpc;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动名称',
  `area` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区域',
  `passwd` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '密码',
  `state` tinyint(2) DEFAULT NULL COMMENT '活动状态',
  `creator` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='活动配置';

-- ----------------------------
-- Records of activity
-- ----------------------------
BEGIN;
INSERT INTO `activity` VALUES (1, 20243001, '三线采集测试', 'MD3', "12345",1, 'jagger', '2021-08-08 20:14:50', '2021-08-08 20:14:50');
COMMIT;

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `node_id` bigint(11)  NOT NULL COMMENT '节点ID',
  `node_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '节点名称',
  `node_type` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '数据类型',
  `connect_status` tinyint(2) DEFAULT NULL COMMENT '连接状态',
  `opc_enabled` BOOLEAN DEFAULT NULL COMMENT '启用状态',
  `scan_cycle` int(11) DEFAULT NULL COMMENT '扫描速率',
  `is_sensor` BOOLEAN DEFAULT NULL COMMENT '传感器',
  `ip_address` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'ip',
  `device_code` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '编码',
  `area` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区域',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='节点配置';

-- ----------------------------
-- Records of node
-- ----------------------------
BEGIN;
INSERT INTO `node` VALUES (1, 30001, 'Dynamic/RandomDouble', 'DOUBLE', 1, TRUE, 5000, FALSE, '192.168.0.1', 'P301', 'MD3', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `node` VALUES (2, 30002, 'Dynamic/RandomFloat', 'FLOAT', 1, TRUE, 1000, FALSE, '192.168.0.1', 'P301', 'MD3', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `node` VALUES (3, 30003, 'Dynamic/RandomInt32', 'INTEGER', 1, TRUE, 1000, FALSE, '192.168.0.1', 'P301', 'MD3', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `node` VALUES (4, 30004, 'Dynamic/RandomInt64', 'INTEGER', 1, TRUE, 1000, FALSE, '192.168.0.1', 'P301', 'MD3', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `node` VALUES (5, 30005, 'ComplexTypes/CustomEnumTypeVariable', 'INTEGER', 1, TRUE, 2000, FALSE, '192.168.0.1', 'P301', 'MD3', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
COMMIT;

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `strategy_desc` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '策略描述',
  `strategy_mode` tinyint(2) DEFAULT NULL COMMENT '策略方式',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ext_info` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '扩展信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `strategy_strategyId_uindex` (`strategy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略配置';

-- ----------------------------
-- Records of strategy
-- ----------------------------
BEGIN;
INSERT INTO `strategy` VALUES (1, 10001, 'Hungry', 1,  '2021-09-04 15:37:52', '2021-09-04 15:37:52', NULL);
COMMIT;

-- ----------------------------
-- Table structure for strategy_detail
-- ----------------------------
DROP TABLE IF EXISTS `strategy_detail`;
CREATE TABLE `strategy_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `node_id` bigint(11) NOT NULL COMMENT '节点ID',
  `node_name` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '节点描述',
  `frequency` int(11) DEFAULT NULL COMMENT '监控频率',
  `threshold` int(11) DEFAULT '0' COMMENT '阈值',
  `begin_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `stop_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略明细';

-- ----------------------------
-- Records of strategy_detail
-- ----------------------------
BEGIN;
INSERT INTO `strategy_detail` VALUES (1, 10001, 3001, '运行信号', 1000, 6000, '2024-08-15 15:38:05', '2024-08-16 15:38:05','2021-08-15 15:38:05', '2021-08-15 15:38:05');
COMMIT;

-- ----------------------------
-- Table structure for opc_task
-- ----------------------------
DROP TABLE IF EXISTS `opc_task`;
CREATE TABLE `opc_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `node_id` bigint(20) NOT NULL COMMENT '节点ID',
  `node_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '节点名称',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动名称',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `opc_enabled` BOOLEAN DEFAULT NULL COMMENT '启用状态',
  `uuid` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'uuid',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='任务配置';

SET FOREIGN_KEY_CHECKS = 1;
