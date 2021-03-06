create table cidade (id_cidade  serial not null, nome varchar(40) not null, estado_id_estado int4 not null, primary key (id_cidade))
create table cozinha (id_cozinha  serial not null, nome varchar(50) not null, primary key (id_cozinha))
create table estado (id_estado  serial not null, nome varchar(30) not null, uf varchar(2) not null, primary key (id_estado))
create table forma_pagamento (id_forma_pagamento  serial not null, descricao varchar(50) not null, primary key (id_forma_pagamento))
create table grupo (id_grupo  serial not null, nome varchar(30), primary key (id_grupo))
create table grupo_permissao (id_grupo int4 not null, id_permissao int4 not null)
create table permissao (id_permissao  serial not null, descricao varchar(100) not null, nome varchar(30) not null, primary key (id_permissao))
create table produto (id_produto  serial not null, ativo boolean not null, descricao varchar(128) not null, nome varchar(30) not null, preco numeric(10, 2) not null, id_restaurante int4 not null, primary key (id_produto))
create table restaurante (id_restaurante  serial not null, data_atualizacao timestamp not null, data_cadastro timestamp not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(30) not null, taxa_frete numeric(10, 2) not null, cozinha_id_cozinha int4 not null, id_cidade int4, primary key (id_restaurante))
create table restaurante_forma_pagamento (id_restaurante int4 not null, id_forma_pagamento int4 not null)
create table usuario (id_usuario  serial not null, data_cadastro timestamp not null, email varchar(50) not null, nome varchar(50) not null, senha varchar(128) not null, primary key (id_usuario))
create table usuario_grupo (id_usuario int4 not null, id_grupo int4 not null)
alter table cidade add constraint FK817tyfktkubqwe1w37jdrwjnh foreign key (estado_id_estado) references estado
alter table grupo_permissao add constraint FKfdid07dqkcx8cc53nj7rkmvcl foreign key (id_permissao) references permissao
alter table grupo_permissao add constraint FK9oesxnxaml2s24exucm3jr4fr foreign key (id_grupo) references grupo
alter table produto add constraint FKi9wilbw69bh3m9rw7xs7sqmah foreign key (id_restaurante) references restaurante
alter table restaurante add constraint FKjmcmorjv01skx0cat6mqgr08c foreign key (cozinha_id_cozinha) references cozinha
alter table restaurante add constraint FKt4xq1b20kyhhkfhlvt5x249a2 foreign key (id_cidade) references cidade
alter table restaurante_forma_pagamento add constraint FKi5u3gytpsof4uedhurtis2r8q foreign key (id_forma_pagamento) references forma_pagamento
alter table restaurante_forma_pagamento add constraint FK9urxjm86vev9pa1ai1awuenvc foreign key (id_restaurante) references restaurante
alter table usuario_grupo add constraint FKbwgd2w3fvj0ulnbrcny29q4kx foreign key (id_grupo) references permissao
alter table usuario_grupo add constraint FK9huj1upwjyabwkwnpnhnernnu foreign key (id_usuario) references usuario
insert into cozinha (nome) values('Tailandesa')
insert into cozinha (nome) values('Indiana')
insert into cozinha (nome) values('Japonesa')
insert into cozinha (nome) values('Brasileira')
insert into estado (uf,nome) values ('AC','Acre')
insert into estado (uf,nome) values ('AL','Alagoas')
insert into estado (uf,nome) values ('AP','Amapá')
insert into estado (uf,nome) values ('AM','Amazonas')
insert into estado (uf,nome) values ('BA','Bahia')
insert into estado (uf,nome) values ('CE','Ceará')
insert into estado (uf,nome) values ('DF','Distrito Federal')
insert into estado (uf,nome) values ('ES','Espírito Santo')
insert into estado (uf,nome) values ('GO','Goiás')
insert into estado (uf,nome) values ('MA','Maranhão')
insert into estado (uf,nome) values ('MT','Mato Grosso')
insert into estado (uf,nome) values ('MS','Mato Grosso do Sul')
insert into estado (uf,nome) values ('MG','Minas Gerais')
insert into estado (uf,nome) values ('PA','Pará')
insert into estado (uf,nome) values ('PB','Paraíba')
insert into estado (uf,nome) values ('PR','Paraná')
insert into estado (uf,nome) values ('PE','Pernambuco')
insert into estado (uf,nome) values ('PI','Piauí')
insert into estado (uf,nome) values ('RJ','Rio de Janeiro')
insert into estado (uf,nome) values ('RN','Rio Grande do Norte')
insert into estado (uf,nome) values ('RS','Rio Grande do Sul')
insert into estado (uf,nome) values ('RO','Rondônia')
insert into estado (uf,nome) values ('RR','Roraima')
insert into estado (uf,nome) values ('SC','Santa Catarina')
insert into estado (uf,nome) values ('SP','São Paulo')
insert into estado (uf,nome) values ('SE','Sergipe')
insert into estado (uf,nome) values ('TO','Tocantins')
INSERT INTO cidade(nome, estado_id) VALUES ('Planaltina', 7),('Guará',7),('Plano Piloto',7),('Gama',7)
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 1', 5.55, 1, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 2', 10.55, 1, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',2,'Rest. Indiana 1', 14.55, 2, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',3,'Rest. Japonês', 9.55, 3, now(),now())
INSERT INTO public.forma_pagamento(descricao) VALUES ('Cartão de Crédito'), ('Cartão de Débito'),('Cheque'),('Dinheiro')
insert into restaurante_forma_pagamento (id_restaurante, id_forma_pagamento) values(1,1), (1,2),(1,3),(2,1),(2,3), (2,4), (3,1),(3,2),(3,4), (4,1),(4,2),(4,3), (4,4)
INSERT INTO public.produto(ativo, descricao, nome, preco, id_restaurante) VALUES (true, 'Prato 1', 'Prato 1',10.90, 1),(true, 'Prato 2', 'Prato 2',5.90, 1),(true, 'Prato 3', 'Prato 3',3.90, 1),(true, 'Prato 4', 'Prato 4',5.90, 2),(true, 'Prato 5', 'Prato 5',5.50, 3),(true, 'Prato 6', 'Prato 6',5.60, 4)
create table cidade (id_cidade  serial not null, nome varchar(40) not null, estado_id_estado int4 not null, primary key (id_cidade))
create table cozinha (id_cozinha  serial not null, nome varchar(50) not null, primary key (id_cozinha))
create table estado (id_estado  serial not null, nome varchar(30) not null, uf varchar(2) not null, primary key (id_estado))
create table forma_pagamento (id_forma_pagamento  serial not null, descricao varchar(50) not null, primary key (id_forma_pagamento))
create table grupo (id_grupo  serial not null, nome varchar(30), primary key (id_grupo))
create table grupo_permissao (id_grupo int4 not null, id_permissao int4 not null)
create table permissao (id_permissao  serial not null, descricao varchar(100) not null, nome varchar(30) not null, primary key (id_permissao))
create table produto (id_produto  serial not null, ativo boolean not null, descricao varchar(128) not null, nome varchar(30) not null, preco numeric(10, 2) not null, id_restaurante int4 not null, primary key (id_produto))
create table restaurante (id_restaurante  serial not null, data_atualizacao timestamp not null, data_cadastro timestamp not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(30) not null, taxa_frete numeric(10, 2) not null, cozinha_id_cozinha int4 not null, id_cidade int4, primary key (id_restaurante))
create table restaurante_forma_pagamento (id_restaurante int4 not null, id_forma_pagamento int4 not null)
create table usuario (id_usuario  serial not null, data_cadastro timestamp not null, email varchar(50) not null, nome varchar(50) not null, senha varchar(128) not null, primary key (id_usuario))
create table usuario_grupo (id_usuario int4 not null, id_grupo int4 not null)
alter table cidade add constraint FK817tyfktkubqwe1w37jdrwjnh foreign key (estado_id_estado) references estado
alter table grupo_permissao add constraint FKfdid07dqkcx8cc53nj7rkmvcl foreign key (id_permissao) references permissao
alter table grupo_permissao add constraint FK9oesxnxaml2s24exucm3jr4fr foreign key (id_grupo) references grupo
alter table produto add constraint FKi9wilbw69bh3m9rw7xs7sqmah foreign key (id_restaurante) references restaurante
alter table restaurante add constraint FKjmcmorjv01skx0cat6mqgr08c foreign key (cozinha_id_cozinha) references cozinha
alter table restaurante add constraint FKt4xq1b20kyhhkfhlvt5x249a2 foreign key (id_cidade) references cidade
alter table restaurante_forma_pagamento add constraint FKi5u3gytpsof4uedhurtis2r8q foreign key (id_forma_pagamento) references forma_pagamento
alter table restaurante_forma_pagamento add constraint FK9urxjm86vev9pa1ai1awuenvc foreign key (id_restaurante) references restaurante
alter table usuario_grupo add constraint FKbwgd2w3fvj0ulnbrcny29q4kx foreign key (id_grupo) references permissao
alter table usuario_grupo add constraint FK9huj1upwjyabwkwnpnhnernnu foreign key (id_usuario) references usuario
insert into cozinha (nome) values('Tailandesa')
insert into cozinha (nome) values('Indiana')
insert into cozinha (nome) values('Japonesa')
insert into cozinha (nome) values('Brasileira')
insert into estado (uf,nome) values ('AC','Acre')
insert into estado (uf,nome) values ('AL','Alagoas')
insert into estado (uf,nome) values ('AP','Amapá')
insert into estado (uf,nome) values ('AM','Amazonas')
insert into estado (uf,nome) values ('BA','Bahia')
insert into estado (uf,nome) values ('CE','Ceará')
insert into estado (uf,nome) values ('DF','Distrito Federal')
insert into estado (uf,nome) values ('ES','Espírito Santo')
insert into estado (uf,nome) values ('GO','Goiás')
insert into estado (uf,nome) values ('MA','Maranhão')
insert into estado (uf,nome) values ('MT','Mato Grosso')
insert into estado (uf,nome) values ('MS','Mato Grosso do Sul')
insert into estado (uf,nome) values ('MG','Minas Gerais')
insert into estado (uf,nome) values ('PA','Pará')
insert into estado (uf,nome) values ('PB','Paraíba')
insert into estado (uf,nome) values ('PR','Paraná')
insert into estado (uf,nome) values ('PE','Pernambuco')
insert into estado (uf,nome) values ('PI','Piauí')
insert into estado (uf,nome) values ('RJ','Rio de Janeiro')
insert into estado (uf,nome) values ('RN','Rio Grande do Norte')
insert into estado (uf,nome) values ('RS','Rio Grande do Sul')
insert into estado (uf,nome) values ('RO','Rondônia')
insert into estado (uf,nome) values ('RR','Roraima')
insert into estado (uf,nome) values ('SC','Santa Catarina')
insert into estado (uf,nome) values ('SP','São Paulo')
insert into estado (uf,nome) values ('SE','Sergipe')
insert into estado (uf,nome) values ('TO','Tocantins')
INSERT INTO cidade(nome, estado_id) VALUES ('Planaltina', 7),('Guará',7),('Plano Piloto',7),('Gama',7)
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 1', 5.55, 1, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 2', 10.55, 1, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',2,'Rest. Indiana 1', 14.55, 2, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',3,'Rest. Japonês', 9.55, 3, now(),now())
INSERT INTO public.forma_pagamento(descricao) VALUES ('Cartão de Crédito'), ('Cartão de Débito'),('Cheque'),('Dinheiro')
insert into restaurante_forma_pagamento (id_restaurante, id_forma_pagamento) values(1,1), (1,2),(1,3),(2,1),(2,3), (2,4), (3,1),(3,2),(3,4), (4,1),(4,2),(4,3), (4,4)
INSERT INTO public.produto(ativo, descricao, nome, preco, id_restaurante) VALUES (true, 'Prato 1', 'Prato 1',10.90, 1),(true, 'Prato 2', 'Prato 2',5.90, 1),(true, 'Prato 3', 'Prato 3',3.90, 1),(true, 'Prato 4', 'Prato 4',5.90, 2),(true, 'Prato 5', 'Prato 5',5.50, 3),(true, 'Prato 6', 'Prato 6',5.60, 4)
create table cidade (id_cidade  serial not null, nome varchar(40) not null, estado_id_estado int4 not null, primary key (id_cidade))
create table cozinha (id_cozinha  serial not null, nome varchar(50) not null, primary key (id_cozinha))
create table estado (id_estado  serial not null, nome varchar(30) not null, uf varchar(2) not null, primary key (id_estado))
create table forma_pagamento (id_forma_pagamento  serial not null, descricao varchar(50) not null, primary key (id_forma_pagamento))
create table grupo (id_grupo  serial not null, nome varchar(30), primary key (id_grupo))
create table grupo_permissao (id_grupo int4 not null, id_permissao int4 not null)
create table permissao (id_permissao  serial not null, descricao varchar(100) not null, nome varchar(30) not null, primary key (id_permissao))
create table produto (id_produto  serial not null, ativo boolean not null, descricao varchar(128) not null, nome varchar(30) not null, preco numeric(10, 2) not null, id_restaurante int4 not null, primary key (id_produto))
create table restaurante (id_restaurante  serial not null, data_atualizacao timestamp not null, data_cadastro timestamp not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(30) not null, taxa_frete numeric(10, 2) not null, cozinha_id_cozinha int4 not null, id_cidade int4, primary key (id_restaurante))
create table restaurante_forma_pagamento (id_restaurante int4 not null, id_forma_pagamento int4 not null)
create table usuario (id_usuario  serial not null, data_cadastro timestamp not null, email varchar(50) not null, nome varchar(50) not null, senha varchar(128) not null, primary key (id_usuario))
create table usuario_grupo (id_usuario int4 not null, id_grupo int4 not null)
alter table cidade add constraint FK817tyfktkubqwe1w37jdrwjnh foreign key (estado_id_estado) references estado
alter table grupo_permissao add constraint FKfdid07dqkcx8cc53nj7rkmvcl foreign key (id_permissao) references permissao
alter table grupo_permissao add constraint FK9oesxnxaml2s24exucm3jr4fr foreign key (id_grupo) references grupo
alter table produto add constraint FKi9wilbw69bh3m9rw7xs7sqmah foreign key (id_restaurante) references restaurante
alter table restaurante add constraint FKjmcmorjv01skx0cat6mqgr08c foreign key (cozinha_id_cozinha) references cozinha
alter table restaurante add constraint FKt4xq1b20kyhhkfhlvt5x249a2 foreign key (id_cidade) references cidade
alter table restaurante_forma_pagamento add constraint FKi5u3gytpsof4uedhurtis2r8q foreign key (id_forma_pagamento) references forma_pagamento
alter table restaurante_forma_pagamento add constraint FK9urxjm86vev9pa1ai1awuenvc foreign key (id_restaurante) references restaurante
alter table usuario_grupo add constraint FKbwgd2w3fvj0ulnbrcny29q4kx foreign key (id_grupo) references permissao
alter table usuario_grupo add constraint FK9huj1upwjyabwkwnpnhnernnu foreign key (id_usuario) references usuario
insert into cozinha (nome) values('Tailandesa')
insert into cozinha (nome) values('Indiana')
insert into cozinha (nome) values('Japonesa')
insert into cozinha (nome) values('Brasileira')
insert into estado (uf,nome) values ('AC','Acre')
insert into estado (uf,nome) values ('AL','Alagoas')
insert into estado (uf,nome) values ('AP','Amapá')
insert into estado (uf,nome) values ('AM','Amazonas')
insert into estado (uf,nome) values ('BA','Bahia')
insert into estado (uf,nome) values ('CE','Ceará')
insert into estado (uf,nome) values ('DF','Distrito Federal')
insert into estado (uf,nome) values ('ES','Espírito Santo')
insert into estado (uf,nome) values ('GO','Goiás')
insert into estado (uf,nome) values ('MA','Maranhão')
insert into estado (uf,nome) values ('MT','Mato Grosso')
insert into estado (uf,nome) values ('MS','Mato Grosso do Sul')
insert into estado (uf,nome) values ('MG','Minas Gerais')
insert into estado (uf,nome) values ('PA','Pará')
insert into estado (uf,nome) values ('PB','Paraíba')
insert into estado (uf,nome) values ('PR','Paraná')
insert into estado (uf,nome) values ('PE','Pernambuco')
insert into estado (uf,nome) values ('PI','Piauí')
insert into estado (uf,nome) values ('RJ','Rio de Janeiro')
insert into estado (uf,nome) values ('RN','Rio Grande do Norte')
insert into estado (uf,nome) values ('RS','Rio Grande do Sul')
insert into estado (uf,nome) values ('RO','Rondônia')
insert into estado (uf,nome) values ('RR','Roraima')
insert into estado (uf,nome) values ('SC','Santa Catarina')
insert into estado (uf,nome) values ('SP','São Paulo')
insert into estado (uf,nome) values ('SE','Sergipe')
insert into estado (uf,nome) values ('TO','Tocantins')
INSERT INTO cidade(nome, estado_id) VALUES ('Planaltina', 7),('Guará',7),('Plano Piloto',7),('Gama',7)
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 1', 5.55, 1, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 2', 10.55, 1, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',2,'Rest. Indiana 1', 14.55, 2, now(),now())
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',3,'Rest. Japonês', 9.55, 3, now(),now())
INSERT INTO public.forma_pagamento(descricao) VALUES ('Cartão de Crédito'), ('Cartão de Débito'),('Cheque'),('Dinheiro')
insert into restaurante_forma_pagamento (id_restaurante, id_forma_pagamento) values(1,1), (1,2),(1,3),(2,1),(2,3), (2,4), (3,1),(3,2),(3,4), (4,1),(4,2),(4,3), (4,4)
INSERT INTO public.produto(ativo, descricao, nome, preco, id_restaurante) VALUES (true, 'Prato 1', 'Prato 1',10.90, 1),(true, 'Prato 2', 'Prato 2',5.90, 1),(true, 'Prato 3', 'Prato 3',3.90, 1),(true, 'Prato 4', 'Prato 4',5.90, 2),(true, 'Prato 5', 'Prato 5',5.50, 3),(true, 'Prato 6', 'Prato 6',5.60, 4)
