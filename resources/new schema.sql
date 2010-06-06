-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.47-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ttf
--

CREATE DATABASE IF NOT EXISTS ttf;
USE ttf;

--
-- Definition of table `articlefeatures`
--

DROP TABLE IF EXISTS `articlefeatures`;
CREATE TABLE `articlefeatures` (
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `score` double NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`type`,`name`,`articleId`) USING BTREE,
  KEY `FK_articlefeatures_1` (`articleId`),
  CONSTRAINT `FK_articlefeatures_1` FOREIGN KEY (`articleId`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `articlefeatures`
--

/*!40000 ALTER TABLE `articlefeatures` DISABLE KEYS */;
/*!40000 ALTER TABLE `articlefeatures` ENABLE KEYS */;


--
-- Definition of table `articles`
--

DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `publishedAt` varchar(255) NOT NULL,
  `discoveredAt` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `topicId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_articles_1` (`topicId`),
  CONSTRAINT `FK_articles_1` FOREIGN KEY (`topicId`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `articles`
--

/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;


--
-- Definition of table `incomingarticles`
--

DROP TABLE IF EXISTS `incomingarticles`;
CREATE TABLE `incomingarticles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publishedAt` datetime NOT NULL,
  `discoveredAt` datetime NOT NULL,
  `sourceId` int(10) unsigned DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `processed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `address` (`address`),
  KEY `FK_incomingarticles_1` (`sourceId`),
  CONSTRAINT `FK_incomingarticles_1` FOREIGN KEY (`sourceId`) REFERENCES `sources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `incomingarticles`
--

/*!40000 ALTER TABLE `incomingarticles` DISABLE KEYS */;
/*!40000 ALTER TABLE `incomingarticles` ENABLE KEYS */;


--
-- Definition of table `sources`
--

DROP TABLE IF EXISTS `sources`;
CREATE TABLE `sources` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `retrievalInterval` mediumint(8) unsigned DEFAULT '0',
  `lastChecked` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sources`
--

/*!40000 ALTER TABLE `sources` DISABLE KEYS */;
INSERT INTO `sources` (`id`,`retrievalInterval`,`lastChecked`,`address`) VALUES 
 (2,19,'2010-06-06 18:03:47','http://feeds.feedburner.com/TechCrunch');
/*!40000 ALTER TABLE `sources` ENABLE KEYS */;


--
-- Definition of table `topicfeatures`
--

DROP TABLE IF EXISTS `topicfeatures`;
CREATE TABLE `topicfeatures` (
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `score` double NOT NULL,
  `topicId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`type`,`name`,`topicId`) USING BTREE,
  KEY `FK_topicfeatures_1` (`topicId`),
  CONSTRAINT `FK_topicfeatures_1` FOREIGN KEY (`topicId`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topicfeatures`
--

/*!40000 ALTER TABLE `topicfeatures` DISABLE KEYS */;
/*!40000 ALTER TABLE `topicfeatures` ENABLE KEYS */;


--
-- Definition of table `topics`
--

DROP TABLE IF EXISTS `topics`;
CREATE TABLE `topics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topics`
--

/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
