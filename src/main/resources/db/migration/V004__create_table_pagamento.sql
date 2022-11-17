create table pagamento (
	id bigserial primary key,
	taxa_acrescimo numeric(1000,2),
	taxa_desconto numeric(1000,2),
	valor_total numeric(1000,2),
	status boolean 
	
	
	);
	