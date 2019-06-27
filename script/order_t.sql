/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : manager

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-06-27 10:54:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_t
-- ----------------------------
DROP TABLE IF EXISTS `order_t`;
CREATE TABLE `order_t` (
  `order_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单编号',
  `chan_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '渠道编号',
  `product_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品编号',
  `chan_user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '渠道用户编号',
  `order_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型：APPLY：申购，REDEEN：赎回',
  `order_status` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态，INIT：初始化，PROCESS：处理中，SUCCESS：成功，FAIL：失败',
  `outer_order_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '外部订单编号',
  `amount` decimal(15,3) NOT NULL COMMENT '金额',
  `memo` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
