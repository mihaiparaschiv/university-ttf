-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.45-community


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

drop database ttf;

CREATE DATABASE IF NOT EXISTS ttf;
USE ttf;

CREATE TABLE `articlefeatures` (
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `score` double NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`type`,`name`) USING BTREE,
  KEY `FK_articlefeatures_1` (`articleId`),
  CONSTRAINT `FK_articlefeatures_1` FOREIGN KEY (`articleId`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `articlefeatures` DISABLE KEYS */;
/*!40000 ALTER TABLE `articlefeatures` ENABLE KEYS */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;

CREATE TABLE `incomingarticles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publishedAt` datetime NOT NULL,
  `discoveredAt` datetime NOT NULL,
  `sourceId` int(10) unsigned DEFAULT NULL,
  `address` varchar(255) NOT NULL UNIQUE,
  `content` text NOT NULL,
  `processed` boolean NOT NULL DEFAULT 0, 
  PRIMARY KEY (`id`),
  KEY `FK_incomingarticles_1` (`sourceId`),
  CONSTRAINT `FK_incomingarticles_1` FOREIGN KEY (`sourceId`) REFERENCES `sources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `incomingarticles` DISABLE KEYS */;
/*!40000 ALTER TABLE `incomingarticles` ENABLE KEYS */;

CREATE TABLE `sources` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `retrievalInterval` mediumint unsigned DEFAULT 0,
  `lastChecked` timestamp DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `sources` ENABLE KEYS */;

CREATE TABLE `topicfeatures` (
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `score` double NOT NULL,
  `topicId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`type`,`name`) USING BTREE,
  KEY `FK_topicfeatures_1` (`topicId`),
  CONSTRAINT `FK_topicfeatures_1` FOREIGN KEY (`topicId`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `topicfeatures` DISABLE KEYS */;
/*!40000 ALTER TABLE `topicfeatures` ENABLE KEYS */;

CREATE TABLE `topics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
