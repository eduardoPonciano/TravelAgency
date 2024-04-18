
CREATE SCHEMA IF NOT EXISTS booking_hotel
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA booking_hotel IS 'standard public schema';

CREATE SEQUENCE IF NOT EXISTS booking_hotel.address_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_hotel.address_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_hotel.address_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_hotel.booked_room_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_hotel.booked_room_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_hotel.booked_room_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_hotel.booking_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_hotel.booking_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_hotel.booking_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_hotel.hotel_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_hotel.hotel_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_hotel.hotel_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_hotel.room_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_hotel.room_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_hotel.room_id_seq TO postgres;


CREATE SEQUENCE IF NOT EXISTS booking_hotel.type_bed_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


ALTER SEQUENCE booking_hotel.type_bed_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE booking_hotel.type_bed_id_seq TO postgres;


CREATE TABLE IF NOT EXISTS booking_hotel.address (
	id bigserial NOT NULL,
	address_line varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	country varchar(255) NOT NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT address_unique UNIQUE (address_line)
);


ALTER TABLE booking_hotel.address OWNER TO postgres;
GRANT ALL ON TABLE booking_hotel.address TO postgres;


CREATE TABLE IF NOT EXISTS booking_hotel.type_bed (
	id bigserial NOT NULL,
	capacity int4 NULL,
	description varchar(255) NULL,
	CONSTRAINT type_bed_pkey PRIMARY KEY (id),
	CONSTRAINT type_bed_unique UNIQUE (description)
);


ALTER TABLE booking_hotel.type_bed OWNER TO postgres;
GRANT ALL ON TABLE booking_hotel.type_bed TO postgres;


CREATE TABLE IF NOT EXISTS booking_hotel.hotel (
	id bigserial NOT NULL,
	hotel_manager_id int8 NULL,
	"name" varchar(255) NOT NULL,
	address_id int8 NULL,
	CONSTRAINT hotel_pkey PRIMARY KEY (id),
	CONSTRAINT uk_dcpycvarhghd0g3l6reewmsak UNIQUE (name),
	CONSTRAINT uk_owt8iiq4d3dff6uvmdyjbmmar UNIQUE (address_id),
	CONSTRAINT fk48m0ei7s6biikxbrku04slp0s FOREIGN KEY (address_id) REFERENCES booking_hotel.address(id)
);


ALTER TABLE booking_hotel.hotel OWNER TO postgres;
GRANT ALL ON TABLE booking_hotel.hotel TO postgres;


CREATE TABLE IF NOT EXISTS booking_hotel.room (
	id bigserial NOT NULL,
	count int4 NULL,
	daily_price numeric(38, 2) NULL,
	hotel_id int8 NOT NULL,
	type_bed_id int8 NOT NULL,
	CONSTRAINT room_pkey PRIMARY KEY (id),
	CONSTRAINT fkdosq3ww4h9m2osim6o0lugng8 FOREIGN KEY (hotel_id) REFERENCES booking_hotel.hotel(id),
	CONSTRAINT fkib8ff038j2drha9bpqalsytqq FOREIGN KEY (type_bed_id) REFERENCES booking_hotel.type_bed(id)
);


ALTER TABLE booking_hotel.room OWNER TO postgres;
GRANT ALL ON TABLE booking_hotel.room TO postgres;


CREATE TABLE IF NOT EXISTS booking_hotel.booking (
	id bigserial NOT NULL,
	booking_date timestamp(6) NULL,
	checkin_date date NOT NULL,
	checkout_date date NOT NULL,
	confirmation_number varchar(255) NOT NULL,
	customer_id int8 NULL,
	hotel_id int8 NULL,
	CONSTRAINT booking_pkey PRIMARY KEY (id),
	CONSTRAINT uk_nxoscxy53bg6iswii0ku8ng5j UNIQUE (confirmation_number),
	CONSTRAINT fkhacdq9bfa3r9xdimovsnonbyi FOREIGN KEY (hotel_id) REFERENCES booking_hotel.hotel(id)
);


ALTER TABLE booking_hotel.booking OWNER TO postgres;
GRANT ALL ON TABLE booking_hotel.booking TO postgres;


CREATE TABLE IF NOT EXISTS booking_hotel.booked_room (
	id bigserial NOT NULL,
	count int4 NOT NULL,
	booking_id int8 NOT NULL,
	type_bed_id int8 NOT NULL,
	CONSTRAINT booked_room_pkey PRIMARY KEY (id),
	CONSTRAINT fk7u3obx7tj0depoc00uvo0gyb4 FOREIGN KEY (type_bed_id) REFERENCES booking_hotel.type_bed(id),
	CONSTRAINT fkbpl3is8trxr5ghidw8sik4ix1 FOREIGN KEY (booking_id) REFERENCES booking_hotel.booking(id)
);


ALTER TABLE booking_hotel.booked_room OWNER TO postgres;
GRANT ALL ON TABLE booking_hotel.booked_room TO postgres;


GRANT ALL ON SCHEMA booking_hotel TO pg_database_owner;