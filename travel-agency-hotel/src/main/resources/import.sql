--hotel_init

	 ('Acısu Sokağı No:19, 34357','Istanbul','Turkey'),
	 ('Çırağan Cd. No:28, 34349 Beşiktaş','Istanbul','Turkey'),
	 ('Çırağan Cd. No:32, 34349 Beşiktaş','Istanbul','Turkey'),
	 ('Unter den Linden 77','Berlin','Germany'),
	 ('Potsdamer Platz 3, Mitte, 10785','Berlin','Germany'),
	 ('Budapester Str. 2, Mitte, 10787','Berlin','Germany'),
	 ('Champ de Mars, 5 Av. Anatole France, 75007','Paris','França'),
	 ('Central Park','Nova York','EUA'),
	 ('Grand Canyon','Arizona','EUA'),
	 ('Palácio de Versalhes','Versalhes','França');
INSERT INTO modulo_hotel.address (address_line,city,country) VALUES
	 ('Deserto do Saara','Saara','Africa'),
	 ('Monte Everest','Everest','Nepal'),
	 ('Pirâmides de Gizé','Gizé','Egito'),
	 ('Machu Picchu','Machu Picchu','Peru'),
	 ('Copacabana','Rio de Janeiro','Brasil'),
	 ('Muralha da China','China','China'),
	 ('Praias de Phuket','Phuket','Tailândia'),
	 ('Alpes Suíços','Alpes','Suiça'),
	 ('Parque Nacional Yellowston','Yellowston','EUA'),
	 ('Sagrada Família em Barcelona','Barcelona','Espanha');
INSERT INTO modulo_hotel.hotel ("name", address_id,hotel_manager_id) VALUES
	 ('Swissotel The Bosphorus Istanbul',1,1),
	 ('Four Seasons Hotel Istanbul',2,1),
	 ('Ciragan Palace Kempinski Istanbul',3,1),
	 ('Hotel Adlon Kempinski Berlin',4,2),
	 ('The Ritz-Carlton Berlin',5,2),
	 ('InterContinental Berlin',6,2),
	 ('Vista Turística Hotel',7,3),
	 ('Ponto de Encontro Resort',8,3),
	 ('Destino Sonhado Lodge',9,3),
	 ('Jardins da Cidade Hotel',10,3);
INSERT INTO modulo_hotel.hotel ("name",address_id,hotel_manager_id) VALUES
	 ('Oásis Turístico Resort',11,3),
	 ('Horizon Travel Hotel',12,3),
	 ('Maravilhas do Turismo Lodge',13,3),
	 ('Pousada dos Viajantes',14,3),
	 ('Estância das Marés Hotel',15,3),
	 ('Acolhida do Viajante Resort',16,3),
	 ('Sol e Mar Lodge',17,3),
	 ('Montanha Encantada Hotel',18,3),
	 ('Refúgio Turístico Lodge',19,3),
	 ('Encanto da Cidade Resort',20,3);
	
	INSERT INTO modulo_hotel.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (370.0,35,'SOLTEIRO',1),
	 (459.0,45,'CASAL',1),
	 (700.0,25,'SOLTEIRO',2),
	 (890.0,30,'CASAL',2),
	 (691.0,30,'SOLTEIRO',3),
	 (800.0,75,'CASAL',3),
	 (120.0,25,'SOLTEIRO',4),
	 (250.0,15,'CASAL',4),
	 (300.0,50,'SOLTEIRO',5),
	 (400.0,50,'CASAL',5);
INSERT INTO modulo_hotel.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (179.0,45,'SOLTEIRO',6),
	 (256.0,25,'CASAL',6),
	 (800.0,10,'SOLTEIRO',7),
	 (500.0,30,'CASAL',7),
	 (800.0,30,'SOLTEIRO',8),
	 (600.0,80,'CASAL',8),
	 (1000.0,10,'SOLTEIRO',9),
	 (600.0,10,'CASAL',9),
	 (100.0,10,'SOLTEIRO',10),
	 (100.0,10,'CASAL',10);
INSERT INTO modulo_hotel.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (1000.0,1,'SOLTEIRO',11),
	 (1000.0,1,'CASAL',11),
	 (2000.0,1,'SOLTEIRO',12),
	 (1000.0,1,'CASAL',12),
	 (80.0,10,'SOLTEIRO',13),
	 (50.0,10,'CASAL',13),
	 (100.0,10,'SOLTEIRO',14),
	 (80.0,20,'CASAL',14),
	 (1000.0,10,'SOLTEIRO',15),
	 (600.0,50,'CASAL',15);
INSERT INTO modulo_hotel.room (price_per_night,room_count,room_type,hotel_id) VALUES
	 (150.0,10,'SOLTEIRO',16),
	 (110.0,30,'CASAL',16),
	 (650.0,5,'SOLTEIRO',17),
	 (450.0,15,'CASAL',17),
	 (1500.0,10,'SOLTEIRO',18),
	 (900.0,80,'CASAL',18),
	 (750.0,10,'SOLTEIRO',19),
	 (350.0,40,'CASAL',19),
	 (750.0,10,'SOLTEIRO',20),
	 (349.98,30,'CASAL',20);

INSERT INTO modulo_hotel.availability (available_rooms,"date",hotel_id,room_id) VALUES
	 (5,'2023-09-01',4,7),
	 (7,'2023-09-02',4,8),
	 (24,'2024-04-02',6,12),
	 (24,'2024-04-03',6,12),
	 (24,'2024-04-04',6,12),
	 (1,'2024-04-05',16,30);
