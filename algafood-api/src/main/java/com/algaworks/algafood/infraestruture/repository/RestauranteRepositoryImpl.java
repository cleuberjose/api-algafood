package com.algaworks.algafood.infraestruture.repository;

import static com.algaworks.algafood.infraestruture.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infraestruture.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;

	@Override
	public List<Restaurante> find(String nome, BigDecimal valorInicio, BigDecimal valorFim){			
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteriaQuery=criteriaBuilder.createQuery(Restaurante.class);
		Root<Restaurante> root=criteriaQuery.from(Restaurante.class);

		var predicates= new ArrayList<Predicate>();
		if(StringUtils.hasLength(nome)) {
			predicates.add(criteriaBuilder.like(root.get("nome"), "%"+nome+"%"));
		}
		if(valorInicio!=null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(
					root.get("taxaFrete"),valorInicio));
		}
		if(valorFim!=null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(
					root.get("taxaFrete"),valorFim));
		}
		criteriaQuery.where(predicates.toArray(new Predicate[0]));

		return entityManager.createQuery(criteriaQuery).getResultList();



		//		var query= new StringBuffer();
		//		query.append("from Restaurante where 1=1 ");
		//		Map<String,Object> params= new HashMap<String,Object>();
		//		if(StringUtils.hasLength(nome)) {
		//			query.append("AND nome like :nome ");
		//			params.put("nome","%"+nome+"%");
		//		}
		//		if(valorInicio!=null) {
		//			query.append("AND taxaFrete >= :valorInicio ");
		//			params.put("valorInicio", valorInicio);
		//		}
		//		if(valorFim!=null) {
		//			query.append("AND taxaFrete<=:valorFim ");
		//			params.put("valorFim", valorFim);
		//		}		
		//		TypedQuery<Restaurante> retorno= entityManager.createQuery(query.toString(), Restaurante.class);
		//		
		//		params.forEach((chave, valor)->{
		//			retorno.setParameter(chave, valor);
		//		});
		//		return retorno.getResultList();
	}

	@Override
	public List<Restaurante> pesquisarNomeFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

}
