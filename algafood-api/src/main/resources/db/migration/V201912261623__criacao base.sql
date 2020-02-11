CREATE TABLE cozinha
(
  id_cozinha serial,
  nome character varying(50) NOT NULL,
  CONSTRAINT cozinha_pkey PRIMARY KEY (id_cozinha)
);

CREATE TABLE estado
(
  id_estado serial,
  nome character varying(30) NOT NULL,
  uf character varying(2) NOT NULL,
  CONSTRAINT estado_pkey PRIMARY KEY (id_estado)
);
CREATE TABLE cidade
(
  id_cidade serial,
  nome character varying(40) NOT NULL,
  id_estado integer NOT NULL,
  CONSTRAINT cidade_pkey PRIMARY KEY (id_cidade),
  CONSTRAINT fk_cidade_estado FOREIGN KEY (id_estado)
      REFERENCES estado (id_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE forma_pagamento
(
  id_forma_pagamento serial,
  descricao character varying(50) NOT NULL,
  CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id_forma_pagamento)
);
CREATE TABLE grupo
(
  id_grupo serial,
  nome character varying(30),
  CONSTRAINT grupo_pkey PRIMARY KEY (id_grupo)
);
CREATE TABLE permissao
(
  id_permissao serial,
  descricao character varying(100) NOT NULL,
  nome character varying(30) NOT NULL,
  CONSTRAINT permissao_pkey PRIMARY KEY (id_permissao)
);
CREATE TABLE grupo_permissao
(
  id_grupo integer NOT NULL,
  id_permissao integer NOT NULL,
  CONSTRAINT fk_grupo_permissao_grupo FOREIGN KEY (id_grupo)
      REFERENCES grupo (id_grupo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_grupo_permissao_permissao FOREIGN KEY (id_permissao)
      REFERENCES permissao (id_permissao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE restaurante
(
  id_restaurante serial,
  data_atualizacao timestamp with time zone NOT NULL,
  data_cadastro timestamp with time zone NOT NULL,
  endereco_bairro character varying(40),
  endereco_cep character varying(10),
  endereco_complemento character varying(50),
  endereco_logradouro character varying(80),
  endereco_numero character varying(10),
  nome character varying(30) NOT NULL,
  taxa_frete numeric(10,2) NOT NULL,
  id_cozinha integer NOT NULL,
  id_cidade integer,
  CONSTRAINT restaurante_pkey PRIMARY KEY (id_restaurante),
  CONSTRAINT fk_restaurante_cozinha FOREIGN KEY (id_cozinha)
      REFERENCES cozinha (id_cozinha) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_restaurante_cidade FOREIGN KEY (id_cidade)
      REFERENCES cidade (id_cidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE produto
(
  id_produto serial,
  ativo boolean NOT NULL,
  descricao character varying(128) NOT NULL,
  nome character varying(30) NOT NULL,
  preco numeric(10,2) NOT NULL,
  id_restaurante integer NOT NULL,
  CONSTRAINT produto_pkey PRIMARY KEY (id_produto),
  CONSTRAINT fk_produto_restaurante FOREIGN KEY (id_restaurante)
      REFERENCES restaurante (id_restaurante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE restaurante_forma_pagamento
(
  id_restaurante integer NOT NULL,
  id_forma_pagamento integer NOT NULL,
  CONSTRAINT pk_restaurante_forma_pagamento PRIMARY KEY(id_restaurante,id_forma_pagamento),
  CONSTRAINT fk_restaurante_forma_pagamento_restaurante FOREIGN KEY (id_restaurante)
      REFERENCES restaurante (id_restaurante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_restaurante_forma_pagamento_forma FOREIGN KEY (id_forma_pagamento)
      REFERENCES forma_pagamento (id_forma_pagamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE usuario
(
  id_usuario serial,
  data_cadastro timestamp with time zone NOT NULL,
  email character varying(50) NOT NULL,
  nome character varying(50) NOT NULL,
  senha character varying(128) NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
);


CREATE TABLE usuario_grupo
(
  id_usuario integer NOT NULL,
  id_grupo integer NOT NULL,
  CONSTRAINT pk_usuario_grupo PRIMARY KEY(id_usuario, id_grupo),
  CONSTRAINT fk_usuario_grupo_usuario FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usuario_grupo_grupo FOREIGN KEY (id_grupo)
      REFERENCES permissao (id_permissao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
