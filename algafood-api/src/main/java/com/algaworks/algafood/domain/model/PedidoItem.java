package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algafood.core.validation.Groups.PedidoId;
import com.algaworks.algafood.core.validation.Groups.ProdutoId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="pedido_item")
public class PedidoItem {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido_item")
	private Integer id;
	
	@Valid
	@ConvertGroup(from=Default.class, to=PedidoId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_pedido", referencedColumnName = "id_pedido", nullable = false)
	private Pedido pedido;
	
	@Valid
	@ConvertGroup(from=Default.class, to=ProdutoId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_produto", referencedColumnName = "id_produto", nullable = false)
	private Produto produto;
	
	@NotNull
	@Column (nullable = false)
	private Integer quantidade;
	
	@NotNull
	@Column (name="preco_unitario",nullable = false)
	private BigDecimal precoUnitario;
	
	@NotNull
	@Column (name="preco_total",nullable = false)
	private BigDecimal precoTotal;
	
	@NotBlank
	@Column (nullable = false)
	private String observacao;
}
