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
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `GTID` char(9) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Name` varchar(30) NOT NULL,
  PRIMARY KEY (`GTID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES ('700000000','Bob00@gatech.edu','bob 00'),('700000001','Bob01@gatech.edu','bob 01'),('700000002','Bob02@gatech.edu','bob 02'),('700000003','Bob03@gatech.edu','bob 03'),('700000004','Bob04@gatech.edu','bob 04'),('700000005','Bob05@gatech.edu','bob 05'),('700000006','Bob06@gatech.edu','bob 06'),('700000007','Bob07@gatech.edu','bob 07'),('700000008','Bob08@gatech.edu','bob 08'),('700000009','Bob09@gatech.edu','bob 09'),('700000010','Bob10@gatech.edu','bob 10'),('700000011','Bob11@gatech.edu','bob 11'),('700000012','Bob12@gatech.edu','bob 12'),('700000013','Bob13@gatech.edu','bob 13'),('700000014','Bob14@gatech.edu','bob 14'),('700000015','Bob15@gatech.edu','bob 15'),('700000016','Bob16@gatech.edu','bob 16'),('700000017','Bob17@gatech.edu','bob 17'),('700000018','Bob18@gatech.edu','bob 18'),('700000019','Bob19@gatech.edu','bob 19');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-21 16:00:15
