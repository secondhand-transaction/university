/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : university

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-07-10 19:03:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_number` int(11) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('1', '11', 'd9b1d7db4cd6e70935368a1efb10e377');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '书籍');
INSERT INTO `category` VALUES ('2', '数码');
INSERT INTO `category` VALUES ('3', '交通');
INSERT INTO `category` VALUES ('4', '运动');
INSERT INTO `category` VALUES ('5', '盆栽');
INSERT INTO `category` VALUES ('6', '杂货');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `goods_status` int(11) NOT NULL COMMENT '0代表正常上架，1代表被举报，2代表正在交易的，3代表已交易',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `price` double(10,2) NOT NULL,
  `goods_image` varchar(255) DEFAULT NULL,
  `evaluation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('31', '8', 'macbook air', '用过一年，略有磨损', '0', '2', '2300.00', '91e0f191-b3f0-4589-a3ba-c3b708857784.jpg', null);
INSERT INTO `goods` VALUES ('32', '8', '小风扇', '风力不大，要对头吹！！！', '0', '6', '15.00', 'cb740996-8069-46cd-8453-0c496f3dd24f.jpg', null);
INSERT INTO `goods` VALUES ('33', '8', '高数上下', '用过，有笔记', '22', '1', '40.00', '1fd85e47-475a-4e24-8133-94ef37383c87.jpg', null);
INSERT INTO `goods` VALUES ('34', '8', '仙人掌', '养的好好，但我带不走。', '2', '5', '30.00', '6c767475-01de-4d37-8b09-303ecf0fb860.jpg', null);
INSERT INTO `goods` VALUES ('35', '9', '扫把3个', '没用过的', '0', '6', '30.00', '5d53c6a9-40fb-43af-87ed-31b7fe0d2d7e.jpg', null);
INSERT INTO `goods` VALUES ('36', '9', '足球', '踢过很多次', '4', '4', '20.00', '44ecabe7-ad32-4a84-a659-0d3ad57523f4.png', null);
INSERT INTO `goods` VALUES ('37', '8', '篮球', '买了没怎么打', '0', '4', '40.00', 'dd5ce74e-6c43-4fce-99a6-9b4191d958de.jpg', null);
INSERT INTO `goods` VALUES ('38', '8', '哑铃2个', '基本是新的！！', '0', '4', '68.00', '6d62dbf8-776d-414a-9e6f-68b28a7a2701.jpg', null);
INSERT INTO `goods` VALUES ('39', '8', '崔书阳', '任意陪玩，10元一次', '3', '6', '10.00', '02acfe2a-545b-4061-93f9-54121324471b.jpg', '贱的很');
INSERT INTO `goods` VALUES ('40', '9', '薛金龙', '寂寞求撩', '0', '6', '0.00', '3021d8c4-993e-4446-9017-d545974a3a88.jpg', null);
INSERT INTO `goods` VALUES ('58', '8', '英语书', '没用过', '0', '1', '21.00', 'ac357b17-e971-4097-92a1-723d011a3ac8.png', null);

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `information_id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `sendtime` date NOT NULL,
  `nextinfor_id` int(11) DEFAULT NULL,
  `information_status` int(11) NOT NULL,
  PRIMARY KEY (`information_id`),
  KEY `buyer_id` (`buyer_id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `information_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES ('1', '9', '8', '我想联系薛金龙', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('3', '8', '9', '你们直接见面可以嘛？', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('4', '9', '8', '可以，天桥，18：30', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('5', '9', '8', '提前一个小时，17：30', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('6', '8', '9', '好的', '2019-07-10', '0', '0');
INSERT INTO `information` VALUES ('11', '8', '-1', '你的物品ID：39被白云选中，请尽快联系他（电话号码：123456789），完成交易。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('12', '8', '-1', '你的物品ID：33被白云选中，请尽快联系他（电话号码：123456789），完成交易。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('16', '8', '-1', '你的物品ID：38被白云选中，请尽快联系他（电话号码：123456789），完成交易。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('17', '8', '-1', '你的物品ID：37被白云选中，请尽快联系他（电话号码：123456789），完成交易。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('18', '8', '-1', '你的物品ID：34被白云选中，请尽快联系他（电话号码：123456789），完成交易。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('21', '9', '-1', '你购买的物品ID:38被小白鸽取消，有问题请联系他（电话号码：123456789）。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('22', '8', '-1', '你发布的物品ID:37被白云取消，有问题请联系他（电话号码：123456789）。', '2019-07-10', '0', '1');
INSERT INTO `information` VALUES ('23', '9', '-1', '您关注的电脑已经上架, 请搜索笔记本', '2019-07-10', '0', '0');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '买家的id',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('4', '9');
INSERT INTO `order` VALUES ('6', '9');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderItem_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL COMMENT '物品id，用来查找物品信息',
  `orderItem_status` int(11) NOT NULL COMMENT '0，代表买家，卖家未确认，1代表一方未确认，2代表都确认',
  PRIMARY KEY (`orderItem_id`),
  KEY `order_id` (`order_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', '4', '39', '3');
INSERT INTO `orderitem` VALUES ('2', '4', '33', '2');
INSERT INTO `orderitem` VALUES ('9', '6', '38', '-1');
INSERT INTO `orderitem` VALUES ('10', '6', '37', '-2');
INSERT INTO `orderitem` VALUES ('11', '6', '34', '0');

-- ----------------------------
-- Table structure for querygood
-- ----------------------------
DROP TABLE IF EXISTS `querygood`;
CREATE TABLE `querygood` (
  `query_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `query_name` varchar(50) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`query_id`),
  KEY `user_id` (`user_id`),
  KEY `querygood_ibfk_2` (`category_id`),
  CONSTRAINT `querygood_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `querygood_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of querygood
-- ----------------------------
INSERT INTO `querygood` VALUES ('4', '9', '电脑', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `phone` int(11) NOT NULL,
  `user_number` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user_status` int(11) NOT NULL COMMENT '0代表买家，1代表卖家。',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', '小白鸽', 'd9b1d7db4cd6e70935368a1efb10e377', '123456789', '1', '123456@qq.com', '1');
INSERT INTO `user` VALUES ('9', '白云吖', '665f644e43731ff9db3d341da5c827e1', '123456789', '2', 'hongfeng342@163.com', '1');
INSERT INTO `user` VALUES ('10', '蓝牙', 'd9b1d7db4cd6e70935368a1efb10e377', '123456789', '3', '2@qq.com', '2');
