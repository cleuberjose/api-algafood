package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("teste.dos")
public class NotificadorProperties {
	/**
	 * Mestre dos magos
	 */
	private String cachorroParaguaio="Cachorro paraguaio";

	public String getCachorroParaguaio() {
		return cachorroParaguaio;
	}
	public void setCachorroParaguaio(String cachorroParaguaio) {
		this.cachorroParaguaio = cachorroParaguaio;
	}
	
}
