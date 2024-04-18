-- Criando o esquema 'booking_app' se não existir
CREATE SCHEMA IF NOT EXISTS booking_airplane
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA booking_airplane IS 'standard public schema';
