/*
 Navicat Premium Data Transfer

 Source Server         : mysqlStudy
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : localhost
 Source Database       : pound_master

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : utf-8

 Date: 10/22/2018 08:23:33 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `inspection`
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `inspection`
-- ----------------------------
BEGIN;
INSERT INTO `inspection` VALUES ('39', '1', '201810131655', '2018-10-13-16-57', '浙B684H', '1', '星星冷链', '2000.0000', '0.0000', '1', '0', '2018-10-20 13:34:36', '2018-10-15 16:43:33'), ('40', '1', '201810131655', '2018-10-13-16-58', '浙B684H', '3', '星星冷链', '200.0000', '0.0000', '0', '0', '2018-10-20 13:34:36', '2018-10-16 09:09:43'), ('41', '1', '201810131655', '2018-10-15-13-33', '浙B684H', '2', '星星冷链', '900.0000', '300.0000', '0', '0', '2018-10-20 13:34:36', '2018-10-16 10:04:49'), ('42', '1', '20181016161428', 'M2N0677004', '1006612020345396', '2', 'RCV', '123.0000', '123.0000', '0', '0', '2018-10-20 15:57:16', null);
COMMIT;

-- ----------------------------
--  Table structure for `pound_info`
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
--  Records of `pound_info`
-- ----------------------------
BEGIN;
INSERT INTO `pound_info` VALUES ('1', '总部东大门地方-1', '1000', 'Xk3190-A9+', null, '1', '2018-09-25 15:53:21'), ('2', '总部东大门地方-2', '1000', 'Xk3190-A9+', null, '0', '2018-09-25 16:00:24'), ('3', '总部西大门地方-3', '1003', 'Xk3190-A9+3', null, '1', '2018-09-25 16:07:02'), ('4', '总部东大门地方-4', '1004', 'Xk3190-A9+4', null, '1', '2018-09-25 16:07:02'), ('5', '总部南大门地方-5', '1005', 'Xk3190-A9+5', null, '1', '2018-09-25 16:07:02'), ('6', '总部北大门地方-6', '1006', 'Xk3190-A9+6', null, '1', '2018-09-25 16:07:02'), ('7', '总部南大门地方-7', '1007', 'Xk3190-A9+7', null, '1', '2018-09-25 16:07:02'), ('8', '总部南大门地方-8', '1008', 'Xk3190-A9+8', null, '1', '2018-09-25 16:07:02'), ('9', '总部南大门地方-9', '1009', 'Xk3190-A9+9', null, '0', '2018-09-25 16:07:02'), ('10', '总部西大门地方-10', '1010', 'Xk3190-A9+10', null, '1', '2018-09-25 16:07:02'), ('11', '总部西大门地方-11', '1011', 'Xk3190-A9+11', null, '1', '2018-09-25 16:07:02'), ('12', '总部南大门地方-12', '1012', 'Xk3190-A9+12', null, '0', '2018-09-25 16:07:02'), ('13', '总部东大门地方-13', '1013', 'Xk3190-A9+13', null, '0', '2018-09-25 16:07:02');
COMMIT;

-- ----------------------------
--  Table structure for `pound_log`
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
  `gross_img_url` varchar(200) DEFAULT NULL COMMENT '总重图片',
  `tare_img_url` varchar(200) DEFAULT NULL COMMENT '毛重图片',
  `unit_name` varchar(30) DEFAULT '' COMMENT '收货单位',
  `plate_no` varchar(20) DEFAULT '' COMMENT '车牌号',
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `pound_log`
-- ----------------------------
BEGIN;
INSERT INTO `pound_log` VALUES ('18', '1', '总部东大门地方-1', '201810131655', '9791.0000', '5610.0000', '1881.0000', '2000.0000', '300.0000', '1081.0000', 'http://localhost:8008/images/upload/2018/10/20/201810131655/1540013675935.jpg', 'http://localhost:8008/images/upload/2018/10/20/201810131655/1540013675937.jpg', '生产一部', '浙B684H', '备注啥的啊啊', '0', '2', null, '2018-10-13 16:58:16', '2018-10-20 10:30:57', '2018-10-18 11:27:29', '2018-10-18 10:56:54'), ('19', '1', '总部东大门地方-1', '20181019093610', '11748.0000', '8694.0000', '3054.0000', '0.0000', '0.0000', '2804.0000', 'http://localhost:8008/images/upload/2018/10/20/20181019093610/1540021863802.jpg', 'http://localhost:8008/images/upload/2018/10/20/20181019093610/1540021863803.jpg', '', '1006612020345396', '', '0', '2', null, '2018-10-19 09:39:03', '2018-10-20 13:29:06', '2018-10-19 09:39:48', '2018-10-19 09:39:39'), ('20', '1', '总部东大门地方-1', '20181016161428', '11610.0000', '8162.0000', '3448.0000', '0.0000', '123.0000', '0.0000', 'http://localhost:8008/images/upload/2018/10/20/20181016161428/1540022236281.jpg', 'http://localhost:8008/images/upload/2018/10/20/20181016161428/1540022236283.jpg', '', null, '卖废品', '1', '2', null, '2018-10-16 16:14:31', '2018-10-20 11:19:53', '2018-10-20 10:45:20', '2018-10-19 09:17:25');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(50) NOT NULL COMMENT '权限名称',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限',
  `permission_desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `type` varchar(50) NOT NULL COMMENT '类型;menu、button',
  `url` varchar(50) DEFAULT NULL COMMENT 'url',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `pids` varchar(100) DEFAULT NULL COMMENT '层级关系;0/1/41',
  `status` int(5) NOT NULL DEFAULT '0' COMMENT '是否启用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_cname` varchar(50) DEFAULT NULL COMMENT '角色中文名称	',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `emp_id` int(11) unsigned DEFAULT NULL COMMENT '工号',
  `dind_uid` varchar(20) DEFAULT '' COMMENT '钉钉用户id',
  `group_id` int(11) unsigned DEFAULT NULL COMMENT '分组id',
  `nickname` varchar(20) DEFAULT '' COMMENT '昵称',
  `rank` int(3) DEFAULT '0' COMMENT '等级',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '状态',
  `created_by` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `phone` varchar(20) DEFAULT '' COMMENT '发送验证码的手机号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `last_login_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次登录时间',
  `last_login_ip` varchar(200) DEFAULT NULL COMMENT '上次登录ip',
  `email` varchar(30) DEFAULT '' COMMENT '邮箱',
  `salt` varchar(100) NOT NULL COMMENT '盐，加密用的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `emp_id` int(11) DEFAULT NULL COMMENT '工号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
