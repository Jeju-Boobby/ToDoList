-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: todolist
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.21-MariaDB


--
-- Dumping data for table `todo`
--
--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (41,'test','$2a$10$uym9idwfSP2oLDCgY626wefAXxOuPoNVoVlVfXlYe9qYj6k8b8QSK','aaaaa','aaaa'),(46,'test1','$2a$10$5DMNAGJeFEEN7LWLMh4E5u6WxuXv8/Uahga1VCyc.vgrfzIeUFzKC','?�스??,'직업?�요'),(47,'boo1984','$2a$10$l7jk8l2V6kmUb1q..uwabeZsT11r1S5H4WuFynXoS2/zqa7crVWay','부?�??,'?�?�생'),(48,'ggggggg','$2a$10$YTohIMjDX5SlFomAK7BHDeUA2kzxthTOyccSOLs9w0hIOJ/CW5bBK','qeqw','qweqwe'),(49,'aaaaa','$2a$10$t/WScXgiueWMkiXwnBF/..C16dPfFp2y1f5BkF3EephTsYU08wa5e','123','123');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (80,41,'과제','1234','2017-06-23 19:30:00','adfasf','DONE'),(81,41,'과제','과아?�제','2017-06-16 19:00:00','?�ㅁ?�ㅁ??,'DONE'),(82,41,'과제','daffaff','2017-06-30 13:44:00','adafdfsdaf','DONE'),(84,41,'기�?','?�스?�중','2017-06-29 23:02:00','gsvsv','DONE'),(85,41,'?�속','123123123','2017-06-30 00:23:00','13213123','DONE'),(88,41,'?�속','ffaed','2017-06-28 02:23:00','12321312312','DONE'),(89,41,'과제','affsdfadsf','2017-06-28 00:00:00','123123123123','TO_DO'),(91,41,'기�?','qweqweqweqweqw','2017-06-23 00:05:00','qeq2eeqdasx','TO_DO'),(92,41,'?�속','asdsasdasas','2017-06-29 00:00:00','dasdasd','FAIL'),(93,46,'?�험','daffasd','2017-06-20 00:00:00','qwascdscx','TO_DO');
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;



-- Dump completed on 2017-06-18 16:32:58
