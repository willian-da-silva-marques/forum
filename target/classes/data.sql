INSERT INTO usuario(nome, email, senha) VALUES('Willian da Silva Marques', 'willian.marques@compasso.com.br', '$2a$10$T5JFAKkXrv3MJEQ9Qa14DOMlFbo7rRPi5YM84P9Dho0TsxQxv8sBi');

INSERT INTO curso(nome, categoria) VALUES('Spring Boot Parte 1', 'Programação');
INSERT INTO curso(nome, categoria) VALUES('HTML 5 e CSS 3', 'Front-end');

INSERT INTO topico(data_criacao, mensagem, status, titulo, autor_id, curso_id) VALUES('2019-05-05 18:12:00', 'Dúvida sobre mapeamento JPA...', 'NAO_RESPONDIDO', 'Dúvida A', 1, 1);
INSERT INTO topico(data_criacao, mensagem, status, titulo, autor_id, curso_id) VALUES('2019-05-15 19:48:00', 'Como formatar uma data na response...', 'NAO_RESPONDIDO','Dúvida B', 1, 1);
INSERT INTO topico(data_criacao, mensagem, status, titulo, autor_id, curso_id) VALUES('2019-05-25 20:26:00', 'Como criar uma tabela em html 5...', 'NAO_RESPONDIDO','Dúvida C', 1, 2);