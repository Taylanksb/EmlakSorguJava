-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydatabase
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` int NOT NULL,
  `city_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Adana'),(2,'Adıyaman'),(3,'Afyonkarahisar'),(4,'Ağrı'),(5,'Amasya'),(6,'Ankara'),(7,'Antalya'),(8,'Artvin'),(9,'Aydın'),(10,'Balıkesir'),(11,'Bilecik'),(12,'Bingöl'),(13,'Bitlis'),(14,'Bolu'),(15,'Burdur'),(16,'Bursa'),(17,'Çanakkale'),(18,'Çankırı'),(19,'Çorum'),(20,'Denizli'),(21,'Diyarbakır'),(22,'Edirne'),(23,'Elazığ'),(24,'Erzincan'),(25,'Erzurum'),(26,'Eskişehir'),(27,'Gaziantep'),(28,'Giresun'),(29,'Gümüşhane'),(30,'Hakkari'),(31,'Hatay'),(32,'Isparta'),(33,'Mersin'),(34,'İstanbul'),(35,'İzmir'),(36,'Kars'),(37,'Kastamonu'),(38,'Kayseri'),(39,'Kırklareli'),(40,'Kırşehir'),(41,'Kocaeli'),(42,'Konya'),(43,'Kütahya'),(44,'Malatya'),(45,'Manisa'),(46,'Kahramanmaraş'),(47,'Mardin'),(48,'Muğla'),(49,'Muş'),(50,'Nevşehir'),(51,'Niğde'),(52,'Ordu'),(53,'Rize'),(54,'Sakarya'),(55,'Samsun'),(56,'Siirt'),(57,'Sinop'),(58,'Sivas'),(59,'Tekirdağ'),(60,'Tokat'),(61,'Trabzon'),(62,'Tunceli'),(63,'Şanlıurfa'),(64,'Uşak'),(65,'Van'),(66,'Yozgat'),(67,'Zonguldak'),(68,'Aksaray'),(69,'Bayburt'),(70,'Karaman'),(71,'Kırıkkale'),(72,'Batman'),(73,'Şırnak'),(74,'Bartın'),(75,'Ardahan'),(76,'Iğdır'),(77,'Yalova'),(78,'Karabük'),(79,'Kilis'),(80,'Osmaniye'),(81,'Düzce');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `houses`
--

DROP TABLE IF EXISTS `houses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` varchar(50) DEFAULT NULL,
  `heat` varchar(50) DEFAULT NULL,
  `room` varchar(50) DEFAULT NULL,
  `volume` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `village` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `image` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `houses`
--

LOCK TABLES `houses` WRITE;
/*!40000 ALTER TABLE `houses` DISABLE KEYS */;
/*!40000 ALTER TABLE `houses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin'),(14,'onur','123'),(15,'admin2','admin2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-24 20:18:35
