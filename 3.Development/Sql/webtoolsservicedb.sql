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

 Date: 17/12/2022 22:46:29
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
  INDEX `GROUP_ID`(`GROUP_ID`) USING BTREE,
  UNIQUE INDEX `GROUP_ID_2`(`GROUP_ID`, `NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '物品表，存储原材料、产品及经销商库存信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article
-- ----------------------------
INSERT INTO `tbl_article` VALUES ('370befff-9f3a-42e6-936b-94dd19c65447', '5b292c8e-b454-43f1-ba55-bb8770419d29', 'Car', 200000, 30, 50);
INSERT INTO `tbl_article` VALUES ('4825c0fb-ebc2-454b-84dd-eeed75d8a581', '37132451-a0b5-4cbf-90e6-508ff12aaa58', 'Computer Chip', 1000, 10, 2000);
INSERT INTO `tbl_article` VALUES ('75c69e3c-1b3c-41dc-a228-106b6206d2a7', '3e6e119c-3469-4476-84ad-8e6ee8e7fc21', 'Laptop (C)', 1000, 7, 991);
INSERT INTO `tbl_article` VALUES ('8846acf5-0968-481f-9424-d52863fa33a8', '37132451-a0b5-4cbf-90e6-508ff12aaa58', 'Steel', 500, 5, 100);
INSERT INTO `tbl_article` VALUES ('8c6c7bb6-d974-4609-a9dc-3e19654b1ab4', '5b292c8e-b454-43f1-ba55-bb8770419d29', 'Laptop', 10000, 3, 150);
INSERT INTO `tbl_article` VALUES ('92e18966-bcf5-4ea1-8cc5-1104e71c4f75', '3e6e119c-3469-4476-84ad-8e6ee8e7fc21', 'Car（C）', 10000, 10, 5);

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
  `USER_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建订单的用户Id',
  PRIMARY KEY (`UUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表，存储各种订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
INSERT INTO `tbl_order` VALUES ('1f064b13-d2b2-4fc6-8586-90e5461219ae', '62a58992-e833-45f7-bec3-cb92700ff42c', 100, 0, NULL);
INSERT INTO `tbl_order` VALUES ('3082c6a9-942a-4dc3-95af-d8d63ce2cc0e', '62a58992-e833-45f7-bec3-cb92700ff42c', 100, 1, NULL);
INSERT INTO `tbl_order` VALUES ('4f5d1323-be42-46d2-8279-cf2006e41deb', '62a58992-e833-45f7-bec3-cb92700ff42c', 1, 0, NULL);
INSERT INTO `tbl_order` VALUES ('98a649dc-edb6-4316-a8e7-0018a671fc67', '92e18966-bcf5-4ea1-8cc5-1104e71c4f75', 1, 0, 'dc339591-d1c3-4576-9bb4-a74b72bd33cc');
INSERT INTO `tbl_order` VALUES ('b6f87354-d8f9-4d36-bc9d-7d1cd7fcaef3', '25e5fca5-9eb7-4c06-a8e3-795904e9e4e5', 1, 0, NULL);
INSERT INTO `tbl_order` VALUES ('d4846504-ca36-40ba-973d-3f134e81d6fc', 'bdf81444-8cff-48cd-85a5-efdcfe54b19e', 1, 0, NULL);
INSERT INTO `tbl_order` VALUES ('d9bb59b1-2eef-4848-a722-7d7d4c4d4395', '75c69e3c-1b3c-41dc-a228-106b6206d2a7', 3, 1, 'dc339591-d1c3-4576-9bb4-a74b72bd33cc');
INSERT INTO `tbl_order` VALUES ('f5e1c9b1-9509-42d9-80d5-dce723362ef8', '49082c51-dac4-45aa-a8c9-4742884b7673', 18, 0, NULL);

-- ----------------------------
-- Table structure for tbl_relation
-- ----------------------------
DROP TABLE IF EXISTS `tbl_relation`;
CREATE TABLE `tbl_relation`  (
  `UUID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关系的ID',
  `SOURCE_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '生产者ID',
  `DESTINATION_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消费者ID',
  `NUMBER` int(0) NOT NULL COMMENT '多少个生产者能生产一个消费者',
  PRIMARY KEY (`UUID`) USING BTREE,
  INDEX `SOURCE_ID`(`SOURCE_ID`) USING BTREE,
  INDEX `DESTINATION_ID`(`DESTINATION_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关系邻接表，存储产品和原材料、库存和产品以及用户和经销商的关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_relation
-- ----------------------------
INSERT INTO `tbl_relation` VALUES ('0b9cbfb1-9ab7-4336-b323-4825d3921861', '4825c0fb-ebc2-454b-84dd-eeed75d8a581', '370befff-9f3a-42e6-936b-94dd19c65447', 4);
INSERT INTO `tbl_relation` VALUES ('25e5fca5-9eb7-4c06-a8e3-795904e9e4e5', '8c6c7bb6-d974-4609-a9dc-3e19654b1ab4', '75c69e3c-1b3c-41dc-a228-106b6206d2a7', 1);
INSERT INTO `tbl_relation` VALUES ('49082c51-dac4-45aa-a8c9-4742884b7673', '8846acf5-0968-481f-9424-d52863fa33a8', '370befff-9f3a-42e6-936b-94dd19c65447', 2);
INSERT INTO `tbl_relation` VALUES ('62a58992-e833-45f7-bec3-cb92700ff42c', '4825c0fb-ebc2-454b-84dd-eeed75d8a581', '8c6c7bb6-d974-4609-a9dc-3e19654b1ab4', 1);
INSERT INTO `tbl_relation` VALUES ('bdf81444-8cff-48cd-85a5-efdcfe54b19e', '370befff-9f3a-42e6-936b-94dd19c65447', '92e18966-bcf5-4ea1-8cc5-1104e71c4f75', 1);

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
  UNIQUE INDEX `NAME`(`NAME`, `ENTITY`) USING BTREE,
  INDEX `GROUP_ID`(`GROUP_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('0a2bcc60-e528-46f6-89a5-5fd91b65f061', '37132451-a0b5-4cbf-90e6-508ff12aaa58', 'Manager1', 'Changeme_123', -1, NULL, NULL, '401-222 Shutter St, Toronto, ON M5A 4M3 ', 'SUPPLIER', 3);
INSERT INTO `tbl_user` VALUES ('1f9fc55d-5937-4db0-80fd-f6e0e05824b3', NULL, 'admin', 'Changeme_123', -1, NULL, NULL, '110-197 Yonge St, Toronto, ON, M5B 4A3 ', 'MANUFACTURE', 1);
INSERT INTO `tbl_user` VALUES ('37132451-a0b5-4cbf-90e6-508ff12aaa58', '37132451-a0b5-4cbf-90e6-508ff12aaa58', 'Manager2', 'Changeme_123', -1, NULL, NULL, '103-22 Bay st, Vancouver, BC V6A 1P9', 'SUPPLIER', 2);
INSERT INTO `tbl_user` VALUES ('3c8a86a6-cbee-488c-952d-1bf5e47e07ef', '37132451-a0b5-4cbf-90e6-508ff12aaa58', '123456', 'Changeme_123', -1, '', '', '123456789', 'SUPPLIER', 3);
INSERT INTO `tbl_user` VALUES ('5b292c8e-b454-43f1-ba55-bb8770419d29', '5b292c8e-b454-43f1-ba55-bb8770419d29', 'ManuPlantManager', 'Changeme_123', -1, NULL, NULL, '5401 11th Avenue West, Regina, SK S4P 3A1', 'MANUFACTURE', 2);
INSERT INTO `tbl_user` VALUES ('6a86df0b-013e-411a-8907-56bda1886eb4', '5b292c8e-b454-43f1-ba55-bb8770419d29', 'ManuManager', 'Changeme_123', -1, NULL, NULL, '3101 St Mary\'s Rd, Winnipeg, MB R2N 4B1', 'MANUFACTURE', 3);
INSERT INTO `tbl_user` VALUES ('9eb9673f-4a8c-41e0-98d6-7b2ea711a327', NULL, 'admin', 'Changeme_123', -1, NULL, NULL, '550- 2 West St, Montreal, QC J0T LJ0', 'SUPPLIER', 1);
INSERT INTO `tbl_user` VALUES ('a40f12c4-7aac-4f3c-9f83-f58998fd5fd2', '3e6e119c-3469-4476-84ad-8e6ee8e7fc21', 'Manager', 'Changeme_123', -1, '', '', '6100 O\'Grady Rd, Prince George, BC V2N 4R1', 'DISTRIBUTOR', 3);
INSERT INTO `tbl_user` VALUES ('d430b17e-4ee5-4b58-a6af-d0ca431c3f6f', NULL, 'admin', 'Changeme_123', -1, NULL, NULL, '130-700 Seniuk Rd, Winnipeg, MB R2N 4G3', 'DISTRIBUTOR', 1);
INSERT INTO `tbl_user` VALUES ('dc339591-d1c3-4576-9bb4-a74b72bd33cc', 'dc339591-d1c3-4576-9bb4-a74b72bd33cc', 'Customer1', 'Changeme_123', -1, 'cambridge_james@foxmail.com', '13856942036', '3630 Williams Road East, Prince George, BC V2N 5Z3', 'CUSTOMER', 2);

SET FOREIGN_KEY_CHECKS = 1;
