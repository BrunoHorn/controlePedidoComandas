create table pedido (
	id bigserial primary key,
	observacao varchar(200),
	data_atualizacao_pedido timestamp,
	status_pedido varchar(30),
	produto_id bigint,
	comanda_id bigint,
	constraint fk_comanda_id foreign key (comanda_id) references comanda (id),
	constraint fk_produto_id foreign key (produto_id) references produto (id)

        );
	