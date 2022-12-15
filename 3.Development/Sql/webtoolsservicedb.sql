/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : webtoolsservicedb

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 15/12/2022 19:21:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_article
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article`;
CREATE TABLE `tbl_article`  (
  `UUID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物品ID',
  `GROUP_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属经理ID',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物品名称',
  `MONEY_COST` int(0) NOT NULL COMMENT '生产一个单位所需的金钱成本',
  `TIME_COST` int(0) NOT NULL COMMENT '生产一个单位所需的时间成本',
  `NUMBER` int(0) NOT NULL COMMENT '数量',
  PRIMARY KEY (`UUID`) USING BTREE,
  INDEX `GROUP_ID`(`GROUP_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '物品表，存储原材料、产品及经销商库存信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_cost
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cost`;
CREATE TABLE `tbl_cost`  (
  `UUID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录ID',
  `RELATION_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关系ID',
  `TRANS_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '运输途径',
  `IS_VALID` int(0) NOT NULL COMMENT '是否有效（0-有效，1-无效）',
  `MONEY_COST` int(0) NOT NULL COMMENT '运输时间成本',
  `TIME_COST` int(0) NOT NULL COMMENT '运输金钱成本',
  PRIMARY KEY (`UUID`) USING BTREE,
  INDEX `RELATION_ID`(`RELATION_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '成本表，存储每种运输关系的运输途径和时间、金钱成本，以及该种运输途径是否有效' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_cost
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order`  (
  `UUID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单ID',
  `RELATION_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关系ID',
  `NUMBER` int(0) NOT NULL COMMENT '订购数量',
  `IS_CONFIRMED` int(0) NOT NULL COMMENT '是否已被确认（0-未确认，1-已确认）',
  PRIMARY KEY (`UUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表，存储各种订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_relation
-- ----------------------------
DROP TABLE IF EXISTS `tbl_relation`;
CREATE TABLE `tbl_relation`  (
  `UUID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关系的ID',
  `SOURCE_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '生产者ID',
  `DESTINATION_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消费者ID',
  PRIMARY KEY (`UUID`) USING BTREE,
  INDEX `SOURCE_ID`(`SOURCE_ID`) USING BTREE,
  INDEX `DESTINATION_ID`(`DESTINATION_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关系邻接表，存储产品和原材料、库存和产品以及用户和经销商的关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_relation
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `UUID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `GROUP_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属经理的ID',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `PASSWD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `CHANGE_PASSWD_TIME` bigint(0) NOT NULL COMMENT '下一次强制改密时间',
  `EMAIL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'E-mail',
  `PHONE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地址',
  `ENTITY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '实体类型',
  `ROLE` int(0) NOT NULL COMMENT '角色',
  PRIMARY KEY (`UUID`) USING BTREE,
  INDEX `GROUP_ID`(`GROUP_ID`) USING BTREE,
  UNIQUE INDEX `NAME`(`NAME`, `ENTITY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1f9fc55d-5937-4db0-80fd-f6e0e05824b3', NULL, 'admin', 'Changeme_123', -1, NULL, NULL, 'null', 'MANUFACTURE', 1);
INSERT INTO `tbl_user` VALUES ('9eb9673f-4a8c-41e0-98d6-7b2ea711a327', NULL, 'admin', 'Changeme_123', -1, NULL, NULL, 'null', 'SUPPLIER', 1);
INSERT INTO `tbl_user` VALUES ('d430b17e-4ee5-4b58-a6af-d0ca431c3f6f', NULL, 'admin', 'Changeme_123', -1, NULL, NULL, 'null', 'DISTRIBUTOR', 1);

SET FOREIGN_KEY_CHECKS = 1;
