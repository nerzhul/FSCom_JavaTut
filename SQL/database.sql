/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50136
Source Host           : localhost:3306
Source Database       : messenger

Target Server Type    : MYSQL
Target Server Version : 50136
File Encoding         : 65001

Date: 2010-02-03 14:14:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `acc_contact`
-- ----------------------------
DROP TABLE IF EXISTS `acc_contact`;
CREATE TABLE `acc_contact` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  `blocked` int(1) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `group` int(2) NOT NULL,
  `accepted` int(1) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of acc_contact
-- ----------------------------

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
  `sha_pass` varchar(40) NOT NULL,
  `pseudo` varchar(50) NOT NULL,
  `phr_perso` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of account
-- ----------------------------
