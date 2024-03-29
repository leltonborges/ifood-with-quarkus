create table tb_restaurante
(
    id              bigint generated by default as identity
        constraint tb_restaurante_pkey
            primary key,
    cnpj            varchar(255),
    dataatualizacao timestamp,
    datacriacao     timestamp,
    nome            varchar(255),
    proprietario    varchar(255),
    localizacao_id  bigint
        constraint fk2d6xcrmwkhgpesm0qimq9hiri
            references tb_localizacao(id)
);

alter table tb_restaurante
    owner to study;
