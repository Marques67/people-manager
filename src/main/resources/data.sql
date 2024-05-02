INSERT INTO tb_person (full_name, birthday) VALUES ('Alex Andrade', TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z');
INSERT INTO tb_person (full_name, birthday) VALUES ('Joao Victor Medeiros', TIMESTAMP WITH TIME ZONE '2020-07-15T10:00:00Z');
INSERT INTO tb_person (full_name, birthday) VALUES ('Lucas Marques', TIMESTAMP WITH TIME ZONE '2020-07-16T10:00:00Z');

INSERT INTO tb_address (street, cep, number, city, state, person_id, is_main) VALUES ('Rua One', '21921-221', 21, 'Rio de Janeiro', 'Rio de Janeiro', 1, true);
INSERT INTO tb_address (street, cep, number, city, state, person_id, is_main) VALUES ('Rua Two', '21921-222', 22, 'Duque de caxias', 'Rio de Janeiro', 1, false);
INSERT INTO tb_address (street, cep, number, city, state, person_id, is_main) VALUES ('Rua Three', '21921-223', 23, 'Nilopolis', 'Rio de Janeiro', 2, true);
INSERT INTO tb_address (street, cep, number, city, state, person_id, is_main) VALUES ('Rua Four', '21921-224', 24, 'Cabo Frio', 'Rio de Janeiro', 3, false);