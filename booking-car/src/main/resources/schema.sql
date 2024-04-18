-- Criando o esquema 'booking_app' se não existir
CREATE SCHEMA IF NOT EXISTS booking_car
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA booking_car IS 'standard public schema';
