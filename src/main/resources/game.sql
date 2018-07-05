/*
 Navicat Premium Data Transfer

 Source Server         : lo
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : cif

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 05/07/2018 22:33:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `sname` varchar(50) DEFAULT NULL COMMENT '简称',
  `ename` varchar(50) DEFAULT NULL COMMENT '英文名称',
  `version` int(10) DEFAULT NULL COMMENT '版本',
  `dt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `dt_updated` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of game
-- ----------------------------
BEGIN;
INSERT INTO `game` VALUES ('7e8b5d93282f4eb9b2e28265c4aa493f', '王者荣耀', '农药', 'League of Legends', 1, '2018-06-22 23:23:13', NULL, '1', NULL);
INSERT INTO `game` VALUES ('97dae297ea2d4d2d95d78ec54cd1997b', '反恐精英：全球攻势', 'csgo', 'Counter-Strike: Global Offensive', 1, '2018-06-22 19:52:14', NULL, '1', NULL);
INSERT INTO `game` VALUES ('a886fea30192466bbc4e17e2c40232a8', '英雄联盟', '撸啊撸', 'League of Legends', 1, '2018-06-22 19:52:14', NULL, '1', NULL);
INSERT INTO `game` VALUES ('ef21761b864749eeb70c02b89d6eca56', '刀塔2', '刀塔2', 'DOTA2', 1, '2018-06-22 19:52:14', NULL, '1', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
