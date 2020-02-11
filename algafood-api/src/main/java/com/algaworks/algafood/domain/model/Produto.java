package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algafood.core.validation.Groups.ProdutoId;
import com.algaworks.algafood.core.validation.Groups.RestauranteId;

import lombok.Data;


@Data
@Entity
public class Produto {
	
	@NotNull(groups = ProdutoId.class)
	@Id
	@Column(name="id_produto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Valid
	@ConvertGroup(from=Default.class, to=RestauranteId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_restaurante", referencedColumnName = "id_restaurante", nullable = false)
	private Restaurante restaurante;
	
	@NotBlank
	@Column(length=30,nullable=false)
	private String nome;
	
	@NotBlank
	@Column(length=128,nullable=false)
	private String descricao;
	
	@PositiveOrZero
	@NotNull
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal preco;
	
	@NotNull
	@Column(nullable=false)
	private Boolean ativo;
}
