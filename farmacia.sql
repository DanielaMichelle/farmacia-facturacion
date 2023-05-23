CREATE DATABASE IF NOT EXISTS `farmacia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `farmacia`;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `sucursales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo_electronico` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `compras` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_compra` date NOT NULL,
  `hora_compra` time NOT NULL,
  `sucursal_id` bigint(20) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sucursal_id` (`sucursal_id`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `sucursal_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sucursal_id` (`sucursal_id`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `compras_productos` (
  `compra_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  `cantidad` bigint(20) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  KEY `compra_id` (`compra_id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `compras_productos_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`),
  CONSTRAINT `compras_productos_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sucursal_id` bigint(20) NOT NULL,
  `dni` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email` (`email`),
  UNIQUE KEY `uk_dni` (`dni`),
  KEY `sucursal_id` (`sucursal_id`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  KEY `keyrol_id` (`rol_id`),
  KEY `keyusuarios_id` (`usuario_id`),
  CONSTRAINT `usuarios_roles_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `usuarios_roles_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Volcando estructura para función farmacia.calcular_total_compra
DELIMITER //
CREATE FUNCTION `calcular_total_compra`(cantidad INT, precio DECIMAL(10, 2)) RETURNS decimal(10,2)
BEGIN
    DECLARE total DECIMAL(10, 2);
    SET total = cantidad * precio;
    RETURN total;
END//
DELIMITER ;

SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER actualizar_total_compra
AFTER INSERT ON compras_productos
FOR EACH ROW
BEGIN
    DECLARE total_compra DECIMAL(10, 2);
    SELECT SUM(calcular_total_compra(NEW.cantidad, productos.precio)) INTO total_compra
    FROM productos
    WHERE productos.id = NEW.producto_id;

    UPDATE compras
    SET total = total + total_compra
    WHERE id = NEW.compra_id;
END//
DELIMITER ;
DELIMITER //
CREATE TRIGGER control_duplicados_usuarios_roles
BEFORE INSERT ON usuarios_roles
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM usuarios_roles
    WHERE usuario_id = NEW.usuario_id AND rol_id = NEW.rol_id
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'No se pueden inserta el mismo producto, si desea puede editar la cantidad';
  END IF;
END//
DELIMITER ;

INSERT INTO `sucursales` (`id`, `nombre`, `direccion`, `telefono`, `correo_electronico`) VALUES
	(1, 'Sucursal 1', 'Calle San Miguel 123', '545645', 'sucursal1@ejemplo.com'),
	(2, 'Sucursal 2', 'Calle Polar 45', '445455454666', 'dfgdfgdf@ekgkg.com'),
	(3, 'Sucursal 3', 'José Olaya 121', '445455645', 'fghfgh@rktgdfkg.com');
INSERT INTO `roles` (`id`, `nombre`) VALUES
	(1, 'Gerente'),
	(2, 'Farmacéutico');
INSERT INTO `productos` (`id`, `nombre`, `precio`, `sucursal_id`) VALUES
	(1, 'Ibuprofeno', 3.00, 1),
	(2, 'Cetiricina generico', 5.00, 1),
	(3, 'Antalgina', 2.00, 1),
	(4, 'Suero', 15.00, 2);
INSERT INTO `stock` (`id`, `cantidad`, `producto_id`) VALUES
	(1, 3, 4),
	(2, 15, 4),
	(3, 10, 3),
	(4, 25, 4);
INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `dni`, `email`, `password`, `sucursal_id`) VALUES
	(1, 'Ricardo', 'Rivero', '12345678', 'admin@ucsm.edu.pe', '$2a$10$61BHefHH5zuX3XNB0PXCQux6wwgKV7xKMv7MtxzB1uls9rtm0UqUe', 1),
	(2, 'Daniela', 'Infantes', '87654321', 'dani@ucsm.edu.pe', '$2a$10$61BHefHH5zuX3XNB0PXCQux6wwgKV7xKMv7MtxzB1uls9rtm0UqUe', 2);
INSERT INTO `usuarios_roles` (`usuario_id`, `rol_id`) VALUES
	(1, 1),
	(2, 2);