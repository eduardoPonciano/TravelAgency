INSERT INTO booking_app."role" (role_type) VALUES
	 ('ADMIN'),
	 ('CUSTOMER'),
	 ('HOTEL_MANAGER') ON CONFLICT (role_type)
DO NOTHING;
	
INSERT INTO booking_app.user_plataform (created_date,last_name,"name","password",username,role_id) VALUES
	 ('2024-04-02 14:38:31.746292','Admin','Admin','$2a$10$qPMYr/j8weHFto9GeMY8/.bvJwteUuvKfGsoOfzjnJfvg36C9suUu','admin@teste.com',1),
	 ('2024-04-02 18:04:01.454107','Cliente','Cliente','$2a$10$pyOEqsixxk3513Qe05zxP.bBxBFYbKMmb0DZ70O7n1xFYVjXdVhpO','customer@teste.com',2),
	 ('2024-04-02 22:55:14.821853','Manager','Primeiro','$2a$10$M94y/3OIXZ/hlhlelsrKBeMLjb9K86B0z.zx74CxuKNEQxRvz2xye','manager1@teste.com',3),
	 ('2024-04-02 22:55:14.821853','Manager','Segundo','$2a$10$M94y/3OIXZ/hlhlelsrKBeMLjb9K86B0z.zx74CxuKNEQxRvz2xye','manager2@teste.com',3)
	 ON CONFLICT (username) DO NOTHING;

	
INSERT INTO booking_app."admin" (user_id) VALUES
	 (1) ON CONFLICT (user_id) DO NOTHING;
INSERT INTO booking_app.customer (user_id) VALUES
	 (2) ON CONFLICT (user_id) DO NOTHING;
	
INSERT INTO booking_app.hotel_manager (user_id) VALUES
	 (3),(4) ON CONFLICT (user_id) DO NOTHING;
	 
	 

INSERT INTO booking_app.address (address_line,city,country) VALUES
	 ('Big Ben, Westminster, London SW1A', 'Londres', 'Reino Unido'),
	 ('Palácio de Buckingham, Buckingham Palace Rd, Westminster, London SW1A 1AA', 'Londres', 'Reino Unido'),
	 ('Taj Mahal, Dharmapuri, Forest Colony, Tajganj, Agra, Uttar Pradesh 282001', 'Agra', 'Índia'),
	 ('Sydney Opera House, Sydney', 'Sydney', 'Austrália'),
	 ('Budapester Str. 2, Mitte, 10787','Berlin','Germany'),
	 ('Unter den Linden 77','Berlin','Germany'),
	 ('Champ de Mars, 5 Av. Anatole France, 75007','Paris','França'),
	 ('Central Park','Nova York','EUA'),
	 ('Grand Canyon','Arizona','EUA'),
	 ('Palácio de Versalhes','Versalhes','França')
	  ON CONFLICT (address_line) DO NOTHING;
INSERT INTO booking_app.address (address_line,city,country) VALUES
	 ('Deserto do Saara','Saara','Africa'),
	 ('Monte Everest','Everest','Nepal'),
	 ('Pirâmides de Gizé','Gizé','Egito'),
	 ('Machu Picchu','Machu Picchu','Peru'),
	 ('Copacabana','Rio de Janeiro','Brasil'),
	 ('Muralha da China','China','China'),
	 ('Praias de Phuket','Phuket','Tailândia'),
	 ('Alpes Suíços','Alpes','Suiça'),
	 ('Parque Nacional Yellowston','Yellowston','EUA'),
	 ('Sagrada Família em Barcelona','Barcelona','Espanha')
	  ON CONFLICT (address_line) DO NOTHING;
	  
	  
INSERT INTO booking_app.hotel ("name", address_id,hotel_manager_id) VALUES
	 ('Swissotel The Bosphorus Istanbul',1,1),
	 ('Four Seasons Hotel Istanbul',2,1),
	 ('Ciragan Palace Kempinski Istanbul',3,1),
	 ('Hotel Adlon Kempinski Berlin',4,1),
	 ('The Ritz-Carlton Berlin',5,1),
	 ('InterContinental Berlin',6,1),
	 ('Vista Turística Hotel',7,1),
	 ('Ponto de Encontro Resort',8,1),
	 ('Destino Sonhado Lodge',9,1),
	 ('Jardins da Cidade Hotel',10,1)
	  ON CONFLICT ("name") DO NOTHING;
INSERT INTO booking_app.hotel ("name",address_id,hotel_manager_id) VALUES
	 ('Oásis Turístico Resort',11,2),
	 ('Horizon Travel Hotel',12,2),
	 ('Maravilhas do Turismo Lodge',13,2),
	 ('Pousada dos Viajantes',14,2),
	 ('Estância das Marés Hotel',15,2),
	 ('Acolhida do Viajante Resort',16,2),
	 ('Sol e Mar Lodge',17,2),
	 ('Montanha Encantada Hotel',18,2),
	 ('Refúgio Turístico Lodge',19,2),
	 ('Encanto da Cidade Resort',20,2)
	  ON CONFLICT ("name") DO NOTHING;

INSERT INTO booking_app.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (370.0,35,'SOLTEIRO',1),
	 (459.0,45,'CASAL',1),
	 (700.0,25,'SOLTEIRO',2),
	 (890.0,30,'CASAL',2),
	 (691.0,30,'SOLTEIRO',3),
	 (800.0,75,'CASAL',3),
	 (120.0,25,'SOLTEIRO',4),
	 (250.0,15,'CASAL',4),
	 (300.0,50,'SOLTEIRO',5),
	 (400.0,50,'CASAL',5)
	 ON CONFLICT DO NOTHING;
INSERT INTO booking_app.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (179.0,45,'SOLTEIRO',6),
	 (256.0,25,'CASAL',6),
	 (800.0,10,'SOLTEIRO',7),
	 (500.0,30,'CASAL',7),
	 (800.0,30,'SOLTEIRO',8),
	 (600.0,80,'CASAL',8),
	 (1000.0,10,'SOLTEIRO',9),
	 (600.0,10,'CASAL',9),
	 (100.0,10,'SOLTEIRO',10),
	 (100.0,10,'CASAL',10)
	 ON CONFLICT DO NOTHING;
INSERT INTO booking_app.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (1000.0,1,'SOLTEIRO',11),
	 (1000.0,1,'CASAL',11),
	 (2000.0,1,'SOLTEIRO',12),
	 (1000.0,1,'CASAL',12),
	 (80.0,10,'SOLTEIRO',13),
	 (50.0,10,'CASAL',13),
	 (100.0,10,'SOLTEIRO',14),
	 (80.0,20,'CASAL',14),
	 (1000.0,10,'SOLTEIRO',15),
	 (600.0,50,'CASAL',15)
	 ON CONFLICT DO NOTHING;
INSERT INTO booking_app.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (150.0,10,'SOLTEIRO',16),
	 (110.0,30,'CASAL',16),
	 (650.0,5,'SOLTEIRO',17),
	 (450.0,15,'CASAL',17),
	 (1500.0,10,'SOLTEIRO',18),
	 (900.0,80,'CASAL',18),
	 (750.0,10,'SOLTEIRO',19),
	 (350.0,40,'CASAL',19),
	 (750.0,10,'SOLTEIRO',20),
	 (349.98,30,'CASAL',20)
	 ON CONFLICT DO NOTHING;

INSERT INTO booking_app.availability (available_rooms,"date",hotel_id,room_id) VALUES
	 (5,'2023-09-01',4,7),
	 (7,'2023-09-02',4,8),
	 (24,'2024-04-02',6,12),
	 (1,'2024-04-05',16,30);
