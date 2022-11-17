create table  produto (
id bigserial primary key,
nome varchar(50),
observacao varchar(150),
tipo varchar(50),
valor numeric(1000,2),
status boolean
)