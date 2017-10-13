/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.28 : Database - springmvc_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springmvc_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springmvc_demo`;

/*Table structure for table `log` */

CREATE TABLE `log` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `url` varchar(20) NOT NULL COMMENT '请求url',
  `user_id` varchar(10) DEFAULT NULL COMMENT '用户Id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `log` */

insert  into `log`(`id`,`url`,`user_id`,`create_time`,`update_time`,`del_flag`) values (1,'/user/login.do',NULL,'2017-10-10 17:02:41','0000-00-00 00:00:00',0),(2,'/user/queryUser.do','user','2017-10-10 17:02:41','0000-00-00 00:00:00',0),(3,'/user/logout.do','user','2017-10-10 17:03:14','0000-00-00 00:00:00',0),(4,'/user/login.do',NULL,'2017-10-10 17:03:32','0000-00-00 00:00:00',0),(5,'/user/queryUser.do','admin','2017-10-10 17:03:32','0000-00-00 00:00:00',0),(6,'/user/login.do',NULL,'2017-10-10 17:32:45','0000-00-00 00:00:00',0),(7,'/user/queryUser.do','admin','2017-10-10 17:32:45','0000-00-00 00:00:00',0),(8,'/user/login.do','admin','2017-10-10 17:33:49','0000-00-00 00:00:00',0);

/*Table structure for table `log_his` */

CREATE TABLE `log_his` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `url` varchar(20) NOT NULL COMMENT '请求url',
  `user_id` varchar(10) DEFAULT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新日期',
  `del_flag` int(1) NOT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `log_his` */

/*Table structure for table `user` */

CREATE TABLE `user` (
  `user_id` varchar(10) NOT NULL COMMENT '用户id',
  `password` varchar(10) NOT NULL COMMENT '密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新日期',
  `del_flag` int(1) NOT NULL COMMENT '删除标识',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`password`,`create_time`,`update_time`,`del_flag`) values ('admin','admin','2017-10-10 15:48:31','2017-10-10 15:48:31',0),('user','user','2017-10-10 13:32:49','0000-00-00 00:00:00',0),('user10','user10','2017-10-10 16:28:05','0000-00-00 00:00:00',0),('user2','user2','2017-10-10 16:16:36','2017-10-10 16:16:36',0),('user3','user3','2017-10-10 16:27:13','0000-00-00 00:00:00',0),('user4','user4','2017-10-10 16:27:21','0000-00-00 00:00:00',0),('user5','user5','2017-10-10 16:27:26','0000-00-00 00:00:00',0),('user6','user6','2017-10-10 16:27:30','0000-00-00 00:00:00',0),('user7','user7','2017-10-10 16:27:33','0000-00-00 00:00:00',0),('user8','user8','2017-10-10 16:27:37','0000-00-00 00:00:00',0),('user9','user10','2017-10-10 16:27:52','2017-10-10 16:27:52',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
