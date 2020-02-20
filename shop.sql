/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shop`;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `id` varchar(225) NOT NULL COMMENT '订单ID',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `pid` int(11) DEFAULT NULL COMMENT '商品ID',
  `money` bigint(10) DEFAULT NULL COMMENT '订单金额',
  `bill_date` datetime NOT NULL COMMENT '订单日期',
  `bill_status` char(4) DEFAULT '0' COMMENT '订单状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `bill` */

/*Table structure for table `pay_record` */

DROP TABLE IF EXISTS `pay_record`;

CREATE TABLE `pay_record` (
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `front_sum` int(10) DEFAULT NULL COMMENT '支付前金额',
  `last_sum` int(10) DEFAULT NULL COMMENT '支付后金额',
  `bid` varchar(225) DEFAULT NULL COMMENT '支付订单号',
  `pay_date` datetime NOT NULL COMMENT '支付时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `pay_record` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(111) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `pic_url` varchar(225) DEFAULT NULL COMMENT '商品主图',
  `pro_name` varchar(225) DEFAULT NULL COMMENT '商品名称',
  `pro_details` mediumtext COMMENT '商品详情',
  `price` bigint(10) DEFAULT NULL COMMENT '价格',
  `repertory` int(5) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `product` */

insert  into `product`(`id`,`pic_url`,`pro_name`,`pro_details`,`price`,`repertory`) values (1,'000000000125042693_3.jpg','金士顿U盘','<table class=\"el-table\"  style=\"width: 500px\">\r\n	<tr>\r\n	  <td rowspan=\"4\"><img src=\"http://localhost:82/images/000000000125042693_3.jpg\" style=\"width: 200px;height: 150px;\"/></td>\r\n	</tr>\r\n	<tr> <td>商品名称：金士顿U盘</td></tr>\r\n	<tr> <td>商品价格：79 ￥</td></tr>\r\n	<tr> <td>商品库存：500 </td></tr>\r\n</table>',79,500),(2,'281214484333514161900.jpg','朋克键盘','<table class=\"el-table\"  style=\"width: 500px\">\r\n	<tr>\r\n	  <td rowspan=\"4\"><img src=\"http://localhost:82/images/281214484333514161900.jpg\" style=\"width: 200px;height: 150px;\"/></td>\r\n	</tr>\r\n	<tr> <td>商品名称：朋克键盘</td></tr>\r\n	<tr> <td>商品价格：159 ￥</td></tr>\r\n	<tr> <td>商品库存：360 </td></tr>\r\n</table>',156,350),(3,'41808203751542942502.jpg','牧马人鼠标','<table class=\"el-table\"  style=\"width: 500px\">\r\n	<tr>\r\n	  <td rowspan=\"4\"><img src=\"http://localhost:82/images/41808203751542942502.jpg\" style=\"width: 200px;height: 150px;\"/></td>\r\n	</tr>\r\n	<tr> <td>商品名称：牧马人鼠标</td></tr>\r\n	<tr> <td>商品价格：56 ￥</td></tr>\r\n	<tr> <td>商品库存：360 </td></tr>\r\n</table>',56,360),(4,'1594729314545185663.jpg','Lenovo 笔记本','<table class=\"el-table\"  style=\"width: 500px\">\r\n	<tr>\r\n	  <td rowspan=\"4\"><img src=\"http://localhost:82/images/1594729314545185663.jpg\" style=\"width: 200px;height: 150px;\"/></td>\r\n	</tr>\r\n	<tr> <td>商品名称：Lenovo 笔记本</td></tr>\r\n	<tr> <td>商品价格：3548 ￥</td></tr>\r\n	<tr> <td>商品库存：100 </td></tr>\r\n</table>',3548,100),(5,'1943552796540572174.jpg','电竞耳机','<table class=\"el-table\"  style=\"width: 500px\">\r\n	<tr>\r\n	  <td rowspan=\"4\"><img src=\"http://localhost:82/images/1943552796540572174.jpg\" style=\"width: 200px;height: 150px;\"/></td>\r\n	</tr>\r\n	<tr> <td>商品名称：电竞耳机</td></tr>\r\n	<tr> <td>商品价格：46 ￥</td></tr>\r\n	<tr> <td>商品库存：200 </td></tr>\r\n</table>',46,200),(6,'32579141761160511181.jpg','液晶显示屏','<table class=\"el-table\"  style=\"width: 500px\">\r\n	<tr>\r\n	  <td rowspan=\"4\"><img src=\"http://localhost:82/images/32579141761160511181.jpg\" style=\"width: 200px;height: 150px;\"/></td>\r\n	</tr>\r\n	<tr> <td>商品名称：液晶显示屏</td></tr>\r\n	<tr> <td>商品价格：3000 ￥</td></tr>\r\n	<tr> <td>商品库存：50 </td></tr>\r\n</table>',3000,50);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(225) NOT NULL COMMENT '用户名',
  `password` varchar(225) NOT NULL COMMENT '密码',
  `balance` bigint(10) NOT NULL DEFAULT '10000' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `NewIndex1` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`balance`) values (1,'admin','39HL8Q37CA28EEB0A9JLTN05J8',10000),(2,'13311111111','39HL8Q37CA28EEB0A9JLTN05J8',10000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
