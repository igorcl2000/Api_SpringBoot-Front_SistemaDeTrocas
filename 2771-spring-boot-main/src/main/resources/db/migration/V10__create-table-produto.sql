create table produto(

    id bigint not null auto_increment,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    foto VARCHAR(255),
    cod_prod VARCHAR(255),
    ativo tinyint not null,

    primary key(id)

);