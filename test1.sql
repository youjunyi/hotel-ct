/*
Navicat MySQL Data Transfer

Source Server         : 1111
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-09 11:15:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dinnertable
-- ----------------------------
DROP TABLE IF EXISTS `dinnertable`;
CREATE TABLE `dinnertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(20) DEFAULT NULL,
  `tableStatus` int(11) DEFAULT '0',
  `orderDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dinnertable
-- ----------------------------
INSERT INTO `dinnertable` VALUES ('2', '213123', '1', '2017-12-08 20:24:26');

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(20) DEFAULT NULL,
  `foodType_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `mprice` double DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_food_foodType_id` (`foodType_id`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`foodType_id`) REFERENCES `foodtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '蹲草', '1', '100', '90', '123123213123', 'upload/bmzp.jpg');

-- ----------------------------
-- Table structure for foodtype
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES ('1', '東北');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `foodCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderDetail_order_id` (`orderId`),
  KEY `orderDetail_food_id` (`food_id`),
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `orderStatus` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_table_id` (`table_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `dinnertable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `zjjb` varchar(255) DEFAULT NULL,
  `zjlb` varchar(255) DEFAULT NULL,
  `bz` varchar(2000) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('2', '123123', '12312', '123123', '123123', '123123123', '111111');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `sbjf` varchar(255) DEFAULT NULL,
  `wenjinurl` varchar(255) DEFAULT NULL,
  `sbsj` varchar(255) DEFAULT NULL,
  `sndw` varchar(255) DEFAULT NULL,
  `ndwcsj` varchar(255) DEFAULT NULL,
  `ktlb` varchar(255) DEFAULT NULL,
  `ktjb` varchar(255) DEFAULT NULL,
  `sysid` int(11) DEFAULT NULL,
  `issh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '213123123', '123123123123', '3', '123123123123', 'upload/123123.txt', '123123123123', '12321321312312', null, '工科类', '系级', '2', '1');
INSERT INTO `topic` VALUES ('2', '213123123', '123123123123', '3', '123123123123', 'upload/123123.txt', '123123123123', '12321321312312', null, '工科类', '系级', '2', '1');
INSERT INTO `topic` VALUES ('3', '213123123', '123123123123', '3', '123123123123', 'upload/bmzp.jpg', '123123123123', '12321321312312', '', '工科类', '校级', '2', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `minzhu` varchar(255) DEFAULT NULL,
  `cno` varchar(255) DEFAULT NULL,
  `adds` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `emil` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `cjgzdate` date DEFAULT NULL,
  `zhuanye` varchar(255) DEFAULT NULL,
  `yuanxiao` varchar(255) DEFAULT NULL,
  `zhicheng` varchar(255) DEFAULT NULL,
  `xueli` varchar(255) DEFAULT NULL,
  `xuewei` varchar(255) DEFAULT NULL,
  `waiyu` varchar(255) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `jianyanshi` varchar(255) DEFAULT NULL,
  `yjfx` varchar(255) DEFAULT NULL,
  `beizhu` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '二恶烷', '23213', '111111', '男', '123123', '123123', '213213', '213123', '123123', '2017-12-07', '2017-12-07', '123213', '123213', '教授', '专科', '学士', '123123', '信息科学与电气工程学院', '计算机科学与技术', '213213', '12312321321');
INSERT INTO `user` VALUES ('4', '姓名', '职工号', '000000', '男', '民族', '身份证号', '住址', '联系电话', '邮箱', '2017-12-07', '2017-12-07', '所学专业', '毕业院校', '教授', '专科', '学士', '外语程度', '信息科学与电气工程学院', '计算机科学与技术', '研究放向', '备注');
