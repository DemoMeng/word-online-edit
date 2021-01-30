/*
 Navicat Premium Data Transfer

 Source Server         : rds
 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Host           : rdsv45k6542skme03476.mysql.rds.aliyuncs.com:3306
 Source Schema         : online_edit

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : 65001

 Date: 12/12/2020 11:29:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for online_edit_file
-- ----------------------------
DROP TABLE IF EXISTS `online_edit_file`;
CREATE TABLE `online_edit_file` (
  `id` varchar(50) NOT NULL COMMENT '主键id,字符串长度小于40（建议使用uuid避免读取到重复的文档）',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名(必须带文件后缀)',
  `version` int(15) DEFAULT NULL COMMENT '版本',
  `size` int(40) DEFAULT NULL COMMENT '文件大小,单位为B(文件真实大小，否则会出现异常)',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者,字符串长度小于40',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间,时间戳，单位为秒',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者,字符串长度小于40',
  `modify_time` bigint(20) DEFAULT NULL COMMENT '修改时间,时间戳，单位为秒',
  `download_url` text COMMENT '文件路径',
  `deleted` char(2) DEFAULT 'N' COMMENT '删除标识',
  `can_delete` char(2) DEFAULT 'Y' COMMENT '是否可删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='在线编辑文件表';

-- ----------------------------
-- Records of online_edit_file
-- ----------------------------
BEGIN;
INSERT INTO `online_edit_file` VALUES ('fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 17, 17655, '1', 1563079046, '1', 1607743359636, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212112239637.docx', 'N', 'Y');
COMMIT;

-- ----------------------------
-- Table structure for online_edit_file_version
-- ----------------------------
DROP TABLE IF EXISTS `online_edit_file_version`;
CREATE TABLE `online_edit_file_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `file_id` varchar(50) DEFAULT NULL COMMENT '文件id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `download_url` text COMMENT '下载地址',
  `size` int(40) DEFAULT NULL COMMENT '文件大小',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modify_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='在线编辑文件版本表';

-- ----------------------------
-- Records of online_edit_file_version
-- ----------------------------
BEGIN;
INSERT INTO `online_edit_file_version` VALUES (1, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 1, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/doc/1607567553634.docx', 17241, '1', 1563079046, '1', 1607679404849);
INSERT INTO `online_edit_file_version` VALUES (2, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 2, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201211173644851.docx', 17251, '1', 1563079046, '1', 1607679469714);
INSERT INTO `online_edit_file_version` VALUES (3, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 3, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201211173749716.docx', 17256, '1', 1563079046, '1', 1607686751329);
INSERT INTO `online_edit_file_version` VALUES (4, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 4, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201211193911329.docx', 17414, '1', 1563079046, '1', 1607735404390);
INSERT INTO `online_edit_file_version` VALUES (5, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 5, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091004390.docx', 17422, '1', 1563079046, '1', 1607735405985);
INSERT INTO `online_edit_file_version` VALUES (6, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 6, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091005985.docx', 17429, '1', 1563079046, '1', 1607735406165);
INSERT INTO `online_edit_file_version` VALUES (7, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 7, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091006166.docx', 17441, '1', 1563079046, '1', 1607735500435);
INSERT INTO `online_edit_file_version` VALUES (8, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 8, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091140436.docx', 17528, '1', 1563079046, '1', 1607735781709);
INSERT INTO `online_edit_file_version` VALUES (9, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 9, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091621709.docx', 17557, '1', 1563079046, '1', 1607735784331);
INSERT INTO `online_edit_file_version` VALUES (10, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 10, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091624332.docx', 17570, '1', 1563079046, '1', 1607735794238);
INSERT INTO `online_edit_file_version` VALUES (11, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 11, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091634238.docx', 17578, '1', 1563079046, '1', 1607735795426);
INSERT INTO `online_edit_file_version` VALUES (12, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 12, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091635426.docx', 17628, '1', 1563079046, '1', 1607735801067);
INSERT INTO `online_edit_file_version` VALUES (13, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 13, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091641067.docx', 17637, '1', 1563079046, '1', 1607735801395);
INSERT INTO `online_edit_file_version` VALUES (14, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 14, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091641395.docx', 17644, '1', 1563079046, '1', 1607735802237);
INSERT INTO `online_edit_file_version` VALUES (15, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 15, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091642237.docx', 17649, '1', 1563079046, '1', 1607735866426);
INSERT INTO `online_edit_file_version` VALUES (16, 'fb977165d0f04d2fae4743834fa25cf1', 'company (2).docx', 16, 'https://sign-online-group.oss-cn-hangzhou.aliyuncs.com/onlinecompany (2)20201212091746426.docx', 17655, '1', 1563079046, '1', 1607743359636);
COMMIT;

-- ----------------------------
-- Table structure for online_edit_file_watermark
-- ----------------------------
DROP TABLE IF EXISTS `online_edit_file_watermark`;
CREATE TABLE `online_edit_file_watermark` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `file_id` varchar(50) DEFAULT NULL COMMENT '文件id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0为无水印； 1为文字水印',
  `value` varchar(50) DEFAULT NULL COMMENT '文字水印的文字，当type为1时此字段必选',
  `fillstyle` varchar(100) DEFAULT NULL COMMENT '水印的透明度，非必选，有默认值',
  `font` varchar(100) DEFAULT NULL COMMENT '水印的字体，非必选，有默认值',
  `rotate` decimal(20,0) DEFAULT NULL COMMENT '水印的旋转度，非必选，有默认值',
  `horizontal` int(11) DEFAULT NULL COMMENT '水印水平间距，非必选，有默认值',
  `vertical` int(11) DEFAULT NULL COMMENT '水印水垂直距，非必选，有默认值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='在线编辑文件水印表';

-- ----------------------------
-- Records of online_edit_file_watermark
-- ----------------------------
BEGIN;
INSERT INTO `online_edit_file_watermark` VALUES (1, '1', 0, '', 'rgba( 192, 192, 192, 0.6 )', 'bold 20px Serif', 0, 50, 50);
COMMIT;

-- ----------------------------
-- Table structure for online_edit_user
-- ----------------------------
DROP TABLE IF EXISTS `online_edit_user`;
CREATE TABLE `online_edit_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `avatar_url` text COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='在线编辑用户表';

-- ----------------------------
-- Records of online_edit_user
-- ----------------------------
BEGIN;
INSERT INTO `online_edit_user` VALUES (1, '蒙大拿', 'http://api-new.oss-cn-hangzhou.aliyuncs.com/elephant.png');
INSERT INTO `online_edit_user` VALUES (2, '小拿', 'http://api-new.oss-cn-hangzhou.aliyuncs.com/chicken.png');
COMMIT;

-- ----------------------------
-- Table structure for online_edit_user_acl
-- ----------------------------
DROP TABLE IF EXISTS `online_edit_user_acl`;
CREATE TABLE `online_edit_user_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(40) DEFAULT NULL COMMENT '用户id',
  `file_id` varchar(50) DEFAULT NULL COMMENT '文件id',
  `permission` varchar(50) DEFAULT NULL COMMENT '用户操作权限，write：可编辑，read：预览',
  `re_name` int(11) DEFAULT NULL COMMENT '重命名权限，1为打开该权限，0为关闭该权限，默认为0',
  `history` int(11) DEFAULT NULL COMMENT '历史版本权限，1为打开该权限，0为关闭该权限,默认为1',
  `copy` int(11) DEFAULT '1' COMMENT '复制，1为打开该权限，0为关闭该权限,默认为1',
  `export` int(11) DEFAULT '1' COMMENT '导出pdf，1为打开该权限，0为关闭该权限,默认为1',
  `print` int(11) DEFAULT '1' COMMENT '打印，1为打开该权限，0为关闭该权限,默认为1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='在线编辑文件用户关联表';

-- ----------------------------
-- Records of online_edit_user_acl
-- ----------------------------
BEGIN;
INSERT INTO `online_edit_user_acl` VALUES (1, 1, 'fb977165d0f04d2fae4743834fa25cf1', 'write', 1, 1, 1, 1, 1);
INSERT INTO `online_edit_user_acl` VALUES (2, 2, 'fb977165d0f04d2fae4743834fa25cf1', 'read', 1, 1, 1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
