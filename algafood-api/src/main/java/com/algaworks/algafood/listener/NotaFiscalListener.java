package com.algaworks.algafood.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.service.AtivacaoClienteEvent;

@Component
public class NotaFiscalListener {
	
	@EventListener
	public void imprimirNotaFiscal(AtivacaoClienteEvent event) {
		System.out.println("------------Imprimir:"+event.getCliente().getNome());
	}
}
