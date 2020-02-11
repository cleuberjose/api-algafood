package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer id;
	
	@NotBlank
	@Column(nullable = false,length=50)
	private String nome;
	
	@Email
	@NotBlank
	@Column(nullable = false,length=50)
	private String email;
	
	@NotBlank
	@Column(nullable = false,length=128)
	private String senha;
		
	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name="usuario_grupo",
	joinColumns = @JoinColumn(name="id_usuario"),
	inverseJoinColumns = @JoinColumn(name="id_grupo"))
	private Set<Grupo> grupos= new HashSet<Grupo>();

	public boolean senhaNaoCoincideCom(String senha) {
		return !this.senha.equals(senha);
	}
	public void adicionarGrupo(Grupo grupo) {
		getGrupos().add(grupo);		
	}
	public void removerGrupo(Grupo grupo) {
		getGrupos().remove(grupo);		
	}	
}
