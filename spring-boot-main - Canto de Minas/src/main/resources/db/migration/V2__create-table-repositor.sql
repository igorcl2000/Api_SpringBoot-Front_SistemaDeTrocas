create table repositor(

    id bigint not null auto_increment,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(30),
    ativo tinyint not null,

    primary key(id)

);