-- Adminer 4.2.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `gimpacto`;
CREATE DATABASE `gimpacto` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gimpacto`;

DROP TABLE IF EXISTS `cafetin`;
CREATE TABLE `cafetin` (
  `cod_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `precio` float unsigned NOT NULL,
  `cantidad` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cod_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `cod_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_ingreso` date NOT NULL,
  `tipo_membresia` int(11) NOT NULL,
  `ci_cliente` varchar(10) NOT NULL,
  PRIMARY KEY (`cod_cliente`),
  KEY `ci_cliente` (`ci_cliente`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`ci_cliente`) REFERENCES `persona` (`ci`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ingresos`;
CREATE TABLE `ingresos` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `ingreso` float unsigned NOT NULL,
  `fecha` date NOT NULL,
  `cod_cliente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `cod_cliente` (`cod_cliente`),
  CONSTRAINT `ingresos_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `inventario`;
CREATE TABLE `inventario` (
  `cod_equipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `costo` float unsigned NOT NULL,
  `fecha_compra` date NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`cod_equipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `persona`;
CREATE TABLE `persona` (
  `ci` varchar(10) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `fecha_nac` date NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `personal`;
CREATE TABLE `personal` (
  `cod_personal` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_contrato` varchar(20) NOT NULL,
  `horario` varchar(50) NOT NULL,
  `salario` float NOT NULL,
  `ci_personal` varchar(10) NOT NULL,
  PRIMARY KEY (`cod_personal`),
  KEY `ci_personal` (`ci_personal`),
  CONSTRAINT `personal_ibfk_1` FOREIGN KEY (`ci_personal`) REFERENCES `persona` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `cod_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pass` varchar(30) NOT NULL,
  `rol` int(11) NOT NULL,
  `ci_persona` varchar(10) NOT NULL,
  PRIMARY KEY (`cod_usuario`),
  KEY `ci_persona` (`ci_persona`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ci_persona`) REFERENCES `persona` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2016-11-01 01:12:27
