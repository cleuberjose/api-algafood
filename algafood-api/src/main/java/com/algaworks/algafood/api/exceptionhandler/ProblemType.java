package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado")
	,ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso")
	,ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio")
	,MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incomprieensivel"), 
	PARAMENTRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
	ERRO_DE_SISTEMA("Erro de Sistema","/erro-de-sistema"), 
	DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos");
	
	private String title;
	private String uri;
	
	ProblemType(String title, String path) {
		this.title = title;
		this.uri ="http://api.algafood.local:8080"+path;
	}
	

}
