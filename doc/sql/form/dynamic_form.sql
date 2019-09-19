/*
 Navicat Premium Data Transfer

 Source Server         : SAAS-dev
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : rm-bp1oydriw2vi7fc68qo.mysql.rds.aliyuncs.com:3306
 Source Schema         : saas_configuration

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 19/09/2019 16:45:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dynamic_form
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_form`;
CREATE TABLE `dynamic_form`  (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ClientId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端Id',
  `Title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `Model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块实体名',
  `IsComponent` tinyint(1) NULL DEFAULT NULL COMMENT '是否是自定义组件：0 :false 1:true',
  `componentName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自定义组件名',
  `ModelOrder` int(2) NULL DEFAULT NULL,
  `ModelGroup` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块组',
  `HideStatusIDs` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ComponentType` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实体格式: 0-obj  非0-array (i)[]',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态表单主表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dynamic_form
-- ----------------------------
INSERT INTO `dynamic_form` VALUES (1, '326a0000-56b2-0050-4bab-08d71169aa96', '基本信息', 'perBaseInfo', 1, 'ocrInfo', 1, '1', '', '0');
INSERT INTO `dynamic_form` VALUES (2, '326a0000-56b2-0050-4bab-08d71169aa96', '个人情况', 'perBaseInfo', 0, NULL, 2, '1', '', '0');
INSERT INTO `dynamic_form` VALUES (3, '326a0000-56b2-0050-4bab-08d71169aa96', '居住信息', 'perBaseInfo', 0, NULL, 3, '1', NULL, '0');
INSERT INTO `dynamic_form` VALUES (4, '326a0000-56b2-0050-4bab-08d71169aa96', '职业信息', 'careerInfoList', 0, NULL, 4, '1', NULL, '1');
INSERT INTO `dynamic_form` VALUES (5, '326a0000-56b2-0050-4bab-08d71169aa96', '紧急联系人', 'custEmergencyList', 0, NULL, 55, '2', NULL, '2');
INSERT INTO `dynamic_form` VALUES (6, '326a0000-56b2-0050-4bab-08d71169aa96', '其他情况说明', 'perBaseInfo', 0, NULL, 5, '1', NULL, '0');
INSERT INTO `dynamic_form` VALUES (7, '326a0000-56b2-0050-4bab-08d71169aa96', '居住信息', 'relativeModels', 0, NULL, 5, '2', NULL, '0');
INSERT INTO `dynamic_form` VALUES (8, '326a0000-56b2-0050-4bab-08d71169aa96', '职业信息', 'relativeModels', 0, NULL, 5, '2', NULL, '0');
INSERT INTO `dynamic_form` VALUES (9, '326a0000-56b2-0050-4bab-08d71169aa96', '征信材料', 'relativeModels', 0, NULL, 5, '9999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (10, '326a0000-56b2-0050-4bab-08d71169aa96', '银行卡开卡信息', 'prelendingOpenBankInfo', 0, NULL, 5, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (11, '326a0000-56b2-0050-4bab-08d71169aa96', '车辆信息', 'prelendingCapitalizeInfo', 0, 'finVehicle', 1, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (12, '326a0000-56b2-0050-4bab-08d71169aa96', '银行合作信息', 'prelendingCapitalizeInstallMent', 0, NULL, 7, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (13, '326a0000-56b2-0050-4bab-08d71169aa96', '银行担保信息', 'prelendingMortgageInfo', 0, NULL, 9, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (14, '326a0000-56b2-0050-4bab-08d71169aa96', '还款计划表', 'prelendingRepaymentplan', 0, 'repaymentplan', 11, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (15, '326a0000-56b2-0050-4bab-08d71169aa96', '二手车信息', 'usedCarInfo', 0, NULL, 3, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (16, '326a0000-56b2-0050-4bab-08d71169aa96', '产品信息', 'prelendingCapitalizeProduct', 1, 'productinfo', 4, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (17, '326a0000-56b2-0050-4bab-08d71169aa96', '上传承保意向书', 'prelendingOpenBankInfo1', 1, 'underWriting', 5, '999', ',-1,1,2,3,4,5,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (18, '326a0000-56b2-0050-4bab-08d71169aa96', '上传保险保单', 'prelendingOpenBankInfo3', 1, 'warranty', 5, '999', ',-1,1,2,3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (19, '326a0000-56b2-0050-4bab-08d71169aa96', '垫资开始', 'prelendingOpenBankInfo', 0, NULL, 5, '999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (20, '326a0000-56b2-0050-4bab-08d71169aa96', '签约信息确认', 'prelendingCapitalizeSimpleInfo', 0, 'signInfo', 6, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,9000,9001,', '0');
INSERT INTO `dynamic_form` VALUES (21, '326a0000-56b2-0050-4bab-08d71169aa96', '还款信息', 'prelendingOpenBankInfo', 0, NULL, 7, '9999', ',3,4,5,6,7,8,9,10,11,12,13,14,15,9000,9001,', '0');
INSERT INTO `dynamic_form` VALUES (22, '326a0000-56b2-0050-4bab-08d71169aa96', 'GPS安装信息', 'prelendingCapitalizeSimpleInfo', 0, 'gpsInatall', 8, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,9000,9001,', '0');
INSERT INTO `dynamic_form` VALUES (23, '326a0000-56b2-0050-4bab-08d71169aa96', 'GPS定位信息', 'gpsLocateInfo', 0, 'gpsLocation', 9, '9999', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,23,22,9999,21,25,24,', '0');
INSERT INTO `dynamic_form` VALUES (24, '326a0000-56b2-0050-4bab-08d71169aa96', '保险信息', 'prelendingInsuranceInfo', 0, 'insuranceInfo', 10, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,9000,9001,', '0');
INSERT INTO `dynamic_form` VALUES (25, '326a0000-56b2-0050-4bab-08d71169aa96', '抵押信息', 'uploadMortgageInfo', 0, NULL, 25, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,23,24,9000,9001,9002,9003,9004,', '0');
INSERT INTO `dynamic_form` VALUES (26, '326a0000-56b2-0050-4bab-08d71169aa96', '财务放款信息', 'prelendingAdvanceInfo', 0, NULL, 12, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,9000,9001,9002,9003,9004,', '0');
INSERT INTO `dynamic_form` VALUES (27, '326a0000-56b2-0050-4bab-08d71169aa96', '返利信息', 'prelendingRebateInfo', 0, NULL, 13, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,9000,9001,9002,9003,9004,', '0');
INSERT INTO `dynamic_form` VALUES (28, '326a0000-56b2-0050-4bab-08d71169aa96', '结清/垫资信息', 'prelendingMortgageInfo', 0, NULL, 30, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,9000,9001,9002,9003,9004,', '0');
INSERT INTO `dynamic_form` VALUES (29, '326a0000-56b2-0050-4bab-08d71169aa96', '抵押费用信息', 'prelendingMortgageInfo', 0, NULL, 11, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,9000,9001,9002,9003,9004,', '0');
INSERT INTO `dynamic_form` VALUES (30, '326a0000-56b2-0050-4bab-08d71169aa96', '银行打款信息', 'prelendingAdvanceInfo', 0, NULL, 14, '9999', '3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,9999,25,24,9000,9001,9002,9003,9004,', '0');
INSERT INTO `dynamic_form` VALUES (31, '326a0000-56b2-0050-6655-08d71169bdc2', '基本信息', 'perBaseInfo', 1, 'ocrInfo', 1, '1', '', '0');
INSERT INTO `dynamic_form` VALUES (32, '326a0000-56b2-0050-6655-08d71169bdc2', '个人情况', 'perBaseInfo', 0, NULL, 2, '1', '', '0');
INSERT INTO `dynamic_form` VALUES (33, '326a0000-56b2-0050-6655-08d71169bdc2', '居住信息', 'perBaseInfo', 0, NULL, 3, '1', NULL, '0');
INSERT INTO `dynamic_form` VALUES (34, '326a0000-56b2-0050-6655-08d71169bdc2', '职业信息', 'careerInfoList', 0, NULL, 4, '1', NULL, '1');
INSERT INTO `dynamic_form` VALUES (35, '326a0000-56b2-0050-6655-08d71169bdc2', '紧急联系人', 'custEmergencyList', 0, NULL, 55, '2', NULL, '2');
INSERT INTO `dynamic_form` VALUES (36, '326a0000-56b2-0050-6655-08d71169bdc2', '其他情况说明', 'perBaseInfo', 0, NULL, 5, '1', NULL, '0');
INSERT INTO `dynamic_form` VALUES (37, '326a0000-56b2-0050-6655-08d71169bdc2', '居住信息', 'relativeModels', 0, NULL, 5, '2', NULL, '0');
INSERT INTO `dynamic_form` VALUES (38, '326a0000-56b2-0050-6655-08d71169bdc2', '职业信息', 'relativeModels', 0, NULL, 5, '2', NULL, '0');
INSERT INTO `dynamic_form` VALUES (39, '326a0000-56b2-0050-6655-08d71169bdc2', '征信材料', 'relativeModels', 0, NULL, 5, '9999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (40, '326a0000-56b2-0050-6655-08d71169bdc2', '银行卡开卡信息', 'prelendingOpenBankInfo', 0, NULL, 5, '9999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (41, '326a0000-56b2-0050-6655-08d71169bdc2', '车辆信息', 'prelendingCapitalizeInfo', 0, 'finVehicle', 1, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (42, '326a0000-56b2-0050-6655-08d71169bdc2', '银行合作信息', 'prelendingCapitalizeInstallMent', 0, NULL, 7, '9999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (43, '326a0000-56b2-0050-6655-08d71169bdc2', '银行担保信息', 'prelendingMortgageInfo', 0, NULL, 9, '9999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (44, '326a0000-56b2-0050-6655-08d71169bdc2', '还款计划表', 'prelendingRepaymentplan', 0, 'repaymentplan', 11, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (45, '326a0000-56b2-0050-6655-08d71169bdc2', '二手车信息', 'usedCarInfo', 0, NULL, 3, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (46, '326a0000-56b2-0050-6655-08d71169bdc2', '产品信息', 'prelendingCapitalizeProduct', 1, 'productinfo', 4, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (47, '326a0000-56b2-0050-6655-08d71169bdc2', '上传承保意向书', 'prelendingOpenBankInfo1', 1, 'underWriting', 5, '9999', ',-1,1,2,3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (48, '326a0000-56b2-0050-6655-08d71169bdc2', '上传保险保单', 'prelendingOpenBankInfo3', 1, 'warranty', 5, '9999', ',-1,1,2,3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (49, '326a0000-56b2-0050-6655-08d71169bdc2', '垫资开始', 'prelendingOpenBankInfo', 0, NULL, 5, '999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (50, '326a0000-56b2-0050-6655-08d71169bdc2', '签约信息确认', 'prelendingCapitalizeSimpleInfo', 0, 'signInfo', 6, '4', ',3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (51, '326a0000-56b2-0050-6655-08d71169bdc2', '还款信息', 'prelendingOpenBankInfo', 0, NULL, 7, '4', ',3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (52, '326a0000-56b2-0050-6655-08d71169bdc2', 'GPS安装信息', 'prelendingCapitalizeSimpleInfo', 0, 'gpsInatall', 8, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,', '0');
INSERT INTO `dynamic_form` VALUES (53, '326a0000-56b2-0050-6655-08d71169bdc2', 'GPS定位信息', 'gpsLocateInfo', 0, 'gpsLocation', 9, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,23,', '0');
INSERT INTO `dynamic_form` VALUES (54, '326a0000-56b2-0050-6655-08d71169bdc2', '保险信息', 'prelendingInsuranceInfo', 0, 'insuranceInfo', 10, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,', '0');
INSERT INTO `dynamic_form` VALUES (55, '326a0000-56b2-0050-6655-08d71169bdc2', '抵押信息', 'uploadMortgageInfo', 0, NULL, 25, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,23,', '0');
INSERT INTO `dynamic_form` VALUES (56, '326a0000-56b2-0050-6655-08d71169bdc2', '财务放款信息', 'prelendingAdvanceInfo', 0, NULL, 12, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,', '0');
INSERT INTO `dynamic_form` VALUES (57, '326a0000-56b2-0050-6655-08d71169bdc2', '返利信息', 'prelendingRebateInfo', 0, NULL, 13, '9999', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,9999,', '0');
INSERT INTO `dynamic_form` VALUES (58, '326a0000-56b2-0050-6655-08d71169bdc2', '结清/垫资信息', 'prelendingMortgageInfo', 0, NULL, 30, '9999', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,22,9999,', '0');
INSERT INTO `dynamic_form` VALUES (59, '326a0000-56b2-0050-6655-08d71169bdc2', '抵押费用信息', 'prelendingMortgageInfo', 0, NULL, 11, '9999', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,21,', '0');
INSERT INTO `dynamic_form` VALUES (60, '326a0000-56b2-0050-6655-08d71169bdc2', '银行打款信息', 'prelendingMortgageInfo', 0, NULL, 14, '9999', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,23,22,', '0');
INSERT INTO `dynamic_form` VALUES (61, '326a0000-56b2-0050-0629-08d71169c2a7', '基本信息', 'perBaseInfo', 1, 'ocrInfo', 1, '1', '', '0');
INSERT INTO `dynamic_form` VALUES (62, '326a0000-56b2-0050-0629-08d71169c2a7', '个人情况', 'perBaseInfo', 0, NULL, 2, '1', '', '0');
INSERT INTO `dynamic_form` VALUES (63, '326a0000-56b2-0050-0629-08d71169c2a7', '居住信息', 'perBaseInfo', 0, NULL, 3, '1', NULL, '0');
INSERT INTO `dynamic_form` VALUES (64, '326a0000-56b2-0050-0629-08d71169c2a7', '职业信息', 'careerInfoList', 0, NULL, 4, '1', NULL, '1');
INSERT INTO `dynamic_form` VALUES (65, '326a0000-56b2-0050-0629-08d71169c2a7', '紧急联系人', 'custEmergencyList', 0, NULL, 55, '2', NULL, '2');
INSERT INTO `dynamic_form` VALUES (66, '326a0000-56b2-0050-0629-08d71169c2a7', '其他情况说明', 'perBaseInfo', 0, NULL, 5, '1', NULL, '0');
INSERT INTO `dynamic_form` VALUES (67, '326a0000-56b2-0050-0629-08d71169c2a7', '居住信息', 'relativeModels', 0, NULL, 5, '2', NULL, '0');
INSERT INTO `dynamic_form` VALUES (68, '326a0000-56b2-0050-0629-08d71169c2a7', '职业信息', 'relativeModels', 0, NULL, 5, '2', NULL, '0');
INSERT INTO `dynamic_form` VALUES (69, '326a0000-56b2-0050-0629-08d71169c2a7', '征信材料', 'relativeModels', 0, NULL, 5, '9999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (70, '326a0000-56b2-0050-0629-08d71169c2a7', '银行卡开卡信息', 'prelendingOpenBankInfo', 0, NULL, 5, '339999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (71, '326a0000-56b2-0050-0629-08d71169c2a7', '车辆信息', 'prelendingCapitalizeInfo', 0, 'finVehicle', 1, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (72, '326a0000-56b2-0050-0629-08d71169c2a7', '银行合作信息', 'prelendingCapitalizeInstallMent', 0, NULL, 7, '39999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (73, '326a0000-56b2-0050-0629-08d71169c2a7', '银行担保信息', 'prelendingMortgageInfo', 0, NULL, 9, '39999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (74, '326a0000-56b2-0050-0629-08d71169c2a7', '还款计划表', 'prelendingRepaymentplan', 0, 'repaymentplan', 11, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (75, '326a0000-56b2-0050-0629-08d71169c2a7', '二手车信息', 'usedCarInfo', 0, NULL, 3, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (76, '326a0000-56b2-0050-0629-08d71169c2a7', '产品信息', 'prelendingCapitalizeProduct', 1, 'productinfo', 4, '3', NULL, '0');
INSERT INTO `dynamic_form` VALUES (77, '326a0000-56b2-0050-0629-08d71169c2a7', '上传承保意向书', 'prelendingOpenBankInfo1', 1, 'underWriting', 5, '9999', ',-1,1,2,3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (78, '326a0000-56b2-0050-0629-08d71169c2a7', '上传保险保单', 'prelendingOpenBankInfo3', 1, 'warranty', 5, '9999', ',-1,1,2,3,4,5,6,7,8,9,10,11,12,13,', '0');
INSERT INTO `dynamic_form` VALUES (79, '326a0000-56b2-0050-0629-08d71169c2a7', '垫资开始', 'prelendingOpenBankInfo', 0, NULL, 5, '999', NULL, '0');
INSERT INTO `dynamic_form` VALUES (80, '326a0000-56b2-0050-0629-08d71169c2a7', '签约信息确认', 'prelendingCapitalizeSimpleInfo', 0, 'signInfo', 6, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,', '0');
INSERT INTO `dynamic_form` VALUES (81, '326a0000-56b2-0050-0629-08d71169c2a7', '还款信息', 'prelendingOpenBankInfo', 0, NULL, 7, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,', '0');
INSERT INTO `dynamic_form` VALUES (82, '326a0000-56b2-0050-0629-08d71169c2a7', 'GPS安装信息', 'prelendingCapitalizeSimpleInfo', 0, 'gpsInatall', 8, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,17,', '0');
INSERT INTO `dynamic_form` VALUES (83, '326a0000-56b2-0050-0629-08d71169c2a7', 'GPS定位信息', 'gpsLocateInfo', 0, 'gpsLocation', 9, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,17,', '0');
INSERT INTO `dynamic_form` VALUES (84, '326a0000-56b2-0050-0629-08d71169c2a7', '保险信息', 'prelendingInsuranceInfo', 0, 'insuranceInfo', 10, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,', '0');
INSERT INTO `dynamic_form` VALUES (85, '326a0000-56b2-0050-0629-08d71169c2a7', '抵押信息', 'uploadMortgageInfo', 0, NULL, 25, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,23,24,', '0');
INSERT INTO `dynamic_form` VALUES (86, '326a0000-56b2-0050-0629-08d71169c2a7', '财务放款信息', 'prelendingAdvanceInfo', 0, NULL, 12, '4', ',3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,', '0');

SET FOREIGN_KEY_CHECKS = 1;
