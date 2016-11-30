-- Adminer 4.2.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `testgym`;
CREATE DATABASE `testgym` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testgym`;

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
  `id_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`id_egreso`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `egreso_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`ci_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ingresos`;
CREATE TABLE `ingresos` (
  `id_ingreso` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `iva` float unsigned NOT NULL DEFAULT '0.12',
  `sub_total` float unsigned NOT NULL,
  `total` float unsigned NOT NULL,
  `ci_cliente` varchar(10) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`id_ingreso`),
  KEY `ci_cliente` (`ci_cliente`),
  KEY `id_producto` (`id_producto`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ingresos_ibfk_1` FOREIGN KEY (`ci_cliente`) REFERENCES `cliente` (`ci_cliente`),
  CONSTRAINT `ingresos_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `ingresos_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`ci_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DELIMITER ;;

CREATE TRIGGER `sub_total_bi` BEFORE INSERT ON `ingresos` FOR EACH ROW
  BEGIN
    IF NEW.sub_total > 0 THEN
      SET @sub_total = -(NEW.sub_total);
    END IF;
  END;;

DELIMITER ;

DROP TABLE IF EXISTS `ingreso_producto`;
CREATE TABLE `ingreso_producto` (
  `id_ingreso` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) unsigned NOT NULL DEFAULT '1',
  KEY `id_ingreso` (`id_ingreso`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `ingreso_producto_ibfk_1` FOREIGN KEY (`id_ingreso`) REFERENCES `ingresos` (`id_ingreso`),
  CONSTRAINT `ingreso_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `inventario_equipo`;
CREATE TABLE `inventario_equipo` (
  `id_equipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `fecha_compra` date NOT NULL,
  `costo` float NOT NULL,
  `estado` enum('Activo','Inactivo') NOT NULL,
  `id_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`id_equipo`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `inventario_equipo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`ci_usuario`)
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


DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `precio` float unsigned NOT NULL,
  `cantidad_disponible` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `provee`;
CREATE TABLE `provee` (
  `rif_proveedor` varchar(10) NOT NULL,
  `id_producto` int(11) NOT NULL,
  KEY `rif_proveedor` (`rif_proveedor`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `provee_ibfk_1` FOREIGN KEY (`rif_proveedor`) REFERENCES `proveedor` (`rif`),
  CONSTRAINT `provee_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `rif` varchar(10) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `desc_producto` varchar(20) NOT NULL,
  `id_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`rif`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`ci_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `servicio`;
CREATE TABLE `servicio` (
  `tipo` enum('Diario','Quincenal','Mensual') NOT NULL,
  `fecha_inicio` date NOT NULL,
  `id_ingreso` int(11) NOT NULL,
  `ci_cliente` varchar(10) NOT NULL,
  PRIMARY KEY (`id_ingreso`,`ci_cliente`),
  KEY `ci_cliente` (`ci_cliente`),
  CONSTRAINT `servicio_ibfk_1` FOREIGN KEY (`id_ingreso`) REFERENCES `ingresos` (`id_ingreso`),
  CONSTRAINT `servicio_ibfk_2` FOREIGN KEY (`ci_cliente`) REFERENCES `cliente` (`ci_cliente`)
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


-- 2016-11-30 03:58:36
