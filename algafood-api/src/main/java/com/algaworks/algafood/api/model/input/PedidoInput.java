package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.domain.model.PedidoStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@Valid
	@NotNull
	private UsuarioIdInput usuario;
	
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PedidoStatus pedidoStatus;
	
	@NotNull
	private BigDecimal subtotal;
	
	@NotNull
	private BigDecimal taxaFrete;
	
	@NotNull
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	
	private OffsetDateTime dataConfirmacao;
	
	private OffsetDateTime dataCancelamento;
	
	private OffsetDateTime dataEntrega;
}
