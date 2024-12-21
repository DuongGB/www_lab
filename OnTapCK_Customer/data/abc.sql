-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.2.3-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for ck1_customer_www
CREATE DATABASE IF NOT EXISTS `ck1_customer_www` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `ck1_customer_www`;

-- Dumping structure for table ck1_customer_www.account
CREATE TABLE IF NOT EXISTS `account` (
  `acc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acc_number` double DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `cust_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `FK9y85nxkfpucu706yjrnxu7u7j` (`cust_id`),
  CONSTRAINT `FK9y85nxkfpucu706yjrnxu7u7j` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ck1_customer_www.account: ~9 rows (approximately)
INSERT INTO `account` (`acc_id`, `acc_number`, `status`, `cust_id`) VALUES
	(1, 568.8, 2, 1),
	(2, 808.5, 2, 1),
	(3, 546.89, 1, 1),
	(4, 722.64, 2, 2),
	(5, 883.43, 1, 2),
	(6, 467.19, 2, 2),
	(7, 915.76, 1, 3),
	(8, 227.64, 1, 3),
	(9, 994.9, 2, 3);

-- Dumping structure for table ck1_customer_www.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_address` varchar(255) DEFAULT NULL,
  `cust_dob` date DEFAULT NULL,
  `cust_email` varchar(255) DEFAULT NULL,
  `cust_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ck1_customer_www.customer: ~3 rows (approximately)
INSERT INTO `customer` (`cust_id`, `cust_address`, `cust_dob`, `cust_email`, `cust_name`) VALUES
	(1, 'Suite 380 4752 Nicolas Glens, Elfriedaton, VA 01271', '2000-07-02', 'darnell.schoen@yahoo.com', 'Harlan Konopelski V'),
	(2, 'Suite 590 9027 Vandervort Camp, Groverchester, AR 74793', '1991-03-30', 'tyesha.smith@gmail.com', 'Mohammad Bayer'),
	(3, 'Apt. 178 54882 Danilo Mountains, Gerholdmouth, RI 53541', '1991-05-26', 'norbert.crona@hotmail.com', 'Antony Conroy');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
