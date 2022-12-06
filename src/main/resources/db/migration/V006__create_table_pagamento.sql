create table pagamento (
	id bigserial primary key,
	valor_total numeric(1000,2),
	taxa numeric (3,2),
	comanda_id bigint,
	constraint fk_comanda_id foreign key (comanda_id) references comanda (id)
	);