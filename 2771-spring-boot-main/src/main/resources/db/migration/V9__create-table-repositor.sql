create table repositor(

    id bigint not null auto_increment,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(14),
    telefone VARCHAR(20),
    ativo tinyint not null,

    primary key(id)

);