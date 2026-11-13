CREATE DATABASE conjunto_lumvet_db;

USE conjunto_lumvet_db;

CREATE TABLE residente (
	idUser INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipoDoc VARCHAR(10) NOT NULL,
    numDoc VARCHAR(20) NOT NULL UNIQUE,
    numCasa INT NOT NULL,
    numBloque INT NOT NULL
);

CREATE TABLE historial_ingreso (
	idIngreso INT PRIMARY KEY AUTO_INCREMENT,
    fechaHora DATETIME NOT NULL,
    tipoUsuario VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    tipoDoc VARCHAR(10) NOT NULL,
    numDoc VARCHAR(20) NOT NULL,
    idResidente INT NULL,
    empresa VARCHAR(100) NULL,
    cargo VARCHAR(50) NULL,
    idResidenteVisitado INT NULL,
    horaSalida DATETIME NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'Dentro',
    FOREIGN KEY (idResidente) REFERENCES residente(idUser),
    FOREIGN KEY (idResidenteVisitado) REFERENCES residente(idUser)
);

CREATE USER 'admin_conjunto_lumvet'@'localhost' IDENTIFIED BY '#LuMVet$15811';
GRANT ALL PRIVILEGES ON conjunto_lumvet_db.* TO 'admin_conjunto_lumvet'@'localhost';
FLUSH PRIVILEGES;

