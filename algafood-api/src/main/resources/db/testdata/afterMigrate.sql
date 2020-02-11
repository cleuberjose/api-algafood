SET timezone='UTC';
delete from produto;
delete from restaurante_forma_pagamento;
delete from restaurante;
delete from restaurante_usuario;
delete from usuario_grupo;
delete from usuario;
delete from cozinha;
delete from cidade;
delete from estado;
delete from grupo_permissao;
delete from permissao;
delete from grupo;
delete from forma_pagamento;
delete from pedido;
delete from pedido_item;

ALTER SEQUENCE restaurante_id_restaurante_seq RESTART WITH 1;
ALTER SEQUENCE cozinha_id_cozinha_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_estado_seq RESTART WITH 1;
ALTER SEQUENCE cidade_id_cidade_seq RESTART WITH 1;
ALTER SEQUENCE forma_pagamento_id_forma_pagamento_seq RESTART WITH 1;
ALTER SEQUENCE grupo_id_grupo_seq RESTART WITH 1;
ALTER SEQUENCE permissao_id_permissao_seq RESTART WITH 1;
ALTER SEQUENCE produto_id_produto_seq RESTART WITH 1;
ALTER SEQUENCE usuario_id_usuario_seq RESTART WITH 1;
ALTER SEQUENCE pedido_id_pedido_seq RESTART WITH 1;
ALTER SEQUENCE pedido_item_id_pedido_item_seq RESTART WITH 1;


insert into cozinha (nome) values('Tailandesa');
insert into cozinha (nome) values('Indiana');
insert into cozinha (nome) values('Japonesa');
insert into cozinha (nome) values('Brasileira');

insert into grupo (nome) values('Gerente');
insert into grupo (nome) values('Administrador');
insert into grupo (nome) values('Caixa');
insert into grupo (nome) values('Vendedor');

insert into estado (uf,nome) values ('AC','Acre');
insert into estado (uf,nome) values ('AL','Alagoas');
insert into estado (uf,nome) values ('AP','Amapá');
insert into estado (uf,nome) values ('AM','Amazonas');
insert into estado (uf,nome) values ('BA','Bahia');
insert into estado (uf,nome) values ('CE','Ceará');
insert into estado (uf,nome) values ('DF','Distrito Federal');
insert into estado (uf,nome) values ('ES','Espírito Santo');
insert into estado (uf,nome) values ('GO','Goiás');
insert into estado (uf,nome) values ('MA','Maranhão');
insert into estado (uf,nome) values ('MT','Mato Grosso');
insert into estado (uf,nome) values ('MS','Mato Grosso do Sul');
insert into estado (uf,nome) values ('MG','Minas Gerais');
insert into estado (uf,nome) values ('PA','Pará');
insert into estado (uf,nome) values ('PB','Paraíba');
insert into estado (uf,nome) values ('PR','Paraná');
insert into estado (uf,nome) values ('PE','Pernambuco');
insert into estado (uf,nome) values ('PI','Piauí');
insert into estado (uf,nome) values ('RJ','Rio de Janeiro');
insert into estado (uf,nome) values ('RN','Rio Grande do Norte');
insert into estado (uf,nome) values ('RS','Rio Grande do Sul');
insert into estado (uf,nome) values ('RO','Rondônia');
insert into estado (uf,nome) values ('RR','Roraima');
insert into estado (uf,nome) values ('SC','Santa Catarina');
insert into estado (uf,nome) values ('SP','São Paulo');
insert into estado (uf,nome) values ('SE','Sergipe');
insert into estado (uf,nome) values ('TO','Tocantins');

INSERT INTO cidade(nome, id_estado) VALUES ('Planaltina', 7),('Guará',7),('Plano Piloto',7),('Gama',7);

insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,id_cozinha, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 1', 5.55, 1, now(),now());
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,id_cozinha, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',1,'Rest. Tailandesa 2', 10.55, 1, now(),now());
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,id_cozinha, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',2,'Rest. Indiana 1', 14.55, 2, now(),now());
insert into restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, id_cidade,nome, taxa_frete,id_cozinha, data_cadastro, data_atualizacao) values('Vale','11111111','Complemento','Logradouro','100',3,'Rest. Japonês', 9.55, 3, now(),now());
	

INSERT INTO public.forma_pagamento(descricao) VALUES ('Cartão de Crédito'), ('Cartão de Débito'),('Cheque'),('Dinheiro');

insert into restaurante_forma_pagamento (id_restaurante, id_forma_pagamento) values(1,1), (1,2),(1,3),(2,1),(2,3), (2,4), (3,1),(3,2),(3,4), (4,1),(4,2),(4,3), (4,4);

INSERT INTO public.produto(ativo, descricao, nome, preco, id_restaurante) VALUES (true, 'Prato 1', 'Prato 1',10.90, 1),(true, 'Prato 2', 'Prato 2',5.90, 1),(true, 'Prato 3', 'Prato 3',3.90, 1),(true, 'Prato 4', 'Prato 4',5.90, 2),(true, 'Prato 5', 'Prato 5',5.50, 3),(true, 'Prato 6', 'Prato 6',5.60, 4);

INSERT INTO public.usuario(data_cadastro, email, nome, senha) VALUES (now(), 'teste1@algafood.com', 'Teste1', '123');
INSERT INTO public.usuario(data_cadastro, email, nome, senha) VALUES (now(), 'teste2@algafood.com', 'Teste2', '123');
INSERT INTO public.usuario(data_cadastro, email, nome, senha) VALUES (now(), 'teste3@algafood.com', 'Teste3', '123');
INSERT INTO public.usuario(data_cadastro, email, nome, senha) VALUES (now(), 'teste4@algafood.com', 'Teste4', '123');

INSERT INTO public.permissao(descricao, nome) VALUES 
('Consultar produto','CONSULTAR_PRODUTO'),
('Consultar restaurante','CONSULTAR_RESTAURANTE'),
('Cadastrar restaurante','CADASTRAR_RESTAURANTE'),
('Cadastrar estado','CADASTRAR_ESTADO');

INSERT INTO public.grupo_permissao( id_grupo, id_permissao) VALUES (1, 1),(1, 2),(1, 3),(1,4),(2, 1),
	(2, 2),(2, 3),(2,4),(3, 1),(3, 2),(3, 3),(3,4),(4, 1),(4, 2);
	
insert into restaurante_usuario (id_restaurante, id_usuario) values  (1, 1),(1, 2),(1, 3),(1,4),(2, 1),
	(2, 2),(2, 3),(2,4),(3, 1),(3, 2),(3, 3),(3,4),(4, 1),(4, 2);
