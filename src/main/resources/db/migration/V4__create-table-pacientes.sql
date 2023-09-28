create table pacientes (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documentoIdentidad varchar(6) not null,
    telefono varchar(10) not null,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    complemento varchar(100) not null,
    numero varchar(20),
    ciudad varchar(100) not null,

    primary key (id)
);