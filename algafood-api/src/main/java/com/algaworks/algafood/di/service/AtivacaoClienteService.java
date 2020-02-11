package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NotificadorProperties;

@Component
public class AtivacaoClienteService {
	
	@Autowired
	private NotificadorProperties notificadorProperties;
	
//	@Value("${teste.dos.cachorros}")
//	private String testePropriedade;
//	@TipoDoNotificador(NotificadorTipo.URGENTE)
//	@Autowired
//	private Notificador notificador;	
	
//	public AtivacaoClienteService(String teste) {
//		
//	}
//	@Autowired
//	public AtivacaoClienteService(Notificador notificador) {
//		System.out.println("AtivadorCliente "+notificador);
//		this.notificador = notificador;
//	}
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();	
		System.out.println("Propriedade: "+notificadorProperties.getCachorroParaguaio());
		applicationEventPublisher.publishEvent(new AtivacaoClienteEvent(cliente));
//		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
	}
//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}

}
