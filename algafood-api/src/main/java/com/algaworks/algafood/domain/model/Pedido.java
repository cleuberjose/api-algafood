package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.core.validation.Groups.FormaPagamentoId;
import com.algaworks.algafood.core.validation.Groups.PedidoId;
import com.algaworks.algafood.core.validation.Groups.RestauranteId;
import com.algaworks.algafood.core.validation.Groups.UsuarioId;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@NotNull(groups = PedidoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Integer id;
	
	@Valid
	@ConvertGroup(from=Default.class, to=UsuarioId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@Valid
	@ConvertGroup(from=Default.class,to = RestauranteId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_restaurante", referencedColumnName = "id_restaurante", nullable = false)
	private Restaurante restaurante;
	
	@Valid
	@ConvertGroup(from=Default.class, to=FormaPagamentoId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_forma_pagamento", referencedColumnName = "id_forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="pedido_status", nullable = false)
	private PedidoStatus pedidoStatus;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@NotNull
	@Column(name = "taxa_frete",nullable = false)
	private BigDecimal taxaFrete;
	
	@NotNull
	@Column(name = "valor_total",nullable = false)
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	@Column(name = "data_criacao",nullable = false)
	private OffsetDateTime dataCriacao;
	
	@Column(name = "data_confirmacao")
	private OffsetDateTime dataConfirmacao;
	
	@Column(name = "data_cancelamento")
	private OffsetDateTime dataCancelamento;
	
	@Column(name = "data_entrega")
	private OffsetDateTime dataEntrega;
}
