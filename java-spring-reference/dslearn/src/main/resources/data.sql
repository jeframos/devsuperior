INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);

INSERT INTO tb_course (name, img_uri, img_gray_uri) VALUES ('Bootcamp HTML', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlNK8PI_jQ9uQ2OFx5MyYKhlJx5VBLlxR63cEb5nzi_29l9oxaYEsb_HI&s=10', 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg');

INSERT INTO tb_offer (edition, start_moment, end_moment, course_id) VALUES (1.0, TIMESTAMP WITH TIME ZONE '2026-11-06T00:00:00Z', TIMESTAMP WITH TIME ZONE '2027-11-06T00:00:00Z', 1);
INSERT INTO tb_offer (edition, start_moment, end_moment, course_id) VALUES (2.0, TIMESTAMP WITH TIME ZONE '2026-12-06T00:00:00Z', TIMESTAMP WITH TIME ZONE '2027-12-06T00:00:00Z', 1);

INSERT INTO tb_resource (title, description, position, img_uri, type, offer_id) VALUES ('Trilha HTML', 'Trilha principal do curso', 1, 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg', 1, 1);
INSERT INTO tb_resource (title, description, position, img_uri, type, offer_id) VALUES ('Forum', 'Tire suas dúvidas', 2, 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg', 2, 1);
INSERT INTO tb_resource (title, description, position, img_uri, type, offer_id) VALUES ('Lives', 'Lives exclusivas para a turma', 3, 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg', 0, 1);

INSERT INTO tb_section (title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Introdução', 'Introdução ao curso', 1, 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg', 1, null);
INSERT INTO tb_section (title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('HTML Básico', 'Aprenda o básico de HTML', 2, 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg', 1, 1);
INSERT INTO tb_section (title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('HTML Avançado', 'Aprenda o avançado de HTML', 3, 'https://www.uidm.in/wp-content/uploads/2020/09/is-there-any-future-of-digital-marketing.jpg', 1, 2);

INSERT INTO tb_enrollment (user_id, offer_id, enroll_moment, refund_moment, available, only_update) VALUES (1, 1, TIMESTAMP WITH TIME ZONE '2026-11-06T13:00:00Z', null, true, false);
INSERT INTO tb_enrollment (user_id, offer_id, enroll_moment, refund_moment, available, only_update) VALUES (2, 1, TIMESTAMP WITH TIME ZONE '2026-11-06T13:00:00Z', null, true, false);
