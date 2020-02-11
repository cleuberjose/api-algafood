package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class Endereco {
	
	
	@Column(name="endereco_cep", nullable = false, length = 10)
	private String cep;
	
	
	@Column(name="endereco_logradouro", nullable = false, length = 80)
	private String logradouro;
	
	
	@Column(name="endereco_numero", nullable = false, length = 10)
	private String numero;
	
	
	@Column(name="endereco_complemento", length = 50)
	private String complemento;
	
	
	@Column(name="endereco_bairro", nullable = false, length = 40)
	private String bairro;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_cidade", nullable = false)
	private Cidade cidade;
	
}
