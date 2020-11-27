-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: ws_factory
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bahan`
--

DROP TABLE IF EXISTS `bahan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bahan` (
  `idbahan` int NOT NULL AUTO_INCREMENT,
  `namabahan` varchar(45) NOT NULL,
  `jumlah` int unsigned NOT NULL,
  `tanggalkadaluarsa` datetime NOT NULL,
  PRIMARY KEY (`idbahan`),
  UNIQUE KEY `idbahan_UNIQUE` (`idbahan`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bahan`
--

LOCK TABLES `bahan` WRITE;
/*!40000 ALTER TABLE `bahan` DISABLE KEYS */;
INSERT INTO `bahan` VALUES (1,'kokoa',150,'2021-11-22 18:10:07'),(2,'susu',100,'2021-11-22 18:10:27'),(3,'tepung',458,'2020-11-27 15:14:12'),(4,'kacang tanah',295,'2021-08-10 23:59:59');
/*!40000 ALTER TABLE `bahan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coklat`
--

DROP TABLE IF EXISTS `coklat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coklat` (
  `idcoklat` int NOT NULL AUTO_INCREMENT,
  `namacoklat` varchar(45) NOT NULL,
  `jumlah` int unsigned NOT NULL,
  PRIMARY KEY (`idcoklat`),
  UNIQUE KEY `idcoklat_UNIQUE` (`idcoklat`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coklat`
--

LOCK TABLES `coklat` WRITE;
/*!40000 ALTER TABLE `coklat` DISABLE KEYS */;
INSERT INTO `coklat` VALUES (101,'Silverqueen',100);
/*!40000 ALTER TABLE `coklat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requeststock`
--

DROP TABLE IF EXISTS `requeststock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requeststock` (
  `idrequeststock` int NOT NULL AUTO_INCREMENT,
  `idcoklat` int NOT NULL,
  `jumlahrequest` int unsigned NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`idrequeststock`),
  UNIQUE KEY `idrequeststock_UNIQUE` (`idrequeststock`),
  KEY `fk_idcoklat_idx` (`idcoklat`),
  CONSTRAINT `fk_idcoklat` FOREIGN KEY (`idcoklat`) REFERENCES `coklat` (`idcoklat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requeststock`
--

LOCK TABLES `requeststock` WRITE;
/*!40000 ALTER TABLE `requeststock` DISABLE KEYS */;
/*!40000 ALTER TABLE `requeststock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resep`
--

DROP TABLE IF EXISTS `resep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resep` (
  `idcoklat` int NOT NULL,
  `idbahan` int NOT NULL,
  `jumlahbahan` int unsigned NOT NULL,
  PRIMARY KEY (`idcoklat`,`idbahan`),
  KEY `fk_idbahan_idx` (`idbahan`),
  CONSTRAINT `fk_resep_idbahan` FOREIGN KEY (`idbahan`) REFERENCES `bahan` (`idbahan`),
  CONSTRAINT `fk_resep_idcoklat` FOREIGN KEY (`idcoklat`) REFERENCES `coklat` (`idcoklat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resep`
--

LOCK TABLES `resep` WRITE;
/*!40000 ALTER TABLE `resep` DISABLE KEYS */;
INSERT INTO `resep` VALUES (101,1,10);
/*!40000 ALTER TABLE `resep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saldo`
--

DROP TABLE IF EXISTS `saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saldo` (
  `idsaldo` int NOT NULL AUTO_INCREMENT,
  `saldo` int unsigned NOT NULL,
  `saldo_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idsaldo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (1,100000,'2020-11-24 17:26:06'),(3,2000000,'2020-11-24 17:34:38'),(4,2000000,'2020-11-24 17:36:02'),(5,252525252,'2020-11-27 04:33:49');
/*!40000 ALTER TABLE `saldo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-27 20:12:16
