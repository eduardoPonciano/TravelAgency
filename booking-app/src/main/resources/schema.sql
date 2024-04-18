-- Criando o esquema 'booking_app' se não existir
CREATE SCHEMA IF NOT EXISTS booking_app
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA booking_app IS 'standard public schema';

-- Criando sequências para cada entidade, com a cláusula IF NOT EXISTS para evitar criar sequências já existentes
CREATE SEQUENCE IF NOT EXISTS booking_app.address_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.address_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.address_id_seq TO postgres;

-- Repetir para as outras sequências...
CREATE SEQUENCE IF NOT EXISTS booking_app.admin_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.admin_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.admin_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.availability_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.availability_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.availability_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.booked_room_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.booked_room_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.booked_room_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.booking_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.booking_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.booking_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.customer_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.customer_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.customer_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.hotel_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.hotel_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.hotel_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.hotel_manager_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.hotel_manager_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.hotel_manager_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.payment_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.payment_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.payment_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.role_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.role_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.role_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.room_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.room_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.room_id_seq TO postgres;

CREATE SEQUENCE IF NOT EXISTS booking_app.user_plataform_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

ALTER SEQUENCE booking_app.user_plataform_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_app.user_plataform_id_seq TO postgres;

-- Criação das tabelas com IF NOT EXISTS para evitar criação duplicada
CREATE TABLE IF NOT EXISTS booking_app.address (
    id bigserial NOT NULL,
    address_line varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    country varchar(255) NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT address_unique UNIQUE (address_line)
);

ALTER TABLE booking_app.address OWNER TO postgres;
GRANT ALL ON TABLE booking_app.address TO postgres;

-- Repetir para as outras tabelas...
CREATE TABLE IF NOT EXISTS booking_app."role" (
    id bigserial NOT NULL,
    role_type varchar(255) NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT role_role_type_check CHECK (((role_type)::text = ANY ((ARRAY['ADMIN'::character varying, 'CUSTOMER'::character varying, 'HOTEL_MANAGER'::character varying])::text[]))),
    CONSTRAINT uk_8nhufvk7ufr23s4xoqglqtbdx UNIQUE (role_type)
);

ALTER TABLE booking_app."role" OWNER TO postgres;
GRANT ALL ON TABLE booking_app."role" TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.user_plataform (
    id bigserial NOT NULL,
    created_date timestamp(6) NULL,
    last_name varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT uk_ipuumrelaucujf092h1yl28vr UNIQUE (username),
    CONSTRAINT user_plataform_pkey PRIMARY KEY (id),
    CONSTRAINT fkfgb4k0w4yim6cngqsirti4de8 FOREIGN KEY (role_id) REFERENCES booking_app."role"(id)
);

ALTER TABLE booking_app.user_plataform OWNER TO postgres;
GRANT ALL ON TABLE booking_app.user_plataform TO postgres;

CREATE TABLE IF NOT EXISTS booking_app."admin" (
    id bigserial NOT NULL,
    user_id int8 NOT NULL,
    CONSTRAINT admin_pkey PRIMARY KEY (id),
    CONSTRAINT uk_hawikyhwwfvbnog5byokutpff UNIQUE (user_id),
    CONSTRAINT fki30uarvfaj7c9twhyvea6xubm FOREIGN KEY (user_id) REFERENCES booking_app.user_plataform(id)
);

ALTER TABLE booking_app."admin" OWNER TO postgres;
GRANT ALL ON TABLE booking_app."admin" TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.customer (
    id bigserial NOT NULL,
    user_id int8 NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id),
    CONSTRAINT uk_j7ja2xvrxudhvssosd4nu1o92 UNIQUE (user_id),
    CONSTRAINT fk3sht02nf1aqk150eppvg5f3yr FOREIGN KEY (user_id) REFERENCES booking_app.user_plataform(id)
);

ALTER TABLE booking_app.customer OWNER TO postgres;
GRANT ALL ON TABLE booking_app.customer TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.hotel_manager (
    id bigserial NOT NULL,
    user_id int8 NOT NULL,
    CONSTRAINT hotel_manager_pkey PRIMARY KEY (id),
    CONSTRAINT uk_82ejf160xpaxvgstsl5t6xyay UNIQUE (user_id),
    CONSTRAINT fklscaugkhvx3qg4kfvtsykbh66 FOREIGN KEY (user_id) REFERENCES booking_app.user_plataform(id)
);

ALTER TABLE booking_app.hotel_manager OWNER TO postgres;
GRANT ALL ON TABLE booking_app.hotel_manager TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.hotel (
    id bigserial NOT NULL,
    "name" varchar(255) NOT NULL,
    address_id int8 NULL,
    hotel_manager_id int8 NOT NULL,
    CONSTRAINT hotel_pkey PRIMARY KEY (id),
    CONSTRAINT uk_dcpycvarhghd0g3l6reewmsak UNIQUE (name),
    CONSTRAINT uk_owt8iiq4d3dff6uvmdyjbmmar UNIQUE (address_id),
    CONSTRAINT fk48m0ei7s6biikxbrku04slp0s FOREIGN KEY (address_id) REFERENCES booking_app.address(id),
    CONSTRAINT fkkcfgroubk0buae4mmn359qv87 FOREIGN KEY (hotel_manager_id) REFERENCES booking_app.hotel_manager(id)
);

ALTER TABLE booking_app.hotel OWNER TO postgres;
GRANT ALL ON TABLE booking_app.hotel TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.room (
    id bigserial NOT NULL,
    price_per_night float8 NOT NULL,
    room_count int4 NOT NULL,
    room_type varchar(255) NULL,
    hotel_id int8 NOT NULL,
    CONSTRAINT room_pkey PRIMARY KEY (id),
    CONSTRAINT room_room_type_check CHECK (((room_type)::text = ANY ((ARRAY['SOLTEIRO'::character varying, 'CASAL'::character varying])::text[]))),
    CONSTRAINT fkdosq3ww4h9m2osim6o0lugng8 FOREIGN KEY (hotel_id) REFERENCES booking_app.hotel(id),
	CONSTRAINT unique_room UNIQUE (price_per_night, room_count, room_type, hotel_id)
);

ALTER TABLE booking_app.room OWNER TO postgres;
GRANT ALL ON TABLE booking_app.room TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.availability (
    id bigserial NOT NULL,
    available_rooms int4 NOT NULL,
    "date" date NOT NULL,
    hotel_id int8 NOT NULL,
    room_id int8 NOT NULL,
    CONSTRAINT availability_pkey PRIMARY KEY (id),
    CONSTRAINT fk9wrk0t8eqhhaci64arkcllk8c FOREIGN KEY (hotel_id) REFERENCES booking_app.hotel(id),
    CONSTRAINT fkp6w1yyf6rerg3ihlnpdsgidrg FOREIGN KEY (room_id) REFERENCES booking_app.room(id)
);

ALTER TABLE booking_app.availability OWNER TO postgres;
GRANT ALL ON TABLE booking_app.availability TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.booking (
    id bigserial NOT NULL,
    booking_date timestamp(6) NULL,
    checkin_date date NOT NULL,
    checkout_date date NOT NULL,
    confirmation_number varchar(255) NOT NULL,
    customer_id int8 NOT NULL,
    hotel_id int8 NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (id),
    CONSTRAINT uk_nxoscxy53bg6iswii0ku8ng5j UNIQUE (confirmation_number),
    CONSTRAINT fkhacdq9bfa3r9xdimovsnonbyi FOREIGN KEY (hotel_id) REFERENCES booking_app.hotel(id),
    CONSTRAINT fklnnelfsha11xmo2ndjq66fvro FOREIGN KEY (customer_id) REFERENCES booking_app.customer(id)
);

ALTER TABLE booking_app.booking OWNER TO postgres;
GRANT ALL ON TABLE booking_app.booking TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.payment (
    id bigserial NOT NULL,
    currency varchar(255) NOT NULL,
    payment_date timestamp(6) NULL,
    payment_method varchar(255) NOT NULL,
    payment_status varchar(255) NOT NULL,
    total_price numeric(38, 2) NOT NULL,
    transaction_id varchar(255) NOT NULL,
    booking_id int8 NOT NULL,
    CONSTRAINT payment_currency_check CHECK (((currency)::text = ANY ((ARRAY['USD'::character varying, 'EUR'::character varying, 'TRY'::character varying])::text[]))),
    CONSTRAINT payment_payment_method_check CHECK (((payment_method)::text = ANY ((ARRAY['CREDIT_CARD'::character varying, 'DEBIT_CARD'::character varying, 'PAYPAL'::character varying])::text[]))),
    CONSTRAINT payment_payment_status_check CHECK (((payment_status)::text = ANY ((ARRAY['PENDING'::character varying, 'COMPLETED'::character varying, 'FAILED'::character varying, 'REFUNDED'::character varying])::text[]))),
    CONSTRAINT payment_pkey PRIMARY KEY (id),
    CONSTRAINT uk_ku02qy6369hn9uhy3n7jk9v6e UNIQUE (booking_id),
    CONSTRAINT uk_tacis04bqalsngo46yvxlo7yb UNIQUE (transaction_id),
    CONSTRAINT fkqewrl4xrv9eiad6eab3aoja65 FOREIGN KEY (booking_id) REFERENCES booking_app.booking(id)
);

ALTER TABLE booking_app.payment OWNER TO postgres;
GRANT ALL ON TABLE booking_app.payment TO postgres;

CREATE TABLE IF NOT EXISTS booking_app.booked_room (
    id bigserial NOT NULL,
    count int4 NOT NULL,
    room_type varchar(255) NOT NULL,
    booking_id int8 NOT NULL,
    CONSTRAINT booked_room_pkey PRIMARY KEY (id),
    CONSTRAINT booked_room_room_type_check CHECK (((room_type)::text = ANY ((ARRAY['SOLTEIRO'::character varying, 'CASAL'::character varying])::text[]))),
    CONSTRAINT fkbpl3is8trxr5ghidw8sik4ix1 FOREIGN KEY (booking_id) REFERENCES booking_app.booking(id)
);

ALTER TABLE booking_app.booked_room OWNER TO postgres;
GRANT ALL ON TABLE booking_app.booked_room TO postgres;

-- Permissões no esquema
GRANT ALL ON SCHEMA public TO pg_database_owner;
GRANT USAGE ON SCHEMA public TO public;
