CREATE SCHEMA IF NOT EXISTS appfacturacion;


USE appfacturacion;

CREATE TABLE cliente (
    cliente_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    dni INT NOT NULL,
    correo_electronico VARCHAR(255),
    PRIMARY KEY (cliente_id)
);

CREATE TABLE producto (
    id_producto INT NOT NULL AUTO_INCREMENT,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255),
    codigo_producto VARCHAR(255) NOT NULL,
    precio DECIMAL(38,2) NOT NULL,
    stock INT NOT NULL,
    cantidad_vendida INT,
    caracteristicas VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_producto)
);

CREATE TABLE venta (
    id_venta INT NOT NULL AUTO_INCREMENT,
    fecha_creacion DATE NOT NULL,
    precio_total DECIMAL(38,2) NOT NULL,
    cliente_id INT NOT NULL,
    completa BIT,
    PRIMARY KEY (id_venta),
    CONSTRAINT clienteFK FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE ventas_productos (
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    PRIMARY KEY (id_venta, id_producto),
    CONSTRAINT ventaFK FOREIGN KEY (id_venta) REFERENCES venta(id_venta),
    CONSTRAINT productoFK FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);


INSERT INTO `clientes` (`nombre`, `apellido`, `dni`, `correo_electronico`) VALUES
('Evelyn', 'Pereyra', 36794385, 'pereyrae582@gmail.com'),
('Antonio', 'Fernandez', 2365896, 'af@hotmail.com'),
('Mariela', 'Bautisteza', 5326987, 'mari@yahoo.com');

INSERT INTO `productos` (`marca`, `modelo`, `codigo_producto`, `caracteristicas`, `precio`, `stock`) VALUES
('Xiaomi', 'Redmi S10', 'TEL01', 'Memoria interna 125Gb', 250000, 5),
('Acer', 'Aspire 3', 'Not01', '12 Gb Ram', 500000, 10),
('Iphone', 'S9', 'Tel02', 'Memoria interna 250 Gb', 10000000, 2);

