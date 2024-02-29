CREATE SCHEMA IF NOT EXISTS appfacturacion;


USE appfacturacion;

CREATE TABLE clientes (
    cliente_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    dni INT NOT NULL,
    correo_electronico VARCHAR(255),
    PRIMARY KEY (cliente_id)
);

CREATE TABLE productos (
    id_producto INT NOT NULL AUTO_INCREMENT,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255),
    codigo_producto VARCHAR(255) NOT NULL,
    precio DECIMAL(38,2) NOT NULL,
    stock INT NOT NULL,
    total_venta INT,
    cantidad_vendida INT,
    caracteristicas VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_producto)
);

CREATE TABLE ventas (
    id_venta INT NOT NULL AUTO_INCREMENT,
    fecha_creacion DATE NOT NULL,
    total_venta DECIMAL(38,2) NOT NULL,
    cliente_id BIGINT NOT NULL,
    completa BIT,
    PRIMARY KEY (id_venta),
    CONSTRAINT clienteFK FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE detalle_venta(
id_venta_detalle INT NOT NULL AUTO_INCREMENT,
precio DECIMAL(38,2),
id_producto BIGINT,
id_venta BIGINT,
caracteristica vARCHAR(255),
marca VARCHAR(255),
modelo VARCHAR(255),
PRIMARY KEY (id_venta_detalle)

);

CREATE TABLE detalle_venta (
    id_venta_detalle INT NOT NULL AUTO_INCREMENT,
    precio DECIMAL(38,2),
    id_producto BIGINT,
    id_venta BIGINT,
    caracteristica VARCHAR(255),
    marca VARCHAR(255),
    modelo VARCHAR(255),
    PRIMARY KEY (id_venta_detalle),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_venta) REFERENCES ventas(id_venta)
);






