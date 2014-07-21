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
-- Table structure for table `TimeSlot`
--

DROP TABLE IF EXISTS `TimeSlot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TimeSlot` (
  `GTID` char(9) NOT NULL,
  `Time` varchar(5) NOT NULL,
  `Semester` varchar(9) NOT NULL,
  `Weekday` varchar(9) NOT NULL,
  PRIMARY KEY (`GTID`,`Time`,`Weekday`,`Semester`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TimeSlot`
--

LOCK TABLES `TimeSlot` WRITE;
/*!40000 ALTER TABLE `TimeSlot` DISABLE KEYS */;
INSERT INTO `TimeSlot` VALUES ('700000015','1PM','Fall','Friday'),('700000015','1PM','Spring','Friday'),('700000015','1PM','Summer','Friday'),('700000015','1PM','Fall','Monday'),('700000015','1PM','Spring','Monday'),('700000015','1PM','Summer','Monday'),('700000015','1PM','Fall','Wednesday'),('700000015','1PM','Spring','Wednesday'),('700000015','1PM','Summer','Wednesday'),('700000016','10AM','Summer','Friday'),('700000016','10AM','Summer','Monday'),('700000016','10AM','Fall','Tuesday'),('700000016','10AM','Spring','Tuesday'),('700000016','10AM','Summer','Wednesday'),('700000016','11AM','Fall','Tuesday'),('700000016','11AM','Spring','Tuesday'),('700000016','12PM','Fall','Tuesday'),('700000016','12PM','Spring','Tuesday'),('700000016','3PM','Fall','Friday'),('700000016','3PM','Spring','Friday'),('700000016','3PM','Fall','Monday'),('700000016','3PM','Spring','Monday'),('700000016','3PM','Fall','Wednesday'),('700000016','3PM','Spring','Wednesday'),('700000017','10AM','Fall','Thursday'),('700000017','10AM','Spring','Thursday'),('700000017','10AM','Fall','Tuesday'),('700000017','10AM','Spring','Tuesday'),('700000017','11AM','Fall','Thursday'),('700000017','11AM','Spring','Thursday'),('700000017','11AM','Fall','Tuesday'),('700000017','11AM','Spring','Tuesday'),('700000017','12PM','Fall','Thursday'),('700000017','12PM','Spring','Thursday'),('700000017','12PM','Fall','Tuesday'),('700000017','12PM','Spring','Tuesday');
/*!40000 ALTER TABLE `TimeSlot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-21 16:00:33
