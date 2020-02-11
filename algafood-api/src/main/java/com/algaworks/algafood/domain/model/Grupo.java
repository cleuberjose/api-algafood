package com.algaworks.algafood.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo")
	private Integer id;
	
	@NotBlank
	@Column(length = 30)
	private String nome;
	
	@ManyToMany
	@JoinTable(name="grupo_permissao",
	joinColumns = @JoinColumn(name="id_grupo"),
	inverseJoinColumns = @JoinColumn(name="id_permissao"))
	private Set<Permissao> permissoes= new HashSet<Permissao>();

	public void adicionarPermissao(Permissao permissao) {
		getPermissoes().add(permissao);
		
	}

	public void removerPermissao(Permissao permissao) {
		getPermissoes().remove(permissao);		
	}
}
