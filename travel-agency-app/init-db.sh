#!/bin/sh
set -e

# Inicializa o banco de dados PostgreSQL
/etc/init.d/postgresql start

# Cria um banco de dados e um usuário
psql -U postgres -c "CREATE DATABASE hotelbooking;"
psql -U postgres -c "CREATE USER hotelbooking_user WITH PASSWORD 'password';"
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE hotelbooking TO hotelbooking_user;"
