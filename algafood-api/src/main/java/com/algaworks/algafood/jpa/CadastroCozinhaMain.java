package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class CadastroCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicatonContext= new SpringApplicationBuilder(AlgafoodApiApplication.class).
				web(WebApplicationType.NONE).run(args);
		
		CozinhaRepository cozinhaRepository=applicatonContext.getBean(CozinhaRepository.class);
		RestauranteRepository restauranteRepository=applicatonContext.getBean(RestauranteRepository.class);
		
//		Cozinha cozinha= new Cozinha();
//		cozinha.setNome("Brasileira");
//		
//		Cozinha cozinha2= new Cozinha();
//		cozinha2.setNome("Japonesa");	
//		
//		cozinhaRepository.salvar(cozinha);
//		cozinhaRepository.salvar(cozinha2);
		
		
		System.out.println("-------Cozinhas");
		for(Cozinha coz: cozinhaRepository.findAll()) {
			System.out.println("cozinha: "+coz.getId()+" -> "+coz.getNome());
		}
		System.out.println("-------Restaurantes");
		for(Restaurante rest: restauranteRepository.findAll()) {
			System.out.println("restaurante: "+rest.getId()+" -> "+rest.getNome()+" -> "+rest.getTaxaFrete()+
					" -> "+rest.getCozinha().getNome());
		}
		
//		Cozinha coz1= cozinhaRepository.pesquisarPorId(3);
//		System.out.println("cozinha: "+coz1.getId()+" -> "+coz1.getNome());
//		coz1.setNome("Brasileira Gaucha");
//		cozinhaRepository.salvar(coz1);
//		System.out.println("---------------Excluir----------");
//		coz1= cozinhaRepository.pesquisarPorId(3);
//		System.out.println("cozinha: "+coz1.getId()+" -> "+coz1.getNome());
//		System.out.println("---------------Excluir----------");
//		cozinhaRepository.remover(coz1);
//		System.out.println("---------------Listar----------");
//		for(Cozinha coz: cozinhaRepository.listar()) {
//			System.out.println("cozinha: "+coz.getId()+" -> "+coz.getNome());
//		}
		
		
		
	}

}
