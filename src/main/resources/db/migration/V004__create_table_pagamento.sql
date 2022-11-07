create table tb_pagamento (
	id bigserial primary key,
	taxa_acrescimo numeric(1000,2),
	taxa_desconto numeric(1000,2),
	usuario_id bigint,
	valor_total numeric(1000,2),
	status_pagamento boolean ,
	constraint fk_usuario_id foreign key (usuario_id) references tb_usuario (id)
	
	);
	