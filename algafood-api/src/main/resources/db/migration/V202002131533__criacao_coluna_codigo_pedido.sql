CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

alter table pedido add column codigo varchar(36) not null default uuid_generate_v4();
