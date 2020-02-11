package com.algaworks.algafood.infraestruture.repository.spec;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

public class RestauranteSpecs {
	public static Specification<Restaurante> comFreteGratis() {
		return new Specification<Restaurante>() {
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
			}
		};
	}
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return (root, query, criteriaBuilder)->{
			return criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
		};
	}

}
