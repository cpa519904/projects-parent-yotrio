/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : pound_master

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-24 17:30:14
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
  `insp_net_weight` double(16,4) DEFAULT NULL COMMENT '报检单上的净重',
  `types` int(4) NOT NULL DEFAULT '0' COMMENT '类型：是否样品',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `UK_pl_no` (`pl_no`) USING BTREE,
  KEY `UK_insp_no` (`insp_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspection
-- ----------------------------
INSERT INTO `inspection` VALUES ('55', '1', '201810131655', '2018-10-13-16-57', '浙B684H', '1', '星星冷链', '2000.0000', '0.0000', null, '1', '0', '2018-10-23 13:37:27', '2018-10-15 16:43:33');
INSERT INTO `inspection` VALUES ('56', '1', '201810131655', '2018-10-13-16-58', '浙B684H', '3', '星星冷链', '200.0000', '0.0000', null, '0', '0', '2018-10-23 13:37:27', '2018-10-16 09:09:43');
INSERT INTO `inspection` VALUES ('57', '1', '201810131655', '2018-10-15-13-33', '浙B684H', '2', '星星冷链', '900.0000', '300.0000', null, '0', '0', '2018-10-23 13:37:27', '2018-10-16 10:04:49');
INSERT INTO `inspection` VALUES ('58', '1', '201810131647', '2018-10-13-16-48', '浙J8888', '3', '伟星管业', '1500.0000', '20.0000', null, '0', '0', '2018-10-23 14:38:50', '2018-10-15 14:14:36');
INSERT INTO `inspection` VALUES ('59', '1', '201810131647', '2018-10-15-10-18', '浙J8888', '1', '伟星管业1', '1500.0000', '0.0000', null, '0', '0', '2018-10-23 14:38:50', '2018-10-17 09:23:39');
INSERT INTO `inspection` VALUES ('60', '1', '201810131647', '2018-10-16-16-46', '浙J8888', '1', '伟星管业', '500.0000', null, null, '0', '0', '2018-10-23 14:38:50', null);
INSERT INTO `inspection` VALUES ('61', '1', '20181016161428', 'M2N0677004', '1006612020345396', '2', 'RCV', '123.0000', '123.0000', null, '0', '0', '2018-10-23 14:51:50', null);
INSERT INTO `inspection` VALUES ('62', '1', '201810131101', '2018-10-15-13-16', '浙J123C', '2', '华为铝业', '800.0000', '120.0000', null, '1', '0', '2018-10-23 14:57:31', '2018-10-15 13:48:53');
INSERT INTO `inspection` VALUES ('63', '1', '20181023150800', 'aaaaaaaaaaaaaaaaaa', '浙C1728', '2', '飞机速有限公司', '1800.0000', '0.0000', null, '0', '0', '2018-10-23 15:23:23', '2018-10-23 15:19:00');

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
  `goods_kind` int(4) DEFAULT NULL COMMENT '货品类型',
  `comp_name` varchar(50) DEFAULT '' COMMENT '供应商名称',
  `gross_weight` double(16,4) DEFAULT '0.0000' COMMENT '毛重',
  `tare_weight` double(16,4) DEFAULT '0.0000' COMMENT '皮重',
  `net_weight` double(16,4) DEFAULT '0.0000' COMMENT '净重',
  `sample_net_weight` double(16,4) DEFAULT '0.0000' COMMENT '样品净重',
  `return_weight_total` double(16,4) DEFAULT '0.0000' COMMENT '随车退货重量',
  `insp_weight_total` double(16,4) DEFAULT NULL COMMENT '过磅单总重',
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pound_log
-- ----------------------------
INSERT INTO `pound_log` VALUES ('34', '1', '总部东大门地方-1', '20181019093610', null, null, '11748.0000', '8694.0000', '3054.0000', '0.0000', '0.0000', '0.0000', '2804.0000', 'http://localhost:8008/images/upload/2018/10/23/20181019093610/1540282668359.jpg', 'http://localhost:8008/images/upload/2018/10/23/20181019093610/1540282668376.jpg', '制造三部', '1006612020345396', '', '0', '2', null, '2018-10-19 09:39:03', '2018-10-23 14:57:06', '2018-10-19 09:39:48', '2018-10-19 09:39:39');
INSERT INTO `pound_log` VALUES ('35', '1', '总部东大门地方-1', '20181019092347', null, null, '12729.0000', '6707.0000', '6022.0000', '0.0000', '0.0000', '0.0000', null, 'http://localhost:8008/images/upload/2018/10/24/20181019092347/1540359762641.jpg', 'http://localhost:8008/images/upload/2018/10/24/20181019092347/1540359762645.jpg', '废品站', null, '出货', '1', '2', null, '2018-10-19 09:29:47', '2018-10-23 17:13:56', '2018-10-22 11:35:12', '2018-10-22 11:35:14');
INSERT INTO `pound_log` VALUES ('36', '1', '总部东大门地方-1', '201810131655', '1', '星星冷链', '13925.0000', '6280.0000', '5345.0000', '2000.0000', '300.0000', '3100.0000', '4545.0000', 'http://localhost:8008/images/upload/2018/10/24/201810131655/1540363696537.jpg', 'http://localhost:8008/images/upload/2018/10/24/201810131655/1540363696540.jpg', '生产一部', '浙B684H', '备注啥的啊啊', '0', '2', null, '2018-10-13 16:58:16', '2018-10-24 14:25:51', '2018-10-23 14:50:02', '2018-10-24 13:07:30');
INSERT INTO `pound_log` VALUES ('37', '1', '总部东大门地方-1', '20181016155116', null, null, '13943.0000', '12995.0000', '948.0000', '0.0000', '0.0000', '0.0000', '0.0000', 'http://localhost:8008/images/upload/2018/10/24/20181016155116/1540363364963.jpg', 'http://localhost:8008/images/upload/2018/10/24/20181016155116/1540363364965.jpg', '共产党', null, '退货或者卖废品', '1', '2', null, '2018-10-16 15:51:20', '2018-10-24 14:24:10', '2018-10-22 14:19:00', '2018-10-22 14:19:08');
INSERT INTO `pound_log` VALUES ('38', '1', '总部东大门地方-1', '20181016154338', null, null, '9791.0000', '5995.0000', '3796.0000', '0.0000', '0.0000', '0.0000', '0.0000', 'http://localhost:8008/images/upload/2018/10/24/20181016154338/1540363328756.jpg', 'http://localhost:8008/images/upload/2018/10/24/20181016154338/1540363328758.jpg', '啊啊啊', null, '退货', '1', '2', null, '2018-10-16 15:44:51', '2018-10-24 13:51:48', '2018-10-18 09:35:10', '2018-10-23 10:27:16');
INSERT INTO `pound_log` VALUES ('39', '1', '总部东大门地方-1', '201810131647', '3', '伟星管业', '7998.0000', '7116.0000', '862.0000', '0.0000', '20.0000', '3500.0000', '-2618.0000', 'http://localhost:8008/images/upload/2018/10/24/201810131647/1540362440990.jpg', 'http://localhost:8008/images/upload/2018/10/24/201810131647/1540362440992.jpg', '大方的说法', '浙J8888', '', '0', '2', null, '2018-10-13 16:49:12', '2018-10-24 09:23:43', '2018-10-18 09:58:58', '2018-10-18 09:59:03');
INSERT INTO `pound_log` VALUES ('40', '1', '总部东大门地方-1', '20181016161428', '2', 'RCV', '12869.0000', '8162.0000', '4707.0000', '0.0000', '123.0000', '123.0000', '0.0000', 'http://localhost:8008/images/upload/2018/10/24/20181016161428/1540363663437.jpg', 'http://localhost:8008/images/upload/2018/10/24/20181016161428/1540363663438.jpg', '大发送到', null, '卖废品', '1', '2', null, '2018-10-16 16:14:31', '2018-10-24 13:48:59', '2018-10-22 14:42:11', '2018-10-19 09:17:25');
INSERT INTO `pound_log` VALUES ('41', '1', '总部东大门地方-1', '201810131101', '2', '华为铝业', '7879.0000', '5594.0000', '1365.0000', '800.0000', '120.0000', '800.0000', '1485.0000', 'http://localhost:8008/images/upload/2018/10/24/201810131101/1540362519650.jpg', 'http://localhost:8008/images/upload/2018/10/24/201810131101/1540362519651.jpg', '第三方', '浙J123C', '', '0', '2', null, '2018-10-13 11:02:55', '2018-10-24 11:01:55', '2018-10-18 16:14:17', '2018-10-18 16:14:21');
INSERT INTO `pound_log` VALUES ('42', '1', '总部东大门地方-1', '20181023150800', '2', '飞机速有限公司', '7540.0000', '6126.0000', '1414.0000', '0.0000', '0.0000', '1800.0000', '-386.0000', 'http://localhost:8008/images/upload/2018/10/23/20181023150800/1540283146772.jpg', 'http://localhost:8008/images/upload/2018/10/23/20181023150800/1540283146774.jpg', '制造十步', '浙C', '', '0', '2', null, '2018-10-23 15:17:46', '2018-10-23 15:23:30', '2018-10-23 15:17:53', '2018-10-23 15:17:57');
INSERT INTO `pound_log` VALUES ('43', '1', '总部东大门地方-1', '20181023161113', null, null, '11870.0000', '6371.0000', '5499.0000', '0.0000', '0.0000', '0.0000', null, 'http://localhost:8008/images/upload/2018/10/24/20181023161113/1540364328825.jpg', 'http://localhost:8008/images/upload/2018/10/24/20181023161113/1540364328826.jpg', '十部门', null, '', '1', '2', null, '2018-10-23 16:11:16', '2018-10-24 14:29:29', '2018-10-23 16:11:28', '2018-10-23 16:11:27');

-- ----------------------------
-- Table structure for sys_permission
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
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
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
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
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
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `emp_id` int(11) DEFAULT NULL COMMENT '工号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
