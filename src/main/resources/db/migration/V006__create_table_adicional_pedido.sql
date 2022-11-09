create table adicional_pedido (
	id bigserial primary key,
	produto_id bigint,
	adicional_id bigint,
	pedido_id	bigint,
	observacao varchar(200),
    constraint fk_produto_id foreign key (produto_id) references produto (id),
    constraint fk_adicional_id foreign key (adicional_id) references adicional (id),
    constraint fk_pedido_id foreign key (pedido_id) references pedido (id)
); 