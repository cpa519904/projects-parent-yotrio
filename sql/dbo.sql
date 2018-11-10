/*
Navicat SQL Server Data Transfer

Source Server         : sqlserver-local
Source Server Version : 110000
Source Host           : 127.0.0.1:1433
Source Database       : pound_master
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 110000
File Encoding         : 65001

Date: 2018-11-09 21:59:00
*/


-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE [dbo].[company]
GO
CREATE TABLE [dbo].[company] (
[id] int NOT NULL IDENTITY(1,1) ,
[comp_code] nvarchar(20) NOT NULL ,
[comp_name] nvarchar(30) NOT NULL ,
[types] int NULL ,
[status] int NOT NULL ,
[description] nvarchar(255) NULL ,
[update_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[company]', RESEED, 6)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'comp_code')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'comp_code'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'comp_code'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'comp_name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'组织名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'comp_name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'组织名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'comp_name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'types')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'types'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'types'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'description'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'company', 
'COLUMN', N'update_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'update_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'company'
, @level2type = 'COLUMN', @level2name = N'update_time'
GO

-- ----------------------------
-- Records of company
-- ----------------------------
SET IDENTITY_INSERT [dbo].[company] ON
GO
INSERT INTO [dbo].[company] ([id], [comp_code], [comp_name], [types], [status], [description], [update_time]) VALUES (N'5', N'5761047', N'临海市铭源金属材料有限公司', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[company] ([id], [comp_code], [comp_name], [types], [status], [description], [update_time]) VALUES (N'4', N'5761360', N'临海市亚邦无纺织品有限公司', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[company] ([id], [comp_code], [comp_name], [types], [status], [description], [update_time]) VALUES (N'6', N'5761372', N'临海市晨旭包装材料有限公司', null, N'1', N'', null)
GO
GO
SET IDENTITY_INSERT [dbo].[company] OFF
GO

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE [dbo].[goods]
GO
CREATE TABLE [dbo].[goods] (
[id] int NOT NULL IDENTITY(1,1) ,
[goods_code] nvarchar(20) NOT NULL ,
[goods_name] nvarchar(30) NOT NULL ,
[types] int NULL ,
[status] int NOT NULL ,
[description] nvarchar(255) NULL ,
[update_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[goods]', RESEED, 44)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'goods_code')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'货品编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'goods_code'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'货品编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'goods_code'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'goods_name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'货品名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'goods_name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'货品名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'goods_name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'types')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'types'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'types'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'description'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'goods', 
'COLUMN', N'update_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'update_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'goods'
, @level2type = 'COLUMN', @level2name = N'update_time'
GO

-- ----------------------------
-- Records of goods
-- ----------------------------
SET IDENTITY_INSERT [dbo].[goods] ON
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'2', N'1541768960488', N'铝材', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'3', N'1541768984181', N'铁材', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'4', N'1541769007845', N'气泡纸', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'5', N'1541769017453', N'塑料', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'6', N'1541769023772', N'藤条', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'7', N'1541769029781', N'化学品', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'8', N'1541769035818', N'塑粉', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'9', N'1541769041811', N'布', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'10', N'1541769047968', N'氩气', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'11', N'1541769053406', N'纸', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'12', N'1541769058881', N'堆高架', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'13', N'1541769064553', N'棉', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'14', N'1541769071595', N'穿布条', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'15', N'1541769077854', N'废铁', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'16', N'1541769084077', N'油', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'17', N'1541769091470', N'废铝', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'18', N'1541769098359', N'板带', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'19', N'1541769104560', N'废特斯林', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'20', N'1541769110760', N'废坐垫', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'21', N'1541769116867', N'废坐垫套', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'22', N'1541769123633', N'废白海绵', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'23', N'1541769131605', N'废藤条', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'24', N'1541769137266', N'废纸板', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'25', N'1541769142792', N'珍珠棉', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'26', N'1541769148254', N'电焊机', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'27', N'1541769161469', N'堆高架（车间）', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'28', N'1541769169874', N'污泥', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'29', N'1541769176128', N'废铝+铁', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'30', N'1541769182697', N'薄膜', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'31', N'1541769198378', N'二氧化碳', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'32', N'1541769203983', N'废塑料', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'33', N'1541769209382', N'塑料+铁', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'34', N'1541769215262', N'玻璃碎', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'35', N'1541769221191', N'PP棉', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'36', N'1541769226791', N'废塑粉', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'37', N'1541769232654', N'发泡块', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'38', N'1541769238584', N'折扣铸铝', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'39', N'1541769245638', N'石灰', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'40', N'1541769251610', N'氩气', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'41', N'1541769258143', N'合金钢丸', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'42', N'1541769263821', N'藤条', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'43', N'1541769269608', N'网纱', null, N'1', N'', null)
GO
GO
INSERT INTO [dbo].[goods] ([id], [goods_code], [goods_name], [types], [status], [description], [update_time]) VALUES (N'44', N'1541769274727', N'无纺布', null, N'1', N'', null)
GO
GO
SET IDENTITY_INSERT [dbo].[goods] OFF
GO

-- ----------------------------
-- Table structure for inspection
-- ----------------------------
DROP TABLE [dbo].[inspection]
GO
CREATE TABLE [dbo].[inspection] (
[id] int NOT NULL IDENTITY(1,1) ,
[pl_id] int NULL ,
[pl_no] nvarchar(20) NOT NULL ,
[insp_no] nvarchar(20) NOT NULL ,
[plate_no] nvarchar(20) NOT NULL ,
[goods_kind] int NOT NULL ,
[comp_name] nvarchar(30) NOT NULL ,
[insp_weight] real NOT NULL ,
[return_weight] real NULL ,
[insp_net_weight] real NULL ,
[types] int NOT NULL ,
[status] int NOT NULL ,
[create_time] datetime2(0) NOT NULL ,
[update_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[inspection]', RESEED, 25)
GO

-- ----------------------------
-- Records of inspection
-- ----------------------------
SET IDENTITY_INSERT [dbo].[inspection] ON
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'11', N'6', N'20181103164442', N'FD201810291041', N'浙Bbbbbb', N'2', N'浙江龙威灯饰有限公司', N'380', N'0', N'380', N'0', N'0', N'2018-11-03 16:46:57', null)
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'18', N'22', N'20181103154941', N'FD201810311013', N'浙Aaaaaa', N'1', N'浙江龙威灯饰有限公司', N'3535', N'0', N'2098', N'0', N'0', N'2018-11-03 15:53:30', null)
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'19', N'22', N'20181103154941', N'FD201810281005', N'浙Aaaaaa', N'1', N'浙江龙威灯饰有限公司', N'558', N'0', N'331', N'0', N'0', N'2018-11-03 16:02:30', N'2018-11-03 16:11:14')
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'20', N'22', N'20181103154941', N'FD201811011007', N'浙Aaaaaa', N'1', N'浙江龙威灯饰有限公司', N'1265', N'200', N'751', N'1', N'0', N'2018-11-03 16:06:16', N'2018-11-03 16:11:36')
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'21', N'23', N'20181107113653', N'FD201811061005', N'浙C12345', N'1', N'浙江龙威灯饰有限公司', N'4430', N'0', N'4430', N'0', N'0', N'2018-11-07 11:38:27', N'2018-11-07 11:42:05')
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'22', N'23', N'20181107113653', N'FD201811061006', N'浙C12345', N'1', N'浙江龙威灯饰有限公司', N'725', N'0', N'725', N'0', N'0', N'2018-11-07 11:41:51', null)
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'23', N'24', N'20181107135327', N'FD201810311001', N'JWYQ52', N'1', N'A公司', N'20000', N'0', N'5', N'0', N'0', N'2018-11-07 13:55:53', N'2018-11-07 13:58:09')
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'24', N'26', N'20181108115340', N'FD201811071058', N'浙J2563', N'1', N'浙江龙威灯饰有限公司', N'220', N'0', N'147', N'1', N'0', N'2018-11-08 14:46:48', N'2018-11-08 14:49:05')
GO
GO
INSERT INTO [dbo].[inspection] ([id], [pl_id], [pl_no], [insp_no], [plate_no], [goods_kind], [comp_name], [insp_weight], [return_weight], [insp_net_weight], [types], [status], [create_time], [update_time]) VALUES (N'25', N'26', N'20181108115340', N'FD201811081003', N'浙J2563', N'1', N'浙江龙威灯饰有限公司', N'2710', N'0', N'1810', N'0', N'0', N'2018-11-08 14:48:57', N'2018-11-08 14:49:09')
GO
GO
SET IDENTITY_INSERT [dbo].[inspection] OFF
GO

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE [dbo].[organization]
GO
CREATE TABLE [dbo].[organization] (
[id] int NOT NULL IDENTITY(1,1) ,
[org_code] nvarchar(20) NOT NULL ,
[org_name] nvarchar(30) NOT NULL ,
[types] int NULL ,
[status] int NOT NULL ,
[description] nvarchar(255) NULL ,
[update_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[organization]', RESEED, 56)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'org_code')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'组织编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'org_code'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'组织编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'org_code'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'org_name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'组织名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'org_name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'组织名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'org_name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'types')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'types'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'types'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'description'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'organization', 
'COLUMN', N'update_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'update_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'organization'
, @level2type = 'COLUMN', @level2name = N'update_time'
GO

-- ----------------------------
-- Records of organization
-- ----------------------------
SET IDENTITY_INSERT [dbo].[organization] ON
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'1', N'001', N'浙江永强集团股份有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'2', N'101', N'台州永强工艺品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'3', N'102', N'临海市英仕达遮阳制品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'4', N'103', N'宁波花园旅游用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'5', N'104', N'宁波杰倍德日用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'6', N'105', N'永强户外用品(宁波)有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'7', N'108', N'宁波永宏户外休闲用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'8', N'109', N'宁波强邦户外休闲用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'9', N'110', N'浙江永强集团股份有限公司厦门分公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'10', N'111', N'浙江临海永强股权并购投资中心（有限合伙）', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'11', N'112', N'山东永旭户外休闲用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'12', N'113', N'浙江尚匠智能家居有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'13', N'114', N'宁波浙科永强创业投资合伙企业（有限合伙）', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'14', N'115', N'宁波亿泽日用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'15', N'116', N'深圳德宝西克曼智能家居有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'16', N'117', N'宁波博睿苏菲股权投资合伙企业（有限合伙）', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'17', N'301', N'制造一部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'18', N'302', N'制造二部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'19', N'303', N'制造三部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'20', N'304', N'制造四部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'21', N'305', N'制造五部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'22', N'306', N'塑料厂', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'23', N'307', N'制造十部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'24', N'308', N'冲件部（失效）', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'25', N'309', N'制造五部（英仕达厂区）', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'26', N'310', N'浙江永信检测技术有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'27', N'311', N'临海永金管业有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'28', N'312', N'上海优享家居有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'29', N'313', N'临海市永强投资有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'30', N'314', N'浙江永强石英科技发展股份有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'31', N'315', N'上海寓悦科技有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'32', N'316', N'上海齐家永强户外用品有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'33', N'317', N'北京联拓天际电子商务有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'34', N'318', N'北京九州之旅科贸有限责任公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'35', N'319', N'北京联拓天下旅行社有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'36', N'320', N'合肥联拓天际电子商务有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'37', N'321', N'北京中航易购信息服务有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'38', N'322', N'上海先临投资有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'39', N'323', N'上海永强鸿坤资产经营中心（有限合伙）', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'40', N'324', N'冲件部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'41', N'325', N'技术部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'42', N'326', N'合肥三分网络科技有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'43', N'327', N'邵家渡厂区', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'44', N'328', N'制造十二部', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'45', N'329', N'临海永强新材料有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'46', N'330', N'北京金旅通科技有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'47', N'331', N'柘溪厂区', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'48', N'501', N'浙江永强朗成房地产有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'49', N'502', N'台州朗成房地产开发有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'50', N'503', N'临海朗成商业经营管理有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'51', N'504', N'上海朗成房地产开发有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'52', N'551', N'浙江东都节能技术股份有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'53', N'552', N'嘉善东都节能技术有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'54', N'553', N'钟祥市东都节能有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'55', N'554', N'临海市科森热能设备有限公司', null, N'1', null, null)
GO
GO
INSERT INTO [dbo].[organization] ([id], [org_code], [org_name], [types], [status], [description], [update_time]) VALUES (N'56', N'555', N'萍乡市东都节能有限公司', null, N'1', null, null)
GO
GO
SET IDENTITY_INSERT [dbo].[organization] OFF
GO

-- ----------------------------
-- Table structure for pound_info
-- ----------------------------
DROP TABLE [dbo].[pound_info]
GO
CREATE TABLE [dbo].[pound_info] (
[id] int NOT NULL IDENTITY(1,1) ,
[pound_name] nvarchar(20) NOT NULL ,
[admin_emp_id] int NOT NULL ,
[admin_name] nvarchar(20) NOT NULL ,
[admin_mobile] nvarchar(50) NOT NULL ,
[unit_id] int NULL ,
[model] nvarchar(100) NULL ,
[remark] nvarchar(200) NULL ,
[status] int NOT NULL ,
[create_time] datetime2(0) NOT NULL 
)


GO

-- ----------------------------
-- Records of pound_info
-- ----------------------------
SET IDENTITY_INSERT [dbo].[pound_info] ON
GO
INSERT INTO [dbo].[pound_info] ([id], [pound_name], [admin_emp_id], [admin_name], [admin_mobile], [unit_id], [model], [remark], [status], [create_time]) VALUES (N'1', N'集团1号', N'326897', N'王以强', N'15726816286', null, null, null, N'1', N'2018-11-06 17:04:15')
GO
GO
SET IDENTITY_INSERT [dbo].[pound_info] OFF
GO

-- ----------------------------
-- Table structure for pound_log
-- ----------------------------
DROP TABLE [dbo].[pound_log]
GO
CREATE TABLE [dbo].[pound_log] (
[id] int NOT NULL IDENTITY(1,1) ,
[pound_id] int NOT NULL ,
[pound_name] nvarchar(50) NULL ,
[pound_log_no] nvarchar(20) NOT NULL ,
[goods_kind] int NULL ,
[comp_name] nvarchar(50) NULL ,
[gross_weight] real NULL ,
[tare_weight] real NULL ,
[net_weight] real NULL ,
[sample_net_weight] real NULL ,
[return_weight_total] real NULL ,
[insp_weight_total] real NULL ,
[diff_weight] real NULL ,
[gross_img_url] nvarchar(200) NULL ,
[tare_img_url] nvarchar(200) NULL ,
[unit_name] nvarchar(30) NULL ,
[plate_no] nvarchar(20) NULL ,
[remark] nvarchar(200) NULL ,
[types] int NOT NULL ,
[status] int NOT NULL ,
[return_goods] int NULL ,
[create_time] datetime2(0) NOT NULL ,
[update_time] datetime2(0) NULL ,
[first_time] datetime2(0) NULL ,
[second_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[pound_log]', RESEED, 26)
GO

-- ----------------------------
-- Records of pound_log
-- ----------------------------
SET IDENTITY_INSERT [dbo].[pound_log] ON
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'6', N'1', N'集团1号', N'20181103164442', N'2', N'浙江龙威灯饰有限公司', N'9383', N'5657', N'3726', N'0', N'0', N'380', N'3346', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181103164442/1541502671777.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181103164442/1541502671777.jpg', N'制造一部', N'浙Bbbbbb', N'', N'0', N'2', null, N'2018-11-03 16:46:57', N'2018-11-06 19:11:40', N'2018-11-06 19:11:37', N'2018-11-06 19:11:36')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'5', N'1', N'集团1号', N'20181106173918', N'19', null, N'11526', N'10592', N'934', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106173918/1541497198555.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106173918/1541497198570.jpg', N'特斯林部', N'浙J1234', N'', N'1', N'2', null, N'2018-11-06 17:39:36', N'2018-11-06 17:39:40', N'2018-11-06 17:39:40', N'2018-11-06 17:39:36')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'7', N'1', N'集团1号', N'20181106200738', N'35', null, N'12754', N'5913', N'6841', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106200738/1541506229754.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106200738/1541506229769.jpg', N'废品站', N'浙12345', N'出货', N'1', N'2', null, N'2018-11-06 20:07:54', N'2018-11-06 20:10:11', N'2018-11-06 20:10:11', N'2018-11-06 20:07:54')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'8', N'1', N'集团1号', N'20181106200123', N'30', null, N'12210', N'8835', N'3375', null, null, N'0', null, N'http://localhost:8000/images/upload/2018/11/6/20181106200123/1541505805211.jpg', N'http://localhost:8000/images/upload/2018/11/6/20181106200123/1541505800477.jpg', N'制造十部', N'浙J9879', N'出货', N'1', N'3', null, N'2018-11-06 20:01:41', N'2018-11-06 20:18:49', N'2018-11-06 20:03:25', N'2018-11-06 20:03:20')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'12', N'1', N'集团1号', N'20181106203058', N'25', null, N'13451', N'10594', N'2857', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106203058/1541508108936.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106203058/1541508108967.jpg', N'制造一部', N'浙J12345', N'', N'1', N'2', null, N'2018-11-06 20:31:11', N'2018-11-06 20:31:47', N'2018-11-06 20:31:41', N'2018-11-06 20:31:47')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'10', N'1', N'集团1号', N'20181106202341', N'35', null, N'9094', N'6502', N'2592', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106202341/1541507019973.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106202341/1541507019973.jpg', N'制造五部', N'浙C11111', N'', N'1', N'2', null, N'2018-11-06 20:23:54', N'2018-11-06 20:23:56', N'2018-11-06 20:23:56', N'2018-11-06 20:23:54')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'13', N'1', N'集团1号', N'20181106205018', N'4', null, N'7309', N'7201', N'108', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106205018/1541508746665.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106205018/1541508746681.jpg', N'制造二百部', N'浙C65321', N'', N'1', N'3', null, N'2018-11-06 20:50:28', N'2018-11-06 20:50:46', N'2018-11-06 20:50:37', N'2018-11-06 20:50:35')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'14', N'1', N'集团1号', N'20181106204806', N'37', null, N'12881', N'7578', N'5303', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106204806/1541508746774.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106204806/1541508746790.jpg', N'制造一百部', N'浙J36985', N'', N'1', N'3', null, N'2018-11-06 20:48:41', N'2018-11-06 20:49:05', N'2018-11-06 20:48:42', N'2018-11-06 20:48:41')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'15', N'1', N'集团1号', N'20181106224248', N'19', null, N'11687', N'5153', N'6534', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106224248/1541515411972.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/6/20181106224248/1541515411972.jpg', N'制造二十部', N'浙E54623', N'', N'1', N'2', null, N'2018-11-06 22:43:06', N'2018-11-06 22:43:31', N'2018-11-06 22:43:31', N'2018-11-06 22:43:24')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'22', N'1', N'集团1号', N'20181103154941', N'1', N'浙江龙威灯饰有限公司', N'11246', N'8066', N'3180', N'1265', N'200', N'5358', N'-2178', N'http://192.168.0.98:8080/statics/images/upload/2018/11/8/20181103154941/1541677456523.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/8/20181103154941/1541677456523.jpg', N'制造一部', N'浙Aaaaaa', N'明天上线', N'0', N'2', null, N'2018-11-03 15:53:30', N'2018-11-08 19:44:17', N'2018-11-06 17:37:41', N'2018-11-06 17:37:31')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'23', N'1', N'集团1号', N'20181107113653', N'1', N'浙江龙威灯饰有限公司', N'11424', N'5343', N'6081', N'0', N'0', N'5155', N'926', N'http://192.168.0.98:8080/statics/images/upload/2018/11/7/20181107113653/1541562178045.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/7/20181107113653/1541562178061.jpg', N'制造一部', N'浙C12345', N'', N'0', N'2', null, N'2018-11-07 11:38:27', N'2018-11-07 11:43:29', N'2018-11-07 11:43:03', N'2018-11-07 11:42:19')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'24', N'1', N'集团1号', N'20181107135327', N'1', N'A公司', N'65', N'60', N'5', N'0', N'0', N'20000', N'-19995', N'http://192.168.0.98:8080/statics/images/upload/2018/11/7/20181107135327/1541571154348.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/7/20181107135327/1541571154348.jpg', N'制造一部', N'JWYQ52', N'', N'0', N'10', null, N'2018-11-07 13:55:52', N'2018-11-07 14:02:40', N'2018-11-07 13:57:06', N'2018-11-07 13:57:11')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'25', N'1', N'集团1号', N'20181107142552', N'17', null, N'22315', N'22315', N'0', null, null, N'0', null, N'http://192.168.0.98:8080/statics/images/upload/2018/11/7/20181107142552/1541572155433.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/7/20181107142552/1541572155433.jpg', N'废品部门', N'J12345', N'', N'1', N'2', null, N'2018-11-07 14:29:07', N'2018-11-07 14:29:22', N'2018-11-07 14:29:22', N'2018-11-07 14:29:07')
GO
GO
INSERT INTO [dbo].[pound_log] ([id], [pound_id], [pound_name], [pound_log_no], [goods_kind], [comp_name], [gross_weight], [tare_weight], [net_weight], [sample_net_weight], [return_weight_total], [insp_weight_total], [diff_weight], [gross_img_url], [tare_img_url], [unit_name], [plate_no], [remark], [types], [status], [return_goods], [create_time], [update_time], [first_time], [second_time]) VALUES (N'26', N'1', N'集团1号', N'20181108115340', N'1', N'浙江龙威灯饰有限公司', N'9852', N'7895', N'1957', N'220', N'0', N'2930', N'-973', N'http://192.168.0.98:8080/statics/images/upload/2018/11/8/20181108115340/1541659787807.jpg', N'http://192.168.0.98:8080/statics/images/upload/2018/11/8/20181108115340/1541659787807.jpg', N'制造一部', N'浙J2563', N'', N'0', N'2', null, N'2018-11-08 14:35:19', N'2018-11-08 14:50:22', N'2018-11-08 14:35:56', N'2018-11-08 14:36:02')
GO
GO
SET IDENTITY_INSERT [dbo].[pound_log] OFF
GO

-- ----------------------------
-- Table structure for store_keeper
-- ----------------------------
DROP TABLE [dbo].[store_keeper]
GO
CREATE TABLE [dbo].[store_keeper] (
[id] int NOT NULL IDENTITY(1,1) ,
[goods_code] nvarchar(20) NOT NULL ,
[goods_name] nvarchar(30) NULL ,
[org_code] nvarchar(20) NOT NULL ,
[org_name] nvarchar(30) NULL ,
[keeper_name] nvarchar(30) NULL ,
[keeper_empid] int NULL ,
[keeper_mobile] nvarchar(30) NULL ,
[status] int NOT NULL ,
[description] nvarchar(255) NULL ,
[create_time] datetime2(0) NOT NULL ,
[update_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[store_keeper]', RESEED, 2)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'自增id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'goods_code')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'货品编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'goods_code'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'货品编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'goods_code'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'goods_name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'货品名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'goods_name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'货品名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'goods_name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'org_code')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'组织编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'org_code'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'组织编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'org_code'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'org_name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'组织名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'org_name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'组织名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'org_name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'keeper_name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'仓管员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'keeper_name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'仓管员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'keeper_name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'keeper_empid')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'仓管员工号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'keeper_empid'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'仓管员工号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'keeper_empid'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'keeper_mobile')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'仓管员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'keeper_mobile'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'仓管员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'keeper_mobile'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'description'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'create_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'create_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'create_time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'store_keeper', 
'COLUMN', N'update_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'update_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'store_keeper'
, @level2type = 'COLUMN', @level2name = N'update_time'
GO

-- ----------------------------
-- Records of store_keeper
-- ----------------------------
SET IDENTITY_INSERT [dbo].[store_keeper] ON
GO
INSERT INTO [dbo].[store_keeper] ([id], [goods_code], [goods_name], [org_code], [org_name], [keeper_name], [keeper_empid], [keeper_mobile], [status], [description], [create_time], [update_time]) VALUES (N'2', N'1541768960488', N'铝材', N'001', N'浙江永强集团股份有限公司', N'王以强', N'326897', N'15726816286', N'1', null, N'2018-11-09 21:21:11', N'2018-11-09 21:56:01')
GO
GO
SET IDENTITY_INSERT [dbo].[store_keeper] OFF
GO

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE [dbo].[sys_permission]
GO
CREATE TABLE [dbo].[sys_permission] (
[id] int NOT NULL IDENTITY(1,1) ,
[permission_name] nvarchar(50) NOT NULL ,
[permission] nvarchar(50) NULL ,
[permission_desc] nvarchar(255) NULL ,
[type] nvarchar(50) NOT NULL ,
[url] nvarchar(50) NULL ,
[pid] int NULL ,
[pids] nvarchar(100) NULL ,
[status] int NOT NULL ,
[create_time] datetime2(0) NULL ,
[update_time] datetime2(0) NULL 
)


GO

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
SET IDENTITY_INSERT [dbo].[sys_permission] ON
GO
SET IDENTITY_INSERT [dbo].[sys_permission] OFF
GO

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE [dbo].[sys_role]
GO
CREATE TABLE [dbo].[sys_role] (
[id] int NOT NULL IDENTITY(1,1) ,
[role_name] nvarchar(50) NOT NULL ,
[role_cname] nvarchar(50) NULL ,
[role_desc] nvarchar(255) NULL ,
[status] int NOT NULL 
)


GO

-- ----------------------------
-- Records of sys_role
-- ----------------------------
SET IDENTITY_INSERT [dbo].[sys_role] ON
GO
SET IDENTITY_INSERT [dbo].[sys_role] OFF
GO

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE [dbo].[sys_role_permission]
GO
CREATE TABLE [dbo].[sys_role_permission] (
[id] int NOT NULL IDENTITY(1,1) ,
[role_id] int NULL ,
[permission_id] int NULL 
)


GO

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
SET IDENTITY_INSERT [dbo].[sys_role_permission] ON
GO
SET IDENTITY_INSERT [dbo].[sys_role_permission] OFF
GO

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE [dbo].[sys_user]
GO
CREATE TABLE [dbo].[sys_user] (
[id] int NOT NULL IDENTITY(1,1) ,
[username] nvarchar(20) NOT NULL ,
[password] nvarchar(32) NOT NULL ,
[emp_id] int NULL ,
[dind_uid] nvarchar(20) NULL ,
[group_id] int NULL ,
[nickname] nvarchar(20) NULL ,
[rank] int NULL ,
[status] int NOT NULL ,
[created_by] nvarchar(20) NOT NULL ,
[phone] nvarchar(20) NULL ,
[create_time] datetime2(0) NOT NULL ,
[update_time] datetime2(0) NULL ,
[last_login_time] datetime2(0) NULL ,
[last_login_ip] nvarchar(200) NULL ,
[email] nvarchar(30) NULL ,
[salt] nvarchar(100) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[sys_user]', RESEED, 1003)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'username')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'username'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'username'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'password')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'密码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'password'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'密码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'password'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'emp_id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'工号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'emp_id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'工号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'emp_id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'dind_uid')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'钉钉用户id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'dind_uid'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'钉钉用户id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'dind_uid'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'group_id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分组id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'group_id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分组id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'group_id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'nickname')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'昵称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'nickname'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'昵称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'nickname'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'rank')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'等级'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'rank'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'等级'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'rank'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'created_by')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'created_by'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'created_by'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'phone')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发送验证码的手机号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'phone'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发送验证码的手机号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'phone'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'create_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'create_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'create_time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'update_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'update_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'update_time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'last_login_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上次登录时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'last_login_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上次登录时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'last_login_time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'last_login_ip')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上次登录ip'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'last_login_ip'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上次登录ip'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'last_login_ip'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'email')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'邮箱'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'email'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'邮箱'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'email'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_user', 
'COLUMN', N'salt')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'盐，加密用的'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'salt'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'盐，加密用的'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_user'
, @level2type = 'COLUMN', @level2name = N'salt'
GO

-- ----------------------------
-- Records of sys_user
-- ----------------------------
SET IDENTITY_INSERT [dbo].[sys_user] ON
GO
INSERT INTO [dbo].[sys_user] ([id], [username], [password], [emp_id], [dind_uid], [group_id], [nickname], [rank], [status], [created_by], [phone], [create_time], [update_time], [last_login_time], [last_login_ip], [email], [salt]) VALUES (N'3', N'panyuyan', N'3af7d92e451bd9aa149248d2b7661424', N'326601', null, null, N'系统用户', N'7', N'1', N'SuperAdmin', N'13957673594', N'2018-11-05 10:42:03', null, null, null, N'364514921@qq.com', N'8890c95b979e0028c22a8a86e7f30d8c')
GO
GO
INSERT INTO [dbo].[sys_user] ([id], [username], [password], [emp_id], [dind_uid], [group_id], [nickname], [rank], [status], [created_by], [phone], [create_time], [update_time], [last_login_time], [last_login_ip], [email], [salt]) VALUES (N'4', N'admin', N'5977b867b3e3e43cec2c2e6a6c8e04f1', N'326897', null, null, N'钻石管理员', N'2', N'1', N'SuperAdmin', N'15726816286', N'2018-11-05 10:51:02', null, null, null, N'364514921@qq.com', N'a94845f288df370864e8213115a912ac')
GO
GO
SET IDENTITY_INSERT [dbo].[sys_user] OFF
GO

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE [dbo].[sys_user_role]
GO
CREATE TABLE [dbo].[sys_user_role] (
[id] int NOT NULL IDENTITY(1,1) ,
[u_id] int NULL ,
[emp_id] int NULL ,
[role_id] int NULL 
)


GO

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
SET IDENTITY_INSERT [dbo].[sys_user_role] ON
GO
SET IDENTITY_INSERT [dbo].[sys_user_role] OFF
GO

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE [dbo].[task]
GO
CREATE TABLE [dbo].[task] (
[id] int NOT NULL IDENTITY(1,1) ,
[task_name] nvarchar(50) NOT NULL ,
[description] nvarchar(100) NULL ,
[other_id] nvarchar(20) NULL ,
[datas] nvarchar(1000) NULL ,
[weight] int NULL ,
[types] int NOT NULL ,
[status] int NOT NULL ,
[create_time] datetime2(0) NOT NULL ,
[update_time] datetime2(0) NULL ,
[execute_time] datetime2(0) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[task]', RESEED, 9)
GO

-- ----------------------------
-- Records of task
-- ----------------------------
SET IDENTITY_INSERT [dbo].[task] ON
GO
SET IDENTITY_INSERT [dbo].[task] OFF
GO
