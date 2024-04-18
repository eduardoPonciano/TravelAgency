
INSERT INTO booking_hotel.address (address_line,city,country) VALUES
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
INSERT INTO booking_hotel.address (address_line,city,country) VALUES
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
	  
	  
INSERT INTO booking_hotel.hotel ("name", address_id,hotel_manager_id) VALUES
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
INSERT INTO booking_hotel.hotel ("name",address_id,hotel_manager_id) VALUES
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
	  
INSERT INTO booking_hotel.type_bed (id, capacity, description) 
	VALUES(nextval('booking_hotel.type_bed_id_seq1'::regclass), 1, 'SOLTEIRO')
	  ON CONFLICT ("description") DO NOTHING;
INSERT INTO booking_hotel.type_bed (id, capacity, description) 
	VALUES(nextval('booking_hotel.type_bed_id_seq1'::regclass), 2, 'CASAL')
	  ON CONFLICT ("description") DO NOTHING;

INSERT INTO booking_hotel.room (daily_price,count,type_bed_id,hotel_id) VALUES
	 (370.0,35,1,1),
	 (459.0,45,2,1),
	 (700.0,25,1,2),
	 (890.0,30,2,2),
	 (691.0,30,1,3),
	 (800.0,75,2,3),
	 (120.0,25,1,4),
	 (250.0,15,2,4),
	 (300.0,50,1,5),
	 (400.0,50,2,5)
	 ON CONFLICT DO NOTHING;
INSERT INTO booking_hotel.room (daily_price,count,type_bed_id,hotel_id) VALUES
	 (179.0,45,1,6),
	 (256.0,25,2,6),
	 (800.0,10,1,7),
	 (500.0,30,2,7),
	 (800.0,30,1,8),
	 (600.0,80,2,8),
	 (1000.0,10,1,9),
	 (600.0,10,2,9),
	 (100.0,10,1,10),
	 (100.0,10,2,10)
	 ON CONFLICT DO NOTHING;
INSERT INTO booking_hotel.room (daily_price,count,type_bed_id,hotel_id) VALUES
	 (1000.0,1,1,11),
	 (1000.0,1,2,11),
	 (2000.0,1,1,12),
	 (1000.0,1,2,12),
	 (80.0,10,1,13),
	 (50.0,10,2,13),
	 (100.0,10,1,14),
	 (80.0,20,2,14),
	 (1000.0,10,1,15),
	 (600.0,50,2,15)
	 ON CONFLICT DO NOTHING;
INSERT INTO booking_hotel.room (daily_price,count,type_bed_id,hotel_id) VALUES
	 (150.0,10,1,16),
	 (110.0,30,2,16),
	 (650.0,5,1,17),
	 (450.0,15,2,17),
	 (1500.0,10,1,18),
	 (900.0,80,2,18),
	 (750.0,10,1,19),
	 (350.0,40,2,19),
	 (750.0,10,1,20),
	 (349.98,30,2,20)
	 ON CONFLICT DO NOTHING;
