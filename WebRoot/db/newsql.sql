/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.73-community : Database - crmitem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crmitem` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `crmitem`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`Id`,`Address`) values (1,'郑州市'),(2,'洛阳市'),(3,'开封市'),(4,'信阳市'),(5,'驻马店市'),(6,'南阳市'),(7,'周口市'),(8,'许昌市'),(9,'平顶山市'),(10,'新乡市'),(11,'漯河市'),(12,'商丘市'),(13,'三门峡市'),(14,'济源市'),(15,'焦作市'),(16,'安阳市'),(17,'鹤壁市'),(18,'濮阳市');

/*Table structure for table `askers` */

DROP TABLE IF EXISTS `askers`;

CREATE TABLE `askers` (
  `AskerId` varchar(100) NOT NULL COMMENT '咨询师Id',
  `AskerName` varchar(100) DEFAULT NULL COMMENT '咨询师名称',
  `CheckState` varchar(50) DEFAULT '未签到' COMMENT '签到状态',
  `CheckInTime` timestamp NULL DEFAULT NULL COMMENT '签到时间',
  `ChangeState` varchar(50) DEFAULT '否' COMMENT '是否分配学生：是，否',
  `Weight` int(11) DEFAULT '0' COMMENT '权重',
  `RoleName` varchar(100) DEFAULT '咨询师' COMMENT '角色名称',
  `BakContent` varchar(2000) DEFAULT NULL COMMENT '备注信息',
  `AskerIsDel` varchar(2) DEFAULT '0' COMMENT '是否删除，0：有效；1：删除；',
  PRIMARY KEY (`AskerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `askers` */

insert  into `askers`(`AskerId`,`AskerName`,`CheckState`,`CheckInTime`,`ChangeState`,`Weight`,`RoleName`,`BakContent`,`AskerIsDel`) values ('1367ea2f31d745bf868518bd40d6f567','咨询师三','未签到',NULL,'否',2,'咨询师','','0'),('713f14e8a94440fcb39ccf49ce527bff','咨询师一','未签到',NULL,'否',3,'咨询师','','0'),('7ae559fa9f46493fb2808126c3e0c635','咨询师四','未签到',NULL,'否',8,'咨询师','','0'),('a024d44e787c46cb9bea1d039d231cc3','咨询师二','未签到',NULL,'否',4,'咨询师','','0'),('f87c4924469b4e4380007d97545783a7','张十一','未签到',NULL,'否',5,'咨询师','','0');

/*Table structure for table `autoonoff` */

DROP TABLE IF EXISTS `autoonoff`;

CREATE TABLE `autoonoff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isOpen` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `autoonoff` */

insert  into `autoonoff`(`id`,`isOpen`) values (1,1);

/*Table structure for table `dynamic` */

DROP TABLE IF EXISTS `dynamic`;

CREATE TABLE `dynamic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dyaskerId` varchar(100) NOT NULL COMMENT '咨询师ID',
  `dyaskerName` varchar(100) NOT NULL COMMENT '咨询师姓名',
  `dystuId` varchar(100) NOT NULL COMMENT '学生ID',
  `dystuName` varchar(100) NOT NULL COMMENT '学生姓名',
  `dynamicContext` varchar(1000) NOT NULL COMMENT '动态消息',
  `dycreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dyisLook` int(11) DEFAULT '0' COMMENT '是否已看,0未看1已看',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `dynamic` */

insert  into `dynamic`(`id`,`dyaskerId`,`dyaskerName`,`dystuId`,`dystuName`,`dynamicContext`,`dycreateTime`,`dyisLook`) values (1,'f87c4924469b4e4380007d97545783a7','张十一','6811a29768e34ba38d8e9e97190bb53c','小苏','成功了吧','2017-06-12 12:52:53',0),(2,'f87c4924469b4e4380007d97545783a7','张十一','6811a29768e34ba38d8e9e97190bb53c','小苏','成功了吧1','2017-06-12 12:54:39',0);

/*Table structure for table `modules` */

DROP TABLE IF EXISTS `modules`;

CREATE TABLE `modules` (
  `ModuleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块编号',
  `ModuleNAME` varchar(50) NOT NULL COMMENT '模块名称',
  `ModuleParentId` int(11) DEFAULT '0' COMMENT '父模块编号',
  `ModulePath` varchar(200) DEFAULT NULL COMMENT '模块路径',
  `ModuleWeight` int(11) DEFAULT NULL COMMENT '权重',
  `ModuleIsDel` varchar(2) DEFAULT '0' COMMENT '是否删除，0：有效；1：删除；',
  PRIMARY KEY (`ModuleId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `modules` */

insert  into `modules`(`ModuleId`,`ModuleNAME`,`ModuleParentId`,`ModulePath`,`ModuleWeight`,`ModuleIsDel`) values (1,'权限管理',0,NULL,NULL,'0'),(2,'用户管理',1,'jsp/UsersManage.jsp',NULL,'0'),(9,'角色管理',1,'jsp/Roles.jsp',NULL,'0'),(10,'模块管理',1,'jsp/Modules.jsp',NULL,'0'),(11,'系统管理',0,NULL,NULL,'0'),(12,'修改密码',11,'jsp/UpdatePwd.jsp',NULL,'0'),(13,'分量设置',11,'jsp/ComponentSetting.jsp',NULL,'0'),(14,'调整分量顺序',11,'jsp/SetWeight.jsp',NULL,'0'),(15,'员工签到',11,'jsp/Userchecks.jsp',NULL,'0'),(16,'网络档案',0,NULL,NULL,'0'),(17,'网络学生',16,'jsp/NetStudent.jsp',NULL,'0'),(18,'我的学生',16,'jsp/MyStudent.jsp',NULL,'0'),(19,'网络跟踪',16,'jsp/NetFollow.jsp',NULL,'0'),(20,'网络学生_1',16,'jsp/NetStudent1.jsp',NULL,'0'),(21,'我的学生_1',16,'jsp/MyStudent1.jsp',NULL,'0'),(22,'网络跟踪_1',16,'jsp/NetFollow1.jsp',NULL,'0'),(23,'动态消息',16,'jsp/Dynamic.jsp',NULL,'0');

/*Table structure for table `netfollows` */

DROP TABLE IF EXISTS `netfollows`;

CREATE TABLE `netfollows` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '跟踪Id',
  `StudentId` varchar(40) DEFAULT NULL COMMENT '学生Id',
  `StudentName` varchar(100) DEFAULT NULL COMMENT '学生名称',
  `FollowTime` timestamp NULL DEFAULT NULL COMMENT '跟踪时间',
  `NextFollowTime` timestamp NULL DEFAULT NULL COMMENT '下次跟踪时间',
  `Content` varchar(2000) DEFAULT NULL COMMENT '备注',
  `UserId` varchar(100) DEFAULT NULL COMMENT '用户Id',
  `FollowType` varchar(100) DEFAULT NULL COMMENT '跟踪类型',
  `CreateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `FollowState` varchar(100) DEFAULT NULL COMMENT '跟踪状态',
  `folIsDel` varchar(2) DEFAULT '0' COMMENT '是否删除，0：有效；1：删除；',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `netfollows` */

insert  into `netfollows`(`Id`,`StudentId`,`StudentName`,`FollowTime`,`NextFollowTime`,`Content`,`UserId`,`FollowType`,`CreateTime`,`FollowState`,`folIsDel`) values (4,'8aaa1b250128456ab2b4ff62ae730a4a','荣景','2017-06-14 00:00:00','2017-06-15 00:00:00',NULL,NULL,'面谈','2017-06-04 18:57:53','上门未报名','0'),(5,'eb447bc2b27b49fe9eeb60eb7b5326a8','小白','2017-06-01 00:00:00','2017-06-02 00:00:00','阿斯达斯的撒旦撒',NULL,'网络','2017-06-09 08:18:50','思量','0'),(6,'ef0c84b3460b45d299df0b59a89ca376','容止','2017-06-28 00:00:00','2017-06-29 00:00:00',NULL,NULL,'面谈','2017-06-09 10:13:40','已进班','0'),(7,'8aaa1b250128456ab2b4ff62ae730a4a','荣景','2017-06-11 00:00:00','2017-06-11 00:00:00','阿萨德',NULL,'面谈','2017-06-11 09:30:56','已进班','0'),(8,'eb447bc2b27b49fe9eeb60eb7b5326a8','小白','2017-06-11 09:33:57',NULL,NULL,NULL,NULL,'2017-06-11 09:34:03',NULL,'0'),(9,'8aaa1b250128456ab2b4ff62ae730a4a','荣景',NULL,NULL,'111',NULL,NULL,'2017-06-11 09:39:06',NULL,'0'),(13,'8aaa1b250128456ab2b4ff62ae730a4a','荣景1','2017-06-01 14:08:12','2017-06-11 14:08:11','奥斯多哈soi的',NULL,NULL,'2017-06-11 14:08:19',NULL,'0'),(14,'8aaa1b250128456ab2b4ff62ae730a4a','荣景1','2017-06-04 14:19:04','2017-06-11 14:19:08','测试','808337b9f9754c6bbeca0141173d845f',NULL,'2017-06-11 14:19:12',NULL,'0'),(15,'8aaa1b250128456ab2b4ff62ae730a4a','荣景1','2017-06-07 14:21:08','2017-06-11 14:21:14','测试1','713f14e8a94440fcb39ccf49ce527bff',NULL,'2017-06-11 14:21:20',NULL,'0'),(16,'eb447bc2b27b49fe9eeb60eb7b5326a8','小白','2017-06-12 08:54:14','2017-06-12 08:54:17',NULL,'808337b9f9754c6bbeca0141173d845f',NULL,'2017-06-12 08:54:18',NULL,'0'),(17,'b2b55cb4815d444896f8c3ace8f142c1','小明','2017-06-12 10:07:53','2017-06-14 10:07:55',NULL,'808337b9f9754c6bbeca0141173d845f',NULL,'2017-06-12 10:07:58',NULL,'0'),(18,'57c27bd1e4da404183372a759f95dd89','小豪','2017-07-01 10:08:33','2017-07-07 17:08:39',NULL,'808337b9f9754c6bbeca0141173d845f',NULL,'2017-06-12 10:08:53',NULL,'0');

/*Table structure for table `rolemodules` */

DROP TABLE IF EXISTS `rolemodules`;

CREATE TABLE `rolemodules` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) NOT NULL COMMENT '外键，角色ID',
  `ModuleId` int(11) NOT NULL COMMENT '外键，模块ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8;

/*Data for the table `rolemodules` */

insert  into `rolemodules`(`Id`,`RoleId`,`ModuleId`) values (414,1,11),(415,1,12),(416,1,11),(417,1,13),(418,1,11),(419,1,14),(420,1,11),(421,1,15),(422,1,11),(423,1,17),(424,1,16),(425,1,18),(426,1,16),(427,1,19),(428,1,16),(429,3,12),(430,3,11),(431,3,21),(432,3,16),(433,3,22),(434,3,16),(435,3,23),(436,3,16),(437,5,12),(438,5,11),(439,5,20),(440,5,16),(441,2,1),(442,2,2),(443,2,1),(444,2,9),(445,2,1),(446,2,10),(447,2,1),(448,2,11),(449,2,12),(450,2,11),(451,2,13),(452,2,11),(453,2,14),(454,2,11),(455,2,15),(456,2,11),(457,2,16),(458,2,17),(459,2,16),(460,2,18),(461,2,16),(462,2,19),(463,2,16),(464,2,20),(465,2,16),(466,2,21),(467,2,16),(468,2,22),(469,2,16),(470,2,23),(471,2,16);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组编号',
  `RoleNAME` varchar(50) NOT NULL COMMENT '角色(用户组)名称',
  `RoleIsDel` varchar(2) DEFAULT '0' COMMENT '是否删除，0：有效；1：删除；',
  PRIMARY KEY (`RoleId`),
  UNIQUE KEY `IndexUnique_Name` (`RoleNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`RoleId`,`RoleNAME`,`RoleIsDel`) values (1,'咨询经理','0'),(2,'管理员','0'),(3,'咨询师','0'),(5,'网络咨询','0');

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `StuId` varchar(40) NOT NULL COMMENT '学生编号',
  `StuNAME` varchar(50) NOT NULL COMMENT '学员姓名',
  `StuAge` int(11) DEFAULT NULL COMMENT '年龄',
  `StuSex` varchar(50) DEFAULT NULL COMMENT '性别 ',
  `StuPhone` varchar(50) DEFAULT NULL COMMENT '学员电话',
  `StuStatus` varchar(50) DEFAULT NULL COMMENT '学历',
  `PerState` varchar(50) DEFAULT NULL COMMENT '个人状态',
  `MsgSource` varchar(50) DEFAULT NULL COMMENT '来源渠道',
  `SourceUrl` varchar(100) DEFAULT NULL COMMENT '来源网址',
  `SourceKeyWord` varchar(100) DEFAULT NULL COMMENT '来源关键词',
  `StuAddress` varchar(100) DEFAULT NULL COMMENT '所在区域',
  `NetPusherId` varchar(100) DEFAULT NULL COMMENT '录入人Id',
  `AskerId` varchar(100) DEFAULT NULL COMMENT '咨询师Id',
  `AskerName` varchar(100) DEFAULT NULL COMMENT '咨询师姓名',
  `StuQQ` varchar(50) DEFAULT NULL COMMENT '学员QQ',
  `StuWeiXin` varchar(50) DEFAULT NULL COMMENT '学员微信',
  `Content` varchar(2000) DEFAULT NULL COMMENT '备注',
  `CreateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `LearnForward` varchar(100) DEFAULT NULL COMMENT '课程方向',
  `IsValid` varchar(50) DEFAULT '待定' COMMENT '是否有效',
  `Record` varchar(100) DEFAULT NULL COMMENT '打分',
  `IsReturnVist` varchar(50) DEFAULT '否' COMMENT '是否回访',
  `FirstVisitTime` timestamp NULL DEFAULT NULL COMMENT '首次回访时间',
  `IsHome` varchar(50) DEFAULT '否' COMMENT '是否上门',
  `HomeTime` timestamp NULL DEFAULT NULL COMMENT '上门时间',
  `LostValid` varchar(2000) DEFAULT NULL COMMENT '无效原因',
  `IsPay` varchar(50) DEFAULT '否' COMMENT '是否付款',
  `PayTime` timestamp NULL DEFAULT NULL COMMENT '付款时间',
  `Money` double DEFAULT '0' COMMENT '付款金额',
  `IsReturnMoney` varchar(50) DEFAULT '否' COMMENT '是否退费',
  `IsInClass` varchar(50) DEFAULT '否' COMMENT '是否进班',
  `InClassTime` timestamp NULL DEFAULT NULL COMMENT '进班时间',
  `InClassContent` varchar(2000) DEFAULT NULL COMMENT '进班备注',
  `AskerContent` varchar(2000) DEFAULT NULL COMMENT '咨询师备注',
  `FromPart` varchar(100) DEFAULT NULL COMMENT '来源部门',
  `StuConcern` varchar(100) DEFAULT NULL COMMENT '学员关注',
  `IsBaoBei` varchar(50) DEFAULT NULL COMMENT '是否报备',
  `ZiXunName` varchar(50) DEFAULT NULL COMMENT '咨询师填写的姓名',
  `CreateUser` varchar(50) DEFAULT NULL COMMENT '录入人姓名',
  `ReturnMoneyReason` varchar(2000) DEFAULT NULL COMMENT '退费原因',
  `PreMoney` double DEFAULT '0' COMMENT '预付定金',
  `PreMoneyTime` timestamp NULL DEFAULT NULL COMMENT '预付定金时间',
  `StuIsDel` varchar(2) DEFAULT '0' COMMENT '是否删除，0：有效；1：删除；',
  PRIMARY KEY (`StuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `students` */

insert  into `students`(`StuId`,`StuNAME`,`StuAge`,`StuSex`,`StuPhone`,`StuStatus`,`PerState`,`MsgSource`,`SourceUrl`,`SourceKeyWord`,`StuAddress`,`NetPusherId`,`AskerId`,`AskerName`,`StuQQ`,`StuWeiXin`,`Content`,`CreateTime`,`LearnForward`,`IsValid`,`Record`,`IsReturnVist`,`FirstVisitTime`,`IsHome`,`HomeTime`,`LostValid`,`IsPay`,`PayTime`,`Money`,`IsReturnMoney`,`IsInClass`,`InClassTime`,`InClassContent`,`AskerContent`,`FromPart`,`StuConcern`,`IsBaoBei`,`ZiXunName`,`CreateUser`,`ReturnMoneyReason`,`PreMoney`,`PreMoneyTime`,`StuIsDel`) values ('048bf89c98f84c1bb9c6f041af171ffb','小凡',NULL,NULL,'13783266666',NULL,NULL,'百度移动端','高考站','小凡','洛阳市','808337b9f9754c6bbeca0141173d845f','7ae559fa9f46493fb2808126c3e0c635','咨询师四',NULL,NULL,NULL,'2017-06-12 08:27:37',NULL,'待定',NULL,'否',NULL,'否',NULL,NULL,'否',NULL,0,'否','否',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,0,NULL,'0'),('57c27bd1e4da404183372a759f95dd89','小豪',NULL,NULL,'18337566666',NULL,NULL,'百度','其它','小豪','洛阳市','808337b9f9754c6bbeca0141173d845f',NULL,NULL,NULL,NULL,NULL,'2017-06-12 09:22:09',NULL,'待定',NULL,'否',NULL,'否',NULL,NULL,'否',NULL,0,'否','否',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,0,NULL,'0'),('6811a29768e34ba38d8e9e97190bb53c','小苏',NULL,'','12345699999','','','百度','职英A站','小苏','洛阳市','808337b9f9754c6bbeca0141173d845f','f87c4924469b4e4380007d97545783a7','张十一','','','','2017-06-12 11:08:22','','待定','','否',NULL,'否',NULL,'','否',NULL,0,'否','否',NULL,'','','网络','','否','','张三','',0,NULL,'0'),('8aaa1b250128456ab2b4ff62ae730a4a','荣景1',20,'男','12345678912','未知','待业','自然流量','高考站','21','洛阳市',NULL,'f87c4924469b4e4380007d97545783a7','张十一','','','','2017-06-04 18:51:19','','待定','','否','2017-06-11 09:37:39','否','2017-06-09 09:39:01','','否','2017-06-11 10:24:52',0,'否','否',NULL,'','','','','','金高天',NULL,'',0,'2017-06-11 09:40:12','0'),('acae338a90b746e287311dc483209f3d','天',NULL,NULL,'13783266666',NULL,NULL,'360移动端','职英B站','天','洛阳市','808337b9f9754c6bbeca0141173d845f','7ae559fa9f46493fb2808126c3e0c635','咨询师四',NULL,NULL,NULL,'2017-06-12 11:06:25',NULL,'待定',NULL,'否',NULL,'否',NULL,NULL,'否',NULL,0,'否','否',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,0,NULL,'0'),('b2b55cb4815d444896f8c3ace8f142c1','小明',NULL,NULL,'13788855555',NULL,NULL,'百度移动端','职英A站','小明','洛阳市','808337b9f9754c6bbeca0141173d845f','f87c4924469b4e4380007d97545783a7','张十一',NULL,NULL,NULL,'2017-06-12 09:22:55',NULL,'待定',NULL,'否',NULL,'否',NULL,NULL,'否',NULL,0,'否','否',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,0,NULL,'0'),('bba006bf7b56422697d0223b4906cc0f','小灰',NULL,NULL,'12345678910',NULL,NULL,'百度移动端','职英A站','小灰','洛阳市','808337b9f9754c6bbeca0141173d845f','f87c4924469b4e4380007d97545783a7','张十一',NULL,NULL,NULL,'2017-06-11 15:24:20',NULL,'待定',NULL,'否',NULL,'否',NULL,NULL,'否',NULL,0,'否','否',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,0,NULL,'0'),('eb447bc2b27b49fe9eeb60eb7b5326a8','小白',NULL,'','13245678910','','','百度移动端','高考站','小白','洛阳市','808337b9f9754c6bbeca0141173d845f','713f14e8a94440fcb39ccf49ce527bff','咨询师一','','','','2017-06-09 08:17:35','','待定','','否','2017-06-21 09:36:26','否',NULL,'','否',NULL,0,'否','否',NULL,'','','网络','','否','','张三','',0,NULL,'0'),('ef0c84b3460b45d299df0b59a89ca376','容止',20,'男','97864531201','未知','在职','QQ','其它','21','洛阳市',NULL,NULL,NULL,'237909432',NULL,NULL,'2017-06-04 18:52:35',NULL,'待定',NULL,'否',NULL,'否',NULL,NULL,'否',NULL,0,'否','否',NULL,NULL,NULL,'教质','学时',NULL,NULL,NULL,NULL,0,NULL,'0');

/*Table structure for table `userchecks` */

DROP TABLE IF EXISTS `userchecks`;

CREATE TABLE `userchecks` (
  `CheckId` int(11) NOT NULL AUTO_INCREMENT COMMENT '签到Id',
  `UserId` varchar(100) DEFAULT NULL COMMENT '用户Id',
  `UserName` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `CheckInTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签到时间',
  `CheckState` varchar(50) DEFAULT NULL COMMENT '签到状态',
  `CheckOutTime` timestamp NULL DEFAULT NULL COMMENT '取消签到时间',
  PRIMARY KEY (`CheckId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `userchecks` */

insert  into `userchecks`(`CheckId`,`UserId`,`UserName`,`CheckInTime`,`CheckState`,`CheckOutTime`) values (1,'1711ce385d0f40349fbdd6d88dcee0f5','张三','2017-06-01 20:04:01','已签退','2017-06-01 22:04:01'),(2,'f3ae4f7b7d564caaa32ed75c2229b150','张一','2017-06-01 20:04:01','已签到',NULL),(3,'894fe4bd71db4b98b49f7d427a32189c','张二','2017-06-01 20:04:01','已签到',NULL),(4,'808337b9f9754c6bbeca0141173d845f','张三','2017-06-03 23:52:53','已签退','2017-06-04 03:04:47'),(15,'808337b9f9754c6bbeca0141173d845f','张三','2017-06-11 14:48:59','早退','2017-06-11 14:49:01');

/*Table structure for table `userroles` */

DROP TABLE IF EXISTS `userroles`;

CREATE TABLE `userroles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` varchar(100) NOT NULL COMMENT '外键，用户ID',
  `RoleId` int(11) NOT NULL COMMENT '外键，角色ID',
  PRIMARY KEY (`Id`),
  KEY `FK_UserRoles_Roles_RoleId` (`RoleId`),
  KEY `FK_UserRoles_Users_UserId` (`UserId`),
  CONSTRAINT `FK_UserRoles_Roles_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`RoleId`),
  CONSTRAINT `FK_UserRoles_Users_UserId` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `userroles` */

insert  into `userroles`(`Id`,`UserId`,`RoleId`) values (13,'808337b9f9754c6bbeca0141173d845f',2),(14,'15131ee94fad4faf94b71c381591ed59',1),(27,'713f14e8a94440fcb39ccf49ce527bff',3),(29,'1367ea2f31d745bf868518bd40d6f567',3),(30,'7ae559fa9f46493fb2808126c3e0c635',3),(34,'a024d44e787c46cb9bea1d039d231cc3',3),(35,'f87c4924469b4e4380007d97545783a7',3),(36,'2e2f65327a9042b98238cdd6d9313dfd',5);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `UserId` varchar(36) NOT NULL COMMENT '编号',
  `UserName` varchar(50) NOT NULL COMMENT '用户名',
  `UserPASSWORD` varchar(50) NOT NULL DEFAULT 'eight123' COMMENT '密码',
  `UserEmail` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `UserPhone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `UserCreateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创立时间',
  `UserLastLoginTime` timestamp NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `UserPsdWrongTime` int(11) DEFAULT '0' COMMENT '密码错误次数',
  `UserIsLockout` char(2) DEFAULT '否' COMMENT '是否锁定',
  `UserLockTime` timestamp NULL DEFAULT NULL COMMENT '被锁定时间',
  `UserIsDel` varchar(2) DEFAULT '0' COMMENT '是否删除，0：有效；1：删除；',
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `IndexUnique_LoginName` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`UserId`,`UserName`,`UserPASSWORD`,`UserEmail`,`UserPhone`,`UserCreateTime`,`UserLastLoginTime`,`UserPsdWrongTime`,`UserIsLockout`,`UserLockTime`,`UserIsDel`) values ('06cd1a36b1b04f7fa68723619ded3ebf','张九','eight123','181@123.com','13783266674','2017-05-31 22:36:41',NULL,0,'否',NULL,'0'),('0a551f87b5a84d528bb589f323204051','张五','eight123','177@123.com','13783266670','2017-05-31 22:34:10',NULL,0,'否',NULL,'0'),('1367ea2f31d745bf868518bd40d6f567','咨询师三','1234567','engiht@163.com','13678956654','2017-06-06 09:20:51',NULL,0,'否',NULL,'0'),('15131ee94fad4faf94b71c381591ed59','张一','eight123','175@123.com','13783266668','2017-05-31 22:32:29','2017-06-17 14:26:28',0,'否',NULL,'0'),('227ed3799994499da3d5f35891c06ef6','张七','eight123','179@123.com','13783266672','2017-05-31 22:36:00',NULL,0,'否',NULL,'0'),('2885cc6fce4f44d4869e677508b0ee11','张八','eight123','180@123.com','13783266673','2017-05-31 22:36:18',NULL,0,'否',NULL,'0'),('2e2f65327a9042b98238cdd6d9313dfd','张二','eight123','174@123.com','13783266667','2017-05-31 22:32:06','2017-06-18 07:33:53',0,'否',NULL,'0'),('713f14e8a94440fcb39ccf49ce527bff','咨询师一','eight123','128@126.com','12345566778','2017-06-06 09:19:25',NULL,0,'否',NULL,'0'),('7505a3cb82c74966ae1cd96a5fc29297','张六','eight123','178@123.com','13783266671','2017-05-31 22:34:36',NULL,0,'否',NULL,'0'),('7ae559fa9f46493fb2808126c3e0c635','咨询师四','eight123','aslkf@126.com','15937895565','2017-06-06 09:21:49',NULL,0,'否',NULL,'0'),('808337b9f9754c6bbeca0141173d845f','张三','eight123','173@123.com','13783266666','2017-05-26 00:19:45','2017-06-18 08:44:43',0,'否',NULL,'0'),('90010e3af1d64f4d914abe960b07548b','张十','eight123','182@123.com','13783266675','2017-05-31 22:37:06',NULL,0,'否',NULL,'0'),('a024d44e787c46cb9bea1d039d231cc3','咨询师二','eight123','126@126.com','15978955598','2017-06-06 09:20:19',NULL,0,'否',NULL,'0'),('a54f6885e718407b89156c73c4646e1a','张四','eight123','176@123.com','13783266669','2017-05-31 22:33:43',NULL,0,'否',NULL,'0'),('f87c4924469b4e4380007d97545783a7','张十一','eight123','183@123.com','13783266676','2017-05-31 22:37:25','2017-06-17 14:26:06',0,'否',NULL,'0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
