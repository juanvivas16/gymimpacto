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
  `ingress_date` date NOT NULL,
  PRIMARY KEY (`ci`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `customer` (`ci`, `ingress_date`) VALUES
('V-20431975',	'2016-11-29'),
('V-23942234',	'2016-12-06'),
('V-3245623',	'2016-12-06'),
('V-7421344',	'2016-12-06')
ON DUPLICATE KEY UPDATE `ci` = VALUES(`ci`), `ingress_date` = VALUES(`ingress_date`);

DROP TABLE IF EXISTS `earnings`;
CREATE TABLE `earnings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(50) NOT NULL,
  `customer_id` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `sub_total` double unsigned NOT NULL,
  `iva` double unsigned NOT NULL DEFAULT '0.12',
  `total` double unsigned NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `earnings_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ci`),
  CONSTRAINT `earnings_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `earnings` (`id`, `desc`, `customer_id`, `date`, `sub_total`, `iva`, `total`, `user_id`) VALUES
(1,	'Mensualidad',	'V-23942234',	'2016-12-06',	2000,	0.12,	2240,	'paovera'),
(2,	'Mensualidad + Gatorade',	'V-3245623',	'2016-12-06',	3000,	0.12,	3360,	'paovera')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `desc` = VALUES(`desc`), `customer_id` = VALUES(`customer_id`), `date` = VALUES(`date`), `sub_total` = VALUES(`sub_total`), `iva` = VALUES(`iva`), `total` = VALUES(`total`), `user_id` = VALUES(`user_id`);

DELIMITER ;;

CREATE TRIGGER `sub_total_bi` BEFORE INSERT ON `earnings` FOR EACH ROW
BEGIN
IF NEW.sub_total > 0 THEN
    SET @sub_total = -(NEW.sub_total);
END IF;
END;;

DELIMITER ;

DROP TABLE IF EXISTS `earning_product`;
CREATE TABLE `earning_product` (
  `earning_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(10) unsigned NOT NULL DEFAULT '1',
  KEY `earning_id` (`earning_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `earning_product_ibfk_1` FOREIGN KEY (`earning_id`) REFERENCES `earnings` (`id`),
  CONSTRAINT `earning_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `earning_product` (`earning_id`, `product_id`, `quantity`) VALUES
(2,	2,	1)
ON DUPLICATE KEY UPDATE `earning_id` = VALUES(`earning_id`), `product_id` = VALUES(`product_id`), `quantity` = VALUES(`quantity`);

DROP TABLE IF EXISTS `equipment_inventory`;
CREATE TABLE `equipment_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `ad_date` date NOT NULL,
  `cost` double NOT NULL,
  `state` enum('Activo','Inactivo') NOT NULL,
  `quantity` int(11) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `equipment_inventory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO `equipment_inventory` (`id`, `name`, `model`, `ad_date`, `cost`, `state`, `quantity`, `user_id`) VALUES
(6,	'Multifuerza',	'Unico',	'1999-12-01',	760,	'Activo',	1,	'juanvivas'),
(7,	'Bicicleta',	'SB2',	'2001-04-05',	2000,	'Activo',	10,	'juanvivas'),
(8,	'Mancuerna',	'20kg',	'2002-04-11',	600,	'Activo',	20,	'juanvivas'),
(9,	'Plato',	'2kg',	'2002-05-12',	200,	'Activo',	30,	'juanvivas')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `name` = VALUES(`name`), `model` = VALUES(`model`), `ad_date` = VALUES(`ad_date`), `cost` = VALUES(`cost`), `state` = VALUES(`state`), `quantity` = VALUES(`quantity`), `user_id` = VALUES(`user_id`);

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

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `ci` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('Masculino','Femenino') NOT NULL,
  `dir` varchar(100) NOT NULL,
  `phone` varchar(12) NOT NULL,
  PRIMARY KEY (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `person` (`ci`, `name`, `last_name`, `birth_date`, `gender`, `dir`, `phone`) VALUES
('V-19895266',	'Julian',	'Lacruz',	'1991-03-05',	'Masculino',	'Ejido',	'0416-2739235'),
('V-20321312',	'Luisa',	'Ruiz',	'1992-04-12',	'Femenino',	'Los proceres',	'0424-2423423'),
('V-20431975',	'Juan Andres',	'Vivas Contreras',	'1992-09-16',	'Masculino',	'Santa Maria Sur',	'0424-7804473'),
('V-20435560',	'Paola',	'Vera',	'1991-06-20',	'Femenino',	'El trapiche',	'0424-7005096'),
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
  `type_s` enum('Mensual','Quincenal','Diario') NOT NULL,
  `init_date` date NOT NULL,
  `earning_id` int(11) NOT NULL,
  `customer_id` varchar(10) NOT NULL,
  PRIMARY KEY (`earning_id`,`customer_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `service_ibfk_1` FOREIGN KEY (`earning_id`) REFERENCES `earnings` (`id`),
  CONSTRAINT `service_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `service` (`type_s`, `init_date`, `earning_id`, `customer_id`) VALUES
('Mensual',	'2016-12-06',	1,	'V-23942234'),
('Mensual',	'2016-12-06',	2,	'V-3245623')
ON DUPLICATE KEY UPDATE `type_s` = VALUES(`type_s`), `init_date` = VALUES(`init_date`), `earning_id` = VALUES(`earning_id`), `customer_id` = VALUES(`customer_id`);

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staff_ci` varchar(10) NOT NULL,
  `type_c` enum('Tiempo_completo','Medio_tiempo','Por_horas') NOT NULL,
  `pay` double NOT NULL,
  `activity` varchar(30) NOT NULL,
  PRIMARY KEY (`staff_ci`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`staff_ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `staff` (`staff_ci`, `type_c`, `pay`, `activity`) VALUES
('V-19895266',	'Tiempo_completo',	50000,	'Entrenador'),
('V-23942234',	'Medio_tiempo',	32405,	'Instructor Spinning'),
('V-7421344',	'Por_horas',	15000,	'Limpieza')
ON DUPLICATE KEY UPDATE `staff_ci` = VALUES(`staff_ci`), `type_c` = VALUES(`type_c`), `pay` = VALUES(`pay`), `activity` = VALUES(`activity`);

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `dir` varchar(100) NOT NULL,
  `product_desc` varchar(20) NOT NULL,
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
  `rol` enum('Recepcion','Administrador') NOT NULL,
  `user_ci` varchar(10) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `user_ci` (`user_ci`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_ci`) REFERENCES `person` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`username`, `pass`, `rol`, `user_ci`) VALUES
('juanvivas',	'7110eda4d09e062',	'Administrador',	'V-20431975'),
('paovera',	'7110eda4d09e062',	'Recepcion',	'V-20435560')
ON DUPLICATE KEY UPDATE `username` = VALUES(`username`), `pass` = VALUES(`pass`), `rol` = VALUES(`rol`), `user_ci` = VALUES(`user_ci`);

-- 2016-12-07 01:01:24
