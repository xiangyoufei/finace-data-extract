SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for finance_data
-- ----------------------------
DROP TABLE IF EXISTS `finance_data`;
CREATE TABLE `finance_data`  (
                                 `id` int(0) NOT NULL,
                                 `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指数名称',
                                 `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指数代码',
                                 `time` datetime(0) NULL DEFAULT NULL COMMENT '数据时间',
                                 `date` date NULL DEFAULT NULL COMMENT '数据日期',
                                 `value` double NULL DEFAULT NULL COMMENT '指数值',
                                 `high` double NULL DEFAULT NULL COMMENT '最高值',
                                 `low` double NULL DEFAULT NULL COMMENT '最低值',
                                 `rate` double NULL DEFAULT NULL COMMENT '涨幅',
                                 `charge` double NULL DEFAULT NULL COMMENT '涨跌额',
                                 `open` double NULL DEFAULT NULL COMMENT '开盘价',
                                 `prev_close` double NULL DEFAULT NULL COMMENT '昨夜收盘价',
                                 `data_time` date NULL DEFAULT NULL COMMENT '数据获取时间',
                                 `precision` int(0) NULL DEFAULT NULL COMMENT '小数点位数',
                                 `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据分析类型',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_data_analyse
-- ----------------------------
DROP TABLE IF EXISTS `finance_data_analyse`;
CREATE TABLE `finance_data_analyse`  (
                                         `id` int(0) NOT NULL,
                                         `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指数名称',
                                         `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指数代码',
                                         `time` datetime(0) NULL DEFAULT NULL COMMENT '数据时间',
                                         `date` date NULL DEFAULT NULL COMMENT '数据日期',
                                         `value` double NULL DEFAULT NULL COMMENT '指数值',
                                         `high` double NULL DEFAULT NULL COMMENT '最高值',
                                         `low` double NULL DEFAULT NULL COMMENT '最低值',
                                         `rate` double NULL DEFAULT NULL COMMENT '涨幅',
                                         `charge` double NULL DEFAULT NULL COMMENT '涨跌额',
                                         `open` double NULL DEFAULT NULL COMMENT '开盘价',
                                         `prev_close` double NULL DEFAULT NULL COMMENT '昨夜收盘价',
                                         `data_time` date NULL DEFAULT NULL COMMENT '数据获取时间',
                                         `precision` int(0) NULL DEFAULT NULL COMMENT '小数点位数',
                                         `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据分析类型',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for finance_indicator
-- ----------------------------
DROP TABLE IF EXISTS `finance_indicator`;
CREATE TABLE `finance_indicator`  (
                                      `id` int(0) NOT NULL,
                                      `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'code',
                                      `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'code',
                                      `code_prefix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                      `valid` tinyint(0) NULL DEFAULT NULL,
                                      `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for holiday
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday`  (
                            `id` int(0) NOT NULL,
                            `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '节日名称',
                            `date` date NULL DEFAULT NULL COMMENT '节日日期',
                            `year` int(0) NULL DEFAULT NULL COMMENT '年份',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
                                          `installed_rank` int(0) NOT NULL,
                                          `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                          `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                          `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                          `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                          `checksum` int(0) NULL DEFAULT NULL,
                                          `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                          `installed_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                          `execution_time` int(0) NOT NULL,
                                          `success` tinyint(1) NOT NULL,
                                          PRIMARY KEY (`installed_rank`) USING BTREE,
                                          INDEX `flyway_schema_history_s_idx`(`success`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
