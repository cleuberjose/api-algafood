package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.core.validation.Groups.FormaPagamentoId;
import com.algaworks.algafood.core.validation.Groups.PedidoId;
import com.algaworks.algafood.core.validation.Groups.RestauranteId;
import com.algaworks.algafood.core.validation.Groups.UsuarioId;
import com.algaworks.algafood.domain.exception.NegocioException;
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
	
	private String codigo;
	
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
	
	@Embedded
	private Endereco endereco;
	
	@Valid
	@ConvertGroup(from=Default.class, to=FormaPagamentoId.class)
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_forma_pagamento", referencedColumnName = "id_forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="pedido_status", nullable = false)
	private PedidoStatus pedidoStatus=PedidoStatus.CRIADO;
	
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
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<PedidoItem> pedidoItens= new ArrayList<PedidoItem>();
	
	public void confirmar() {
		setPedidoStatus(PedidoStatus.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}
	public void cancelar() {
		setPedidoStatus(PedidoStatus.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
	}
	public void entregar() {
		setPedidoStatus(PedidoStatus.ENTREGUE);
		setDataCancelamento(OffsetDateTime.now());
	}
	
	private void setPedidoStatus(PedidoStatus pedidoStatus) {
		if(getPedidoStatus().naoPodeAlterarPara(pedidoStatus)) {
			throw new NegocioException(
					String.format("Status do pedido %s não pode ser alterado de %s para %s", 
							getCodigo(), getPedidoStatus().getDescricao(), pedidoStatus.getDescricao()));
		}
		this.pedidoStatus=pedidoStatus;
	}
	
	public void calcularValorTotal() {
		getPedidoItens().forEach(PedidoItem::calcularPrecoTotal);
		
		this.subtotal = getPedidoItens().stream()
			.map(item -> item.getPrecoTotal())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
}
