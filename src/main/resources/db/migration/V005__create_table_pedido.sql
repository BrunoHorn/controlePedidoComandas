create table tb_pedido (
	id bigserial primary key,
	observacao varchar(200),
	data_atualizacao_pedido timestamp,
	produto_id bigint,
	comanda_id bigint,
	usuario_id bigint,
	pagamento_id bigint,
	constraint fk_produto_id foreign key (produto_id) references tb_produto (id),
    constraint fk_comanda_id foreign key (comanda_id) references tb_comanda (id),
    constraint fk_usuario_id foreign key (usuario_id) references tb_usuario (id),
    constraint fk_pagamento_id foreign key (pagamento_id) references tb_pagamento (id)
        );
	