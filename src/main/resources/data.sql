-- =============================================
-- DATOS DE PRUEBA PARA SISTEMA SABER PRO
-- =============================================

-- 1. INSERTAR ESTUDIANTES
INSERT INTO estudiantes (identificacion, nombre, apellido, email, telefono, fecha_registro) VALUES
('123456789', 'Juan Carlos', 'Barbosa', 'jbarbosa@universidad.edu.co', '3001234567', NOW()),
('987654321', 'Maria Fernanda', 'Quintero', 'mquintero@universidad.edu.co', '3007654321', NOW()),
('456789123', 'Carlos Alberto', 'Parra', 'cparra@universidad.edu.co', '3004567890', NOW()),
('789123456', 'Ana Maria', 'Anaya', 'aanaya@universidad.edu.co', '3007890123', NOW()),
('321654987', 'Luis Eduardo', 'Flor', 'lflor@universidad.edu.co', '3003216547', NOW());

-- 2. INSERTAR RESULTADOS DE EXAMENES - USANDO LOS VALORES CORRECTOS DEL ENUM
INSERT INTO resultados (estudiante_id, tipo_examen, modulo, puntaje, fecha_examen) VALUES
(1, 'SABER_PRO', 'Comunicación Escrita', 128.0, '2024-01-15'),
(1, 'SABER_PRO', 'Razonamiento Cuantitativo', 182.0, '2024-01-15'),
(1, 'SABER_PRO', 'Lectura Crítica', 202.0, '2024-01-15'),
(2, 'SABER_PRO', 'Comunicación Escrita', 125.0, '2024-01-15'),
(2, 'SABER_PRO', 'Razonamiento Cuantitativo', 151.0, '2024-01-15'),
(3, 'SABER_PRO', 'Lectura Crítica', 182.0, '2024-01-15'),
(3, 'SABER_PRO', 'Competencias Ciudadanas', 142.0, '2024-01-15');

-- 3. INSERTAR BENEFICIOS
INSERT INTO beneficios (estudiante_id, tipo_beneficio, descripcion, fecha_asignacion) VALUES
(1, 'Beca Excelencia', 'Beca por puntaje superior a 180 en Saber Pro', '2024-02-01'),
(2, 'Monitoría', 'Monitoría del área de Inglés', '2024-02-05'),
(3, 'Intercambio', 'Programa de intercambio internacional', '2024-02-10');