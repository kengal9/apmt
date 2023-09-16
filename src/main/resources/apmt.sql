CREATE DATABASE  IF NOT EXISTS `apmt` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `apmt`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: apmt
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` varchar(255) NOT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city_id` varchar(255) DEFAULT NULL,
  `country_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `door_number` decimal(19,2) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `pin_code` decimal(19,2) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `state_id` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('101592','Shri Manjunatha Nilaya','Murarjee Nagar 2nd stage','5','101','2022-11-05 13:09:45.658115',463.00,'2022-11-05 13:09:45.658115',558855.00,2,'17',_binary '','Gokul road','872746'),('840685','Shri Manjunatha Nilaya','Murarjee Nagar 2nd stage','603','101','2022-11-08 17:47:24.948967',463.00,'2022-11-08 17:47:24.948967',558855.00,1,'17',_binary '','Gokul road','662350'),('976153','Shri Manjunatha Nilaya','Murarjee Nagar 2nd stage','5','101','2022-11-14 17:34:50.082495',463.00,'2022-11-14 17:34:50.082495',558855.00,2,'17',_binary '','Gokul road','382659');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_bank`
--

DROP TABLE IF EXISTS `admin_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_bank` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_bank_id` varchar(255) DEFAULT NULL,
  `admin_bank_name` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_bank`
--

LOCK TABLES `admin_bank` WRITE;
/*!40000 ALTER TABLE `admin_bank` DISABLE KEYS */;
INSERT INTO `admin_bank` VALUES (1,'1','SBI',NULL,NULL,NULL),(2,'2','Canara Bamk',NULL,NULL,NULL),(3,'3','Karnataka Bank',NULL,NULL,NULL),(4,'4','Central Bank of India',NULL,NULL,NULL),(5,'5','Axis Bank',NULL,NULL,NULL),(6,'6','HDFC',NULL,NULL,NULL),(7,'7','ICICI',NULL,NULL,NULL);
/*!40000 ALTER TABLE `admin_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `bank_id` varchar(255) NOT NULL,
  `account_number` decimal(19,2) DEFAULT NULL,
  `admin_bank_id` bigint DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `pan_number` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES ('438363',752361841256.00,1,'GokulRoad hubli','2022-11-14 17:34:50.079560','SBIN6040','2022-11-14 17:34:50.079560','AODPH145736',2,_binary '','382659'),('733400',752369841256.00,1,'GokulRoad hubli','2022-11-08 17:47:24.948059','SBIN6040','2022-11-08 17:47:24.948059','AODPH145236',1,_binary '','662350'),('948685',752369841258.00,1,'GokulRoad hubli','2022-11-05 13:09:45.657184','SBIN6041','2022-11-05 13:09:45.657184','AODPH145237',2,_binary '','872746');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_role`
--

DROP TABLE IF EXISTS `business_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `business_role_id` varchar(255) DEFAULT NULL,
  `business_role_name` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_role`
--

LOCK TABLES `business_role` WRITE;
/*!40000 ALTER TABLE `business_role` DISABLE KEYS */;
INSERT INTO `business_role` VALUES (1,'1','student',NULL,NULL,'studentDashboard',1),(2,'2','purchaser',NULL,NULL,'purchaserDashboard',1);
/*!40000 ALTER TABLE `business_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city_id` varchar(255) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `state_id_fk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ssdrnvxqapwks1m2jddqcoap` (`state_id_fk`),
  CONSTRAINT `FK3ssdrnvxqapwks1m2jddqcoap` FOREIGN KEY (`state_id_fk`) REFERENCES `states` (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=641 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'36','Anantnag',NULL,NULL,' ',1,'15'),(2,'37','Bandipore',NULL,NULL,' ',1,'15'),(3,'38','Baramulla',NULL,NULL,' ',1,'15'),(4,'39','Budgam',NULL,NULL,' ',1,'15'),(5,'40','Doda',NULL,NULL,' ',1,'15'),(6,'41','Ganderbal',NULL,NULL,' ',1,'15'),(7,'42','Jammu',NULL,NULL,' ',1,'15'),(8,'43','Kargil',NULL,NULL,' ',1,'15'),(9,'44','Kathua',NULL,NULL,' ',1,'15'),(10,'45','Kishtwar',NULL,NULL,' ',1,'15'),(11,'46','Kulgam',NULL,NULL,' ',1,'15'),(12,'47','Kupwara',NULL,NULL,' ',1,'15'),(13,'48','Leh (null,Ladakh)',NULL,NULL,' ',1,'15'),(14,'49','Poonch',NULL,NULL,' ',1,'15'),(15,'50','Pulwama',NULL,NULL,' ',1,'15'),(16,'51','Rajouri',NULL,NULL,' ',1,'15'),(17,'52','Ramban',NULL,NULL,' ',1,'15'),(18,'53','Reasi',NULL,NULL,' ',1,'15'),(19,'54','Samba',NULL,NULL,' ',1,'15'),(20,'55','Shopian',NULL,NULL,' ',1,'15'),(21,'56','Srinagar',NULL,NULL,' ',1,'15'),(22,'57','Udhampur',NULL,NULL,' ',1,'15'),(23,'58','Bilaspur (null,Himachal Pradesh)',NULL,NULL,' ',1,'14'),(24,'59','Chamba',NULL,NULL,' ',1,'14'),(25,'60','Hamirpur (null,Himachal Pradesh)',NULL,NULL,' ',1,'14'),(26,'61','Kangra',NULL,NULL,' ',1,'14'),(27,'62','Kinnaur',NULL,NULL,' ',1,'14'),(28,'63','Kullu',NULL,NULL,' ',1,'14'),(29,'64','Lahul & Spiti',NULL,NULL,' ',1,'14'),(30,'65','Mandi',NULL,NULL,' ',1,'14'),(31,'66','Shimla',NULL,NULL,' ',1,'14'),(32,'67','Sirmaur',NULL,NULL,' ',1,'14'),(33,'68','Solan',NULL,NULL,' ',1,'14'),(34,'69','Una',NULL,NULL,' ',1,'14'),(35,'70','Amritsar',NULL,NULL,' ',1,'28'),(36,'71','Barnala',NULL,NULL,' ',1,'28'),(37,'72','Bathinda',NULL,NULL,' ',1,'28'),(38,'73','Faridkot',NULL,NULL,' ',1,'28'),(39,'74','Fatehgarh Sahib',NULL,NULL,' ',1,'28'),(40,'75','Firozpur',NULL,NULL,' ',1,'28'),(41,'76','Gurdaspur',NULL,NULL,' ',1,'28'),(42,'77','Hoshiarpur',NULL,NULL,' ',1,'28'),(43,'78','Jalandhar',NULL,NULL,' ',1,'28'),(44,'79','Kapurthala',NULL,NULL,' ',1,'28'),(45,'80','Ludhiana',NULL,NULL,' ',1,'28'),(46,'81','Mansa',NULL,NULL,' ',1,'28'),(47,'82','Moga',NULL,NULL,' ',1,'28'),(48,'83','Muktsar',NULL,NULL,' ',1,'28'),(49,'84','Patiala',NULL,NULL,' ',1,'28'),(50,'85','Rupnagar (null,Ropar)',NULL,NULL,' ',1,'28'),(51,'86','Sahibzada Ajit Singh Nagar (null,Mohali)',NULL,NULL,' ',1,'28'),(52,'87','Sangrur',NULL,NULL,' ',1,'28'),(53,'88','Shahid Bhagat Singh Nagar (null,Nawanshahr)',NULL,NULL,' ',1,'28'),(54,'89','Tarn Taran',NULL,NULL,' ',1,'28'),(55,'90','Chandigarh',NULL,NULL,' ',1,'6'),(56,'91','Almora',NULL,NULL,' ',1,'34'),(57,'92','Bageshwar',NULL,NULL,' ',1,'34'),(58,'93','Chamoli',NULL,NULL,' ',1,'34'),(59,'94','Champawat',NULL,NULL,' ',1,'34'),(60,'95','Dehradun',NULL,NULL,' ',1,'34'),(61,'96','Haridwar',NULL,NULL,' ',1,'34'),(62,'97','Nainital',NULL,NULL,' ',1,'34'),(63,'98','Pauri Garhwal',NULL,NULL,' ',1,'34'),(64,'99','Pithoragarh',NULL,NULL,' ',1,'34'),(65,'100','Rudraprayag',NULL,NULL,' ',1,'34'),(66,'101','Tehri Garhwal',NULL,NULL,' ',1,'34'),(67,'102','Udham Singh Nagar',NULL,NULL,' ',1,'34'),(68,'103','Uttarkashi',NULL,NULL,' ',1,'34'),(69,'104','Ambala',NULL,NULL,' ',1,'13'),(70,'105','Bhiwani',NULL,NULL,' ',1,'13'),(71,'106','Faridabad',NULL,NULL,' ',1,'13'),(72,'107','Fatehabad',NULL,NULL,' ',1,'13'),(73,'108','Gurgaon',NULL,NULL,' ',1,'13'),(74,'109','Hisar',NULL,NULL,' ',1,'13'),(75,'110','Jhajjar',NULL,NULL,' ',1,'13'),(76,'111','Jind',NULL,NULL,' ',1,'13'),(77,'112','Kaithal',NULL,NULL,' ',1,'13'),(78,'113','Karnal',NULL,NULL,' ',1,'13'),(79,'114','Kurukshetra',NULL,NULL,' ',1,'13'),(80,'115','Mahendragarh',NULL,NULL,' ',1,'13'),(81,'116','Mewat',NULL,NULL,' ',1,'13'),(82,'117','Palwal',NULL,NULL,' ',1,'13'),(83,'118','Panchkula',NULL,NULL,' ',1,'13'),(84,'119','Panipat',NULL,NULL,' ',1,'13'),(85,'120','Rewari',NULL,NULL,' ',1,'13'),(86,'121','Rohtak',NULL,NULL,' ',1,'13'),(87,'122','Sirsa',NULL,NULL,' ',1,'13'),(88,'123','Sonipat',NULL,NULL,' ',1,'13'),(89,'124','Yamuna Nagar',NULL,NULL,' ',1,'13'),(90,'125','Central Delhi',NULL,NULL,' ',1,'10'),(91,'126','East Delhi',NULL,NULL,' ',1,'10'),(92,'127','New Delhi',NULL,NULL,' ',1,'10'),(93,'128','North Delhi',NULL,NULL,' ',1,'10'),(94,'129','North East Delhi',NULL,NULL,' ',1,'10'),(95,'130','North West Delhi',NULL,NULL,' ',1,'10'),(96,'131','South Delhi',NULL,NULL,' ',1,'10'),(97,'132','South West Delhi',NULL,NULL,' ',1,'10'),(98,'133','West Delhi',NULL,NULL,' ',1,'10'),(99,'134','Ajmer',NULL,NULL,' ',1,'29'),(100,'135','Alwar',NULL,NULL,' ',1,'29'),(101,'136','Banswara',NULL,NULL,' ',1,'29'),(102,'137','Baran',NULL,NULL,' ',1,'29'),(103,'138','Barmer',NULL,NULL,' ',1,'29'),(104,'139','Bharatpur',NULL,NULL,' ',1,'29'),(105,'140','Bhilwara',NULL,NULL,' ',1,'29'),(106,'141','Bikaner',NULL,NULL,' ',1,'29'),(107,'142','Bundi',NULL,NULL,' ',1,'29'),(108,'143','Chittorgarh',NULL,NULL,' ',1,'29'),(109,'144','Churu',NULL,NULL,' ',1,'29'),(110,'145','Dausa',NULL,NULL,' ',1,'29'),(111,'146','Dholpur',NULL,NULL,' ',1,'29'),(112,'147','Dungarpur',NULL,NULL,' ',1,'29'),(113,'148','Ganganagar',NULL,NULL,' ',1,'29'),(114,'149','Hanumangarh',NULL,NULL,' ',1,'29'),(115,'150','Jaipur',NULL,NULL,' ',1,'29'),(116,'151','Jaisalmer',NULL,NULL,' ',1,'29'),(117,'152','Jalor',NULL,NULL,' ',1,'29'),(118,'153','Jhalawar',NULL,NULL,' ',1,'29'),(119,'154','Jhunjhunu',NULL,NULL,' ',1,'29'),(120,'155','Jodhpur',NULL,NULL,' ',1,'29'),(121,'156','Karauli',NULL,NULL,' ',1,'29'),(122,'157','Kota',NULL,NULL,' ',1,'29'),(123,'158','Nagaur',NULL,NULL,' ',1,'29'),(124,'159','Pali',NULL,NULL,' ',1,'29'),(125,'160','Pratapgarh (null,Rajasthan)',NULL,NULL,' ',1,'29'),(126,'161','Rajsamand',NULL,NULL,' ',1,'29'),(127,'162','Sawai Madhopur',NULL,NULL,' ',1,'29'),(128,'163','Sikar',NULL,NULL,' ',1,'29'),(129,'164','Sirohi',NULL,NULL,' ',1,'29'),(130,'165','Tonk',NULL,NULL,' ',1,'29'),(131,'166','Udaipur',NULL,NULL,' ',1,'29'),(132,'167','Agra',NULL,NULL,' ',1,'33'),(133,'168','Aligarh',NULL,NULL,' ',1,'33'),(134,'169','Allahabad',NULL,NULL,' ',1,'33'),(135,'170','Ambedkar Nagar',NULL,NULL,' ',1,'33'),(136,'171','Auraiya',NULL,NULL,' ',1,'33'),(137,'172','Azamgarh',NULL,NULL,' ',1,'33'),(138,'173','Bagpat',NULL,NULL,' ',1,'33'),(139,'174','Bahraich',NULL,NULL,' ',1,'33'),(140,'175','Ballia',NULL,NULL,' ',1,'33'),(141,'176','Balrampur',NULL,NULL,' ',1,'33'),(142,'177','Banda',NULL,NULL,' ',1,'33'),(143,'178','Barabanki',NULL,NULL,' ',1,'33'),(144,'179','Bareilly',NULL,NULL,' ',1,'33'),(145,'180','Basti',NULL,NULL,' ',1,'33'),(146,'181','Bijnor',NULL,NULL,' ',1,'33'),(147,'182','Budaun',NULL,NULL,' ',1,'33'),(148,'183','Bulandshahr',NULL,NULL,' ',1,'33'),(149,'184','Chandauli',NULL,NULL,' ',1,'33'),(150,'185','Chitrakoot',NULL,NULL,' ',1,'33'),(151,'186','Deoria',NULL,NULL,' ',1,'33'),(152,'187','Etah',NULL,NULL,' ',1,'33'),(153,'188','Etawah',NULL,NULL,' ',1,'33'),(154,'189','Faizabad',NULL,NULL,' ',1,'33'),(155,'190','Farrukhabad',NULL,NULL,' ',1,'33'),(156,'191','Fatehpur',NULL,NULL,' ',1,'33'),(157,'192','Firozabad',NULL,NULL,' ',1,'33'),(158,'193','Gautam Buddha Nagar',NULL,NULL,' ',1,'33'),(159,'194','Ghaziabad',NULL,NULL,' ',1,'33'),(160,'195','Ghazipur',NULL,NULL,' ',1,'33'),(161,'196','Gonda',NULL,NULL,' ',1,'33'),(162,'197','Gorakhpur',NULL,NULL,' ',1,'33'),(163,'198','Hamirpur',NULL,NULL,' ',1,'33'),(164,'199','Hardoi',NULL,NULL,' ',1,'33'),(165,'200','Hathras',NULL,NULL,' ',1,'33'),(166,'201','Jalaun',NULL,NULL,' ',1,'33'),(167,'202','Jaunpur',NULL,NULL,' ',1,'33'),(168,'203','Jhansi',NULL,NULL,' ',1,'33'),(169,'204','Jyotiba Phule Nagar',NULL,NULL,' ',1,'33'),(170,'205','Kannauj',NULL,NULL,' ',1,'33'),(171,'206','Kanpur Dehat',NULL,NULL,' ',1,'33'),(172,'207','Kanpur Nagar',NULL,NULL,' ',1,'33'),(173,'208','Kanshiram Nagar',NULL,NULL,' ',1,'33'),(174,'209','Kaushambi',NULL,NULL,' ',1,'33'),(175,'210','Kheri',NULL,NULL,' ',1,'33'),(176,'211','Kushinagar',NULL,NULL,' ',1,'33'),(177,'212','Lalitpur',NULL,NULL,' ',1,'33'),(178,'213','Lucknow',NULL,NULL,' ',1,'33'),(179,'214','Maharajganj',NULL,NULL,' ',1,'33'),(180,'215','Mahoba',NULL,NULL,' ',1,'33'),(181,'216','Mainpuri',NULL,NULL,' ',1,'33'),(182,'217','Mathura',NULL,NULL,' ',1,'33'),(183,'218','Mau',NULL,NULL,' ',1,'33'),(184,'219','Meerut',NULL,NULL,' ',1,'33'),(185,'220','Mirzapur',NULL,NULL,' ',1,'33'),(186,'221','Moradabad',NULL,NULL,' ',1,'33'),(187,'222','Muzaffarnagar',NULL,NULL,' ',1,'33'),(188,'223','Pilibhit',NULL,NULL,' ',1,'33'),(189,'224','Pratapgarh',NULL,NULL,' ',1,'33'),(190,'225','Rae Bareli',NULL,NULL,' ',1,'33'),(191,'226','Rampur',NULL,NULL,' ',1,'33'),(192,'227','Saharanpur',NULL,NULL,' ',1,'33'),(193,'228','Sant Kabir Nagar',NULL,NULL,' ',1,'33'),(194,'229','Sant Ravidas Nagar (null,Bhadohi)',NULL,NULL,' ',1,'33'),(195,'230','Shahjahanpur',NULL,NULL,' ',1,'33'),(196,'231','Shrawasti',NULL,NULL,' ',1,'33'),(197,'232','Siddharthnagar',NULL,NULL,' ',1,'33'),(198,'233','Sitapur',NULL,NULL,' ',1,'33'),(199,'234','Sonbhadra',NULL,NULL,' ',1,'33'),(200,'235','Sultanpur',NULL,NULL,' ',1,'33'),(201,'236','Unnao',NULL,NULL,' ',1,'33'),(202,'237','Varanasi',NULL,NULL,' ',1,'33'),(203,'238','Araria',NULL,NULL,' ',1,'5'),(204,'239','Arwal',NULL,NULL,' ',1,'5'),(205,'240','Aurangabad (null,Bihar)',NULL,NULL,' ',1,'5'),(206,'241','Banka',NULL,NULL,' ',1,'5'),(207,'242','Begusarai',NULL,NULL,' ',1,'5'),(208,'243','Bhagalpur',NULL,NULL,' ',1,'5'),(209,'244','Bhojpur',NULL,NULL,' ',1,'5'),(210,'245','Buxar',NULL,NULL,' ',1,'5'),(211,'246','Darbhanga',NULL,NULL,' ',1,'5'),(212,'247','East Champaran',NULL,NULL,' ',1,'5'),(213,'248','Gaya',NULL,NULL,' ',1,'5'),(214,'249','Gopalganj',NULL,NULL,' ',1,'5'),(215,'250','Jamui',NULL,NULL,' ',1,'5'),(216,'251','Jehanabad',NULL,NULL,' ',1,'5'),(217,'252','Kaimur (null,Bhabua)',NULL,NULL,' ',1,'5'),(218,'253','Katihar',NULL,NULL,' ',1,'5'),(219,'254','Khagaria',NULL,NULL,' ',1,'5'),(220,'255','Kishanganj',NULL,NULL,' ',1,'5'),(221,'256','Lakhisarai',NULL,NULL,' ',1,'5'),(222,'257','Madhepura',NULL,NULL,' ',1,'5'),(223,'258','Madhubani',NULL,NULL,' ',1,'5'),(224,'259','Munger',NULL,NULL,' ',1,'5'),(225,'260','Muzaffarpur',NULL,NULL,' ',1,'5'),(226,'261','Nalanda',NULL,NULL,' ',1,'5'),(227,'262','Nawada',NULL,NULL,' ',1,'5'),(228,'263','Patna',NULL,NULL,' ',1,'5'),(229,'264','Purnia',NULL,NULL,' ',1,'5'),(230,'265','Rohtas',NULL,NULL,' ',1,'5'),(231,'266','Saharsa',NULL,NULL,' ',1,'5'),(232,'267','Samastipur',NULL,NULL,' ',1,'5'),(233,'268','Saran',NULL,NULL,' ',1,'5'),(234,'269','Sheikhpura',NULL,NULL,' ',1,'5'),(235,'270','Sheohar',NULL,NULL,' ',1,'5'),(236,'271','Sitamarhi',NULL,NULL,' ',1,'5'),(237,'272','Siwan',NULL,NULL,' ',1,'5'),(238,'273','Supaul',NULL,NULL,' ',1,'5'),(239,'274','Vaishali',NULL,NULL,' ',1,'5'),(240,'275','West Champaran',NULL,NULL,' ',1,'5'),(241,'276','East Sikkim',NULL,NULL,' ',1,'30'),(242,'277','North Sikkim',NULL,NULL,' ',1,'30'),(243,'278','South Sikkim',NULL,NULL,' ',1,'30'),(244,'279','West Sikkim',NULL,NULL,' ',1,'30'),(245,'280','Anjaw',NULL,NULL,' ',1,'3'),(246,'281','Changlang',NULL,NULL,' ',1,'3'),(247,'282','Dibang Valley',NULL,NULL,' ',1,'3'),(248,'283','East Kameng',NULL,NULL,' ',1,'3'),(249,'284','East Siang',NULL,NULL,' ',1,'3'),(250,'285','Kurung Kumey',NULL,NULL,' ',1,'3'),(251,'286','Lohit',NULL,NULL,' ',1,'3'),(252,'287','Lower Dibang Valley',NULL,NULL,' ',1,'3'),(253,'288','Lower Subansiri',NULL,NULL,' ',1,'3'),(254,'289','Papum Pare',NULL,NULL,' ',1,'3'),(255,'290','Tawang',NULL,NULL,' ',1,'3'),(256,'291','Tirap',NULL,NULL,' ',1,'3'),(257,'292','Upper Siang',NULL,NULL,' ',1,'3'),(258,'293','Upper Subansiri',NULL,NULL,' ',1,'3'),(259,'294','West Kameng',NULL,NULL,' ',1,'3'),(260,'295','West Siang',NULL,NULL,' ',1,'3'),(261,'296','Dimapur',NULL,NULL,' ',1,'25'),(262,'297','Kiphire',NULL,NULL,' ',1,'25'),(263,'298','Kohima',NULL,NULL,' ',1,'25'),(264,'299','Longleng',NULL,NULL,' ',1,'25'),(265,'300','Mokokchung',NULL,NULL,' ',1,'25'),(266,'301','Mon',NULL,NULL,' ',1,'25'),(267,'302','Peren',NULL,NULL,' ',1,'25'),(268,'303','Phek',NULL,NULL,' ',1,'25'),(269,'304','Tuensang',NULL,NULL,' ',1,'25'),(270,'305','Wokha',NULL,NULL,' ',1,'25'),(271,'306','Zunheboto',NULL,NULL,' ',1,'25'),(272,'307','Bishnupur',NULL,NULL,' ',1,'22'),(273,'308','Chandel',NULL,NULL,' ',1,'22'),(274,'309','Churachandpur',NULL,NULL,' ',1,'22'),(275,'310','Imphal East',NULL,NULL,' ',1,'22'),(276,'311','Imphal West',NULL,NULL,' ',1,'22'),(277,'312','Senapati',NULL,NULL,' ',1,'22'),(278,'313','Tamenglong',NULL,NULL,' ',1,'22'),(279,'314','Thoubal',NULL,NULL,' ',1,'22'),(280,'315','Ukhrul',NULL,NULL,' ',1,'22'),(281,'316','Aizawl',NULL,NULL,' ',1,'24'),(282,'317','Champhai',NULL,NULL,' ',1,'24'),(283,'318','Kolasib',NULL,NULL,' ',1,'24'),(284,'319','Lawngtlai',NULL,NULL,' ',1,'24'),(285,'320','Lunglei',NULL,NULL,' ',1,'24'),(286,'321','Mamit',NULL,NULL,' ',1,'24'),(287,'322','Saiha',NULL,NULL,' ',1,'24'),(288,'323','Serchhip',NULL,NULL,' ',1,'24'),(289,'324','Dhalai',NULL,NULL,' ',1,'32'),(290,'325','North Tripura',NULL,NULL,' ',1,'32'),(291,'326','South Tripura',NULL,NULL,' ',1,'32'),(292,'327','West Tripura',NULL,NULL,' ',1,'32'),(293,'328','East Garo Hills',NULL,NULL,' ',1,'23'),(294,'329','East Khasi Hills',NULL,NULL,' ',1,'23'),(295,'330','Jaintia Hills',NULL,NULL,' ',1,'23'),(296,'331','Ri Bhoi',NULL,NULL,' ',1,'23'),(297,'332','South Garo Hills',NULL,NULL,' ',1,'23'),(298,'333','West Garo Hills',NULL,NULL,' ',1,'23'),(299,'334','West Khasi Hills',NULL,NULL,' ',1,'23'),(300,'335','Baksa',NULL,NULL,' ',1,'4'),(301,'336','Barpeta',NULL,NULL,' ',1,'4'),(302,'337','Bongaigaon',NULL,NULL,' ',1,'4'),(303,'338','Cachar',NULL,NULL,' ',1,'4'),(304,'339','Chirang',NULL,NULL,' ',1,'4'),(305,'340','Darrang',NULL,NULL,' ',1,'4'),(306,'341','Dhemaji',NULL,NULL,' ',1,'4'),(307,'342','Dhubri',NULL,NULL,' ',1,'4'),(308,'343','Dibrugarh',NULL,NULL,' ',1,'4'),(309,'344','Dima Hasao (null,North Cachar Hills)',NULL,NULL,' ',1,'4'),(310,'345','Goalpara',NULL,NULL,' ',1,'4'),(311,'346','Golaghat',NULL,NULL,' ',1,'4'),(312,'347','Hailakandi',NULL,NULL,' ',1,'4'),(313,'348','Jorhat',NULL,NULL,' ',1,'4'),(314,'349','Kamrup',NULL,NULL,' ',1,'4'),(315,'350','Kamrup Metropolitan',NULL,NULL,' ',1,'4'),(316,'351','Karbi Anglong',NULL,NULL,' ',1,'4'),(317,'352','Karimganj',NULL,NULL,' ',1,'4'),(318,'353','Kokrajhar',NULL,NULL,' ',1,'4'),(319,'354','Lakhimpur',NULL,NULL,' ',1,'4'),(320,'355','Morigaon',NULL,NULL,' ',1,'4'),(321,'356','Nagaon',NULL,NULL,' ',1,'4'),(322,'357','Nalbari',NULL,NULL,' ',1,'4'),(323,'358','Sivasagar',NULL,NULL,' ',1,'4'),(324,'359','Sonitpur',NULL,NULL,' ',1,'4'),(325,'360','Tinsukia',NULL,NULL,' ',1,'4'),(326,'361','Udalguri',NULL,NULL,' ',1,'4'),(327,'362','Bankura',NULL,NULL,' ',1,'35'),(328,'363','Bardhaman',NULL,NULL,' ',1,'35'),(329,'364','Birbhum',NULL,NULL,' ',1,'35'),(330,'365','Cooch Behar',NULL,NULL,' ',1,'35'),(331,'366','Dakshin Dinajpur (null,South Dinajpur)',NULL,NULL,' ',1,'35'),(332,'367','Darjiling',NULL,NULL,' ',1,'35'),(333,'368','Hooghly',NULL,NULL,' ',1,'35'),(334,'369','Howrah',NULL,NULL,' ',1,'35'),(335,'370','Jalpaiguri',NULL,NULL,' ',1,'35'),(336,'371','Kolkata',NULL,NULL,' ',1,'35'),(337,'372','Maldah',NULL,NULL,' ',1,'35'),(338,'373','Murshidabad',NULL,NULL,' ',1,'35'),(339,'374','Nadia',NULL,NULL,' ',1,'35'),(340,'375','North 24 Parganas',NULL,NULL,' ',1,'35'),(341,'376','Paschim Medinipur (null,West Midnapore)',NULL,NULL,' ',1,'35'),(342,'377','Purba Medinipur (null,East Midnapore)',NULL,NULL,' ',1,'35'),(343,'378','Puruliya',NULL,NULL,' ',1,'35'),(344,'379','South 24 Parganas',NULL,NULL,' ',1,'35'),(345,'380','Uttar Dinajpur (null,North Dinajpur)',NULL,NULL,' ',1,'35'),(346,'381','Bokaro',NULL,NULL,' ',1,'16'),(347,'382','Chatra',NULL,NULL,' ',1,'16'),(348,'383','Deoghar',NULL,NULL,' ',1,'16'),(349,'384','Dhanbad',NULL,NULL,' ',1,'16'),(350,'385','Dumka',NULL,NULL,' ',1,'16'),(351,'386','East Singhbhum',NULL,NULL,' ',1,'16'),(352,'387','Garhwa',NULL,NULL,' ',1,'16'),(353,'388','Giridih',NULL,NULL,' ',1,'16'),(354,'389','Godda',NULL,NULL,' ',1,'16'),(355,'390','Gumla',NULL,NULL,' ',1,'16'),(356,'391','Hazaribagh',NULL,NULL,' ',1,'16'),(357,'392','Jamtara',NULL,NULL,' ',1,'16'),(358,'393','Khunti',NULL,NULL,' ',1,'16'),(359,'394','Koderma',NULL,NULL,' ',1,'16'),(360,'395','Latehar',NULL,NULL,' ',1,'16'),(361,'396','Lohardaga',NULL,NULL,' ',1,'16'),(362,'397','Pakur',NULL,NULL,' ',1,'16'),(363,'398','Palamu',NULL,NULL,' ',1,'16'),(364,'399','Ramgarh',NULL,NULL,' ',1,'16'),(365,'400','Ranchi',NULL,NULL,' ',1,'16'),(366,'401','Sahibganj',NULL,NULL,' ',1,'16'),(367,'402','Seraikela-Kharsawan',NULL,NULL,' ',1,'16'),(368,'403','Simdega',NULL,NULL,' ',1,'16'),(369,'404','West Singhbhum',NULL,NULL,' ',1,'16'),(370,'405','Angul',NULL,NULL,' ',1,'26'),(371,'406','Balangir',NULL,NULL,' ',1,'26'),(372,'407','Baleswar',NULL,NULL,' ',1,'26'),(373,'408','Bargarh',NULL,NULL,' ',1,'26'),(374,'409','Bhadrak',NULL,NULL,' ',1,'26'),(375,'410','Boudh',NULL,NULL,' ',1,'26'),(376,'411','Cuttack',NULL,NULL,' ',1,'26'),(377,'412','Debagarh',NULL,NULL,' ',1,'26'),(378,'413','Dhenkanal',NULL,NULL,' ',1,'26'),(379,'414','Gajapati',NULL,NULL,' ',1,'26'),(380,'415','Ganjam',NULL,NULL,' ',1,'26'),(381,'416','Jagatsinghapur',NULL,NULL,' ',1,'26'),(382,'417','Jajapur',NULL,NULL,' ',1,'26'),(383,'418','Jharsuguda',NULL,NULL,' ',1,'26'),(384,'419','Kalahandi',NULL,NULL,' ',1,'26'),(385,'420','Kandhamal',NULL,NULL,' ',1,'26'),(386,'421','Kendrapara',NULL,NULL,' ',1,'26'),(387,'422','Kendujhar',NULL,NULL,' ',1,'26'),(388,'423','Khordha',NULL,NULL,' ',1,'26'),(389,'424','Koraput',NULL,NULL,' ',1,'26'),(390,'425','Malkangiri',NULL,NULL,' ',1,'26'),(391,'426','Mayurbhanj',NULL,NULL,' ',1,'26'),(392,'427','Nabarangapur',NULL,NULL,' ',1,'26'),(393,'428','Nayagarh',NULL,NULL,' ',1,'26'),(394,'429','Nuapada',NULL,NULL,' ',1,'26'),(395,'430','Puri',NULL,NULL,' ',1,'26'),(396,'431','Rayagada',NULL,NULL,' ',1,'26'),(397,'432','Sambalpur',NULL,NULL,' ',1,'26'),(398,'433','Subarnapur (null,Sonapur)',NULL,NULL,' ',1,'26'),(399,'434','Sundergarh',NULL,NULL,' ',1,'26'),(400,'435','Bastar',NULL,NULL,' ',1,'7'),(401,'436','Bijapur (null,Chhattisgarh)',NULL,NULL,' ',1,'7'),(402,'437','Bilaspur (null,Chhattisgarh)',NULL,NULL,' ',1,'7'),(403,'438','Dakshin Bastar Dantewada',NULL,NULL,' ',1,'7'),(404,'439','Dhamtari',NULL,NULL,' ',1,'7'),(405,'440','Durg',NULL,NULL,' ',1,'7'),(406,'441','Janjgir-Champa',NULL,NULL,' ',1,'7'),(407,'442','Jashpur',NULL,NULL,' ',1,'7'),(408,'443','Kabirdham (null,Kawardha)',NULL,NULL,' ',1,'7'),(409,'444','Korba',NULL,NULL,' ',1,'7'),(410,'445','Koriya',NULL,NULL,' ',1,'7'),(411,'446','Mahasamund',NULL,NULL,' ',1,'7'),(412,'447','Narayanpur',NULL,NULL,' ',1,'7'),(413,'448','Raigarh (null,Chhattisgarh)',NULL,NULL,' ',1,'7'),(414,'449','Raipur',NULL,NULL,' ',1,'7'),(415,'450','Rajnandgaon',NULL,NULL,' ',1,'7'),(416,'451','Surguja',NULL,NULL,' ',1,'7'),(417,'452','Uttar Bastar Kanker',NULL,NULL,' ',1,'7'),(418,'453','Alirajpur',NULL,NULL,' ',1,'20'),(419,'454','Anuppur',NULL,NULL,' ',1,'20'),(420,'455','Ashok Nagar',NULL,NULL,' ',1,'20'),(421,'456','Balaghat',NULL,NULL,' ',1,'20'),(422,'457','Barwani',NULL,NULL,' ',1,'20'),(423,'458','Betul',NULL,NULL,' ',1,'20'),(424,'459','Bhind',NULL,NULL,' ',1,'20'),(425,'460','Bhopal',NULL,NULL,' ',1,'20'),(426,'461','Burhanpur',NULL,NULL,' ',1,'20'),(427,'462','Chhatarpur',NULL,NULL,' ',1,'20'),(428,'463','Chhindwara',NULL,NULL,' ',1,'20'),(429,'464','Damoh',NULL,NULL,' ',1,'20'),(430,'465','Datia',NULL,NULL,' ',1,'20'),(431,'466','Dewas',NULL,NULL,' ',1,'20'),(432,'467','Dhar',NULL,NULL,' ',1,'20'),(433,'468','Dindori',NULL,NULL,' ',1,'20'),(434,'469','Guna',NULL,NULL,' ',1,'20'),(435,'470','Gwalior',NULL,NULL,' ',1,'20'),(436,'471','Harda',NULL,NULL,' ',1,'20'),(437,'472','Hoshangabad',NULL,NULL,' ',1,'20'),(438,'473','Indore',NULL,NULL,' ',1,'20'),(439,'474','Jabalpur',NULL,NULL,' ',1,'20'),(440,'475','Jhabua',NULL,NULL,' ',1,'20'),(441,'476','Katni',NULL,NULL,' ',1,'20'),(442,'477','Khandwa (null,East Nimar)',NULL,NULL,' ',1,'20'),(443,'478','Khargone (null,West Nimar)',NULL,NULL,' ',1,'20'),(444,'479','Mandla',NULL,NULL,' ',1,'20'),(445,'480','Mandsaur',NULL,NULL,' ',1,'20'),(446,'481','Morena',NULL,NULL,' ',1,'20'),(447,'482','Narsinghpur',NULL,NULL,' ',1,'20'),(448,'483','Neemuch',NULL,NULL,' ',1,'20'),(449,'484','Panna',NULL,NULL,' ',1,'20'),(450,'485','Raisen',NULL,NULL,' ',1,'20'),(451,'486','Rajgarh',NULL,NULL,' ',1,'20'),(452,'487','Ratlam',NULL,NULL,' ',1,'20'),(453,'488','Rewa',NULL,NULL,' ',1,'20'),(454,'489','Sagar',NULL,NULL,' ',1,'20'),(455,'490','Satna',NULL,NULL,' ',1,'20'),(456,'491','Sehore',NULL,NULL,' ',1,'20'),(457,'492','Seoni',NULL,NULL,' ',1,'20'),(458,'493','Shahdol',NULL,NULL,' ',1,'20'),(459,'494','Shajapur',NULL,NULL,' ',1,'20'),(460,'495','Sheopur',NULL,NULL,' ',1,'20'),(461,'496','Shivpuri',NULL,NULL,' ',1,'20'),(462,'497','Sidhi',NULL,NULL,' ',1,'20'),(463,'498','Singrauli',NULL,NULL,' ',1,'20'),(464,'499','Tikamgarh',NULL,NULL,' ',1,'20'),(465,'500','Ujjain',NULL,NULL,' ',1,'20'),(466,'501','Umaria',NULL,NULL,' ',1,'20'),(467,'502','Vidisha',NULL,NULL,' ',1,'20'),(468,'503','Ahmedabad',NULL,NULL,' ',1,'12'),(469,'504','Amreli',NULL,NULL,' ',1,'12'),(470,'505','Anand',NULL,NULL,' ',1,'12'),(471,'506','Banaskantha',NULL,NULL,' ',1,'12'),(472,'507','Bharuch',NULL,NULL,' ',1,'12'),(473,'508','Bhavnagar',NULL,NULL,' ',1,'12'),(474,'509','Dahod',NULL,NULL,' ',1,'12'),(475,'510','Gandhi Nagar',NULL,NULL,' ',1,'12'),(476,'511','Jamnagar',NULL,NULL,' ',1,'12'),(477,'512','Junagadh',NULL,NULL,' ',1,'12'),(478,'513','Kachchh',NULL,NULL,' ',1,'12'),(479,'514','Kheda',NULL,NULL,' ',1,'12'),(480,'515','Mahesana',NULL,NULL,' ',1,'12'),(481,'516','Narmada',NULL,NULL,' ',1,'12'),(482,'517','Navsari',NULL,NULL,' ',1,'12'),(483,'518','Panch Mahals',NULL,NULL,' ',1,'12'),(484,'519','Patan',NULL,NULL,' ',1,'12'),(485,'520','Porbandar',NULL,NULL,' ',1,'12'),(486,'521','Rajkot',NULL,NULL,' ',1,'12'),(487,'522','Sabarkantha',NULL,NULL,' ',1,'12'),(488,'523','Surat',NULL,NULL,' ',1,'12'),(489,'524','Surendra Nagar',NULL,NULL,' ',1,'12'),(490,'525','Tapi',NULL,NULL,' ',1,'12'),(491,'526','The Dangs',NULL,NULL,' ',1,'12'),(492,'527','Vadodara',NULL,NULL,' ',1,'12'),(493,'528','Valsad',NULL,NULL,' ',1,'12'),(494,'529','Daman',NULL,NULL,' ',1,'9'),(495,'530','Diu',NULL,NULL,' ',1,'9'),(496,'531','Dadra & Nagar Haveli',NULL,NULL,' ',1,'8'),(497,'532','Ahmed Nagar',NULL,NULL,' ',1,'21'),(498,'533','Akola',NULL,NULL,' ',1,'21'),(499,'534','Amravati',NULL,NULL,' ',1,'21'),(500,'535','Aurangabad',NULL,NULL,' ',1,'21'),(501,'536','Beed',NULL,NULL,' ',1,'21'),(502,'537','Bhandara',NULL,NULL,' ',1,'21'),(503,'538','Buldhana',NULL,NULL,' ',1,'21'),(504,'539','Chandrapur',NULL,NULL,' ',1,'21'),(505,'540','Dhule',NULL,NULL,' ',1,'21'),(506,'541','Gadchiroli',NULL,NULL,' ',1,'21'),(507,'542','Gondia',NULL,NULL,' ',1,'21'),(508,'543','Hingoli',NULL,NULL,' ',1,'21'),(509,'544','Jalgaon',NULL,NULL,' ',1,'21'),(510,'545','Jalna',NULL,NULL,' ',1,'21'),(511,'546','Kolhapur',NULL,NULL,' ',1,'21'),(512,'547','Latur',NULL,NULL,' ',1,'21'),(513,'548','Mumbai',NULL,NULL,' ',1,'21'),(514,'549','Mumbai Suburban',NULL,NULL,' ',1,'21'),(515,'550','Nagpur',NULL,NULL,' ',1,'21'),(516,'551','Nanded',NULL,NULL,' ',1,'21'),(517,'552','Nandurbar',NULL,NULL,' ',1,'21'),(518,'553','Nashik',NULL,NULL,' ',1,'21'),(519,'554','Osmanabad',NULL,NULL,' ',1,'21'),(520,'555','Parbhani',NULL,NULL,' ',1,'21'),(521,'556','Pune',NULL,NULL,' ',1,'21'),(522,'557','Raigarh (null,Maharashtra)',NULL,NULL,' ',1,'21'),(523,'558','Ratnagiri',NULL,NULL,' ',1,'21'),(524,'559','Sangli',NULL,NULL,' ',1,'21'),(525,'560','Satara',NULL,NULL,' ',1,'21'),(526,'561','Sindhudurg',NULL,NULL,' ',1,'21'),(527,'562','Solapur',NULL,NULL,' ',1,'21'),(528,'563','Thane',NULL,NULL,' ',1,'21'),(529,'564','Wardha',NULL,NULL,' ',1,'21'),(530,'565','Washim',NULL,NULL,' ',1,'21'),(531,'566','Yavatmal',NULL,NULL,' ',1,'21'),(532,'567','Adilabad',NULL,NULL,' ',1,'2'),(533,'568','Anantapur',NULL,NULL,' ',1,'2'),(534,'569','Chittoor',NULL,NULL,' ',1,'2'),(535,'570','East Godavari',NULL,NULL,' ',1,'2'),(536,'571','Guntur',NULL,NULL,' ',1,'2'),(537,'572','Hyderabad',NULL,NULL,' ',1,'2'),(538,'573','Kadapa (null,Cuddapah)',NULL,NULL,' ',1,'2'),(539,'574','Karim Nagar',NULL,NULL,' ',1,'2'),(540,'575','Khammam',NULL,NULL,' ',1,'2'),(541,'576','Krishna',NULL,NULL,' ',1,'2'),(542,'577','Kurnool',NULL,NULL,' ',1,'2'),(543,'578','Mahbubnagar',NULL,NULL,' ',1,'2'),(544,'579','Medak',NULL,NULL,' ',1,'2'),(545,'580','Nalgonda',NULL,NULL,' ',1,'2'),(546,'581','Nellore',NULL,NULL,' ',1,'2'),(547,'582','Nizamabad',NULL,NULL,' ',1,'2'),(548,'583','Prakasam',NULL,NULL,' ',1,'2'),(549,'584','Rangareddy',NULL,NULL,' ',1,'2'),(550,'585','Srikakulam',NULL,NULL,' ',1,'2'),(551,'586','Visakhapatnam',NULL,NULL,' ',1,'2'),(552,'587','Vizianagaram',NULL,NULL,' ',1,'2'),(553,'588','Warangal',NULL,NULL,' ',1,'2'),(554,'589','West Godavari',NULL,NULL,' ',1,'2'),(555,'590','Bagalkot',NULL,NULL,' ',1,'17'),(556,'591','Bangalore',NULL,NULL,' ',1,'17'),(557,'592','Bangalore Rural',NULL,NULL,' ',1,'17'),(558,'593','Belgaum',NULL,NULL,' ',1,'17'),(559,'594','Bellary',NULL,NULL,' ',1,'17'),(560,'595','Bidar',NULL,NULL,' ',1,'17'),(561,'596','Bijapur (null,Karnataka)',NULL,NULL,' ',1,'17'),(562,'597','Chamrajnagar',NULL,NULL,' ',1,'17'),(563,'598','Chickmagalur',NULL,NULL,' ',1,'17'),(564,'599','Chikkaballapur',NULL,NULL,' ',1,'17'),(565,'600','Chitradurga',NULL,NULL,' ',1,'17'),(566,'601','Dakshina Kannada',NULL,NULL,' ',1,'17'),(567,'602','Davanagere',NULL,NULL,' ',1,'17'),(568,'603','Dharwad',NULL,NULL,' ',1,'17'),(569,'604','Gadag',NULL,NULL,' ',1,'17'),(570,'605','Gulbarga',NULL,NULL,' ',1,'17'),(571,'606','Hassan',NULL,NULL,' ',1,'17'),(572,'607','Haveri',NULL,NULL,' ',1,'17'),(573,'608','Kodagu',NULL,NULL,' ',1,'17'),(574,'609','Kolar',NULL,NULL,' ',1,'17'),(575,'610','Koppal',NULL,NULL,' ',1,'17'),(576,'611','Mandya',NULL,NULL,' ',1,'17'),(577,'612','Mysore',NULL,NULL,' ',1,'17'),(578,'613','Raichur',NULL,NULL,' ',1,'17'),(579,'614','Ramanagara',NULL,NULL,' ',1,'17'),(580,'615','Shimoga',NULL,NULL,' ',1,'17'),(581,'616','Tumkur',NULL,NULL,' ',1,'17'),(582,'617','Udupi',NULL,NULL,' ',1,'17'),(583,'618','Uttara Kannada',NULL,NULL,' ',1,'17'),(584,'619','Yadgir',NULL,NULL,' ',1,'17'),(585,'620','North Goa',NULL,NULL,' ',1,'11'),(586,'621','South Goa',NULL,NULL,' ',1,'11'),(587,'622','Lakshadweep',NULL,NULL,' ',1,'19'),(588,'623','Alappuzha',NULL,NULL,' ',1,'18'),(589,'624','Ernakulam',NULL,NULL,' ',1,'18'),(590,'625','Idukki',NULL,NULL,' ',1,'18'),(591,'626','Kannur',NULL,NULL,' ',1,'18'),(592,'627','Kasaragod',NULL,NULL,' ',1,'18'),(593,'628','Kollam',NULL,NULL,' ',1,'18'),(594,'629','Kottayam',NULL,NULL,' ',1,'18'),(595,'630','Kozhikode',NULL,NULL,' ',1,'18'),(596,'631','Malappuram',NULL,NULL,' ',1,'18'),(597,'632','Palakkad',NULL,NULL,' ',1,'18'),(598,'633','Pathanamthitta',NULL,NULL,' ',1,'18'),(599,'634','Thiruvananthapuram',NULL,NULL,' ',1,'18'),(600,'635','Thrissur',NULL,NULL,' ',1,'18'),(601,'636','Wayanad',NULL,NULL,' ',1,'18'),(602,'637','Ariyalur',NULL,NULL,' ',1,'31'),(603,'638','Chennai',NULL,NULL,' ',1,'31'),(604,'639','Coimbatore',NULL,NULL,' ',1,'31'),(605,'640','Cuddalore',NULL,NULL,' ',1,'31'),(606,'641','Dharmapuri',NULL,NULL,' ',1,'31'),(607,'642','Dindigul',NULL,NULL,' ',1,'31'),(608,'643','Erode',NULL,NULL,' ',1,'31'),(609,'644','Kanchipuram',NULL,NULL,' ',1,'31'),(610,'645','Kanyakumari',NULL,NULL,' ',1,'31'),(611,'646','Karur',NULL,NULL,' ',1,'31'),(612,'647','Krishnagiri',NULL,NULL,' ',1,'31'),(613,'648','Madurai',NULL,NULL,' ',1,'31'),(614,'649','Nagapattinam',NULL,NULL,' ',1,'31'),(615,'650','Namakkal',NULL,NULL,' ',1,'31'),(616,'651','Nilgiris',NULL,NULL,' ',1,'31'),(617,'652','Perambalur',NULL,NULL,' ',1,'31'),(618,'653','Pudukkottai',NULL,NULL,' ',1,'31'),(619,'654','Ramanathapuram',NULL,NULL,' ',1,'31'),(620,'655','Salem',NULL,NULL,' ',1,'31'),(621,'656','Sivaganga',NULL,NULL,' ',1,'31'),(622,'657','Thanjavur',NULL,NULL,' ',1,'31'),(623,'658','Theni',NULL,NULL,' ',1,'31'),(624,'659','Thoothukudi (null,Tuticorin)',NULL,NULL,' ',1,'31'),(625,'660','Tiruchirappalli',NULL,NULL,' ',1,'31'),(626,'661','Tirunelveli',NULL,NULL,' ',1,'31'),(627,'662','Tiruppur',NULL,NULL,' ',1,'31'),(628,'663','Tiruvallur',NULL,NULL,' ',1,'31'),(629,'664','Tiruvannamalai',NULL,NULL,' ',1,'31'),(630,'665','Tiruvarur',NULL,NULL,' ',1,'31'),(631,'666','Vellore',NULL,NULL,' ',1,'31'),(632,'667','Viluppuram',NULL,NULL,' ',1,'31'),(633,'668','Virudhunagar',NULL,NULL,' ',1,'31'),(634,'669','Karaikal',NULL,NULL,' ',1,'27'),(635,'670','Mahe',NULL,NULL,' ',1,'27'),(636,'671','Puducherry (null,Pondicherry)',NULL,NULL,' ',1,'27'),(637,'672','Yanam',NULL,NULL,' ',1,'27'),(638,'673','Nicobar',NULL,NULL,' ',1,'1'),(639,'674','North And Middle Andaman',NULL,NULL,' ',1,'1'),(640,'675','South Andaman',NULL,NULL,' ',1,'1');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country_code` varchar(255) DEFAULT NULL,
  `country_id` varchar(255) DEFAULT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3i0ierpsxha1d12fxdd265oov` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (4,NULL,'101','INDIA',NULL,1,NULL,NULL),(5,NULL,'102','usa',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `employee_role_id` varchar(255) DEFAULT NULL,
  `employee_role_name` varchar(255) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (1,'2022-10-31 15:41:57.746261','1','admin','2022-10-31 15:41:57.746261','adminDashboard',1),(2,NULL,'2','verificationManager',NULL,'verificationManagerDashboard',1),(3,NULL,'3','salesManager',NULL,'salesManagerDashboard',1);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `gender_id` varchar(255) DEFAULT NULL,
  `gender_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g23vqgdv580aqlyorwkqfjs95` (`gender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'2','Female'),(2,'1','Male');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role_id_fk` int DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `FKr2meb30j8uaxena2yh2xdo0ny` (`role_id_fk`),
  CONSTRAINT `FKr2meb30j8uaxena2yh2xdo0ny` FOREIGN KEY (`role_id_fk`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'about',1,_binary '\0'),(2,'upload',2,_binary '\0'),(3,'pqr',3,_binary '');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_role_id`
--

DROP TABLE IF EXISTS `menu_role_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_role_id` (
  `menu_domain_menu_id` int NOT NULL,
  `role_id_role_id` int NOT NULL,
  PRIMARY KEY (`menu_domain_menu_id`,`role_id_role_id`),
  KEY `FKctbxm2mnulapj4hgg1vpga63t` (`role_id_role_id`),
  CONSTRAINT `FKc9wcovbxbj0vmgjktyy1xs7ss` FOREIGN KEY (`menu_domain_menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FKctbxm2mnulapj4hgg1vpga63t` FOREIGN KEY (`role_id_role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role_id`
--

LOCK TABLES `menu_role_id` WRITE;
/*!40000 ALTER TABLE `menu_role_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu_role_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  UNIQUE KEY `UK_4ji5d2xoxa7mheyn57p6ybc70` (`menu_id`),
  CONSTRAINT `FKrvhjnns4bvlh4m1n97vb7vbar` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKs6f918l17t80phe898jig002s` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
 CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `modules` varchar(255) DEFAULT NULL,
  `price` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `technology_used` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `category_id` int NOT NULL DEFAULT '0',
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `projectcode` varchar(255) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '0',
  `uploader_id` varchar(255) NOT NULL,
  `edit_price` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Project1','img1','mod1',0,0,'syn1','tech1','title1','video1');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `role_id_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,' adminDashboard','admin',1,NULL),(2,NULL,NULL,'verificationManagerDashboard','verificationManager',1,NULL),(3,NULL,NULL,'salesManagerDashboard','salesManager',1,NULL),(4,NULL,NULL,'studentDashboard','student',1,NULL),(5,NULL,NULL,'purchaserDashboard','purchaser',1,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu_id_fk`
--

DROP TABLE IF EXISTS `role_menu_id_fk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu_id_fk` (
  `role_domain_role_id` int NOT NULL,
  `menu_id_fk_menu_id` int NOT NULL,
  PRIMARY KEY (`role_domain_role_id`,`menu_id_fk_menu_id`),
  UNIQUE KEY `UK_lng0hubapd72uhn3yhfdvql0b` (`menu_id_fk_menu_id`),
  CONSTRAINT `FK3nb0ufkio6mn46sb01ffg9s1n` FOREIGN KEY (`role_domain_role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FK7ms6ow78bkjf7ls84t8eyafhr` FOREIGN KEY (`menu_id_fk_menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu_id_fk`
--

LOCK TABLES `role_menu_id_fk` WRITE;
/*!40000 ALTER TABLE `role_menu_id_fk` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_menu_id_fk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `short_name` varchar(255) DEFAULT NULL,
  `state_id` varchar(255) DEFAULT NULL,
  `state_name` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `country_id_fk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4rxlgxl5i4jfo1mba1cc3y3dd` (`state_id`),
  KEY `FK9sj29xcibn19befrfwmc58kq1` (`country_id_fk`),
  CONSTRAINT `FK9sj29xcibn19befrfwmc58kq1` FOREIGN KEY (`country_id_fk`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (9,' ','1','Andaman & Nicobar Islands',1,'101'),(10,' ','2','Andhra Pradesh',1,'101'),(11,' ','3','Arunachal Pradesh',1,'101'),(12,' ','4','Assam',1,'101'),(13,' ','5','Bihar',1,'101'),(14,' ','6','Chandigarh',1,'101'),(15,' ','7','Chhattisgarh',1,'101'),(16,' ','8','Dadra & Nagar Haveli',1,'101'),(17,' ','9','Daman & Diu',1,'101'),(18,' ','10','Delhi',1,'101'),(19,' ','11','Goa',1,'101'),(20,' ','12','Gujarat',1,'101'),(21,' ','13','Haryana',1,'101'),(22,' ','14','Himachal Pradesh',1,'101'),(23,' ','15','Jammu & Kashmir',1,'101'),(24,' ','16','Jharkhand',1,'101'),(25,' ','17','Karnataka',1,'101'),(26,' ','18','Kerala',1,'101'),(27,' ','19','Lakshadweep',1,'101'),(28,' ','20','Madhya Pradesh',1,'101'),(29,' ','21','Maharashtra',1,'101'),(30,' ','22','Manipur',1,'101'),(31,' ','23','Meghalaya',1,'101'),(32,' ','24','Mizoram',1,'101'),(33,' ','25','Nagaland',1,'101'),(34,' ','26','Odisha',1,'101'),(35,' ','27','Puducherry',1,'101'),(36,' ','28','Punjab',1,'101'),(37,' ','29','Rajasthan',1,'101'),(38,' ','30','Sikkim',1,'101'),(39,' ','31','Tamil Nadu',1,'101'),(40,' ','32','Tripura',1,'101'),(41,' ','33','Uttar Pradesh',1,'101'),(42,' ','34','Uttarakhand',1,'101'),(43,' ','35','West Bengal',1,'101');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `aadhar_number` decimal(19,2) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `alternative_number` bigint DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `employeestatus` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender_id` int DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` bigint DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_source` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `token_expires` varchar(255) DEFAULT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  `bank_id` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`),
  KEY `FKlhs64uv6rna1fxa30cmjjt0we` (`bank_id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FKlhs64uv6rna1fxa30cmjjt0we` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('382659',202020206020.00,NULL,7777722222,'2022-11-14 17:34:50.058566','27/08/1995','hema.ah@gmail.com',_binary '\0','Hema',1,'Latha',9920608869,'2022-11-14 17:34:50.058566','$2a$10$dE4pfH6B7am5c6nTFtTP6uO4nEMpWOzWgesRK4nLb8/rdWaCXJpUq','app',_binary '',NULL,'976153','438363',2),('662350',202020202020.00,NULL,7777722222,'2022-11-08 17:47:24.936560','27/08/1995','Abhi2022@gmail.com',_binary '\0','ABHISHEK',1,'HULAGADDI',9876543210,'2022-11-08 17:47:24.936560','$2a$10$TG6esfIa.rtyxJsQq8A8Quu2TYJj0gE74YNt2ic0LCOhtZd6Klc7a','app',_binary '',NULL,'840685','733400',1),('872746',202020202023.00,NULL,7777722222,'2022-11-05 13:09:45.643068','27/08/1997','Rahul2022@gmail.com',_binary '\0','Rahul',1,'B',8904135905,'2022-11-05 13:09:45.643068','$2a$10$E01IYUZ9Py/tWCtHbn0ErOMZ4u1zC1x5HDMiSCEJ9e6NVEtLDgH72','app',_binary '',NULL,'101592','948685',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrole` (
  `uu_id` varchar(255) NOT NULL,
  `apptoken_id` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `is_active` tinyint NOT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `webtoken_id` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uu_id`),
  KEY `FKf9a7cojfuvf40x6co16kxa1jb` (`role_id`),
  KEY `FKtbick5dbrpnos6ll2175dt5qr` (`user_id`),
  CONSTRAINT `FKf9a7cojfuvf40x6co16kxa1jb` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKtbick5dbrpnos6ll2175dt5qr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES ('719682',NULL,'2022-11-05 13:09:45.654210',1,'2022-11-05 13:09:45.654210',1,NULL,2,'872746'),('732420',NULL,'2022-11-08 17:47:24.946033',1,'2022-11-08 17:47:24.946033',1,NULL,1,'662350'),('769081',NULL,'2022-11-14 17:34:50.077570',1,'2022-11-14 17:34:50.077570',1,NULL,2,'382659');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-16 15:13:22
drop table if exists `user_log`;
CREATE TABLE `user_log` (
   `id` bigint NOT NULL auto_increment ,
   `user_id` varchar(255) NOT NULL,
  `aadhar_number` decimal(19,2) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `alternative_number` bigint DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender_id` int DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
   `role_id` bigint NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `project_title` varchar(255) NOT NULL,
  `mobile_number` bigint DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `user_activity` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
