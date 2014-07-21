CREATE DATABASE  IF NOT EXISTS `cs4400_Group_30` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cs4400_Group_30`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: cs4400_Group_30
-- ------------------------------------------------------
-- Server version	5.1.73

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Rates`
--

DROP TABLE IF EXISTS `Rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rates` (
  `UGTID` char(9) NOT NULL,
  `TGTID` char(9) NOT NULL,
  `School` varchar(20) NOT NULL,
  `Number` varchar(10) NOT NULL,
  `Semester` varchar(9) NOT NULL,
  `Description` text,
  `Rating` int(11) NOT NULL,
  PRIMARY KEY (`UGTID`,`School`,`Number`),
  KEY `TGTID` (`TGTID`),
  KEY `School` (`School`,`Number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rates`
--

LOCK TABLES `Rates` WRITE;
/*!40000 ALTER TABLE `Rates` DISABLE KEYS */;
INSERT INTO `Rates` VALUES ('700000005','700000015','CS','4400','Fall','This tutor was awesome!',1),('700000012','700000017','CS','1331','Spring','This tutor was good.',2),('700000010','700000016','CS','1332','Spring','This tutor was okay.',3),('700000006','700000016','CS','3600','Fall','This tutor was okay.',3),('700000008','700000016','CS','4400','Fall','This tutor was awful!',4),('700000010','700000017','CS','1331','Fall','This tutor was awesome!',1),('700000011','700000017','CS','2340','Fall','This tutor was cool.',2),('700000009','700000017','CS','1331','Fall','This tutor was good.',2),('700000014','700000017','CS','2340','Spring','This tutor was awful!',4),('700000009','700000016','CS','3600','Spring','This tutor was awesome!',1),('700000011','700000016','CS','4400','Spring','This tutor was cool.',2),('700000006','700000015','CS','4400','Summer','This tutor was awesome!',1);
/*!40000 ALTER TABLE `Rates` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-21 16:00:22
