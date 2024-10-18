-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.2.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for demodb
DROP DATABASE IF EXISTS `demodb`;
CREATE DATABASE IF NOT EXISTS `demodb` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `demodb`;

-- Dumping structure for table demodb.candidate
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE IF NOT EXISTS `candidate` (
                                           `id` int(11) NOT NULL AUTO_INCREMENT,
    `lastName` varchar(50) NOT NULL,
    `middleName` varchar(50) DEFAULT NULL,
    `firstName` varchar(50) NOT NULL,
    `dob` date DEFAULT NULL,
    `email` varchar(100) NOT NULL,
    `address` varchar(255) DEFAULT NULL,
    `phone` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table demodb.candidateskill
DROP TABLE IF EXISTS `candidateskill`;
CREATE TABLE IF NOT EXISTS `candidateskill` (
                                                `candidateId` int(11) NOT NULL,
    `skillId` int(11) NOT NULL,
    `skillLevel` int(11) DEFAULT NULL,
    PRIMARY KEY (`candidateId`,`skillId`),
    KEY `skillId` (`skillId`),
    CONSTRAINT `candidateskill_ibfk_1` FOREIGN KEY (`candidateId`) REFERENCES `candidate` (`id`) ON DELETE CASCADE,
    CONSTRAINT `candidateskill_ibfk_2` FOREIGN KEY (`skillId`) REFERENCES `skill` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table demodb.job
DROP TABLE IF EXISTS `job`;
CREATE TABLE IF NOT EXISTS `job` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT,
    `description` varchar(50) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table demodb.jobskill
DROP TABLE IF EXISTS `jobskill`;
CREATE TABLE IF NOT EXISTS `jobskill` (
                                          `jobId` int(11) NOT NULL,
    `skillId` int(11) NOT NULL,
    `requiredLevel` int(11) DEFAULT NULL,
    PRIMARY KEY (`jobId`,`skillId`),
    KEY `skillId` (`skillId`),
    CONSTRAINT `jobskill_ibfk_1` FOREIGN KEY (`jobId`) REFERENCES `job` (`id`) ON DELETE CASCADE,
    CONSTRAINT `jobskill_ibfk_2` FOREIGN KEY (`skillId`) REFERENCES `skill` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table demodb.skill
DROP TABLE IF EXISTS `skill`;
CREATE TABLE IF NOT EXISTS `skill` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
    `skillName` varchar(50) NOT NULL,
    `description` varchar(50) DEFAULT NULL,
    `field` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
