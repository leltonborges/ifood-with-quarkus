create schema if not exists ifood authorization marketplace;

CREATE TABLE ifood.tb_localizacao
(
    id        int8   NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    latitude  float8 NULL,
    longitude float8 NULL,
    CONSTRAINT localizacao_pkey PRIMARY KEY (id)
);

CREATE TABLE ifood.tb_restaurante
(
    id             int8        NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    nome           varchar(50) NULL,
    cnpj           char(14)    NOT NULL,
    localizacao_id int8        NOT NULL,
    CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);

ALTER TABLE ifood.tb_restaurante
    ADD CONSTRAINT res_loc FOREIGN KEY (localizacao_id) REFERENCES tb_localizacao (id);

CREATE TABLE ifood.tb_prato
(
    id             int8           NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    descricao      varchar(200)   NULL,
    nome           varchar(50)    NULL,
    preco          numeric(19, 2) NOT NULL,
    restaurante_id int8           NOT NULL
);

ALTER TABLE ifood.tb_prato
    ADD CONSTRAINT prato_rest FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante (id);


CREATE TABLE ifood.tb_prato_cliente
(
    prato   int,
    cliente varchar(200)
);

INSERT INTO ifood.tb_localizacao (id, latitude, longitude)
VALUES (1000, -15.817759, -47.836959);

INSERT INTO ifood.tb_restaurante (id, localizacao_id, nome, cnpj)
VALUES (999, 1000, 'Manguai', '04963102057245'),
       (888, 1000, 'Norme', '70996044162965');

INSERT INTO ifood.tb_prato
    (id, nome, descricao, restaurante_id, preco)
VALUES (9998, 'Cuscuz com Ovo', 'Bom demais no café da manhã', 999, 3.99);

INSERT INTO ifood.tb_prato
    (id, nome, descricao, restaurante_id, preco)
VALUES (9997, 'Peixe frito', 'Agulhinha frita, excelente com Cerveja', 999, 99.99);


