create table assistances
(
    id          bigint auto_increment
        primary key,
    description varchar(300) not null,
    name        varchar(100) not null
);

INSERT INTO assistances (name, description) VALUES ('Troca de Aparelho', 'Troca de aparelho decodificador de sinal');
INSERT INTO assistances (name, description) VALUES ('Troca de Cabo Interno', 'Troca de cabo interno');
INSERT INTO assistances (name, description) VALUES ('Troca de Fiação Interna', 'Substituição de fiação interna da residência');
INSERT INTO assistances (name, description) VALUES ('Manutenção em Fogão', 'Reparo sem necessidade de compra de peças');
INSERT INTO assistances (name, description) VALUES ('Manutenção em Geladeira', 'Reparo sem necessidade de compra de peças');
INSERT INTO assistances (name, description) VALUES ('Manutenção em Máquina de Lavar', 'Reparo sem necessidade de compra de peças');