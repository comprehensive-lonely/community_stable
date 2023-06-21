/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 20/06/2023 07:25:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bms_billboard
-- ----------------------------
DROP TABLE IF EXISTS `bms_billboard`;
CREATE TABLE `bms_billboard`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '公告时间',
  `show` tinyint(1) NULL DEFAULT NULL COMMENT '1：展示中，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '全站公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_billboard
-- ----------------------------
INSERT INTO `bms_billboard` VALUES (2, 'R1.0 开始已实现护眼模式 ,妈妈再也不用担心我的眼睛了。', '2023-06-13 17:16:19', 0);
INSERT INTO `bms_billboard` VALUES (4, '系统已更新至最新版1.0.1', '2023-06-13 17:16:19', 1);

-- ----------------------------
-- Table structure for bms_comment
-- ----------------------------
DROP TABLE IF EXISTS `bms_comment`;
CREATE TABLE `bms_comment`  (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `user_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '作者ID',
  `topic_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'topic_id',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_comment
-- ----------------------------
INSERT INTO `bms_comment` VALUES ('1670072662300336129', '你好！', '1670072612870463489', '1333447953558765569', '2023-06-17 22:15:26', NULL);
INSERT INTO `bms_comment` VALUES ('1670072754797322242', '你好？\n', '1670072612870463489', '1333447953558765569', '2023-06-17 22:15:48', NULL);
INSERT INTO `bms_comment` VALUES ('1670072778142818305', '你好？\n', '1670072612870463489', '1333447953558765569', '2023-06-17 22:15:53', NULL);
INSERT INTO `bms_comment` VALUES ('1670075717771141121', 'hello', '1670072612870463489', '1332650453142827009', '2023-06-17 22:27:34', NULL);
INSERT INTO `bms_comment` VALUES ('1670084018705649666', 'hello 晚上好呀\n', '1670072612870463489', '1333447953558765569', '2023-06-17 23:00:33', NULL);
INSERT INTO `bms_comment` VALUES ('1670930574561202178', '好玩!', '1670072612870463489', '1670930448476229633', '2023-06-20 07:04:28', NULL);

-- ----------------------------
-- Table structure for bms_follow
-- ----------------------------
DROP TABLE IF EXISTS `bms_follow`;
CREATE TABLE `bms_follow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被关注人ID',
  `follower_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关注人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户关注' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_follow
-- ----------------------------

-- ----------------------------
-- Table structure for bms_post
-- ----------------------------
DROP TABLE IF EXISTS `bms_post`;
CREATE TABLE `bms_post`  (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT 'markdown内容',
  `user_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '作者ID',
  `comments` int(0) NOT NULL DEFAULT 0 COMMENT '评论统计',
  `collects` int(0) NOT NULL DEFAULT 0 COMMENT '收藏统计',
  `view` int(0) NOT NULL DEFAULT 0 COMMENT '浏览统计',
  `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶，1-是，0-否',
  `essence` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否加精，1-是，0-否',
  `section_id` int(0) NULL DEFAULT 0 COMMENT '专栏ID',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  UNIQUE INDEX `title`(`title`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '话题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_post
-- ----------------------------
INSERT INTO `bms_post` VALUES ('1333447953558765569', '1', '12\n2\n\n', '1670072612870463489', 0, 0, 107, b'0', b'0', 0, '2023-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1349362401438392322', '2023 健康，快乐', '2023的`FLAG`\n\n1. 技能进步\n2. 没有烦恼\n3. 发财 :smile:\n\n', '1670072612870463489', 0, 0, 23, b'0', b'0', 0, '2023-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1670930448476229633', 'DNF启动！', 'DNF创新世纪\n\n', '1670072612870463489', 0, 0, 3, b'0', b'0', 0, '2023-06-20 07:03:58', NULL);
INSERT INTO `bms_post` VALUES ('1334481725322297346', 'hello，spring-security', ':hibiscus: spring-security\n\n', '1670072612870463489', 0, 0, 47, b'0', b'0', 0, '2023-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1332650453142827009', '哈哈哈，helloworld', '这是第一篇哦\n\n> hi :handshake: 你好\n\n`hello world`\n\n:+1: 很好\n', '1670072612870463489', 0, 0, 30, b'0', b'0', 1, '2023-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1333432347031646209', '哈哈哈，换了个dark主题', '主题更换为Dark\n\n', '1670072612870463489', 0, 0, 6, b'0', b'0', 0, '2022-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1333668258587750401', '嘿嘿，测试一下啊', '大家好\n`Hello everyone!`\n\n\n\n', '1670072612870463489', 0, 0, 7, b'0', b'0', 0, '2022-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1333695976536748034', '地下城最新版本更新！！', 'DNF启动！', '1670072612870463489', 0, 0, 45, b'1', b'1', 0, '2022-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1332682473151635458', '我要发财', '2023 冲冲冲！！！\n\n', '1670072612870463489', 0, 0, 94, b'0', b'0', 2, '2022-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1349631541260595202', '权限部分 OK', '1. 创建 ok\n2. 修改 ok\n3. 删除 ok\n\n', '1670072612870463489', 0, 0, 18, b'0', b'0', 0, '2022-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1333676096156528641', '测试', '测试\n\n', '1670072612870463489', 0, 0, 38, b'0', b'0', 0, '2022-06-16 00:29:01', '2022-06-17 20:56:51');
INSERT INTO `bms_post` VALUES ('1332681213400817665', '聚合查询并统计', '* [x] SQL：\n\n```sql\nSELECT s.*,\nCOUNT(t.id) AS topics\nFROM section s\nLEFT JOIN topic t\nON s.id = t.section_id\nGROUP BY s.title\n```\n\n', '1670072612870463489', 0, 0, 55, b'0', b'0', 1, '2022-06-16 00:29:01', '2022-06-17 20:56:51');

-- ----------------------------
-- Table structure for bms_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `bms_post_tag`;
CREATE TABLE `bms_post_tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签ID',
  `topic_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '话题ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE,
  INDEX `topic_id`(`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '话题-标签 中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_post_tag
-- ----------------------------
INSERT INTO `bms_post_tag` VALUES (36, '1332650453377708034', '1332650453142827009');
INSERT INTO `bms_post_tag` VALUES (37, '1332681213568589825', '1332681213400817665');
INSERT INTO `bms_post_tag` VALUES (38, '1332681213631504385', '1332681213400817665');
INSERT INTO `bms_post_tag` VALUES (39, '1332682473218744321', '1332682473151635458');
INSERT INTO `bms_post_tag` VALUES (40, '1332913064463794178', '1332913064396685314');
INSERT INTO `bms_post_tag` VALUES (41, '1332913064530903041', '1332913064396685314');
INSERT INTO `bms_post_tag` VALUES (42, '1333432347107143681', '1333432347031646209');
INSERT INTO `bms_post_tag` VALUES (43, '1333432347107143682', '1333432347031646209');
INSERT INTO `bms_post_tag` VALUES (44, '1333447953697177602', '1333447953558765569');
INSERT INTO `bms_post_tag` VALUES (45, '1332913064463794178', '1333668258587750401');
INSERT INTO `bms_post_tag` VALUES (46, '1333676096320106498', '1333676096156528641');
INSERT INTO `bms_post_tag` VALUES (47, '1333695976742268930', '1333695976536748034');
INSERT INTO `bms_post_tag` VALUES (48, '1334481725519429634', '1334481725322297346');
INSERT INTO `bms_post_tag` VALUES (49, '1333447953697177602', '1335149981733449729');
INSERT INTO `bms_post_tag` VALUES (50, '1349362401597775874', '1349362401438392322');
INSERT INTO `bms_post_tag` VALUES (51, '1349631541306732545', '1349631541260595202');
INSERT INTO `bms_post_tag` VALUES (52, '1670930448530755585', '1670930448476229633');

-- ----------------------------
-- Table structure for bms_promotion
-- ----------------------------
DROP TABLE IF EXISTS `bms_promotion`;
CREATE TABLE `bms_promotion`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '广告标题',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '广告链接',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '广告推广表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_promotion
-- ----------------------------
INSERT INTO `bms_promotion` VALUES (1, '开发者头条', 'https://juejin.cn/', '开发者头条');
INSERT INTO `bms_promotion` VALUES (2, '并发编程网', 'https://juejin.cn/', '并发编程网');
INSERT INTO `bms_promotion` VALUES (3, '掘金', 'https://juejin.cn/', '掘金');

-- ----------------------------
-- Table structure for bms_tag
-- ----------------------------
DROP TABLE IF EXISTS `bms_tag`;
CREATE TABLE `bms_tag`  (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签ID',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '标签',
  `topic_count` int(0) NOT NULL DEFAULT 0 COMMENT '关联话题',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_tag
-- ----------------------------
INSERT INTO `bms_tag` VALUES ('1332650453377708034', 'java', 1);
INSERT INTO `bms_tag` VALUES ('1332681213568589825', 'css', 1);
INSERT INTO `bms_tag` VALUES ('1332681213631504385', 'mongodb', 1);
INSERT INTO `bms_tag` VALUES ('1332682473218744321', 'python', 1);
INSERT INTO `bms_tag` VALUES ('1332913064463794178', 'vue', 2);
INSERT INTO `bms_tag` VALUES ('1332913064530903041', 'react', 1);
INSERT INTO `bms_tag` VALUES ('1333432347107143681', 'node', 1);
INSERT INTO `bms_tag` VALUES ('1333432347107143682', 'mysql', 1);
INSERT INTO `bms_tag` VALUES ('1333447953697177602', 'flask', 2);
INSERT INTO `bms_tag` VALUES ('1333676096320106498', 'spring', 1);
INSERT INTO `bms_tag` VALUES ('1333695976742268930', 'django', 1);
INSERT INTO `bms_tag` VALUES ('1334481725519429634', 'security', 1);
INSERT INTO `bms_tag` VALUES ('1349362401597775874', 'tensorflow', 1);
INSERT INTO `bms_tag` VALUES ('1349631541306732545', 'pytorch', 1);
INSERT INTO `bms_tag` VALUES ('1670930448530755585', 'dnf', 1);

-- ----------------------------
-- Table structure for bms_tip
-- ----------------------------
DROP TABLE IF EXISTS `bms_tip`;
CREATE TABLE `bms_tip`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `author` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '作者',
  `type` tinyint(0) NOT NULL COMMENT '1：使用，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24864 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '每日赠言' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bms_tip
-- ----------------------------
INSERT INTO `bms_tip` VALUES (1, '多锉出快锯，多做长知识。', '佚名', 1);
INSERT INTO `bms_tip` VALUES (2, '未来总留着什么给对它抱有信心的人。', '佚名', 1);
INSERT INTO `bms_tip` VALUES (3, '一个人的智慧不够用，两个人的智慧用不完。', '谚语', 1);
INSERT INTO `bms_tip` VALUES (4, '十个指头按不住十个跳蚤', '傣族', 1);
INSERT INTO `bms_tip` VALUES (5, '言不信者，行不果。', '墨子', 1);
INSERT INTO `bms_tip` VALUES (6, '攀援而登，箕踞而遨，则几数州之土壤，皆在衽席之下。', '柳宗元', 1);
INSERT INTO `bms_tip` VALUES (7, '美德大都包含在良好的习惯之内。', '帕利克', 1);
INSERT INTO `bms_tip` VALUES (8, '人有不及，可以情恕。', '《晋书》', 1);
INSERT INTO `bms_tip` VALUES (9, '法不传六耳', '明·吴惟顺', 1);
INSERT INTO `bms_tip` VALUES (10, '真正的朋友应该说真话，不管那话多么尖锐。', '奥斯特洛夫斯基', 1);
INSERT INTO `bms_tip` VALUES (11, '时间是一切财富中最宝贵的财富。', '德奥弗拉斯多', 1);
INSERT INTO `bms_tip` VALUES (12, '看人下菜碟', '民谚', 1);
INSERT INTO `bms_tip` VALUES (13, '如果不是怕别人反感，女人决不会保持完整的严肃。', '拉罗什福科', 1);
INSERT INTO `bms_tip` VALUES (14, '爱是春暖花开时对你满满的笑意', '佚名', 1);
INSERT INTO `bms_tip` VALUES (15, '希望是坚韧的拐杖，忍耐是旅行袋，携带它们，人可以登上永恒之旅。', '罗素', 1);
INSERT INTO `bms_tip` VALUES (18, '天国般的幸福，存在于对真爱的希望。', '佚名', 1);
INSERT INTO `bms_tip` VALUES (19, '我们现在必须完全保持党的纪律，否则一切都会陷入污泥中。', '马克思', 1);
INSERT INTO `bms_tip` VALUES (20, '在科学上没有平坦的大道，只有不畏劳苦沿着陡峭山路攀登的人，才有希望达到光辉的顶点。', '马克思', 1);
INSERT INTO `bms_tip` VALUES (21, '懒惰的马嫌路远', '蒙古', 1);
INSERT INTO `bms_tip` VALUES (22, '别忘记热水是由冷水烧成的', '乔圣维亚', 1);

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户ID',
  `username` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `alias` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机',
  `score` int(0) NOT NULL DEFAULT 0 COMMENT '积分',
  `token` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT 'token',
  `bio` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活，1：是，0：否',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime(0) NOT NULL COMMENT '加入时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`username`) USING BTREE,
  INDEX `user_email`(`email`) USING BTREE,
  INDEX `user_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES ('1670072612870463489', '123456', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'assets/img/pic.jpeg', '123@qq.com', NULL, 1, '', '自由职业者', b'1', b'1', NULL, '2023-06-17 22:15:14', NULL);

SET FOREIGN_KEY_CHECKS = 1;
