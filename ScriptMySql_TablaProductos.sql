CREATE DATABASE prod;
USE prod;
CREATE TABLE productos (
    id INT PRIMARY KEY,
    codigo VARCHAR(10),
    nombre VARCHAR(100),
    precio DECIMAL(10, 2)
);
INSERT INTO productos (id, codigo, nombre, precio) VALUES
(1, 'S001', 'Monitor Samsung Ultra HD', 200),
(2, 'S002', 'TV Riviera 50 pulgadas', 125),
(3, 'S003', 'Impresora Laser BN', 125),
(4, 'S004', 'Impresora Laser Color', 625),
(5, 'S005', 'Parlantes', 25),
(6, 'S006', 'Monitor VGA', 50),
(7, 'S007', 'Impresora Tinta WF2630', 425),
(8, 'S008', 'Impresora Tinta LX9030', 525),
(9, 'A001', 'Disco Duro 2TB', 90),
(10, 'A002', 'Disco Duro 4TB', 120),
(11, 'A003', 'Disco Duro 8TB', 150),
(12, 'A004', 'Memoria USB 8GB', 40),
(13, 'A005', 'Memoria USB 16GB', 50),
(14, 'A006', 'Memoria USB 32GB', 70),
(15, 'P001', 'Memoria RAM 8GB', 60),
(16, 'P002', 'Memoria RAM 16GB', 100),
(17, 'P003', 'Procesador Core i7', 250),
(18, 'P004', 'Procesador Core i9', 400),
(19, 'P005', 'Procesador Core i5', 190),
(20, 'P006', 'Procesador AMD', 250),
(21, 'E001', 'Teclado Genius', 10),
(22, 'E002', 'Mouse Genius', 10),
(23, 'E003', 'Microfono', 15),
(24, 'E004', 'Mouse Gamer Inalambrico', 50),
(25, 'E005', 'Teclado Gamer Inalambrico', 60);