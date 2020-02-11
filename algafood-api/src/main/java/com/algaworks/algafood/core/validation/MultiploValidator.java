package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number>{
	
	private int numero;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numero=constraintAnnotation.numero();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean valido=true;
		if(value!=null) {
		BigDecimal n1=BigDecimal.valueOf(value.doubleValue());
		BigDecimal n2=BigDecimal.valueOf(numero);
		valido= n1.remainder(n2).compareTo(BigDecimal.ZERO)==0;
		}
		return valido;
	}

}
