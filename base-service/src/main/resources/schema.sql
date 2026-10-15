-- ==========================================================
-- SCRIPT DDL: Red de Huertos Urbanos Comunitarios
-- Base de datos: parcel_db (PostgreSQL)
-- NOTA: Spring Boot con Hibernate genera esta tabla
-- automáticamente al iniciar (ddl-auto=update).
-- ==========================================================

CREATE TABLE IF NOT EXISTS parcels (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    soil_type VARCHAR(20) NOT NULL,
    owner_or_lead VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Datos de prueba opcionales
INSERT INTO parcels (name, location, soil_type, owner_or_lead, created_at)
VALUES 
('Huerto Experimental Norte', '19.4326,-99.1332', 'ARCILLOSO', 'Dra. Elena Ramos', CURRENT_TIMESTAMP),
('Huerto Sur Los Olivos', '20.6596,-103.3496', 'ARENOSO', 'Biol. Carlos Fuentes', CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;
