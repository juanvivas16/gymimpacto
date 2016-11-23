-- Adminer 4.2.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `testgym`;
CREATE DATABASE `testgym` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testgym`;

DROP TABLE IF EXISTS `cafetin`;
CREATE TABLE `cafetin` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(10) unsigned NOT NULL,
  `id_proveedor` varchar(10) NOT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `cafetin_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`rif`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `ci_cliente` varchar(10) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  PRIMARY KEY (`ci_cliente`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`ci_cliente`) REFERENCES `persona` (`ci`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `egreso`;
CREATE TABLE `egreso` (
  `id_egreso` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `fecha` date NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`id_egreso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ingresos`;
CREATE TABLE `ingresos` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `iva` float unsigned NOT NULL,
  `sub_total` float unsigned NOT NULL,
  `total` float unsigned NOT NULL,
  `ci_cliente` varchar(10) NOT NULL,
  `id_cafetin` int(11) NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `ci_cliente` (`ci_cliente`),
  KEY `id_cafetin` (`id_cafetin`),
  CONSTRAINT `ingresos_ibfk_1` FOREIGN KEY (`ci_cliente`) REFERENCES `cliente` (`ci_cliente`) ON DELETE NO ACTION,
  CONSTRAINT `ingresos_ibfk_2` FOREIGN KEY (`id_cafetin`) REFERENCES `cafetin` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `inventario_equipo`;
CREATE TABLE `inventario_equipo` (
  `id_equipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `fecha_compra` date NOT NULL,
  `costo` float NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_equipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `membresia`;
CREATE TABLE `membresia` (
  `ci_cliente` varchar(10) NOT NULL,
  `ci_usuario` varchar(10) NOT NULL,
  `tipo_membresia` varchar(10) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`ci_cliente`,`ci_usuario`),
  KEY `ci_usuario` (`ci_usuario`),
  CONSTRAINT `membresia_ibfk_1` FOREIGN KEY (`ci_cliente`) REFERENCES `cliente` (`ci_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `membresia_ibfk_2` FOREIGN KEY (`ci_usuario`) REFERENCES `usuario` (`ci_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `persona`;
CREATE TABLE `persona` (
  `ci` varchar(10) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `fecha_nac` date NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  PRIMARY KEY (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `personal`;
CREATE TABLE `personal` (
  `ci_personal` varchar(10) NOT NULL,
  `tipo_contrato` varchar(15) NOT NULL,
  `sueldo` float NOT NULL,
  `actividad` varchar(15) NOT NULL,
  PRIMARY KEY (`ci_personal`),
  CONSTRAINT `personal_ibfk_1` FOREIGN KEY (`ci_personal`) REFERENCES `persona` (`ci`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `rif` varchar(10) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `desc_producto` varchar(20) NOT NULL,
  PRIMARY KEY (`rif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `ci_usuario` varchar(10) NOT NULL,
  `nombre_usuario` varchar(15) NOT NULL,
  `clave` varchar(15) NOT NULL,
  `rol` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ci_usuario`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`ci_usuario`) REFERENCES `persona` (`ci`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2016-11-23 04:14:24
