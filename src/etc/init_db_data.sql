-- Adminer 4.2.5 MySQL dump

SET NAMES utf8;
SET time_zone = '' + 00 :00'';
SET foreign_key_checks = 0;
SET sql_mode = '' NO_AUTO_VALUE_ON_ZERO'';

DROP DATABASE IF EXISTS `testgym`;
CREATE DATABASE `testgym` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testgym`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `ci`        VARCHAR(10) NOT NULL,
  `init_date` DATE        NOT NULL,
  PRIMARY KEY (`ci`),
  CONSTRAINT `customer_ibfk_6` FOREIGN KEY (`ci`) REFERENCES `person` (`ci`)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `customer` (`ci`, `init_date`) VALUES
  (''E - 12345678'', ''2016 - 12 - 12''),
  (''V - 03245623'', ''2016 - 12 - 06''),
  (''V - 07421344'', ''2016 - 12 - 06''),
  (''V - 08007748'', ''2016 - 12 - 14''),
  (''V - 20431972'', ''2016 - 12 - 14''),
  (''V - 20431973'', ''2016 - 12 - 13''),
  (''V - 20431975'', ''2016 - 11 - 29''),
  (''V - 20431976'', ''2016 - 12 - 14''),
  (''V - 22435987'', ''2016 - 12 - 12''),
  (''V - 23942234'', ''2016 - 12 - 06'')
ON DUPLICATE KEY UPDATE `ci` = VALUES(`ci`), `init_date` = VALUES(`init_date`);

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_ci` VARCHAR(10)                                                 NOT NULL,
  `type_c`      ENUM (''Tiempo_completo'', ''Medio_tiempo'', ''Por_horas'') NOT NULL,
  `pay`         DOUBLE                                                      NOT NULL,
  `activity`    VARCHAR(30)                                                 NOT NULL,
  PRIMARY KEY (`employee_ci`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`employee_ci`) REFERENCES `person` (`ci`)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `employee` (`employee_ci`, `type_c`, `pay`, `activity`) VALUES
  (''V - 07421344'', ''Por_horas'', 15000, ''Limpieza''),
  (''V - 19895266'', ''Tiempo_completo'', 50000, ''Entrenador''),
  (''V - 23942234'', ''Medio_tiempo'', 32405, ''Instructor Spinning'')
ON DUPLICATE KEY UPDATE `employee_ci` = VALUES(`employee_ci`), `type_c` = VALUES(`type_c`), `pay` = VALUES(`pay`),
  `activity`                          = VALUES(`activity`);

DROP TABLE IF EXISTS `equipment_inventory`;
CREATE TABLE `equipment_inventory` (
  `id`          INT(11)                         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(20)                     NOT NULL,
  `model`       VARCHAR(20)                     NOT NULL,
  `description` VARCHAR(50)                     NOT NULL,
  `ad_date`     DATE                            NOT NULL,
  `cost`        DOUBLE                          NOT NULL,
  `state`       ENUM (''Activo'', ''Inactivo'') NOT NULL,
  `quantity`    INT(11)                         NOT NULL,
  `user_id`     VARCHAR(15)                     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `equipment_inventory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8;

INSERT INTO `equipment_inventory` (`id`, `name`, `model`, `description`, `ad_date`, `cost`, `state`, `quantity`, `user_id`)
VALUES
  (6, ''Multifuerza'', ''Unico'', ''En buen estado'', ''1999 - 12 - 01'', 760, ''Activo'', 1, ''juanvivas''),
  (7, ''Bicicleta'', ''SB2'', ''Pedal a medio funcionar'', ''2001 - 04 - 05'', 2000, ''Activo'', 10, ''juanvivas''),
  (8, ''Mancuerna'', ''20kg'', ''Decolor negro todas'', ''2002 - 04 - 11'', 600, ''Activo'', 20, ''juanvivas''),
  (9, ''Plato'', ''2kg'', ''desfigurados'', ''2002 - 05 - 12'', 200, ''Activo'', 30, ''juanvivas''),
  (10, ''Silla turca'', ''Unico'', ''De color blanco'', ''2016 - 03 - 05'', 24500, ''Activo'', 2, ''javiers''),
  (11, ''Platos 3kg'', ''Unico'', ''Negros'', ''2011 - 12 - 09'', 2500, ''Activo'', 10, ''javiers''),
  (12, ''Pesa 1kg'', ''Unico'', ''Negras'', ''2016 - 12 - 13'', 2000, ''Activo'', 3, ''javiers'')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `name` = VALUES(`name`), `model` = VALUES(`model`),
  `description`              = VALUES(`description`), `ad_date` = VALUES(`ad_date`), `cost` = VALUES(`cost`),
  `state`                    = VALUES(`state`), `quantity` = VALUES(`quantity`), `user_id` = VALUES(`user_id`);

DROP TABLE IF EXISTS `expenses`;
CREATE TABLE `expenses` (
  `id`          INT(11)         NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(50)     NOT NULL,
  `date`        DATE            NOT NULL,
  `total`       DOUBLE UNSIGNED NOT NULL,
  `user_id`     VARCHAR(15)     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `expenses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

INSERT INTO `expenses` (`id`, `description`, `date`, `total`, `user_id`) VALUES
  (1, ''Arreglo mueble'', ''2016 - 12 - 06'', 20000, ''paovera''),
  (2, ''Pago nomina total mes de diciembre'', ''2016 - 12 - 06'', 345000, ''paovera''),
  (4, ''Compra gatorade cafetin'', ''2016 - 12 - 06'', 45000, ''juanvivas''),
  (5, ''Compra yogurt varios sabores'', ''2016 - 12 - 06'', 30000, ''juanvivas''),
  (6, ''Prueba de engreso insertadoooo'', ''2016 - 10 - 02'', 13450.56, ''javiers''),
  (7, ''Prueba de engreso insertadoooo22'', ''2016 - 10 - 02'', 13450.56, ''javiers''),
  (8, ''Compra de cloro'', ''2016 - 12 - 02'', 2000, ''javiers''),
  (9, ''Compra de mancuerta'', ''2016 - 12 - 31'', 2500, ''javiers'')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `description` = VALUES(`description`), `date` = VALUES(`date`),
  `total`                    = VALUES(`total`), `user_id` = VALUES(`user_id`);

DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id`          INT(11)         NOT NULL AUTO_INCREMENT,
  `customer_id` VARCHAR(10)     NOT NULL,
  `description` VARCHAR(50)     NOT NULL,
  `date`        DATE            NOT NULL,
  `sub_total`   DOUBLE UNSIGNED NOT NULL,
  `iva`         DOUBLE UNSIGNED NOT NULL DEFAULT ''0.12'',
  `total`       DOUBLE UNSIGNED NOT NULL,
  `user_id`     VARCHAR(15)     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `income_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`),
  CONSTRAINT `income_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ci`)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8;

INSERT INTO `income` (`id`, `customer_id`, `description`, `date`, `sub_total`, `iva`, `total`, `user_id`) VALUES
  (1, ''V - 23942234'', ''Mensualidad'', ''2016 - 12 - 06'', 2000, 0.12, 2240, ''paovera''),
  (2, ''V - 03245623'', ''Mensualidad + Gatorade'', ''2016 - 12 - 06'', 3000, 0.12, 3360, ''paovera''),
  (3, ''V - 20431975'', ''Servicio Quincenal'', ''2016 - 12 - 13'', 1000, 0.12, 1200, ''paovera''),
  (4, ''E - 12345678'', ''Servicio Diario'', ''2016 - 12 - 10'', 200, 0.12, 224, ''javiers''),
  (5, ''E - 12345678'', ''Servicio Quincenal'', ''2016 - 12 - 13'', 100, 0.12, 1200, ''paovera''),
  (14, ''V - 20431973'', ''Servicio Quincenal'', ''2016 - 12 - 14'', 3000, 0.12, 3360, ''javiers''),
  (15, ''V - 22435987'', ''Servicio Mensual'', ''2016 - 12 - 14'', 5000, 0.12, 5600, ''javiers''),
  (16, ''V - 20431976'', ''Servicio Mensual'', ''2016 - 12 - 14'', 5000, 0.12, 5600, ''javiers''),
  (17, ''V - 08007748'', ''Servicio Diario'', ''2016 - 12 - 14'', 1000, 0.12, 1120, ''javiers''),
  (18, ''V - 08007748'', ''Servicio Diario'', ''2016 - 12 - 14'', 4000, 0.12, 4480, ''javiers''),
  (19, ''V - 20431972'', ''Servicio Quincenal'', ''2016 - 12 - 14'', 2500, 0.12, 2800, ''javiers'')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `customer_id` = VALUES(`customer_id`),
  `description`              = VALUES(`description`), `date` = VALUES(`date`), `sub_total` = VALUES(`sub_total`),
  `iva`                      = VALUES(`iva`), `total` = VALUES(`total`), `user_id` = VALUES(`user_id`);

DELIMITER ;;

CREATE TRIGGER `sub_total_bi`
BEFORE INSERT ON `income`
FOR EACH ROW
  BEGIN
    IF NEW.sub_total > 0
    THEN
      SET @sub_total = -(NEW.sub_total);
    END IF;
  END;;

DELIMITER ;

DROP TABLE IF EXISTS `income_product`;
CREATE TABLE `income_product` (
  `income_id`  INT(11)          NOT NULL,
  `product_id` INT(11)          NOT NULL,
  `quantity`   INT(10) UNSIGNED NOT NULL,
  KEY `income_id` (`income_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `income_product_ibfk_1` FOREIGN KEY (`income_id`) REFERENCES `income` (`id`),
  CONSTRAINT `income_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `income_product` (`income_id`, `product_id`, `quantity`) VALUES
  (2, 2, 1)
ON DUPLICATE KEY UPDATE `income_id` = VALUES(`income_id`), `product_id` = VALUES(`product_id`),
  `quantity`                        = VALUES(`quantity`);

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `ci`         VARCHAR(10)                        NOT NULL,
  `name`       VARCHAR(20)                        NOT NULL,
  `last_name`  VARCHAR(20)                        NOT NULL,
  `birth_date` DATE                               NOT NULL,
  `gender`     ENUM (''Masculino'', ''Femenino'') NOT NULL,
  `dir`        VARCHAR(50)                        NOT NULL,
  `phone`      VARCHAR(12)                        NOT NULL,
  PRIMARY KEY (`ci`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `person` (`ci`, `name`, `last_name`, `birth_date`, `gender`, `dir`, `phone`) VALUES
  (''E - 12345678'', ''Jorge'', ''Coll'', ''1998 - 12 - 11'', ''Masculino'', ''La joya'', ''0424 - 7804473''),
  (''V - 03245623'', ''Julio'', ''Cabrera'', ''1960 - 06 - 24'', ''Masculino'', ''Tabay'', ''0416 - 4323455''),
  (''V - 07421344'', ''Maria'', ''Villa'', ''1945 - 03 - 05'', ''Femenino'', ''Las Americas'', ''0274 - 2525544''),
  (''V - 08007748'', ''Lula'', ''Contreras'', ''1987 - 12 - 24'', ''Femenino'', ''Santa Barbara'', ''0412 - 1231241''),
  (''V - 19895266'', ''Julian'', ''Lacruz'', ''1991 - 03 - 05'', ''Masculino'', ''Ejido'', ''0416 - 2739235''),
  (''V - 20321312'', ''Luisa'', ''Ruiz'', ''1992 - 04 - 12'', ''Femenino'', ''Los proceres'', ''0424 - 2423423''),
  (''V - 20431972'', ''Josefa'', ''Elias'', ''1989 - 08 - 31'', ''Femenino'', ''La montana'', ''0416 - 2346465''),
  (''V - 20431973'', ''Jose'', ''Marquez'', ''2010 - 08 - 06'', ''Masculino'', ''Su casa'', ''0424 - 7804473''),
  (''V - 20431975'', ''Juan Andres'', ''Vivas Contreras'', ''1992 - 09 - 16'', ''Masculino'', ''Santa Maria Sur'', ''0424 - 7804473''),
  (''V - 20431976'', ''Maria '', ''Rodriguez'', ''1992 - 12 - 17'', ''Femenino'', ''Ejido'', ''0412 - 3423423''),
  (''V - 20435560'', ''Paola'', ''Vera'', ''1991 - 06 - 20'', ''Femenino'', ''El trapiche'', ''0424 - 7005096''),
  (''V - 21365922'', ''Javier'', ''Solsona'', ''1993 - 07 - 07'', ''Masculino'', ''Paseo la fera'', ''0416 - 4786475''),
  (''V - 22435987'', ''David'', ''Garrido'', ''1992 - 10 - 07'', ''Masculino'', ''Las Gonzales'', ''0274 - 2443283''),
  (''V - 23453464'', ''Fabiola'', ''Araque'', ''1995 - 10 - 10'', ''Femenino'', ''Cardenal Quintero'', ''0412 -
   2323453''),
  (''V - 23497463'', ''Mudafar'', ''El Halabi'', ''1989 - 07 - 17'', ''Masculino'', ''La joya'', ''0416 - 0466440''),
  (''V - 23942234'', ''Carlos'', ''Rojas'', ''1976 - 04 - 03'', ''Masculino'', ''Centro'', ''0274 - 2342144'')
ON DUPLICATE KEY UPDATE `ci` = VALUES(`ci`), `name` = VALUES(`name`), `last_name` = VALUES(`last_name`),
  `birth_date`               = VALUES(`birth_date`), `gender` = VALUES(`gender`), `dir` = VALUES(`dir`),
  `phone`                    = VALUES(`phone`);

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id`                 INT(11)          NOT NULL AUTO_INCREMENT,
  `description`        VARCHAR(50)      NOT NULL,
  `price`              DOUBLE UNSIGNED  NOT NULL,
  `quantity_available` INT(10) UNSIGNED NOT NULL,
  `supplier_rif`       VARCHAR(11)      NOT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_rif` (`supplier_rif`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`supplier_rif`) REFERENCES `supplier` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8;

INSERT INTO `product` (`id`, `description`, `price`, `quantity_available`, `supplier_rif`) VALUES
  (2, ''Gatorade'', 1000, 100, ''J - 2313423''),
  (3, ''Yogurt Fresa'', 750, 15, ''J - 32342344''),
  (4, ''Yogurt Mora'', 750, 15, ''J - 32342344''),
  (5, ''Refresco'', 450, 30, ''J - 2313423''),
  (6, ''Galleta de avena'', 500, 50, ''V - 21321333''),
  (7, ''Galleta de mantequilla grande'', 800, 10, ''V - 21321333''),
  (13, ''Frescolita'', 450, 10, ''J - 2313423'')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `description` = VALUES(`description`), `price` = VALUES(`price`),
  `quantity_available`       = VALUES(`quantity_available`), `supplier_rif` = VALUES(`supplier_rif`);

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `customer_id` VARCHAR(10)                                   NOT NULL,
  `type_s`      ENUM (''Mensual'', ''Quincenal'', ''Diario'') NOT NULL,
  `init_date`   DATE                                          NOT NULL,
  `income_id`   INT(11)                                       NOT NULL,
  PRIMARY KEY (`income_id`, `customer_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `service_ibfk_3` FOREIGN KEY (`income_id`) REFERENCES `income` (`id`),
  CONSTRAINT `service_ibfk_4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ci`)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `service` (`customer_id`, `type_s`, `init_date`, `income_id`) VALUES
  (''V - 23942234'', ''Mensual'', ''2016 - 12 - 06'', 1),
  (''V - 03245623'', ''Mensual'', ''2016 - 12 - 06'', 2),
  (''E - 12345678'', ''Diario'', ''2016 - 12 - 10'', 3),
  (''V - 20431975'', ''Quincenal'', ''2016 - 12 - 09'', 3),
  (''E - 12345678'', ''Quincenal'', ''2016 - 12 - 13'', 4),
  (''V - 20431973'', ''Quincenal'', ''2016 - 12 - 15'', 14),
  (''V - 22435987'', ''Mensual'', ''2017 - 01 - 09'', 15),
  (''V - 20431976'', ''Mensual'', ''2016 - 12 - 20'', 16),
  (''V - 08007748'', ''Diario'', ''2016 - 12 - 14'', 17),
  (''V - 20431972'', ''Quincenal'', ''2016 - 12 - 15'', 19)
ON DUPLICATE KEY UPDATE `customer_id` = VALUES(`customer_id`), `type_s` = VALUES(`type_s`),
  `init_date`                         = VALUES(`init_date`), `income_id` = VALUES(`income_id`);

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id`           VARCHAR(11) NOT NULL,
  `name`         VARCHAR(20) NOT NULL,
  `phone`        VARCHAR(12) NOT NULL,
  `dir`          VARCHAR(50) NOT NULL,
  `product_desc` VARCHAR(30) NOT NULL,
  `user_id`      VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `supplier` (`id`, `name`, `phone`, `dir`, `product_desc`, `user_id`) VALUES
  (''J - 2313423'', ''Pepsi - Cola'', ''0212 - 2342324'', ''Caracas'', ''Refrescos y Gatorade'', ''juanvivas''),
  (''J - 30383695'', ''Carmen Contreras'', ''0274 - 2525596'', ''Santa Maria Sur'', ''Servicio de gerencia'',
   ''javiers''),
  (''J - 32342344'', ''Jose Colmenares'', ''0414 - 2342342'', ''El valle'', ''Yogurt'', ''juanvivas''),
  (''V - 21321333'', ''Luis Rodriguez'', ''0416 - 5645634'', ''La mucuy'', ''Galletas y tortas'', ''juanvivas'')
ON DUPLICATE KEY UPDATE `id` = VALUES(`id`), `name` = VALUES(`name`), `phone` = VALUES(`phone`), `dir` = VALUES(`dir`),
  `product_desc`             = VALUES(`product_desc`), `user_id` = VALUES(`user_id`);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` VARCHAR(15)                                          NOT NULL,
  `pass`     VARCHAR(15)                                          NOT NULL,
  `rol`      ENUM (''Recepcion'', ''Administrador'', ''Gerente'') NOT NULL,
  `user_ci`  VARCHAR(10)                                          NOT NULL,
  PRIMARY KEY (`username`),
  KEY `user_ci` (`user_ci`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_ci`) REFERENCES `person` (`ci`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `user` (`username`, `pass`, `rol`, `user_ci`) VALUES
  (''javiers'', ''1234'', ''Gerente'', ''V - 21365922''),
  (''juanvivas'', ''1234'', ''Administrador'', ''V - 20431975''),
  (''paovera'', ''1234'', ''Recepcion'', ''V - 20435560'')
ON DUPLICATE KEY UPDATE `username` = VALUES(`username`), `pass` = VALUES(`pass`), `rol` = VALUES(`rol`),
  `user_ci`                        = VALUES(`user_ci`);

-- 2016-12-14 16:50:18
