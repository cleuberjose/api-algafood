package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {	
	
	private static final String MSG_UM_OU_MAIS_CAMPO_INVALIDO = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

	@Autowired
	private MessageSource messageSource;
		
	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";
	
	@ExceptionHandler(ValidacaoException.class)
	private ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

	    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
	    String detail = MSG_UM_OU_MAIS_CAMPO_INVALIDO;
	    
	    BindingResult bindingResult = ex.getBindingResult();
	    
	    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
	    		.map(objectError -> {
	    			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
	    			
	    			String name = objectError.getObjectName();
	    			
	    			if (objectError instanceof FieldError) {
	    				name = ((FieldError) objectError).getField();
	    			}
	    			
	    			return Problem.Object.builder()
	    				.name(name)
	    				.userMessage(message)
	    				.build();
	    		})
	    		.collect(Collectors.toList());
	    
	    Problem problem=createProblemBuilder(status, problemType,detail, detail).objects(problemObjects).build();
	    
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

	    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
	    String detail = MSG_UM_OU_MAIS_CAMPO_INVALIDO;
	    
	    BindingResult bindingResult = ex.getBindingResult();
	    
	    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
	    		.map(objectError -> {
	    			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
	    			
	    			String name = objectError.getObjectName();
	    			
	    			if (objectError instanceof FieldError) {
	    				name = ((FieldError) objectError).getField();
	    			}
	    			
	    			return Problem.Object.builder()
	    				.name(name)
	    				.userMessage(message)
	    				.build();
	    		})
	    		.collect(Collectors.toList());
	    
	    Problem problem=createProblemBuilder(status, problemType,detail, detail).objects(problemObjects).build();
	    
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){
		HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType=ProblemType.ERRO_DE_SISTEMA;
		String detail=MSG_ERRO_GENERICA_USUARIO_FINAL;
		ex.printStackTrace();
		Problem problem=createProblemBuilder(status, problemType,detail, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ProblemType problemType=ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail=String.format("O recurso '%s', que você tentou acessar, é inexistente.",ex.getRequestURL());
		Problem problem= createProblemBuilder(status, problemType, detail,MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex
			, WebRequest request){
		HttpStatus status=HttpStatus.BAD_REQUEST;
		ProblemType problemType=ProblemType.PARAMENTRO_INVALIDO;

		String detail=String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido." + 
				"Corrija e informe o valor compatível com o tipo %s.",ex.getParameter().getParameterName(),ex.getValue(),ex.getRequiredType().getSimpleName());
		Problem problem= createProblemBuilder(status, problemType, detail,MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	private ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		HttpStatus status=HttpStatus.NOT_FOUND;
		ProblemType problemType=ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail=ex.getMessage();
		
		Problem problem=createProblemBuilder(status, problemType,detail,detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}	
	@ExceptionHandler(EntidadeEmUsoException.class)
	private ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		
		HttpStatus status=HttpStatus.CONFLICT;
		ProblemType problemType=ProblemType.ENTIDADE_EM_USO;
		String detail=ex.getMessage();
		
		Problem problem=createProblemBuilder(status, problemType,detail,detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	@ExceptionHandler(NegocioException.class)
	private ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
		HttpStatus status=HttpStatus.BAD_REQUEST;
		ProblemType problemType=ProblemType.ERRO_NEGOCIO;
		String detail=ex.getMessage();
		
		Problem problem=createProblemBuilder(status, problemType,detail,detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	//noHandleFoundException
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request); 
		}
		
		ProblemType problemType=ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail="O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problem problem=createProblemBuilder(status, problemType,detail,MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		// Criei o método joinPath para reaproveitar em todos os métodos que precisam
		// concatenar os nomes das propriedades (separando por ".")
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não existe. "
				+ "Corrija ou remova essa propriedade e tente novamente.", path);

		Problem problem = createProblemBuilder(status, problemType, detail,MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemType problemType=ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		String path=ex.getPath().stream().map(ref->ref.getFieldName()).collect(Collectors.joining("."));
			
		String detail=String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path,ex.getValue(),ex.getTargetType().getSimpleName());
		
		Problem problem=createProblemBuilder(status, problemType,detail,MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problem,headers, status, request);
	}
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body==null) {
			body=Problem.builder()
					.timestamp(OffsetDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.build();
		}else if (body instanceof String) {
			body = Problem.builder()
					.timestamp(OffsetDateTime.now())
					.title((String) body)
					.status(status.value())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.build();		
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail, String userMessage) {
		return Problem.builder()
				.detail(detail)
				.type(problemType.getUri())
				.status(status.value())
				.userMessage(userMessage)
				.title(problemType.getTitle())
				.timestamp(OffsetDateTime.now());
	}
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}
}
