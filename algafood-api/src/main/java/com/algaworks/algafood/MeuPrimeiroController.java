package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService=ativacaoClienteService;
	}

	@GetMapping("/ola")
	@ResponseBody
	public String hello() {
		Cliente cliente= new Cliente("Cléuber José","teste@teste.com.br","1234123412");
		ativacaoClienteService.ativar(cliente);
		return "Olá, mestre dos magos2";
	}
}
