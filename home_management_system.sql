/*
 Navicat MySQL Data Transfer

 Source Server         : connection
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : home_management_system

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 04/01/2024 10:22:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'guanliyuan', '123456');
INSERT INTO `admin_user` VALUES (2, 'zs', '654321');
INSERT INTO `admin_user` VALUES (3, 'zxh', '123456');
INSERT INTO `admin_user` VALUES (4, 'xh', '123');
INSERT INTO `admin_user` VALUES (5, 'lxh', '123');
INSERT INTO `admin_user` VALUES (6, 'lyh', '123');
INSERT INTO `admin_user` VALUES (7, 'hh', '123');

-- ----------------------------
-- Table structure for consumer_user
-- ----------------------------
DROP TABLE IF EXISTS `consumer_user`;
CREATE TABLE `consumer_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别，0 女 1 男',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of consumer_user
-- ----------------------------
INSERT INTO `consumer_user` VALUES (1, 'yonghu', '张三', '123456', 1, '福建省福州市···', '12345678910', '2456879152', 'http://s5ylvlikx.hd-bkt.clouddn.com/5f8a4c80-147b-4b3f-b18f-246f71536584.jpg', 1963.64);
INSERT INTO `consumer_user` VALUES (2, 'lisi', '李四', '123456', 1, '福建省漳州市···', '15478956234', '2489517536', 'http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg', 0.00);
INSERT INTO `consumer_user` VALUES (3, 'zoo', '张园园', 'zoo123456', 0, '至诚', '12345678910', 'zoo@qq.com', 'http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg', 0.00);
INSERT INTO `consumer_user` VALUES (6, 'zxh123', NULL, '123', 1, '福大至诚', '12345678910', 'zxh@qq.com', 'http://s5ylvlikx.hd-bkt.clouddn.com/d49c3867-69dd-4180-aa07-48a63afb2a17.jpg', 121.70);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '服务商品id',
  `kinds_id` int NULL DEFAULT NULL COMMENT '服务种类id(外键)',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '服务价格',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 1, '新居开荒保洁', 7.50, 'http://s5ylvlikx.hd-bkt.clouddn.com/aedc6f20-6b0a-4038-8245-5d108a148c99.jpg');
INSERT INTO `goods` VALUES (2, 3, '全屋大扫除', 218.00, 'http://s5ylvlikx.hd-bkt.clouddn.com/a6d3f937-8de6-48da-a4e5-bbd8f1be3331.jpg');
INSERT INTO `goods` VALUES (3, 1, '精致擦玻璃', 12.00, 'http://s5ylvlikx.hd-bkt.clouddn.com/b407fee1-17ce-4942-b3d5-e7bceec39f2b.jpg');
INSERT INTO `goods` VALUES (4, 1, '全屋深度保洁', 188.00, NULL);
INSERT INTO `goods` VALUES (5, 2, '热水器清洗', 138.00, NULL);
INSERT INTO `goods` VALUES (6, 2, '洗衣机清洗', 168.00, NULL);
INSERT INTO `goods` VALUES (7, 2, '冰箱清洗', 128.00, NULL);
INSERT INTO `goods` VALUES (8, 2, '油烟机清洗', 158.00, NULL);
INSERT INTO `goods` VALUES (9, 2, '空调清洗', 118.00, NULL);
INSERT INTO `goods` VALUES (10, 3, '专业整理收纳', 118.00, NULL);
INSERT INTO `goods` VALUES (11, 4, '地毯清洗', 22.00, NULL);
INSERT INTO `goods` VALUES (12, 4, '床垫清洗', 148.00, NULL);
INSERT INTO `goods` VALUES (13, 4, '沙发清洗', 70.00, NULL);
INSERT INTO `goods` VALUES (14, 4, '窗帘清洗', 12.00, NULL);
INSERT INTO `goods` VALUES (15, 5, '马桶疏通', 148.00, NULL);
INSERT INTO `goods` VALUES (16, 5, '洗菜鹏疏通', 148.00, NULL);
INSERT INTO `goods` VALUES (17, 5, '地漏疏通', 148.00, NULL);
INSERT INTO `goods` VALUES (18, 5, '浴缸疏通', 148.00, NULL);
INSERT INTO `goods` VALUES (21, 3, '摸鱼', 1.00, NULL);
INSERT INTO `goods` VALUES (22, 4, '摸大鱼', 1.00, NULL);
INSERT INTO `goods` VALUES (23, 2, '摸', 1.00, NULL);

-- ----------------------------
-- Table structure for kinds
-- ----------------------------
DROP TABLE IF EXISTS `kinds`;
CREATE TABLE `kinds`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '服务类型id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of kinds
-- ----------------------------
INSERT INTO `kinds` VALUES (1, '日常保洁');
INSERT INTO `kinds` VALUES (2, '家电清洗');
INSERT INTO `kinds` VALUES (3, '收纳整理');
INSERT INTO `kinds` VALUES (4, '家具养护');
INSERT INTO `kinds` VALUES (5, '管道疏通');
INSERT INTO `kinds` VALUES (6, '木地板打蜡');
INSERT INTO `kinds` VALUES (9, '整');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id(外键)',
  `goods_id` int NULL DEFAULT NULL COMMENT '服务商品id(外键)',
  `worker_id` int NULL DEFAULT NULL COMMENT '家政人员id(外键)',
  `status` tinyint NULL DEFAULT NULL COMMENT '订单状态（0 待支付 ，1 已支付，等待服务人员接单，2 已接单，等待服务人员上门，3 订单完成）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 1, 1, NULL, 1, '2023-12-19 15:17:32');
INSERT INTO `order` VALUES (2, 2, 2, NULL, 1, '2023-12-19 17:38:15');
INSERT INTO `order` VALUES (3, 6, 1, NULL, 1, '2023-12-25 13:39:52');
INSERT INTO `order` VALUES (4, 6, 2, NULL, 0, '2023-12-25 13:40:13');
INSERT INTO `order` VALUES (5, 6, 3, NULL, 0, '2023-12-25 13:40:24');
INSERT INTO `order` VALUES (6, 6, 4, NULL, 0, '2023-12-25 13:40:35');
INSERT INTO `order` VALUES (7, 6, 5, NULL, 1, '2023-12-25 17:35:51');
INSERT INTO `order` VALUES (8, 6, 1, NULL, 1, '2023-12-26 17:42:56');
INSERT INTO `order` VALUES (10, 6, 3, 6, 3, '2023-12-26 17:51:26');
INSERT INTO `order` VALUES (11, 6, 1, 6, 3, '2024-01-01 22:53:49');
INSERT INTO `order` VALUES (12, 6, 1, 6, 2, '2024-01-01 23:46:59');

-- ----------------------------
-- Table structure for worker
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别，0 女，1 男',
  `age` tinyint NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `brief_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `status` tinyint NULL DEFAULT NULL COMMENT '工作状态：0 空闲，1工作中，2未启用',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker` VALUES (1, 'gsd', '罗斯科', '123456', 1, 45, '福建省福州市···', '12456987523', '2145789652', '本人性格活泼开朗，善于与人沟通，有较强的交际技巧。工作认真负责，细心、有条理，能够及时准确地完成各项工作。', 1, 'http://s5ylvlikx.hd-bkt.clouddn.com/a9f97b3c-306e-4e81-b3cc-62e887f37cc6.jpg', 430.54);
INSERT INTO `worker` VALUES (2, 'ygas', '黄函', '123456', 0, 54, '福建省福州市···', '14528964758', '2451789652', '工作经验10年，比较擅长加点清洗家电', 0, 'http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg', 276.00);
INSERT INTO `worker` VALUES (3, 'dala', '林海统', '123456', 1, 48, '福建省福州市···', '15896424785', '2485961758', '工作经验15年，比较擅长加点疏通管道', 0, 'http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg', 24.00);
INSERT INTO `worker` VALUES (4, 'qkzoo', '晴空', '123', 0, NULL, NULL, NULL, NULL, NULL, 0, 'http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg', 0.00);
INSERT INTO `worker` VALUES (6, 'zxh', '郑小黑', '123456', 0, 1, NULL, '12345678910', NULL, NULL, 1, 'http://s5ylvlikx.hd-bkt.clouddn.com/db001ea7-9fd7-45c4-8616-13e26b459f99.jpg', 0.00);

SET FOREIGN_KEY_CHECKS = 1;
