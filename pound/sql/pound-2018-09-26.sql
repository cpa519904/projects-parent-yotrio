/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : pound

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-09-26 11:45:52
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pound_info
-- ----------------------------
INSERT INTO `pound_info` VALUES ('1', '总部东大门地方-1', '1000', 'Xk3190-A9+', null, '1', '2018-09-25 15:53:21');
INSERT INTO `pound_info` VALUES ('2', '总部东大门地方-2', '1000', 'Xk3190-A9+', null, '1', '2018-09-25 16:00:24');
INSERT INTO `pound_info` VALUES ('3', '总部西大门地方-3', '1003', 'Xk3190-A9+3', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('4', '总部东大门地方-4', '1004', 'Xk3190-A9+4', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('5', '总部南大门地方-5', '1005', 'Xk3190-A9+5', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('6', '总部北大门地方-6', '1006', 'Xk3190-A9+6', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('7', '总部南大门地方-7', '1007', 'Xk3190-A9+7', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('8', '总部南大门地方-8', '1008', 'Xk3190-A9+8', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('9', '总部南大门地方-9', '1009', 'Xk3190-A9+9', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('10', '总部西大门地方-10', '1010', 'Xk3190-A9+10', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('11', '总部西大门地方-11', '1011', 'Xk3190-A9+11', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('12', '总部南大门地方-12', '1012', 'Xk3190-A9+12', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('13', '总部东大门地方-13', '1013', 'Xk3190-A9+13', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('14', '总部南大门地方-14', '1014', 'Xk3190-A9+14', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('15', '总部东大门地方-15', '1015', 'Xk3190-A9+15', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('16', '总部南大门地方-16', '1016', 'Xk3190-A9+16', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('17', '总部东大门地方-17', '1017', 'Xk3190-A9+17', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('18', '总部北大门地方-18', '1018', 'Xk3190-A9+18', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('19', '总部东大门地方-19', '1019', 'Xk3190-A9+19', null, '1', '2018-09-25 16:07:02');
INSERT INTO `pound_info` VALUES ('20', '总部东大门地方-20', '1020', 'Xk3190-A9+20', null, '1', '2018-09-25 16:07:03');
INSERT INTO `pound_info` VALUES ('21', '总部西大门地方-21', '1021', 'Xk3190-A9+21', null, '1', '2018-09-25 16:07:03');
INSERT INTO `pound_info` VALUES ('22', '总部西大门地方-22', '1022', 'Xk3190-A9+22', null, '1', '2018-09-25 16:07:03');
INSERT INTO `pound_info` VALUES ('23', '总部西大门地方-23', '1023', 'Xk3190-A9+23', null, '1', '2018-09-25 16:07:03');
INSERT INTO `pound_info` VALUES ('24', '总部南大门地方-24', '1024', 'Xk3190-A9+24', null, '1', '2018-09-25 16:07:03');
INSERT INTO `pound_info` VALUES ('25', '总部东大门地方-25', '1025', 'Xk3190-A9+25', null, '1', '2018-09-25 16:07:03');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pound_log
-- ----------------------------
INSERT INTO `pound_log` VALUES ('1', '9', '总部南大门地方-9', '1100.0000', '1010.0000', '110.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物1', '公司1', '单位1部', '11963348', '10672', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('2', '12', '总部南大门地方-12', '1200.0000', '1020.0000', '220.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物2', '公司2', '单位2部', '24626153', '05126', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('3', '7', '总部南大门地方-7', '1300.0000', '1030.0000', '330.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物3', '公司3', '单位3部', '93868339', '99181', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('4', '13', '总部东大门地方-13', '1400.0000', '1040.0000', '440.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物4', '公司4', '单位4部', '20575027', '75853', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('5', '9', '总部南大门地方-9', '1500.0000', '1050.0000', '550.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物5', '公司5', '单位5部', '10294412', '47123', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('6', '14', '总部南大门地方-14', '1600.0000', '1060.0000', '660.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物6', '公司6', '单位6部', '97957527', '38143', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('7', '19', '总部东大门地方-19', '1700.0000', '1070.0000', '770.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物7', '公司7', '单位7部', '91763995', '25579', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('8', '12', '总部南大门地方-12', '1800.0000', '1080.0000', '880.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物8', '公司8', '单位8部', '56560070', '85297', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('9', '16', '总部南大门地方-16', '1900.0000', '1090.0000', '990.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物9', '公司9', '单位9部', '01293983', '13226', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('10', '7', '总部南大门地方-7', '2000.0000', '1100.0000', '1100.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物10', '公司10', '单位10部', '20816341', '31904', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('11', '8', '总部南大门地方-8', '2100.0000', '1110.0000', '1210.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物11', '公司11', '单位11部', '60561361', '67467', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('12', '6', '总部北大门地方-6', '2200.0000', '1120.0000', '1320.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物12', '公司12', '单位12部', '45554301', '37546', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('13', '22', '总部西大门地方-22', '2300.0000', '1130.0000', '1430.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物13', '公司13', '单位13部', '26323404', '49203', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('14', '18', '总部北大门地方-18', '2400.0000', '1140.0000', '1540.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物14', '公司14', '单位14部', '98160970', '42599', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('15', '3', '总部西大门地方-3', '2500.0000', '1150.0000', '1650.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物15', '公司15', '单位15部', '36347623', '48813', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('16', '9', '总部南大门地方-9', '2600.0000', '1160.0000', '1760.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物16', '公司16', '单位16部', '72618655', '25824', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('17', '4', '总部东大门地方-4', '2700.0000', '1170.0000', '1870.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物17', '公司17', '单位17部', '45073229', '55527', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('18', '6', '总部北大门地方-6', '2800.0000', '1180.0000', '1980.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物18', '公司18', '单位18部', '89643279', '45482', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('19', '4', '总部东大门地方-4', '2900.0000', '1190.0000', '2090.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物19', '公司19', '单位19部', '25356537', '21055', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('20', '15', '总部东大门地方-15', '3000.0000', '1200.0000', '2200.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物20', '公司20', '单位20部', '79182929', '77346', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('21', '13', '总部东大门地方-13', '3100.0000', '1210.0000', '2310.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物21', '公司21', '单位21部', '26932152', '67822', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('22', '6', '总部北大门地方-6', '3200.0000', '1220.0000', '2420.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物22', '公司22', '单位22部', '61954805', '13685', null, '1', '2018-09-26 09:52:39', null);
INSERT INTO `pound_log` VALUES ('23', '24', '总部南大门地方-24', '3300.0000', '1230.0000', '2530.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物23', '公司23', '单位23部', '02398510', '60665', null, '1', '2018-09-26 09:52:40', null);
INSERT INTO `pound_log` VALUES ('24', '11', '总部西大门地方-11', '3400.0000', '1240.0000', '2640.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物24', '公司24', '单位24部', '00899150', '21259', null, '1', '2018-09-26 09:52:40', null);
INSERT INTO `pound_log` VALUES ('25', '10', '总部西大门地方-10', '3500.0000', '1250.0000', '2750.0000', 'https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg', 'https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg', '货物25', '公司25', '单位25部', '89113806', '55412', null, '1', '2018-09-26 09:52:40', null);
