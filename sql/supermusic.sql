/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : supermusic

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2021-12-03 22:12:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父权限id',
  `parent_name` varchar(100) DEFAULT NULL COMMENT '父权限名',
  `label` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `code` varchar(100) DEFAULT NULL COMMENT '授权标识符',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `name` varchar(100) DEFAULT NULL COMMENT '路径名',
  `url` varchar(100) DEFAULT NULL COMMENT '授权路径',
  `order_num` varchar(50) DEFAULT NULL COMMENT '序号',
  `type` int(10) DEFAULT NULL COMMENT '类型（0目录，1菜单，2按钮）',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标路径',
  `comment` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_home` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `playlist`
-- ----------------------------
DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '歌单序号',
  `playlist_name` varchar(50) DEFAULT NULL COMMENT '歌单名字',
  `playlist_tag` varchar(20) DEFAULT NULL COMMENT '歌单类型',
  `playlist_create` varchar(20) DEFAULT NULL COMMENT '创建者',
  `playlist_comment` varchar(200) DEFAULT NULL COMMENT '备注',
  `playlist_type` int(10) DEFAULT NULL COMMENT '歌单类型（0普通歌单，1排行榜，2我的歌单）',
  `icon` varchar(255) DEFAULT NULL COMMENT '歌单图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of playlist
-- ----------------------------
INSERT INTO `playlist` VALUES ('1', '我的歌单', '快乐', 'user', '喜欢的歌曲', null, null);
INSERT INTO `playlist` VALUES ('2', '周杰伦的歌单', '轻快', 'root', '还行', null, null);

-- ----------------------------
-- Table structure for `playlist_song`
-- ----------------------------
DROP TABLE IF EXISTS `playlist_song`;
CREATE TABLE `playlist_song` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `song_id` int(10) DEFAULT NULL COMMENT '歌曲序号',
  `playlist_id` int(10) DEFAULT NULL COMMENT '歌单序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of playlist_song
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `comment` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(10) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `singer`
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `singer_name` varchar(20) DEFAULT NULL COMMENT '歌手名字',
  `nationality` varchar(20) DEFAULT NULL COMMENT '国籍',
  `birth_address` varchar(100) DEFAULT NULL COMMENT '出生地址',
  `sex` int(10) DEFAULT NULL COMMENT '性别（0：男，1：女）',
  `profile` varchar(500) DEFAULT NULL COMMENT '简介',
  `icon` varchar(255) DEFAULT NULL COMMENT '歌手照片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singer
-- ----------------------------

-- ----------------------------
-- Table structure for `song`
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '歌曲id',
  `name` varchar(10) DEFAULT NULL COMMENT '歌曲名字',
  `singer_id` int(50) DEFAULT NULL COMMENT '歌手id',
  `song_url` varchar(100) DEFAULT NULL COMMENT '歌曲文件地址',
  `picture_url` varchar(100) DEFAULT NULL COMMENT '歌曲封面地址',
  `playlist_name` varchar(50) DEFAULT NULL COMMENT '歌单名字',
  `comment` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `root` int(10) DEFAULT NULL COMMENT '权限等级',
  `phone` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `icon` varchar(200) DEFAULT NULL COMMENT '用户头像路径',
  `nickname` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `sex` int(10) DEFAULT NULL COMMENT '性别（0男，1女）',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
