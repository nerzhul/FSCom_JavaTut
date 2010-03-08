/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50136
Source Host           : localhost:3306
Source Database       : messenger

Target Server Type    : MYSQL
Target Server Version : 50136
File Encoding         : 65001

Date: 2010-03-08 16:53:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `acc_blocked`
-- ----------------------------
DROP TABLE IF EXISTS `acc_blocked`;
CREATE TABLE `acc_blocked` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  `blocked` int(1) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of acc_blocked
-- ----------------------------
INSERT INTO `acc_blocked` VALUES ('1', '2', '0');
INSERT INTO `acc_blocked` VALUES ('2', '3', '0');
INSERT INTO `acc_blocked` VALUES ('2', '1', '0');
INSERT INTO `acc_blocked` VALUES ('2', '5', '0');

-- ----------------------------
-- Table structure for `acc_contact`
-- ----------------------------
DROP TABLE IF EXISTS `acc_contact`;
CREATE TABLE `acc_contact` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `group` int(2) NOT NULL,
  `accepted` int(1) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of acc_contact
-- ----------------------------
INSERT INTO `acc_contact` VALUES ('2', '1', '', '0', '0');
INSERT INTO `acc_contact` VALUES ('5', '2', '', '0', '0');
INSERT INTO `acc_contact` VALUES ('1', '3', 'sdfsgfll', '0', '1');
INSERT INTO `acc_contact` VALUES ('2', '5', '', '0', '0');
INSERT INTO `acc_contact` VALUES ('1', '2', '', '0', '0');

-- ----------------------------
-- Table structure for `acc_group`
-- ----------------------------
DROP TABLE IF EXISTS `acc_group`;
CREATE TABLE `acc_group` (
  `uid` int(5) NOT NULL,
  `gid` int(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`,`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of acc_group
-- ----------------------------
INSERT INTO `acc_group` VALUES ('1', '1', 'vvv');
INSERT INTO `acc_group` VALUES ('1', '2', 'ssss');
INSERT INTO `acc_group` VALUES ('2', '2', 'sdffds');
INSERT INTO `acc_group` VALUES ('2', '5', 'zzzz');
INSERT INTO `acc_group` VALUES ('2', '6', 'eeeee');
INSERT INTO `acc_group` VALUES ('2', '7', 'ffff');
INSERT INTO `acc_group` VALUES ('2', '8', 'vvvv');
INSERT INTO `acc_group` VALUES ('2', '9', 'aaaaaaaaaa');
INSERT INTO `acc_group` VALUES ('2', '10', 'vvvvvvvvvvvvvv');
INSERT INTO `acc_group` VALUES ('2', '11', 'bbbbbbbbb');
INSERT INTO `acc_group` VALUES ('2', '12', 'nnnnnnnnn');
INSERT INTO `acc_group` VALUES ('2', '13', ',,,,,');
INSERT INTO `acc_group` VALUES ('2', '14', 'vvvvvvv');

-- ----------------------------
-- Table structure for `acc_invitation`
-- ----------------------------
DROP TABLE IF EXISTS `acc_invitation`;
CREATE TABLE `acc_invitation` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of acc_invitation
-- ----------------------------

-- ----------------------------
-- Table structure for `acc_status`
-- ----------------------------
DROP TABLE IF EXISTS `acc_status`;
CREATE TABLE `acc_status` (
  `guid` int(5) NOT NULL,
  `status` int(1) NOT NULL,
  `suffix` varchar(10) NOT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acc_status
-- ----------------------------

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `uid` int(5) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) NOT NULL,
  `sha_pass` varchar(25) NOT NULL,
  `pseudo` varchar(25) NOT NULL,
  `phr_perso` varchar(25) NOT NULL DEFAULT 'msg personnel',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user` (`user`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'toto', 'toto', 'Nerzhul', 'rtyuiop');
INSERT INTO `account` VALUES ('2', 'msdi', 'tutu', 'Jean-Baptiste', 'dfhfghfgj');
INSERT INTO `account` VALUES ('3', 'dsfghjk', 'sggjhfjgj', 'ghjhfjdfsdf', 'sdfsf');
INSERT INTO `account` VALUES ('4', 'bubu', 'tutut', 'sdf', 'sfsdfsdf');
INSERT INTO `account` VALUES ('5', 'azerty', 'azertyu', 'azerty', 'eqp,g');
INSERT INTO `account` VALUES ('6', 'toto2', 'tatata', 'toto2', 'msg personnel');
INSERT INTO `account` VALUES ('7', 'toto3', 'tototo', 'toto3', 'msg personnel');
INSERT INTO `account` VALUES ('8', 'dsfdfdfs', 'azerty', 'dsfdfdfs', 'msg personnel');
