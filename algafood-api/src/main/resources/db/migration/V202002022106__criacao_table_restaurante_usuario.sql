CREATE TABLE public.restaurante_usuario
(
  id_restaurante integer NOT NULL,
  id_usuario integer NOT NULL,
  CONSTRAINT pk_restaurante_usuario PRIMARY KEY (id_restaurante, id_usuario),
  CONSTRAINT fk_restaurante_usuario_restaurante FOREIGN KEY (id_restaurante)
      REFERENCES public.restaurante (id_restaurante) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_restaurante_usuario_usuario FOREIGN KEY (id_usuario)
      REFERENCES public.usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
