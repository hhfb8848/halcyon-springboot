/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机版本8
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : 192.168.88.130:3306
 Source Schema         : halcyon

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 01/09/2024 01:25:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键值',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '是否系统内置（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认主题', 'admin.platform.theme', '{\"Layout\":\"vertical\",\"Theme\":\"light\",\"DarkMode\":false,\"SidebarStatus\":true,\"EpThemeColor\":\"#409EFF\",\"Grey\":false,\"Weak\":false,\"HideTabs\":false,\"HideFooter\":false,\"ShowLogo\":true,\"ShowModel\":\"smart\",\"MultiTagsCache\":false,\"Stretch\":false,\"Version\":\"5.4.0\",\"Title\":\"HalcyonAdmin\",\"FixedHeader\":true,\"HiddenSideBar\":false,\"KeepAlive\":true,\"MenuArrowIconNoTransition\":false,\"CachingAsyncRoutes\":false,\"TooltipEffect\":\"light\",\"ResponsiveStorageNameSpace\":\"responsive-\",\"MenuSearchHistory\":6}', 1, '2024-08-02 16:27:16', 1, '2024-08-19 14:28:45', 0, '后台系统主框架页-默认主题，可在右侧的设置中复制配置进行参数键值的修改', 1);
INSERT INTO `sys_config` VALUES (2, '用户默认头像', 'user.default.avatar', 'http://127.0.0.1:8888/admin/sysFile/check-file/3/get/332fc015fb923f49db9c3d7dae37fa00c3aa9c7fb43a84d7d50eb8765fcf12f5.png', 1, '2024-08-02 16:41:05', 1, '2024-08-04 16:46:15', 0, '用户创建或注册时的默认头像，可填入图片URL', 1);
INSERT INTO `sys_config` VALUES (3, '验证码类型', 'captcha.type', 'admin123', 1, '2024-08-02 20:34:17', 1, '2024-08-02 23:16:33', 0, '验证码类型', 1);
INSERT INTO `sys_config` VALUES (4, '页脚信息', 'admin.footer.message', 'HalcyonAdmin', 1, '2024-08-19 11:39:47', 1, '2024-08-19 11:47:03', 0, '页脚信息', 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字典编码',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0开启 1关闭）',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（0否 1是）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sd_dict_code`(`dict_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'TEST', '用户性别', 0, 0, 1, '2024-06-26 22:05:25', 1, '2024-08-04 15:48:50', '用户性别字典');
INSERT INTO `sys_dict` VALUES (2, 'FD', '通知公告类型', 0, 0, 1, '2024-06-26 22:28:53', 1, '2024-08-04 15:49:22', '通知公告类型');
INSERT INTO `sys_dict` VALUES (3, '54555', '放大刚分手的', 0, 1, 1, '2024-06-30 01:02:01', 1, '2024-08-04 15:50:34', NULL);
INSERT INTO `sys_dict` VALUES (4, '5454', '放大四分', 0, 1, 1, '2024-06-30 01:02:09', 1, '2024-08-04 15:50:37', NULL);
INSERT INTO `sys_dict` VALUES (5, '7657', '热武器若', 0, 1, 1, '2024-06-30 01:02:16', 1, '2024-08-02 14:08:45', NULL);
INSERT INTO `sys_dict` VALUES (6, '54543525', '特让他', 0, 1, 1, '2024-06-30 01:02:23', 1, '2024-07-01 21:56:09', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dict_id` bigint NOT NULL COMMENT '字典ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据项名称',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据项值',
  `sort_order` int NULL DEFAULT NULL COMMENT '排序',
  `color` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '颜色值',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0开启 1关闭）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (4, 1, '未知', '0', 1, '', 0, 1, '2024-06-29 20:53:58', 1, '2024-08-04 15:50:52', '');
INSERT INTO `sys_dict_data` VALUES (6, 1, '男', '1', 1, '', 0, 1, '2024-06-29 20:54:26', 1, '2024-08-04 15:51:21', '');
INSERT INTO `sys_dict_data` VALUES (8, 1, '女', '2', 1, '', 0, 1, '2024-08-04 15:51:15', NULL, NULL, '');
INSERT INTO `sys_dict_data` VALUES (9, 2, '通知', '0', 1, '', 0, 1, '2024-08-04 15:51:52', NULL, NULL, '');
INSERT INTO `sys_dict_data` VALUES (10, 2, '公告', '1', 1, '', 0, 1, '2024-08-04 15:52:03', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件编号',
  `config_id` bigint NULL DEFAULT NULL COMMENT '配置编号',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名',
  `path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件路径',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件 URL',
  `type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` int NOT NULL COMMENT '文件大小',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, 2, '758165380cd79123a23cc122ba345982b3b7808d.jpg', '5791af501aa468c53299397e0ff3dedb165873959f99c6cee9a8d22f9ec2c726.jpg', 'http://test.yudao.iocoder.cn/5791af501aa468c53299397e0ff3dedb165873959f99c6cee9a8d22f9ec2c726.jpg', 'image/jpeg', 33443, 1, '2024-07-31 22:08:37', NULL, '2024-07-31 22:08:36', 0);
INSERT INTO `sys_file` VALUES (3, 2, 'c4f50ad162d9f2d3d0fe8addefec8a136327cc13.jpg', '49959ace9455f464161ae873868c1b0f330af4ce9010db27dc6228ff06b2bdae.jpg', 'http://test.yudao.iocoder.cn/49959ace9455f464161ae873868c1b0f330af4ce9010db27dc6228ff06b2bdae.jpg', 'image/jpeg', 104624, 1, '2024-07-31 22:42:02', NULL, '2024-07-31 22:42:02', 0);
INSERT INTO `sys_file` VALUES (4, 2, 'f46e5efbb2fb431684b179b565a4462308f7d353.jpg', '0b406c91faf9988c78181f48fb1f08141723b0fd7ab4acead7861a9789d39178.jpg', 'http://test.yudao.iocoder.cn/0b406c91faf9988c78181f48fb1f08141723b0fd7ab4acead7861a9789d39178.jpg', 'image/jpeg', 48221, 1, '2024-07-31 22:46:02', NULL, '2024-07-31 22:46:01', 0);
INSERT INTO `sys_file` VALUES (5, 2, '0984dffaaf51f3de604d4e51d2eef01f3b29799f.jpg', '0552f45216d1574e77db79e382f555f3dd61277157ad112e37110692728b87e3.jpg', 'http://test.yudao.iocoder.cn/0552f45216d1574e77db79e382f555f3dd61277157ad112e37110692728b87e3.jpg', 'image/jpeg', 234414, 1, '2024-07-31 22:50:00', NULL, '2024-07-31 22:50:00', 0);
INSERT INTO `sys_file` VALUES (6, 2, '007dZPb2ly1g77m4uqwg5j304405owew.jpg', 'a5adf4aef88e166ec7ea3844cb9fc826d8b5f10ec697857a5b6b463b4c0e0ee8.jpg', 'http://test.yudao.iocoder.cn/a5adf4aef88e166ec7ea3844cb9fc826d8b5f10ec697857a5b6b463b4c0e0ee8.jpg', 'image/jpeg', 9902, 1, '2024-07-31 22:52:37', NULL, '2024-07-31 22:52:37', 0);
INSERT INTO `sys_file` VALUES (7, 2, '728831d88d1001e90f5cc336fe0e7bec54e79769.jpg', 'daa546495e5feb65de0c76ff3b3ab453db742e06a76a56a7645e10aa2ac745df.jpg', 'http://test.yudao.iocoder.cn/daa546495e5feb65de0c76ff3b3ab453db742e06a76a56a7645e10aa2ac745df.jpg', 'image/jpeg', 281804, 1, '2024-07-31 22:57:18', NULL, '2024-07-31 22:57:18', 0);
INSERT INTO `sys_file` VALUES (8, 2, '0db79e315c6034a8811d4fb48d1349540823769f.jpg', 'a879e529d552c13db4a4ea0d24dc959cd457d89757bce393ec44681b5942ee83.jpg', 'http://test.yudao.iocoder.cn/a879e529d552c13db4a4ea0d24dc959cd457d89757bce393ec44681b5942ee83.jpg', 'image/jpeg', 119340, 1, '2024-07-31 23:02:55', NULL, '2024-07-31 23:02:54', 0);
INSERT INTO `sys_file` VALUES (9, 2, '0e3433fa828ba61e94b58fe10434970a314e59a2.jpg', 'a162b8355ed138aabb52f7c9c5c97e2e4b68294b957be0f51c10517b6737d1b7.jpg', 'http://test.yudao.iocoder.cn/a162b8355ed138aabb52f7c9c5c97e2e4b68294b957be0f51c10517b6737d1b7.jpg', 'image/jpeg', 29589, 1, '2024-07-31 23:04:28', NULL, '2024-07-31 23:04:28', 0);
INSERT INTO `sys_file` VALUES (10, 2, '-9lddQ2p-8iq3K10T3cSk0-dy.jpeg', 'bb0270dd66825558b2616556bad156b9245bec934b32a28a28b13a7f9ce35fce.jpeg', 'http://test.yudao.iocoder.cn/bb0270dd66825558b2616556bad156b9245bec934b32a28a28b13a7f9ce35fce.jpeg', 'image/jpeg', 37084, 1, '2024-07-31 23:10:55', NULL, '2024-07-31 23:10:54', 0);
INSERT INTO `sys_file` VALUES (11, 2, 'c4f50ad162d9f2d3d0fe8addefec8a136327cc13.jpg', '49959ace9455f464161ae873868c1b0f330af4ce9010db27dc6228ff06b2bdae.jpg', 'http://test.yudao.iocoder.cn/49959ace9455f464161ae873868c1b0f330af4ce9010db27dc6228ff06b2bdae.jpg', 'image/jpeg', 104624, 1, '2024-07-31 23:22:03', NULL, '2024-07-31 23:22:03', 0);
INSERT INTO `sys_file` VALUES (12, 2, 'c16990fb43166d22cf469da1002309f79052d261.jpg', '45a8eb824220ca55c5d34cffb29469c9f887614b464e9ce4e351a551175c5a8f.jpg', 'http://test.yudao.iocoder.cn/45a8eb824220ca55c5d34cffb29469c9f887614b464e9ce4e351a551175c5a8f.jpg', 'image/jpeg', 25695, 1, '2024-07-31 23:28:53', NULL, '2024-07-31 23:28:52', 0);
INSERT INTO `sys_file` VALUES (13, 2, '4dfbcf43fbf2b211569bd1768c8065380dd78e91.jpg', '46b92b76af19be3172d3abd8a7cccdc50a84a10b4d80cae5d0e1c892c2750e93.jpg', 'http://test.yudao.iocoder.cn/46b92b76af19be3172d3abd8a7cccdc50a84a10b4d80cae5d0e1c892c2750e93.jpg', 'image/jpeg', 46924, 1, '2024-07-31 23:29:56', NULL, '2024-07-31 23:29:56', 0);
INSERT INTO `sys_file` VALUES (14, 2, 'c16990fb43166d22cf469da1002309f79052d261.jpg', '45a8eb824220ca55c5d34cffb29469c9f887614b464e9ce4e351a551175c5a8f.jpg', 'http://test.yudao.iocoder.cn/45a8eb824220ca55c5d34cffb29469c9f887614b464e9ce4e351a551175c5a8f.jpg', 'image/jpeg', 25695, 1, '2024-07-31 23:37:03', NULL, '2024-07-31 23:37:03', 0);
INSERT INTO `sys_file` VALUES (15, 2, '0e3433fa828ba61e94b58fe10434970a314e59a2.jpg', 'a162b8355ed138aabb52f7c9c5c97e2e4b68294b957be0f51c10517b6737d1b7.jpg', 'http://test.yudao.iocoder.cn/a162b8355ed138aabb52f7c9c5c97e2e4b68294b957be0f51c10517b6737d1b7.jpg', 'image/jpeg', 29589, 1, '2024-07-31 23:41:28', NULL, '2024-07-31 23:41:28', 0);
INSERT INTO `sys_file` VALUES (16, 2, '007dZPb2ly1g77m4uqwg5j304405owew.jpg', 'a5adf4aef88e166ec7ea3844cb9fc826d8b5f10ec697857a5b6b463b4c0e0ee8.jpg', 'http://test.yudao.iocoder.cn/a5adf4aef88e166ec7ea3844cb9fc826d8b5f10ec697857a5b6b463b4c0e0ee8.jpg', 'image/jpeg', 9902, 1, '2024-07-31 23:42:19', NULL, '2024-07-31 23:42:18', 0);
INSERT INTO `sys_file` VALUES (17, 2, '0e3433fa828ba61e94b58fe10434970a314e59a2.jpg', 'a162b8355ed138aabb52f7c9c5c97e2e4b68294b957be0f51c10517b6737d1b7.jpg', 'http://test.yudao.iocoder.cn/a162b8355ed138aabb52f7c9c5c97e2e4b68294b957be0f51c10517b6737d1b7.jpg', 'image/jpeg', 29589, 1, '2024-07-31 23:55:33', NULL, '2024-07-31 23:55:32', 0);
INSERT INTO `sys_file` VALUES (18, 2, 'vue.js', '12a8d2de69ffaeb66b9d19f75fb280b5a328740b31e7cdab6eb943dc41b88e63.js', 'http://test.yudao.iocoder.cn/12a8d2de69ffaeb66b9d19f75fb280b5a328740b31e7cdab6eb943dc41b88e63.js', 'application/javascript', 431155, 1, '2024-08-01 00:00:19', NULL, '2024-08-01 00:00:18', 0);
INSERT INTO `sys_file` VALUES (19, 2, 'Java程序设计精编教程（第3版） 第1章_Java入门.ppt', '4f658cdcd226d69bdaaaac0d7ff1b9054f11a57f2bea866f8f55435bd5d14523.ppt', 'http://test.yudao.iocoder.cn/4f658cdcd226d69bdaaaac0d7ff1b9054f11a57f2bea866f8f55435bd5d14523.ppt', 'application/vnd.ms-powerpoint', 3806208, 1, '2024-08-01 00:03:57', NULL, '2024-08-01 00:03:56', 0);
INSERT INTO `sys_file` VALUES (20, 2, 'Java程序设计精编教程（第3版）第11章_组件及事件处理.ppt', '5f282c83011ba4f9cf519cdc4e4aeaf74a3eebb81515d0edbf3ad54635976d94.ppt', 'http://test.yudao.iocoder.cn/5f282c83011ba4f9cf519cdc4e4aeaf74a3eebb81515d0edbf3ad54635976d94.ppt', 'application/vnd.ms-powerpoint', 2339840, 1, '2024-08-01 00:06:54', NULL, '2024-08-01 00:17:56', 1);

-- ----------------------------
-- Table structure for sys_file_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_config`;
CREATE TABLE `sys_file_config`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '配置名',
  `storage` tinyint NOT NULL COMMENT '存储器',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `master` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为主配置（0否1是）',
  `config` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '存储配置',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file_config
-- ----------------------------
INSERT INTO `sys_file_config` VALUES (1, '数据库存储', 1, '使用数据库存储', 0, '{\"@class\":\"com.halcyon.file.client.db.DBFileClientConfig\",\"domain\":\"http://127.0.0.1:8888\"}', 1, '2024-07-31 02:34:19', NULL, '2024-09-01 01:23:23', 0);
INSERT INTO `sys_file_config` VALUES (2, '七牛云存储', 20, '七牛云存储', 0, '{\"@class\":\"com.halcyon.file.client.s3.S3FileClientConfig\",\"endpoint\":\"test\",\"domain\":\"http://test.com\",\"bucket\":\"test\",\"accessKey\":\"test\",\"accessSecret\":\"test\"}', 1, '2024-07-31 15:52:10', NULL, '2024-09-01 01:22:55', 0);
INSERT INTO `sys_file_config` VALUES (3, '磁盘存储', 10, '本地磁盘存储', 1, '{\"@class\":\"com.halcyon.file.client.local.LocalFileClientConfig\",\"basePath\":\"/halcyon-file\",\"domain\":\"http://127.0.0.1:8888\"}', 1, '2024-07-31 16:39:33', NULL, '2024-09-01 01:23:41', 0);

-- ----------------------------
-- Table structure for sys_file_content
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_content`;
CREATE TABLE `sys_file_content`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `config_id` bigint NOT NULL COMMENT '配置编号',
  `path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件路径',
  `content` mediumblob NOT NULL COMMENT '文件内容',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件内容表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file_content
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_login_log_s`(`status` ASC) USING BTREE,
  INDEX `idx_sys_login_log_lt`(`login_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由名称',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `sort_order` int NULL DEFAULT 1 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由参数',
  `cache_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否缓存（0不缓存 1缓存）',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '菜单类型（0目录 1菜单 2iframe 3外链 4按钮）',
  `visible` tinyint(1) NULL DEFAULT 0 COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `platform_type` tinyint NULL DEFAULT 0 COMMENT '菜单所属平台（0后台 1前台）',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由重定向',
  `frame_loading` tinyint(1) NULL DEFAULT 0 COMMENT '内嵌的iframe页面是否开启首次加载动画（0否 1是）',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'iframe页面地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'System', '系统管理', 0, 60, '/system', '', NULL, 0, 0, 0, '', 'ep:brush-filled', 1, '2024-06-04 10:37:13', 1, '2024-08-31 23:54:03', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (3, 'SystemMenu', '菜单管理', 1, 1, '/system/menu', 'system/menu/index', NULL, 0, 1, 0, NULL, 'ep:menu', 1, '2024-06-05 10:07:13', 1, '2024-08-04 20:05:47', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (4, 'SysDict', '字典管理', 1, 2, '/system/dict', 'system/dict/index', NULL, 0, 1, 0, NULL, 'fa-solid:book', 1, '2024-06-15 22:15:56', 1, '2024-07-30 22:21:05', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (5, 'Frient', '友情链接', 0, 20, '/frient', '', NULL, 0, 0, 0, '', 'ep:link', 1, '2024-06-24 15:41:40', 1, '2024-08-31 23:53:58', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (6, 'PureAdmin', 'PureAdmin文档', 5, 1, '/PureAdmin', '', NULL, 0, 2, 0, NULL, 'ep:basketball', 1, '2024-06-24 15:42:50', 1, '2024-08-02 23:10:29', '', 0, '', 1, 'https://pure-admin.github.io/pure-admin-doc/');
INSERT INTO `sys_menu` VALUES (7, 'Satoken', 'SaToken文档', 5, 1, '/sa-token', '', NULL, 0, 2, 0, NULL, 'ep:alarm-clock', 1, '2024-06-24 17:54:04', 1, '2024-08-02 23:10:24', '', 0, '', 1, 'https://sa-token.cc/doc.html#/');
INSERT INTO `sys_menu` VALUES (8, 'SysRole', '角色管理', 1, 6, '/system/role', 'system/role/index', NULL, 0, 1, 0, '', 'fa-solid:user-injured', 1, '2024-07-01 20:20:07', 1, '2024-08-21 10:45:17', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (9, 'SysUser', '用户管理', 1, 5, '/system/user', 'system/user/index', NULL, 0, 1, 0, '', 'ep:avatar', 1, '2024-07-02 10:32:45', 1, '2024-08-21 10:45:03', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (12, '', '角色查询', 8, 1, '', '', NULL, 0, 4, 0, 'system:role:query', '', 1, '2024-07-02 20:03:25', 1, '2024-08-21 10:03:16', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (13, 'SysNotice', '通知公告', 1, 4, '/system/notice', 'system/notice/index', NULL, 0, 1, 0, '', 'ri:notification-3-fill', 1, '2024-07-02 20:12:30', 1, '2024-08-21 10:44:52', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (14, 'SysConfig', '系统配置', 1, 3, '/system/config', 'system/config/index', NULL, 0, 1, 0, '', 'ep:setting', 1, '2024-07-03 10:05:23', 1, '2024-08-21 10:44:38', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (15, 'SysFileList', '文件列表', 17, 2, '/system/file/list', 'system/file/list/index', NULL, 0, 1, 0, NULL, 'ri:file-chart-fill', 1, '2024-07-03 10:06:41', 1, '2024-07-30 22:28:26', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (16, 'SysFileConfig', '文件配置', 17, 1, '/system/file/config', 'system/file/config/index', NULL, 0, 1, 0, NULL, 'ri:folder-settings-fill', 1, '2024-07-03 10:06:58', 1, '2024-07-30 22:26:21', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (17, 'SysFile', '文件管理', 1, 10, '/system/file', '', NULL, 0, 0, 0, NULL, 'ri:file-copy-2-fill', 1, '2024-07-03 10:06:58', 1, '2024-08-02 02:13:06', '', 0, '/system/file/config', 0, '');
INSERT INTO `sys_menu` VALUES (18, '', '菜单查询', 3, 1, '', '', NULL, 0, 4, 0, 'system:menu:query', '', 1, '2024-08-18 20:37:54', 1, '2024-08-18 20:45:03', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (19, '', '菜单新增', 3, 1, '', '', NULL, 0, 4, 0, 'system:menu:create', '', 1, '2024-08-18 20:38:29', 1, '2024-08-18 21:05:39', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (20, '', '菜单修改', 3, 3, '', '', NULL, 0, 4, 0, 'system:menu:update', '', 1, '2024-08-18 20:47:04', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (21, '', '菜单删除', 3, 4, '', '', NULL, 0, 4, 0, 'system:menu:delete', '', 1, '2024-08-18 20:47:36', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (22, '', '角色新增', 8, 1, '', '', NULL, 0, 4, 0, 'system:role:create', '', 1, '2024-08-21 10:03:51', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (23, '', '角色修改', 8, 1, '', '', NULL, 0, 4, 0, 'system:role:update', '', 1, '2024-08-21 10:19:51', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (24, '', '角色删除', 8, 1, '', '', NULL, 0, 4, 0, 'system:role:delete', '', 1, '2024-08-21 10:20:26', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (25, '', '菜单授予', 8, 1, '', '', NULL, 0, 4, 0, 'system:permission:assignMenu', '', 1, '2024-08-21 10:21:26', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (26, '', '角色所分配菜单查询', 8, 1, '', '', NULL, 0, 4, 0, 'system:permission:getMenus', '', 1, '2024-08-21 10:25:41', 1, '2024-08-21 16:33:40', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (27, '', '用户查询', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:query', '', 1, '2024-08-21 10:48:57', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (28, '', '用户新增', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:create', '', 1, '2024-08-21 10:50:32', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (29, '', '用户修改', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:update', '', 1, '2024-08-21 10:52:58', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (30, '', '用户删除', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:delete', '', 1, '2024-08-21 10:53:59', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (31, '', '密码重置', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:resetPwd', '', 1, '2024-08-21 10:57:14', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (32, '', '为用户分配角色', 9, 1, '', '', NULL, 0, 4, 0, 'system:permission:assignRole', '', 1, '2024-08-21 10:58:40', 1, '2024-08-21 16:17:45', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (33, '', '字典查询', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:query', '', 1, '2024-08-21 11:19:16', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (34, '', '字典新增', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:create', '', 1, '2024-08-21 11:19:56', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (35, '', '字典修改', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:update', '', 1, '2024-08-21 11:20:24', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (36, '', '字典删除', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:delete', '', 1, '2024-08-21 11:21:37', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (37, '', '字典数据项查询', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:data:query', '', 1, '2024-08-21 11:26:12', 1, '2024-08-21 15:15:38', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (38, '', '字典数据项新增', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:data:create', '', 1, '2024-08-21 11:28:02', 1, '2024-08-21 15:15:54', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (39, '', '字典数据项修改', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:data:update', '', 1, '2024-08-21 11:28:37', 1, '2024-08-21 15:16:06', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (40, '', '字典数据项删除', 4, 1, '', '', NULL, 0, 4, 0, 'system:dict:data:delete', '', 1, '2024-08-21 11:31:14', 1, '2024-08-21 15:16:22', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (41, '', '配置修改', 14, 1, '', '', NULL, 0, 4, 0, 'system:config:update', '', 1, '2024-08-21 11:34:23', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (42, '', '配置新增', 14, 1, '', '', NULL, 0, 4, 0, 'system:config:create', '', 1, '2024-08-21 11:35:27', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (43, '', '通知公告查询', 13, 1, '', '', NULL, 0, 4, 0, 'system:notice:query', '', 1, '2024-08-21 11:37:07', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (44, '', '通知公告创建', 13, 1, '', '', NULL, 0, 4, 0, 'system:notice:create', '', 1, '2024-08-21 11:49:20', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (45, '', '通知公告修改', 13, 1, '', '', NULL, 0, 4, 0, 'system:notice:update', '', 1, '2024-08-21 11:49:52', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (46, '', '通知公告删除', 13, 1, '', '', NULL, 0, 4, 0, 'system:notice:delete', '', 1, '2024-08-21 11:50:22', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (47, '', '配置查询', 16, 1, '', '', NULL, 0, 4, 0, 'system:file:config:query', '', 1, '2024-08-21 14:35:40', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (48, '', '配置详情', 16, 2, '', '', NULL, 0, 4, 0, 'system:file:config:detail', '', 1, '2024-08-21 14:39:44', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (49, '', '配置创建', 16, 1, '', '', NULL, 0, 4, 0, 'system:file:config:create', '', 1, '2024-08-21 14:51:27', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (50, '', '配置修改', 16, 1, '', '', NULL, 0, 4, 0, 'system:file:config:update', '', 1, '2024-08-21 15:03:52', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (51, '', '配置删除', 16, 7, '', '', NULL, 0, 4, 0, 'system:file:config:delete', '', 1, '2024-08-21 15:05:41', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (52, '', '文件列表查询', 15, 1, '', '', NULL, 0, 4, 0, 'system:file:list:query', '', 1, '2024-08-21 15:08:03', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (53, '', '文件删除', 15, 2, '', '', NULL, 0, 4, 0, 'system:file:list:delete', '', 1, '2024-08-21 15:08:57', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (54, '', '配置查询', 14, 1, '', '', NULL, 0, 4, 0, 'system:config:query', '', 1, '2024-08-21 15:45:50', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (55, '', '配置删除', 14, 1, '', '', NULL, 0, 4, 0, 'system:config:delete', '', 1, '2024-08-21 15:47:27', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (56, '', '用户详情', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:detail', '', 1, '2024-08-21 16:03:04', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (57, '', '用户状态修改', 9, 1, '', '', NULL, 0, 4, 0, 'system:user:updateStatus', '', 1, '2024-08-21 16:08:45', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (58, '', '获取用户所分配角色', 9, 1, '', '', NULL, 0, 4, 0, 'system:permission:getRoles', '', 1, '2024-08-21 16:18:51', 1, '2024-08-21 16:19:06', '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (59, '', '刷新缓存', 14, 1, '', '', NULL, 0, 4, 0, 'system:config:refreshCache', '', 1, '2024-08-21 16:20:46', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (60, '', '查询所有简单角色', 8, 1, '', '', NULL, 0, 4, 0, 'system:role:listSimpleAll', '', 1, '2024-08-21 16:32:00', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (61, '', '设为主配置', 16, 1, '', '', NULL, 0, 4, 0, 'system:file:config:updateMaster', '', 1, '2024-08-21 16:45:37', NULL, NULL, '', 0, '', 0, '');
INSERT INTO `sys_menu` VALUES (62, '范德萨', '非常是', 5, 1, '发', '', NULL, 0, 0, 0, '', '', 2, '2024-08-21 17:29:11', NULL, NULL, '', 0, '', 0, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` longblob NULL COMMENT '公告内容',
  `type` tinyint(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '公告状态（0正常 1关闭）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_role`;
CREATE TABLE `sys_notice_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `notice_id` bigint NOT NULL COMMENT '通知ID，只有在通知公告类型为通知时才存',
  `role_id` bigint NOT NULL COMMENT '要通知的角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通知角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_user_read
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_user_read`;
CREATE TABLE `sys_notice_user_read`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `notice_id` bigint NOT NULL COMMENT '通知公告ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已读（0否1是）',
  `read_time` datetime NULL DEFAULT NULL COMMENT '已读时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除（0否1是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_user_notice`(`user_id` ASC, `notice_id` ASC) USING BTREE,
  INDEX `idx_user_notice`(`user_id` ASC, `notice_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户公告通知已读状态表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice_user_read
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` tinyint NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` tinyint(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2前台用户）',
  `oper_user_id` bigint NULL DEFAULT NULL COMMENT '操作用户ID',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type` ASC) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status` ASC) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '角色状态（0正常 1停用）',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0否 1是）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_sys_role_role_code`(`role_code` ASC) USING BTREE,
  INDEX `idx_sr_role_code`(`role_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'super-admin', 0, 0, 1, '2024-05-09 15:00:27', 1, '2024-07-01 22:41:18', '拥有一切权限');
INSERT INTO `sys_role` VALUES (2, '管理员', 'admin', 0, 0, 1, '2024-05-09 15:03:17', 1, '2024-08-21 10:08:44', '可以进入后台');
INSERT INTO `sys_role` VALUES (3, '普通用户', 'user', 0, 0, 1, '2024-05-09 15:07:49', 1, '2024-08-21 10:09:49', '普通用户');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` VALUES (8, 1, 8);
INSERT INTO `sys_role_menu` VALUES (9, 1, 9);
INSERT INTO `sys_role_menu` VALUES (43, 1, 5);
INSERT INTO `sys_role_menu` VALUES (44, 1, 6);
INSERT INTO `sys_role_menu` VALUES (45, 1, 7);
INSERT INTO `sys_role_menu` VALUES (46, 1, 15);
INSERT INTO `sys_role_menu` VALUES (47, 1, 16);
INSERT INTO `sys_role_menu` VALUES (48, 1, 17);
INSERT INTO `sys_role_menu` VALUES (63, 2, 16);
INSERT INTO `sys_role_menu` VALUES (64, 2, 1);
INSERT INTO `sys_role_menu` VALUES (65, 2, 17);
INSERT INTO `sys_role_menu` VALUES (66, 2, 3);
INSERT INTO `sys_role_menu` VALUES (67, 2, 4);
INSERT INTO `sys_role_menu` VALUES (68, 2, 8);
INSERT INTO `sys_role_menu` VALUES (69, 2, 9);
INSERT INTO `sys_role_menu` VALUES (70, 2, 15);
INSERT INTO `sys_role_menu` VALUES (71, 1, 14);
INSERT INTO `sys_role_menu` VALUES (72, 1, 13);
INSERT INTO `sys_role_menu` VALUES (73, 2, 13);
INSERT INTO `sys_role_menu` VALUES (74, 1, 18);
INSERT INTO `sys_role_menu` VALUES (75, 1, 19);
INSERT INTO `sys_role_menu` VALUES (76, 1, 20);
INSERT INTO `sys_role_menu` VALUES (77, 1, 21);
INSERT INTO `sys_role_menu` VALUES (78, 2, 18);
INSERT INTO `sys_role_menu` VALUES (82, 1, 12);
INSERT INTO `sys_role_menu` VALUES (83, 1, 22);
INSERT INTO `sys_role_menu` VALUES (84, 1, 23);
INSERT INTO `sys_role_menu` VALUES (85, 1, 24);
INSERT INTO `sys_role_menu` VALUES (86, 1, 25);
INSERT INTO `sys_role_menu` VALUES (87, 1, 26);
INSERT INTO `sys_role_menu` VALUES (88, 1, 27);
INSERT INTO `sys_role_menu` VALUES (89, 1, 28);
INSERT INTO `sys_role_menu` VALUES (90, 1, 29);
INSERT INTO `sys_role_menu` VALUES (91, 1, 30);
INSERT INTO `sys_role_menu` VALUES (92, 1, 31);
INSERT INTO `sys_role_menu` VALUES (93, 1, 32);
INSERT INTO `sys_role_menu` VALUES (94, 1, 33);
INSERT INTO `sys_role_menu` VALUES (95, 1, 34);
INSERT INTO `sys_role_menu` VALUES (96, 1, 35);
INSERT INTO `sys_role_menu` VALUES (97, 1, 36);
INSERT INTO `sys_role_menu` VALUES (98, 1, 37);
INSERT INTO `sys_role_menu` VALUES (99, 1, 38);
INSERT INTO `sys_role_menu` VALUES (100, 1, 39);
INSERT INTO `sys_role_menu` VALUES (101, 1, 40);
INSERT INTO `sys_role_menu` VALUES (102, 1, 41);
INSERT INTO `sys_role_menu` VALUES (103, 1, 42);
INSERT INTO `sys_role_menu` VALUES (104, 1, 43);
INSERT INTO `sys_role_menu` VALUES (105, 1, 44);
INSERT INTO `sys_role_menu` VALUES (106, 1, 45);
INSERT INTO `sys_role_menu` VALUES (107, 1, 46);
INSERT INTO `sys_role_menu` VALUES (108, 1, 47);
INSERT INTO `sys_role_menu` VALUES (109, 1, 48);
INSERT INTO `sys_role_menu` VALUES (110, 1, 49);
INSERT INTO `sys_role_menu` VALUES (111, 1, 50);
INSERT INTO `sys_role_menu` VALUES (112, 1, 51);
INSERT INTO `sys_role_menu` VALUES (113, 1, 52);
INSERT INTO `sys_role_menu` VALUES (114, 1, 53);
INSERT INTO `sys_role_menu` VALUES (115, 1, 54);
INSERT INTO `sys_role_menu` VALUES (116, 1, 55);
INSERT INTO `sys_role_menu` VALUES (117, 1, 56);
INSERT INTO `sys_role_menu` VALUES (118, 1, 57);
INSERT INTO `sys_role_menu` VALUES (119, 1, 58);
INSERT INTO `sys_role_menu` VALUES (120, 1, 59);
INSERT INTO `sys_role_menu` VALUES (121, 1, 60);
INSERT INTO `sys_role_menu` VALUES (122, 1, 61);
INSERT INTO `sys_role_menu` VALUES (123, 2, 33);
INSERT INTO `sys_role_menu` VALUES (124, 2, 37);
INSERT INTO `sys_role_menu` VALUES (125, 2, 12);
INSERT INTO `sys_role_menu` VALUES (126, 2, 14);
INSERT INTO `sys_role_menu` VALUES (127, 2, 47);
INSERT INTO `sys_role_menu` VALUES (128, 2, 52);
INSERT INTO `sys_role_menu` VALUES (129, 2, 54);
INSERT INTO `sys_role_menu` VALUES (130, 2, 27);
INSERT INTO `sys_role_menu` VALUES (131, 2, 60);
INSERT INTO `sys_role_menu` VALUES (132, 2, 43);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键（用户id）',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名（登录名）',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱号',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `intro` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别（0未知，1男，2女）',
  `birthday` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0正常，1禁用）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP地址',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP来源',
  `register_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '注册来源',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'super-admin', 'e10adc3949ba59abbe56e057f20f883e', '', '', '超级管理员', 'http://127.0.0.1:8888/admin/sysFile/check-file/3/get/1104b7a9d94b875101a1e6d4a2918cbeda6690179a21e4f0ca3387c47a715095.png', '我是超级管理员', 1, '2001-02-21 16:00:00', 0, '2024-05-09 17:22:35', 1, '2024-09-01 01:18:01', 1, '2024-09-01 01:17:10', '127.0.0.1', '内网IP', '内网IP', 0);
INSERT INTO `sys_user` VALUES (2, 'admin', 'a66abb5684c45962d887564f08346e8d', '', '', '管理员', 'http://127.0.0.1:8888/admin/sysFile/check-file/3/get/f34223f8486c2de2d752ea93887a87f707a8241720b9bc7852f3a6ba1b0f54e3.png', '普通管理员，无写权限', 0, '2024-08-02 16:00:00', 0, '2024-07-10 22:20:18', 1, '2024-09-01 01:20:09', 1, '2024-08-21 17:29:45', '127.0.0.1', '内网IP', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (43, 2, 2);
INSERT INTO `sys_user_role` VALUES (45, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
