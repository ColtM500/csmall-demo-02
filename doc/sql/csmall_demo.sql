/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.33-log : Database - csmall_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`csmall_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `csmall_db`;

/*Table structure for table `cart_tbl` */

DROP TABLE IF EXISTS `cart_tbl`;

CREATE TABLE `cart_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `commodity_code` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `price` int(11) DEFAULT '0' COMMENT '商品单价',
  `count` int(11) DEFAULT '0' COMMENT '购买数量',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `cart_tbl` */

insert  into `cart_tbl`(`id`,`commodity_code`,`price`,`count`,`user_id`) values (1,'PU201',500,10,'UU100');
insert  into `cart_tbl`(`id`,`commodity_code`,`price`,`count`,`user_id`) values (2,'PC100',500,10,'UU100');

/*Table structure for table `order_tbl` */

DROP TABLE IF EXISTS `order_tbl`;

CREATE TABLE `order_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `commodity_code` varchar(255) DEFAULT NULL COMMENT '商品编码,也可以是商品id',
  `count` int(11) DEFAULT '0' COMMENT '购买这个商品的数量',
  `money` int(11) DEFAULT '0' COMMENT '订单金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `order_tbl` */

insert  into `order_tbl`(`id`,`user_id`,`commodity_code`,`count`,`money`) values (22,'UU100','PU201',10,200);
insert  into `order_tbl`(`id`,`user_id`,`commodity_code`,`count`,`money`) values (23,'UU100','PU201',10,200);
insert  into `order_tbl`(`id`,`user_id`,`commodity_code`,`count`,`money`) values (24,'UU100','PU201',10,200);
insert  into `order_tbl`(`id`,`user_id`,`commodity_code`,`count`,`money`) values (25,'UU100','PU201',10,200);

/*Table structure for table `stock_tbl` */

DROP TABLE IF EXISTS `stock_tbl`;

CREATE TABLE `stock_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commodity_code` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `count` int(11) DEFAULT '0' COMMENT '商品库存',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `stock_tbl` */

insert  into `stock_tbl`(`id`,`commodity_code`,`count`) values (1,'PU201',1000);
insert  into `stock_tbl`(`id`,`commodity_code`,`count`) values (2,'PC100',1000);
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
