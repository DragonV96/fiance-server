/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : manager

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-06-27 10:43:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品名称',
  `threshold_amount` decimal(15,3) NOT NULL COMMENT '起投金额',
  `step_amount` decimal(15,3) NOT NULL COMMENT '投资步长',
  `lock_term` smallint(6) NOT NULL COMMENT '锁定期',
  `reward_rate` decimal(5,3) NOT NULL COMMENT '收益率，0-100 百分比',
  `status` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态，AUDINTING：审核中，IN_SELL：销售中，LOCKED：暂停销售，FINISHED：已结束',
  `memo` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_user` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
