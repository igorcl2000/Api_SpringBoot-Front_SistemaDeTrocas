create table trocas(

    id bigint not null auto_increment,
    id_loja bigint not null,
    id_repositor bigint not null,
    id_produto bigint not null,
    lote VARCHAR(255),
    data_troca VARCHAR(255),
    descricao VARCHAR(255),
    quantidade VARCHAR(255),
    data_fabricacao VARCHAR(255),
    data_vencimento VARCHAR(255),
    motivo_troca VARCHAR(255),
    ativo tinyint not null,

    primary key(id),

    constraint fk_trocas_id_loja foreign key(id_loja) references loja(id),
    constraint fk_trocas_id_repositor foreign key(id_repositor) references repositor(id),
    constraint fk_trocas_id_produto foreign key(id_produto) references produto(id)

);