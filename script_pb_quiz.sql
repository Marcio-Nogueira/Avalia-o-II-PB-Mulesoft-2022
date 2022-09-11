create database pb_quiz;

use pb_quiz;

create table questions (
id int AUTO_INCREMENT,
question varchar(255) NOT NULL,
answer boolean,
isActive boolean,
PRIMARY KEY (id)
);

select * from questions;

INSERT INTO questions(question, answer, isActive) VALUES ("ENIAC foi o primeiro computador. Verdadeiro ou Falso?", true, true);
INSERT INTO questions(question, answer, isActive) VALUES ("Elon Musk é o homem mais rico do planeta. Verdadeiro ou Falso?", false, true);
INSERT INTO questions(question, answer, isActive) VALUES ("A Compasso Uol é uma excelente empresa. Verdadeiro ou Falso?", true, true);
INSERT INTO questions(question, answer, isActive) VALUES ("A gestação dos cavalos-marinhos é feita pelos machos. Verdadeiro ou Falso?", true, false);
INSERT INTO questions(question, answer, isActive) VALUES ("2 + 2 = 5. Verdadeiro ou Falso?", true, false);
INSERT INTO questions(question, answer, isActive) VALUES ("Vegetarianos podem comer presunto. Verdadeiro ou Falso?", false, true);
INSERT INTO questions(question, answer, isActive) VALUES ("O Queijo Minas Frescal é considerado Patrimônio Cultural Brasileiro. Verdadeiro ou Falso?", true, true);
INSERT INTO questions(question, answer, isActive) VALUES ("Antes de ser narrador, Galvão Bueno foi jogador de futebol.. Verdadeiro ou Falso?", false, true);
INSERT INTO questions(question, answer, isActive) VALUES ("No Brasil, as mulheres podem votar desde 1932. Verdadeiro ou Falso?", true, true);
INSERT INTO questions(question, answer, isActive) VALUES ("O México é um país localizado na América Central. Verdadeiro ou Falso?", false, true);
INSERT INTO questions(question, answer, isActive) VALUES ("Fernando Pessoa foi um escritor brasileiro. Verdadeiro ou Falso?", false, true);
INSERT INTO questions(question, answer, isActive) VALUES ("A Guerra Fria aconteceu entre Estados Unidos da América e a União Soviética. Verdadeiro ou Falso?", true, true);
INSERT INTO questions(question, answer, isActive) VALUES ("A pintura é arte chamada de “sétima arte”. Verdadeiro ou Falso?", false, true);
INSERT INTO questions(question, answer, isActive) VALUES ("Dom Casmurro é a mais conhecida dentre as obras de Machado de Assis.. Verdadeiro ou Falso?", true, true);
INSERT INTO questions(question, answer, isActive) VALUES ("O Brasil tem um sistema de governo parlamentarista. Verdadeiro ou Falso?", false, true);





