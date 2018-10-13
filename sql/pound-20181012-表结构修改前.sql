/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : pound

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-12 14:51:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pound_info
-- ----------------------------
DROP TABLE IF EXISTS `pound_info`;
CREATE TABLE `pound_info` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pound_name` varchar(20) NOT NULL DEFAULT '' COMMENT '地磅名',
  `unit_id` int(5) NOT NULL COMMENT '所属单位',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pound_log
-- ----------------------------
DROP TABLE IF EXISTS `pound_log`;
CREATE TABLE `pound_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pound_id` int(11) unsigned NOT NULL COMMENT '地磅id',
  `pound_name` varchar(20) DEFAULT '' COMMENT '地磅名',
  `gross_weight` double(16,4) DEFAULT '0.0000' COMMENT '总重',
  `tare_weight` double(16,4) DEFAULT '0.0000' COMMENT '毛重',
  `net_weight` double(16,4) DEFAULT '0.0000' COMMENT '净重',
  `gross_img_url` varchar(100) DEFAULT NULL COMMENT '总重图片',
  `tare_img_url` varchar(100) DEFAULT NULL COMMENT '毛重图片',
  `goods_name` varchar(30) NOT NULL DEFAULT '' COMMENT '货品',
  `comp_name` varchar(30) NOT NULL DEFAULT '' COMMENT '供货商',
  `unit_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货单位',
  `delivery_numb` varchar(30) NOT NULL DEFAULT '' COMMENT '货单号',
  `plate_numb` varchar(20) NOT NULL DEFAULT '' COMMENT '车牌号',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_delivery_numb` (`delivery_numb`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `task_name` varchar(20) NOT NULL DEFAULT '' COMMENT '任务名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `other_id` varchar(20) DEFAULT '' COMMENT '备注',
  `datas` varchar(1000) DEFAULT '' COMMENT '数据,推荐json格式',
  `weight` int(11) DEFAULT '0' COMMENT '权重',
  `types` int(4) NOT NULL DEFAULT '0' COMMENT '类型',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `execute_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
