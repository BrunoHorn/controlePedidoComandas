create table tb_comanda (
	id bigserial primary key,
	obeservacao varchar(250),
	status_comanda boolean );
	
create table tb_usuario(
	id bigserial primary key,
	nome varchar(50),
	senha varchar(50),
	tipo_permissao varchar(20),
	status_usuario boolean );	
	
	