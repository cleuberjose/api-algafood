package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Controller
@RequestMapping("teste")
public class TesteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("entre-valores")
	public List<Restaurante> pesquisarEntreTaxaEntrega(BigDecimal valorInicio, BigDecimal valorFim){
		return restauranteRepository.queryByTaxaFreteBetween(valorInicio, valorFim);
		
	}
}
