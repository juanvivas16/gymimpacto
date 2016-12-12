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

INSERT INTO `customer` (`ci`, `init_date`) VALUES
  ('V-20431975',	'2016-11-29'),
  ('V-23942234',	'2016-12-06'),
  ('V-3245623',	'2016-12-06'),
  ('V-7421344',	'2016-12-06')
ON DUPLICATE KEY UPDATE `ci` = VALUES(`ci`), `init_date` = VALUES(`init_date`);

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_ci` varchar(10) NOT NULL,
  `type_c` enum('Tiempo_completo','Medio_tiempo','Por_horas') NOT NULL,
  `pay` double NOT NULL,
  `activity` varchar(30) NOT NULL,
  PRIMARY KEY (`employee_ci`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`employee_ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `employee` (`employee_ci`, `type_c`, `pay`, `activity`) VALUES
  ('V-19895266',	'Tiempo_completo',	50000,	'Entrenador'),
  ('V-23942234',	'Medio_tiempo',	32405,	'Instructor Spinning'),
  ('V-7421344',	'Por_horas',	15000,	'Limpieza')
ON DUPLICATE KEY UPDATE `employee_ci` = VALUES(`employee_ci`), `type_c` = VALUES(`type_c`), `pay` = VALUES(`pay`), `activity` = VALUES(`activity`);

DROP TABLE IF EXISTS `equipment_inventory`;
CREATE TABLE `equipment_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `desc` varchar(50) NOT NULL,
  `ad_date` date NOT NULL,
  `cost` double NOT NULL,
  `state` enum('Activo','Inactivo') NOT NULL,
  `quantity` int(11) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `equipment_inventory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO `equipment_inventory` (`id`, `name`, `model`, `desc`, `ad_date`, `cost`, `state`, `quantity`, `user_id`) VALUES
  (6,	'Multifuerza',	'Unico',	'En buen estado',	'1999-12-01',	760,	'Activo',	1,	'juanvivas'),
  (7,	'Bicicleta',	'SB2',	'Pedal a medio funcionar',	'2001-04-05',	2000,	'Activo',	10,	'juanvivas'),
  (8,	'Mancuerna',	'20kg',	'Decolor negro todas',	'2002-04-11',	600,	'Activo',	20,	'juanvivas'),
  (9,	'Plato',	'2kg',	'desfigurados',	'2002-05-12',	200,	'Activo',	30,	'juanvivas')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `name` = VALUES(`name`), `model` = VALUES(`model`), `desc` = VALUES(`desc`), `ad_date` = VALUES(`ad_date`), `cost` = VALUES(`cost`), `state` = VALUES(`state`), `quantity` = VALUES(`quantity`), `user_id` = VALUES(`user_id`);

DROP TABLE IF EXISTS `expenses`;
CREATE TABLE `expenses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `total` double unsigned NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `expenses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `expenses` (`id`, `desc`, `date`, `total`, `user_id`) VALUES
  (1,	'Arreglo mueble',	'2016-12-06',	20000,	'paovera'),
  (2,	'Pago nomina total mes de diciembre',	'2016-12-06',	345000,	'paovera'),
  (4,	'Compra gatorade cafetin',	'2016-12-06',	45000,	'juanvivas'),
  (5,	'Compra yogurt varios sabores',	'2016-12-06',	30000,	'juanvivas')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `desc` = VALUES(`desc`), `date` = VALUES(`date`), `total` = VALUES(`total`), `user_id` = VALUES(`user_id`);

DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(10) NOT NULL,
  `desc` varchar(50) NOT NULL,
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

INSERT INTO `income` (`id`, `customer_id`, `desc`, `date`, `sub_total`, `iva`, `total`, `user_id`) VALUES
  (1,	'V-23942234',	'Mensualidad',	'2016-12-06',	2000,	0.12,	2240,	'paovera'),
  (2,	'V-3245623',	'Mensualidad + Gatorade',	'2016-12-06',	3000,	0.12,	3360,	'paovera')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `customer_id` = VALUES(`customer_id`), `desc` = VALUES(`desc`), `date` = VALUES(`date`), `sub_total` = VALUES(`sub_total`), `iva` = VALUES(`iva`), `total` = VALUES(`total`), `user_id` = VALUES(`user_id`);

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

INSERT INTO `income_product` (`income_id`, `product_id`, `quantity`) VALUES
  (2,	2,	1)
ON DUPLICATE KEY UPDATE `income_id` = VALUES(`income_id`), `product_id` = VALUES(`product_id`), `quantity` = VALUES(`quantity`);

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

INSERT INTO `person` (`ci`, `name`, `last_name`, `birth_date`, `gender`, `dir`, `phone`) VALUES
  ('V-19895266',	'Julian',	'Lacruz',	'1991-03-05',	'Masculino',	'Ejido',	'0416-2739235'),
  ('V-20321312',	'Luisa',	'Ruiz',	'1992-04-12',	'Femenino',	'Los proceres',	'0424-2423423'),
  ('V-20431975',	'Juan Andres',	'Vivas Contreras',	'1992-09-16',	'Masculino',	'Santa Maria Sur',	'0424-7804473'),
  ('V-20435560',	'Paola',	'Vera',	'1991-06-20',	'Femenino',	'El trapiche',	'0424-7005096'),
  ('V-21365922',	'Javier',	'Solsona',	'1993-07-07',	'Masculino',	'Paseo la fera',	'0416-4786475'),
  ('V-23453464',	'Fabiola',	'Araque',	'1995-10-10',	'Femenino',	'Cardenal Quintero',	'0412-2323453'),
  ('V-23497463',	'Mudafar',	'El Halabi',	'1989-07-17',	'Masculino',	'La joya',	'0416-0466440'),
  ('V-23942234',	'Carlos',	'Rojas',	'1976-04-03',	'Masculino',	'Centro',	'0274-2342144'),
  ('V-3245623',	'Julio',	'Cabrera',	'1960-06-24',	'Masculino',	'Tabay',	'0416-4323455'),
  ('V-7421344',	'Maria',	'Villa',	'1945-03-05',	'Femenino',	'Las Americas',	'0274-2525544')
ON DUPLICATE KEY UPDATE `ci` = VALUES(`ci`), `name` = VALUES(`name`), `last_name` = VALUES(`last_name`), `birth_date` = VALUES(`birth_date`), `gender` = VALUES(`gender`), `dir` = VALUES(`dir`), `phone` = VALUES(`phone`);

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(50) NOT NULL,
  `price` double unsigned NOT NULL,
  `quantity_available` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `product` (`id`, `desc`, `price`, `quantity_available`) VALUES
  (2,	'Gatorade',	1000,	100),
  (3,	'Yogurt Fresa',	750,	15),
  (4,	'Yogurt Mora',	750,	15),
  (5,	'Refresco',	450,	30),
  (6,	'Galleta de avena',	500,	50),
  (7,	'Galleta de mantequilla grande',	800,	10)
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `desc` = VALUES(`desc`), `price` = VALUES(`price`), `quantity_available` = VALUES(`quantity_available`);

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

INSERT INTO `service` (`customer_id`, `type_s`, `init_date`, `income_id`) VALUES
  ('V-23942234',	'Mensual',	'2016-12-06',	1),
  ('V-3245623',	'Mensual',	'2016-12-06',	2)
ON DUPLICATE KEY UPDATE `customer_id` = VALUES(`customer_id`), `type_s` = VALUES(`type_s`), `init_date` = VALUES(`init_date`), `income_id` = VALUES(`income_id`);

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

INSERT INTO `supplier` (`id`, `name`, `phone`, `dir`, `product_desc`, `user_id`) VALUES
  ('J-2313423',	'Pepsi-Cola',	'0212-2342324',	'Caracas',	'Refrescos y Gatorade',	'juanvivas'),
  ('J-32342344',	'Jose Colmenares',	'0414-2342342',	'El valle',	'Yogurt',	'juanvivas'),
  ('V-21321333',	'Luis Rodriguez',	'0416-5645634',	'La mucuy',	'Galletas y tortas',	'juanvivas')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `name` = VALUES(`name`), `phone` = VALUES(`phone`), `dir` = VALUES(`dir`), `product_desc` = VALUES(`product_desc`), `user_id` = VALUES(`user_id`);

DROP TABLE IF EXISTS `supplier_product`;
CREATE TABLE `supplier_product` (
  `product_id` int(11) NOT NULL,
  `supplier_rif` varchar(10) NOT NULL,
  KEY `product_id` (`product_id`),
  KEY `supplier_rif` (`supplier_rif`),
  CONSTRAINT `supplier_product_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `supplier_product_ibfk_2` FOREIGN KEY (`supplier_rif`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `supplier_product` (`product_id`, `supplier_rif`) VALUES
  (2,	'J-2313423'),
  (3,	'J-32342344'),
  (4,	'J-32342344'),
  (5,	'J-2313423'),
  (6,	'V-21321333'),
  (7,	'V-21321333')
ON DUPLICATE KEY UPDATE `product_id` = VALUES(`product_id`), `supplier_rif` = VALUES(`supplier_rif`);

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

INSERT INTO `user` (`username`, `pass`, `rol`, `user_ci`) VALUES
  ('javiers',	'1234',	'Gerente',	'V-21365922'),
  ('juanvivas',	'1234',	'Administrador',	'V-20431975'),
  ('paovera',	'1234',	'Recepcion',	'V-20435560')
ON DUPLICATE KEY UPDATE `username` = VALUES(`username`), `pass` = VALUES(`pass`), `rol` = VALUES(`rol`), `user_ci` = VALUES(`user_ci`);

-- 2016-12-12 02:53:08
