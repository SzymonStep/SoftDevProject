-- MySQL dump 10.13  Distrib 9.2.0, for macos15 (arm64)
--
-- Host: localhost    Database: SalesSystem
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `customerId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'John','Doe','123 Main St','john.doe@example.com','0864567890'),(2,'Mary','Smith','456 Main St','MarySmith@gmail.com','0851234567'),(3,'Ava','Mar','43 Meadows Street','AvaMar@outlook.com','0891746734'),(4,'Stephen','Strange','89 Mellow Avenue','StephanStrange@gmail.com','0861846482'),(5,'Gregory','House','23 Forest Park','GregoryHouse@gmail.xom','0871658374');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipment`
--

DROP TABLE IF EXISTS `Equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Equipment` (
  `equipmentId` int NOT NULL AUTO_INCREMENT,
  `equipmentName` varchar(50) NOT NULL,
  `equipmentType` varchar(50) NOT NULL,
  `equipmentSpecifications` text NOT NULL,
  `quantityAvailable` int NOT NULL,
  `equipmentPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`equipmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipment`
--

LOCK TABLES `Equipment` WRITE;
/*!40000 ALTER TABLE `Equipment` DISABLE KEYS */;
INSERT INTO `Equipment` VALUES (1,'ECG Machine','Diagnostic','2-lead, portable',10,1200.00),(2,'X-ray Machine','Imaging','Digital, wall-mounted',5,10000.00),(3,'Infusion Pump','Therapeutic','Volume control, touchscreen',15,3000.00),(4,'Defibrillator','Emergency','Biphasic, AED mode',8,5000.00),(5,'Ventilator','Life Support','ICU type, battery backup',6,15000.00);
/*!40000 ALTER TABLE `Equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FaultyItems`
--

DROP TABLE IF EXISTS `FaultyItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FaultyItems` (
  `faultId` int NOT NULL AUTO_INCREMENT,
  `equipmentId` int NOT NULL,
  `batchNumber` varchar(50) NOT NULL,
  `faultDescription` text NOT NULL,
  `reportedDate` datetime NOT NULL,
  PRIMARY KEY (`faultId`),
  KEY `equipmentId` (`equipmentId`),
  CONSTRAINT `faultyitems_ibfk_1` FOREIGN KEY (`equipmentId`) REFERENCES `Equipment` (`equipmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FaultyItems`
--

LOCK TABLES `FaultyItems` WRITE;
/*!40000 ALTER TABLE `FaultyItems` DISABLE KEYS */;
INSERT INTO `FaultyItems` VALUES (1,1,'101','Power issue','2025-02-27 00:00:00'),(2,2,'102','Image distortion','2025-04-06 13:00:00'),(3,3,'103','Pump jammed','2025-04-04 15:00:00'),(4,4,'104','Battery not charging','2025-04-03 16:30:00'),(5,5,'105','Alarm malfunction','2025-04-02 17:00:00');
/*!40000 ALTER TABLE `FaultyItems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `customerId` int NOT NULL,
  `orderDate` datetime NOT NULL,
  `totalAmount` decimal(10,2) DEFAULT NULL,
  `orderStatus` varchar(50) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `customerId` (`customerId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `Customer` (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,1,'2025-04-07 00:00:00',150.00,'Shipped'),(2,1,'2025-04-07 00:00:00',150.00,'Shipped'),(3,1,'2025-02-27 00:00:00',9.99,'Processing'),(4,1,'2025-02-27 00:00:00',9.99,'Processing'),(5,1,'2025-02-27 00:00:00',9.99,'Processing'),(6,1,'2025-02-27 00:00:00',9.99,'Processing'),(7,1,'2025-02-27 00:00:00',9.99,'Processing'),(8,1,'2025-03-04 00:00:00',9.99,'Processing'),(9,1,'2025-03-04 00:00:00',9.99,'Processing');
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `staffId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `staffRole` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  PRIMARY KEY (`staffId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (1,'Alice','Smith','Manager','alice.smith@example.com','987654321'),(2,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(3,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(4,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(5,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(6,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(7,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(8,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(9,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(10,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(11,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(12,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(13,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(14,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(15,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(16,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(17,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(18,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567'),(19,'Sarah','Connor','Manager','sarah.connor@example.com','0851234567');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-08 17:33:00
