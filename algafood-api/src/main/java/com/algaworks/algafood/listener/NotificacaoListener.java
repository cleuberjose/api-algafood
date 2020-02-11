package com.algaworks.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.NotificadorTipo;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;
import com.algaworks.algafood.di.service.AtivacaoClienteEvent;

@Component
public class NotificacaoListener {
	
	@Autowired
	@TipoDoNotificador(NotificadorTipo.NORMAL)
	private Notificador notificador;
	
	@EventListener
	public void notificarCliente(AtivacaoClienteEvent ativacaoClienteEvent) {
		notificador.notificar(ativacaoClienteEvent.getCliente(), "O cliente foi notifidado");
	}
}
