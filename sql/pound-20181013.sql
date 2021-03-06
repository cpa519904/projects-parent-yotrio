/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : pound

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-13 17:29:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for inspection
-- ----------------------------
DROP TABLE IF EXISTS `inspection`;
CREATE TABLE `inspection` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pl_id` int(11) unsigned DEFAULT NULL COMMENT '过磅单id',
  `pl_no` varchar(20) NOT NULL DEFAULT '' COMMENT '过磅单号',
  `insp_no` varchar(20) NOT NULL DEFAULT '' COMMENT '报检单号',
  `plate_no` varchar(20) NOT NULL COMMENT '车牌号',
  `goods_kind` int(4) NOT NULL DEFAULT '0' COMMENT '货品类型',
  `comp_name` varchar(30) NOT NULL DEFAULT '' COMMENT '供货商',
  `insp_weight` double(16,4) NOT NULL DEFAULT '0.0000' COMMENT '报价单重量',
  `return_weight` double(16,4) DEFAULT '0.0000' COMMENT '随车退重量',
  `types` int(4) NOT NULL DEFAULT '0' COMMENT '类型：是否样品',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `UK_pl_no` (`pl_no`) USING BTREE,
  KEY `UK_insp_no` (`insp_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspection
-- ----------------------------
INSERT INTO `inspection` VALUES ('14', null, '201810131101', '123456789', '浙J123C', '1', '华为铝业', '800.0000', '100.0000', '1', '0', '2018-10-13 11:03:05', null);
INSERT INTO `inspection` VALUES ('17', null, '201810131101', '123456987', '浙J123C', '2', '华为铝业', '300.0000', '0.0000', '0', '0', '2018-10-13 15:28:53', null);
INSERT INTO `inspection` VALUES ('18', null, '201810131101', '123456897', '浙J123C', '3', '华为铝业', '500.0000', '0.0000', '0', '0', '2018-10-13 15:33:52', null);
INSERT INTO `inspection` VALUES ('19', null, '201810131647', '2018-10-13-16-48', '浙J8888', '3', '伟星管业', '1500.0000', '0.0000', '0', '0', '2018-10-13 16:49:12', null);
INSERT INTO `inspection` VALUES ('20', null, '201810131655', '2018-10-13-16-57', '浙B684H', '2', '星星冷链', '2000.0000', '0.0000', '0', '0', '2018-10-13 16:58:16', null);
INSERT INTO `inspection` VALUES ('21', null, '201810131655', '2018-10-13-16-58', '浙B684H', '3', '星星冷链', '200.0000', '0.0000', '1', '0', '2018-10-13 16:59:04', null);

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
-- Records of pound_info
-- ----------------------------
INSERT INTO `pound_info` VALUES ('1', '总部东大门地方-1', '1000', 'Xk3190-A9+', null, '1', '2018-09-25 15:53:21');
INSERT INTO `pound_info` VALUES ('2', '总部东大门地方-2', '1000', 'Xk3190-A9+', null, '0', '2018-09-25 16:00:24');
INSERT INTO `pound_info` VALUES ('3', '总部西大门地方-3', '1003', 'Xk3190-A9+3', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('4', '总部东大门地方-4', '1004', 'Xk3190-A9+4', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('5', '总部南大门地方-5', '1005', 'Xk3190-A9+5', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('6', '总部北大门地方-6', '1006', 'Xk3190-A9+6', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('7', '总部南大门地方-7', '1007', 'Xk3190-A9+7', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('8', '总部南大门地方-8', '1008', 'Xk3190-A9+8', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('9', '总部南大门地方-9', '1009', 'Xk3190-A9+9', null, '0', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('10', '总部西大门地方-10', '1010', 'Xk3190-A9+10', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('11', '总部西大门地方-11', '1011', 'Xk3190-A9+11', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('12', '总部南大门地方-12', '1012', 'Xk3190-A9+12', null, '0', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('13', '总部东大门地方-13', '1013', 'Xk3190-A9+13', null, '0', '2018-09-25 16:07:02');

-- ----------------------------
-- Table structure for pound_log
-- ----------------------------
DROP TABLE IF EXISTS `pound_log`;
CREATE TABLE `pound_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pound_id` int(11) unsigned NOT NULL COMMENT '地磅id',
  `pound_name` varchar(50) DEFAULT '' COMMENT '地磅名',
  `pound_log_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '过磅单号',
  `gross_weight` double(16,4) DEFAULT '0.0000' COMMENT '毛重',
  `tare_weight` double(16,4) DEFAULT '0.0000' COMMENT '皮重',
  `net_weight` double(16,4) DEFAULT '0.0000' COMMENT '净重',
  `sample_net_weight` double(16,4) DEFAULT '0.0000' COMMENT '样品净重',
  `return_weight_total` double(16,4) DEFAULT '0.0000' COMMENT '随车退货重量',
  `diff_weight` double(16,4) DEFAULT '0.0000' COMMENT '磅差',
  `gross_img_url` varchar(100) DEFAULT NULL COMMENT '总重图片',
  `tare_img_url` varchar(100) DEFAULT NULL COMMENT '毛重图片',
  `unit_name` varchar(30) DEFAULT '' COMMENT '收货单位',
  `plate_no` varchar(20) NOT NULL DEFAULT '' COMMENT '车牌号',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `types` int(4) NOT NULL DEFAULT '0' COMMENT '类型',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `return_goods` int(4) DEFAULT '0' COMMENT '是否退货 0不是，1退货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `first_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '第一次过磅时间',
  `second_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '第二次过磅时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pound_log_no` (`pound_log_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pound_log
-- ----------------------------
INSERT INTO `pound_log` VALUES ('1', '1', 'æ»é¨ä¸å¤§é¨å°æ¹-1', '201810131101', null, null, null, null, null, null, null, null, null, '浙J123C', null, '0', '1', null, '2018-10-13 11:02:55', null, null, null);
INSERT INTO `pound_log` VALUES ('2', '1', 'æ»é¨ä¸å¤§é¨å°æ¹-1', '201810131647', null, null, null, null, null, null, null, null, null, '浙J8888', null, '0', '1', null, '2018-10-13 16:49:12', null, null, null);
INSERT INTO `pound_log` VALUES ('3', '1', 'æ»é¨ä¸å¤§é¨å°æ¹-1', '201810131655', null, null, null, null, null, null, null, null, null, '浙B684H', null, '0', '1', null, '2018-10-13 16:58:16', null, null, null);

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
  `execute_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', 'task-1', '第1个task', '2519', null, '1', '0', '1', '2018-09-27 15:29:10', null, '2018-09-30 13:10:39');
INSERT INTO `task` VALUES ('2', 'task-2', '第2个task', '8037', null, '0', '0', '1', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('3', 'task-3', '第3个task', '9051', null, '1', '0', '1', '2018-09-27 15:29:10', null, '2018-09-30 13:10:41');
INSERT INTO `task` VALUES ('4', 'task-4', '第4个task', '3112', null, '0', '0', '1', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('5', 'task-5', '第5个task', '5771', null, '0', '0', '0', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('6', 'task-6', '第6个task', '9711', null, '0', '0', '1', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('7', 'task-7', '第7个task', '7755', null, '0', '0', '1', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('8', 'task-8', '第8个task', '3794', null, '0', '0', '0', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('9', 'task-9', '第9个task', '5564', null, '0', '0', '1', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('10', 'task-10', '第10个task', '6990', null, '0', '0', '1', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('11', 'task-11', '第11个task', '7219', null, '0', '0', '0', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('12', 'task-12', '第12个task', '6421', null, '0', '0', '0', '2018-09-27 15:29:10', null, null);
INSERT INTO `task` VALUES ('13', 'task-13', '第13个task', '1742', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('14', 'task-14', '第14个task', '7483', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('15', 'task-15', '第15个task', '6350', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('16', 'task-16', '第16个task', '3165', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('17', 'task-17', '第17个task', '2859', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('18', 'task-18', '第18个task', '0126', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('19', 'task-19', '第19个task', '2694', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('20', 'task-20', '第20个task', '2862', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('21', 'task-21', '第21个task', '5218', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('22', 'task-22', '第22个task', '8449', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('23', 'task-23', '第23个task', '8674', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('24', 'task-24', '第24个task', '3577', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('25', 'task-25', '第25个task', '1881', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('26', 'task-26', '第26个task', '1061', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('27', 'task-27', '第27个task', '8455', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('28', 'task-28', '第28个task', '2792', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('29', 'task-29', '第29个task', '2140', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('30', 'task-30', '第30个task', '8430', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('31', 'task-31', '第31个task', '0980', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('32', 'task-32', '第32个task', '9241', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('33', 'task-33', '第33个task', '5469', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('34', 'task-34', '第34个task', '7830', null, '0', '0', '1', '2018-09-27 15:29:11', null, null);
INSERT INTO `task` VALUES ('35', 'task-35', '第35个task', '0878', null, '0', '0', '0', '2018-09-27 15:29:11', null, null);
