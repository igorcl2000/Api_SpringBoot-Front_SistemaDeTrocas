create table loja(

    id bigint not null auto_increment,
    rasao_social VARCHAR(255),
    nome_fantasia VARCHAR(255),
    cnpj VARCHAR(14),
    telefone VARCHAR(20),
    responsavel VARCHAR(255),
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    ativo tinyint not null,

    primary key(id)

);