INSERT INTO public."user" (username, email, full_name, password, phone, birth_date, created, updated, biography, profile_photo) VALUES
('adrsphad', 'adrsphad@email.com', 'Adrian Sephard', 'hashedpassword123', '+1 432 655 6543', '1978-11-19', '2025-02-10', NULL, 'En espera por una mision.', NULL),
('gfreman', 'gfreman@email.com', 'Gordon Freeman', 'securepassword456', '+1 365 524 1755', '1983-12-24', '2025-02-10', NULL, 'Cientifico y Fisico, investigador de {redacted}.', NULL),
('barcal', 'barcal@email.com', 'Barney Calhoun', 'pass1234', '+1 301 641 5321', '1970-05-19', '2025-02-10', NULL, 'Guardia de seguridad.', NULL),
('jean', 'jean@email.com', 'Javier Eduardo Acevedo Noguera', '46hd721awqw', '+57 731 432 4321', '2006-10-19', '2025-02-10', NULL, 'Programador apasionado en Spring Boot y React.', NULL);

-- from user to user
INSERT INTO public.follow (followed, follow_from, follow_to) VALUES
('2025-02-10 21:38:00', 'barcal', 'gfreman'),
('2025-02-10 21:24:00', 'jean', 'barcal'),
('2025-02-10 21:41:00', 'adrsphad', 'jean'),
('2025-02-10 21:30:00', 'gfreman', 'adrsphad');

INSERT INTO public.notification (content, sended, readed, username) VALUES
('Barney Calhoun ha comenzado a seguirte.', '2025-02-10 21:24:00', FALSE, 'gfreman'),
('Javier Eduardo Acevedo Noguera ha comenzado a seguirte.', '2025-02-10 21:26:00', FALSE, 'barcal'),
('Adrian Sephard ha comenzado a seguirte.', '2025-02-10 21:43:00', FALSE, 'jean'),
('Gordon Freeman ha comenzado a seguirte.', '2025-02-10 21:32:00', FALSE, 'adrsphad');

INSERT INTO public.publication (content, img_src, created, updated, tags, username) VALUES
('Mi primer post en la plataforma!', NULL, '2025-02-10 20:30:00', NULL, '{"#Hola","#Welcome"}', 'gfreman'),
('Una hermosa puesta de sol en la playa. #Vacaciones', NULL, '2025-02-10 20:30:00', NULL, '{"#Descanso","#Vaciones"}', 'barcal'),
('Estoy en una mision hacia unas instalaciones', NULL, '2025-02-10 20:30:00', NULL, NULL, 'adrsphad'),
('Iniciando un nuevo proyecto en Spring Boot y React, es un proyecto sobre una red social :).', NULL, '2025-02-10 20:30:00', NULL, '{"#Programacion","#Java"}', 'jean');

INSERT INTO public.comment (content, created, updated, tagged, id_publication, username) VALUES
('Hey, yo te conosco', '2025-02-10 20:40:00', NULL, 'gfreman', 1, 'adrsphad'),
('Disfruta de tus vacaciones, te las ganaste', '2025-02-10 20:35:00', NULL, 'barcal', 2, 'gfreman'),
('No paso nada en las instalaciones todo esta bajo control :)', '2025-02-10 20:42:00', NULL, NULL, 3, 'gfreman'),
('Como te va en tu proyecto', '2025-02-10 20:44:00', NULL, 'jean', 4, 'gfreman'),
('Animos, terminalo pronto', '2025-02-10 21:00:00', NULL, 'jean', 4, 'barcal'),
('Que es eso', '2025-02-10 21:10:00', NULL, 'jean', 4, 'adrsphad');

INSERT INTO public."like" (liked, username, id_publication) VALUES
('2025-02-10 20:38:00', 'adrsphad', 1),
('2025-02-10 20:34:00', 'gfreman', 2),
('2025-02-10 20:41:00', 'gfreman', 3);