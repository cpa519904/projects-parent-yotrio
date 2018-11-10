/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : pound

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-11-10 17:28:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `comp_code` varchar(20) NOT NULL DEFAULT '' COMMENT '编码',
  `comp_name` varchar(30) NOT NULL DEFAULT '' COMMENT '组织名称',
  `types` int(4) DEFAULT '0' COMMENT '类型',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_code` (`comp_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('4', '5761360', '临海市亚邦无纺织品有限公司', null, '1', '', '2018-11-10 13:01:58', '2018-11-10 12:59:58');
INSERT INTO `company` VALUES ('5', '5761047', '临海市铭源金属材料有限公司', null, '1', '', '2018-11-10 13:01:58', '2018-11-10 12:59:58');
INSERT INTO `company` VALUES ('6', '5761372', '临海市晨旭包装材料有限公司', null, '1', '修改一个看看', '2018-11-10 13:01:58', '2018-11-10 12:59:58');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `goods_code` varchar(20) NOT NULL DEFAULT '' COMMENT '货品编码',
  `goods_name` varchar(30) NOT NULL DEFAULT '' COMMENT '货品名称',
  `types` int(4) DEFAULT '0' COMMENT '类型',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_code` (`goods_code`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2', '1541768960488', '铝材', null, '1', '', '2018-11-10 13:07:48', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('3', '1541768984181', '铁材', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('4', '1541769007845', '气泡纸', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('5', '1541769017453', '塑料', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('6', '1541769023772', '藤条', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('7', '1541769029781', '化学品', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('8', '1541769035818', '塑粉', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('9', '1541769041811', '布', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('10', '1541769047968', '氩气', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('11', '1541769053406', '纸', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('12', '1541769058881', '堆高架', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('13', '1541769064553', '棉', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('14', '1541769071595', '穿布条', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('15', '1541769077854', '废铁', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('16', '1541769084077', '油', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('17', '1541769091470', '废铝', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('18', '1541769098359', '板带', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('19', '1541769104560', '废特斯林', null, '1', '', '2018-11-10 13:07:54', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('20', '1541769110760', '废坐垫', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('21', '1541769116867', '废坐垫套', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('22', '1541769123633', '废白海绵', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('23', '1541769131605', '废藤条', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('24', '1541769137266', '废纸板', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('25', '1541769142792', '珍珠棉', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('26', '1541769148254', '电焊机', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('27', '1541769161469', '堆高架（车间）', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('28', '1541769169874', '污泥', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('29', '1541769176128', '废铝+铁', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('30', '1541769182697', '薄膜', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('31', '1541769198378', '二氧化碳', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('32', '1541769203983', '废塑料', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('33', '1541769209382', '塑料+铁', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('34', '1541769215262', '玻璃碎', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('35', '1541769221191', 'PP棉', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('36', '1541769226791', '废塑粉', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('37', '1541769232654', '发泡块', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('38', '1541769238584', '折扣铸铝', null, '1', '', '2018-11-10 13:07:55', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('39', '1541769245638', '石灰', null, '1', '', '2018-11-10 13:07:56', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('40', '1541769251610', '氩气', null, '1', '', '2018-11-10 13:07:56', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('41', '1541769258143', '合金钢丸', null, '1', '', '2018-11-10 13:07:56', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('42', '1541769263821', '藤条', null, '1', '', '2018-11-10 13:07:56', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('43', '1541769269608', '网纱', null, '1', '', '2018-11-10 13:07:56', '2018-11-10 13:00:18');
INSERT INTO `goods` VALUES ('44', '1541769274727', '无纺布', null, '1', '', '2018-11-10 13:07:56', '2018-11-10 13:00:18');

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspection
-- ----------------------------
INSERT INTO `inspection` VALUES ('12', null, '20181103154941', 'FD201810311013', '浙Aaaaaa', '1', '浙江龙威灯饰有限公司', '3535.0000', '0.0000', '2098.0000', '0', '0', '2018-11-03 15:53:30', null);
INSERT INTO `inspection` VALUES ('13', null, '20181103154941', 'FD201810281005', '浙Aaaaaa', '1', '浙江龙威灯饰有限公司', '558.0000', '0.0000', '331.0000', '0', '0', '2018-11-03 16:02:30', '2018-11-03 16:11:14');
INSERT INTO `inspection` VALUES ('14', null, '20181103154941', 'FD201811011007', '浙Aaaaaa', '1', '浙江龙威灯饰有限公司', '1265.0000', '200.0000', '751.0000', '1', '0', '2018-11-03 16:06:16', '2018-11-03 16:11:36');
INSERT INTO `inspection` VALUES ('15', null, '20181103164442', 'FD201810291041', '浙Bbbbbb', '2', '浙江龙威灯饰有限公司', '380.0000', '0.0000', '380.0000', '0', '0', '2018-11-03 16:46:57', null);
INSERT INTO `inspection` VALUES ('16', null, '20181107113653', 'FD201811061005', '浙C12345', '1', '浙江龙威灯饰有限公司', '4430.0000', '0.0000', '4430.0000', '0', '0', '2018-11-07 11:38:27', '2018-11-07 11:42:05');
INSERT INTO `inspection` VALUES ('17', null, '20181107113653', 'FD201811061006', '浙C12345', '1', '浙江龙威灯饰有限公司', '725.0000', '0.0000', '725.0000', '0', '0', '2018-11-07 11:41:51', null);
INSERT INTO `inspection` VALUES ('18', null, '20181107135327', 'FD201810311001', 'JWYQ52', '1', 'A公司', '20000.0000', '0.0000', '5.0000', '0', '0', '2018-11-07 13:55:53', '2018-11-07 13:58:09');
INSERT INTO `inspection` VALUES ('20', null, '20181108115340', 'FD201811071058', '浙J2563', '1', '浙江龙威灯饰有限公司', '220.0000', '0.0000', '147.0000', '1', '0', '2018-11-08 14:46:48', '2018-11-08 14:49:05');
INSERT INTO `inspection` VALUES ('21', null, '20181108115340', 'FD201811081003', '浙J2563', '1', '浙江龙威灯饰有限公司', '2710.0000', '0.0000', '1810.0000', '0', '0', '2018-11-08 14:48:57', '2018-11-08 14:49:09');

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `org_code` varchar(20) NOT NULL DEFAULT '' COMMENT '组织编码',
  `org_name` varchar(30) NOT NULL DEFAULT '' COMMENT '组织名称',
  `types` int(4) DEFAULT '0' COMMENT '类型',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_code` (`org_code`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '001', '浙江永强集团股份有限公司', null, '1', null, '2018-11-10 13:07:43', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('2', '101', '台州永强工艺品有限公司', null, '1', null, '2018-11-10 13:07:43', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('3', '102', '临海市英仕达遮阳制品有限公司', null, '1', null, '2018-11-10 13:07:46', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('4', '103', '宁波花园旅游用品有限公司', null, '1', null, '2018-11-10 13:07:48', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('5', '104', '宁波杰倍德日用品有限公司', null, '1', null, '2018-11-10 13:07:49', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('6', '105', '永强户外用品(宁波)有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('7', '108', '宁波永宏户外休闲用品有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('8', '109', '宁波强邦户外休闲用品有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('9', '110', '浙江永强集团股份有限公司厦门分公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('10', '111', '浙江临海永强股权并购投资中心（有限合伙）', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('11', '112', '山东永旭户外休闲用品有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('12', '113', '浙江尚匠智能家居有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('13', '114', '宁波浙科永强创业投资合伙企业（有限合伙）', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('14', '115', '宁波亿泽日用品有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('15', '116', '深圳德宝西克曼智能家居有限公司', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('16', '117', '宁波博睿苏菲股权投资合伙企业（有限合伙）', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('17', '301', '制造一部', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('18', '302', '制造二部', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('19', '303', '制造三部', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('20', '304', '制造四部', null, '1', null, '2018-11-10 13:07:54', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('21', '305', '制造五部', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('22', '306', '塑料厂', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('23', '307', '制造十部', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('24', '308', '冲件部（失效）', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('25', '309', '制造五部（英仕达厂区）', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('26', '310', '浙江永信检测技术有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('27', '311', '临海永金管业有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('28', '312', '上海优享家居有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('29', '313', '临海市永强投资有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('30', '314', '浙江永强石英科技发展股份有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('31', '315', '上海寓悦科技有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('32', '316', '上海齐家永强户外用品有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('33', '317', '北京联拓天际电子商务有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('34', '318', '北京九州之旅科贸有限责任公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('35', '319', '北京联拓天下旅行社有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('36', '320', '合肥联拓天际电子商务有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('37', '321', '北京中航易购信息服务有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('38', '322', '上海先临投资有限公司', null, '1', null, '2018-11-10 13:07:55', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('39', '323', '上海永强鸿坤资产经营中心（有限合伙）', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('40', '324', '冲件部', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('41', '325', '技术部', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('42', '326', '合肥三分网络科技有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('43', '327', '邵家渡厂区', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('44', '328', '制造十二部', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('45', '329', '临海永强新材料有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('46', '330', '北京金旅通科技有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('47', '331', '柘溪厂区', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('48', '501', '浙江永强朗成房地产有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('49', '502', '台州朗成房地产开发有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('50', '503', '临海朗成商业经营管理有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('51', '504', '上海朗成房地产开发有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('52', '551', '浙江东都节能技术股份有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('53', '552', '嘉善东都节能技术有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('54', '553', '钟祥市东都节能有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('55', '554', '临海市科森热能设备有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');
INSERT INTO `organization` VALUES ('56', '555', '萍乡市东都节能有限公司', null, '1', null, '2018-11-10 13:07:56', '2018-11-10 12:58:46');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pound_info
-- ----------------------------

-- ----------------------------
-- Table structure for pound_log
-- ----------------------------
DROP TABLE IF EXISTS `pound_log`;
CREATE TABLE `pound_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pound_id` int(11) unsigned NOT NULL COMMENT '地磅id',
  `pound_name` varchar(50) DEFAULT '' COMMENT '地磅名',
  `pound_log_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '过磅单号',
  `goods_kind` int(4) DEFAULT NULL,
  `gross_weight` double(16,4) DEFAULT '0.0000' COMMENT '毛重',
  `tare_weight` double(16,4) DEFAULT '0.0000' COMMENT '皮重',
  `net_weight` double(16,4) DEFAULT '0.0000' COMMENT '净重',
  `sample_net_weight` double(16,4) DEFAULT '0.0000' COMMENT '样品净重',
  `return_weight_total` double(16,4) DEFAULT '0.0000' COMMENT '随车退货重量',
  `insp_weight_total` double(16,4) DEFAULT NULL COMMENT '报价单总重',
  `diff_weight` double(16,4) DEFAULT '0.0000' COMMENT '磅差',
  `gross_img_url` varchar(200) DEFAULT NULL COMMENT '总重图片',
  `tare_img_url` varchar(200) DEFAULT NULL COMMENT '毛重图片',
  `unit_name` varchar(30) DEFAULT '' COMMENT '收货单位',
  `org_code` varchar(30) DEFAULT NULL COMMENT '组织code',
  `goods_code` varchar(30) DEFAULT NULL COMMENT '物料code',
  `goods_name` varchar(30) DEFAULT NULL COMMENT '物料名称',
  `comp_name` varchar(50) DEFAULT NULL COMMENT '供应商名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pound_log
-- ----------------------------
INSERT INTO `pound_log` VALUES ('9', '1', 'ZongBu-1', '20181103154941', '1', '11246.0000', '8066.0000', '3180.0000', '1265.0000', '200.0000', '5358.0000', '-2178.0000', 'http://localhost:8000/images/upload/2018/11/6/20181103154941/1541497060824.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181103154941/1541497050985.jpg', '制造一部', null, null, null, null, '浙Aaaaaa', '明天上线', '0', '10', null, '2018-11-03 15:53:30', '2018-11-08 20:59:35', '2018-11-06 17:37:41', '2018-11-06 17:37:31');
INSERT INTO `pound_log` VALUES ('11', '1', 'ZongBu-1', '20181106173918', '19', '11526.0000', '10592.0000', '934.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106173918/1541497179625.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106173918/1541497176286.jpg', '特斯林部', null, null, null, null, '浙J1234', '', '1', '10', null, '2018-11-06 17:39:36', '2018-11-06 18:46:38', '2018-11-06 17:39:40', '2018-11-06 17:39:36');
INSERT INTO `pound_log` VALUES ('12', '1', 'ZongBu-1', '20181106191906', '15', '10575.0000', '8492.0000', '2083.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106191906/1541506380623.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106191906/1541504145634.jpg', '制造一部', null, null, null, null, '浙J25463', '出货', '1', '10', null, '2018-11-06 19:35:46', '2018-11-06 20:34:47', '2018-11-06 20:13:01', '2018-11-06 19:35:46');
INSERT INTO `pound_log` VALUES ('13', '1', 'ZongBu-1', '20181106200123', '30', '12210.0000', '8835.0000', '3375.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106200123/1541505805211.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106200123/1541505800477.jpg', '制造十部', null, null, null, null, '浙J9879', '出货', '1', '10', null, '2018-11-06 20:01:41', '2018-11-06 20:35:11', '2018-11-06 20:03:25', '2018-11-06 20:03:20');
INSERT INTO `pound_log` VALUES ('14', '1', 'ZongBu-1', '20181106200738', '35', '12754.0000', '5913.0000', '6841.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106200738/1541506211247.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106200738/1541506073977.jpg', '废品站', null, null, null, null, '浙12345', '出货', '1', '10', null, '2018-11-06 20:07:54', '2018-11-06 20:12:39', '2018-11-06 20:10:11', '2018-11-06 20:07:54');
INSERT INTO `pound_log` VALUES ('16', '1', 'ZongBu-1', '20181106202341', '35', '9094.0000', '6502.0000', '2592.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106202341/1541507035776.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106202341/1541507034401.jpg', '制造五部', null, null, null, null, '浙C11111', '', '1', '10', null, '2018-11-06 20:23:54', '2018-11-06 20:28:17', '2018-11-06 20:23:56', '2018-11-06 20:23:54');
INSERT INTO `pound_log` VALUES ('17', '1', 'ZongBu-1', '20181106202523', '39', '8029.0000', '5260.0000', '2769.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106202523/1541507142796.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106202523/1541507141020.jpg', '制造三部', null, null, null, null, '浙A33333', '', '1', '10', null, '2018-11-06 20:25:41', '2018-11-06 20:35:05', '2018-11-06 20:25:43', '2018-11-06 20:25:41');
INSERT INTO `pound_log` VALUES ('18', '1', 'ZongBu-1', '20181106203058', '25', '13451.0000', '10594.0000', '2857.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106203058/1541507501430.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106203058/1541507506787.jpg', '制造一部', null, null, null, null, '浙J12345', '', '1', '10', null, '2018-11-06 20:31:11', '2018-11-06 20:58:54', '2018-11-06 20:31:41', '2018-11-06 20:31:47');
INSERT INTO `pound_log` VALUES ('19', '1', 'ZongBu-1', '20181106204806', '37', '12881.0000', '7578.0000', '5303.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106204806/1541508522312.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106204806/1541508521008.jpg', '制造一百部', null, null, null, null, '浙J36985', '', '1', '10', null, '2018-11-06 20:48:41', '2018-11-06 20:59:02', '2018-11-06 20:48:42', '2018-11-06 20:48:41');
INSERT INTO `pound_log` VALUES ('20', '1', 'ZongBu-1', '20181106205018', '4', '7309.0000', '7201.0000', '108.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106205018/1541508636710.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106205018/1541508634902.jpg', '制造二百部', null, null, null, null, '浙C65321', '', '1', '10', null, '2018-11-06 20:50:28', '2018-11-06 20:58:58', '2018-11-06 20:50:37', '2018-11-06 20:50:35');
INSERT INTO `pound_log` VALUES ('21', '1', 'ZongBu-1', '20181106224248', '19', '11687.0000', '5153.0000', '6534.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/6/20181106224248/1541515410984.jpg', 'http://localhost:8000/images/upload/2018/11/6/20181106224248/1541515403647.jpg', '制造二十部', null, null, null, null, '浙E54623', '', '1', '10', null, '2018-11-06 22:43:06', '2018-11-06 22:44:34', '2018-11-06 22:43:31', '2018-11-06 22:43:24');
INSERT INTO `pound_log` VALUES ('22', '1', 'ZongBu-1', '20181107113653', '1', '11424.0000', '5343.0000', '6081.0000', '0.0000', '0.0000', '5155.0000', '926.0000', 'http://localhost:8000/images/upload/2018/11/7/20181107113653/1541562182989.jpg', 'http://localhost:8000/images/upload/2018/11/7/20181107113653/1541562138620.jpg', '制造一部', null, null, null, null, '浙C12345', '', '0', '10', null, '2018-11-07 11:38:27', '2018-11-07 14:05:16', '2018-11-07 11:43:03', '2018-11-07 11:42:19');
INSERT INTO `pound_log` VALUES ('23', '1', 'ZongBu-1', '20181107135327', '1', '65.0000', '60.0000', '5.0000', '0.0000', '0.0000', '20000.0000', '-19995.0000', 'http://localhost:8000/images/upload/2018/11/7/20181107135327/1541570226307.jpg', 'http://localhost:8000/images/upload/2018/11/7/20181107135327/1541570230659.jpg', '制造一部', null, null, null, null, 'JWYQ52', '', '0', '10', null, '2018-11-07 13:55:52', '2018-11-07 14:02:40', '2018-11-07 13:57:06', '2018-11-07 13:57:11');
INSERT INTO `pound_log` VALUES ('24', '1', 'ZongBu-1', '20181107142552', '17', '22315.0000', '22315.0000', '0.0000', null, null, null, null, 'http://localhost:8000/images/upload/2018/11/7/20181107142552/1541572162181.jpg', 'http://localhost:8000/images/upload/2018/11/7/20181107142552/1541572147005.jpg', '废品部门', null, null, null, null, 'J12345', '', '1', '10', null, '2018-11-07 14:29:07', '2018-11-07 14:41:12', '2018-11-07 14:29:22', '2018-11-07 14:29:07');
INSERT INTO `pound_log` VALUES ('25', '1', 'ZongBu-1', '20181108115340', '1', '9852.0000', '7895.0000', '1957.0000', '220.0000', '0.0000', '2930.0000', '-973.0000', 'http://localhost:8000/images/upload/2018/11/8/20181108115340/1541658955953.jpg', 'http://localhost:8000/images/upload/2018/11/8/20181108115340/1541658962465.jpg', '制造一部', null, null, null, null, '浙J2563', '', '0', '10', null, '2018-11-08 14:35:19', '2018-11-08 19:44:02', '2018-11-08 14:35:56', '2018-11-08 14:36:02');
INSERT INTO `pound_log` VALUES ('26', '1', 'ZongBu-1', '20181110135147807', null, '10029.0000', '5887.0000', '4142.0000', null, '0.0000', null, '0.0000', 'http://localhost:8000/images/upload/2018/11/10/20181110135147807/1541838221535.jpg', 'http://localhost:8000/images/upload/2018/11/10/20181110135147807/1541838225117.jpg', '制造一部', '301', '', '气泡纸', null, '浙J12346', '添加备注', '0', '10', null, '2018-11-10 16:02:26', '2018-11-10 16:51:12', '2018-11-10 16:23:41', '2018-11-10 16:23:45');
INSERT INTO `pound_log` VALUES ('27', '1', 'ZongBu-1', '20181110164451275', null, '13230.0000', '7942.0000', '5288.0000', null, '0.0000', null, '0.0000', 'http://localhost:8000/images/upload/2018/11/10/20181110164451275/1541839828315.jpg', 'http://localhost:8000/images/upload/2018/11/10/20181110164451275/1541839832295.jpg', '制造二部', '302', '1541769029781', '化学品', null, '浙A22222', '', '0', '10', null, '2018-11-10 16:45:22', '2018-11-10 16:52:21', '2018-11-10 16:50:28', '2018-11-10 16:50:32');

-- ----------------------------
-- Table structure for store_keeper
-- ----------------------------
DROP TABLE IF EXISTS `store_keeper`;
CREATE TABLE `store_keeper` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `goods_code` varchar(20) NOT NULL DEFAULT '' COMMENT '货品编码',
  `goods_name` varchar(30) DEFAULT '' COMMENT '货品名称',
  `org_code` varchar(20) NOT NULL DEFAULT '' COMMENT '组织编码',
  `org_name` varchar(30) DEFAULT '' COMMENT '组织名称',
  `keeper_name` varchar(30) DEFAULT '' COMMENT '仓管员',
  `keeper_empid` int(11) DEFAULT '0' COMMENT '仓管员工号',
  `keeper_mobile` varchar(30) DEFAULT '' COMMENT '仓管员',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `last_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store_keeper
-- ----------------------------

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `task_name` varchar(50) NOT NULL DEFAULT '' COMMENT '任务名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('11', '上传过磅记录失败|plNo=20181030143827', '成功！', '20181030143827', null, '0', '3', '1', '2018-10-31 13:02:16', '2018-10-31 13:38:01', null);
INSERT INTO `task` VALUES ('12', '上传过磅记录失败|plNo=20181030142435', '成功！', '20181030142435', null, '0', '3', '1', '2018-10-31 13:03:11', '2018-10-31 13:38:02', null);
INSERT INTO `task` VALUES ('13', '上传过磅记录失败|plNo=20181031111838', '成功！', '20181031111838', null, '0', '3', '1', '2018-10-31 13:03:18', '2018-10-31 13:38:02', null);
INSERT INTO `task` VALUES ('14', '上传过磅记录失败|plNo=20181103154941', '成功！', '20181103154941', null, '0', '3', '1', '2018-11-05 15:23:46', '2018-11-05 15:40:59', null);
INSERT INTO `task` VALUES ('15', '上传过磅记录失败|plNo=20181106191906', '成功！', '20181106191906', null, '0', '3', '1', '2018-11-06 20:18:09', '2018-11-06 20:22:43', null);
INSERT INTO `task` VALUES ('16', '上传过磅记录失败|plNo=20181106200123', '成功！', '20181106200123', null, '0', '3', '1', '2018-11-06 20:18:49', '2018-11-06 20:22:43', null);
INSERT INTO `task` VALUES ('17', '上传过磅记录失败|plNo=20181106202523', '成功！', '20181106202523', null, '0', '3', '1', '2018-11-06 20:26:50', '2018-11-06 20:28:19', null);
INSERT INTO `task` VALUES ('18', '上传过磅记录失败|plNo=20181106204806', '成功！', '20181106204806', null, '0', '3', '1', '2018-11-06 20:49:04', '2018-11-06 20:52:56', null);
INSERT INTO `task` VALUES ('19', '上传过磅记录失败|plNo=20181106205018', '成功！', '20181106205018', null, '0', '3', '1', '2018-11-06 20:50:46', '2018-11-06 20:52:56', null);
INSERT INTO `task` VALUES ('20', '上传过磅记录失败|plNo=20181107135327', '成功！', '20181107135327', null, '0', '3', '1', '2018-11-07 14:02:26', '2018-11-07 14:13:07', null);
