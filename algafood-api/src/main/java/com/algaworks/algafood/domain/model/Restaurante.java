package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups.CozinhaId;
import com.algaworks.algafood.core.validation.Groups.RestauranteId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@NotNull(groups = RestauranteId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_restaurante")
	private Integer id;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	@Valid
	@ConvertGroup(from=Default.class, to = CozinhaId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cozinha", referencedColumnName = "id_cozinha", nullable = false)
	private Cozinha cozinha;
	
	@NotBlank
	@Column(length = 30, nullable = false)
	private String nome;
	
//	@DecimalMin(value = "0")
	@PositiveOrZero
//	@TaxaFrete
//	@Multiplo(numero = 5)
	@NotNull
	@Column(name="taxa_frete",precision = 10, scale=2, nullable=false)
	private BigDecimal taxaFrete;	
	
	private Boolean ativo=true;
	
	private Boolean aberto=false;
	
//	@JsonIgnore
	@CreationTimestamp
	@Column(name="data_cadastro", nullable = false,columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
//	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable=false,columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
//	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", 
	joinColumns = @JoinColumn(name="id_restaurante"),
	inverseJoinColumns = @JoinColumn(name="id_forma_pagamento"))
	private Set<FormaPagamento> formaPagamentos= new HashSet<FormaPagamento>();
	
	@ManyToMany
	@JoinTable(name="restaurante_usuario",
	joinColumns = @JoinColumn(name="id_restaurante"), 
	inverseJoinColumns = @JoinColumn(name="id_usuario"))
	private Set<Usuario> usuarios= new HashSet<Usuario>();
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurante")
	private List<Produto> produtos= new ArrayList<Produto>();
	
	public void ativar() {
		setAtivo(true);
	}
	public void inativar() {
		setAtivo(false);
	}
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormaPagamentos().add(formaPagamento);
		
	}
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormaPagamentos().remove(formaPagamento);		
	}
	public void abrir() {
		setAberto(true);		
	}	
	public void fechar() {
		setAberto(false);
	}
	public boolean removerUsuario(Usuario usuario) {
		return getUsuarios().remove(usuario);		
	}
	public boolean adicionarUsuario(Usuario usuario) {
		return getUsuarios().add(usuario);
	}
	
}
