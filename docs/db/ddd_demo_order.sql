-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.40 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ssm_test 的数据库结构
CREATE DATABASE IF NOT EXISTS `ssm_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ssm_test`;


-- 导出  表 ssm_test.ddd_order 结构
CREATE TABLE IF NOT EXISTS `ddd_order` (
  `id` bigint(20) NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  `create_time` datetime NOT NULL,
  `order_status` int(11) NOT NULL,
  `discount_price` decimal(10,0) NOT NULL,
  `user_id` int(11) NOT NULL,
  `vendor_id` bigint(20) NOT NULL,
  `vendor_name` varchar(20) NOT NULL,
  `delivery_name` varchar(20) NOT NULL,
  `delivery_phone` varchar(20) NOT NULL,
  `delivery_address` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 ssm_test.ddd_order_items 结构
CREATE TABLE IF NOT EXISTS `ddd_order_items` (
  `order_id` bigint(20) NOT NULL,
  `sku_id` bigint(20) NOT NULL,
  `number` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
