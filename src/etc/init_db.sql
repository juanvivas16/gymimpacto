-- Adminer 4.2.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `testgym`;
CREATE DATABASE `testgym` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testgym`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `ci` varchar(10) NOT NULL,
  `init_date` date NOT NULL,
  PRIMARY KEY (`ci`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_ci` varchar(10) NOT NULL,
  `type_c` enum('Tiempo_completo','Medio_tiempo','Por_horas') NOT NULL,
  `pay` double NOT NULL,
  `activity` varchar(30) NOT NULL,
  PRIMARY KEY (`employee_ci`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`employee_ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equipment_inventory`;
CREATE TABLE `equipment_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `description` varchar(50) NOT NULL,
  `ad_date` date NOT NULL,
  `cost` double NOT NULL,
  `state` enum('Activo','Inactivo') NOT NULL,
  `quantity` int(11) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `equipment_inventory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `expenses`;
CREATE TABLE `expenses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `total` double unsigned NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `expenses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(10) NOT NULL,
  `description` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `sub_total` double unsigned NOT NULL,
  `iva` double unsigned NOT NULL DEFAULT '0.12',
  `total` double unsigned NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `income_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ci`),
  CONSTRAINT `income_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


DELIMITER ;;

CREATE TRIGGER `sub_total_bi` BEFORE INSERT ON `income` FOR EACH ROW
  BEGIN
    IF NEW.sub_total > 0 THEN
      SET @sub_total = -(NEW.sub_total);
    END IF;
  END;;

DELIMITER ;

DROP TABLE IF EXISTS `income_product`;
CREATE TABLE `income_product` (
  `income_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  KEY `income_id` (`income_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `income_product_ibfk_1` FOREIGN KEY (`income_id`) REFERENCES `income` (`id`),
  CONSTRAINT `income_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `ci` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('Masculino','Femenino') NOT NULL,
  `dir` varchar(50) NOT NULL,
  `phone` varchar(12) NOT NULL,
  PRIMARY KEY (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `price` double unsigned NOT NULL,
  `quantity_available` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `customer_id` varchar(10) NOT NULL,
  `type_s` enum('Mensual','Quincenal','Diario') NOT NULL,
  `init_date` date NOT NULL,
  `income_id` int(11) NOT NULL,
  PRIMARY KEY (`income_id`,`customer_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `service_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ci`),
  CONSTRAINT `service_ibfk_3` FOREIGN KEY (`income_id`) REFERENCES `income` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` varchar(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `dir` varchar(50) NOT NULL,
  `product_desc` varchar(30) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `supplier_product`;
CREATE TABLE `supplier_product` (
  `product_id` int(11) NOT NULL,
  `supplier_rif` varchar(10) NOT NULL,
  KEY `product_id` (`product_id`),
  KEY `supplier_rif` (`supplier_rif`),
  CONSTRAINT `supplier_product_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `supplier_product_ibfk_2` FOREIGN KEY (`supplier_rif`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(15) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `rol` enum('Recepcion','Administrador','Gerente') NOT NULL,
  `user_ci` varchar(10) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `user_ci` (`user_ci`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2016-12-13 05:42:51
