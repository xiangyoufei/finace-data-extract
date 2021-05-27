/*
 Navicat Premium Data Transfer

 Source Server         : ailiyun-mysql
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 114.215.175.196:33306
 Source Schema         : finace_data

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 13/05/2021 19:26:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for finance_data_day
-- ----------------------------
DROP TABLE IF EXISTS `finance_data_day`;
CREATE TABLE `finance_data_day`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `value` double NULL DEFAULT NULL COMMENT '值',
  `high` double NULL DEFAULT NULL COMMENT '最大值',
  `low` double NULL DEFAULT NULL COMMENT '最小值',
  `rate` double NULL DEFAULT NULL COMMENT '比率',
  `charge` double NULL DEFAULT NULL COMMENT '涨跌额',
  `open` double NULL DEFAULT NULL COMMENT '开盘',
  `prev_close` double NULL DEFAULT NULL COMMENT '昨收',
  `data_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指数代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
