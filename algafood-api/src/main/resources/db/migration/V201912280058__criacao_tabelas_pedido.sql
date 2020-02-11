CREATE TABLE public.pedido
(
  id_pedido serial,
  id_usuario integer NOT NULL,
  id_restaurante integer NOT NULL,
  id_forma_pagamento integer NOT NULL,
  pedido_status character varying(10) NOT NULL,
  subtotal numeric(10,2) NOT NULL,
  taxa_frete numeric(10,2) NOT NULL,
  valor_total numeric(10,2) NOT NULL,
  data_entrega timestamp with time zone,
  data_confirmacao timestamp with time zone,
  data_criacao timestamp with time zone NOT NULL,
  data_cancelamento timestamp with time zone,
  endereco_bairro character varying(40) NOT NULL,
  endereco_cep character varying(10) NOT NULL,
  endereco_complemento character varying(50),
  endereco_logradouro character varying(80)  NOT NULL,
  endereco_numero character varying(10)  NOT NULL,
  CONSTRAINT pk_pedido PRIMARY KEY (id_pedido),
  CONSTRAINT fk_pedido_forma_pagamento FOREIGN KEY (id_forma_pagamento)
      REFERENCES public.forma_pagamento (id_forma_pagamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedido_restaurante FOREIGN KEY (id_restaurante)
      REFERENCES public.restaurante (id_restaurante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario)
      REFERENCES public.usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.pedido_item
(
  id_pedido_item serial,
  id_pedido integer NOT NULL,
  id_produto integer NOT NULL,
  quantidade smallint NOT NULL,
  preco_unitario numeric(10,2) NOT NULL,
  preco_total numeric(10,2) NOT NULL,
  observacao character varying(128),
  CONSTRAINT pk_pedido_item PRIMARY KEY (id_pedido_item),
  CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (id_pedido)
      REFERENCES public.pedido (id_pedido) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedido_item_produto FOREIGN KEY (id_produto)
      REFERENCES public.produto (id_produto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
