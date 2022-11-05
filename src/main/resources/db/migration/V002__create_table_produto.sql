create table  tb_produto (
id bigserial primary key,
nome_produto varchar(50),
obs_produto varchar(150),
tipo_produto varchar(50),
valor_produto numeric(1000,2),
status_produto boolean
)