/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.33 : Database - dorm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dorm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dorm`;

/*Table structure for table `tb_dormbuild` */

DROP TABLE IF EXISTS `tb_dormbuild`;

CREATE TABLE `tb_dormbuild` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `disabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_dormbuild` */

insert  into `tb_dormbuild`(`id`,`name`,`remark`,`disabled`) values 
(1,'均园D','女',0),
(2,'均园E','女',0),
(3,'柳园D','女',0),
(4,'柳园E','男',0),
(5,'柳园F','男',0);

/*Table structure for table `tb_manage_dormbuild` */

DROP TABLE IF EXISTS `tb_manage_dormbuild`;

CREATE TABLE `tb_manage_dormbuild` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dormBuild_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `dormBuild_id` (`dormBuild_id`),
  CONSTRAINT `tb_manage_dormbuild_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_manage_dormbuild_ibfk_2` FOREIGN KEY (`dormBuild_id`) REFERENCES `tb_dormbuild` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_manage_dormbuild` */

insert  into `tb_manage_dormbuild`(`id`,`user_id`,`dormBuild_id`) values 
(3,1,1),
(4,1,2),
(5,1,3),
(6,1,4),
(7,1,5),
(8,64,1),
(9,65,2),
(10,66,3),
(11,67,4),
(12,68,5),
(13,69,1),
(14,69,2),
(15,69,3);

/*Table structure for table `tb_record` */

DROP TABLE IF EXISTS `tb_record`;

CREATE TABLE `tb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `data` date DEFAULT NULL COMMENT '缺勤时间',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `disabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_record_ibfk_1` (`student_id`),
  CONSTRAINT `tb_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_record` */

insert  into `tb_record`(`id`,`student_id`,`data`,`remark`,`disabled`) values 
(1,NULL,NULL,NULL,NULL),
(2,NULL,NULL,NULL,NULL),
(3,NULL,NULL,NULL,NULL),
(4,NULL,NULL,NULL,NULL),
(5,NULL,NULL,NULL,NULL),
(6,NULL,NULL,NULL,NULL),
(7,NULL,NULL,NULL,NULL),
(8,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `passWord` varchar(20) NOT NULL,
  `stu_code` varchar(20) DEFAULT NULL COMMENT '学号',
  `dorm_Code` varchar(20) DEFAULT NULL COMMENT '宿舍编号',
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL COMMENT '宿舍楼id',
  `role_id` int(11) DEFAULT NULL COMMENT '0表示admin,1表示宿舍管理员,2表示学生',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `disabled` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stu_code` (`stu_code`),
  KEY `dormBuildId` (`dormBuildId`),
  KEY `create_user_id` (`create_user_id`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`dormBuildId`) REFERENCES `tb_dormbuild` (`id`),
  CONSTRAINT `tb_user_ibfk_2` FOREIGN KEY (`create_user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`name`,`passWord`,`stu_code`,`dorm_Code`,`sex`,`tel`,`dormBuildId`,`role_id`,`create_user_id`,`disabled`) values 
(1,'岳川','root','root',NULL,NULL,NULL,NULL,0,NULL,0),
(5,'岳川','admin','admin','f115','男','15629706495',NULL,0,NULL,0),
(64,'a','1','2',NULL,'女','13713611111',NULL,1,1,0),
(65,'b','1','2.0191025E7',NULL,'女','13713611112',NULL,1,1,0),
(66,'c','1','2.0191026E7',NULL,'女','13713611113',NULL,1,1,0),
(67,'d','1','5',NULL,'男','13713611114',NULL,1,1,0),
(68,'e','1','2.0191028E7',NULL,'男','13713611115',NULL,1,1,0),
(69,'f','1','7',NULL,'女','13713611116',NULL,1,1,0),
(70,'czw','1','19240194','101-1','女','13713610000',3,2,1,0),
(71,'wcq','1','19240226','201-2','女','13713610001',3,2,1,0),
(72,'wxq','1','19240206','301-1','女','13713610002',2,2,1,0),
(73,'zyc','4567','19240227','401-2','男','13713610003',5,2,1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
