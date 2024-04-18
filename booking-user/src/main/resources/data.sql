INSERT INTO booking_user."role" (role_type) VALUES
	 ('ADMIN'),
	 ('CUSTOMER'),
	 ('HOTEL_MANAGER')ON CONFLICT (role_type)
DO NOTHING;;
	
INSERT INTO booking_user.user_plataform (created_date,last_name,"name","password",username,role_id) VALUES
	 ('2024-04-02 14:38:31.746292','Admin','Admin','$2a$10$qPMYr/j8weHFto9GeMY8/.bvJwteUuvKfGsoOfzjnJfvg36C9suUu','admin@hotel.com',1),
	 ('2024-04-02 14:38:31.751636','Koker','Kaya Alp','$2a$10$lXfDxFYzn1SQw/0TKEzkOOuus5vEM6nqZq4IkM64voUF1O9RxX2H6','customer1@hotel.com',2),
	 ('2024-04-02 14:38:31.754307','Doe','John','$2a$10$kAXe8dQxTTOFJcTTkNtzo.5p.Ul1gwko48MOySWNS1i8gTR7ppMn.','manager1@hotel.com',3),
	 ('2024-04-02 14:38:31.757081','Mustermann','Max','$2a$10$MGzGrUYCyRP8fZhcAXhkH.MpJcpbydRH9LzoVCuAj8R.7O7f7exOy','manager2@hotel.com',3),
	 ('2024-04-02 15:07:26.259445','Testador','Usuario','$2a$10$XeQQwkcEcQmXcZPkw3eeB.5eprsG4SrNz9X196RgMOIxdppvhIQje','usuario.testador@teste.com',1),
	 ('2024-04-02 18:04:01.454107','Testador','Admin','$2a$10$pyOEqsixxk3513Qe05zxP.bBxBFYbKMmb0DZ70O7n1xFYVjXdVhpO','admin@adminteste.com',3),
	 ('2024-04-02 22:55:14.821853','Terceiro','Testador','$2a$10$M94y/3OIXZ/hlhlelsrKBeMLjb9K86B0z.zx74CxuKNEQxRvz2xye','terceito@teste.com',2)
	 ON CONFLICT (username) DO NOTHING;;

	
INSERT INTO booking_user."admin" (user_id) VALUES
	 (1) ON CONFLICT (user_id) DO NOTHING;;
INSERT INTO booking_user.customer (user_id) VALUES
	 (2),
	 (5),
	 (7) ON CONFLICT (user_id) DO NOTHING;;

	
INSERT INTO booking_user.hotel_manager (user_id) VALUES
	 (3),
	 (4),
	 (6) ON CONFLICT (user_id) DO NOTHING;;