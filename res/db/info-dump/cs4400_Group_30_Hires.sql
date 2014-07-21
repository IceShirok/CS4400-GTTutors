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
-- Table structure for table `Hires`
--

DROP TABLE IF EXISTS `Hires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hires` (
  `GTID` char(9) NOT NULL,
  `School` varchar(20) NOT NULL,
  `Number` varchar(10) NOT NULL,
  `Time` varchar(5) NOT NULL,
  `Semester` varchar(9) NOT NULL,
  `Weekday` varchar(9) NOT NULL,
  PRIMARY KEY (`GTID`,`School`,`Number`,`Time`,`Weekday`),
  KEY `School` (`School`,`Number`),
  KEY `Time` (`Time`,`Weekday`,`Semester`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hires`
--

LOCK TABLES `Hires` WRITE;
/*!40000 ALTER TABLE `Hires` DISABLE KEYS */;
INSERT INTO `Hires` VALUES ('700000005','CS','4400','1PM','Fall','Monday'),('700000006','CS','3600','10AM','Fall','Tuesday'),('700000007','CS','1332','3PM','Fall','Wednesday'),('700000008','CS','4400','3PM','Fall','Monday'),('700000009','CS','1331','10AM','Fall','Thursday'),('700000010','CS','1331','11AM','Fall','Thursday'),('700000011','CS','2340','12PM','Fall','Tuesday'),('700000008','CS','4400','1PM','Spring','Monday'),('700000009','CS','3600','10AM','Spring','Tuesday'),('700000010','CS','1332','3PM','Spring','Wednesday'),('700000011','CS','4400','3PM','Spring','Monday'),('700000012','CS','1331','10AM','Spring','Thursday'),('700000013','CS','1331','11AM','Spring','Thursday'),('700000014','CS','2340','12PM','Spring','Tuesday'),('700000006','CS','4400','1PM','Summer','Monday'),('700000009','CS','3600','10AM','Summer','Wednesday');
/*!40000 ALTER TABLE `Hires` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-21 16:00:26
