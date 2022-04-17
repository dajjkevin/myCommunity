/*
 Navicat Premium Data Transfer

 Source Server         : 2107user
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : localhost:3306
 Source Schema         : gcsmdb

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 13/04/2022 23:22:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `act_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `act_theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动主题',
  `act_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图',
  `act_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `act_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `act_num` int(11) NULL DEFAULT NULL COMMENT '可报名人数',
  `act_dtl` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '活动详情介绍(富文本)',
  `is_open` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否上下线 0可以 1禁止',
  `act_join_num` int(11) NULL DEFAULT 0 COMMENT '活动参与人数',
  PRIMARY KEY (`act_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6761832 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (913106, '3.15消费者日', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpicnew12.photophoto.cn%2F20171202%2Fchuangjianhexieshehui-29233528_1.jpg&refer=http%3A%2F%2Fpicnew12.photophoto.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1649639292&t=4f104a839465f0c86b871a84c1d0e927', '2022-03-13 09:00', '2022-03-13 10:00', 9, '<p></p><p></p><p></p><p></p><p></p><p></p><p></p><p>欢迎使用富文本编辑器123</p><p>提高广大群众的消费意识，欢迎大家报名参加1231231</p><p><img src=\"https://img022-1306164549.cos.ap-nanjing.myqcloud.com/oxl/24801647050756050.jpeg\" style=\"max-width:30%;\"><br></p><p></p><p><br></p><p></p><p><br></p><p></p><p><br></p>', '0', 1);
INSERT INTO `activity` VALUES (913107, '植树节', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg-qn-2.51miz.com%2Fpreview%2Fmuban%2F00%2F00%2F57%2F32%2FM-573270-83613919.jpg&refer=http%3A%2F%2Fimg-qn-2.51miz.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1649724179&t=fdd8a8c70d9d446c5777c3c0f7a79da1', '2022-03-14 09:00', '2022-03-14 16:00', 19, '<p></p><p><em>植树节</em>是按照法律规定宣传保护树木，并组织动员群众积极参加以植树造林为活动内容的节日。按时间长短可分为植树日、植树周和植树月，共称为国际<em>植树节</em>。<br></p><p><img src=\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg-qn-2.51miz.com%2Fpreview%2Fmuban%2F00%2F00%2F57%2F32%2FM-573270-83613919.jpg&amp;refer=http%3A%2F%2Fimg-qn-2.51miz.com&amp;app=2002&amp;size=f9999,10000&amp;q=a80&amp;n=0&amp;g=0n&amp;fmt=auto?sec=1649724179&amp;t=fdd8a8c70d9d446c5777c3c0f7a79da1\" style=\"max-width:30%;\"><br></p><p></p><p><br></p>', '0', 1);

-- ----------------------------
-- Table structure for activity_list
-- ----------------------------
DROP TABLE IF EXISTS `activity_list`;
CREATE TABLE `activity_list`  (
  `act_list_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名id',
  `act_id` int(11) NULL DEFAULT NULL COMMENT '活动id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`act_list_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123329 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报名活动信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of activity_list
-- ----------------------------
INSERT INTO `activity_list` VALUES (123327, 913106, 913005);
INSERT INTO `activity_list` VALUES (123328, 913107, 913005);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作者id',
  `admin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名称',
  `admin_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别 0-男 1-女',
  `admin_type` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '管理员状态 0超级 1普通',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `admin_avatar` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `admin_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `ethnic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `census` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `political_identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治身份',
  `admin_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0-正常 1-禁用',
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `admin_email_UNIQUE`(`admin_email`) USING BTREE COMMENT '管理员唯一邮箱'
) ENGINE = InnoDB AUTO_INCREMENT = 110019 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (110011, 'admin', '21232f297a57a5a743894a0e4a801fc3', '0', '0', '13646028517', '福建省|福州市|闽侯县|溪源宫路1号', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0165cb5d14565ca8012155290a6d86.png%402o.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648213883&t=ae062e4657838e19ca98f0d8999e7456', '308913397@qq.com', '回族', NULL, '2021-01-06', '0', '0');
INSERT INTO `admin` VALUES (110012, '小郭', 'e10adc3949ba59abbe56e057f20f883e', '0', '1', '136460', '福建省|福州市|闽侯县|溪源宫路11号', 'https://img022-1306164549.cos.ap-nanjing.myqcloud.com/oxl/76851645625313787.jpg', NULL, '汉族', '阿松大1', '2021-01-07', '0', '1');
INSERT INTO `admin` VALUES (110014, '小欧', 'e10adc3949ba59abbe56e057f20f883e', '0', '1', '11', '福建省|福州市|闽侯县|1111', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0165cb5d14565ca8012155290a6d86.png%402o.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648213883&t=ae062e4657838e19ca98f0d8999e7456', NULL, '汉族', '11', '2021-01-06', '0', '0');
INSERT INTO `admin` VALUES (110015, '小陈', 'e10adc3949ba59abbe56e057f20f883e', '0', '1', '122121', '福建省|福州市|闽侯县|12', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0165cb5d14565ca8012155290a6d86.png%402o.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648213883&t=ae062e4657838e19ca98f0d8999e7456', NULL, '汉族', '福建', '2021-01-06', '0', '0');
INSERT INTO `admin` VALUES (110018, '小吴', 'e10adc3949ba59abbe56e057f20f883e', '0', '1', '21', '福建省|福州市|闽侯县|12', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0165cb5d14565ca8012155290a6d86.png%402o.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648213883&t=ae062e4657838e19ca98f0d8999e7456', NULL, '汉族', '1', '2021-01-06', '0', '0');

-- ----------------------------
-- Table structure for aged
-- ----------------------------
DROP TABLE IF EXISTS `aged`;
CREATE TABLE `aged`  (
  `aged_id` int(11) NOT NULL COMMENT '老龄化人口id',
  `elder_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老人名字',
  `birthday` date NULL DEFAULT NULL COMMENT '生日(根据生日取年龄)',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `health_status` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '健康状态 0健康 1一般 2较差',
  `gender` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0-男 1-女',
  `regist_date` datetime NULL DEFAULT NULL COMMENT '记录日期',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`aged_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of aged
-- ----------------------------

-- ----------------------------
-- Table structure for appeal
-- ----------------------------
DROP TABLE IF EXISTS `appeal`;
CREATE TABLE `appeal`  (
  `appeal_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '诉求id\r\n',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述内容(富文本)',
  `user_id` int(11) NOT NULL COMMENT '诉求用户id',
  `deal_with` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '处理结果(回复)',
  `reply_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '默认0未处理 1处理(回复 就是进行处理)',
  `uemail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `uphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `app_time` datetime NULL DEFAULT NULL COMMENT '投诉时间',
  PRIMARY KEY (`appeal_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社区用户的投诉/建议/诉求' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of appeal
-- ----------------------------
INSERT INTO `appeal` VALUES (3, '多开展活动', '<p>留下您的投诉与建议内容</p><p>请多开展活动让我们的社区更加精彩更加充实<img src=\"https://img022-1306164549.cos.ap-nanjing.myqcloud.com/oxl/48651647872233644.jpg\" style=\"font-size:14px; max-width:50%;\"><br></p>', 913005, '<p>处理回复内容请填写</p><p>好的我们会多加强这方面的拓展,请您期待我们开展活动哦</p>', '1', '', '13646028517', '2022-03-21 22:39:48');

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin`  (
  `bulletin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `bulletin_theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告主题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公告内容(富文本)',
  `release_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `view` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否显示 0显示 1删除(逻辑)',
  PRIMARY KEY (`bulletin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78786 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社区公告' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin` VALUES (78784, '垃圾分类(1)', '<p></p><p><img src=\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic179.nipic.com%2Ffile%2F20180901%2F18488184_103442565085_2.jpg&amp;refer=http%3A%2F%2Fpic179.nipic.com&amp;app=2002&amp;size=f9999,10000&amp;q=a80&amp;n=0&amp;g=0n&amp;fmt=auto?sec=1650370860&amp;t=f4b9b62f30dbb3cf52cb812c0f8c8ceb\" style=\"max-width:50%;\"><br></p><p>配合垃圾分类，争做文明市民。播下一个行动，你将收获一份美丽。环境是大事，扔垃圾是小事，大事要从小事做起。回收废弃电池，创造美好家园。垃圾分类，举手之劳。变废为宝，美化家园。养成文明餐饮习惯，减少餐厨垃圾。你们的舍弃却是我的最爱，它们在这里找到了归宿。1<br></p><p></p><p><br></p>', '2022-03-20 20:28:15', '0');
INSERT INTO `bulletin` VALUES (78785, '垃圾分类(2）', '<p></p><p><img src=\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic179.nipic.com%2Ffile%2F20180901%2F18488184_103442565085_2.jpg&amp;refer=http%3A%2F%2Fpic179.nipic.com&amp;app=2002&amp;size=f9999,10000&amp;q=a80&amp;n=0&amp;g=0n&amp;fmt=auto?sec=1650370860&amp;t=f4b9b62f30dbb3cf52cb812c0f8c8ceb\" style=\"max-width:50%;\"><br></p><p>配合垃圾分类，争做文明市民。播下一个行动，你将收获一份美丽。环境是大事，扔垃圾是小事，大事要从小事做起。回收废弃电池，创造美好家园。垃圾分类，举手之劳。变废为宝，美化家园。养成文明餐饮习惯，减少餐厨垃圾。你们的舍弃却是我的最爱，它们在这里找到了归宿。1<br></p><p></p><p><br></p>', '2022-03-20 17:28:15', '0');

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`  (
  `e_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品论表id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评价内容',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `serve_id` int(11) NULL DEFAULT NULL COMMENT '服务id',
  `e_status` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '评论 0已品论(不能在评论) 1删除 只能删除本人',
  `e_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价时间',
  `fraction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '星级',
  PRIMARY KEY (`e_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES (6, '<p id=\"evalContent\">评论本次服务与建议,让我们以可做的更好！</p>', 913005, 636737, 6, '0', '2022-04-05 10:33:42', '4');
INSERT INTO `evaluate` VALUES (8, '<p id=\"evalContent\">评论本次服务与建议,让我们以可做的更好！</p>', 913005, 636735, 6, '0', '2022-04-05 15:05:06', '3');
INSERT INTO `evaluate` VALUES (9, '<p>很好</p>', 913005, 636736, 6, '0', '2022-04-05 15:05:27', '5');
INSERT INTO `evaluate` VALUES (10, '<p>真棒啊</p>', 913005, 636734, 6, '0', '2022-04-10 16:32:17', '4');
INSERT INTO `evaluate` VALUES (12, '<p id=\"evalContent\">非常棒！oo</p><p><br></p>', 913005, 636732, 6, '0', '2022-04-10 20:00:09', '5');
INSERT INTO `evaluate` VALUES (13, '<p id=\"evalContent\">评论本次服务与建议,让我们以可做的更好！1213</p>', 913005, 636733, 6, '0', '2022-04-13 22:41:58', '4');

-- ----------------------------
-- Table structure for household
-- ----------------------------
DROP TABLE IF EXISTS `household`;
CREATE TABLE `household`  (
  `house_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '户主id',
  `house_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '户主名字',
  `census` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍',
  `house_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `house_cnt` int(11) NOT NULL DEFAULT 1 COMMENT '家庭人口',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别 0-男 1-女',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `ethnic` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族（可能还需要一个民族专有的表）',
  `regist_date` datetime NULL DEFAULT NULL COMMENT '登记日期',
  `marital_status` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况 0未婚 1未婚',
  `housing_status` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '搬迁状态 0正常 1搬迁',
  PRIMARY KEY (`house_Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '户主' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of household
-- ----------------------------
INSERT INTO `household` VALUES (1, '王一', '福建省漳州市', '福建省|福州市|闽侯县|溪源宫路1号', 5, '2021-01-05', '0', '123110086', '回族', '2022-01-26 00:56:13', '0', '0');
INSERT INTO `household` VALUES (2, '王二', '11', '福建省|福州市|鼓楼区|111', 3, '2021-01-06', '0', '11', '汉族', '2022-01-26 14:57:35', '0', '0');
INSERT INTO `household` VALUES (3, '李二', '1212', '福建省|福州市|闽侯县|12', 1, '2021-01-06', '0', '1212', '汉族', '2022-01-26 14:57:41', '0', '0');
INSERT INTO `household` VALUES (4, '王大', '12123', '福建省|福州市|闽侯县|122', 1, '2021-01-06', '0', '1231123', '汉族', '2022-01-26 14:57:50', '0', '0');
INSERT INTO `household` VALUES (5, '大福', '1231', '福建省|福州市|闽侯县|1231', 1, '2021-01-06', '0', '12312', '汉族', '2022-01-26 14:57:56', '0', '0');
INSERT INTO `household` VALUES (6, '小杨', '江西省南昌市', '福建省|福州市|闽侯县|甘蔗镇', 2, '2021-01-06', '0', '12312', '汉族', '2022-01-26 14:58:03', '0', '0');
INSERT INTO `household` VALUES (7, '小郭', '福建厦门市', '福建省|福州市|闽侯县|上街镇', 1, '2021-01-06', '0', '123', '汉族', '2022-01-26 14:58:08', '0', '0');
INSERT INTO `household` VALUES (8, '小欧', '福建省龙岩市', '福建省|福州市|闽侯县|闽江学院', 1, '2021-01-06', '0', '123', '汉族', '2022-01-26 14:58:14', '0', '0');
INSERT INTO `household` VALUES (9, '小陈', '福建省厦门市', '福建省|厦门市|思明区|开合路口181号', 1, '2021-01-06', '0', '12124124', '汉族', '2022-02-13 16:27:43', '0', '0');
INSERT INTO `household` VALUES (10, '小吴', '福建省漳州市', '福建省|福州市|鼓楼区|闽侯', 1, '2021-01-06', '1', 'we', '朝鲜族', '2022-02-13 16:53:55', '0', '0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号id',
  `order_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `serve_id` int(11) NULL DEFAULT NULL COMMENT '服务id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `pay_status` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '支付状态 0未成功 1成功 ',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单价格',
  `order_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `order_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单地址',
  `order_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单电话',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付时间',
  `order_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单用户',
  `order_status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0正常  1完成订单 2删除',
  `eval_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否评价 0未评价 1已评价',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 636739 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务订单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (636715, '62491bb477b79ebb9c4d2652', 1, 913005, '1', '35.50', '水管维修', '福建省|福州市|闽侯县|蓝天幼儿园', '13646028517', '2022-04-03 11:59:48', '2022-04-03 12:00:19', '陈雅红', '1', '0');
INSERT INTO `orders` VALUES (636716, '6249386377b708dcd1f53c3c', 1, 913005, '1', '35.50', '水管维修', '福建省|福州市|闽侯县|蓝天幼儿园', '13646028517', '2022-04-03 14:02:11', '2022-04-03 14:03:12', '陈雅红', '1', '0');
INSERT INTO `orders` VALUES (636732, '624a5c9577b728c6337de812', 6, 913005, '1', '30.50', '电器维修', '福建省|福州市|闽侯县|闽江学院', '13646028517', '2022-04-04 10:48:53', '2022-04-04 10:49:12', 'o', '1', '1');
INSERT INTO `orders` VALUES (636733, '624a84e177b71a4bc81ad382', 6, 913005, '1', '30.50', '电器维修', '福建省|福州市|闽侯县|蓝天幼儿园', '13646028517', '2022-04-04 13:40:49', '2022-04-04 13:41:08', 'o', '1', '1');
INSERT INTO `orders` VALUES (636734, '624ab75a77b7f9b2ab9b83be', 6, 913005, '1', '30.50', '电器维修', '福建省|福州市|闽侯县|闽江学院', '13646028517', '2022-04-04 17:16:10', '2022-04-04 17:17:07', 'o', '1', '1');
INSERT INTO `orders` VALUES (636735, '624ac486923768b0e7df0675', 6, 913005, '1', '30.50', '电器维修', '福建省|福州市|闽侯县|蓝天幼儿园', '13646028517', '2022-04-04 18:12:22', '2022-04-04 17:12:07', 'o', '1', '1');
INSERT INTO `orders` VALUES (636736, '624ac53c923752b8602b828f', 6, 913005, '1', '30.50', '电器维修', '福建省|福州市|闽侯县|海天', '13646028517', '2022-04-04 18:15:24', '2022-04-04 18:15:35', 'o', '1', '1');
INSERT INTO `orders` VALUES (636737, '624ac5ca9237796448949f01', 6, 913005, '1', '30.50', '电器维修', '福建省|福州市|闽侯县|蓝天幼儿园', '13646028517', '2022-04-04 18:17:46', '2022-04-04 18:17:58', 'o', '1', '1');

-- ----------------------------
-- Table structure for resident
-- ----------------------------
DROP TABLE IF EXISTS `resident`;
CREATE TABLE `resident`  (
  `person_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '居民id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '户号(户主编号)',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `census` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `ethnic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `house_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `relation` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-1' COMMENT '与户主关系 0儿女 1父母  2配偶 -1表示无',
  `marital_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况 0未婚 1未婚',
  `culture` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文化水平 0小学 1初中 2高中 3大学及以上',
  `gender` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别 0-男 1-女',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`person_id`) USING BTREE,
  INDEX `FK_HouseId`(`house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101011 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '居民' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resident
-- ----------------------------
INSERT INTO `resident` VALUES (101000, '王大大', NULL, '232141', '123', '2021-01-06', '汉族', '福建省|福州市|闽侯县|福州1', '-1', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101001, '居民测试', 6, '232141', '123', '2021-01-06', '汉族', '福建省|福州市|闽侯县|福州1', '0', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101002, '王有财', 1, '11', '11', '2021-01-06', '汉族', '福建省|福州市|闽侯县|11', '0', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101003, '理想', NULL, '234', '234', '2021-01-06', '回族', '福建省|福州市|闽侯县|234', '-1', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101004, 'jely', 1, '234', '234', '2021-01-06', '回族', '福建省|福州市|闽侯县|234', '0', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101005, 'oxl', 1, 'asda', 'ads', '2021-01-06', '回族', '福建省|福州市|闽侯县|sada', '-1', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101006, 'Angele', 2, 'qwe', 'qwe', '2021-01-06', '维吾尔族', '福建省|福州市|闽侯县|qwe', '0', '0', '0', '0', NULL);
INSERT INTO `resident` VALUES (101007, 'bay', 1, '11', '11', '2021-01-06', '汉族', '福建省|福州市|闽侯县|11', '0', '0', '0', '0', 1);
INSERT INTO `resident` VALUES (101008, '星哥', NULL, '21', '12', '2008-01-06', '汉族', '福建省|福州市|闽侯县|11', '-1', '0', '0', '0', 14);
INSERT INTO `resident` VALUES (101009, '猫王', NULL, '232141', '1231', '2021-01-06', '汉族', '福建省|福州市|闽侯县|福州1', '-1', '0', '0', '0', 1);
INSERT INTO `resident` VALUES (101010, '王小二', 2, '123123', '福建', '2021-01-06', '汉族', '福建省|福州市|闽侯县|1212', '0', '0', '0', '0', 1);

-- ----------------------------
-- Table structure for serve
-- ----------------------------
DROP TABLE IF EXISTS `serve`;
CREATE TABLE `serve`  (
  `serve_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务id',
  `serve_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务人员名字',
  `serve_type_id` int(11) NOT NULL COMMENT '服务类型id',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务电话',
  `serve_int` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '服务简介(富文本)',
  `serve_money` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务价格',
  `serve_img` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`serve_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社区服务' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of serve
-- ----------------------------
INSERT INTO `serve` VALUES (1, '小陈12', 1, '13646028517', '<p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><h1>社区水电服务</h1><p></p><p><br></p><p></p><p><br></p><p></p>', '35.50', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdpic.tiankong.com%2Fwg%2Fll%2FQJ8219347964.jpg&refer=http%3A%2F%2Fdpic.tiankong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650266097&t=cccd9dcd28aed4f3e83db5afebc0881a');
INSERT INTO `serve` VALUES (6, '小吴', 2, '13646028517', '<p>欢迎使用富文本编辑器</p><p><br></p>', '30.50', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpicnew12.photophoto.cn%2F20171202%2Fchuangjianhexieshehui-29233528_1.jpg&refer=http%3A%2F%2Fpicnew12.photophoto.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1649639292&t=4f104a839465f0c86b871a84c1d0e927');

-- ----------------------------
-- Table structure for serve_type
-- ----------------------------
DROP TABLE IF EXISTS `serve_type`;
CREATE TABLE `serve_type`  (
  `serve_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名字',
  PRIMARY KEY (`serve_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of serve_type
-- ----------------------------
INSERT INTO `serve_type` VALUES (1, '水管维修');
INSERT INTO `serve_type` VALUES (2, '电器维修');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `user_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `user_avatar` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态 0-正常 1冻结',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0-男 1-女',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `user_int` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `unique_userName`(`user_name`) USING BTREE COMMENT '唯一用户名字'
) ENGINE = InnoDB AUTO_INCREMENT = 913012 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (913000, 'oxl', '202cb962ac59075b964b07152d234b70', NULL, NULL, '福建省|福州市|闽侯县|123', NULL, '1', '1', NULL, NULL);
INSERT INTO `user` VALUES (913005, 'cyh', 'c20ad4d76fe97759aa27a0c99bff6710', NULL, NULL, '福建省|福州市|闽侯县|123', NULL, '0', '0', NULL, NULL);
INSERT INTO `user` VALUES (913010, 'ox', 'e10adc3949ba59abbe56e057f20f883e', NULL, '12', '福建省|福州市|闽侯县|12', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi.qqkou.com%2Fi%2F0a1406481394x2846025398b26.jpg&refer=http%3A%2F%2Fi.qqkou.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1649253947&t=deede54935f706da8c8eee1dccfb345c', '0', '0', NULL, NULL);
INSERT INTO `user` VALUES (913011, 'cy1', 'e10adc3949ba59abbe56e057f20f883e', NULL, '12', '福建省|福州市|闽侯县|蓝天幼儿园', 'https://img022-1306164549.cos.ap-nanjing.myqcloud.com/oxl/83581649688707606.jpeg', '0', '0', NULL, NULL);

-- ----------------------------
-- Table structure for user_heart
-- ----------------------------
DROP TABLE IF EXISTS `user_heart`;
CREATE TABLE `user_heart`  (
  `heart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户收藏id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `act_id` int(11) NULL DEFAULT NULL COMMENT '活动id',
  `my_heart` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否收藏 0取消  1收藏',
  PRIMARY KEY (`heart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的收藏' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_heart
-- ----------------------------
INSERT INTO `user_heart` VALUES (1, 913005, 913107, '1');
INSERT INTO `user_heart` VALUES (2, 913005, 913106, '1');

SET FOREIGN_KEY_CHECKS = 1;
