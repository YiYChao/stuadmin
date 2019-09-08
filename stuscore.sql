/*
 Navicat Premium Data Transfer

 Source Server         : Tencent
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 58.87.64.18:3306
 Source Schema         : stuscore

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 08/09/2019 18:39:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for stu_course
-- ----------------------------
DROP TABLE IF EXISTS `stu_course`;
CREATE TABLE `stu_course`  (
  `cno` int(11) NOT NULL,
  `sno` int(11) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_course
-- ----------------------------
INSERT INTO `stu_course` VALUES (194012, 9731, 56);
INSERT INTO `stu_course` VALUES (194023, 9812, 87);

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class`  (
  `classno` int(11) NOT NULL,
  `classname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `dno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`classno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_class
-- ----------------------------
INSERT INTO `tb_class` VALUES (1010, '计算机科学与技术', 55);
INSERT INTO `tb_class` VALUES (1011, '物联网工程', 55);
INSERT INTO `tb_class` VALUES (1012, '网络工程', 55);
INSERT INTO `tb_class` VALUES (1013, '软件工程', 55);
INSERT INTO `tb_class` VALUES (2010, '英语', 56);
INSERT INTO `tb_class` VALUES (2011, '日语', 56);
INSERT INTO `tb_class` VALUES (2012, '德语', 56);

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course`  (
  `cno` int(11) NOT NULL,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `credit` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `period` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `semester` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES (9731, '英美文学', '4', '64', '1', 195621);
INSERT INTO `tb_course` VALUES (9812, '程序设计', '4', '64', '2', 195501);

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department`  (
  `dno` int(11) NOT NULL,
  `dname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`dno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES (55, '计算机学院');
INSERT INTO `tb_department` VALUES (56, '外国语学院');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `sno` int(11) NOT NULL,
  `sname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ssex` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sbirthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `snative` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `shome` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stel` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `dno` int(11) NULL DEFAULT NULL,
  `classno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (194012, '刘国', '男', '1996-12-03', '广东珠海', '广东省珠海市青岛路67号', '13834452353', 55, 1010);
INSERT INTO `tb_student` VALUES (194018, '邓肯', '男', '1994-06-28', '山西晋中', '山西省晋中市榆次区省高校新区', '16547397392', 55, 1012);
INSERT INTO `tb_student` VALUES (194023, '孙峰', '男', '1997-04-30', '江苏苏州', '江苏省苏州市苏州园林', '15576453545', 56, 2010);
INSERT INTO `tb_student` VALUES (194025, '吴风华', '女', '1998-07-12', '陕西西安', '陕西省西安市南昌路12号', '18743453344', 55, 1012);
INSERT INTO `tb_student` VALUES (194318, '钱大孚', '男', '1995-09-23', '北京昌平', '北京市昌平区回龙观镇', '15045434232', 56, 2012);
INSERT INTO `tb_student` VALUES (194321, '叶杰夫', '男', '1996-02-06', '江西南昌', '江西省南昌市东湖区', '18734342432', 56, 1013);

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `tno` int(11) NOT NULL,
  `tname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tsex` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tbirthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tnative` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `thome` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ttel` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `dno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`tno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (195501, '张刚', '男', '1996-03-24', '北京昌平', '北京市昌平区回龙观镇', '13423453211', 55);
INSERT INTO `tb_teacher` VALUES (195621, '王鹏', '男', '1987-08-12', '广州', '广东省广州市白云区', '18754783728', 56);
INSERT INTO `tb_teacher` VALUES (195626, '乔英', '女', '1979-11-23', '太原', '山西省太原市万柏林区', '17747393782', 56);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `tb_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `tb_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33337 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (33336, '小易', '123456');

SET FOREIGN_KEY_CHECKS = 1;
