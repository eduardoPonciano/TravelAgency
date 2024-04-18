-- Criando o esquema 'booking_app' se não existir
CREATE SCHEMA IF NOT EXISTS booking_user
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA booking_user IS 'standard public schema';

CREATE SEQUENCE IF NOT EXISTS booking_user.admin_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

ALTER SEQUENCE booking_user.admin_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_user.admin_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_user.customer_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_user.customer_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_user.customer_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_user.hotel_manager_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_user.hotel_manager_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_user.hotel_manager_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_user.role_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_user.role_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_user.role_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_user.user_plataform_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_user.user_plataform_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_user.user_plataform_id_seq TO postgres;


CREATE TABLE IF NOT EXISTS booking_user."role" (
	id bigserial NOT NULL,
	role_type varchar(255) NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id),
	CONSTRAINT role_role_type_check CHECK (((role_type)::text = ANY ((ARRAY['ADMIN'::character varying, 'CUSTOMER'::character varying, 'HOTEL_MANAGER'::character varying])::text[]))),
	CONSTRAINT uk_8nhufvk7ufr23s4xoqglqtbdx UNIQUE (role_type)
);


ALTER TABLE booking_user."role" OWNER TO postgres;
GRANT ALL ON TABLE booking_user."role" TO postgres;


CREATE TABLE IF NOT EXISTS booking_user.user_plataform (
	id bigserial NOT NULL,
	created_date timestamp(6) NULL,
	last_name varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT uk_ipuumrelaucujf092h1yl28vr UNIQUE (username),
	CONSTRAINT user_plataform_pkey PRIMARY KEY (id),
	CONSTRAINT fkfgb4k0w4yim6cngqsirti4de8 FOREIGN KEY (role_id) REFERENCES booking_user."role"(id)
);


ALTER TABLE booking_user.user_plataform OWNER TO postgres;
GRANT ALL ON TABLE booking_user.user_plataform TO postgres;



CREATE TABLE IF NOT EXISTS booking_user."admin" (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT admin_pkey PRIMARY KEY (id),
	CONSTRAINT uk_hawikyhwwfvbnog5byokutpff UNIQUE (user_id),
	CONSTRAINT fki30uarvfaj7c9twhyvea6xubm FOREIGN KEY (user_id) REFERENCES booking_user.user_plataform(id)
);


ALTER TABLE booking_user."admin" OWNER TO postgres;
GRANT ALL ON TABLE booking_user."admin" TO postgres;



CREATE TABLE IF NOT EXISTS booking_user.customer (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
	CONSTRAINT uk_j7ja2xvrxudhvssosd4nu1o92 UNIQUE (user_id),
	CONSTRAINT fk3sht02nf1aqk150eppvg5f3yr FOREIGN KEY (user_id) REFERENCES booking_user.user_plataform(id)
);

-- Permissions

ALTER TABLE booking_user.customer OWNER TO postgres;
GRANT ALL ON TABLE booking_user.customer TO postgres;


CREATE TABLE IF NOT EXISTS booking_user.hotel_manager (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT hotel_manager_pkey PRIMARY KEY (id),
	CONSTRAINT uk_82ejf160xpaxvgstsl5t6xyay UNIQUE (user_id),
	CONSTRAINT fklscaugkhvx3qg4kfvtsykbh66 FOREIGN KEY (user_id) REFERENCES booking_user.user_plataform(id)
);


ALTER TABLE booking_user.hotel_manager OWNER TO postgres;
GRANT ALL ON TABLE booking_user.hotel_manager TO postgres;


GRANT ALL ON SCHEMA booking_user TO pg_database_owner;