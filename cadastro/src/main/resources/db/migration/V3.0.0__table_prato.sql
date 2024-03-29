create table tb_prato
(
    id             bigint generated by default as identity
        constraint tb_prato_pkey
            primary key,
    descricao      varchar(255),
    nome           varchar(255),
    preco          numeric(19, 2),
    restaurante_id bigint
        constraint fkqw2g574pxky5ycq29if4ra24m
            references tb_restaurante(id)
);

alter table tb_prato
    owner to study;